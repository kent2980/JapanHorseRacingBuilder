package com.racing.model.pckeiba;

import java.io.Serializable;
import java.util.List;

import com.database.access.PckeibaSession;
import com.pckeiba.entity.JvdChokyoshiMaster;
import com.pckeiba.entity.JvdChokyoshiMasterExample;
import com.pckeiba.entity.JvdChokyoshiMasterMapper;
import com.racing.model.DataInterface;

public class ChokyoshiMaster extends PckeibaSession implements Serializable, DataInterface<JvdChokyoshiMaster> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdChokyoshiMaster> list;
	private List<String> ChokyoshiCode;

	/**
	 * @param ChokyoshiCode
	 */
	public ChokyoshiMaster(List<String> ChokyoshiCode) {
		this.ChokyoshiCode = ChokyoshiCode;
		addDataResouce();
	}

	@Override
	public void addDataResouce() {
		// MAPPER
		JvdChokyoshiMasterMapper mapper = session.getMapper(JvdChokyoshiMasterMapper.class);
		// EXAMPLE
		JvdChokyoshiMasterExample example = new JvdChokyoshiMasterExample();
		// WHERE{
		example.createCriteria().andChokyoshiCodeIn(ChokyoshiCode);
		list = mapper.selectByExample(example);
	}

	/* (非 Javadoc)
	 * @see com.racing.model.DataInterface#getList()
	 */
	public List<JvdChokyoshiMaster> getList() {
		return list;
	}

	public List<String> getChokyoshiCode() {
		return ChokyoshiCode;
	}

	public void setChokyoshiCode(List<String> chokyoshiCode) {
		ChokyoshiCode = chokyoshiCode;
		addDataResouce();
	}

}
