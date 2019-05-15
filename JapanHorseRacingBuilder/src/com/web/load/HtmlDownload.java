package com.web.load;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class HtmlDownload {
	public static void main(String[] args) {
		try {
			//ダウンロードする対象のURLと文字コード
			String url = "https://db.netkeiba.com/horse/2016104880/";
			String charset = "JISAutoDetect";

			//HTMLを取得
			HtmlDownload downloader = new HtmlDownload();
			List<String> contents = downloader.read(url, charset);

			//取得したHTMLを出力
			for(String str : contents) {
				if(str.contains("id=\"HorseMainPhoto\""))
				System.out.println(str);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> read(String url,String charset) throws Exception {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			URLConnection conn = new URL(url).openConnection();
			is = conn.getInputStream();
			isr = new InputStreamReader(is,charset);
			br = new BufferedReader(isr);

			ArrayList<String> lineList = new ArrayList<String>();
			String line = null;
			while((line = br.readLine()) != null) {
				lineList.add(line);
			}
			return lineList;
		}finally {
			try {
				br.close();
			}catch(Exception e) {
			}
			try {
				isr.close();
			}catch(Exception e) {
			}
			try {
				is.close();
			}catch(Exception e) {
			}
		}
	}
}