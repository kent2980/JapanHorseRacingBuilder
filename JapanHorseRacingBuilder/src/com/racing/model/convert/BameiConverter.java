package com.racing.model.convert;

public class BameiConverter {
	private String bamei;
	private String bameiConvert;

	public BameiConverter(String bamei) {
		this.bamei = bamei;
		setBameiConvert(bamei);
	}

	public String getBamei() {
		return bamei;
	}

	public String getBameiConvert() {
		return bameiConvert;
	}

	public void setBamei(String bamei) {
		this.bamei = bamei;
		setBameiConvert(bamei);
	}

	private void setBameiConvert(String bamei) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 9 - bamei.length(); i++) {
			sb.append("　");
		}
		String bameiConvert = bamei + sb.toString();
		this.bameiConvert = bameiConvert;
	}


}
