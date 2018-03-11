package com.rpg.controller;

import java.io.Serializable;
import java.util.List;

import com.rpg.entities.MovementDirection;
import com.rpg.entities.Player;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public interface GameIntf extends Serializable {

	/**
	 * Initializes the battle field
	 * 
	 * @param numberOfPlayers
	 *            (both HUMAN and MACHINE)
	 * @param fieldLength
	 * @param fieldBreadth
	 * @param activePlayersCount
	 * @param gameStage
	 */
	public void createBattleField(int numberOfPlayers, int fieldLength, int fieldBreadth, int activePlayersCount,
			int gameStage);

	/**
	 * Randomly place the players in the field
	 */
	public void positionPlayers(boolean isTest);

	/**
	 * Move and place the player according to the direction. Currently only user
	 * player could be moved.
	 * 
	 * @param player
	 * @param movementDirection
	 */
	public void movePlayer(Player player, MovementDirection movement, boolean isTest);

	/**
	 * @return whether the Game is over or not
	 */
	public boolean isGameOver();

	/**
	 * @return whether the current game stage is over. Use this to while moving to
	 *         next stage of the game
	 */
	public boolean isStageCompleted();

	/**
	 * Saves the battle field by serializing the object and storing to
	 * PlayerName.ser file
	 */
	public void saveBattleField();

	/**
	 * Loads battle field from the stored .ser file
	 * 
	 * @param fileName
	 */
	public void loadBattleField(String fileName);

	/**
	 * Create yourselves (the HUMAN user)
	 * 
	 * @param userName
	 * @param clanTypeCode
	 */
	public void createUserPlayer(String userName, int clanTypeCode);

	/**
	 * Return the health of the player
	 * 
	 * @param player
	 * @return
	 */
	public int getHealth(Player player);

	/**
	 * Saves the list of names of the games
	 * 
	 * @param listOfGameNames
	 */
	public void saveListOfGameNames(List<String> listOfGameNames);

	/**
	 * Deletes the game if the User gets killed. Could also extend to let the user
	 * delete a game
	 * 
	 */
	public void deleteBattleField();

}
