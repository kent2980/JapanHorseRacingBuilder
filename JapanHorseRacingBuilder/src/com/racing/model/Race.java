package com.racing.model;

import java.util.List;

import com.database.access.PckeibaSession;
import com.pckeiba.datamodel.RaceData;

public class Race extends PckeibaSession {
	protected List<RaceData> list;

	public List<RaceData> getList() {
		return list;
	}

	protected void setList(List<RaceData> list) {
		this.list = list;
	}



}
