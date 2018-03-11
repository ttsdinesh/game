package com.rpg.util;

import com.rpg.exception.RpgException;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 11-Mar-2018
 */
public class GameParamValidator {
	/*
	 * NOTE: Validation failures here throws unchecked exception
	 */

	public static void validateCreateBattleField(int numberOfPlayers, int fieldLength, int fieldBreadth,
			int activePlayersCount, int gameStage) throws RpgException {
		if (numberOfPlayers < 2 || fieldLength < 2 || fieldBreadth < 2 || activePlayersCount < 2 || gameStage < 1)
			throw new RpgException("Invalid game parameters");
	}
}
