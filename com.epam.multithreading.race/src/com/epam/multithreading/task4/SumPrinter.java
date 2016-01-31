package com.epam.multithreading.task4;

import java.util.ArrayList;
import java.util.List;

public class SumPrinter implements Runnable {

	private static List numbers;

	public SumPrinter(final List numbers) {
		this.numbers = numbers;
	}

	@Override
	public void run() {
		int sum = 0;

		while(true){
			for (Object temp: numbers) {
				sum += (Integer) temp;
			}
			System.out.println("Sum " + sum);
			sum = 0;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}
