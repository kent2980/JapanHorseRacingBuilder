package com.racing.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.aql.access.JvdUmagotoRaceJohoSession;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pckeiba.datamodel.HorseData;
import com.pckeiba.entity.JvdUmagotoRaceJoho;

public class UmagotoRaceJoho extends Horse implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public UmagotoRaceJoho(String raceCode) {
		try(JvdUmagotoRaceJohoSession rs = new JvdUmagotoRaceJohoSession();){
			rs.getExample().createCriteria().andRaceCodeEqualTo(raceCode);
			rs.getExample().setOrderByClause("ijo_kubun_code asc, kakutei_chakujun asc, umaban asc");
			List<JvdUmagotoRaceJoho> umagoto = rs.getMapper().selectByExample(rs.getExample());
			setList(Lists.transform(umagoto, new Function<JvdUmagotoRaceJoho,HorseData>(){
				@Override
				public HorseData apply(JvdUmagotoRaceJoho arg0) {
					return (HorseData)arg0;
				}
			}));
			setKettotorokubango(umagoto.stream()
									.map(s -> s.getKettoTorokuBango())
									.collect(Collectors.toList()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
