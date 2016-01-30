package com.epam.multithreading.race;

import org.apache.log4j.Logger;

public class Car implements Runnable {

	private static final long MAX_DISTANCE = 10000;
	private static final long DISQUALIFICATION_TIME = 1000 * 5; // 5 seconds
	private static final long BUFFER = 1000;

	Logger log = Logger.getLogger(getClass());

	private long friction;
	private long distance;

	private String name;

	public Car(String name, long friction) {
		this.name = name;
		this.friction = friction;
	}

	@Override
	public void run() {

		long startTime = System.currentTimeMillis();
		long dsqTime = startTime + DISQUALIFICATION_TIME;
		try {
			while (distance < MAX_DISTANCE) {

				Thread.sleep(friction);
				distance += 100;
				log.info(name + " " + distance);
			}

			// When car finished race it tries to write it name to variable
			// 'winnerName'.
			// Only First car will be able to do this.
			System.out.println(this.name + " finished");
			if (RaceRunner.getWinnerName().equals("")) {
				synchronized (RaceRunner.getWinnerName()) {
					if (RaceRunner.getWinnerName().equals("")) {
						RaceRunner.setWinnerName(this.name);
					}
				}
			}
		} catch (InterruptedException e) {
			// BUFFER variable means time from 1st tread started till last
			// thread started
			if (System.currentTimeMillis() - startTime < DISQUALIFICATION_TIME
					+ BUFFER) {
				System.out.println(this.name + " was disqualified");
				log.info(this.name + " was disqualified");
			} else {
				{
					System.out.println(e);
					log.error(e);
				}
			}
		}

	}
}