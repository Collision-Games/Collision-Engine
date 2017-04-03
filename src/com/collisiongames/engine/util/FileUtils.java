package com.collisiongames.engine.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	public static String readLines(File file) {
		StringBuilder builder = new StringBuilder();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line;
		
		try {
			while((line = reader.readLine()) != null)
				builder.append(line).append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
}