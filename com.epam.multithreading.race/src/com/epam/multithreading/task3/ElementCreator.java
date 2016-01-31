package com.epam.multithreading.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class creates elements
 * @author Pavel
 *
 */
public class ElementCreator implements Runnable {

	private List numbers;
	private final Object monitor;

	public ElementCreator(final List numbers, final Object monitor) {
		this.numbers = numbers;
		this.monitor = monitor;
	}

	@Override
	public void run() {
		while (true) {
			
			synchronized(numbers){
				int number = new Random().nextInt(100);
				numbers.add(number);
				if (numbers.size() == 1) {
					// Here we notify that collection is not empty. It is necessary because thread 
					// which removes element waits in case collection is empty
					synchronized (monitor) {
						monitor.notifyAll();
					}
				}
				System.out.println("Added " + number + " Total " + numbers.size());
			}
			


			// Sleep was added just to provide possibility to see output at console
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
