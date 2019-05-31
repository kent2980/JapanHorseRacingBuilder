package jhrb.sql.access;

import java.io.Serializable;
import java.util.List;

import com.pckeiba.entity.JvdBanushiMaster;
import com.pckeiba.entity.JvdBanushiMasterExample;
import com.pckeiba.entity.JvdBanushiMasterMapper;

import jhrb.datainterface.DataInterface;
import jhrb.sql.session.PckeibaSession;
import jhrb.sql.session.PckeibaSqlSessionFactory;

public class BanushiMaster extends PckeibaSession<JvdBanushiMaster> implements Serializable, DataInterface<JvdBanushiMaster> {
	/**
	 *	フィールド
	 */
	private static final long serialVersionUID = 1L;
	private List<String> BanushiCode;

	/**
	 * @param BanushiCode 馬主コードのリスト
	 * コンストラクタ
	 */
	public BanushiMaster(List<String> BanushiCode) {
		this.BanushiCode = BanushiCode;
		this.addDataResouce();
	}

	/* (非 Javadoc)
	 * @see com.racing.model.PckeibaDataInterface#addDataResouce()
	 */
	@Override
	public void addDataResouce() {
		// MAPPER
		JvdBanushiMasterMapper mapper = session.getMapper(JvdBanushiMasterMapper.class);
		// EXAMPLE
		JvdBanushiMasterExample example = new JvdBanushiMasterExample();
		// WHERE
		example.createCriteria().andBanushiCodeIn(BanushiCode);
		list = mapper.selectByExample(example);
	}

	/**
	 * @return 馬主コードのリスト
	 */
	public List<String> getBanushiCode() {
		return BanushiCode;
	}

	/* (非 Javadoc)
	 * @see com.racing.model.PckeibaDataInterface#getList()
	 */
	@Override
	public List<JvdBanushiMaster> getList() {
		return list;
	}

	/**
	 * @param banushiCode 馬主コードのリスト
	 */
	public void setBanushiCode(List<String> banushiCode) {
		BanushiCode = banushiCode;
		addDataResouce();
	}

	public static JvdBanushiMaster getBanushiMaster(String banushiCode) {
		// MAPPER
		JvdBanushiMasterMapper mapper = PckeibaSqlSessionFactory.openSession().getMapper(JvdBanushiMasterMapper.class);
		// EXAMPLE
		JvdBanushiMasterExample example = new JvdBanushiMasterExample();
		// WHERE
		example.createCriteria().andBanushiCodeEqualTo(banushiCode);
		return mapper.selectByExample(example).get(0);

	}
}
