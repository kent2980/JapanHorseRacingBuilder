package com.racing.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.aql.access.JvdUmagotoRaceJohoSession;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pckeiba.datamodel.HorseData;
import com.pckeiba.entity.JvdUmagotoRaceJoho;

public class UmagotoRaceJoho implements Serializable,Horse{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<HorseData> list;
	private List<String> kishuList;
	private List<String> kettotorokubango;
	private List<String> chokyoshiList;
	private List<String> banushiList;

	public UmagotoRaceJoho(String raceCode) {
		try(JvdUmagotoRaceJohoSession rs = new JvdUmagotoRaceJohoSession();){
			rs.getExample().createCriteria().andRaceCodeEqualTo(raceCode);
			rs.getExample().setOrderByClause("ijo_kubun_code asc, kakutei_chakujun asc, umaban asc, bamei asc");
			List<JvdUmagotoRaceJoho> umagoto = rs.getMapper().selectByExample(rs.getExample());
			setList(Lists.transform(umagoto, new Function<JvdUmagotoRaceJoho,HorseData>(){
				@Override
				public HorseData apply(JvdUmagotoRaceJoho arg0) {
					return (HorseData)arg0;
				}
			}));
			setKettotorokubango(umagoto.stream()
									.map(s -> s.getKettoTorokuBango())
									.collect(Collectors.toList()));
			setKishuList(umagoto.stream()
								.map(s -> s.getKishuCode())
								.collect(Collectors.toList()));
			setChokyoshiList(umagoto.stream()
					.map(s -> s.getChokyoshiCode())
					.collect(Collectors.toList()));
			setBanushiList(umagoto.stream()
					.map(s -> s.getBanushiCode())
					.collect(Collectors.toList()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getKishuList() {
		return kishuList;
	}

	private void setKishuList(List<String> kishuList) {
		this.kishuList = kishuList;
	}

	@Override
	public List<HorseData> getList() {
		return list;
	}

	@Override
	public List<String> getKettotorokubango() {
		return kettotorokubango;
	}

	@Override
	public List<String> getChokyoshiList() {
		return chokyoshiList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getBanushiList() {
		return banushiList;
	}

	private void setList(List<HorseData> list) {
		this.list = list;
	}

	private void setKettotorokubango(List<String> kettotorokubango) {
		this.kettotorokubango = kettotorokubango;
	}

	private void setChokyoshiList(List<String> chokyoshiList) {
		this.chokyoshiList = chokyoshiList;
	}

	private void setBanushiList(List<String> banushiList) {
		this.banushiList = banushiList;
	}

}
