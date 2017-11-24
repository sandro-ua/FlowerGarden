package com.flowergarden.run;

import java.io.File;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

public class RunBouquetFolder {

	public static void main(String[] args) {
		MarriedBouquet bouquet = new MarriedBouquet();
		for (int i = 0; i < 5; i++) {
			bouquet.addFlower(new Rose(false, 12, 15, new FreshnessInteger(2)));
		}
		for (int i = 0; i < 2; i++) {
			bouquet.addFlower(new Chamomile(7, 5, 2, new FreshnessInteger(1)));
		}

		MarriedBouquet bouquet2 = new MarriedBouquet();
		for (int i = 0; i < 3; i++) {
			bouquet2.addFlower(new Rose(false, 7, 15, new FreshnessInteger(2)));
		}
		for (int i = 0; i < 7; i++) {
			bouquet2.addFlower(new Chamomile(7, 9, 2, new FreshnessInteger(1)));
		}
		
		//bouquet.saveToFolder("bouquets" + "/" + "married_boquet_" + bouquet.hashCode());
		//bouquet2.saveToFolder("bouquets" + "/" + "married_boquet_" + bouquet2.hashCode());
		bouquet.assembleFromFolder("bouquets/married_boquet_356573597");
	}

}
