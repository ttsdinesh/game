package com.rpg.util;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.rpg.controller.GameControllerFactory;
import com.rpg.controller.GameOfThrones;
import com.rpg.controller.ResourceManager;
import com.rpg.entities.BattleField;
import com.rpg.entities.ClanType;
import com.rpg.entities.ColorCodes;
import com.rpg.entities.MovementDirection;
import com.rpg.entities.Player;
import com.rpg.entities.PlayerPosition;
import com.rpg.entities.Realm;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public class OutputTextUtil {

	public static void printWelcome() {
		OutputColorUtil.printText(AsciiArts.WELCOME, ColorCodes.BLUE_FG);
	}

	public static void printActionClose() {
		OutputColorUtil.printText(AsciiArts.SWORD, ColorCodes.BLUE_FG);
	}

	public static void printStageComplete() {
		OutputColorUtil.printText(
				"You won this stage! Buy the game to unlock next stages. \n"
						+ "You can reload this game again and enjoy playing from where it was previously saved.",
				ColorCodes.RED_FG);
		OutputColorUtil.printText(AsciiArts.STAGE_COMPLETE, ColorCodes.BLUE_FG);
	}

	public static int getUserRealm() {
		OutputColorUtil.printText("Choose a realm", ColorCodes.WHITE_FG);
		OutputColorUtil.printText("-1) Quit", ColorCodes.WHITE_FG);

		AtomicInteger count = new AtomicInteger(0);
		Arrays.stream(Realm.values()).forEach(realm -> {
			if (realm.equals(Realm.GoT))
				OutputColorUtil.printText(realm.ordinal() + ") " + realm.toString(), ColorCodes.WHITE_FG);
			else
				OutputColorUtil.printText(
						realm.ordinal() + ") " + realm.toString() + " [Realm under construction, default to GoT]",
						ColorCodes.WHITE_FG);
			count.getAndIncrement();
		});

		List<String> savedGames = ResourceManager.getInstance().getListOfGameNames();
		savedGames.stream().forEach(gameName -> {
			OutputColorUtil.printText(count.getAndIncrement() + ") " + gameName + "[Saved game]", ColorCodes.WHITE_FG);
		});

		int input = 0;
		try {
			input = Integer.valueOf(InputHandler.scanner.nextLine());
			if (input == -1)
				ResourceManager.getInstance().terminateGame();
			if (input >= count.get() || input < 0 || (input > 0 && input < Realm.values().length)) {
				OutputColorUtil.printText("Invalid realm. Exiting the game. Try again", ColorCodes.RED_FG);
				ResourceManager.getInstance().terminateGame();
			}
		} catch (Exception w) {
			OutputColorUtil.printText("Invalid realm. Exiting the game. Try again", ColorCodes.RED_FG);
			ResourceManager.getInstance().terminateGame();
		}
		return input;
	}

	public static Object[] getUserDetails() {
		return new Object[] { getName(), getClanCode() };
	}

	private static String getName() {
		OutputColorUtil.printText("What's your name?", ColorCodes.WHITE_FG);
		// User name is case sensitive
		String name = InputHandler.scanner.nextLine();
		if (name == null || name.trim().length() == 0) {
			OutputColorUtil.printText("Don't you have a name?", ColorCodes.RED_FG);
			getName();
		}
		return name;
	}

	private static int getClanCode() {
		int clanTypeCode = 0; // Default
		OutputColorUtil.printText("Choose a clan", ColorCodes.WHITE_FG);
		AtomicInteger count = new AtomicInteger(0);
		StringBuilder clanSb = new StringBuilder();
		Arrays.stream(ClanType.values()).forEach(clanType -> {
			clanSb.append(count.getAndIncrement());
			clanSb.append(") ");
			clanSb.append(clanType.toString());
			clanSb.append(" [");
			clanSb.append(GameOfThrones.clanTypePower.get(clanType));
			clanSb.append("]");
			clanSb.append("\n");
		});
		OutputColorUtil.printText(clanSb.toString(), ColorCodes.WHITE_FG);
		try {
			clanTypeCode = Integer.valueOf(InputHandler.scanner.nextLine());
			if (clanTypeCode == -1)
				ResourceManager.getInstance().terminateGame();
			if (clanTypeCode >= ClanType.values().length || clanTypeCode < 0) {
				OutputColorUtil.printText("Don't be an outcast. Choose an existing clan.", ColorCodes.RED_FG);
				getClanCode();
			}
		} catch (Exception e) {
			OutputColorUtil.printText("Don't be an outcast. Choose an existing clan.", ColorCodes.RED_FG);
			getClanCode();
		}
		return clanTypeCode;
	}

	public static void printBattleField(BattleField battleField, Player yourself) {
		PlayerPosition[][] position = battleField.getPlayerPosition();
		System.out.print(" ");

		StringBuilder borderSb = new StringBuilder();
		for (int i = 0; i < battleField.getFieldLength(); i++)
			borderSb.append("===");
		String topBottomBorders = borderSb.toString();

		// top border
		OutputColorUtil.printText(topBottomBorders, ColorCodes.WHITE_FG);

		System.out.println();
		for (int i = 0; i < battleField.getFieldBreadth(); i++)
			for (int j = 0; j < battleField.getFieldLength(); j++) {
				if (j == 0)
					System.out.print("|");
				if (position[j][i].getPlayer() == null)
					System.out.print("   ");
				else
					OutputColorUtil.printPlayer(position[j][i].getPlayer());
				if (j + 1 == battleField.getFieldLength())
					System.out.println("|");
			}
		System.out.print(" ");

		// bottom border
		OutputColorUtil.printText(topBottomBorders, ColorCodes.WHITE_FG);
		System.out.println();
		printStats(yourself);
	}

	public static void printStats(Player player) {
		StringBuilder statsSb = new StringBuilder();
		if (player.getHealth() <= 0) {
			// For now, not deleting the game in case the user player is dead.
			statsSb.append("Oh Lord! You're dead in all the 7 kingdoms. Reload the game again and continue playing.");
			OutputColorUtil.printText(statsSb.toString(), ColorCodes.RED_FG);
			OutputColorUtil.printText(AsciiArts.DEAD, ColorCodes.RED_FG);
		} else {
			// Could improve changing the color based on deteriorating health
			statsSb.append("Health: ");
			statsSb.append(player.getHealth());
			statsSb.append(". Remember, you are a ");
			statsSb.append(player.getClan().getClanType());
			statsSb.append("! [");
			Arrays.stream(ClanType.values()).forEach(clanType -> {
				statsSb.append(clanType.toString());
				statsSb.append(":");
				statsSb.append(GameOfThrones.clanTypeHealth.get(clanType));
				statsSb.append(", ");
			});
			statsSb.setLength(statsSb.length() - 2);
			statsSb.append("]");
			OutputColorUtil.printText(statsSb.toString(), ColorCodes.WHITE_FG);
		}
	}

	/*
	 * -1) Quit 0) Save and Quit N) NORTH S) SOUTH E) EAST W) WEST
	 * 
	 */
	public static Object movePlayer() {
		OutputColorUtil.printText("-1) Quit", ColorCodes.WHITE_FG);
		OutputColorUtil.printText("0) Save and quit", ColorCodes.WHITE_FG);
		Arrays.stream(MovementDirection.values()).forEach(direction -> {
			OutputColorUtil.printText(direction.toString().charAt(0) + ") " + direction, ColorCodes.WHITE_FG);
		});

		String input = InputHandler.scanner.nextLine();
		if (input.equals("-1"))
			ResourceManager.getInstance().terminateGame();

		// Inputs are not made case in-sensitive
		input = input.toUpperCase();
		switch (input) {
		case "N":
			return MovementDirection.NORTH;
		case "S":
			return MovementDirection.SOUTH;
		case "E":
			return MovementDirection.EAST;
		case "W":
			return MovementDirection.WEST;
		case "0":
			GameControllerFactory.getInstance().saveAndEndGame();
		default:
			// Explicitly not validating to allow future cheat codes
			// User will not be moved for an invalid input
			return input;
		}
	}

	public static void printInvalidMove() {
		OutputColorUtil.printText("Invalid move", ColorCodes.RED_FG);
	}
}
