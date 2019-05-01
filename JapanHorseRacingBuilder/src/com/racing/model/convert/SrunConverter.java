package com.racing.model.convert;

import java.math.BigDecimal;

public class SrunConverter {
	public SrunConverter(BigDecimal srun, BigDecimal futanJuryo) {
		super();
		this.srun = srun;
		try {
			BigDecimal hosei = BigDecimal.valueOf(55).subtract(futanJuryo).multiply(BigDecimal.valueOf(0.3)).setScale(3,BigDecimal.ROUND_HALF_UP);
			BigDecimal hoseiSrun = srun.add(hosei);
			setHoseiSrun(hoseiSrun);
		}catch(NullPointerException e) {
			setHoseiSrun(srun);
		}
		try {
			BigDecimal convertSrun = hoseiSrun.add(BigDecimal.valueOf(12)).multiply(BigDecimal.valueOf(4.5)).setScale(2,BigDecimal.ROUND_HALF_UP);;
			setConvertSrun(convertSrun);
		}catch(NullPointerException e) {
			setConvertSrun(null);
		}
	}
	private BigDecimal srun;
	private BigDecimal convertSrun;
	private BigDecimal hoseiSrun;
	private BigDecimal futanJuryo;
	public BigDecimal getSrun() {
		return srun;
	}
	public BigDecimal getConvertSrun() {
		return convertSrun;
	}
	private void setConvertSrun(BigDecimal convertSrun) {
		this.convertSrun = convertSrun;
	}
	public BigDecimal getHoseiSrun() {
		return hoseiSrun;
	}
	public BigDecimal getFutanJuryo() {
		return futanJuryo;
	}
	private void setHoseiSrun(BigDecimal hoseiSrun) {
		this.hoseiSrun = hoseiSrun;
	}

}
