package com.racing.model.convert;

import java.math.BigDecimal;

import com.pckeiba.enumutil.CodeConvert;
import com.pckeiba.enumutil.KyosoJokenCode;
import com.pckeiba.enumutil.KyosoShubetsuCode;

public class PckeibaConvert {

	private PckeibaConvert() {

	}

	static public String NameConvert(String name, int n) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n - name.length(); i++) {
			sb.append("　");
		}
		String convertKey = name + sb.toString();
		return convertKey;
	}

	static public String KyosomeiConvert(String kyosomei, String kyosoShubetsuCode, String kyosoJokenCode) {
		String convertKyosomei = kyosomei.length() > 0? kyosomei :
			CodeConvert.valueOf(KyosoShubetsuCode.class, kyosoShubetsuCode).getContent() + CodeConvert.valueOf(KyosoJokenCode.class, kyosoJokenCode).getContent();
		return convertKyosomei;
	}

	static public String SohaTimeHour(BigDecimal sohaTime) throws NullPointerException{
		if(sohaTime.compareTo(BigDecimal.ZERO) == 0) {
			return "未計測";
		}else {
			BigDecimal hour = sohaTime.divide(BigDecimal.valueOf(60), 0, BigDecimal.ROUND_DOWN);
			BigDecimal minutes = sohaTime.subtract((hour.multiply(BigDecimal.valueOf(60))));
			return hour + ":" + String.format("%04.1f", minutes.doubleValue());
		}
	}

	static public BigDecimal ConvertSrun(BigDecimal srun, BigDecimal futanJuryo){
		if(futanJuryo.equals(BigDecimal.ZERO.setScale(1))) {
			futanJuryo = BigDecimal.valueOf(55);
		}
		if(srun == null) {
			return null;
		}
		BigDecimal hosei = BigDecimal.valueOf(55).subtract(futanJuryo).multiply(BigDecimal.valueOf(0.3)).setScale(3,BigDecimal.ROUND_HALF_UP);
		BigDecimal hoseiSrun = srun.add(hosei);
		return hoseiSrun.add(BigDecimal.valueOf(12)).multiply(BigDecimal.valueOf(4.5)).setScale(2,BigDecimal.ROUND_HALF_UP);
	}
}
