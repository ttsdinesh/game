package com.rpg.entities;

import java.io.Serializable;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public enum ClanType implements Serializable {
	WHITE_WALKER('W'), LANISTER('L'), DOTHRAKI('D');

	private final char clanCode;

	private ClanType(char clanCode) {
		this.clanCode = clanCode;
	}

	public char getClanCode() {
		return clanCode;
	}

}
