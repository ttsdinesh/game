package com.rpg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.rpg.entities.BattleField;
import com.rpg.entities.Clan;
import com.rpg.entities.ClanType;
import com.rpg.entities.ColorCodes;
import com.rpg.entities.MovementDirection;
import com.rpg.entities.Player;
import com.rpg.entities.PlayerPosition;
import com.rpg.entities.PlayerType;
import com.rpg.persist.GameDAO;
import com.rpg.util.CheatCodes;
import com.rpg.util.DefaultProperties;
import com.rpg.util.GameParamValidator;
import com.rpg.util.OutputColorUtil;
import com.rpg.util.OutputTextUtil;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 08-Mar-2018
 */
public class GameOfThrones implements GameIntf {

	private static final long serialVersionUID = 1L;
	private Random randomGenerator;

	BattleField battleField;

	List<Character> playerCodes;
	List<Player> players;
	Clan[] clans;
	Player yourself;
	int fieldLength, fieldBreadth;
	PlayerPosition userPosition;
	public static Map<ClanType, Integer> clanTypeHealth = new HashMap<ClanType, Integer>();
	public static Map<ClanType, String> clanTypePower = new HashMap<ClanType, String>();

	public GameOfThrones() {
		populateClanPowers();
	}

	@Override
	public void createBattleField(int numberOfPlayers, int fieldLength, int fieldBreadth, int activePlayersCount,
			int gameStage) {
		GameParamValidator.validateCreateBattleField(numberOfPlayers, fieldLength, fieldBreadth, activePlayersCount,
				gameStage);
		this.battleField = new BattleField(fieldLength, fieldBreadth, activePlayersCount);
		randomGenerator = new Random();
		playerCodes = new ArrayList<Character>();
		players = new ArrayList<Player>();
		clans = new Clan[3];
		this.fieldLength = fieldLength;
		this.fieldBreadth = fieldBreadth;
		this.battleField.setYourself(yourself);
		preparePlayers(battleField);
	}

	@Override
	public void positionPlayers(boolean isTest) {
		PlayerPosition[][] positions = new PlayerPosition[this.fieldLength][this.fieldBreadth];
		fillBattleField(positions);
		randomlyPlacePlayers(positions, 0);
		battleField.setPlayerPosition(positions);
		if (!isTest)
			OutputTextUtil.printBattleField(battleField, yourself);
	}

	// Exposed as public only for the purpose of test
	public void fillBattleField(PlayerPosition[][] positions) {
		for (int i = 0; i < this.fieldLength; i++)
			for (int j = 0; j < this.fieldBreadth; j++)
				positions[i][j] = new PlayerPosition(i, j, null);
	}

	/**
	 * TODO Make this sophisticated to get more players based on active players
	 * count. For now just create 7 players including the human player
	 * 
	 * @param battleField
	 * @return List of Clan Codes
	 */
	private List<Character> preparePlayers(BattleField battleField) {
		createClans();
		populateClanHealth();
		players.add(new Player(DefaultProperties.WHITE_WALKER_HEALTH, DefaultProperties.WHITE_WALKER_NAME, null,
				clans[0], PlayerType.MACHINE, ClanType.WHITE_WALKER.getClanCode()));
		players.add(new Player(DefaultProperties.WHITE_WALKER_HEALTH, DefaultProperties.WHITE_WALKER_NAME, null,
				clans[0], PlayerType.MACHINE, ClanType.WHITE_WALKER.getClanCode()));
		players.add(new Player(DefaultProperties.DOTHRAKI_HEALTH, DefaultProperties.DOTHRAKI_NAME, null, clans[1],
				PlayerType.MACHINE, ClanType.DOTHRAKI.getClanCode()));
		players.add(new Player(DefaultProperties.DOTHRAKI_HEALTH, DefaultProperties.DOTHRAKI_NAME, null, clans[1],
				PlayerType.MACHINE, ClanType.DOTHRAKI.getClanCode()));
		players.add(new Player(DefaultProperties.LANISTER_HEALTH, DefaultProperties.LANISTER_NAME, null, clans[2],
				PlayerType.MACHINE, ClanType.LANISTER.getClanCode()));
		players.add(new Player(DefaultProperties.LANISTER_HEALTH, DefaultProperties.LANISTER_NAME, null, clans[2],
				PlayerType.MACHINE, ClanType.LANISTER.getClanCode()));
		players.add(yourself);
		return playerCodes;
	}

