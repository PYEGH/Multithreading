package com.epam.multithreading.task4;

import java.util.List;
import java.util.Random;

public class SqrtPrinter implements Runnable {
	private static List numbers;

	public SqrtPrinter(final List numbers) {
		this.numbers = numbers;
	}

	@Override
	public void run() {
		int sum = 0;

		while(true){
			for (int i = 0; i < numbers.size(); i++) {
				sum += Math.sqrt((Integer) numbers.get(i));
			}
			System.out.println("Sqrt " + Math.sqrt(sum));
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
