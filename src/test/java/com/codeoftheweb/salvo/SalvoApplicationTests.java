package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.Modelo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalvoApplicationTests {

	@Test
	public void contextLoads() {

		Game game= new Game(LocalDateTime.now());
		Player player1= new Player("Alis","123");
		Player player2= new Player("Michell","123");
		GamePlayer gamePlayer1= new GamePlayer(game,player1,LocalDateTime.now());
		GamePlayer gamePlayer2= new GamePlayer(game,player2,LocalDateTime.now());
		List<String> ubicacion_1 = Arrays.asList("H2", "H3", "H4");
		Ship barco_1 = new Ship(gamePlayer1, ubicacion_1,"Destroyer");
		List<String> ubicacion_2 = Arrays.asList("E1", "F1", "G1");
		Ship barco_2 = new Ship(gamePlayer1,ubicacion_2,"Submarine" );
		List<String> ubicacion_3 = Arrays.asList("B4", "B5");
		Ship barco_3 = new Ship(gamePlayer1,ubicacion_3,"Patrol Boat");
		List<String> ubicacion_4 = Arrays.asList("B5", "C5", "D5");
		Ship barco_4 = new Ship(gamePlayer2, ubicacion_4,"Destroyer");
		List<String> ubicacion_5 = Arrays.asList("F1", "F2");
		Ship barco_5 = new Ship(gamePlayer2, ubicacion_5,"Patrol Boat");
		List<String> salvo_ubicacion_1 = Arrays.asList("B5", "C5", "F1");
		Salvo salvo_1 = new Salvo(1, gamePlayer1, salvo_ubicacion_1);
		List<String> salvo_ubicacion_2 = Arrays.asList("B4", "B5", "B6");
		Salvo salvo_2 = new Salvo(1, gamePlayer2, salvo_ubicacion_2);
		List<String> salvo_ubicacion_3 = Arrays.asList("F2", "D5");
		Salvo salvo_3 = new Salvo(2, gamePlayer1, salvo_ubicacion_3);
		List<String> salvo_ubicacion_4 = Arrays.asList("E1", "H3", "A2");
		Salvo salvo_4 = new Salvo(2, gamePlayer2, salvo_ubicacion_4);
		List<String> salvo_ubicacion_5 = Arrays.asList("A2", "A4", "G6");



	}

}
