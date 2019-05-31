package jhrb.sql.access;

import org.apache.ibatis.session.SqlSession;

import com.pckeiba.entity.JvdSeisanshaMaster;
import com.pckeiba.entity.JvdSeisanshaMasterExample;
import com.pckeiba.entity.JvdSeisanshaMasterMapper;

import jhrb.sql.session.PckeibaSqlSessionFactory;

public class SeisanshaMaster {

	public static JvdSeisanshaMaster getSeisanshaMaster(String seisanshaCode) {
		SqlSession session = PckeibaSqlSessionFactory.openSession();
		JvdSeisanshaMasterMapper mapper = session.getMapper(JvdSeisanshaMasterMapper.class);
		JvdSeisanshaMasterExample example = new JvdSeisanshaMasterExample();
		example.createCriteria().andSeisanshaCodeEqualTo(seisanshaCode);
		JvdSeisanshaMaster master = mapper.selectByExample(example).get(0);
		return master;
	}

}
