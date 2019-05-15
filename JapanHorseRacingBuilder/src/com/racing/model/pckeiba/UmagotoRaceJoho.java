package com.racing.model.pckeiba;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.database.access.PckeibaSession;
import com.pckeiba.entity.JvdUmagotoRaceJoho;
import com.pckeiba.entity.JvdUmagotoRaceJohoExample;
import com.pckeiba.entity.JvdUmagotoRaceJohoMapper;
import com.racing.model.DataInterface;

public class UmagotoRaceJoho extends PckeibaSession implements Serializable,DataInterface<JvdUmagotoRaceJoho>{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdUmagotoRaceJoho> list;
	private List<String> kishuList;
	private List<String> kettotorokubango;
	private List<String> chokyoshiList;
	private List<String> banushiList;
	private String raceCode;

	public UmagotoRaceJoho(String raceCode) {
		this.raceCode = raceCode;
		addDataResouce();
	}

	public List<String> getKishuList() {
		return kishuList;
	}

	private void setKishuList() {
		this.kishuList = list.stream()
				.map(s -> s.getKishuCode())
				.collect(Collectors.toList());
	}

	@Override
	public List<JvdUmagotoRaceJoho> getList() {
		return list;
	}

	public List<String> getKettotorokubango() {
		return kettotorokubango;
	}

	public List<String> getChokyoshiList() {
		return chokyoshiList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getBanushiList() {
		return banushiList;
	}

	private void setKettotorokubango() {
		this.kettotorokubango = list.stream()
				.map(s -> s.getKettoTorokuBango())
				.collect(Collectors.toList());
	}

	private void setChokyoshiList() {
		this.chokyoshiList = list.stream()
				.map(s -> s.getChokyoshiCode())
				.collect(Collectors.toList());
	}

	private void setBanushiList() {
		this.banushiList = list.stream()
				.map(s -> s.getBanushiCode())
				.collect(Collectors.toList());
	}

	@Override
	public void addDataResouce() {
		// MAPPER
		JvdUmagotoRaceJohoMapper mapper = session.getMapper(JvdUmagotoRaceJohoMapper.class);
		// EXAMPLE
		JvdUmagotoRaceJohoExample example = new JvdUmagotoRaceJohoExample();
		//WHERE
			example.createCriteria().andRaceCodeEqualTo(raceCode);
			example.setOrderByClause("ijo_kubun_code asc, kakutei_chakujun asc, umaban asc, bamei asc");
			list = mapper.selectByExample(example);
			setKettotorokubango();
			setKishuList();
			setChokyoshiList();
			setBanushiList();
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
		addDataResouce();
	}

}
