package jhrb.sql.access;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
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

	public List<UmaDataView> getList(String kettoTorokuBango){
		List<UmaDataView> list_ = list.stream()
									  .filter(s -> s.getKettoTorokuBango().equals(kettoTorokuBango))
									  .distinct()
									  .collect(Collectors.toList());
		return list_;
	}

	/**
	 * 過去レースにおける単勝人気の平均値を返します。
	 * 値は整数値に切り上げられます。
	 * @return 平均単勝人気
	 */
	public BigDecimal getAverageNinki(String kettoTorokuBango) {
		try {
		double aveNinki = list.stream()
							  .filter(s -> s.getKettoTorokuBango().equals(kettoTorokuBango))
						  	  .mapToInt(s -> s.getTanshoNinkijun())
						  	  .average()
						  	  .getAsDouble();
		BigDecimal aveNinkiDecimal = BigDecimal.valueOf(aveNinki).setScale(0, BigDecimal.ROUND_HALF_UP);
		return aveNinkiDecimal;
		}catch(NoSuchElementException e) {
			return null;
		}
	}

	/**
	 * 過去レースにおける着順の平均値を返します。
	 * 値は整数値に切り上げられます。
	 * @return 平均着順
	 */
	public BigDecimal getAverageKakuteiChakujun(String kettoTorokuBango) {
		try {
		double aveChakujun = list.stream()
							  .filter(s -> s.getKettoTorokuBango().equals(kettoTorokuBango))
						  	  .mapToInt(s -> s.getKakuteiChakujun())
						  	  .average()
						  	  .getAsDouble();
		BigDecimal aveChakujunDecimal = BigDecimal.valueOf(aveChakujun).setScale(0, BigDecimal.ROUND_HALF_UP);
		return aveChakujunDecimal;
		}catch(NoSuchElementException e) {
			return null;
		}
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
		/*for(int i = 0; i < list.size(); i++) {
			if(i>3) {
				list.remove(i--);
			}
		}*/
	}

	public static List<UmaDataView> getUmaKakoData(String kettoTorokuBango) {
		SqlSession session = PckeibalinkSqlSessionFactory.openSession();
		UmaDataViewMapper mapper = session.getMapper(UmaDataViewMapper.class);
		UmaDataViewExample example = new UmaDataViewExample();
		example.createCriteria().andKettoTorokuBangoEqualTo(kettoTorokuBango);
		return mapper.selectByExample(example);
	}

	public static void main(String[] args) {
		UmagotoRaceJoho uma = new UmagotoRaceJoho("2019061602010211");
		List<String> ketto = uma.getKettotorokubango();
		KakoUmagotoRaceJoho ur = new KakoUmagotoRaceJoho("2019061602010211",ketto);
		List<UmaDataView> kako = ur.getList();
		for(UmaDataView data: kako) {
			if(data.getBamei().equals("アスターペガサス"))
			System.out.println(data.getRaceCode());
		}
	}
}
