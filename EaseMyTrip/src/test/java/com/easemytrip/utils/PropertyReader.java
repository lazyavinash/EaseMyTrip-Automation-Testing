package com.easemytrip.utils;

import java.io.FileInputStream;

import java.util.Properties;

public class PropertyReader {

	private static Properties propInstance;

	// to load .property file into prop

	public static Properties getInstance() {

		if (propInstance == null) {
			propInstance = new Properties();
			try {
				propInstance.load(new FileInputStream(".\\src\\test\\resources\\files\\settings.property"));
			} catch (Exception e) {
				System.out.println("Error with reading property File " + e.getMessage());
			}
			return propInstance;
		} else {
			return propInstance;
		}
	}
}
