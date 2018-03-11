package com.rpg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rpg.clone.DeepCloneUtil;
import com.rpg.controller.GameOfThrones;
import com.rpg.entities.BattleField;
import com.rpg.entities.MovementDirection;
import com.rpg.exception.RpgException;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 08-Mar-2018
 */
public class GameOfThronesTest {

	// Case 2: Validate battle field creation - fail
	@Test(expected = RpgException.class)
	public void testCase2BattleFieldCreationFail() {
		GameOfThrones GoT = new GameOfThrones();
		GoT.createBattleField(7, 10, 7, 7, 0);
		GoT.getBattleField();
	}

	// Case 3: Validate battle field creation - pass
	@Test
	public void testCase3BattleFieldCreationPass() {
		GameOfThrones GoT = new GameOfThrones();
		GoT = new GameOfThrones();
		GoT.createBattleField(7, 10, 7, 7, 1);
		BattleField battleField = GoT.getBattleField();

		assertNotNull(battleField);
	}

	// Case 4: Create User Player
	@Test
	public void testCase4CreateUserPlayer() {
		GameOfThrones GoT = new GameOfThrones();
		GoT = new GameOfThrones();
		GoT.createUserPlayer("Case4", 0);
		GoT.createBattleField(7, 10, 7, 7, 1);
		BattleField battleField = GoT.getBattleField();
		assertNotNull(battleField.getYourself());
	}

	// Case 5: Position players. BattleField before positionPlayers() should not be
	// the same as after positionPlayers() - pass
	@Test
	public void testCase5PositionBattleField() {
		GameOfThrones GoT = new GameOfThrones();
		GoT = new GameOfThrones();
		GoT.createUserPlayer("Case5", 0);
		GoT.createBattleField(7, 10, 7, 7, 1);
		// Creating a new deep copy of the object to safe guard for later test purposes
		BattleField battleFieldBefore = (BattleField) new DeepCloneUtil().deepClone(GoT.getBattleField());
		GoT.positionPlayers(true);
		BattleField battleFieldAfter = GoT.getBattleField();
		assertNotEquals(battleFieldBefore, battleFieldAfter);
	}

	// Case 6: Position players. Do not position the players and check BattleField
	// objects are the same
	@Test
	public void testCase6PositionBattleFieldFail() {
		GameOfThrones GoT = new GameOfThrones();
		GoT = new GameOfThrones();
		GoT.createUserPlayer("Case6", 0);
		GoT.createBattleField(7, 10, 7, 7, 1);
		// Creating a new deep copy of the object to safe guard for later test purposes
		BattleField battleFieldBefore = (BattleField) new DeepCloneUtil().deepClone(GoT.getBattleField());
		// GoT.positionPlayers();
		BattleField battleFieldAfter = GoT.getBattleField();
		assertEquals(battleFieldBefore, battleFieldAfter);
	}

	// Case 7: Check randomness of the player positions
	@Test
	public void testCase7PlayerPositionRandomness() {
		GameOfThrones GoT1 = new GameOfThrones();
		GoT1 = new GameOfThrones();
		GoT1.createUserPlayer("Case7.1", 0);
		GoT1.createBattleField(7, 10, 7, 7, 1);
		// Creating a new deep copy of the object to safe guard for later test purposes
		BattleField battleFieldBefore = (BattleField) new DeepCloneUtil().deepClone(GoT1.getBattleField());
		GameOfThrones GoT2 = new GameOfThrones();
		GoT2 = new GameOfThrones();
		GoT2.createUserPlayer("Case7.2", 0); // Note that player name is not part of battle field equals
		GoT2.createBattleField(7, 10, 7, 7, 1);
		BattleField battleFieldAfter = GoT2.getBattleField();

		assertEquals(battleFieldBefore, battleFieldAfter);
	}

	// Case 8: Test player movement
	@Test
	public void testCase8PlayerMovement() {
		GameOfThrones GoT = new GameOfThrones();
		GoT = new GameOfThrones();
		GoT.createUserPlayer("Case8", 0);
		GoT.createBattleField(7, 10, 7, 7, 1);
		GoT.positionPlayers(true);
		// Creating a new deep copy of the object to safe guard for later test purposes
		BattleField battleField = GoT.getBattleField();
		int initialX = battleField.getYourself().getBattlePosition().getX();
		int initialY = battleField.getYourself().getBattlePosition().getY();

		// Do a random movement. If moved NORTH -> SOUTH -> EAST -> WEST there are
		// possibilities of coming to the same position
		GoT.movePlayer(null, MovementDirection.NORTH, true);
		GoT.movePlayer(null, MovementDirection.EAST, true);
		GoT.movePlayer(null, MovementDirection.EAST, true);
		GoT.movePlayer(null, MovementDirection.NORTH, true);
		GoT.movePlayer(null, MovementDirection.WEST, true);
		GoT.movePlayer(null, MovementDirection.SOUTH, true);
		GoT.movePlayer(null, MovementDirection.WEST, true);
		GoT.movePlayer(null, MovementDirection.WEST, true);
		GoT.movePlayer(null, MovementDirection.SOUTH, true);

		BattleField battleFieldAfter = GoT.getBattleField();
		int laterX = battleFieldAfter.getYourself().getBattlePosition().getX();
		int laterY = battleFieldAfter.getYourself().getBattlePosition().getY();

		assertTrue(initialX != laterX || initialY != laterY);
	}

	// Case 9: Test is game over. Game not over case
	@Test
	public void testCase9PlayerIsGameOver1() {
		GameOfThrones GoT = new GameOfThrones();
		GoT = new GameOfThrones();
		GoT.createUserPlayer("Case9", 0);
		GoT.createBattleField(7, 10, 7, 7, 1);
		GoT.positionPlayers(true);
		GoT.movePlayer(null, MovementDirection.NORTH, true);
		assertFalse(GoT.isGameOver());
	}

	// Case 9.1: Test is game over. Game over case. Simulating the game over
	// situation by setting the health to 0
	@Test
	public void testCase9PlayerIsGameOver2() {
		GameOfThrones GoT = new GameOfThrones();
		GoT = new GameOfThrones();
		GoT.createUserPlayer("Case9.2", 0);
		GoT.createBattleField(7, 10, 7, 7, 1);
		GoT.positionPlayers(true);
		GoT.movePlayer(null, MovementDirection.NORTH, true);
		BattleField battleField = GoT.getBattleField();
		battleField.getYourself().setHealth(0);
		assertTrue(GoT.isGameOver());
	}
}
