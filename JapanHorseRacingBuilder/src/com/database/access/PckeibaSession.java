package com.database.access;

import org.apache.ibatis.session.SqlSession;

public class PckeibaSession implements SessionControll {
	public PckeibaSession() {
		super();
		this.session = PckeibaSqlSessionFactory.openSession();
	}

	@Override
	public SqlSession getSession() {
		return session;
	}
	protected final SqlSession session;
}
