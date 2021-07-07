package com.miku.lab.util;

public class StringUtil {
	public static String toUpperCaseFirstOne(String name) {
		if(name!=null) {
			char first = Character.toUpperCase(name.charAt(0));
			return first+name.substring(1);
		}
		return null;
	}
}