	private void createClans() {
		Clan whiteWalker = new Clan(DefaultProperties.WHITE_WALKER_NAME, DefaultProperties.WHITE_WALKER_POWER,
				DefaultProperties.WHITE_WALKER_COLOR_CODE, ClanType.WHITE_WALKER);
		Clan dothraki = new Clan(DefaultProperties.DOTHRAKI_NAME, DefaultProperties.DOTHRAKI_POWER,
				DefaultProperties.DOTHRAKI_COLOR_CODE, ClanType.DOTHRAKI);
		Clan lanister = new Clan(DefaultProperties.LANISTER_NAME, DefaultProperties.LANISTER_POWER,
				DefaultProperties.LANISTER_COLOR_CODE, ClanType.LANISTER);
		clans[0] = whiteWalker;
		clans[1] = dothraki;
		clans[2] = lanister;
	}

	private void populateClanHealth() {
		clanTypeHealth.put(ClanType.DOTHRAKI, DefaultProperties.DOTHRAKI_HEALTH);
		clanTypeHealth.put(ClanType.LANISTER, DefaultProperties.LANISTER_HEALTH);
		clanTypeHealth.put(ClanType.WHITE_WALKER, DefaultProperties.WHITE_WALKER_HEALTH);
	}

	private void populateClanPowers() {
		clanTypePower.put(ClanType.DOTHRAKI, DefaultProperties.DOTHRAKI_POWER);
		clanTypePower.put(ClanType.LANISTER, DefaultProperties.LANISTER_POWER);
		clanTypePower.put(ClanType.WHITE_WALKER, DefaultProperties.WHITE_WALKER_POWER);
	}

	/**
	 * Recursively place the players in the battle field. Lot of areas to improve
	 * here. With each progressive stage of the game increase the number of enemies
	 * 
	 * @param positions
	 * @param row
	 */
	private void randomlyPlacePlayers(PlayerPosition[][] positions, int row) {
		if (players == null || players.size() == 0)
			return;
		// Could easily avoid the usage of below 2 int variables. Leaving them just for
		// ease of understanding
		int col = randomGenerator.nextInt(positions[0].length);
		int playerRandIndex = randomGenerator.nextInt(players.size());

		Player player = players.remove(playerRandIndex);
		if (player.getPlayerCode() == 'U')
			userPosition = new PlayerPosition(row, col, player);
		yourself.setBattlePosition(userPosition);
		positions[row++][col].setPlayer(player);
		randomlyPlacePlayers(positions, row);
	}

	@Override
	public void movePlayer(Player player, MovementDirection movementDirection, boolean isTest) {
		// User Player is the default player involved in movements. Other players could
		// not move as of now
		if (player == null)
			player = yourself;

		Object direction = null;
		if (movementDirection != null) {
			direction = (Object) movementDirection;
		} else
			direction = OutputTextUtil.movePlayer();

		if (direction instanceof MovementDirection)
			makeAMove(player, (MovementDirection) direction, movementDirection != null);
		else {
			/*
			 * Handle cheat code. Deliberately 'setting' the health instead of summing up
			 * the health
			 */
			if (CheatCodes.CC_DINESH_STR.equals((String) direction))
				yourself.setHealth(CheatCodes.CC_DINESH_HEALTH);
			else if (CheatCodes.CC_XYZ_STR.equals((String) direction))
				yourself.setHealth(CheatCodes.CC_XYZ_HEALTH);
		}
		if (!isTest)
			OutputTextUtil.printBattleField(battleField, yourself);
	}

	private void makeAMove(Player player, MovementDirection direction, boolean isTest) {
		PlayerPosition oldPosition = player.getBattlePosition();
		PlayerPosition newPosition = new PlayerPosition(oldPosition.getX(), oldPosition.getY(),
				oldPosition.getPlayer());
		switch (direction) {
		case NORTH:
			newPosition.setY(newPosition.getY() - 1);
			break;
		case SOUTH:
			newPosition.setY(newPosition.getY() + 1);
			break;
		case EAST:
			newPosition.setX(newPosition.getX() + 1);
			break;
		case WEST:
			newPosition.setX(newPosition.getX() - 1);
			break;
		}
		if (isValidMove(newPosition)) {
			int xOld = oldPosition.getX(), yOld = oldPosition.getY(), xNew = newPosition.getX(),
					yNew = newPosition.getY();
			// Update target player
			Player targetPlayer = battleField.getPlayerPosition()[xNew][yNew].getPlayer();
			if (targetPlayer != null)
				calculateNewHealth(targetPlayer);

			// Update user player position
			battleField.getPlayerPosition()[xOld][yOld].setPlayer(null);
			yourself.setBattlePosition(newPosition);
			battleField.getPlayerPosition()[xNew][yNew].setPlayer(yourself);
		} else if (!isTest) {
			OutputTextUtil.printInvalidMove();
			movePlayer(player, null, false);
		}
	}

