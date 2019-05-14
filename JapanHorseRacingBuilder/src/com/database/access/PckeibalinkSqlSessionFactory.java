package com.database.access;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * セッションファクトリ.
 */
public final class PckeibalinkSqlSessionFactory {
    /**
     * シングルトン　インスタンス.
     */
    private static SqlSessionFactory sqlSessionFactory;

    /**
     * ファクトリのインスタンスの取得.
     * @return ファクトリ
     */
    private static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("pckeibalink-mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
            System.out.println("pckeibaセッションを開始しました");
        }
            return sqlSessionFactory;
    }

    /**
     * セッション開始.
     * @return セッション
     */
    public static SqlSession openSession() {
        return getSqlSessionFactory().openSession();
    }
}