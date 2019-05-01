package com.racing.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.aql.access.JvdTorokubagotoJohoSession;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pckeiba.datamodel.HorseData;
import com.pckeiba.entity.JvdTorokubagotoJoho;

public class TorokubagotoJoho extends Horse implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public TorokubagotoJoho(String raceCode) {
		try(JvdTorokubagotoJohoSession rs = new JvdTorokubagotoJohoSession();){
			rs.getExample().createCriteria().andRaceCodeEqualTo(raceCode);
			List<JvdTorokubagotoJoho> torokuba = rs.getMapper().selectByExample(rs.getExample());
			setList(Lists.transform(torokuba, new Function<JvdTorokubagotoJoho,HorseData>(){
				@Override
				public HorseData apply(JvdTorokubagotoJoho arg0) {
					return (HorseData)arg0;
				}
			}));
			setKettotorokubango(torokuba.stream()
									.map(s -> s.getKettoTorokuBango())
									.collect(Collectors.toList()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
