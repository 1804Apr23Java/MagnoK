package com.revature.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.revature.media.Book;
import com.revature.media.Movie;

public class SerializeDemo {
	
	// Trying to mirror what she is doing

	public static void main(String[] args) {
		
		String filename = "srs/serializedThings/Movies";
		
	}

	public static void serializeObject(String filename, Object o) {
		
		try {
		// Use a FileOutputStream to write bytestream to a file
			FileOutputStream fileOut = new FileOutputStream(filename);
		// Use an ObjectOutputStream to represent an object as a bytestream
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			// Actually serialize the object
			out.writeObject(o);
			// Close resources
			out.close();
			fileOut.close();
			System.out.println("serialization successful");
		} catch (FileNotFoundException f) {
			// If we give a file that does not exist
			f.printStackTrace();
			// More specific exception
		} catch(IOException e) {
			e.printStackTrace();
			// More general exception
		}
	}
	
}
