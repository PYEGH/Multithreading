package com.epam.multithreading.task3;

import java.util.List;
import java.util.Random;

/**
 * Class removes elements
 * 
 * @author Pavel
 * 
 */
public class ElementRemover implements Runnable {
	private List numbers;
	private final Object monitor;

	public ElementRemover(final List numbers, final Object monitor) {
		this.numbers = numbers;
		this.monitor = monitor;

	}

	@Override
	public void run() {

		while (true) {

			// Here we check if collection has elements,
			// and if no we are waiting the moment when new element will we
			// added.
			// Without such synchronization IndexOutOfBox exception can occurred
			// and the thread will be stopped

			synchronized (monitor) {
				if (numbers.size() == 0) {
					try {
						monitor.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			// Here Collection needs to be synchronized, because
			// at the 1st line removedElement is taken just for ouptut
			// at the 2nd line element is really removed, but between this to
			// action
			// collection can be changed by other Threads and shown information
			// about removed element
			// and really removed element can be different.
			synchronized (numbers) {
				int removedElement = (int) numbers.get(0);
				numbers.remove(0);
				System.out.println("Removed " + removedElement + " Total "
						+ numbers.size());
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
