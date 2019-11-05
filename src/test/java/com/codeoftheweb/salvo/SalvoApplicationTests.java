package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.Modelo.*;
import com.codeoftheweb.salvo.Repositories.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import sun.nio.cs.ext.Big5;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalvoApplicationTests {
	@Autowired
	GameRepository gameRepository;
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	GamePlayerRepository gamePlayerRepository;
	@Autowired
	ShipRepository shipRepository;
	@Autowired
	SalvoRepository salvoRepository;
	@Autowired
	ScoreRepository scoreRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	private Player j_bauer;
	private Player obrian;
	private Player almeida;
	private Player kim_bauer;
	private Game juego_1;
	private Game juego_2;
	private Game juego_3;
	private Game juego_4;
	private Game juego_5;
	private Game juego_6;
	private Game juego_7;
	private Game juego_8;
	private GamePlayer gamePlayer_1;
	private GamePlayer gamePlayer_2;
	private GamePlayer gamePlayer_3;
	private GamePlayer gamePlayer_4;
	private GamePlayer gamePlayer_5;
	private GamePlayer gamePlayer_6;
	private GamePlayer gamePlayer_7;
	private GamePlayer gamePlayer_8;
	private GamePlayer gamePlayer_9;
	private GamePlayer gamePlayer_10;
	private GamePlayer gamePlayer_11;
	private GamePlayer gamePlayer_12;
	private GamePlayer gamePlayer_13;
	private Ship barco_1;
	private Ship barco_2;
	private Ship barco_3;
	private Ship barco_4;
	private Ship barco_5;
	private Ship barco_6;
	private Ship barco_7;
	private Ship barco_8;
	private Ship barco_9;
	private Ship barco_10;
	private Ship barco_11;
	private Ship barco_12;
	private Ship barco_13;
	private Ship barco_14;
	private Ship barco_15;
	private Ship barco_16;
	private Ship barco_17;
	private Ship barco_18;
	private Ship barco_19;
	private Ship barco_20;
	private Ship barco_21;
	private Ship barco_22;
	private Ship barco_23;
	private Ship barco_24;
	private Ship barco_25;
	private Ship barco_26;
	private Ship barco_27;
	private Salvo salvo_1;
	private Salvo salvo_2;
	private Salvo salvo_3;
	private Salvo salvo_4;
	private Salvo salvo_5;
	private Salvo salvo_6;
	private Salvo salvo_7;
	private Salvo salvo_8;
	private Salvo salvo_9;
	private Salvo salvo_10;
	private Salvo salvo_11;
	private Salvo salvo_12;
	private Salvo salvo_13;
	private Salvo salvo_14;
	private Salvo salvo_15;
	private Salvo salvo_16;
	private Salvo salvo_17;
	private Salvo salvo_18;
	private Salvo salvo_19;
	private Salvo salvo_20;
	private Score score1;
	private Score score2;
	private Score score3;
	private Score score4;
	private Score score5;
	private Score score6;
	private Score score7;
	private Score score8;
	private Score score9;
	private Score score10;

	@Test
	public void contextLoad() {

		j_bauer = new Player("j.bauer@ctu.gov",passwordEncoder().encode("24"));
		obrian = new Player("c.obrian@ctu.gov",passwordEncoder().encode("42"));
		almeida = new Player("t.almeida@ctu.gov",passwordEncoder().encode("kb"));
		kim_bauer = new Player("kim_bauer@gmail.com",passwordEncoder().encode("mole"));
//------------------DATES
		Date date = new Date();
		LocalDateTime fecha_ahora = LocalDateTime.now();
		LocalDateTime one_hour_later = LocalDateTime.now().plusHours(1);
		LocalDateTime two_hour_later = LocalDateTime.now().plusHours(2);
		LocalDateTime three_hour_later  = LocalDateTime.now().plusHours(3);
		LocalDateTime four_hour_later =  LocalDateTime.now().plusHours(4);
		LocalDateTime five_hour_later  = LocalDateTime.now().plusHours(5);
//-------------------JUEGOS
		juego_1= new Game(fecha_ahora);
		juego_2= new Game(one_hour_later);
		juego_3= new Game(two_hour_later);
		juego_4= new Game(three_hour_later);
		juego_5= new Game(four_hour_later);
		juego_6= new Game(five_hour_later);
		juego_8= new Game(five_hour_later);
//-------------------GAME_PLAYER
		gamePlayer_1 = new GamePlayer(juego_1, j_bauer, fecha_ahora);
		gamePlayer_2 = new GamePlayer(juego_1, obrian, fecha_ahora);
		LocalDateTime scoreTiempo1= LocalDateTime.now().plusMinutes(10);
		LocalDateTime scoreTiempo2= LocalDateTime.now().plusHours(1).plusMinutes(10);
		LocalDateTime scoreTiempo3= LocalDateTime.now().plusHours(2).plusMinutes(10);
		LocalDateTime scoreTiempo4= LocalDateTime.now().plusHours(3).plusMinutes(10);

		score1= new Score(juego_1,j_bauer,scoreTiempo1,ScoreValue.WON);
		score2= new Score(juego_1,obrian,scoreTiempo1,ScoreValue.LOST);

		score3= new Score(juego_2,j_bauer,scoreTiempo2,ScoreValue.TIE);
		score4= new Score(juego_2,obrian,scoreTiempo2,ScoreValue.TIE);

		score5= new Score(juego_3,obrian,scoreTiempo3,ScoreValue.WON);
		score6= new Score(juego_3,almeida,scoreTiempo3,ScoreValue.LOST);

		score7= new Score(juego_4,obrian,scoreTiempo3,ScoreValue.TIE);
		score8= new Score(juego_4,j_bauer,scoreTiempo3,ScoreValue.TIE);


		gamePlayer_3 = new GamePlayer(juego_2, j_bauer, one_hour_later);
		gamePlayer_4 = new GamePlayer(juego_2, obrian, one_hour_later);
		gamePlayer_5 = new GamePlayer(juego_3, obrian, two_hour_later);
		gamePlayer_6 = new GamePlayer(juego_3, almeida, two_hour_later);
		gamePlayer_7 = new GamePlayer(juego_4, obrian, three_hour_later);
		gamePlayer_8 = new GamePlayer(juego_4, j_bauer, three_hour_later);
		gamePlayer_9 = new GamePlayer(juego_5, almeida, four_hour_later);
		gamePlayer_10 = new GamePlayer(juego_5, j_bauer, four_hour_later);
		gamePlayer_11 = new GamePlayer(juego_6, kim_bauer, five_hour_later);
		gamePlayer_12 = new GamePlayer(juego_8, kim_bauer, five_hour_later);
		gamePlayer_13 = new GamePlayer(juego_8, almeida, five_hour_later);

		//--------------------SHIPS
		List<String> ubicacion_1 = Arrays.asList("H2", "H3", "H4");
		barco_1 = new Ship(gamePlayer_1, ubicacion_1,"Destroyer");
		List<String> ubicacion_2 = Arrays.asList("E1", "F1", "G1");
		barco_2 = new Ship(gamePlayer_1,ubicacion_2,"Submarine" );
		List<String> ubicacion_3 = Arrays.asList("B4", "B5");
		barco_3 = new Ship(gamePlayer_1,ubicacion_3,"Patrol Boat");
		List<String> ubicacion_4 = Arrays.asList("B5", "C5", "D5");
		barco_4 = new Ship(gamePlayer_2, ubicacion_4,"Destroyer");
		List<String> ubicacion_5 = Arrays.asList("F1", "F2");
		barco_5 = new Ship(gamePlayer_2, ubicacion_5,"Patrol Boat");
		List<String> ubicacion_6 = Arrays.asList("B5", "C5", "D5");
		barco_6 = new Ship(gamePlayer_3,ubicacion_6,"Destroyer");
		List<String> ubicacion_7 = Arrays.asList("C6", "C7");
		barco_7 = new Ship(gamePlayer_3, ubicacion_7,"Patrol Boat");
		List<String> ubicacion_8 = Arrays.asList("A2", "A3", "A4");
		barco_8 = new Ship(gamePlayer_4,ubicacion_8,"Submarine");
		List<String> ubicacion_9 = Arrays.asList("G6", "H6");
		barco_9 = new Ship( gamePlayer_4, ubicacion_9,"Patrol Boat");
		List<String> ubicacion_10 = Arrays.asList("B5", "C5", "D5");
		barco_10 = new Ship(gamePlayer_5,"Destroyer", ubicacion_10);
		List<String> ubicacion_11 = Arrays.asList("C6", "C7");
		barco_11 = new Ship(gamePlayer_5,"Patrol Boat", ubicacion_11);
		List<String> ubicacion_12 = Arrays.asList("A2", "A3", "A4");
		barco_12 = new Ship(gamePlayer_6,"Submarine", ubicacion_12);
		List<String> ubicacion_13 = Arrays.asList("G6", "H6");
		barco_13 = new Ship(gamePlayer_6,"Patrol Boat", ubicacion_13);
		List<String> ubicacion_14 = Arrays.asList("B5", "C5", "D5");
		barco_14 = new Ship(gamePlayer_7,"Destroyer", ubicacion_14);
		List<String> ubicacion_15 = Arrays.asList("C6", "C7");
		barco_15 = new Ship(gamePlayer_7,"Patrol Boat", ubicacion_15);
		List<String> ubicacion_16 = Arrays.asList("A2", "A3", "A4");
		barco_16 = new Ship(gamePlayer_8,"Submarine", ubicacion_16);
		List<String> ubicacion_17 = Arrays.asList("G6", "H6");
		barco_17 = new Ship(gamePlayer_8,"Patrol Boat", ubicacion_17);
		List<String> ubicacion_18 = Arrays.asList("B5", "C5", "D5");
		barco_18 = new Ship(gamePlayer_9,"Destroyer", ubicacion_18);
		List<String> ubicacion_19 = Arrays.asList("C6", "C7");
		barco_19 = new Ship(gamePlayer_9,"Patrol Boat", ubicacion_19);
		List<String> ubicacion_20 = Arrays.asList("A2", "A3", "A4");
		barco_20 = new Ship( gamePlayer_10,"Submarine", ubicacion_20);
		List<String> ubicacion_21 = Arrays.asList("G6", "H6");
		barco_21 = new Ship( gamePlayer_10,"Patrol Boat", ubicacion_21);
		List<String> ubicacion_22 = Arrays.asList("B5", "C5", "D5");
		barco_22 = new Ship( gamePlayer_11,"Destroyer", ubicacion_22);
		List<String> ubicacion_23 = Arrays.asList("C6", "C7");
		barco_23 = new Ship( gamePlayer_11,"Patrol Boat", ubicacion_23);
		List<String> ubicacion_24 = Arrays.asList("B5", "C5", "D5");
		barco_24 = new Ship( gamePlayer_12,"Destroyer", ubicacion_24);
		List<String> ubicacion_25 = Arrays.asList("C6", "C7");
		barco_25 = new Ship( gamePlayer_12,"Patrol Boat", ubicacion_25);
		List<String> ubicacion_26 = Arrays.asList("A2", "A3", "A4");
		barco_26 = new Ship( gamePlayer_13,"Submarine", ubicacion_26);
		List<String> ubicacion_27 = Arrays.asList("G6", "H6");
		barco_27 = new Ship( gamePlayer_13,"Patrol Boat", ubicacion_27);


//-----------------GUARDO JUGADORES
		playerRepository.save(j_bauer);
		playerRepository.save(obrian);
		playerRepository.save(almeida);
		playerRepository.save(kim_bauer);


//-------------------JUEGOS
		gameRepository.save(juego_1);
		gameRepository.save(juego_2);
		gameRepository.save(juego_3);
		gameRepository.save(juego_4);
		gameRepository.save(juego_5);
		gameRepository.save(juego_6);
		gameRepository.save(juego_8);


//---------------GUARDO GAMEPLAYERS

		gamePlayerRepository.save(gamePlayer_1);
		gamePlayerRepository.save(gamePlayer_2);
		gamePlayerRepository.save(gamePlayer_3);
		gamePlayerRepository.save(gamePlayer_4);
		gamePlayerRepository.save(gamePlayer_5);
		gamePlayerRepository.save(gamePlayer_6);
		gamePlayerRepository.save(gamePlayer_7);
		gamePlayerRepository.save(gamePlayer_8);
		gamePlayerRepository.save(gamePlayer_9);
		gamePlayerRepository.save(gamePlayer_10);
		gamePlayerRepository.save(gamePlayer_11);
		gamePlayerRepository.save(gamePlayer_12);
		gamePlayerRepository.save(gamePlayer_13);



//---------------GUARDO SHIPS
		shipRepository.save(barco_1);
		shipRepository.save(barco_2);
		shipRepository.save(barco_3);
		shipRepository.save(barco_4);
		shipRepository.save(barco_5);
		shipRepository.save(barco_6);
		shipRepository.save(barco_7);
		shipRepository.save(barco_8);
		shipRepository.save(barco_9);
		shipRepository.save(barco_10);
		shipRepository.save(barco_11);
		shipRepository.save(barco_12);
		shipRepository.save(barco_13);
		shipRepository.save(barco_14);
		shipRepository.save(barco_15);
		shipRepository.save(barco_16);
		shipRepository.save(barco_17);
		shipRepository.save(barco_18);
		shipRepository.save(barco_19);
		shipRepository.save(barco_20);
		shipRepository.save(barco_21);
		shipRepository.save(barco_22);
		shipRepository.save(barco_23);
		shipRepository.save(barco_24);
		shipRepository.save(barco_25);
		shipRepository.save(barco_26);
		shipRepository.save(barco_27);
		// -----------------------SALVOES
		List<String> salvo_ubicacion_1 = Arrays.asList("B5", "C5", "F1");
		salvo_1 = new Salvo(1, gamePlayer_1, salvo_ubicacion_1);
		salvoRepository.save(salvo_1);

		List<String> salvo_ubicacion_2 = Arrays.asList("B4", "B5", "B6");
		salvo_2 = new Salvo(1, gamePlayer_2, salvo_ubicacion_2);
		salvoRepository.save(salvo_2);

		List<String> salvo_ubicacion_3 = Arrays.asList("F2", "D5");
		salvo_3 = new Salvo(2, gamePlayer_1, salvo_ubicacion_3);
		salvoRepository.save(salvo_3);

		List<String> salvo_ubicacion_4 = Arrays.asList("E1", "H3", "A2");
		salvo_4 = new Salvo(2, gamePlayer_2, salvo_ubicacion_4);
		salvoRepository.save(salvo_4);

		List<String> salvo_ubicacion_5 = Arrays.asList("A2", "A4", "G6");
		salvo_5 = new Salvo(1, gamePlayer_3, salvo_ubicacion_5);
		salvoRepository.save(salvo_5);

		List<String> salvo_ubicacion_6 = Arrays.asList("B5", "D5", "C7");
		salvo_6 = new Salvo(1, gamePlayer_4, salvo_ubicacion_6);
		salvoRepository.save(salvo_6);

		List<String> salvo_ubicacion_7 = Arrays.asList("A3", "H6");
		salvo_7 = new Salvo(2, gamePlayer_3, salvo_ubicacion_7);
		salvoRepository.save(salvo_7);

		List<String> salvo_ubicacion_8 = Arrays.asList("C5", "C6");
		salvo_8 = new Salvo(2, gamePlayer_4, salvo_ubicacion_8);
		salvoRepository.save(salvo_8);
		// row #

		List<String> salvo_ubicacion_9 = Arrays.asList("G6", "H6", "A4");
		salvo_9 = new Salvo(1, gamePlayer_5, salvo_ubicacion_9);
		salvoRepository.save(salvo_9);

		List<String> salvo_ubicacion_10 = Arrays.asList("H1", "H2", "H3");
		salvo_10 = new Salvo(1, gamePlayer_6, salvo_ubicacion_10);
		salvoRepository.save(salvo_10);

		List<String> salvo_ubicacion_11 = Arrays.asList("A2", "A3", "D8");
		salvo_11 = new Salvo(2, gamePlayer_5, salvo_ubicacion_11);
		salvoRepository.save(salvo_11);

		List<String> salvo_ubicacion_12 = Arrays.asList("E1", "F2", "G3");
		salvo_12 = new Salvo(2, gamePlayer_6, salvo_ubicacion_12);
		salvoRepository.save(salvo_12);

		List<String> salvo_ubicacion_13 = Arrays.asList("A3", "A4", "F7");
		salvo_13 = new Salvo(1, gamePlayer_7, salvo_ubicacion_13);
		salvoRepository.save(salvo_13);

		List<String> salvo_ubicacion_14 = Arrays.asList("B5", "C6", "H1");
		salvo_14 = new Salvo(1, gamePlayer_8, salvo_ubicacion_14);
		salvoRepository.save(salvo_14);

		List<String> salvo_ubicacion_15 = Arrays.asList("A2", "G6", "H6");
		salvo_15 = new Salvo(2, gamePlayer_7, salvo_ubicacion_15);
		salvoRepository.save(salvo_15);

		List<String> salvo_ubicacion_16 = Arrays.asList("C5", "C7", "D5");
		salvo_16 = new Salvo(2, gamePlayer_8, salvo_ubicacion_16);
		salvoRepository.save(salvo_16);

		List<String> salvo_ubicacion_17 = Arrays.asList("A1", "A2", "A3");
		salvo_17 = new Salvo(1, gamePlayer_9, salvo_ubicacion_17);
		salvoRepository.save(salvo_17);

		List<String> salvo_ubicacion_18 = Arrays.asList("B5", "B6", "C7");
		salvo_18 = new Salvo(1, gamePlayer_10, salvo_ubicacion_18);
		salvoRepository.save(salvo_18);

		List<String> salvo_ubicacion_19 = Arrays.asList("G6", "G7", "G8");
		salvo_19 = new Salvo(2, gamePlayer_9, salvo_ubicacion_19);
		salvoRepository.save(salvo_19);

		List<String> salvo_ubicacion_20 = Arrays.asList("C6", "D6", "E6");
		salvo_20 = new Salvo(2, gamePlayer_10, salvo_ubicacion_20);
		salvoRepository.save(salvo_20);

		scoreRepository.save(score1);
		scoreRepository.save(score2);
		scoreRepository.save(score3);
		scoreRepository.save(score4);
		scoreRepository.save(score5);
		scoreRepository.save(score6);
		scoreRepository.save(score7);
		scoreRepository.save(score8);



	}

	@Test
	public  void primerJugador(){
		Assert.assertTrue(j_bauer.getUserName().equalsIgnoreCase("j_bauer"));

		}

}
