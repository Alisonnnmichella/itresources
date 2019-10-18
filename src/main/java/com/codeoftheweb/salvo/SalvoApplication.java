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

    private Player jugadorbauer=new Player("j.bauer@ctu.gov");
    private Player jugadorobrian= new Player("c.obrian@ctu.gov");
    private Player jugadorkbauer= new Player("kim_bauer@gmail.com kb");
    private Player jugadoralmeida= new Player("t.almeida@ctu.gov");


    private Game juego1= new Game(LocalDateTime.now());
    private Game juego2= new Game(LocalDateTime.now().plusHours(1));
    private GamePlayer gamePlayer1= new GamePlayer(juego1,jugadorbauer,LocalDateTime.now());
    private GamePlayer gamePlayer2= new GamePlayer(juego1,jugadorobrian,LocalDateTime.now());
    private GamePlayer gamePlayer3= new GamePlayer(juego2,jugadorkbauer,LocalDateTime.now());
    private GamePlayer gamePlayer4= new GamePlayer(juego2,jugadoralmeida,LocalDateTime.now());
    private List<String> locaciones1= new ArrayList<>();
    private List<String> locaciones2= new ArrayList<>();
    private Ship ship1= new Ship(gamePlayer1,locaciones1,"Carrier");
    private Ship ship2= new Ship(gamePlayer1,locaciones2,"Carrier");
    private Ship ship3= new Ship(gamePlayer1,locaciones2,"Carrier");
    private Ship ship4= new Ship(gamePlayer1,locaciones2,"Carrier");




	    @Bean
	public CommandLineRunner initData(GameRepository gameRepository,PlayerRepository playerRepository,GamePlayerRepository gamePlayerRepository,  ShipRepository shipRepository) {
		return (args) -> {
			// save a couple of customers
            locaciones1.addAll(Arrays.asList("A2","A3","A4","A5"));
            locaciones2.addAll(Arrays.asList("H5","H6","H7"));

			playerRepository.save(jugadorbauer);
			playerRepository.save(jugadorobrian);
			playerRepository.save(jugadorkbauer);
			playerRepository.save(jugadoralmeida);
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
