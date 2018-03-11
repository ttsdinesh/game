package com.rpg.entities;

import java.io.Serializable;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public enum ColorCodes implements Serializable {

	RED_FG(31), GREEN_FG(32), YELLOW_FG(33), BLUE_FG(34), CYAN_FG(36), WHITE_FG(37), RESET(0);

	private final int colorCode;

	private ColorCodes(int colorCode) {
		this.colorCode = colorCode;
	}

	public int getColorCode() {
		return colorCode;
	}

}