	private void calculateNewHealth(Player targetPlayer) {
		int yourHealth = yourself.getHealth(), targetHealth = targetPlayer.getHealth();
		char yourCode = yourself.getClan().getClanType().getClanCode(), targetCode = targetPlayer.getPlayerCode();
		// If you meet your own clan, your health sums up with the clan health
		if (yourCode == targetCode)
			yourself.setHealth(yourHealth + targetHealth);
		// Otherwise you lose the health equivalent to the health of the clan
		else
			yourself.setHealth(yourHealth - targetHealth);
	}

	private boolean isValidMove(PlayerPosition newPosition) {
		return !(newPosition.getX() < 0 || newPosition.getY() < 0 || newPosition.getX() > fieldLength - 1
				|| newPosition.getY() > fieldBreadth - 1);
	}

	@Override
	public boolean isGameOver() {
		// TODO Not yet decided when to complete the game. For now end the game when the
		// stage is completed
		return isStageCompleted();
	}

	public boolean isStageCompleted() {
		if (yourself.getHealth() <= 0)
			return true;
		for (int i = 0; i < battleField.getFieldBreadth(); i++) {
			for (int j = 0; j < battleField.getFieldLength(); j++) {
				if (battleField.getPlayerPosition()[j][i].getPlayer() == null)
					continue;
				char playerCode = battleField.getPlayerPosition()[j][i].getPlayer().getPlayerCode();
				if (battleField.getPlayerPosition()[j][i].getPlayer() == null)
					continue;
				else if (ClanType.DOTHRAKI.getClanCode() == playerCode
						|| ClanType.WHITE_WALKER.getClanCode() == playerCode
						|| ClanType.LANISTER.getClanCode() == playerCode)
					return false;
			}
		}
		return true;
	}

	@Override
	public void loadBattleField(String fileName) {
		this.battleField = GameDAO.getInstance().loadGame(fileName, false);
		this.yourself = battleField.getYourself();
		this.fieldBreadth = battleField.getFieldBreadth();
		this.fieldLength = battleField.getFieldLength();
		populateClanHealth();
		OutputTextUtil.printBattleField(battleField, yourself);
	}

	@Override
	public void saveBattleField() {
		battleField.setYourself(yourself);
		GameDAO.getInstance().saveGame(battleField, yourself.getName());
		OutputColorUtil.printText("Game saved. Exiting!", ColorCodes.GREEN_FG);
	}

	@Override
	public void createUserPlayer(String userName, int clanTypeCode) {
		if (!isExistingUserName(userName)) {
			OutputColorUtil.printText("UserName already exists! Try again as a different user. Quiting for now.",
					ColorCodes.RED_FG);
			ResourceManager.getInstance().terminateGame();
			return;
		}
		ResourceManager.getInstance().addToListOfGameNames(userName);
		ClanType clanType = null; // default
		String power = null; // default
		int health = DefaultProperties.USER_HEALTH;
		switch (clanTypeCode) {
		case 0:
			clanType = ClanType.WHITE_WALKER;
			power = DefaultProperties.WHITE_WALKER_POWER;
			health = DefaultProperties.WHITE_WALKER_HEALTH;
			break;
		case 1:
			clanType = ClanType.LANISTER;
			power = DefaultProperties.LANISTER_POWER;
			health = DefaultProperties.LANISTER_HEALTH;
			break;
		case 2:
			clanType = ClanType.DOTHRAKI;
			power = DefaultProperties.DOTHRAKI_POWER;
			health = DefaultProperties.DOTHRAKI_HEALTH;
			break;
		}

		Clan yourClan = new Clan(userName, power, DefaultProperties.USER_COLOR_CODE, clanType);
		yourself = new Player(health, userName, null, yourClan, PlayerType.HUMAN, 'U');
	}

	@Override
	public int getHealth(Player player) {
		// Currently health of the use player only supported
		return yourself.getHealth();
	}

	private boolean isExistingUserName(String userName) {
		return !ResourceManager.getInstance().getListOfGameNames().contains(userName);
	}

	@Override
	public void saveListOfGameNames(List<String> listOfGameNames) {
		GameDAO.getInstance().saveListOfGameNames(DefaultProperties.LIST_OF_GAMES_FILE_NAME,
				ResourceManager.getInstance().getListOfGameNames());
	}

	@Override
	public void deleteBattleField() {
		GameDAO.getInstance().deleteGame(yourself.getName() + DefaultProperties.FILE_EXT);
	}

	// Exposed only for the purpose of test
	public BattleField getBattleField() {
		return battleField;
	}

	public void setBattleField(BattleField battleField) {
		this.battleField = battleField;
	}

}
