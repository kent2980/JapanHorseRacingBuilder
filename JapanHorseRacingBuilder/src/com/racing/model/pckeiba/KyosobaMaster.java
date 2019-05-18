package com.racing.model.pckeiba;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.database.access.PckeibaSession;
import com.database.access.PckeibaSqlSessionFactory;
import com.pckeiba.entity.JvdKyosobaMaster;
import com.pckeiba.entity.JvdKyosobaMasterExample;
import com.pckeiba.entity.JvdKyosobaMasterMapper;
import com.racing.model.DataInterface;

public class KyosobaMaster extends PckeibaSession implements Serializable, DataInterface<JvdKyosobaMaster> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdKyosobaMaster> list;
	private List<String> kettoTorokuBango;

	public KyosobaMaster(List<String> kettoTorokuBango) {
		this.kettoTorokuBango = kettoTorokuBango;
		addDataResouce();
	}

	@Override
	public void addDataResouce() {
		// MAPPER
		JvdKyosobaMasterMapper mapper = session.getMapper(JvdKyosobaMasterMapper.class);
		// EXAMPLE
		JvdKyosobaMasterExample example = new JvdKyosobaMasterExample();
		// WHERE
		example.createCriteria().andKettoTorokuBangoIn(kettoTorokuBango);
		list = mapper.selectByExample(example);
	}

	public List<String> getKettoTorokuBango() {
		return kettoTorokuBango;
	}

	public List<JvdKyosobaMaster> getList() {
		return list;
	}

	public void setKettoTorokuBango(List<String> kettoTorokuBango) {
		this.kettoTorokuBango = kettoTorokuBango;
		addDataResouce();
	}

	public static JvdKyosobaMaster getKyosobaMaster(String kettoTorokuBango) {
		SqlSession session = PckeibaSqlSessionFactory.openSession();
		JvdKyosobaMasterMapper mapper = session.getMapper(JvdKyosobaMasterMapper.class);
		JvdKyosobaMasterExample example = new JvdKyosobaMasterExample();
		example.createCriteria().andKettoTorokuBangoEqualTo(kettoTorokuBango);
		return mapper.selectByExample(example).get(0);
	}

}
