package com.epam.multithreading.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGenerator implements Runnable{

	private static List numbers;
	
	public NumberGenerator(final List numbers){
		this.numbers = numbers;
	}
	
	@Override
	public void run() {
		while(true){
			int number = new Random().nextInt(100);
			System.out.println(number);
			numbers.add(number);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

}
