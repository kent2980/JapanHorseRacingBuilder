package com.racing.model.pckeiba;

import java.util.Date;
import java.util.List;

import com.database.access.PckeibaSession;
import com.pckeiba.entity.JvdTokubetsuTorokuba;
import com.pckeiba.entity.JvdTokubetsuTorokubaExample;
import com.pckeiba.entity.JvdTokubetsuTorokubaMapper;
import com.racing.model.DataInterface;

public class TokubetsuTorokuba extends PckeibaSession implements DataInterface<JvdTokubetsuTorokuba>{

	public TokubetsuTorokuba(String raceCode) {
		super();
		this.raceCode = raceCode;
		addDataResouce();
	}

	public TokubetsuTorokuba() {
		super();
		addDataResouce();
	}
	private List<JvdTokubetsuTorokuba> list;
	private String raceCode;

	@Override
	public void addDataResouce() {
		// MAPPER
		JvdTokubetsuTorokubaMapper mapper = session.getMapper(JvdTokubetsuTorokubaMapper.class);
		// EXAMPLE
		JvdTokubetsuTorokubaExample example = new JvdTokubetsuTorokubaExample();
		//WHERE
			example.createCriteria().andKaisaiNengappiGreaterThan(new Date());
			example.setOrderByClause("kaisai_nengappi asc,keibajo_code asc");
			list = mapper.selectByExample(example);
	}

	@Override
	public List<JvdTokubetsuTorokuba> getList() {
		return list;
	}
	public JvdTokubetsuTorokuba getTokubetsuTorokuba() {
		return list.stream()
				   .filter(s -> s.getRaceCode().equals(raceCode))
				   .findFirst().get();
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

}
