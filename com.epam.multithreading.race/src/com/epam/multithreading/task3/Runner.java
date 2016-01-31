package com.epam.multithreading.task3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Class starts 3 thread: 1st thread removes elements from collection 2nd thread
 * cleans all collection 3rd thread added new elements to collection
 * 
 * All 3 threads works with the same collection, so synchronization
 * (synchronized blocks) was added. Synchronized blocks at classes has
 * description why they was added.
 * 
 * @author Pavel
 * 
 */
public class Runner {
	public static void main(String[] args) {
		
		 List numbers = new ArrayList<>(); Object monitor = new Object();
		 
		 ElementRemover elementRemover = new ElementRemover(numbers, monitor);
		 CollectionCleaner collectionCleaner = new CollectionCleaner(numbers,
		 monitor); ElementCreator elementCreator = new ElementCreator(numbers,
		 monitor);
		 
		 Thread t1 = new Thread(elementRemover); Thread t2 = new
		 Thread(collectionCleaner); Thread t3 = new Thread(elementCreator);
		 
		 t1.start(); t2.start(); t3.start();
		
		
	}

}