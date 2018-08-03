package org.assignment.abp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

// class for writing the data into the specified file
public class MyWriter {
	String fileName;
	File file;
	PrintWriter writer;
	String finalFileName;
	
	// constructor for saving file name and creating file object
	public MyWriter(String fileName) {
		this.fileName = fileName;
		// This is useful for writing the output file at any user's desktop location
		String userHomeFolder = System.getProperty("user.home");
		finalFileName = userHomeFolder + File.separator + "Desktop" + File.separator + fileName;
		file = new File(finalFileName);
	}
	
	// Opening a file to start writing
	public void openFile() throws IOException {
			file.createNewFile();
			writer = new PrintWriter(finalFileName, "UTF-8");
			writer.println("      ");
	}
	
	// Writing specific lines one after the other
	public void writeThisLine(String string) {
		writer.println(string);
	} 
	
	// closing the writer object and flushing the data present in it already
	public void closeWriting() {
		writer.flush();
		writer.close();
	}
	
	// method for writing data on the top of the file
	public void writeAtBegining(String size) throws IOException {
		RandomAccessFile f = new RandomAccessFile(new File(finalFileName), "rw");
		f.seek(0); // to the beginning
		f.write((size +" \n").getBytes());
		f.close();
	}
}
