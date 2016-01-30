package com.epam.multithreading.race;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RaceRunner {

	private static String winnerName = "";

	public static void main(String[] args) {
		List<Thread> participants = createCars();
		
		startRace(participants);
		disqualifyRandomCar(participants);
		writeWinnerNameAfterRaceEnd(participants);
	}

	public static String getWinnerName() {
		return winnerName;
	}

	public static void setWinnerName(String winnerName) {
		RaceRunner.winnerName = winnerName;
	}

	private static List<Thread> createCars() {
		List<Thread> participants = new ArrayList<>();

		Car car1 = new Car("Car1", 140);
		Car car2 = new Car("Car2", 100); // potential winner
		Car car3 = new Car("Car3", 139);
		Car car4 = new Car("Car4", 200);
		Car car5 = new Car("Car5", 150);

		Thread t1 = new Thread(car1);
		Thread t2 = new Thread(car2);
		Thread t3 = new Thread(car3);
		Thread t4 = new Thread(car4);
		Thread t5 = new Thread(car5);

		participants.add(t1);
		participants.add(t2);
		participants.add(t3);
		participants.add(t4);
		participants.add(t5);
		return participants;
	}

	private static void startRace(List<Thread> participants) {
		for (Thread t : participants) {
			t.start();
		}
	}

	private static void disqualifyRandomCar(List<Thread> participants) {
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		participants.get(new Random().nextInt(participants.size())).interrupt();
	}

	private static void writeWinnerNameAfterRaceEnd(List<Thread> participants) {
		while (true) {
			int counterOfFinishedCars = 0;
			for (Thread t : participants) {
				if (!t.isAlive()) {
					counterOfFinishedCars++;
				}
			}
			if (counterOfFinishedCars == participants.size()) {
				break;
			}
		}
		System.out.println("Winner is " + winnerName);
	}

}
