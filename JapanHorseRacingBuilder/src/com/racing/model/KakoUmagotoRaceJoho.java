package com.racing.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.example.entity.UmaDataView;
import com.example.entity.UmaDataViewExample;
import com.example.entity.UmaDataViewMapper;

public class KakoUmagotoRaceJoho implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<UmaDataView> list;

	public KakoUmagotoRaceJoho(SqlSession session, String raceCode,List<String> kettoTorokuBango) {
		// MAPPER
		UmaDataViewMapper mapper = session.getMapper(UmaDataViewMapper.class);
		// EXAMPLE
		UmaDataViewExample example = new UmaDataViewExample();
		//WHERE
			example.createCriteria().andKettoTorokuBangoIn(kettoTorokuBango)
											.andUmabanNotEqualTo("00")
											.andDataKubunNotEqualTo("9")
											.andRaceCodeLessThan(raceCode);
			setList(mapper.selectByExample(example));
	}

	public List<UmaDataView> getList() {
		return list.stream()
				   .distinct()
				   .collect(Collectors.toList());
	}

	private void setList(List<UmaDataView> list) {
		this.list = list;
	}

}
