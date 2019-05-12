package com.racing.model;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.pckeiba.entity.JvdKyosobaMaster;
import com.pckeiba.entity.JvdKyosobaMasterExample;
import com.pckeiba.entity.JvdKyosobaMasterMapper;

public class KyosobaMaster implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdKyosobaMaster> list;

	public KyosobaMaster(SqlSession session, List<String> kettoTorokuBango) {
		// MAPPER
		JvdKyosobaMasterMapper mapper = session.getMapper(JvdKyosobaMasterMapper.class);
		// EXAMPLE
		JvdKyosobaMasterExample example = new JvdKyosobaMasterExample();
		//WHERE
			example.createCriteria().andKettoTorokuBangoIn(kettoTorokuBango);
			setList(mapper.selectByExample(example));
	}

	public List<JvdKyosobaMaster> getList() {
		return list;
	}

	private void setList(List<JvdKyosobaMaster> list) {
		this.list = list;
	}

}
