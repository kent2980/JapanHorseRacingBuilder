package com.racing.model.convert;

import com.pckeiba.enumutil.CodeConvert;
import com.pckeiba.enumutil.KyosoJokenCode;
import com.pckeiba.enumutil.KyosoShubetsuCode;

/**
 * 競争名を生成するクラスです。
 * コンストラクタの引数に１．競争名、２．競争種別コード、３．競争条件コードを指定します。
 * @author kent2
 *
 */
public class KyosomeiConverter {

	/**
	 * コンストラクタ
	 * @param kyosomei 競争名
	 * @param kyosoShubetsuCode	競争種別コード
	 * @param kyosoJokenCode 競争条件コード
	 */
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
