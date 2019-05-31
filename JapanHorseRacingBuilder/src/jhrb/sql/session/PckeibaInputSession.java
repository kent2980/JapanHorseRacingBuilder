package jhrb.sql.session;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public abstract class PckeibaInputSession<T,S> implements SessionControll {
	public PckeibaInputSession(S args) {
		this.session = PckeibaSqlSessionFactory.openSession();
		this.args = args;
		addResouce(args);
	}

	@Override
	public SqlSession getSession() {
		return session;
	}
	protected final SqlSession session;
	protected S args;
	protected List<T> list;
	public List<T> getList(){
		return list;
	}
	public S getInputData() {
		return args;
	}

	protected abstract void addResouce(S args);
}
