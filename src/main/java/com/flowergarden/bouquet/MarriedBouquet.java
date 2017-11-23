package com.flowergarden.bouquet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.flowergarden.flowers.GeneralFlower;

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
	
	public MarriedBouquet assembleFromFolder(String folderPath){
		return null;
		
	}

	public void saveToFolder(String folderPath){

/*		String[] folders = folderPath.split(File.pathSeparator);
		for (int i = 0; i < folders.length; i++) {
			new File(folders[i]);
			//check and create
		}*/

		File dir = new File (folderPath);
			if (!dir.exists()) dir.mkdirs();

			//check and create
		//}
		for (GeneralFlower flower : flowerList) {
			File flowerTxt = new File(folderPath,
					flowerList.indexOf(flower) + "_" + flower.getClass().getSimpleName().toString() + ".txt");

			String content = "F: " + flower.getFreshness().getFreshness() + System.lineSeparator() +
					"L: "+ flower.getLenght() + System.lineSeparator() +
					"P: " + flower.getPrice();

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
			//check and save to file
		}
	}
}
