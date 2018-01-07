package com.dscrape.app.utils.dataprocessing;

import java.util.Arrays;
import java.util.List;

public class DataCleaning {

	
	public static List<String> splitStrings(String inputString, String token){
		
		String[] array = inputString.split(token);
		return Arrays.asList(array);
	}
}
