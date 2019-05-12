package com.racing.model;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pckeiba.datamodel.RaceData;
import com.pckeiba.entity.JvdTokubetsuTorokuba;
import com.pckeiba.entity.JvdTokubetsuTorokubaExample;
import com.pckeiba.entity.JvdTokubetsuTorokubaMapper;

public class TokubetsuTorokuba extends Race{

	public TokubetsuTorokuba(SqlSession session, String raceCode) {
		// MAPPER
		JvdTokubetsuTorokubaMapper mapper = session.getMapper(JvdTokubetsuTorokubaMapper.class);
		// EXAMPLE
		JvdTokubetsuTorokubaExample example = new JvdTokubetsuTorokubaExample();
		//WHERE
			example.createCriteria().andRaceCodeEqualTo(raceCode);
			//SELECT
			List<JvdTokubetsuTorokuba> torokuba = mapper.selectByExample(example);
			setList(Lists.transform(torokuba, new Function<JvdTokubetsuTorokuba,RaceData>(){
				@Override
				public RaceData apply(JvdTokubetsuTorokuba arg0) {
					return (RaceData)arg0;
				}
			}));
	}

	public TokubetsuTorokuba(SqlSession session) {
		// MAPPER
		JvdTokubetsuTorokubaMapper mapper = session.getMapper(JvdTokubetsuTorokubaMapper.class);
		// EXAMPLE
		JvdTokubetsuTorokubaExample example = new JvdTokubetsuTorokubaExample();
		//WHERE
			example.createCriteria().andKaisaiNengappiGreaterThan(new Date());
			example.setOrderByClause("kaisai_nengappi asc,keibajo_code asc");
			List<JvdTokubetsuTorokuba> torokuba = mapper.selectByExample(example);
			setList(Lists.transform(torokuba, new Function<JvdTokubetsuTorokuba,RaceData>(){
				@Override
				public RaceData apply(JvdTokubetsuTorokuba arg0) {
					return (RaceData)arg0;
				}
			}));
	}

}
