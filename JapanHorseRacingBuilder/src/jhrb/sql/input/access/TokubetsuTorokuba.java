package jhrb.sql.input.access;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.pckeiba.entity.JvdTokubetsuTorokuba;
import com.pckeiba.entity.JvdTokubetsuTorokubaExample;
import com.pckeiba.entity.JvdTokubetsuTorokubaMapper;

import jhrb.sql.session.PckeibaInputSession;

public class TokubetsuTorokuba extends PckeibaInputSession<JvdTokubetsuTorokuba, Integer> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public TokubetsuTorokuba(Integer year) {
		super(year);
	}

	@Override
	protected void addResouce(Integer year) {
		// MAPPER
		JvdTokubetsuTorokubaMapper mapper = session.getMapper(JvdTokubetsuTorokubaMapper.class);
		// EXAMPLE
		JvdTokubetsuTorokubaExample example = new JvdTokubetsuTorokubaExample();
		// WHERE
		LocalDate beforDate = LocalDate.of(year, 1, 1);
		LocalDate afterDate = LocalDate.of(year, 12, 31);
		Date beforDate_ = Date.from(beforDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date afterDate_ = Date.from(afterDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		example.createCriteria().andKaisaiNengappiBetween(beforDate_, afterDate_);
		example.setOrderByClause("kaisai_nengappi asc,keibajo_code asc,race_bango asc");
		list = mapper.selectByExample(example);
	}


	/**
	 * 引数の日付と開催日が一致するJvdRaceShosaiオブジェクトのリストを返します。
	 * @param date 開催日
	 * @return	JvdTokubetsuTorokubaオブジェクトのリスト
	 * @throws NullPointerException
	 */
	public List<JvdTokubetsuTorokuba> getJvdTokubetsuTorokuba(Date date) throws NullPointerException {
		List<JvdTokubetsuTorokuba> raceList = list.stream()
											.filter(s -> s.getKaisaiNengappi().equals(date))
											.collect(Collectors.toList());
		if(raceList.size() == 0) {
			throw new NullPointerException();
		}
		return raceList;
	}

	/**
	 * 引数の日付と開催日が一致するJvdRaceShosaiオブジェクトのリストを返します。
	 * @param date 開催日
	 * @param keibajoCode 競馬場コード
	 * @return	JvdTokubetsuTorokubaオブジェクトのリスト
	 * @throws NullPointerException
	 */
	public List<JvdTokubetsuTorokuba> getJvdTokubetsuTorokuba(Date date,String keibajoCode) throws NullPointerException {
		List<JvdTokubetsuTorokuba> raceList = list.stream()
											.filter(s -> s.getKaisaiNengappi().equals(date))
											.filter(s -> s.getKeibajoCode().equals(keibajoCode))
											.collect(Collectors.toList());
		if(raceList.size() == 0) {
			throw new NullPointerException();
		}
		return raceList;
	}
	public static void main(String[] args) {
		LocalDate dd = LocalDate.of(2019, 6, 9);
		Date date = Date.from(dd.atStartOfDay(ZoneId.systemDefault()).toInstant());
		System.out.println(date);
		TokubetsuTorokuba tt = new TokubetsuTorokuba(2019);
		List<JvdTokubetsuTorokuba> list = tt.getJvdTokubetsuTorokuba(date);
		System.out.println(list.size());
		for(JvdTokubetsuTorokuba data: list) {
			System.out.println(data.getKyosomeiHondai());
		}
	}
}
