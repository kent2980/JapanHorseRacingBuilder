package com.database.access;

import org.apache.ibatis.session.SqlSession;

public interface SessionControll {
	SqlSession getSession();
}
