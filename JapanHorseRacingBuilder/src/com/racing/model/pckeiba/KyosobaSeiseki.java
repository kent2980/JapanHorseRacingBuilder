package com.racing.model.pckeiba;

import org.apache.ibatis.session.SqlSession;

import com.database.access.PckeibaSqlSessionFactory;
import com.pckeiba.entity.JvdKyosobaSeiseki;
import com.pckeiba.entity.JvdKyosobaSeisekiExample;
import com.pckeiba.entity.JvdKyosobaSeisekiMapper;

public class KyosobaSeiseki {
	public static JvdKyosobaSeiseki getKyosobaSeiseki(String kettoTorokuBango) {
		SqlSession session = PckeibaSqlSessionFactory.openSession();
		JvdKyosobaSeisekiMapper mapper = session.getMapper(JvdKyosobaSeisekiMapper.class);
		JvdKyosobaSeisekiExample example = new JvdKyosobaSeisekiExample();
		example.createCriteria().andKettoTorokuBangoEqualTo(kettoTorokuBango);
		return mapper.selectByExample(example).get(0);
	}
}
