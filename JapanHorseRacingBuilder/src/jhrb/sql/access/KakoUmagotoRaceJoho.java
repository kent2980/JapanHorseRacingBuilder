package jhrb.sql.access;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.example.entity.UmaDataView;
import com.example.entity.UmaDataViewExample;
import com.example.entity.UmaDataViewMapper;

import jhrb.datainterface.DataInterface;
import jhrb.sql.session.PckeibaLinkSession;
import jhrb.sql.session.PckeibalinkSqlSessionFactory;

public class KakoUmagotoRaceJoho extends PckeibaLinkSession implements Serializable,DataInterface<UmaDataView>{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<UmaDataView> list;
	private String raceCode;
	private List<String> kettoTorokuBango;

	public KakoUmagotoRaceJoho(String raceCode,List<String> kettoTorokuBango) {
		this.raceCode = raceCode;
		this.kettoTorokuBango = kettoTorokuBango;
		addDataResouce();
	}

	public void setRaceJoho(String raceCode,List<String> kettoTorokuBango) {
		this.raceCode = raceCode;
		this.kettoTorokuBango = kettoTorokuBango;
		addDataResouce();
	}

	public List<UmaDataView> getList() {
		return list.stream()
				   .distinct()
				   .collect(Collectors.toList());
	}

	@Override
	public void addDataResouce() {
		// MAPPER
		UmaDataViewMapper mapper = session.getMapper(UmaDataViewMapper.class);
		// EXAMPLE
		UmaDataViewExample example = new UmaDataViewExample();
		//WHERE
			example.createCriteria().andKettoTorokuBangoIn(kettoTorokuBango)
											.andUmabanNotEqualTo("00")
											.andDataKubunNotEqualTo("9")
											.andRaceCodeLessThan(raceCode);
		list = mapper.selectByExample(example);
	}

	public static List<UmaDataView> getUmaKakoData(String kettoTorokuBango) {
		SqlSession session = PckeibalinkSqlSessionFactory.openSession();
		UmaDataViewMapper mapper = session.getMapper(UmaDataViewMapper.class);
		UmaDataViewExample example = new UmaDataViewExample();
		example.createCriteria().andKettoTorokuBangoEqualTo(kettoTorokuBango);
		return mapper.selectByExample(example);
	}

}
