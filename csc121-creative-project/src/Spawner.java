import java.util.ArrayList;

public class Spawner {

	static ArrayList<Train> t1Trains = new ArrayList<Train>(); // trains on track 1
	static ArrayList<Train> t2Trains = new ArrayList<Train>(); // trains on track 2
	static ArrayList<Train> t3Trains = new ArrayList<Train>(); // trains on track 3

	static ArrayList<Train> allTrains = new ArrayList<Train>(); // combined list of all trains

	static double chance;

	static void spawn() {
		chance = SSConstants.rgen.nextDouble();
		
		if (chance <= SSConstants.overallSpawnRate) {
			
			chance = SSConstants.rgen.nextDouble();
			
			if (chance <= SSConstants.t1SpawnRate) {
				addTrain(1);
			}
			if (chance <= SSConstants.t2SpawnRate) {
				addTrain(2);
			}
			if (chance <= SSConstants.t3SpawnRate) {
				addTrain(3);
			}
		}
	}

	/**
	 * Adds a train with a random length and speed to the given track
	 */
	static void addTrain(int track) {
		switch (track) {
		case 1:
			t1Trains.add(new Train(SSConstants.rgen.nextInt(3001 - 600) + 600, track,
					SSConstants.rgen.nextInt(20 - 5) + 5, false));
			break;
		case 2:
			t2Trains.add(new Train(SSConstants.rgen.nextInt(3001 - 600) + 600, track,
					SSConstants.rgen.nextInt(20 - 5) + 5, false));
			break;
		case 3:
			t3Trains.add(new Train(SSConstants.rgen.nextInt(3001 - 600) + 600, track,
					SSConstants.rgen.nextInt(20 - 5) + 5, false));
			break;
		}
	}

	/**
	 * Returns a combined list of all the trains in the three track arrays
	 */
	static ArrayList<Train> getAllTrains() {
		if (allTrains.size() > 0) {
			allTrains.clear();
		}
		allTrains.addAll(t1Trains);
		allTrains.addAll(t2Trains);
		allTrains.addAll(t3Trains);
		return allTrains;
	}

	/**
	 * Updates all the trains in each track
	 */
	static void updateTrains() {
		t1Trains.removeIf(train -> train.offScreen); // off the screen
		t1Trains.forEach(train -> train.update());

		t2Trains.removeIf(train -> train.offScreen); // off the screen
		t2Trains.forEach(train -> train.update());

		t3Trains.removeIf(train -> train.offScreen); // off the screen
		t3Trains.forEach(train -> train.update());
	}

}
