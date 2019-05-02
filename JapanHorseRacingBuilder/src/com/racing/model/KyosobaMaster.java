package com.racing.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import com.aql.access.JvdKyosobaMasterSession;
import com.pckeiba.entity.JvdKyosobaMaster;

public class KyosobaMaster implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<JvdKyosobaMaster> list;

	public KyosobaMaster(List<String> kettoTorokuBango) {
		try(JvdKyosobaMasterSession rs = new JvdKyosobaMasterSession();){
			rs.getExample().createCriteria().andKettoTorokuBangoIn(kettoTorokuBango);
			setList(rs.getMapper().selectByExample(rs.getExample()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<JvdKyosobaMaster> getList() {
		return list;
	}

	private void setList(List<JvdKyosobaMaster> list) {
		this.list = list;
	}

}
