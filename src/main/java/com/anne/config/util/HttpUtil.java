package com.anne.config.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class HttpUtil {

	private final int _connectionTimeoutMs = 3000;
	
	private HttpUtil() {}
	
	private static final class HttpUtilHolder {
		public static final HttpUtil instance = new HttpUtil();
	}
	
	public static HttpUtil getInstance() {
		return HttpUtilHolder.instance;
	}

	public String sendPost(String _url, Map<String, Object> postArgs) throws IOException, MalformedURLException {
		System.out.println(_url+"=============================================");
		HttpURLConnection conn = getPostConnection(_url);
		conn.setRequestProperty("Authorization", "KakaoAK fd035a4d4a8bcdb64a072b024260091f");

		String encodedPostArgs = encodeURLParameters(postArgs);
		if (encodedPostArgs != "") {
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			dos.writeBytes(encodedPostArgs);
			dos.flush();
			dos.close();
		}

		String response = "";
		try {
			response = read(conn.getInputStream());
		} catch (Exception e) {
			response = read(conn.getErrorStream());
		} finally {
			conn.disconnect();
		}

		return response;
	}

	public String sendPost(String _url, String _data) throws IOException, MalformedURLException {
		HttpURLConnection conn = getPostConnectionJson(_url);

		if (_data != null && _data.length() > 0) {
			OutputStreamWriter dos = new OutputStreamWriter(conn.getOutputStream());
			dos.write(_data);
			dos.flush();
			dos.close();
		}

		String response = "";
		try {
			response = read(conn.getInputStream());
		} catch (Exception e) {
			response = read(conn.getErrorStream());
		} finally {
			conn.disconnect();
		}

		return response;
	}

	public String sendGet(String _url, Map<String, Object> postArgs) throws IOException, MalformedURLException {
		HttpURLConnection conn = null;

		String strUrl = (postArgs != null ? _url + "?" + encodeURLParameters(postArgs) : _url);

		URL url = new URL(strUrl);
		conn = (HttpURLConnection) url.openConnection();

		String response = "";
		try {
			response = read(conn.getInputStream());
		} catch (Exception e) {
			response = read(conn.getErrorStream());
		} finally {
			conn.disconnect();
		}

		return response;
	}

	private HttpURLConnection getPostConnection(String _url) throws IOException, MalformedURLException {
		URL url = new URL(_url);
		if (url.getProtocol().toLowerCase().equals("https")) {
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept", "application/json");
			connection.setReadTimeout(_connectionTimeoutMs);
			connection.setConnectTimeout(_connectionTimeoutMs);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			return connection;
		}

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("Accept", "application/json");
		connection.setReadTimeout(_connectionTimeoutMs);
		connection.setConnectTimeout(_connectionTimeoutMs);
		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);

		return connection;
	}

	private HttpURLConnection getPostConnectionJson(String _url) throws IOException, MalformedURLException {
		URL url = new URL(_url);
		if (url.getProtocol().toLowerCase().equals("https")) {
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setReadTimeout(_connectionTimeoutMs);
			connection.setConnectTimeout(_connectionTimeoutMs);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			return connection;
		}

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		connection.setReadTimeout(_connectionTimeoutMs);
		connection.setConnectTimeout(_connectionTimeoutMs);
		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);

		return connection;
	}

	private String read(InputStream in) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"), 1000);
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			sb.append(line);
		}
		if (in != null)
			in.close();
		if (br != null)
			br.close();
		return sb.toString();
	}

	public String encodeURLParameters(Map<String, Object> params) throws UnsupportedEncodingException {
		if (params == null || params.size() < 1)
			return "";

		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (String key : params.keySet()) {
			if (!isFirst)
				sb.append('&');
			else
				isFirst = false;
			sb.append(RFC3986Encoder.encode(key));
			sb.append('=');
			sb.append(RFC3986Encoder.encode(params.get(key).toString()));
		}
		return sb.toString();
	}

}
