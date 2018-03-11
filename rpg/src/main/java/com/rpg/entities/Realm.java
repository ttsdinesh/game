package com.rpg.entities;

import java.io.Serializable;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 08-Mar-2018
 */
public enum Realm implements Serializable {
	GoT(1), TWILIGHT(2), // Not implemented
	TROY(3);// Not implemented

	private int realmCode = 1;

	Realm(int realmCode) {
		this.realmCode = realmCode;
	}

	public int getRealmCode() {
		return realmCode;
	}
}
