package com.racing.model;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.aql.access.JvdTokubetsuTorokubaSession;
import com.pckeiba.datamodel.RaceData;
import com.pckeiba.entity.JvdTokubetsuTorokuba;

public class TokubetsuTorokuba extends Race{

	public TokubetsuTorokuba(String raceCode) {
		try(JvdTokubetsuTorokubaSession rs = new JvdTokubetsuTorokubaSession();){
			rs.getExample().createCriteria().andRaceCodeEqualTo(raceCode);
			List<JvdTokubetsuTorokuba> torokuba = rs.getMapper().selectByExample(rs.getExample());
			setList(Lists.transform(torokuba, new Function<JvdTokubetsuTorokuba,RaceData>(){
				@Override
				public RaceData apply(JvdTokubetsuTorokuba arg0) {
					return (RaceData)arg0;
				}
			}));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TokubetsuTorokuba() {
		try(JvdTokubetsuTorokubaSession rs = new JvdTokubetsuTorokubaSession();){
			rs.getExample().createCriteria().andKaisaiNengappiGreaterThan(new Date());
			rs.getExample().setOrderByClause("kaisai_nengappi asc,keibajo_code asc");
			List<JvdTokubetsuTorokuba> torokuba = rs.getMapper().selectByExample(rs.getExample());
			setList(Lists.transform(torokuba, new Function<JvdTokubetsuTorokuba,RaceData>(){
				@Override
				public RaceData apply(JvdTokubetsuTorokuba arg0) {
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
