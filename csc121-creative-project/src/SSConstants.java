import java.util.Random;
import processing.core.*;

/**
 * Stores constants related to Subway Surfers
 */


public class SSConstants {
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	public static final int CAMERA_Z = 2000;
	
	public static float gameSpd = 20;
	
	public static final int PLAYER_Z = 1500;
	public static int floorLvl = HEIGHT;
	
	public static final int TRAIN_WIDTH = 250;
	public static final int TRAIN_HEIGHT = 305;
	public static final int TRAIN_INITIAL_Z = -3000;
	public static final int TRAIN_Y = 655;
	public static final int TRAIN_TOP = floorLvl - TRAIN_HEIGHT;
	public static final int RAMP_LENGTH = 200;
	
	public static final int OBSTACLE_WIDTH = 260;
	public static final int OBSTACLE_HEIGHT = 150;
	public static final int OBSTACLE_DEPTH = 60;


	

	public static final Track[] tracks = { new Track(100), new Track(600), new Track(1100) };
	public static final double overallSpawnRate = 0.9;
	public static final double t1SpawnRate = 0.01; 
	public static final double t2SpawnRate = 0.005; // needs to be the lowest out of the three
	public static final double t3SpawnRate = 0.0085; 
	public static final double barrierRate = 0.75; // chance that a barrier is spawned instead of a train

	
	
	public static final int ENVIRONMENT_Y = 820;
	public static final int BOARD_INITIAL_Z = -3500;
	public static final int BOARD_AMT = 30; // DO NOT CHANGE
	public static int BUILDING_SPAWNPOINT = SSConstants.TRAIN_INITIAL_Z - 1000;
	
	public static final int DELETE_POINT = CAMERA_Z - 300;
	
	
    public static final Random rgen = new Random();
    
    public static Animation playerSprite;

}
