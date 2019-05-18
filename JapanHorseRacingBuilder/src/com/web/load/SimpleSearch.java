package com.web.load;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.*;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class SimpleSearch {

	public static void main(String[] args) throws TwitterException {

		// 初期化
		Twitter twitter = new TwitterFactory().getInstance();
		Query query = new Query();


		// 検索ワードをセット（試しにバルスを検索）
		query.setQuery("yyy");

		// 検索実行
		QueryResult result = twitter.search(query);

		System.out.println("ヒット数 : " + result.getTweets().size());

		// 検索結果を見てみる
		for (Status tweet : result.getTweets()) {
			// 本文
			System.out.println(tweet.getText());

			// 発言したユーザ
			// 発言した日時
			System.out.println(tweet.getCreatedAt());
			// 他、取れる値はJavaDoc参照
			// http://twitter4j.org/ja/javadoc/twitter4j/Tweet.html
		}

	}

}