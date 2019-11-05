package com.codeoftheweb.salvo.Controllers;

import com.codeoftheweb.salvo.Modelo.*;
import com.codeoftheweb.salvo.Modelo.GamePlayer;
import com.codeoftheweb.salvo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    GamePlayerRepository gamePlayerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SalvoRepository salvoRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @RequestMapping("/ids")
    public List<Object> getIds() {
        return gameRepository.findAll().stream().map(game -> game.getId()).collect(Collectors.toList());

    }

    @RequestMapping("/ships")
    public List<Object> ships() {
        return shipRepository.findAll().stream().map(ship -> ship.getLocations()).collect(Collectors.toList());
    }


    @RequestMapping("/game_view/{id}")
    public ResponseEntity<Map<String, Object>> getGameView(@PathVariable Long id, Authentication authentication) {
        if (isGuest(authentication))
            return new ResponseEntity<>(makeMap("error", "You are not logged in."), HttpStatus.UNAUTHORIZED);


        Player player = playerRepository.findByUserName(authentication.getName());
        GamePlayer gamePlayer = gamePlayerRepository.findById(id).orElse(null);

        if (player == null)
            return new ResponseEntity<>(makeMap("error", "You are no logged in"), HttpStatus.UNAUTHORIZED);

        if (gamePlayer == null)
            return new ResponseEntity<>(makeMap("error", "Problemas con el gamePlayer"), HttpStatus.UNAUTHORIZED);

        if (gamePlayer.getPlayer().getId() != player.getId())
            return new ResponseEntity<>(makeMap("error", "Problemas con el gamePlayer")
                    , HttpStatus.UNAUTHORIZED);

        Map<String, Object> hits = new LinkedHashMap<>();
        hits.put("self", gamePlayer.dtoHitsAllTurns());
        hits.put("opponent", gamePlayer.getOpponent().dtoHitsAllTurns());
        Map<String, Object> dto = new HashMap<>();
        dto.put("id", gamePlayer.getGame().getId());
        dto.put("created", gamePlayer.getGame().getLocalDateTime());
        dto.put("gameState", gamePlayer.getState());
        updateScore(gamePlayer);
        dto.put("gamePlayers", gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1 -> gamePlayer1.idplayerdto()));
        dto.put("ships", gamePlayer.getShipSet().stream().map(ship -> ship.getDTO()).collect(Collectors.toList()));
        dto.put("salvoes", gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1 -> gamePlayer1.getSalvoSet()).flatMap(salvos -> salvos.stream())
                .map(salvo -> salvo.getDto()).collect(Collectors.toList()));
        dto.put("hits", hits);

        return new ResponseEntity<>(dto
                , HttpStatus.OK);
    }


    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> register(
            @RequestParam String email, @RequestParam String password) {

        if (email.isEmpty() && password.isEmpty()) {
            return new ResponseEntity<>(makeMap("error", ErrorMessages.MAILEMPTY.getMessage()+ErrorMessages.PASSWORDEMPTY.getMessage()), HttpStatus.FORBIDDEN);
        }
        if (password.isEmpty()) {
            return new ResponseEntity<>(makeMap("error", ErrorMessages.PASSWORDEMPTY.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (email.isEmpty()) {
            return new ResponseEntity<>(makeMap("error", ErrorMessages.MAILEMPTY.getMessage()), HttpStatus.FORBIDDEN);
        }

        if (playerRepository.findByUserName(email) != null) {
            return new ResponseEntity<>(makeMap("error", ErrorMessages.MAILINUSE.getMessage()), HttpStatus.FORBIDDEN);
        }

        Player player = playerRepository.save(new Player(email, passwordEncoder.encode(password)));

        return new ResponseEntity<>(makeMap("OK", "Everything is fine"), HttpStatus.CREATED);

    }


    @PostMapping("/game/{id_juego}/players")
    public ResponseEntity<Map<String, Object>> unirseAlJuego
            (@PathVariable Long id_juego, Authentication authentication) {


        if (isGuest(authentication))
            return new ResponseEntity<>(
                    makeMap("error", ErrorMessages.NOTLOGGED.getMessage())
                    , HttpStatus.UNAUTHORIZED);

        Player player = playerRepository.findByUserName(authentication.getName());

        if (player == null)
            return new ResponseEntity<>(
                    makeMap("error", ErrorMessages.PLAYERNULL.getMessage()),
                    HttpStatus.UNAUTHORIZED);


        Game game = gameRepository.findById(id_juego).get();

        if (game == null)
            return new ResponseEntity<>(
                    makeMap("error", ErrorMessages.GAMENULL.getMessage()), HttpStatus.FORBIDDEN);
        if (game.getGamePlayers().size() >= 2)
            return new ResponseEntity<>(
                    makeMap("error", ErrorMessages.OPPONENTNULL.getMessage()), HttpStatus.FORBIDDEN);

        GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(game, player, LocalDateTime.now()));
        return new ResponseEntity<>(makeMap("gpid", gamePlayer.getId()), HttpStatus.CREATED);

    }


    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    public Map<String, Object> makeMap(String string, Object objeto) {
        Map<String, Object> soyUnMapa = new LinkedHashMap<>();
        soyUnMapa.put(string, objeto);
        return soyUnMapa;
    }

    public void updateScore(GamePlayer gamePlayer) {
        Optional<ScoreValue> scoreValue = Arrays.stream(ScoreValue.values())
                .filter(scoreValue1 -> scoreValue1.toString().equalsIgnoreCase(gamePlayer.getState())).findFirst();
       if (scoreValue.isPresent()) {
           List<Score> scores = scoreRepository.findAll();
            Optional<Score> score = scores.stream().filter(score1 -> score1.getGame().getId() == gamePlayer.getGame().getId()
                    &&  score1.getPlayer().getId()==gamePlayer.getPlayer().getId() ).findFirst();
            if (!score.isPresent()) {
                scoreRepository.save(new Score(gamePlayer.getGame(), gamePlayer.getPlayer(), LocalDateTime.now(), scoreValue.get()));
            }
        }
    }


}
