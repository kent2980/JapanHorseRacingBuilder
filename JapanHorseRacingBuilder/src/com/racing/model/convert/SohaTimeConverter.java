package com.racing.model.convert;

import java.math.BigDecimal;

public class SohaTimeConverter {
	public SohaTimeConverter(BigDecimal sohaTIme) {
		super();
		this.sohaTIme = sohaTIme;
		setSohaTimeHour(sohaTIme);
	}

	private BigDecimal sohaTIme;
	private String sohaTimeHour;

	public BigDecimal getSohaTIme() {
		return sohaTIme;
	}

	public void setSohaTIme(BigDecimal sohaTIme) {
		this.sohaTIme = sohaTIme;
		setSohaTimeHour(sohaTIme);
	}

	public String getSohaTimeHour() {
		return sohaTimeHour;
	}

	private void setSohaTimeHour(BigDecimal sohaTime) {
		if(sohaTime.compareTo(BigDecimal.ZERO) == 0) {
			this.sohaTimeHour = "未計測";
		}else {
			BigDecimal hour = sohaTime.divide(BigDecimal.valueOf(60), 0, BigDecimal.ROUND_DOWN);
			BigDecimal minutes = sohaTime.subtract((hour.multiply(BigDecimal.valueOf(60))));
			this.sohaTimeHour = hour + ":" + String.format("%04.1f", minutes.doubleValue());
		}
	}

}
