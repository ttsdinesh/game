package com.rpg.util;

import java.io.Serializable;

import com.rpg.entities.ColorCodes;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 08-Mar-2018
 */
public class DefaultProperties implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int USER_HEALTH = 100;

	/*
	 * NOTE: Some of these clan properties could be made part of the ClanType enum
	 * for now but not done so, so that later these properties could be read from
	 * files.
	 */
	public static final int DOTHRAKI_HEALTH = 200;
	public static final int LANISTER_HEALTH = 150;
	public static final int WHITE_WALKER_HEALTH = 300;

	public static final String DOTHRAKI_POWER = "Nomadic, horse mounted. Your queen and her 2 dragons (oh, lost one to WhiteWalker) would be with you. Dracarys!";
	public static final String LANISTER_POWER = "Rich and powerful. Your money and mind could buy armies for you!";
	public static final String WHITE_WALKER_POWER = "The damn undead! Nothing could kill you but beware of Dragon Glass and Valyrian Steel";

	public static final int DOTHRAKI_COLOR_CODE = ColorCodes.RED_FG.getColorCode();
	public static final int LANISTER_COLOR_CODE = ColorCodes.GREEN_FG.getColorCode();
	public static final int WHITE_WALKER_COLOR_CODE = ColorCodes.CYAN_FG.getColorCode();
	public static final int USER_COLOR_CODE = ColorCodes.YELLOW_FG.getColorCode();

	public static final String DOTHRAKI_NAME = "Dothraki";
	public static final String LANISTER_NAME = "Lanister";
	public static final String WHITE_WALKER_NAME = "White Walker";

	public static final String LIST_OF_GAMES_FILE_NAME = "ListOfGames";
	public static final String LIST_OF_GAMES_FILE_NAME_TEST = "ListOfGamesTest";

	public static final String FILE_EXT = ".ser";
}
