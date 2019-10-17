package com.codeoftheweb.salvo;

import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @RequestMapping("/ids")
    public List<Object> getIds() {
        return gameRepository.findAll().stream().map(game -> game.getId()).collect(Collectors.toList());

    }

    @RequestMapping("/games")
    public List<Object> games() {
        return gameRepository.findAll().stream().map(game -> game.makeCreationDTO()).collect(Collectors.toList());
    }

    @RequestMapping("/ships")
    public List<Object> ships() {
        return shipRepository.findAll().stream().map(ship -> ship.getLocations()).collect(Collectors.toList());
    }
    @RequestMapping("/game_view/{id}")
    public  Map<String, Object> getGameView(@PathVariable Long id) {
        Map<String,Object> dto=new HashMap<>();
        GamePlayer gamePlayer =gamePlayerRepository.findById(id).get();
        dto.put("id",gamePlayer.getGame().getId());
        dto.put("created",gamePlayer.getGame().getLocalDate());
        dto.put("gamePlayers",gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1 -> gamePlayer1.getPlayer().getDTO()));
        dto.put("ships",gamePlayer.getShipSet().stream().map(ship -> ship.getDTO()).collect(Collectors.toList()));
        return dto;
    }


}

