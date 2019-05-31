package jhrb.sql.session;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class PckeibaSession<T> implements SessionControll {
	public PckeibaSession() {
		super();
		this.session = PckeibaSqlSessionFactory.openSession();
	}

	@Override
	public SqlSession getSession() {
		return session;
	}
	protected final SqlSession session;
	protected List<T> list;
}
