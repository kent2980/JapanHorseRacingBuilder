package com.racing.model.netkeiba;

import java.io.IOException;
import java.io.Serializable;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.web.load.WebScraping;

public class UmagotoShosaiJoho implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String kettoTorokuBango;
	private String bamei;
	private String mainPhotoUrl;
	private String nextRace;

	public UmagotoShosaiJoho() {}

	public UmagotoShosaiJoho(String kettoTorokuBango) throws IOException {
		setKettoTorokuBango(kettoTorokuBango);
	}

	public String getKettoTorokuBango() {
		return kettoTorokuBango;
	}

	public String getMainPhotoUrl() {
		return mainPhotoUrl;
	}

	public void setKettoTorokuBango(String kettoTorokuBango) throws IOException {
		this.kettoTorokuBango = kettoTorokuBango;
		String url = "https://db.netkeiba.com/horse/" + kettoTorokuBango + "/";
		WebScraping ws = new WebScraping(url);
		Document document = ws.getDocument();
		setMainPhotoUrl(document);
		setBamei(document);
		setNextRace(document);
	}
	public void setMainPhotoUrl(Document document) {
		Element el = document.getElementById("HorseMainPhoto");
		this.mainPhotoUrl = el.attr("src");
	}



	public static void main(String[] args) throws IOException {
		UmagotoShosaiJoho usj = new UmagotoShosaiJoho("2016104505");
		String photoUrl = usj.getMainPhotoUrl();
		String bamei = usj.getBamei();
		String nextRace = usj.getNextRace();
		System.out.println(photoUrl);
		System.out.println(bamei);
		System.out.println(nextRace);
	}

	public String getBamei() {
		return bamei;
	}

	public void setBamei(Document document) {
		Elements el = document.select(".horse_title h1");
		this.bamei = el.text();
	}

	public String getNextRace() {
		return nextRace;
	}

	public void setNextRace(Document document) {
		Elements el = document.select("span.race_name");
		this.nextRace = el.html();
	}
}
