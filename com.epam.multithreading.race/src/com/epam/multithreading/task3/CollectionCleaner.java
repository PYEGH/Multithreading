package com.epam.multithreading.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class cleans collection
 * 
 * @author Pavel
 * 
 */
public class CollectionCleaner implements Runnable {

	private List numbers;
	private final Object monitor;

	public CollectionCleaner(final List numbers, final Object monitor) {
		this.numbers = numbers;
		this.monitor = monitor;
	}

	@Override
	public void run() {
		while (true) {
			synchronized(numbers){				
				System.out.println("Cleaning ");
				numbers.clear();
			}


			// Sleep was added just to provide possibility to see output at
			// console
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
