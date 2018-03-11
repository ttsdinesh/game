package com.rpg.controller;

import java.util.ArrayList;
import java.util.List;

import com.rpg.persist.GameDAO;
import com.rpg.util.DefaultProperties;
import com.rpg.util.InputHandler;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public class ResourceManager {

	/**
	 * Ensures singleton instance
	 */
	private ResourceManager() {
	}

	private static ResourceManager resourceManagerInstance = null;

	public static ResourceManager getInstance() {
		if (resourceManagerInstance == null)
			synchronized (ResourceManager.class) {
				if (resourceManagerInstance == null)
					resourceManagerInstance = new ResourceManager();
			}
		return resourceManagerInstance;
	}

	/**
	 * Prints final message, closes input handler and terminates the game
	 * 
	 */
	public void terminateGame() {
		System.out.println("Bye, see you soon!");
		InputHandler.closeInputConnection();
		System.exit(0);
	}

	private static List<String> listOfGames;
	static {
		listOfGames = GameDAO.getInstance()
				.loadListOfSavedGames(DefaultProperties.LIST_OF_GAMES_FILE_NAME + DefaultProperties.FILE_EXT);
	}

	public List<String> getListOfGameNames() {
		return listOfGames;
	}

	public void addToListOfGameNames(String newGameName) {
		if (listOfGames == null)
			listOfGames = new ArrayList<String>();
		listOfGames.add(newGameName);
	}

	public void saveListOfGameNames(boolean isTest) {
		if (!isTest)
			GameDAO.getInstance().saveListOfGameNames(
					DefaultProperties.LIST_OF_GAMES_FILE_NAME + DefaultProperties.FILE_EXT, listOfGames);
		else
			GameDAO.getInstance().saveListOfGameNames(
					DefaultProperties.LIST_OF_GAMES_FILE_NAME_TEST + DefaultProperties.FILE_EXT, listOfGames);
	}

	// Only for test
	public void reloadListOfGames() {
		listOfGames = GameDAO.getInstance()
				.loadListOfSavedGames(DefaultProperties.LIST_OF_GAMES_FILE_NAME + DefaultProperties.FILE_EXT);
	}

}
