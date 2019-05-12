package com.racing.model;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.pckeiba.entity.JvdUmagotoRaceJoho;
import com.pckeiba.entity.JvdUmagotoRaceJohoExample;
import com.pckeiba.entity.JvdUmagotoRaceJohoMapper;

/**
 * MyBatisを使ってDBにアクセスするサンプルプログラムです.
 *
 */
public class App {
    public static void main(String[] args) {
		int i = 0;
		long start = System.currentTimeMillis();
    	long end = System.currentTimeMillis();

		String racecode = "2019051205020811";

        // resources直下のmybatis-config.xmlを読み込みます
        try (Reader r = Resources.getResourceAsReader("pckeiba-mybatis-config.xml");) {

        	//1
        	end = System.currentTimeMillis();
        	System.out.println(++i + ".[" + (end - start) + "ms]");

            // 読み込んだ設定ファイルからSqlSessionFactoryを生成します
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);

            //2
        	end = System.currentTimeMillis();
        	System.out.println(++i + ".[" + (end - start) + "ms]");

            // SQLセッションを取得します
            try (SqlSession session = factory.openSession()) {

            	//3
            	end = System.currentTimeMillis();
            	System.out.println(++i + ".[" + (end - start) + "ms]");

                // ActorテーブルのMapperを取得します
            	JvdUmagotoRaceJohoMapper map = session.getMapper(JvdUmagotoRaceJohoMapper.class);
            	//4
            	end = System.currentTimeMillis();
            	System.out.println(++i + ".[" + (end - start) + "ms]");

                // Actorテーブルの条件検索用クラスを生成します
            	JvdUmagotoRaceJohoExample ex = new JvdUmagotoRaceJohoExample();
            	//5
            	end = System.currentTimeMillis();
            	System.out.println(++i + ".[" + (end - start) + "ms]");

                // WHERE
                //    (first_name LIKE 'T%' AND actor_id < 100)
                //    OR (last_name LIKE 'S%' AND actor_id > 100)
                // 検索条件に↑と同等の条件を設定しています
                // 　  Criteriaを作成し、AND条件を追加する (1)
                ex.createCriteria().andRaceCodeEqualTo(racecode);

                //6
            	end = System.currentTimeMillis();
            	System.out.println(++i + ".[" + (end - start) + "ms]");

                // 上記の条件でテーブルを検索します
                List<JvdUmagotoRaceJoho> actorList = map.selectByExample(ex);
                //7
            	end = System.currentTimeMillis();
            	System.out.println(++i + ".[" + (end - start) + "ms]");

                // 取得結果を表示します
                for (JvdUmagotoRaceJoho actor : actorList) {

                    System.out.println(actor.getBamei());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //8
    	end = System.currentTimeMillis();
    	System.out.println(++i + ".[" + (end - start) + "ms]");
    }

}