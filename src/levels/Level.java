package levels;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Crabby;
import main.Game;
import objects.Cannon;
import objects.GameContainer;
import objects.Grass;
import objects.Potion;
import objects.Spike;
import utilz.HelpMethods;

import static utilz.HelpMethods.GetCrabs;
import static utilz.HelpMethods.GetPlayerSpawn;

public class Level {
	
	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Crabby> crabs;
	private ArrayList<Potion> potions;
	private ArrayList<Spike> spikes;
	private ArrayList<GameContainer> containers;
	private ArrayList<Cannon> cannons;
	private ArrayList<Grass> grass = new ArrayList<>();
	private int lvlTilesWide;
	private int maxTilesOffset;
	private int maxLvlOffset;
	private Point playerSpawn;

	public Level(BufferedImage img) {
		this.img = img;
		lvlData = new int[img.getHeight()][img.getWidth()];
		loadLevel();
//		createLevelData();
		createEnemies();
		createPotions();
		createContainers();
		createCannons();
		createSpikes();
		calcLvlOffsets();
		calcPlayerSpawn();
	}

	private void loadLevel() {
		for (int y = 0; y < img.getHeight(); y++)
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
				int red = c.getRed();
//				int green = c.getGreen();
//				int blue = c.getBlue();

				loadLevelData(red, x, y);
//				loadEntities(green, x, y);
//				loadObjects(blue, x, y);
			}
		
	}
	
	private void loadLevelData(int redValue, int x, int y) {
		if (redValue >= 50)
			lvlData[y][x] = 0;
		else
			lvlData[y][x] = redValue;
		switch (redValue) {
		case 0, 1, 2, 3, 30, 31, 33, 34, 35, 36, 37, 38, 39 -> 
		grass.add(new Grass((int) (x * Game.TILES_SIZE), (int) (y * Game.TILES_SIZE) - Game.TILES_SIZE, getRndGrassType(x)));
		}
	}
	
	private int getRndGrassType(int xPos) {
		return xPos % 2;
	}

	private void createCannons() {
		cannons = HelpMethods.GetCannons(img);
		
	}

	private void createSpikes() {
		spikes = HelpMethods.GetSpikes(img);
		
	}

	private void createContainers() {
		containers = HelpMethods.GetContainers(img);
		
		
	}

	private void createPotions() {
		potions = HelpMethods.GetPotions(img);
		
	}

	private void calcPlayerSpawn() {
		playerSpawn = GetPlayerSpawn(img);
		
	}

	private void calcLvlOffsets() {
		lvlTilesWide = img.getWidth();
		maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
		maxLvlOffset = Game.TILES_SIZE * maxTilesOffset;
		
	}

	private void createEnemies() {
		crabs = GetCrabs(img);
		
	}

//	private void createLevelData() {
//		lvlData = GetLevelData(img);
//		
//	}

	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}

	public int[][] getLevelData() {
		return lvlData;
	}
	
	public int getLvlOffset() {
		return maxLvlOffset;
	}
	
	public ArrayList<Crabby> getCrabs(){
		return crabs;
	}
	
	public Point getPlayerSpawn() {
		return playerSpawn;
	}
	
	public ArrayList<Potion> getPotions(){
		return potions;
	}
	
	public ArrayList<GameContainer> getContainers(){
		return containers;
	}
	
	public ArrayList<Spike> GetSpike(){
		return spikes;
	}
	
	public ArrayList<Cannon> GetCannons(){
		return cannons;
	}
	
	public ArrayList<Grass> getGrass() {
		return grass;
	}

}
