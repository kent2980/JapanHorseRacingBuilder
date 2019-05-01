package com.racing.model;

import java.util.List;

import com.pckeiba.datamodel.HorseData;

public class Horse {
	protected List<HorseData> list;

	public List<HorseData> getList() {
		return list;
	}

	protected void setList(List<HorseData> list) {
		this.list = list;
	}


	protected List<String> kettotorokubango;

	public List<String> getKettotorokubango() {
		return kettotorokubango;
	}

	protected void setKettotorokubango(List<String> kettotorokubango) {
		this.kettotorokubango = kettotorokubango;
	}
}
