package ui_DayCare.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	public static List<String> readCSVFile(String path, Boolean ignoreHeader) {
		List<String> result = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line;
			if (ignoreHeader) in.readLine();
			while((line = in.readLine()) != null) {
				result.add(line);
			}
			in.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void writeCsv(List<String> objects, String header, String outputPath) {
		try {
			FileWriter fw = new FileWriter(outputPath);
			BufferedWriter out = new BufferedWriter(fw);
			if (!header.isEmpty()) {
				out.write(header);
				out.newLine();
			}
			for (String s: objects) {
				out.write(s);
				out.newLine();
			}
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
