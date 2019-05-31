package jhrb.datainterface;

import java.util.List;

public interface DataInputInterface<T,S> {
	/**
	 * ユーザーが指定したリソースからのデータ取得メソッドを提供します。
	 * 様々なリソースから統一されたインターフェースでのデータセットを保証します。
	 */
	void addDataResouce(S args);
	/**
	 * @return	指定されたクラスのリスト
	 */
	List<T> getList();
}
