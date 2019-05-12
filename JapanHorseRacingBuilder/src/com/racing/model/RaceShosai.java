package com.racing.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pckeiba.datamodel.RaceData;
import com.pckeiba.entity.JvdRaceShosai;
import com.pckeiba.entity.JvdRaceShosaiExample;
import com.pckeiba.entity.JvdRaceShosaiMapper;

public class RaceShosai extends Race {

	public RaceShosai(SqlSession session, String raceCode) throws IOException {
		// MAPPER
		JvdRaceShosaiMapper mapper = session.getMapper(JvdRaceShosaiMapper.class);
		// EXAMPLE
		JvdRaceShosaiExample example = new JvdRaceShosaiExample();
		// WEHRE
		example.createCriteria().andRaceCodeEqualTo(raceCode);
		List<JvdRaceShosai> torokuba = mapper.selectByExample(example);
		// SELECT
		setList(Lists.transform(torokuba, new Function<JvdRaceShosai, RaceData>() {
			@Override
			public RaceData apply(JvdRaceShosai arg0) {
				return (RaceData) arg0;
			}
		}));
	}

	public RaceShosai(SqlSession session, Date date) {
		// MAPPER
		JvdRaceShosaiMapper mapper = session.getMapper(JvdRaceShosaiMapper.class);
		// EXAMPLE
		JvdRaceShosaiExample example = new JvdRaceShosaiExample();
		//競馬場コードのリスト
		String[] array = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" };
		List<String> keibajoCode = Arrays.asList(array);
		//WHERE
		example.createCriteria().andKaisaiNengappiEqualTo(date).andKeibajoCodeIn(keibajoCode);
		//ORDER
		example.setOrderByClause("keibajo_code asc,Hasso_Jikoku asc");
		//SELECT
		List<JvdRaceShosai> torokuba = mapper.selectByExample(example);
		setList(Lists.transform(torokuba, new Function<JvdRaceShosai, RaceData>() {
			@Override
			public RaceData apply(JvdRaceShosai arg0) {
				return (RaceData) arg0;
			}
		}));
	}

}
