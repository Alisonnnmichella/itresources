package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {


		SpringApplication.run(SalvoApplication.class, args);
	}

    Player jugadorAli=new Player("Ali");
    Player jugadorLau= new Player("Lauti");
    Game juego1= new Game(LocalDateTime.now());
    Game juego2= new Game(LocalDateTime.now().plusHours(1));
    GamePlayer gamePlayer1= new GamePlayer(juego1,jugadorAli,LocalDateTime.now());
    GamePlayer gamePlayer2= new GamePlayer(juego1,jugadorLau,LocalDateTime.now());
    GamePlayer gamePlayer3= new GamePlayer(juego2,jugadorAli,LocalDateTime.now());
    GamePlayer gamePlayer4= new GamePlayer(juego2,jugadorLau,LocalDateTime.now());
    List<String> locaciones= new ArrayList<>();
    List<String> locaciones2= new ArrayList<>();
    Ship ship1= new Ship(gamePlayer1,locaciones,"Carrier");
    Ship ship2= new Ship(gamePlayer2,locaciones2,"Carrier");




	    @Bean
	public CommandLineRunner initData(GameRepository gameRepository,PlayerRepository playerRepository,GamePlayerRepository gamePlayerRepository,  ShipRepository shipRepository) {
		return (args) -> {
			// save a couple of customers
            locaciones.add("A5");
            locaciones.add("A6");
            locaciones.add("A7");
            locaciones2.add("H5");
            locaciones2.add("H6");
            locaciones2.add("H7");

			playerRepository.save(jugadorAli);
			playerRepository.save(jugadorLau);
			gameRepository.save(juego1);
			gameRepository.save(juego2);
			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);
			shipRepository.save(ship1);
			shipRepository.save(ship2);



		};
	}
}
