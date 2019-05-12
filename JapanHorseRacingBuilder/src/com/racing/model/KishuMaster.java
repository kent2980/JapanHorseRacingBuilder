package com.racing.model;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.pckeiba.entity.JvdKishuMaster;
import com.pckeiba.entity.JvdKishuMasterExample;
import com.pckeiba.entity.JvdKishuMasterMapper;

public class KishuMaster implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdKishuMaster> list;

	public KishuMaster(SqlSession session, List<String> kishuCode) {
		// MAPPER
		JvdKishuMasterMapper mapper = session.getMapper(JvdKishuMasterMapper.class);
		// EXAMPLE
		JvdKishuMasterExample example = new JvdKishuMasterExample();
		//WHERE{
			example.createCriteria().andKishuCodeIn(kishuCode);
			setList(mapper.selectByExample(example));
	}

	public List<JvdKishuMaster> getList() {
		return list;
	}

	private void setList(List<JvdKishuMaster> list) {
		this.list = list;
	}

}
