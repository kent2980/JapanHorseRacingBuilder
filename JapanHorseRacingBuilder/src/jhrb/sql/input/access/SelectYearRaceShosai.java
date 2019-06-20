package jhrb.sql.input.access;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.pckeiba.entity.JvdRaceShosai;
import com.pckeiba.entity.JvdRaceShosaiExample;
import com.pckeiba.entity.JvdRaceShosaiMapper;
import jhrb.sql.session.PckeibaInputSession;

public class SelectYearRaceShosai extends PckeibaInputSession<JvdRaceShosai, Integer> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public SelectYearRaceShosai(Integer year) {
		super(year);
	}

	@Override
	protected void addResouce(Integer year) {
		// MAPPER
		JvdRaceShosaiMapper mapper = session.getMapper(JvdRaceShosaiMapper.class);
		// EXAMPLE
		JvdRaceShosaiExample example = new JvdRaceShosaiExample();
		//競馬場コードのリスト
		String[] array = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" };
		List<String> keibajoCode = Arrays.asList(array);
		// WHERE
		LocalDate beforDate = LocalDate.of(year, 1, 1);
		LocalDate afterDate = LocalDate.of(year, 12, 31);
		Date beforDate_ = Date.from(beforDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date afterDate_ = Date.from(afterDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		example.createCriteria().andKaisaiNengappiBetween(beforDate_, afterDate_).andKeibajoCodeIn(keibajoCode);
		example.setOrderByClause("kaisai_nengappi asc,keibajo_code asc,race_bango asc");
		list = mapper.selectByExample(example);
	}

	/**
	 * 引数の日付と開催日が一致するJvdRaceShosaiオブジェクトのリストを返します。
	 * @param date 開催日
	 * @return	JvdRaceShosaiオブジェクトのリスト
	 * @throws NullPointerException
	 */
	public List<JvdRaceShosai> getRaceShosai(Date date) throws NullPointerException {
		List<JvdRaceShosai> raceList = list.stream()
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
	 * @return	JvdRaceShosaiオブジェクトのリスト
	 * @throws NullPointerException
	 */
	public List<JvdRaceShosai> getRaceShosai(Date date,String keibajoCode) throws NullPointerException {
		List<JvdRaceShosai> raceList = list.stream()
											.filter(s -> s.getKaisaiNengappi().equals(date))
											.filter(s -> s.getKeibajoCode().equals(keibajoCode))
											.collect(Collectors.toList());
		if(raceList.size() == 0) {
			throw new NullPointerException();
		}
		return raceList;
	}

	/**
	 * 引数の日付と一致する開催スケジュールから、競馬場コードのリストを返します。
	 * @param date 開催日
	 * @return 競馬場コードのリスト
	 */
	public List<String> getKeibajoCodeList(Date date) {
		List<String> keibajoCode = list.stream()
									   .filter(s -> s.getKaisaiNengappi().equals(date))
									   .map(s -> s.getKeibajoCode())
									   .distinct()
									   .collect(Collectors.toList());
		return keibajoCode;
	}

	public static void main(String[] args) throws NullPointerException {
		SelectYearRaceShosai rs = new SelectYearRaceShosai(2018);
		List<Date> list = rs.getList().stream().map(s -> s.getKaisaiNengappi())
									.distinct().collect(Collectors.toList());
		for(Date data: list) {
			System.out.println(data);
		}
	}

}
