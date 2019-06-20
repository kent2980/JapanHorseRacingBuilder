package jhrb.sql.access;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.pckeiba.entity.JvdRaceShosai;
import com.pckeiba.entity.JvdRaceShosaiExample;
import com.pckeiba.entity.JvdRaceShosaiMapper;

import jhrb.datainterface.DataInterface;
import jhrb.sql.session.PckeibaSession;

public class RaceShosai extends PckeibaSession<JvdRaceShosai> implements DataInterface<JvdRaceShosai>{

	private Date date;
	private String raceCode;

	public RaceShosai(Date date) {
		super();
		this.date = date;
		addDataResouce();
	}
	public RaceShosai(String raceCode) {
		super();
		this.raceCode = raceCode;
		String dateCode = raceCode.substring(0, 8);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(dateCode);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("レースコードのフォーマットが正しくありません");
		}
		this.date = date;
		addDataResouce();
	}

	@Override
	public void addDataResouce() {
		// MAPPER
		JvdRaceShosaiMapper mapper = session.getMapper(JvdRaceShosaiMapper.class);
		// EXAMPLE
		JvdRaceShosaiExample example = new JvdRaceShosaiExample();
		//競馬場コードのリスト
		String[] array = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" };
		List<String> keibajoCode = Arrays.asList(array);
		//WHERE
		example.createCriteria().andKaisaiNengappiEqualTo(date).andKeibajoCodeIn(keibajoCode);
		//ORDER
		example.setOrderByClause("keibajo_code asc,Hasso_Jikoku asc");
		//SELECT
		list = mapper.selectByExample(example);
	}

	@Override
	public List<JvdRaceShosai> getList() {
		return list;
	}

	public String getRaceCode() {
		return raceCode;
	}

	public JvdRaceShosai getRaceShosai() {
		return list.stream()
				   .filter(s -> s.getRaceCode().equals(raceCode))
				   .findFirst().get();
	}

	/**
	 * JvdRaceShosaiからDataKubunを返します。
	 * @return データ区分
	 */
	public String getDataKubun() {
		String dataKubun = this.getRaceShosai().getDataKubun();
		return dataKubun;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
		String dateCode = raceCode.substring(0, 8);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(dateCode);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("レースコードのフォーマットが正しくありません");
		}
		this.date = date;
		addDataResouce();
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
		addDataResouce();
	}

}
