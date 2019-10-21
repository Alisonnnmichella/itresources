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

//-------------------PLAYERS
    private Player j_bauer = new Player("j.bauer@ctu.gov");
    private Player obrian = new Player("c.obrian@ctu.gov");
    private Player almeida = new Player("t.almeida@ctu.gov");
    private Player kim_bauer = new Player("kim_bauer@gmail.com");
//------------------DATES
    Date date = new Date();
    private LocalDateTime fecha_ahora = LocalDateTime.now();
    private LocalDateTime one_hour_later = LocalDateTime.now().plusHours(1);
    private LocalDateTime two_hour_later = LocalDateTime.now().plusHours(2);
    private LocalDateTime three_hour_later  = LocalDateTime.now().plusHours(3);
    private LocalDateTime four_hour_later =  LocalDateTime.now().plusHours(4);
    private LocalDateTime five_hour_later  = LocalDateTime.now().plusHours(5);
//-------------------JUEGOS
    private Game juego_1= new Game(fecha_ahora);
    private Game juego_2= new Game(one_hour_later);
    private Game juego_3= new Game(two_hour_later);
    private Game juego_4= new Game(three_hour_later);
    private Game juego_5= new Game(four_hour_later);
    private Game juego_6= new Game(five_hour_later);
    private Game juego_8= new Game(five_hour_later);
    //-------------------GAME_PLAYER
    GamePlayer gamePlayer_1 = new GamePlayer(juego_1, j_bauer, fecha_ahora);
    GamePlayer gamePlayer_2 = new GamePlayer(juego_1, obrian, fecha_ahora);
    LocalDateTime scoreTiempo1= LocalDateTime.now().plusMinutes(10);
    LocalDateTime scoreTiempo2= LocalDateTime.now().plusHours(1).plusMinutes(10);
    LocalDateTime scoreTiempo3= LocalDateTime.now().plusHours(2).plusMinutes(10);
    LocalDateTime scoreTiempo4= LocalDateTime.now().plusHours(3).plusMinutes(10);

    Score score1= new Score(juego_1,j_bauer,scoreTiempo1,ScoreValue.WIN);
    Score score2= new Score(juego_1,obrian,scoreTiempo1,ScoreValue.LOST);

    Score score3= new Score(juego_2,j_bauer,scoreTiempo2,ScoreValue.TIED);
    Score score4= new Score(juego_2,obrian,scoreTiempo2,ScoreValue.TIED);

    Score score5= new Score(juego_3,obrian,scoreTiempo3,ScoreValue.WIN);
    Score score6= new Score(juego_3,almeida,scoreTiempo3,ScoreValue.LOST);

    Score score7= new Score(juego_4,obrian,scoreTiempo3,ScoreValue.TIED);
    Score score8= new Score(juego_4,j_bauer,scoreTiempo3,ScoreValue.TIED);


    GamePlayer gamePlayer_3 = new GamePlayer(juego_2, j_bauer, one_hour_later);
    GamePlayer gamePlayer_4 = new GamePlayer(juego_2, obrian, one_hour_later);
    GamePlayer gamePlayer_5 = new GamePlayer(juego_3, obrian, two_hour_later);
    GamePlayer gamePlayer_6 = new GamePlayer(juego_3, almeida, two_hour_later);
    GamePlayer gamePlayer_7 = new GamePlayer(juego_4, obrian, three_hour_later);
    GamePlayer gamePlayer_8 = new GamePlayer(juego_4, j_bauer, three_hour_later);
    GamePlayer gamePlayer_9 = new GamePlayer(juego_5, almeida, four_hour_later);
    GamePlayer gamePlayer_10 = new GamePlayer(juego_5, j_bauer, four_hour_later);
    GamePlayer gamePlayer_11 = new GamePlayer(juego_6, kim_bauer, five_hour_later);
    GamePlayer gamePlayer_12 = new GamePlayer(juego_8, kim_bauer, five_hour_later);
    GamePlayer gamePlayer_13 = new GamePlayer(juego_8, almeida, five_hour_later);

    //--------------------SHIPS
    List<String> ubicacion_1 = Arrays.asList("H2", "H3", "H4");
    Ship barco_1 = new Ship(gamePlayer_1, ubicacion_1,"Destroyer");
    List<String> ubicacion_2 = Arrays.asList("E1", "F1", "G1");
    Ship barco_2 = new Ship(gamePlayer_1,ubicacion_2,"Submarine" );
    List<String> ubicacion_3 = Arrays.asList("B4", "B5");
    Ship barco_3 = new Ship(gamePlayer_1,ubicacion_3,"Patrol Boat");
    List<String> ubicacion_4 = Arrays.asList("B5", "C5", "D5");
    Ship barco_4 = new Ship(gamePlayer_2, ubicacion_4,"Destroyer");
    List<String> ubicacion_5 = Arrays.asList("F1", "F2");
    Ship barco_5 = new Ship(gamePlayer_2, ubicacion_5,"Patrol Boat");
    List<String> ubicacion_6 = Arrays.asList("B5", "C5", "D5");
    Ship barco_6 = new Ship(gamePlayer_3,ubicacion_6,"Destroyer");
    List<String> ubicacion_7 = Arrays.asList("C6", "C7");
    Ship barco_7 = new Ship(gamePlayer_3, ubicacion_7,"Patrol Boat");
    List<String> ubicacion_8 = Arrays.asList("A2", "A3", "A4");
    Ship barco_8 = new Ship(gamePlayer_4,ubicacion_8,"Submarine");
    List<String> ubicacion_9 = Arrays.asList("G6", "H6");
    Ship barco_9 = new Ship( gamePlayer_4, ubicacion_9,"Patrol Boat");
    List<String> ubicacion_10 = Arrays.asList("B5", "C5", "D5");
    Ship barco_10 = new Ship(gamePlayer_5,"Destroyer", ubicacion_10);
    List<String> ubicacion_11 = Arrays.asList("C6", "C7");
    Ship barco_11 = new Ship(gamePlayer_5,"Patrol Boat", ubicacion_11);
    List<String> ubicacion_12 = Arrays.asList("A2", "A3", "A4");
    Ship barco_12 = new Ship(gamePlayer_6,"Submarine", ubicacion_12);
    List<String> ubicacion_13 = Arrays.asList("G6", "H6");
    Ship barco_13 = new Ship(gamePlayer_6,"Patrol Boat", ubicacion_13);
    List<String> ubicacion_14 = Arrays.asList("B5", "C5", "D5");
    Ship barco_14 = new Ship(gamePlayer_7,"Destroyer", ubicacion_14);
    List<String> ubicacion_15 = Arrays.asList("C6", "C7");
    Ship barco_15 = new Ship(gamePlayer_7,"Patrol Boat", ubicacion_15);
    List<String> ubicacion_16 = Arrays.asList("A2", "A3", "A4");
    Ship barco_16 = new Ship(gamePlayer_8,"Submarine", ubicacion_16);
    List<String> ubicacion_17 = Arrays.asList("G6", "H6");
    Ship barco_17 = new Ship(gamePlayer_8,"Patrol Boat", ubicacion_17);
    List<String> ubicacion_18 = Arrays.asList("B5", "C5", "D5");
    Ship barco_18 = new Ship(gamePlayer_9,"Destroyer", ubicacion_18);
    List<String> ubicacion_19 = Arrays.asList("C6", "C7");
    Ship barco_19 = new Ship(gamePlayer_9,"Patrol Boat", ubicacion_19);
    List<String> ubicacion_20 = Arrays.asList("A2", "A3", "A4");
    Ship barco_20 = new Ship( gamePlayer_10,"Submarine", ubicacion_20);
    List<String> ubicacion_21 = Arrays.asList("G6", "H6");
    Ship barco_21 = new Ship( gamePlayer_10,"Patrol Boat", ubicacion_21);
    List<String> ubicacion_22 = Arrays.asList("B5", "C5", "D5");
    Ship barco_22 = new Ship( gamePlayer_11,"Destroyer", ubicacion_22);
    List<String> ubicacion_23 = Arrays.asList("C6", "C7");
    Ship barco_23 = new Ship( gamePlayer_11,"Patrol Boat", ubicacion_23);
    List<String> ubicacion_24 = Arrays.asList("B5", "C5", "D5");
    Ship barco_24 = new Ship( gamePlayer_12,"Destroyer", ubicacion_24);
    List<String> ubicacion_25 = Arrays.asList("C6", "C7");
    Ship barco_25 = new Ship( gamePlayer_12,"Patrol Boat", ubicacion_25);
    List<String> ubicacion_26 = Arrays.asList("A2", "A3", "A4");
    Ship barco_26 = new Ship( gamePlayer_13,"Submarine", ubicacion_26);
    List<String> ubicacion_27 = Arrays.asList("G6", "H6");
    Ship barco_27 = new Ship( gamePlayer_13,"Patrol Boat", ubicacion_27);

    List<String> locationSalvo1 = Arrays.asList("B5","C5","F1");
    List<String> locationSalvo2 = Arrays.asList("B4","B5","B6");
    List<String> locationSalvo3 = Arrays.asList("F2","D5");
    List<String> locationSalvo4 = Arrays.asList("E1","H3","A2");
    List<String> locationSalvo5 = Arrays.asList("A2","A4","G6");
    List<String> locationSalvo6 = Arrays.asList("B5", "D5", "C7");
    List<String> locationSalvo7 = Arrays.asList("A3", "H6");
    List<String> locationSalvo8 = Arrays.asList("C5", "C6");
    List<String> locationSalvo9 = Arrays.asList("G6", "H6", "A4");
    List<String> locationSalvo10 = Arrays.asList("B5", "D5", "C7");
    List<String> locationSalvo11 = Arrays.asList("B5", "D5", "C7");
    List<String> locationSalvo12 = Arrays.asList("B5", "D5", "C7");






    @Autowired
    ScoreRepository scoreRepository;
	@Bean
	public CommandLineRunner initData(GameRepository gameRepository, PlayerRepository playerRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository,SalvoRepository salvoRepository) {
		return (args) -> {


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
            // salvos
            List<String> salvo_ubicacion_1 = Arrays.asList("B5", "C5", "F1");
            Salvo salvo_1 = new Salvo(1, gamePlayer_1, salvo_ubicacion_1);
            salvoRepository.save(salvo_1);

            List<String> salvo_ubicacion_2 = Arrays.asList("B4", "B5", "B6");
            Salvo salvo_2 = new Salvo(1, gamePlayer_2, salvo_ubicacion_2);
            salvoRepository.save(salvo_2);

            List<String> salvo_ubicacion_3 = Arrays.asList("F2", "D5");
            Salvo salvo_3 = new Salvo(2, gamePlayer_1, salvo_ubicacion_3);
            salvoRepository.save(salvo_3);

            List<String> salvo_ubicacion_4 = Arrays.asList("E1", "H3", "A2");
            Salvo salvo_4 = new Salvo(2, gamePlayer_2, salvo_ubicacion_4);
            salvoRepository.save(salvo_4);

            List<String> salvo_ubicacion_5 = Arrays.asList("A2", "A4", "G6");
            Salvo salvo_5 = new Salvo(1, gamePlayer_3, salvo_ubicacion_5);
            salvoRepository.save(salvo_5);

            List<String> salvo_ubicacion_6 = Arrays.asList("B5", "D5", "C7");
            Salvo salvo_6 = new Salvo(1, gamePlayer_4, salvo_ubicacion_6);
            salvoRepository.save(salvo_6);

            List<String> salvo_ubicacion_7 = Arrays.asList("A3", "H6");
            Salvo salvo_7 = new Salvo(2, gamePlayer_3, salvo_ubicacion_7);
            salvoRepository.save(salvo_7);

            List<String> salvo_ubicacion_8 = Arrays.asList("C5", "C6");
            Salvo salvo_8 = new Salvo(2, gamePlayer_4, salvo_ubicacion_8);
            salvoRepository.save(salvo_8);
            // row #

            List<String> salvo_ubicacion_9 = Arrays.asList("G6", "H6", "A4");
            Salvo salvo_9 = new Salvo(1, gamePlayer_5, salvo_ubicacion_9);
            salvoRepository.save(salvo_9);

            List<String> salvo_ubicacion_10 = Arrays.asList("H1", "H2", "H3");
            Salvo salvo_10 = new Salvo(1, gamePlayer_6, salvo_ubicacion_10);
            salvoRepository.save(salvo_10);

            List<String> salvo_ubicacion_11 = Arrays.asList("A2", "A3", "D8");
            Salvo salvo_11 = new Salvo(2, gamePlayer_5, salvo_ubicacion_11);
            salvoRepository.save(salvo_11);

            List<String> salvo_ubicacion_12 = Arrays.asList("E1", "F2", "G3");
            Salvo salvo_12 = new Salvo(2, gamePlayer_6, salvo_ubicacion_12);
            salvoRepository.save(salvo_12);

            List<String> salvo_ubicacion_13 = Arrays.asList("A3", "A4", "F7");
            Salvo salvo_13 = new Salvo(1, gamePlayer_7, salvo_ubicacion_13);
            salvoRepository.save(salvo_13);

            List<String> salvo_ubicacion_14 = Arrays.asList("B5", "C6", "H1");
            Salvo salvo_14 = new Salvo(1, gamePlayer_8, salvo_ubicacion_14);
            salvoRepository.save(salvo_14);

            List<String> salvo_ubicacion_15 = Arrays.asList("A2", "G6", "H6");
            Salvo salvo_15 = new Salvo(2, gamePlayer_7, salvo_ubicacion_15);
            salvoRepository.save(salvo_15);

            List<String> salvo_ubicacion_16 = Arrays.asList("C5", "C7", "D5");
            Salvo salvo_16 = new Salvo(2, gamePlayer_8, salvo_ubicacion_16);
            salvoRepository.save(salvo_16);

            List<String> salvo_ubicacion_17 = Arrays.asList("A1", "A2", "A3");
            Salvo salvo_17 = new Salvo(1, gamePlayer_9, salvo_ubicacion_17);
            salvoRepository.save(salvo_17);

            List<String> salvo_ubicacion_18 = Arrays.asList("B5", "B6", "C7");
            Salvo salvo_18 = new Salvo(1, gamePlayer_10, salvo_ubicacion_18);
            salvoRepository.save(salvo_18);

            List<String> salvo_ubicacion_19 = Arrays.asList("G6", "G7", "G8");
            Salvo salvo_19 = new Salvo(2, gamePlayer_9, salvo_ubicacion_19);
            salvoRepository.save(salvo_19);

            List<String> salvo_ubicacion_20 = Arrays.asList("C6", "D6", "E6");
            Salvo salvo_20 = new Salvo(2, gamePlayer_10, salvo_ubicacion_20);
            salvoRepository.save(salvo_20);

            List<String> salvo_ubicacion_21 = Arrays.asList("H1", "H8");
            Salvo salvo_21 = new Salvo(3, gamePlayer_10, salvo_ubicacion_21);
            salvoRepository.save(salvo_21);


            scoreRepository.save(score1);
            scoreRepository.save(score2);
            scoreRepository.save(score3);
            scoreRepository.save(score4);
            scoreRepository.save(score5);
            scoreRepository.save(score6);
            scoreRepository.save(score7);
            scoreRepository.save(score8);


            };
	}
}
