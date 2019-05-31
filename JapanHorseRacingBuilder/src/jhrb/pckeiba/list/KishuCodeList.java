package jhrb.pckeiba.list;

import java.util.ArrayList;
import java.util.List;

public class KishuCodeList extends ArrayList<String> {

	public KishuCodeList(List<String> list) {
		super();
		setList(list);
	}
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public void setList(List<String> list) {
		for(String code : list) {
			this.add(code);
		}
	}

}
