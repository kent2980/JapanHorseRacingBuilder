package com.racing.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.aql.access.UmaDataViewSession;
import com.example.entity.UmaDataView;

public class KakoUmagotoRaceJoho implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<UmaDataView> list;

	public KakoUmagotoRaceJoho(String raceCode,List<String> kettoTorokuBango) {
		try(UmaDataViewSession rs = new UmaDataViewSession();){
			rs.getExample().createCriteria().andKettoTorokuBangoIn(kettoTorokuBango)
											.andRaceCodeLessThan(raceCode);
			setList(rs.getMapper().selectByExample(rs.getExample()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UmaDataView> getList() {
		return list;
	}

	private void setList(List<UmaDataView> list) {
		this.list = list;
	}

}
