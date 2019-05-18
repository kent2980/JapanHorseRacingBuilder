<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"

	import="java.util.List"

	import="com.racing.model.netkeiba.*"
	import="com.racing.model.pckeiba.*"

	import="com.example.entity.UmaDataView"
	import="com.pckeiba.entity.JvdKyosobaMaster"
	import="com.pckeiba.entity.JvdChokyoshiMaster"
	import="com.pckeiba.entity.JvdBanushiMaster"
	import="com.pckeiba.entity.JvdSeisanshaMaster"
	import="com.pckeiba.entity.JvdKyosobaSeiseki"

	import="com.racing.model.convert.PckeibaConvert"
	import="com.pckeiba.enumutil.*"
	%>
<%
	String kettoTorokuBango = request.getParameter("kettoBango");
	UmagotoShosaiJoho netKeiba = (UmagotoShosaiJoho) request.getAttribute("netKeiba");
	List<UmaDataView> kakoData = KakoUmagotoRaceJoho.getUmaKakoData(kettoTorokuBango);
	JvdKyosobaMaster kyosobaMaster = KyosobaMaster.getKyosobaMaster(kettoTorokuBango);
	JvdChokyoshiMaster chokyoshiMaster = ChokyoshiMaster.getChokyoshiMaster(kyosobaMaster.getChokyoshiCode());
 	JvdBanushiMaster	banushiMaster = BanushiMaster.getBanushiMaster(kyosobaMaster.getBanushiCode());
	JvdSeisanshaMaster seisanshaMaster = SeisanshaMaster.getSeisanshaMaster(kyosobaMaster.getSeisanshaCode());
	JvdKyosobaSeiseki seiseki = KyosobaSeiseki.getKyosobaSeiseki(kettoTorokuBango);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css" rel="stylesheet" />
<link href="/JapanHorseRacingBuilder/css/danceTableGraph.css" rel="stylesheet">
<link rel="shortcut icon" href="/JapanHorseRacingBuilder/icon/kyosoba_3.ico">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/JapanHorseRacingBuilder/js/pop.js"></script>
<title>ここにページタイトルを入力</title>
</head>
<body id="umagoto">

<!-- ヘッダー	********************************************************* -->

	<header id="title">
		<a href="/JapanHorseRacingBuilder/Index"><img class="topIcon" alt="競走馬" src="/JapanHorseRacingBuilder/picture/topIcon.png" title="トップページのアイコン"></a> <a href="/JapanHorseRacingBuilder/Index"><span
			class="title">うまポス！</span></a> <a href="https://twitter.com/search?q=%23<%out.print("ここに検索キーワード");%>&src=typd&lang=ja" target="_blank"><img class="twitter" alt="twitter"
			src="/JapanHorseRacingBuilder/picture/twitter.png"></a>
	</header>

<!-- タイトル	********************************************************* -->

	<div class="title">
		<h1><%=kyosobaMaster.getBamei() %></h1>
		<span><%=CodeConvert.valueOf(SeibetsuCode.class, kyosobaMaster.getSeibetsuCode()).getContentIsShort()%><%=PckeibaConvert.getNenrei(kyosobaMaster.getSeinengappi()) %></span>
	</div>

<!-- トップ画像	********************************************************* -->

	<img alt="horseArt" src="<%=netKeiba.getMainPhotoUrl()%>">

<!-- 馬情報	********************************************************* -->

	<div class="umaJoho">
		<table>
			<tr>
				<th>生年月日</th>
				<td><%=PckeibaConvert.getDateString(kyosobaMaster.getSeinengappi()) %>
			</tr>
			<tr>
				<th>調教師</th>
				<td><%=chokyoshiMaster.getChokyoshimei().replace("　", "") %></td>
			</tr>
			<tr>
				<th>馬主</th>
				<td><%=banushiMaster.getBanushimeiHojinkakuNashi().replace("　", "") %></td>
			</tr>
			<tr>
				<th>生産者</th>
				<td><%=seisanshaMaster.getSeisanshameiHojinkakuNashi().replace("　", "") %>
			</td>
			<tr>
				<th>産地</th>
				<td><%=kyosobaMaster.getSanchimei() %>
			</tr>
			<tr>
				<th>獲得賞金</th>
				<td><%=seiseki.getHeichiHonshokinRuikei()/100 %>万円</td>
			</tr>
			<tr>
				<th>通算成績</th>
				<td><%=seiseki.getTorokuRaceSu() %>戦<%=seiseki.getChuoGokei1chaku() %>勝</td>
			</tr>
		</table>
	</div>

<!-- 競走結果	********************************************************* -->

	<div class="raceResult">

	</div>

</body>
</html>