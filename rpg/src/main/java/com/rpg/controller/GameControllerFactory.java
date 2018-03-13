package com.rpg.controller;

import com.rpg.entities.Realm;
import com.rpg.util.OutputTextUtil;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 08-Mar-2018
 */
public class GameControllerFactory implements AbstractGameController {
	GameIntf game;
	Realm realm = Realm.GoT; // default

	/**
	 * Ensures singleton instance
	 */
	private GameControllerFactory() {
		System.out.println("");
	}

	private volatile static GameControllerFactory gameControllerInstance = null;

	public static GameControllerFactory getInstance() {
		if (gameControllerInstance == null)
			synchronized (GameControllerFactory.class) {
				if (gameControllerInstance == null)
					gameControllerInstance = new GameControllerFactory();
			}
		return gameControllerInstance;
	}

	@Override
	public void prepareGame() {
		int ordinal = OutputTextUtil.getUserRealm();
		if (ordinal < realm.values().length)
			this.realm = Realm.values()[ordinal];
		OutputTextUtil.printActionClose();
		if (Realm.GoT.equals(realm))
			game = new GameOfThrones();
		// else if (Realm.TWILIGHT.ordinal() == realm)
		// game = new TwilightSaga();

		// Loading an existing game
		if (ordinal >= realm.values().length) {
			String fileName = ResourceManager.getInstance().getListOfGameNames().get(ordinal - realm.values().length);
			game.loadBattleField(fileName);
			return;
		}

		// If not loading an existing game, continue creating new game
		Object[] obj = OutputTextUtil.getUserDetails();
		OutputTextUtil.printActionClose();
		game.createUserPlayer((String) obj[0], (int) obj[1]);
		// numberOfPlayers, fieldLength, fieldBreadth, activePlayersCount, gameStage
		game.createBattleField(7, 10, 7, 7, 1); // These properties could be read from user input/file
		game.positionPlayers(false);
	}

	@Override
	public void playGame() {
		while (!game.isGameOver())
			game.movePlayer(null, null, false);

		if (game.getHealth(null) > 0)
			OutputTextUtil.printStageComplete();
		ResourceManager.getInstance().terminateGame();
	}

	@Override
	public void saveAndEndGame() {
		game.saveBattleField();
		ResourceManager.getInstance().saveListOfGameNames(false);
		ResourceManager.getInstance().terminateGame();
	}
}
