package jhrb.sql.access;

import java.io.Serializable;
import java.util.List;

import com.pckeiba.entity.JvdUmagotoRaceJoho;
import com.pckeiba.entity.JvdUmagotoRaceJohoExample;
import com.pckeiba.entity.JvdUmagotoRaceJohoMapper;

import jhrb.sql.session.PckeibaInputSession;

public class KishugotoRaceJoho extends PckeibaInputSession<JvdUmagotoRaceJoho, String> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public KishugotoRaceJoho(String kishuCode) {
		super(kishuCode);
	}

	@Override
	protected void addResouce(String args) {
		// MAPPER
		JvdUmagotoRaceJohoMapper mapper = session.getMapper(JvdUmagotoRaceJohoMapper.class);
		// EXAMPLE
		JvdUmagotoRaceJohoExample example = new JvdUmagotoRaceJohoExample();
		// WHERE
		example.createCriteria().andKishuCodeEqualTo(args);
		example.setOrderByClause("Kaisai_Nengappi asc limit 100");
		list = mapper.selectByExample(example);
	}

	public static void main(String[] args) {
		KishugotoRaceJoho joho = new KishugotoRaceJoho("01102");
		List<JvdUmagotoRaceJoho> list = joho.getList();
		int i = 0;
		for(JvdUmagotoRaceJoho data: list) {
			System.out.println(++i + "." + data.getRaceCode());
		}
	}
}
