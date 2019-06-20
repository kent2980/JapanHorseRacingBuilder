package jhrb.sql.input.access;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.pckeiba.entity.JvdKaisaiSchedule;
import com.pckeiba.entity.JvdKaisaiScheduleExample;
import com.pckeiba.entity.JvdKaisaiScheduleMapper;

import jhrb.sql.session.PckeibaInputSession;

public class KaisaiSchedule extends PckeibaInputSession<JvdKaisaiSchedule, Integer> implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public KaisaiSchedule(Integer year) {
		super(year);
	}

	@Override
	protected void addResouce(Integer year) {
		// MAPPER
		JvdKaisaiScheduleMapper mapper = session.getMapper(JvdKaisaiScheduleMapper.class);
		// EXAMPLE
		JvdKaisaiScheduleExample example = new JvdKaisaiScheduleExample();
		// WHERE
		LocalDate beforDate = LocalDate.of(year, 1, 1);
		LocalDate afterDate = LocalDate.of(year, 12, 31);
		Date beforDate_ = Date.from(beforDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date afterDate_ = Date.from(afterDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		example.createCriteria().andKaisaiNengappiBetween(beforDate_, afterDate_);
		example.setOrderByClause("kaisai_nengappi asc");
		list = mapper.selectByExample(example);
	}

	/**
	 * 引数と合致する開催スケジュールを返します。
	 * 第1引数に日付を、第2引数に競馬場コードを入力してください。
	 * @param date 開催日
	 * @param keibajoCode 競馬場コード
	 * @return 開催スケジュール<JvdKaisaiSchedule>
	 */
	public JvdKaisaiSchedule getKaisaiSchedule(Date date, String keibajoCode) {
		JvdKaisaiSchedule schedule = list.stream()
										 .filter(s -> s.getKaisaiNengappi().equals(date))
										 .filter(s -> s.getKeibajoCode().equals(keibajoCode))
										 .findFirst()
										 .get();
		return schedule;
	}

	/**
	 * 開催スケジュールから開催日数を返します
	 * @return　開催日数
	 */
	public int getKaisaiCount() {
		long count = list.stream()
						.map(s -> s.getKaisaiNengappi())
						.distinct()
						.count();
		return (int) count;
	}

	/**
	 * 引数の開催日に最も近い開催日が何番目であるかを返します。
	 * 最初の開催日は0番目となります。
	 * 開催がリストに存在しない場合は、-1を返します。
	 * @param date 開催日
	 * @return 開催日の順番
	 */
	public int getKaisaiSelectCount(Date date) {
		//Dateオブジェクトの時分をリセットします
	    Instant instant = date.toInstant();
	    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("JST", ZoneId.SHORT_IDS));
	    ZonedDateTime truncated = zonedDateTime.truncatedTo(ChronoUnit.DAYS);
	    Date now = Date.from(truncated.toInstant());	//時分をリセットしたDateオブジェクト
	    //開催スケジュールから開催日付のリストを返します。
		List<Date> dateList = this.getKaisaiNenGappiList();

		//開催日次のリスト内順番を取得します
		for(int i = 0; i < dateList.size(); i++) {
			if(dateList.get(i).compareTo(now) == 0 ) {
				return i;
			}else if(now.before(dateList.get(i))) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 開催年月日の重複しないリストを返します。
	 * @return 開催年月日のリスト
	 */
	public List<Date> getKaisaiNenGappiList(){
		List<Date> dateList = list.stream()
								  .map(s -> s.getKaisaiNengappi())
								  .distinct()
								  .collect(Collectors.toList());
		return dateList;
	}

	/**
	 * 引数の日付と一致した開催スケジュールのリストを返します。
	 * @param date 開催日
	 * @return 開催スケジュールのリスト
	 */
	public List<JvdKaisaiSchedule> getSelectDateList(Date date){
		List<JvdKaisaiSchedule> ksList = list.stream()
											 .filter(s -> s.getKaisaiNengappi().equals(date))
											 .collect(Collectors.toList());
		return ksList;
	}

	public List<JvdKaisaiSchedule> getSelectList(int month) {
		List<JvdKaisaiSchedule> list_ = list.stream()
											.filter(s -> s.getKaisaiNengappi().toInstant().atZone(ZoneId.systemDefault()).getMonthValue() == month)
											.filter(s -> s.getJusho1GradeCode().equals("A")|s.getJusho1GradeCode().equals("B"))
											.collect(Collectors.toList());
		return list_;
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

	public static void main(String[] args) {
		KaisaiSchedule sc = new KaisaiSchedule(2019);
		List<JvdKaisaiSchedule> list = sc.getList();
		for(JvdKaisaiSchedule data: list) {
			System.out.println(data.getJusho1KyosomeiHondai());
			System.out.println(sc.getKaisaiSelectCount(data.getKaisaiNengappi()) + "番目の開催です。");
		}
		System.out.println("開催日は" + sc.getKaisaiCount() + "日");
	    Instant instant = new Date().toInstant();
	    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("JST", ZoneId.SHORT_IDS));
	    ZonedDateTime truncated = zonedDateTime.truncatedTo(ChronoUnit.DAYS);
	    Date now = Date.from(truncated.toInstant());
		System.out.println(now);
		System.out.println("最も近い開催は" + sc.getKaisaiSelectCount(now) + "番目です。");
		System.out.println("5月に開催する重賞は");
		list = sc.getSelectList(12);
		for(JvdKaisaiSchedule data : list) {
			if(!data.getJusho1TokubetsuKyosoBango().equals("0000"))
			System.out.println("1." + data.getJusho1KyosomeiHondai());
			if(!data.getJusho2TokubetsuKyosoBango().equals("0000"))
			System.out.println("2." + data.getJusho2KyosomeiHondai());
			if(!data.getJusho3TokubetsuKyosoBango().equals("0000"))
			System.out.println("3." + data.getJusho3KyosomeiHondai());
		}	}

}
