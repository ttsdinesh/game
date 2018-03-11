package com.rpg.entities;

import java.io.Serializable;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public class PlayerPosition implements Serializable {
	public PlayerPosition(int x, int y, Player player) {
		super();
		X = x;
		Y = y;
		this.player = player;
	}

	private static final long serialVersionUID = 1L;
	private int X;
	private int Y;
	private Player player;

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BattlePosition [X=");
		builder.append(X);
		builder.append(", Y=");
		builder.append(Y);
		builder.append(", player=");
		builder.append(player);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + X;
		result = prime * result + Y;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
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
		PlayerPosition other = (PlayerPosition) obj;
		if (X != other.X)
			return false;
		if (Y != other.Y)
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}

}
