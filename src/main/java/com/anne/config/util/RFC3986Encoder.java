package com.anne.config.util;

import java.net.URLEncoder;

public class RFC3986Encoder {
	public static String encode(String arg0) {
		String tmp = null;
		try {
			tmp = URLEncoder.encode(arg0, "utf-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
		} catch (Exception e) {

		}
		return tmp;
	}
}
