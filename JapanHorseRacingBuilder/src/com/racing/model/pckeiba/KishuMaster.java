package com.racing.model.pckeiba;

import java.io.Serializable;
import java.util.List;

import com.database.access.PckeibaSession;
import com.pckeiba.entity.JvdKishuMaster;
import com.pckeiba.entity.JvdKishuMasterExample;
import com.pckeiba.entity.JvdKishuMasterMapper;
import com.racing.model.DataInterface;

public class KishuMaster extends PckeibaSession implements Serializable,DataInterface<JvdKishuMaster>{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdKishuMaster> list;
	private List<String> kishuCode;

	public KishuMaster(List<String> kishuCode) {
		this.kishuCode = kishuCode;
		addDataResouce();
	}

	public List<JvdKishuMaster> getList() {
		return list;
	}

	@Override
	public void addDataResouce() {
		// MAPPER
		JvdKishuMasterMapper mapper = session.getMapper(JvdKishuMasterMapper.class);
		// EXAMPLE
		JvdKishuMasterExample example = new JvdKishuMasterExample();
		//WHERE{
			example.createCriteria().andKishuCodeIn(kishuCode);
			list = mapper.selectByExample(example);
	}

	public List<String> getKishuCode() {
		return kishuCode;
	}

	public void setKishuCode(List<String> kishuCode) {
		this.kishuCode = kishuCode;
		addDataResouce();
	}

}
