package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.Modelo.*;
import com.codeoftheweb.salvo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    private List<String> locacionesBarco1= new ArrayList<>();
    private List<String> locacionesBarco2= new ArrayList<>();
    private List<String> locacionesSalvo1= new ArrayList<>();
    private List<String> locacionesSalvo2= new ArrayList<>();
    private Ship ship1= new Ship(gamePlayer1,locacionesBarco1,"Carrier");
    private Ship ship2= new Ship(gamePlayer1,locacionesBarco2,"Destroyer");
    private Ship ship3= new Ship(gamePlayer1,locacionesSalvo1,"Patrol Boat");
    private Ship ship4= new Ship(gamePlayer1,locacionesSalvo2,"Submarine");
    private Salvo salvo1= new Salvo(gamePlayer1,1,locacionesSalvo1);
    private Salvo salvo2= new Salvo(gamePlayer2,1,locacionesSalvo2);



	@Bean
	public CommandLineRunner initData(GameRepository gameRepository, PlayerRepository playerRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository,SalvoRepository salvoRepository) {
		return (args) -> {
			// save a couple of customers
            locacionesBarco1.addAll(Arrays.asList("A2","A3","A4","A5"));
            locacionesBarco2.addAll(Arrays.asList("H5","H6","H7"));
			locacionesSalvo1.addAll(Arrays.asList("A5","B5","C5","D5"));
			locacionesSalvo2.addAll(Arrays.asList("A1","B1","C1","D1"));

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
			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);


		};
	}
}
