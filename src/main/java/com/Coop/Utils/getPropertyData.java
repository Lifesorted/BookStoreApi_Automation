package com.Coop.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class getPropertyData {
	
	static File src=new File("./testData/data.properties");
	FileInputStream fis;
	static Properties prop;
	
	public getPropertyData() throws IOException {
			fis=new FileInputStream(src);
			prop=new Properties();
			prop.load(fis);
	}
	

	public static String getBaseUrl() {
	    String baseUrl= prop.getProperty("BASEURL");
		return baseUrl;
	}
	
	public static String getClientId() {
		String clientId=prop.getProperty("client_id");
		return clientId;
		
	}
	
	public static String getClientSecret() {
		String clientsecret=prop.getProperty("client_secret");
		return clientsecret;
	}
	
	public static String getGrantTypeString() {
		String granttype=prop.getProperty("grant_type");
		return granttype;
	}
	
	public static void getKeys() {
		Enumeration<Object> e=prop.keys();
		while (e.hasMoreElements()) {
			String keyString = (String) e.nextElement();
			System.out.println(keyString);
		}
		
	}
	public static void main(String args[]) throws IOException{
		getPropertyData bData=new getPropertyData();
		bData.getKeys();
		try {
		     System.out.println(getPropertyData.getBaseUrl());
		} catch (Exception e) {
			 e.printStackTrace();
		}
			
	}
}
