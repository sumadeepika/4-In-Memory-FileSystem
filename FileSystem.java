package Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Arrays;

public class FileSystem {

	public static void main(String args[]) throws Exception {

		String location = "C:\\New";
		createfolder(location);

		String absolutePath = "C:\\NewFolder\\file1.txt";
		createFile(absolutePath);

		String content = "This is my content which would be appended "
				+ "at the end of the specified file.";
		addContentToFile(content, absolutePath);

		String sourceFile = "C:\\NewFolder\\file1.txt";
		String destFile = "C:\\NewFolder\\file2.txt";
		copyFiles(sourceFile, destFile);

		displayFileContent(absolutePath);
		String absoluteFolderPath = "C:\\NewFolder";
		listFolderContents(absoluteFolderPath);

		String filename = "file1.txt";
		searchForFile(absoluteFolderPath, filename);

		searchForFile(filename);

	}

	public static void createfolder(String location) throws Exception {

		File dir = new File(location + "Folder");
		dir.mkdir();
		System.out.println("Folder created");
	}

	public static void createFile(String absolutePath) {
		try {

			File file = new File(absolutePath);

			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void addContentToFile(String content, String absolutepath)
			throws IOException {

		File file = new File(absolutepath);
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
		System.out.println("Data successfully appended at the end of file");

	}

	public static void copyFiles(String sourceFile, String destFile)
			throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(sourceFile);
			output = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int bytesRead;

			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
			System.out.println("Data successfully copied to another file");
		} finally {
			input.close();
			output.close();
		}
	}

	public static void displayFileContent(String absolutepath)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(absolutepath));
		/*for (String line; (line = br.readLine()) != null;) {
			System.out.print(line);
			br.close();
		}*/
		String line;
		while((line = br.readLine()) != null)
		{
		    System.out.println(line);
		}
		br.close();
	}

	public static void listFolderContents(String absoluteFolderPath) {
		File file = new File(absoluteFolderPath);
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(file
				.list()));
		System.out.println(names);
	}

	public static void searchForFile(String absoluteFolderPath, String filename) {
		File folder = new File(absoluteFolderPath);

		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length+1; i++) {
			String filename1 = listOfFiles[i].getName();
			if (filename1.startsWith("file"))
				System.out.println(filename);
		}

	}

	public static void searchForFile(String filename) {
		File folder = new File(filename);

		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			String file = listOfFiles[i].getName();
			if (file.startsWith(filename))
				System.out.println(filename);
		}

	}
}


