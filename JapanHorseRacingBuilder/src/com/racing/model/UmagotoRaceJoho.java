package com.racing.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pckeiba.datamodel.HorseData;
import com.pckeiba.entity.JvdUmagotoRaceJoho;
import com.pckeiba.entity.JvdUmagotoRaceJohoExample;
import com.pckeiba.entity.JvdUmagotoRaceJohoMapper;

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

	public UmagotoRaceJoho(SqlSession session, String raceCode) {
		// MAPPER
		JvdUmagotoRaceJohoMapper mapper = session.getMapper(JvdUmagotoRaceJohoMapper.class);
		// EXAMPLE
		JvdUmagotoRaceJohoExample example = new JvdUmagotoRaceJohoExample();
		//WHERE
			example.createCriteria().andRaceCodeEqualTo(raceCode);
			example.setOrderByClause("ijo_kubun_code asc, kakutei_chakujun asc, umaban asc, bamei asc");
			List<JvdUmagotoRaceJoho> umagoto = mapper.selectByExample(example);
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
