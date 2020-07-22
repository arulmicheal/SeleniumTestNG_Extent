/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.selenium.TestNG.util.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author arulprak
 */
public class CsvData {
	// public static List<ConcurrentHashMap<String, String>> listOfData = new
	// LinkedList<>();
	private static List<String> listOfHeaders = new ArrayList<String>();
	private static List<Object[]> listOfRows = new ArrayList<Object[]>();

	/**
	 * Reads data from CSV file and stores in list of Maps
	 * 
	 * @param strFilePath
	 * @throws Exception
	 */
	public static void setData(String strFilePath) throws Exception {
		listOfHeaders = new ArrayList<String>();
		listOfRows = new ArrayList<Object[]>();
		getDataListFromCsv(strFilePath);
	}

	/**
	 * Getting csv column names
	 * 
	 * @return Object[]
	 * @throws Exception
	 */
	public static Object[] getCsvHeaders() throws Exception {
		return listOfHeaders.toArray();
	}

	/**
	 * Gets Csv file data rows
	 * 
	 * @return List<Object[]>
	 * @throws Exception
	 */
	public static List<Object[]> getCsvRows() throws Exception {
		return listOfRows;
	}

	/**
	 * Updates Csv data rows
	 * 
	 * @return List<Object[]>
	 * @throws Exception
	 */
	public static void setCsvRows(Vector<Object[]> vecRows) throws Exception {
		listOfRows = new ArrayList<Object[]>();
		listOfRows = vecRows;
	}

	/**
	 * Get particular cell value from CSV data
	 * 
	 * @param iRowNum
	 * @param strColumnName
	 * @return String
	 * @throws Exception
	 */
	public static String getData(int iRowNum, String strColumnName) throws Exception {
		if(listOfRows.size()<=iRowNum)
		{
			throw new ArrayIndexOutOfBoundsException("Row "+iRowNum+" does not available in test data");
		}
		return listOfRows.get(iRowNum)[listOfHeaders.indexOf(strColumnName)].toString();
	}

	/**
	 * Reads data from CSV file
	 * 
	 * @param strFilePath
	 * @return List<ConcurrentHashMap<String, String>>
	 * @throws Exception
	 */
	/*
	 * public static List<ConcurrentHashMap<String, String>>
	 * getDataFromCsv(String strFilePath) throws Exception { try { File fileCsv
	 * = new File(strFilePath); if (fileCsv.exists() && fileCsv.isFile()) {
	 * FileReader readerFile = new FileReader(fileCsv); BufferedReader
	 * bufferFile = new BufferedReader(readerFile); String strLine = "";
	 * ConcurrentHashMap<String, String> hashMapCsv = new ConcurrentHashMap<>();
	 * int iCount = 0; while ((strLine = bufferFile.readLine()) != null) {
	 * String[] arrOfValues = strLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)",
	 * -1); if (iCount == 0) { for (String strEach : arrOfValues) {
	 * listOfHeaders.add(strEach); } iCount++; continue; } hashMapCsv = new
	 * ConcurrentHashMap<>(); for (int iValues = 0; iValues <
	 * arrOfValues.length; iValues++) {
	 * hashMapCsv.put(listOfHeaders.get(iValues), arrOfValues[iValues]); }
	 * listOfRows.add(arrOfValues); listOfData.add(hashMapCsv); iCount++; } } }
	 * catch (Exception ex) { throw new Exception(ex); } return listOfData; }
	 */
	public static List<Object[]> getDataListFromCsv(String strFilePath) throws Exception {
		try {
			File fileCsv = new File(strFilePath);
			if (fileCsv.exists() && fileCsv.isFile()) {
				FileReader readerFile = new FileReader(fileCsv);
				BufferedReader bufferFile = new BufferedReader(readerFile);
				String strLine = "";
				int iCount = 0;
				while ((strLine = bufferFile.readLine()) != null) {
					String[] arrOfValues = strLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
					// Fetching headers from CSV file
					if (iCount == 0) {
						for (String strEach : arrOfValues) {
							listOfHeaders.add(strEach);
						}
						iCount++;
						continue;
					}
					listOfRows.add(arrOfValues);
					iCount++;
				}
				bufferFile.close();
			}
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		return listOfRows;
	}

	public static Object[][] getDataObjectFromCsv() throws Exception {
		Object[][] objectArray = null;
		try {
			objectArray = listOfRows.stream().map(p -> new Object[] { p }).toArray(Object[][]::new);
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		return objectArray;
	}

}
