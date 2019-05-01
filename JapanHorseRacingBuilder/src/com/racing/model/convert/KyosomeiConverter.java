package com.racing.model.convert;

import com.pckeiba.enumutil.CodeConvert;
import com.pckeiba.enumutil.KyosoJokenCode;
import com.pckeiba.enumutil.KyosoShubetsuCode;

public class KyosomeiConverter {

	public KyosomeiConverter(String kyosomei, String kyosoShubetsuCode, String kyosoJokenCode) {
		super();
		this.kyosomei = kyosomei;
		this.kyosoShubetsuCode = kyosoShubetsuCode;
		this.kyosoJokenCode = kyosoJokenCode;
		String convertKyosomei = kyosomei.length() > 0? kyosomei :
			CodeConvert.valueOf(KyosoShubetsuCode.class, kyosoShubetsuCode).getContent() + CodeConvert.valueOf(KyosoJokenCode.class, kyosoJokenCode).getContent();
		setConvertKyosomei(convertKyosomei);
	}
	private String kyosomei;
	private String kyosoShubetsuCode;
	private String kyosoJokenCode;
	private String convertKyosomei;

	public String getKyosomei() {
		return kyosomei;
	}
	public String getKyosoShubetsuCode() {
		return kyosoShubetsuCode;
	}
	public String getKyosoJokenCode() {
		return kyosoJokenCode;
	}
	public String getConvertKyosomei() {
		return convertKyosomei;
	}
	private void setConvertKyosomei(String convertKyosomei) {
		this.convertKyosomei = convertKyosomei;
	}

}
