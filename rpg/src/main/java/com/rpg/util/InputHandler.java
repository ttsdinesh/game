package com.rpg.util;

import java.util.Scanner;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public class InputHandler {
	public static Scanner scanner = new Scanner(System.in);

	public static void closeInputConnection() {
		scanner.close();
	}
}
