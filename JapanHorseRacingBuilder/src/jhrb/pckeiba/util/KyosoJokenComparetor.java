package jhrb.pckeiba.util;

import java.util.Comparator;

public class KyosoJokenComparetor implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// 引数をIntegerにキャストします
		int o1_ = Integer.parseInt(o1);
		int o2_ = Integer.parseInt(o2);

		// 返り値が等価である条件
		if (o1_ == o2_) {
			return 0;
		}else if (o1_ == 701) {
			return 0;
		}else if (o1_ == 702) {
			return 0;
		}else if (o1_ == 703) {
			return 0;
		}

		//値の比較のために、下記の式を実行します
		o1_ = o1_ > 700 ? o1_ - 800 : o1_;
		o2_ = o2_ > 700 ? o2_ - 800 : o2_;

		//返り値がo1 > o2である条件
		if(o1_ > o2_) {
			return 1;
		}else {
			return -1;
		}

	}

	public static void main(String[] args) {
		int cc = new KyosoJokenComparetor().compare("010", "010");
		System.out.println(cc);
	}

}
