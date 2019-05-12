package com.racing.model;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.pckeiba.entity.JvdBanushiMaster;
import com.pckeiba.entity.JvdBanushiMasterExample;
import com.pckeiba.entity.JvdBanushiMasterMapper;

public class BanushiMaster implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdBanushiMaster> list;

	public BanushiMaster(SqlSession session, List<String> BanushiCode) {
		// MAPPER
		JvdBanushiMasterMapper mapper = session.getMapper(JvdBanushiMasterMapper.class);
		// EXAMPLE
		JvdBanushiMasterExample example = new JvdBanushiMasterExample();
		//WHERE
			example.createCriteria().andBanushiCodeIn(BanushiCode);
			setList(mapper.selectByExample(example));
	}

	public List<JvdBanushiMaster> getList() {
		return list;
	}

	private void setList(List<JvdBanushiMaster> list) {
		this.list = list;
	}

}
