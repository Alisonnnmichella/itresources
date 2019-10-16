package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
		Player jugadorAli=new Player("Ali");
		Player jugadorLau= new Player("Lauti");
		Game juego1= new Game(LocalDateTime.now());
		Game juego2= new Game(LocalDateTime.now().plusHours(1));
	    GamePlayer gamePlayer1= new GamePlayer(juego1,jugadorAli);
	 	GamePlayer gamePlayer2= new GamePlayer(juego1,jugadorLau);
		GamePlayer gamePlayer3= new GamePlayer(juego2,jugadorAli);
		GamePlayer gamePlayer4= new GamePlayer(juego2,jugadorLau);


	    @Bean
	public CommandLineRunner initData(GameRepository gameRepository,PlayerRepository playerRepository,GamePlayerRepository gamePlayerRepository) {
		return (args) -> {
			// save a couple of customers
			playerRepository.save(jugadorAli);
			playerRepository.save(jugadorLau);
			gameRepository.save(juego1);
			gameRepository.save(juego2);
			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);

		};
	}
}
