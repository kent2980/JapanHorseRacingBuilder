package jhrb.sql.input.access;

import java.io.Serializable;

import com.pckeiba.entity.JvdTanpukuOdds;
import com.pckeiba.entity.JvdTanpukuOddsExample;
import com.pckeiba.entity.JvdTanpukuOddsMapper;

import jhrb.sql.session.PckeibaInputSession;

public class TanpukuOdds extends PckeibaInputSession<JvdTanpukuOdds, String> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public TanpukuOdds(String raceCode) {
		super(raceCode);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	protected void addResouce(String raceCode) {
		// MAPPER
		JvdTanpukuOddsMapper mapper = session.getMapper(JvdTanpukuOddsMapper.class);
		// EXAMPLE
		JvdTanpukuOddsExample example = new JvdTanpukuOddsExample();
		// WHERE
		example.createCriteria().andRaceCodeEqualTo(raceCode);
		list = mapper.selectByExample(example);

	}

}
