/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.selenium.TestNG.util.Data;

import org.testng.annotations.DataProvider;

/**
 *
 * @author arulprak
 */
public class TestData {

	public static void initializeData(String strFilePath) throws Exception {
		CsvData.setData(System.getProperty("user.dir") + "\\TestData\\data.csv");
	}

	@DataProvider()
	public static Object[][] getDataFromDataprovider() {
		return new Object[][] { { "https://czcholstc001173.prg-dc.dhl.com/fat_afsm", "arulprak", "Jun@9940093354" } };
	}

	@DataProvider(name = "csvdata")
	public static Object[][] getDataFromCSV() throws Exception {
		return CsvData.getDataObjectFromCsv();
	}

	public static String getData(int iRowNum, String strColumnName) throws Exception {
		return CsvData.getData(iRowNum-1, strColumnName);
	}
}
