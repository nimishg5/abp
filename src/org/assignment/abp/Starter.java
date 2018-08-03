package org.assignment.abp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Starter {
	public static void main(String[] args) throws IOException {
		// code for reading a file from resources folder
		ClassLoader classLoader = new Starter().getClass().getClassLoader();
		String fileName = "data.txt";
		File file = new File(classLoader.getResource(fileName).getFile());
		String outputFile = "output.txt";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		int counter = 0;
		HashSet<String> set = new HashSet<String>();
		// variable for counting seconds
		Long startTime = System.currentTimeMillis();
		MyWriter writer = new MyWriter(outputFile);
		writer.openFile();
		String temp = "", string;
		while((string = bufferedReader.readLine()) != null) {
			// splitting the string for taking only words and removing spaces or tabs
			String[] record = string.split("[ \\t]");
			String person = record[0];
			if(temp.equals(person)) {
				// checking if the same person purchased wines 3 times then ignoring that person's demand
				if (counter > 2) {
					continue;
				}
			} else {
				temp = person;
				counter = 0;
			}
			
			if(!set.contains(record[1])) {
				// inserting unique records in hashset so that person cannot purchase already purchased bottle
				set.add(record[1]);
				counter++;
				if(string != null)
					writer.writeThisLine(record[0] + " " + record[1]);
			}
		}
		// closing the file after reading it entirely
		writer.closeWriting();
		writer.writeAtBegining(String.valueOf(set.size()));
		System.out.println("File got written at "+ System.getProperty("user.home") + File.separator + "Desktop" + File.separator + outputFile);
		System.out.println("Time consumed by code " + ((System.currentTimeMillis() - startTime)/1000) + " seconds");
		bufferedReader.close();
	}
}
