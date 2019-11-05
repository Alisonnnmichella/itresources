package com.codeoftheweb.salvo.Controllers;

import com.codeoftheweb.salvo.Modelo.*;
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
public class GameController {
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

    @RequestMapping("/games")
    public Map<String, Object> games(Authentication authentication) {

        Map<String, Object> dto = new LinkedHashMap<>();
        if (isGuest(authentication)) {
            dto.put("player", "Guest");
        } else {
            Player player = playerRepository.findByUserName(authentication.getName());
            dto.put("player", player.getDTO());

        }
        dto.put("games", gameRepository.findAll().stream().map(game -> game.makeCreationDTO()).collect(Collectors.toList()));

        return dto;
    }


    @PostMapping("/games")
    public ResponseEntity<Map<String, Object>> registerGame(Authentication authentication) {
        if (isGuest(authentication))
            return new ResponseEntity<>(makeMap("error", ErrorMessages.NOTLOGGED.getMessage()), HttpStatus.UNAUTHORIZED);

        Player player = playerRepository.findByUserName(authentication.getName());

        if (player == null)
            return new ResponseEntity<>(makeMap("error", ErrorMessages.PLAYERNULL.getMessage()), HttpStatus.UNAUTHORIZED);

        Game game = gameRepository.save(new Game(LocalDateTime.now()));
        GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(game, player, LocalDateTime.now()));
        return new ResponseEntity<>(makeMap("gpid", gamePlayer.getId()), HttpStatus.CREATED);

    }

    @PostMapping("/games/players/{gamePlayerId}/salvoes")
    public ResponseEntity<Map<String, Object>> createSalvoes(@PathVariable long gamePlayerId, @RequestBody Salvo salvo, Authentication authentication) {
        Player player = playerRepository.findByUserName(authentication.getName());
        GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).get();

        ResponseEntity<Map<String, Object>> responseEntity= runSafe( authentication, gamePlayer,gamePlayerId);
        if(responseEntity!=null)
            return responseEntity;

        if (gamePlayer.getSalvoSet().stream().anyMatch(salvo1 -> salvo1.getTurn() == salvo.getTurn()))
            return new ResponseEntity<>(makeMap("error", ErrorMessages.CHEAT.getMessage())
                    , HttpStatus.FORBIDDEN);

        if(gamePlayer.getSalvoSet().isEmpty()){
            salvo.setTurn(1);
        }
        GamePlayer opponent = gamePlayer.getOpponent();
        if (opponent != null) {
            if (gamePlayer.getSalvoSet().size() <= opponent.getSalvoSet().size()) {
                salvo.setTurn(gamePlayer.getSalvoSet().size() + 1);
                salvo.setGamePlayer(gamePlayer);
            } else {
                return new ResponseEntity<>(makeMap("error", ErrorMessages.NOTSALVOES.getMessage()), HttpStatus.FORBIDDEN); }
        }
        else {
            return new ResponseEntity<>(makeMap("error", ErrorMessages.OPPONENTNULL.getMessage()),HttpStatus.FORBIDDEN);
        }
        salvoRepository.save(salvo);
        return new ResponseEntity<>(makeMap("OK", "Salvo created"),HttpStatus.CREATED);
    }



    @GetMapping("/games/players/{gamePlayerId}/salvoes")
    public ResponseEntity<Map<String, Object>> getSalvoes(@PathVariable long gamePlayerId, Authentication authentication) {


        Player player = playerRepository.findByUserName(authentication.getName());
        GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).get();
        ResponseEntity<Map<String, Object>> responseEntity= runSafe( authentication, gamePlayer,gamePlayerId);
        if(responseEntity!=null)
            return responseEntity;
          return new ResponseEntity<>(makeMap("OK", gamePlayer.getSalvoSet().stream().map(salvo -> salvo.getDto()).collect(Collectors.toList()))
                , HttpStatus.OK);

    }

    @PostMapping("/games/players/{gamePlayerId}/ships")
    public ResponseEntity<Map<String, Object>> createShips(@PathVariable long gamePlayerId, @RequestBody List<Ship> ships, Authentication authentication) {



        Player player = playerRepository.findByUserName(authentication.getName());
        GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).get();

        ResponseEntity<Map<String, Object>> responseEntity= runSafe( authentication, gamePlayer,gamePlayerId);
        if(responseEntity!=null)
            return responseEntity;
        if (gamePlayer.getShipSet().size() > 0||gamePlayer.getSalvoSet() == null)
            return new ResponseEntity<>(makeMap("error", ErrorMessages.SHIPSPLACED.getMessage())
                    , HttpStatus.FORBIDDEN);


        ships.forEach(ship -> ship.setGamePlayer(gamePlayer));
        ships.forEach(ship -> shipRepository.save(ship));

        return new ResponseEntity<>(makeMap("OK", "Ships positionated")
                , HttpStatus.CREATED);

    }

    @GetMapping("/games/players/{gamePlayerId}/ships")
    public ResponseEntity<Map<String, Object>> getShips(@PathVariable long gamePlayerId
            , Authentication authentication) {
        GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).get();
        ResponseEntity<Map<String, Object>> responseEntity=runSafe(authentication,gamePlayer,gamePlayerId);
        if(responseEntity!=null)
            return responseEntity;

        return new ResponseEntity<>(makeMap("OK", gamePlayer.getShipSet().stream()
                .map(ship -> ship.getDTO()).collect(Collectors.toList()))
                , HttpStatus.OK);

    }

    public ResponseEntity<Map<String,Object>> runSafe(Authentication authentication,GamePlayer gamePlayer,long gamePlayerId){
        Player player = playerRepository.findByUserName(authentication.getName());


        if (isGuest(authentication))
            return new ResponseEntity<>(makeMap("error", ErrorMessages.NOTLOGGED.getMessage())
                    , HttpStatus.UNAUTHORIZED);

        if (gamePlayer == null)
            return new ResponseEntity<>(makeMap("error", ErrorMessages.GAMEPLAYERNULL.getMessage())
                    , HttpStatus.UNAUTHORIZED);

        if (gamePlayer.getPlayer().getId() != player.getId())
            return new ResponseEntity<>(makeMap("error", ErrorMessages.CHEAT.getMessage())
                    , HttpStatus.UNAUTHORIZED);
        return null;
    }

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    public Map<String, Object> makeMap(String string, Object objeto) {
        Map<String, Object> soyUnMapa = new LinkedHashMap<>();
        soyUnMapa.put(string, objeto);
        return soyUnMapa;
    }

}
