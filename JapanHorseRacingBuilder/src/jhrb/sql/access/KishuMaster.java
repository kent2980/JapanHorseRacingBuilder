package jhrb.sql.access;

import java.io.Serializable;
import java.util.List;

import com.pckeiba.entity.JvdKishuMaster;
import com.pckeiba.entity.JvdKishuMasterExample;
import com.pckeiba.entity.JvdKishuMasterMapper;

import jhrb.datainterface.DataInterface;
import jhrb.sql.session.PckeibaSession;

public class KishuMaster extends PckeibaSession<JvdKishuMaster> implements Serializable,DataInterface<JvdKishuMaster>{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
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
