package jhrb.sql.input.access;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.pckeiba.entity.JvdUmagotoRaceJoho;
import com.pckeiba.entity.JvdUmagotoRaceJohoExample;
import com.pckeiba.entity.JvdUmagotoRaceJohoMapper;

import jhrb.pckeiba.list.KishuCodeList;
import jhrb.sql.session.PckeibaInputSession;

public class UmagotoRaceJoho extends PckeibaInputSession<JvdUmagotoRaceJoho, String> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public UmagotoRaceJoho(String raceCode) {
		super(raceCode);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	protected void addResouce(String raceCode) {
		// MAPPER
		JvdUmagotoRaceJohoMapper mapper = session.getMapper(JvdUmagotoRaceJohoMapper.class);
		// EXAMPLE
		JvdUmagotoRaceJohoExample example = new JvdUmagotoRaceJohoExample();
		// WHERE
		example.createCriteria().andRaceCodeEqualTo(raceCode);
		example.setOrderByClause("ijo_kubun_code asc, kakutei_chakujun asc, umaban asc, bamei asc");
		list = mapper.selectByExample(example);
	}

	public KishuCodeList getKishuCodeList() {
		List<String> kishuList = list.stream()
									 .map(s -> s.getKishuCode())
									 .collect(Collectors.toList());
		KishuCodeList kishuCodeList = new KishuCodeList(kishuList);
		return kishuCodeList;

	}

	public List<String> getKettotorokubango() {
		List<String> kettotorokubango = list.stream()
											.map(s -> s.getKettoTorokuBango())
											.collect(Collectors.toList());
		return kettotorokubango;
	}

	public List<String> getChokyoshiList() {
		List<String> chokyoshiList = list.stream()
										 .map(s -> s.getChokyoshiCode())
										 .collect(Collectors.toList());
		return chokyoshiList;
	}

	public List<String> getBanushiList() {
		List<String> banushiList = list.stream()
									   .map(s -> s.getBanushiCode())
									   .collect(Collectors.toList());
		return banushiList;
	}

	public void setDataKubun(String dataKubun) {
		list = list.stream()
				   .filter(s -> s.getDataKubun().equals(dataKubun))
				   .collect(Collectors.toList());
	}

}
