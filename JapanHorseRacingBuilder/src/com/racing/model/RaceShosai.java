package com.racing.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.aql.access.JvdRaceShosaiSession;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pckeiba.datamodel.RaceData;
import com.pckeiba.entity.JvdRaceShosai;

public class RaceShosai extends Race{

	public RaceShosai(String raceCode) {
		try(JvdRaceShosaiSession rs = new JvdRaceShosaiSession();){
			rs.getExample().createCriteria().andRaceCodeEqualTo(raceCode);
			List<JvdRaceShosai> torokuba = rs.getMapper().selectByExample(rs.getExample());
			setList(Lists.transform(torokuba, new Function<JvdRaceShosai,RaceData>(){
				@Override
				public RaceData apply(JvdRaceShosai arg0) {
					return (RaceData)arg0;
				}
			}));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RaceShosai(Date date) {
		try(JvdRaceShosaiSession rs = new JvdRaceShosaiSession();){
			String[] array = {"01","02","03","04","05","06","07","08","09","10"};
			List<String> keibajoCode = Arrays.asList(array);
			rs.getExample().createCriteria().andKaisaiNengappiEqualTo(date)
											.andKeibajoCodeIn(keibajoCode);
			rs.getExample().setOrderByClause("keibajo_code asc,Hasso_Jikoku asc");
			List<JvdRaceShosai> torokuba = rs.getMapper().selectByExample(rs.getExample());
			setList(Lists.transform(torokuba, new Function<JvdRaceShosai,RaceData>(){
				@Override
				public RaceData apply(JvdRaceShosai arg0) {
					return (RaceData)arg0;
				}
			}));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
