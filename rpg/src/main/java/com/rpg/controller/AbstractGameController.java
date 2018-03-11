package com.rpg.controller;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 08-Mar-2018
 */
public interface AbstractGameController {
	/**
	 * Do prerequisites of the game
	 */
	public void prepareGame();

	/**
	 * Game course
	 */
	public void playGame();

	/**
	 * Cleanup any resources, save the game and terminate
	 */
	public void saveAndEndGame();

}
