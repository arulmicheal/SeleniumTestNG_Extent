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
public class DataProviderClass {
	public DataProviderClass() {
		try {
			CsvData.setData(System.getProperty("user.dir")+"\\TestData\\data.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@DataProvider()
    public static Object[][] getDataFromDataprovider(){
        return new Object[][] {
            { "https://czcholstc001173.prg-dc.dhl.com/fat_afsm","arulprak", "Jun@9940093354" }
        };  
}
	@DataProvider(name="csvdata")
    public static Object[][] getDataFromCSV() throws Exception{
		Object[][] arrayOfObjects=CsvData.getDataObjectFromCsv();
        return arrayOfObjects;  
}
}
