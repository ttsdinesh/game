package com.rpg;

import com.rpg.controller.GameControllerFactory;
import com.rpg.util.OutputTextUtil;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public class Rpg {
	public static void main(String[] args) {
		OutputTextUtil.printWelcome();
		startAndPlayGame();
	}

	/**
	 * Entry point of the game
	 */
	private static void startAndPlayGame() {
		GameControllerFactory.getInstance().prepareGame();
		GameControllerFactory.getInstance().playGame();
	}
}
