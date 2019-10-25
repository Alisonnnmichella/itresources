package com.codeoftheweb.salvo.Controllers;

import com.codeoftheweb.salvo.Modelo.*;
import com.codeoftheweb.salvo.Modelo.GamePlayer;
import com.codeoftheweb.salvo.Repositories.*;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
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

    @RequestMapping("/ids")
    public List<Object> getIds() {
        return gameRepository.findAll().stream().map(game -> game.getId()).collect(Collectors.toList());

    }





    @RequestMapping("/ships")
    public List<Object> ships() {
        return shipRepository.findAll().stream().map(ship -> ship.getLocations()).collect(Collectors.toList());
    }


    @RequestMapping("/game_view/{id}")
        public  ResponseEntity<Map<String, Object>> getGameView(@PathVariable Long id,Authentication authentication) {
        if(isGuest(authentication))
            return new ResponseEntity<>(hacerMap("error","Usted no esta logueado."),HttpStatus.UNAUTHORIZED);


        Player player= playerRepository.findByUserName(authentication.getName());
        GamePlayer gamePlayer =gamePlayerRepository.findById(id).get();
        if(player==null)
            return new ResponseEntity<>(hacerMap("error","Usted no esta logueado."),HttpStatus.UNAUTHORIZED);

        if(gamePlayer==null)
            return new ResponseEntity<>(hacerMap("error","Problemas con el gamePlayer"),HttpStatus.UNAUTHORIZED);

        if(gamePlayer.getPlayer().getId()!=player.getId())
            return new ResponseEntity<>(hacerMap("error","Problemas con el gamePlayer")
                    ,HttpStatus.UNAUTHORIZED);

        Map<String,Object> hits= new HashMap<>();
        hits.put("self",new ArrayList<>());
        hits.put("opponent",new ArrayList<>());
        Map<String,Object> dto=new HashMap<>();
        dto.put("id",gamePlayer.getGame().getId());
        dto.put("created",gamePlayer.getGame().getLocalDateTime());
        dto.put("gameState","PLACESHIPS");
        dto.put("gamePlayers",gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1 -> gamePlayer1.makeDTO()));
        dto.put("ships",gamePlayer.getShipSet().stream().map(ship -> ship.getDTO()).collect(Collectors.toList()));
        dto.put("salvoes",gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1 -> gamePlayer1.getSalvoSet()).flatMap(salvos ->salvos.stream())
                .map(salvo -> salvo.getDto()).collect(Collectors.toList()));
        dto.put("hits",hits);
        return new ResponseEntity<>(dto
                ,HttpStatus.OK);
    }

    @RequestMapping(path = "/players", method= RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> register(
           @RequestParam String email, @RequestParam String password) {

        if (email.isEmpty() || password.isEmpty() ) {
            return new ResponseEntity<>(hacerMap("error","Paso algo"),HttpStatus.FORBIDDEN);
          }

        if (playerRepository.findByUserName(email) !=  null) {
            return new ResponseEntity<>(hacerMap("error","El mail ya esta siendo utlizado"),HttpStatus.FORBIDDEN);
           }

        Player player=playerRepository.save(new Player(email, passwordEncoder.encode(password)));

        return new ResponseEntity<>(hacerMap("OK","Todo bien"),HttpStatus.CREATED);

    }


    @PostMapping("/game/{id_juego}/players")
    public ResponseEntity<Map<String,Object>> unirseAlJuego
            (@PathVariable Long id_juego,Authentication authentication) {


        if(isGuest(authentication))
            return new ResponseEntity<>(
                    hacerMap("error","Usted no esta logueado.")
                    ,HttpStatus.UNAUTHORIZED);

        Player player= playerRepository.findByUserName(authentication.getName());

        if(player==null)
            return new ResponseEntity<>(
                    hacerMap("error","No lo encontramos en la base de datos"),
                    HttpStatus.UNAUTHORIZED);


        Game game= gameRepository.findById(id_juego).get();

        if(game==null)
            return new ResponseEntity<>(
                    hacerMap("error","No such game"),HttpStatus.FORBIDDEN);
        if(game.getGamePlayers().size()>=2)
            return new ResponseEntity<>(
                    hacerMap("error","Game is full"),HttpStatus.FORBIDDEN);

        GamePlayer gamePlayer= gamePlayerRepository.save(new GamePlayer(game,player,LocalDateTime.now()));
        return new ResponseEntity<>(hacerMap("gpid",gamePlayer.getId()),HttpStatus.CREATED);

    }




    private boolean isGuest(Authentication authentication){
        return authentication==null|| authentication instanceof AnonymousAuthenticationToken;
    }
    public Map<String,Object> hacerMap(String string,Object objeto) {
        Map<String, Object> soyUnMapa = new LinkedHashMap<>();
         soyUnMapa.put(string, objeto);
         return soyUnMapa;
    }

}
