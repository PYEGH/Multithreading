package com.epam.multithreading.task4;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Runner {

	public static void main(String[] args) {

		CopyOnWriteArrayList numbers = new CopyOnWriteArrayList<>();
		NumberGenerator numberGenerator = new NumberGenerator(numbers);
		SqrtPrinter sqrtPrinter = new SqrtPrinter(numbers);
		SumPrinter sumPrinter = new SumPrinter(numbers);
		
		
		Thread t1 = new Thread(numberGenerator);
		Thread t2 = new Thread(sqrtPrinter);
		Thread t3 = new Thread(sumPrinter);
		
		t1.start();
		t2.start();
		t3.start();
	}

}
