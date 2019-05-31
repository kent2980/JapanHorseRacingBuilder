package jhrb.sql.session;

import org.apache.ibatis.session.SqlSession;

public class PckeibaLinkSession implements SessionControll {
	public PckeibaLinkSession() {
		super();
		this.session = PckeibalinkSqlSessionFactory.openSession();
	}

	@Override
	public SqlSession getSession() {
		return session;
	}
	protected final SqlSession session;
}
