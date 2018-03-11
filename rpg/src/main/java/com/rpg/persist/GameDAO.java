package com.rpg.persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.rpg.controller.ResourceManager;
import com.rpg.entities.BattleField;
import com.rpg.entities.ColorCodes;
import com.rpg.exception.RpgException;
import com.rpg.util.DefaultProperties;
import com.rpg.util.OutputColorUtil;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 10-Mar-2018
 */
public class GameDAO implements AbstractDAO {

	/**
	 * Ensures singleton instance
	 */
	private GameDAO() {
	}

	private static GameDAO gameDaoInstance = null;

	public static GameDAO getInstance() {
		if (gameDaoInstance == null)
			synchronized (GameDAO.class) {
				if (gameDaoInstance == null)
					gameDaoInstance = new GameDAO();
			}
		return gameDaoInstance;
	}

	@Override
	public void saveGame(BattleField battleField, String playerName) {
		try (FileOutputStream fos = new FileOutputStream(playerName + DefaultProperties.FILE_EXT);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(battleField);
		} catch (Exception e) {
			// Could be extended to handle RpgException and log via logger
			// throw new RpgException(e);
			OutputColorUtil.printText("Game could not be saved. Exiting the game. Sorry!", ColorCodes.RED_FG);
			ResourceManager.getInstance().terminateGame();
		}
	}

	@Override
	public BattleField loadGame(String name, boolean isTest) {
		BattleField battleField = null;
		try (FileInputStream fis = new FileInputStream(name + DefaultProperties.FILE_EXT);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			battleField = (BattleField) ois.readObject();
		} catch (Exception e) {
			// Could be extended to handle RpgException and log via logger
			if (isTest)
				throw new RpgException(e);
		}
		if (battleField == null) {
			/*
			 * Handle file corruption or purposefully altered .ser files
			 */
			OutputColorUtil.printText("Saved game could not be retrieved. Exiting the game. Sorry!", ColorCodes.RED_FG);
			ResourceManager.getInstance().terminateGame();
			return null;
		} else
			return battleField;
	}

	@Override
	public boolean deleteGame(String name) {
		File gameFile = new File(name + DefaultProperties.FILE_EXT);
		return gameFile.delete();
	}

	@Override
	public List<String> loadListOfSavedGames(String fileName) {
		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis);) {
			return (ArrayList<String>) ois.readObject();
		} catch (Exception e) {
			// Could be extended to handle RpgException and log via logger
			// throw new RpgException(e);
		}
		return new ArrayList<String>();
	}

	@Override
	public void saveListOfGameNames(String fileName, List<String> listOfGames) {
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(listOfGames);
		} catch (Exception e) {
			// Could be extended to handle RpgException and log via logger
			// throw new RpgException(e);
		}
	}

}
