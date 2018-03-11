package com.rpg.entities;

import java.io.Serializable;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 07-Mar-2018
 */
public enum GameType implements Serializable {
	HUMAN_MACHINE, // Single Player
	HUMAN_HUMAN, // Multi-player (Not implemented yet)
	MACHINE_MACHINE; // Machine-Machine (Not implemented yet)
}
