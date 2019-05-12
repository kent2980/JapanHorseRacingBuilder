package com.racing.model;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.pckeiba.entity.JvdChokyoshiMaster;
import com.pckeiba.entity.JvdChokyoshiMasterExample;
import com.pckeiba.entity.JvdChokyoshiMasterMapper;

public class ChokyoshiMaster implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdChokyoshiMaster> list;

	public ChokyoshiMaster(SqlSession session, List<String> ChokyoshiCode) {
		// MAPPER
		JvdChokyoshiMasterMapper mapper = session.getMapper(JvdChokyoshiMasterMapper.class);
		// EXAMPLE
		JvdChokyoshiMasterExample example = new JvdChokyoshiMasterExample();
		//WHERE{
			example.createCriteria().andChokyoshiCodeIn(ChokyoshiCode);
			setList(mapper.selectByExample(example));
	}

	public List<JvdChokyoshiMaster> getList() {
		return list;
	}

	private void setList(List<JvdChokyoshiMaster> list) {
		this.list = list;
	}

}
