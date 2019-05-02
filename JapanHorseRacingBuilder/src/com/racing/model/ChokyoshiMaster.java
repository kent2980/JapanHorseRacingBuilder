package com.racing.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import com.aql.access.JvdChokyoshiMasterSession;
import com.pckeiba.entity.JvdChokyoshiMaster;

public class ChokyoshiMaster implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdChokyoshiMaster> list;

	public ChokyoshiMaster(List<String> ChokyoshiCode) {
		try(JvdChokyoshiMasterSession rs = new JvdChokyoshiMasterSession();){
			rs.getExample().createCriteria().andChokyoshiCodeIn(ChokyoshiCode);
			setList(rs.getMapper().selectByExample(rs.getExample()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<JvdChokyoshiMaster> getList() {
		return list;
	}

	private void setList(List<JvdChokyoshiMaster> list) {
		this.list = list;
	}

}
