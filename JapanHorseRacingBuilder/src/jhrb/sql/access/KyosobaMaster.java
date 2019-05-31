package jhrb.sql.access;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.pckeiba.entity.JvdKyosobaMaster;
import com.pckeiba.entity.JvdKyosobaMasterExample;
import com.pckeiba.entity.JvdKyosobaMasterMapper;

import jhrb.datainterface.DataInterface;
import jhrb.sql.session.PckeibaSession;
import jhrb.sql.session.PckeibaSqlSessionFactory;

public class KyosobaMaster extends PckeibaSession<JvdKyosobaMaster> implements Serializable, DataInterface<JvdKyosobaMaster> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
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
