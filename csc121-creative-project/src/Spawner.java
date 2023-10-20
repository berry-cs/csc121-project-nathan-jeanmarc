import java.util.ArrayList;

public class Spawner {

	private static ArrayList<Train> t1Trains = new ArrayList<Train>(); // trains on track 1
	private static ArrayList<Train> t2Trains = new ArrayList<Train>(); // trains on track 2
	private static ArrayList<Train> t3Trains = new ArrayList<Train>(); // trains on track 3

	private static ArrayList<Barrier> t1Barriers = new ArrayList<Barrier>(); // trains on track 1
	private static ArrayList<Barrier> t2Barriers = new ArrayList<Barrier>(); // trains on track 2
	private static ArrayList<Barrier> t3Barriers = new ArrayList<Barrier>(); // trains on track 3

	private static ArrayList<IObstacle> allObstacles = new ArrayList<IObstacle>(); // combined list of all trains

	private static double chance;
	private static double barrierChance;

	/**
	 * If the chance falls below the spawn threshold, attempts to spawn trains based
	 * on individual track thresholds
	 */
	static void spawn() {
			chance = SSConstants.rgen.nextDouble();
	
				//chance = SSConstants.rgen.nextDouble(); // can be removed or left, just adds more variability
	
				if (chance <= SSConstants.t1SpawnRate && chance > SSConstants.t2SpawnRate) {
					barrierChance = SSConstants.rgen.nextDouble();
					
					if (barrierChance <= SSConstants.barrierRate) {
						addBarrier(1);
					} else {
						int rampChance = SSConstants.rgen.nextInt(2);
						if (rampChance == 1) addTrain(1, true);
						else addTrain(1, false);
					}
				}
				if (chance <= SSConstants.t2SpawnRate) {
					barrierChance = SSConstants.rgen.nextDouble();
					
					if (barrierChance <= SSConstants.barrierRate) {
						addBarrier(2);
					} else {
						addTrain(2, false);
					}
				}
				if (chance <= SSConstants.t3SpawnRate && chance > SSConstants.t2SpawnRate) {
					barrierChance = SSConstants.rgen.nextDouble(); 
					
					if (barrierChance <= SSConstants.barrierRate) {
						addBarrier(3);
					} else {
						addTrain(3, false);
					}
				}
	}

	/**
	 * Adds a barrier to the given track
	 */
	static void addBarrier(int track) {
		Barrier b = new Barrier(track);
		switch (track) {
		case 1:
			t1Barriers.add(b);
			break;
		case 2:
			t2Barriers.add(b);
			break;
		case 3:
			t3Barriers.add(b);
			break;
		}
	}

	/**
	 * Adds a train with a random length and speed to the given track
	 */
	static void addTrain(int track, boolean hasRamp) {
		switch (track) {
		case 1:
			t1Trains.add(new Train(SSConstants.rgen.nextInt(3001 - 600) + 600, track,
					SSConstants.rgen.nextInt(20 - 5) + 5, hasRamp));
			break;
		case 2:
			t2Trains.add(new Train(SSConstants.rgen.nextInt(3001 - 600) + 600, track,
					SSConstants.rgen.nextInt(20 - 5) + 5, hasRamp));
			break;
		case 3:
			t3Trains.add(new Train(SSConstants.rgen.nextInt(3001 - 600) + 600, track,
					SSConstants.rgen.nextInt(20 - 5) + 5, hasRamp));
			break;
		}
	}

	/**
	 * Returns a combined list of all the obstacles in the three track arrays
	 */
	static ArrayList<IObstacle> getAllObstacles() {
		if (allObstacles.size() > 0) {
			allObstacles.clear();
		}
		allObstacles.addAll(t1Trains);
		allObstacles.addAll(t2Trains);
		allObstacles.addAll(t3Trains);
		allObstacles.addAll(t1Barriers);
		allObstacles.addAll(t2Barriers);
		allObstacles.addAll(t3Barriers);
		return allObstacles;
	}

	/**
	 * Updates all the trains in each track
	 */
	static void updateObstacles() {
		t1Trains.removeIf(ob -> ob.isOffScreen()); // off the screen
		t1Trains.forEach(ob -> ob.update());

		t2Trains.removeIf(ob -> ob.isOffScreen()); // off the screen
		t2Trains.forEach(ob -> ob.update());

		t3Trains.removeIf(ob -> ob.isOffScreen()); // off the screen
		t3Trains.forEach(ob -> ob.update());
		
		t1Barriers.removeIf(ob -> ob.isOffScreen()); // off the screen
		t1Barriers.forEach(ob -> ob.update());

		t2Barriers.removeIf(ob -> ob.isOffScreen()); // off the screen
		t2Barriers.forEach(ob -> ob.update());

		t3Barriers.removeIf(ob -> ob.isOffScreen()); // off the screen
		t3Barriers.forEach(ob -> ob.update());
	}

}
