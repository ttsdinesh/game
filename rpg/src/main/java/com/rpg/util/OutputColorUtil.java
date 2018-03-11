package com.rpg.util;

import com.rpg.entities.ClanType;
import com.rpg.entities.ColorCodes;
import com.rpg.entities.Player;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public class OutputColorUtil {
	public static void printText(String text, ColorCodes colorCode) {
		System.out.println((char) 27 + "[" + colorCode.getColorCode() + "m" + text);
		resetColor();
	}

	public static void resetColor() {
		System.out.print((char) 27 + "[" + ColorCodes.RESET.getColorCode() + "m");
	}

	public static void printPlayer(Player player) {
		int colorCode = DefaultProperties.USER_COLOR_CODE;
		if (ClanType.DOTHRAKI.getClanCode() == player.getPlayerCode())
			colorCode = DefaultProperties.DOTHRAKI_COLOR_CODE;
		else if (ClanType.WHITE_WALKER.getClanCode() == player.getPlayerCode())
			colorCode = DefaultProperties.WHITE_WALKER_COLOR_CODE;
		else if (ClanType.LANISTER.getClanCode() == player.getPlayerCode())
			colorCode = DefaultProperties.LANISTER_COLOR_CODE;

		System.out.print(" " + (char) 27 + "[" + colorCode + "m" + player.getPlayerCode() + " ");
		resetColor();
	}
}
