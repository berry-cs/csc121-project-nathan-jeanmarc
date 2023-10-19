import java.util.ArrayList;

public class Spawner {

	static ArrayList<Train> t1Trains = new ArrayList<Train>(); // trains on track 1
	static ArrayList<Train> t2Trains = new ArrayList<Train>(); // trains on track 2
	static ArrayList<Train> t3Trains = new ArrayList<Train>(); // trains on track 3

	static ArrayList<Train> allTrains = new ArrayList<Train>(); // combined list of all trains
	/*
	 * Spawner() { t1Trains = new ArrayList<Train>(); // trains on track 1 t2Trains
	 * = new ArrayList<Train>(); // trains on track 2 t3Trains = new
	 * ArrayList<Train>(); // trains on track 3
	 * 
	 * allTrains = new ArrayList<Train>(); // combined list of all trains }
	 */

	static ArrayList<Train> addTrain(int track) {
		switch (track) {
		case 1:
			t1Trains.add(
					new Train(SSConstants.rgen.nextInt(4001 - 600) + 600, track, SSConstants.rgen.nextInt(20 - 5) + 5, false));
			return t1Trains;
		case 2:
			t2Trains.add(
					new Train(SSConstants.rgen.nextInt(4001 - 600) + 600, track, SSConstants.rgen.nextInt(20 - 5) + 5, false));
			return t2Trains;
		case 3:
			t3Trains.add(
					new Train(SSConstants.rgen.nextInt(4001 - 600) + 600, track, SSConstants.rgen.nextInt(20 - 5) + 5, false));
			return t3Trains;
		default:
			return t1Trains;
		}

	}

	static ArrayList<Train> getAllTrains() {
		if (allTrains.size() > 0) {
			allTrains.clear();
		}
		allTrains.addAll(t1Trains);
		allTrains.addAll(t2Trains);
		allTrains.addAll(t3Trains);
		System.out.println(allTrains.size());
		return allTrains;
	}
	
	static void updateTrains() {
		Spawner.getAllTrains().removeIf(train -> train.offScreen); // removes trains that are																				// off the screen
		Spawner.getAllTrains().forEach(train -> train.update());
	}

}
