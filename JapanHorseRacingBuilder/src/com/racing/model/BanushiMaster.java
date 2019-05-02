package com.racing.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import com.aql.access.JvdBanushiMasterSession;
import com.pckeiba.entity.JvdBanushiMaster;

public class BanushiMaster implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdBanushiMaster> list;

	public BanushiMaster(List<String> BanushiCode) {
		try(JvdBanushiMasterSession rs = new JvdBanushiMasterSession();){
			rs.getExample().createCriteria().andBanushiCodeIn(BanushiCode);
			setList(rs.getMapper().selectByExample(rs.getExample()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<JvdBanushiMaster> getList() {
		return list;
	}

	private void setList(List<JvdBanushiMaster> list) {
		this.list = list;
	}

}
