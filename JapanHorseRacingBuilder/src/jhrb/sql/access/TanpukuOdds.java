package jhrb.sql.access;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.pckeiba.entity.JvdTanpukuOdds;
import com.pckeiba.entity.JvdTanpukuOddsExample;
import com.pckeiba.entity.JvdTanpukuOddsMapper;

import jhrb.datainterface.DataInterface;
import jhrb.sql.session.PckeibaSession;
import jhrb.sql.session.PckeibaSqlSessionFactory;

public class TanpukuOdds extends PckeibaSession<JvdTanpukuOdds> implements DataInterface<JvdTanpukuOdds> {

	public TanpukuOdds(String raceCode) {
		super();
		this.raceCode = raceCode;
		addDataResouce();
	}

	private String raceCode;

	@Override
	public void addDataResouce() {
		JvdTanpukuOddsMapper mapper = session.getMapper(JvdTanpukuOddsMapper.class);
		JvdTanpukuOddsExample example = new JvdTanpukuOddsExample();
		example.createCriteria().andRaceCodeEqualTo(raceCode);
		list = mapper.selectByExample(example);
	}

	@Override
	public List<JvdTanpukuOdds> getList() {
		return list;
	}

	public static JvdTanpukuOdds getTanpukuOdds(String raceCode, String umaban) {
		SqlSession session = PckeibaSqlSessionFactory.openSession();
		JvdTanpukuOddsMapper mapper = session.getMapper(JvdTanpukuOddsMapper.class);
		JvdTanpukuOddsExample example = new JvdTanpukuOddsExample();
		example.createCriteria().andRaceCodeEqualTo(raceCode).andUmabanEqualTo(umaban);
		return mapper.selectByExample(example).get(0);
	}
}
