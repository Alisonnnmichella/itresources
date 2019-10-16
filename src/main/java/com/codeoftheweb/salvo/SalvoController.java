package com.codeoftheweb.salvo;

import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    GameRepository gameRepository;

    @RequestMapping("/ids")
    public List<Object> getIds() {
        return gameRepository.findAll().stream().map(game -> game.getId()).collect(Collectors.toList());

    }

    @RequestMapping("/games")
    public List<Object> games() {
        return gameRepository.findAll().stream().map(game -> game.makeCreationDTO()).collect(Collectors.toList());

    }

}

