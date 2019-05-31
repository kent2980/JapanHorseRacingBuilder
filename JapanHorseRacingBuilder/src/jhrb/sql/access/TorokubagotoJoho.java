package jhrb.sql.access;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.pckeiba.entity.JvdTorokubagotoJoho;
import com.pckeiba.entity.JvdTorokubagotoJohoExample;
import com.pckeiba.entity.JvdTorokubagotoJohoMapper;

import jhrb.datainterface.DataInterface;
import jhrb.sql.session.PckeibaSession;

public class TorokubagotoJoho extends PckeibaSession<JvdTorokubagotoJoho> implements Serializable, DataInterface<JvdTorokubagotoJoho> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<String> kettotorokubango;
	private List<String> chokyoshiList;
	private String raceCode;

	public TorokubagotoJoho(String raceCode) {
		this.raceCode = raceCode;
		addDataResouce();
	}

	@Override
	public void addDataResouce() {
		// MAPPER
		JvdTorokubagotoJohoMapper mapper = session.getMapper(JvdTorokubagotoJohoMapper.class);
		// EXAMPLE
		JvdTorokubagotoJohoExample example = new JvdTorokubagotoJohoExample();
		// WHERE
		example.createCriteria().andRaceCodeEqualTo(raceCode);
		list = mapper.selectByExample(example);
		setKettotorokubango();
		setChokyoshiList();
	}

	public List<String> getChokyoshiList() {
		return chokyoshiList;
	}

	public List<String> getKettotorokubango() {
		return kettotorokubango;
	}

	@Override
	public List<JvdTorokubagotoJoho> getList() {
		return list;
	}

	private void setChokyoshiList() {
		this.chokyoshiList = list.stream().map(s -> s.getChokyoshiCode()).collect(Collectors.toList());
	}

	private void setKettotorokubango() {
		this.kettotorokubango = list.stream().map(s -> s.getKettoTorokuBango()).collect(Collectors.toList());
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
		addDataResouce();
	}

}