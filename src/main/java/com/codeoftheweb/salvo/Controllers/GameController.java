package com.codeoftheweb.salvo.Controllers;

import com.codeoftheweb.salvo.Modelo.*;
import com.codeoftheweb.salvo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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
            return new ResponseEntity<>(hacerMap("error", "Usted no esta logueado."), HttpStatus.UNAUTHORIZED);

        Player player = playerRepository.findByUserName(authentication.getName());

        if (player == null)
            return new ResponseEntity<>(hacerMap("error", "Usted no esta logueado."), HttpStatus.UNAUTHORIZED);

        Game game = gameRepository.save(new Game(LocalDateTime.now()));
        GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(game, player, LocalDateTime.now()));
        return new ResponseEntity<>(hacerMap("gpid", gamePlayer.getId()), HttpStatus.CREATED);

    }

    @PostMapping("/games/players/{gamePlayerId}/salvoes")
    public ResponseEntity<Map<String, Object>> createSalvoes(@PathVariable long gamePlayerId, @RequestBody Salvo salvo, Authentication authentication) {

        if (isGuest(authentication))
            return new ResponseEntity<>(hacerMap("error", "Usted no esta logueado"), HttpStatus.UNAUTHORIZED);

        Player player = playerRepository.findByUserName(authentication.getName());
        GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).get();

        if (gamePlayer == null)
            return new ResponseEntity<>(hacerMap("error", "there is no game player with the given ID")
                    , HttpStatus.UNAUTHORIZED);

        if (gamePlayer.getPlayer().getId() != player.getId())
            return new ResponseEntity<>(hacerMap("error", "Problemas con el gamePlayer")
                    , HttpStatus.UNAUTHORIZED);


        if (gamePlayer.getSalvoSet().stream().anyMatch(salvo1 -> salvo1.getTurnNumber()==salvo.getTurnNumber()))
        return new ResponseEntity<>(hacerMap("error", "Already has ships placed")
                , HttpStatus.FORBIDDEN);

        salvo.setGamePlayer(gamePlayer);
        salvoRepository.save(salvo);

        return new ResponseEntity<>(hacerMap("OK", "Todo sigue igual de bien")
                , HttpStatus.CREATED);

    }

    @PostMapping("/games/players/{gamePlayerId}/ships")
    public ResponseEntity<Map<String, Object>> createShips(@PathVariable long gamePlayerId, @RequestBody List<Ship> ships, Authentication authentication) {

        if (isGuest(authentication))
            return new ResponseEntity<>(hacerMap("error", "Usted no esta logueado."), HttpStatus.UNAUTHORIZED);

        Player player = playerRepository.findByUserName(authentication.getName());
        GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).get();

        if (gamePlayer == null)
            return new ResponseEntity<>(hacerMap("error", "there is no game player with the given ID")
                    , HttpStatus.UNAUTHORIZED);

        if (gamePlayer.getPlayer().getId() != player.getId())
            return new ResponseEntity<>(hacerMap("error", "Problemas con el gamePlayer")
                    , HttpStatus.UNAUTHORIZED);


        if (gamePlayer.getShipSet().size() > 0)
            return new ResponseEntity<>(hacerMap("error", "Already has ships placed")
                    , HttpStatus.FORBIDDEN);

        if (gamePlayer.getSalvoSet() == null)
            return new ResponseEntity<>(hacerMap("error", "Already has ships placed")
                    , HttpStatus.FORBIDDEN);

        ships.forEach(ship -> ship.setGamePlayer(gamePlayer));
        ships.forEach(ship -> shipRepository.save(ship));

        return new ResponseEntity<>(hacerMap("OK", "Todo sigue igual de bien")
                , HttpStatus.CREATED);

    }

    @GetMapping("/games/players/{gamePlayerId}/ships")
    public ResponseEntity<Map<String, Object>> getShips(@PathVariable long gamePlayerId
            , Authentication authentication) {

        Player player = playerRepository.findByUserName(authentication.getName());
        GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).get();
        if (isGuest(authentication))
            return new ResponseEntity<>(hacerMap("error", "Usted no esta logueado.")
                    , HttpStatus.UNAUTHORIZED);

        if (gamePlayer == null)
            return new ResponseEntity<>(hacerMap("error", "there is no game player with the given ID")
                    , HttpStatus.UNAUTHORIZED);

        if (gamePlayer.getPlayer().getId() != player.getId())
            return new ResponseEntity<>(hacerMap("error", "Problemas con el gamePlayer")
                    , HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(hacerMap("OK", gamePlayer.getShipSet().stream().map(ship -> ship.getDTO()).collect(Collectors.toList()))
                , HttpStatus.OK);

    }

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    public Map<String, Object> hacerMap(String string, Object objeto) {
        Map<String, Object> soyUnMapa = new LinkedHashMap<>();
        soyUnMapa.put(string, objeto);
        return soyUnMapa;
    }
    @GetMapping("gamePlayers/{gamePlayerId}/damages/{turn}")
    public ResponseEntity<Object> getDamages(@PathVariable long gamePlayerId,@PathVariable int turn){
        GamePlayer gamePlayer= gamePlayerRepository.getOne(gamePlayerId);

        return new ResponseEntity<>(gamePlayer.hitsPorTurno(turn)
                , HttpStatus.OK);
    }


}
