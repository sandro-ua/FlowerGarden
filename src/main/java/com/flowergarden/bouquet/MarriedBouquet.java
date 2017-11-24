package com.flowergarden.bouquet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;


public class MarriedBouquet implements Bouquet<GeneralFlower> {

	private float assemblePrice = 120;
	private List<GeneralFlower> flowerList = new ArrayList<>();

	@Override
	public float getPrice() {
		if (flowerList.isEmpty()) return 0;
		float result = assemblePrice;
		for (GeneralFlower generalFlower : flowerList) {
			result += generalFlower.getPrice();
		}
		return result;
	}

	@Override
	public void addFlower(GeneralFlower flower) {
			flowerList.add(flower);
	}

	@Override
	public Collection<GeneralFlower> searchFlowersByLenght(int start, int end) {
		List<GeneralFlower> searchResult = new ArrayList<GeneralFlower>();
		for (GeneralFlower flower : flowerList) {
			if (flower.getLenght() >= start && flower.getLenght() <= end) {
				searchResult.add(flower);
			}
		}
		return searchResult;
	}

	@Override
	public void sortByFreshness() {
		Collections.sort(flowerList);
	}

	@Override
	public Collection<GeneralFlower> getFlowers() {
		return flowerList;
	}

	public void setAssembledPrice(float price) {
		assemblePrice = price;
	}
	
	public MarriedBouquet assembleFromFolder(String folderPath) {

		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();

/*		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}*/

		String f = null;
		String l = null;
		String p = null;

		MarriedBouquet marriedBouquet = null;

		for (File file : folder.listFiles()) {
			Scanner input = null;
			try {
				input = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			while (input.hasNext()) {
				String line = input.nextLine();

				String[] split = line.split(":");

				if (split[0] == "F:") f = split[1];
				if (split[0] == "L:") l = split[1];
				if (split[0] == "P:") p = split[1];

				// do something with file
				input.close();
			}

			marriedBouquet = new MarriedBouquet();

			for (int i = 0; i < 5; i++) {
				marriedBouquet.addFlower(
						new Rose(false, Integer.valueOf(l), Integer.valueOf(p), new FreshnessInteger(Integer.valueOf(f))));
			}


		}
		return marriedBouquet;
	}

	public void saveToFolder(String folderPath){

		File dir = new File (folderPath);
			if (!dir.exists()) dir.mkdirs();

		for (GeneralFlower flower : flowerList) {
			File flowerTxt = new File(folderPath,
					flowerList.indexOf(flower) + "_" + flower.getClass().getSimpleName().toString() + ".txt");

			String content = "F:" + flower.getFreshness().getFreshness() + System.lineSeparator() +
					"L:"+ flower.getLenght() + System.lineSeparator() +
					"P:" + flower.getPrice();

			if (!flowerTxt.exists()) try {
				flowerTxt.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			BufferedWriter bw;
			FileWriter fw = null;
			try {
				fw = new FileWriter(flowerTxt.getAbsoluteFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
			bw = new BufferedWriter(fw);

			// buffer write
			try {
				bw.write(content);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// buffer close
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
