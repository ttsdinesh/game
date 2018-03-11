package com.rpg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Random;

import org.junit.Test;

import com.rpg.controller.GameOfThrones;
import com.rpg.entities.BattleField;
import com.rpg.entities.MovementDirection;
import com.rpg.exception.RpgException;
import com.rpg.persist.GameDAO;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 08-Mar-2018
 */
public class GameDAOTest {

	// Case 10: Validate saving the game
	@Test
	public void testGameCRUD() {
		System.out.println("1");
		GameOfThrones GoT = new GameOfThrones();
		GoT = new GameOfThrones();
		GoT.createUserPlayer("_Case10_", 0);
		GoT.createBattleField(7, 10, 7, 7, 1);
		GoT.positionPlayers(true);
		GoT.movePlayer(null, MovementDirection.NORTH, true);
		BattleField battleField = GoT.getBattleField();
		GameDAO.getInstance().saveGame(battleField, "_Case10_");

		// Case 11: Validate loading the game
		assertEquals(battleField, GameDAO.getInstance().loadGame("_Case10_", true));

		// Case 12: Validate deleting the game
		assertTrue(GameDAO.getInstance().deleteGame("_Case10_"));
		// Case 13: When tried to delete the same file, it should return false
		assertFalse(GameDAO.getInstance().deleteGame("_Case10_"));
	}

	// Case 14: Error while tried loading wrong file
	@Test(expected = RpgException.class)
	public void testGameLoad() {
		Random rand = new Random();
		GameDAO.getInstance().loadGame(String.valueOf(rand.nextInt(1000000000)), true);
	}

}