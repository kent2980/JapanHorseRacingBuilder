package com.racing.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import com.aql.access.JvdKishuMasterSession;
import com.pckeiba.entity.JvdKishuMaster;

public class KishuMaster implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdKishuMaster> list;

	public KishuMaster(List<String> kishuCode) {
		try(JvdKishuMasterSession rs = new JvdKishuMasterSession();){
			rs.getExample().createCriteria().andKishuCodeIn(kishuCode);
			setList(rs.getMapper().selectByExample(rs.getExample()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<JvdKishuMaster> getList() {
		return list;
	}

	private void setList(List<JvdKishuMaster> list) {
		this.list = list;
	}

}
