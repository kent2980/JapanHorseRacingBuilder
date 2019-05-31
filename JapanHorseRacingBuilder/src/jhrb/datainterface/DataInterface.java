package jhrb.datainterface;

import java.util.List;

public interface DataInterface<T> {
	/**
	 * ユーザーが指定したリソースからのデータ取得メソッドを提供します。
	 * 様々なリソースから統一されたインターフェースでのデータセットを保証します。
	 */
	void addDataResouce();
	/**
	 * @return	指定されたクラスのリスト
	 */
	List<T> getList();
}
