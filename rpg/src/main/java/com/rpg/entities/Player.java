package com.rpg.entities;

import java.io.Serializable;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;

	public Player(int health, String name, PlayerPosition battlePosition, Clan clan, PlayerType playerType,
			char playerCode) {
		super();
		this.health = health;
		this.name = name;
		this.battlePosition = battlePosition;
		this.clan = clan;
		this.playerType = playerType;
		this.playerCode = playerCode;
	}

	private int health;
	private String name;
	private PlayerPosition battlePosition;
	private Clan clan;
	private PlayerType playerType;
	private char playerCode;

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlayerPosition getBattlePosition() {
		return battlePosition;
	}

	public void setBattlePosition(PlayerPosition battlePosition) {
		this.battlePosition = battlePosition;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

	public char getPlayerCode() {
		return playerCode;
	}

	public void setPlayerCode(char playerCode) {
		this.playerCode = playerCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clan == null) ? 0 : clan.hashCode());
		result = prime * result + health;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + playerCode;
		result = prime * result + ((playerType == null) ? 0 : playerType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (clan == null) {
			if (other.clan != null)
				return false;
		} else if (!clan.equals(other.clan))
			return false;
		if (health != other.health)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (playerCode != other.playerCode)
			return false;
		if (playerType != other.playerType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [health=");
		builder.append(health);
		builder.append(", name=");
		builder.append(name);
		builder.append(", clan=");
		builder.append(clan);
		builder.append(", playerType=");
		builder.append(playerType);
		builder.append("]");
		return builder.toString();
	}

}
