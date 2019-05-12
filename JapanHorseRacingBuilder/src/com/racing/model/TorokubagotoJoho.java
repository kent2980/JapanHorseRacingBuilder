package com.racing.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pckeiba.datamodel.HorseData;
import com.pckeiba.entity.JvdTorokubagotoJoho;
import com.pckeiba.entity.JvdTorokubagotoJohoExample;
import com.pckeiba.entity.JvdTorokubagotoJohoMapper;

public class TorokubagotoJoho implements Serializable,Horse{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<HorseData> list;
	private List<String> kettotorokubango;
	private List<String> chokyoshiList;

	public TorokubagotoJoho(SqlSession session, String raceCode) {
		// MAPPER
		JvdTorokubagotoJohoMapper mapper = session.getMapper(JvdTorokubagotoJohoMapper.class);
		// EXAMPLE
		JvdTorokubagotoJohoExample example = new JvdTorokubagotoJohoExample();
		//WHERE
		example.createCriteria().andRaceCodeEqualTo(raceCode);
			List<JvdTorokubagotoJoho> torokuba = mapper.selectByExample(example);
			setList(Lists.transform(torokuba, new Function<JvdTorokubagotoJoho,HorseData>(){
				@Override
				public HorseData apply(JvdTorokubagotoJoho arg0) {
					return (HorseData)arg0;
				}
			}));
			setKettotorokubango(torokuba.stream()
									.map(s -> s.getKettoTorokuBango())
									.collect(Collectors.toList()));
			setChokyoshiList(torokuba.stream()
									 .map(s -> s.getChokyoshiCode())
									 .collect(Collectors.toList()));
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

	private void setList(List<HorseData> list) {
		this.list = list;
	}

	private void setKettotorokubango(List<String> kettotorokubango) {
		this.kettotorokubango = kettotorokubango;
	}

	private void setChokyoshiList(List<String> chokyoshiList) {
		this.chokyoshiList = chokyoshiList;
	}

}