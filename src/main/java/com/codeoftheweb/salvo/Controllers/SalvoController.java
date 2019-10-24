package com.codeoftheweb.salvo.Controllers;

import com.codeoftheweb.salvo.Modelo.GamePlayer;
import com.codeoftheweb.salvo.Modelo.Player;
import com.codeoftheweb.salvo.Modelo.Salvo;
import com.codeoftheweb.salvo.Modelo.Ship;
import com.codeoftheweb.salvo.Repositories.GamePlayerRepository;
import com.codeoftheweb.salvo.Repositories.GameRepository;
import com.codeoftheweb.salvo.Repositories.PlayerRepository;
import com.codeoftheweb.salvo.Repositories.ShipRepository;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/ids")
    public List<Object> getIds() {
        return gameRepository.findAll().stream().map(game -> game.getId()).collect(Collectors.toList());

    }

    @RequestMapping("/games")
    public Map <String,Object>  games(Authentication authentication) {
        Map <String,Object> dto= new LinkedHashMap<>();
        if(isGuest(authentication)){
            dto.put("player","Guest");
        }
        else{
            Player player=playerRepository.findByUserName(authentication.getName());
            dto.put("player",player.getDTO());

        }
         dto.put("games", gameRepository.findAll().stream().map(game -> game.makeCreationDTO()).collect(Collectors.toList()));
        return  dto;
}

    @RequestMapping("/ships")
    public List<Object> ships() {
        return shipRepository.findAll().stream().map(ship -> ship.getLocations()).collect(Collectors.toList());
    }


    @RequestMapping("/game_view/{id}")
    public  Map<String, Object> getGameView(@PathVariable Long id,Authentication authentication) {
      //  if(isGuest(authentication)){
        //    ResponseEntity responseEntity= new ResponseEntity(hacerMap("error","Pasa algo"),HttpStatus.UNAUTHORIZED);
        //}
        Map<String,Object> dto=new HashMap<>();
        GamePlayer gamePlayer =gamePlayerRepository.findById(id).get();
        dto.put("id",gamePlayer.getGame().getId());
        dto.put("created",gamePlayer.getGame().getLocalDateTime());
        dto.put("gamePlayers",gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1 -> gamePlayer1.makeDTO()));
        dto.put("ships",gamePlayer.getShipSet().stream().map(ship -> ship.getDTO()).collect(Collectors.toList()));
        dto.put("salvoes",gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1 -> gamePlayer1.getSalvoSet()).flatMap(salvos ->salvos.stream())
                .map(salvo -> salvo.getDto()).collect(Collectors.toList()));
        return dto;
    }

    @RequestMapping(path = "/players", method= RequestMethod.POST)
    public ResponseEntity<Object> register(
           @RequestParam String email, @RequestParam String password) {

        if (email.isEmpty() || password.isEmpty() ) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (playerRepository.findByUserName(email) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);

        }

        Player player=playerRepository.save(new Player(email, passwordEncoder.encode(password)));
//      // ResponseEntity responseEntity= new ResponseEntity(player.getDTO(),HttpStatus.CREATED);
//        //responseEntity.getHeaders().add("url","/game_view/"+player.getId());
//        return responseEntity;
        return  new ResponseEntity<>(HttpStatus.CREATED);
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
