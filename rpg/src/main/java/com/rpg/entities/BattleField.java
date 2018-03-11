package com.rpg.entities;

import java.io.Serializable;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public class BattleField implements Serializable {

	private static final long serialVersionUID = 1L;
	private PlayerPosition[][] playerPosition;
	private int activePlayersCount;
	private int fieldLength;
	private int fieldBreadth;

	private Player yourself;

	private int gameStage; // Not being used as of now

	public BattleField(int fieldLength, int fieldBreadth, int activePlayersCount) {
		this.playerPosition = new PlayerPosition[fieldLength][fieldBreadth];
		this.activePlayersCount = activePlayersCount;
		this.fieldLength = fieldLength;
		this.fieldBreadth = fieldBreadth;
	}

	public PlayerPosition[][] getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(PlayerPosition[][] playerPosition) {
		this.playerPosition = playerPosition;
	}

	public int getActivePlayersCount() {
		return activePlayersCount;
	}

	public void setActivePlayersCount(int activePlayersCount) {
		this.activePlayersCount = activePlayersCount;
	}

	public int getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(int fieldLength) {
		this.fieldLength = fieldLength;
	}

	public int getFieldBreadth() {
		return fieldBreadth;
	}

	public void setFieldBreadth(int fieldBreadth) {
		this.fieldBreadth = fieldBreadth;
	}

	public Player getYourself() {
		return yourself;
	}

	public void setYourself(Player yourself) {
		this.yourself = yourself;
	}

	public int getGameStage() {
		return gameStage;
	}

	public void setGameStage(int gameStage) {
		this.gameStage = gameStage;
	}

	@Override
	public boolean equals(Object obj) {
		// Using only players to determine if 2 battle field objects are the same
		BattleField thisBattleField = this;
		BattleField otherBattleField = (BattleField) obj;
		final PlayerPosition[][] thisPosition = thisBattleField.getPlayerPosition();
		final PlayerPosition[][] otherPosition = otherBattleField.getPlayerPosition();
		for (int i = 0; i < thisPosition.length; i++)
			for (int j = 0; j < thisPosition[0].length; j++) {
				if ((thisPosition[i][j] != null && otherPosition[i][j] == null)
						|| (thisPosition[i][j] == null && otherPosition[i][j] != null))
					return false;
				if (thisPosition[i][j] == null || otherPosition[i][j] == null)
					continue;
				if ((thisPosition[i][j].getPlayer() != null && otherPosition[i][j].getPlayer() == null)
						|| (thisPosition[i][j].getPlayer() == null && otherPosition[i][j].getPlayer() != null))
					return false;
				if (thisPosition[i][j].getPlayer() == null || otherPosition[i][j].getPlayer() == null)
					continue;
				if (!thisPosition[i][j].getPlayer().equals(otherPosition[i][j].getPlayer()))
					return false;
			}
		return true;
	}
}
