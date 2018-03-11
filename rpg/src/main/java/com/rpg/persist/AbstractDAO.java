package com.rpg.persist;

import java.util.List;
import com.rpg.entities.BattleField;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 10-Mar-2018
 */
public interface AbstractDAO {

	/**
	 * Save the Game(BattleField) by serializing and persisting to .ser file
	 * Improvements - could be saved to a local DB or over the network
	 * 
	 * @param battleField
	 * @param playerName
	 */
	public void saveGame(BattleField battleField, String playerName);

	/**
	 * Load the game from the .ser file in the disk. 'name' identifies the saved
	 * game
	 * 
	 * @param name
	 * @param isTest
	 * @return BattleField
	 */
	public BattleField loadGame(String name, boolean isTest);

	/**
	 * @return List of name of saved games
	 */
	public List<String> loadListOfSavedGames(String fileName);

	/**
	 * Saves the list of names of the games
	 * 
	 * @param fileName
	 * @param listOfGames
	 */
	public void saveListOfGameNames(String fileName, List<String> listOfGames);

	/**
	 * Deletes the game by deleting the .ser file.
	 * 
	 * @param name
	 * @return true if the file is deleted
	 */
	public boolean deleteGame(String name);
}
