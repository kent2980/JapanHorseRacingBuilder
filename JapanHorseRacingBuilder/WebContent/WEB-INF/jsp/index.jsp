<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"

	import="java.util.List"
	import="java.util.stream.Collectors"
	import="java.util.Date"

	import="java.text.SimpleDateFormat"
	import="java.time.LocalDateTime"
	import="java.time.LocalDate"
	import="java.time.format.DateTimeFormatter"
	import="java.time.ZoneId"
	import="com.google.common.base.Function"
	import="com.google.common.collect.Lists"

	import="com.pckeiba.entity.JvdRaceShosai"
	import="com.pckeiba.entity.JvdTokubetsuTorokuba"
	import="com.pckeiba.entity.JvdKaisaiSchedule"

	import="jhrb.sql.access.RaceShosai"
	import="jhrb.sql.access.TokubetsuTorokuba"
	import="jhrb.sql.access.KaisaiSchedule"
	import="jhrb.sql.access.SelectYearRaceShosai"
	import="jhrb.sql.convert.PckeibaConvert"

	import="com.pckeiba.datamodel.RaceData"
	import="com.pckeiba.enumutil.*"
	import="com.racing.model.convert.*"

	import="java.time.LocalDate"

%>
<%
	TokubetsuTorokuba torokuba = (TokubetsuTorokuba) request.getAttribute("torokuba");
	KaisaiSchedule schedule = (KaisaiSchedule) request.getAttribute("schedule");
	SelectYearRaceShosai selectRaceShosai = (SelectYearRaceShosai) request.getAttribute("selectRaceShosai");
	List<JvdTokubetsuTorokuba> torokuList = torokuba.getList();
	List<JvdKaisaiSchedule> scheduleList = schedule.getList();
	List<JvdRaceShosai> raceShosaiList = selectRaceShosai.getList();
	String date = request.getParameter("date");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date_ = null;
	try{
		date_ = sdf.parse(date);
	}catch(NullPointerException e){
		date_ = new Date();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css"
	rel="stylesheet" />
<link href="/JapanHorseRacingBuilder/css/danceTableGraph.css" rel="stylesheet">
<link href="/JapanHorseRacingBuilder/css/timeline.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.1.0/css/drawer.min.css">
<link rel="shortcut icon" href="/JapanHorseRacingBuilder/icon/kyosoba_3.ico">
<title>うまポス！ | レース一覧 【<%=sdf.format(date_) %>】</title>
</head>
<body id="index" class="drawer drawer--left">

<header id="title">
	<a href="/JapanHorseRacingBuilder/Index"><img class="topIcon" alt="競走馬" src="/JapanHorseRacingBuilder/picture/topIcon.png" title="トップページのアイコン"></a>
	<a href="/JapanHorseRacingBuilder/Index"><span class="title">うまポス！</span></a>
</header>

<!-- ******* ドロワーメニューここから ******* -->
<header role="banner">
		<button type="button" class="drawer-toggle drawer-hamburger">
			<span class="sr-only">toggle navigation</span>
			<span class="drawer-hamburger-icon"></span>
			<span class="jushoMenu">重賞スケジュール</span>
		</button>
		<nav class="drawer-nav" role="navigation">
			<ul class="drawer-menu">
				<%for(int i = 1; i <= 12; i++){ %>
				<%List<JvdKaisaiSchedule> selectSchedule = schedule.getSelectList(i);%>
				<%if(selectSchedule.size() > 0){ %>
				<li><a class="drawer-brand" href="#"><%=i %>月</a></li>
				<%for(JvdKaisaiSchedule data: selectSchedule){
				String drawerDate = sdf.format(data.getKaisaiNengappi());%>
				<li><a class="drawer-menu-item" href="/JapanHorseRacingBuilder/Index?date=<%=drawerDate %>"><%=data.getJusho1KyosomeiRyakusho10() %></a></li>
				<%}}} %>
			</ul>
		</nav>
</header>
<main role="main">
		<!-- コンテンツ -->
		<span>aaaacvevcdsv</span>
</main>
<!-- ******* ドロワーメニューここまで ******* -->

<%-- ******* <ここから左サイドメニュー> ******* --%>

<div class="leftMenu">
	<ul>
		<li><a href="/JapanHorseRacingBuilder/Index?date=2019-01-01">2019</a></li>
		<li><a href="/JapanHorseRacingBuilder/Index?date=2018-01-01">2018</a></li>
		<li><a href="/JapanHorseRacingBuilder/Index?date=2017-01-01">2017</a></li>
		<li><a href="/JapanHorseRacingBuilder/Index?date=2016-01-01">2016</a></li>
		<li><a href="/JapanHorseRacingBuilder/Index?date=2015-01-01">2015</a></li>
	</ul>
</div>

<%-- ******* <ここまで左サイドメニュー> ******* --%>

<!-- ************ <ここからタイムラインを表示> **************** -->

<div class="timeline-container timeline-theme-1">
  <div class="timeline js-timeline">
  	<%
  	List<Date> kaisaiList = schedule.getKaisaiNenGappiList();
  	for(int i = 0; i < kaisaiList.size(); i++){
  		//開催日を整形します
  		LocalDate kaisaiDate = kaisaiList.get(i).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();	//LocalDateに変換
  		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd(E)");	//Formatterを作製
  		String kaisaiDate_ = kaisaiDate.format(dtf);	//整形文字列を生成します
  		boolean raceShosaiFrag = true;
  		List<String> keibajoCodeList = selectRaceShosai.getKeibajoCodeList(kaisaiList.get(i));
  		List<JvdRaceShosai> kaisaiRaceList = null;
  		try{
  			kaisaiRaceList = selectRaceShosai.getRaceShosai(kaisaiList.get(i));
  		}catch(NullPointerException e){
  			raceShosaiFrag = false;
  		}
  	%>
    	<div data-time="<%=kaisaiDate_ %>">
    	<!-- ここに出馬表を記述してください。 -->
    		<%if(raceShosaiFrag == true){
    			for(int u = 0; u < keibajoCodeList.size(); u++){%>
    		<h1><span><%=CodeConvert.valueOf(KeibajoCode.class, keibajoCodeList.get(u)).getContent() %></span></h1>	<!-- 競馬場コードを変換 -->
    		<table id="raceShosai">
    			<%
    			kaisaiRaceList = selectRaceShosai.getRaceShosai(kaisaiList.get(i), keibajoCodeList.get(u));
    			/******* --> 1レース～12レースまでの繰り返し処理 *******/
   				for(int t = 0; t < kaisaiRaceList.size(); t++){
    			%>
   				<tr>
    			<%
    			kaisaiRaceList = selectRaceShosai.getRaceShosai(kaisaiList.get(i), keibajoCodeList.get(u));		//競馬場ごとの開催レース詳細のリスト[List<JvdRaceShosai>]
    			JvdRaceShosai raceData = kaisaiRaceList.get(t);	//xレースのレース詳細オブジェクト[JvdRaceShosai]
    			String kyosomei = PckeibaConvert.KyosomeiConvert(raceData.getKyosomeiHondai(), raceData.getKyosoShubetsuCode(), raceData.getKyosoJokenCodeSaijakunen());
    			sdf = new SimpleDateFormat("HH:mm");	//発走時刻のSimpleDateFormat
    			String hassoJikoku = sdf.format(raceData.getHassoJikoku());	//[HH:mm]形式の発走時刻
    			String baba = CodeConvert.valueOf(TrackCode.class, raceData.getTrackCode()).getBaba();	//馬場
    			String kyosoJoken = CodeConvert.valueOf(KyosoJokenCode.class, raceData.getKyosoJokenCodeSaijakunen()).getContent().replace("以下", "下");		//競争条件
    			String kyosoShubetsu = CodeConvert.valueOf(KyosoShubetsuCode.class, raceData.getKyosoShubetsuCode()).getContent();		//競争種別
    			String juryoShubetsu = CodeConvert.valueOf(JuryoShubetsuCode.class, raceData.getJuryoShubetsuCode()).getContent();		//重量種別
    			String kyosoKigo = CodeConvert.valueOf(KyosoKigoCode.class, raceData.getKyosoKigoCode()).getContent();					//競争記号
    			String raceShosai = juryoShubetsu + "・" + kyosoShubetsu + kyosoJoken + kyosoKigo + " " + raceData.getTorokuTosu() + "頭";	//レース詳細
    			String raceGrade = CodeConvert.valueOf(GradeCode.class, raceData.getGradeCode()).getContent();	//レースグレード
    			%>
   				<th class="raceBango"><span><%=raceData.getRaceBango() %>R</span></th>	<!-- レース番号セル -->
    			<td class="jikoku"><%=hassoJikoku %></td>	<!-- 発送時刻 -->
    			<td class="grade"><%if(!raceData.getGradeCode().equals("D") & !raceData.getGradeCode().equals("E")){ %><%=raceGrade %><%} %></td>		<!-- レースグレード -->
    			<td class="kyosomei">	<!-- 競争名セル -->
    				<a href="racedata?racecode=<%=raceData.getRaceCode() %>&shubetsu=DANCE">	<!-- レース出馬表ページのリンク -->
    					<%=kyosomei %>		<!-- 競争名 -->
    				</a>
    			</td>
    			<td class="baba"><%=baba %></td>								<!-- 馬場 -->
    			<td class="kyori"><%=raceData.getKyori() %>m</td>				<!-- 距離 -->
    			<td class="raceShosai"><%=raceShosai %></td>					<!-- レース詳細 -->
    			<td class="dance">												<!-- 出馬表 -->
    				<a href="racedata?racecode=<%=raceData.getRaceCode() %>&shubetsu=DANCE">
    				<span class="dance">出馬表</span>
    				</a>
    			</td>
    			<td class="record">												<!-- レース結果 -->
    				<%int dataKubun = Integer.valueOf(raceData.getDataKubun());
    				if(dataKubun > 0 & dataKubun < 3){ %>
					<span class="noRecord">結果</span>
					<%}else if(dataKubun > 2 & dataKubun < 8){ %>
    				<a href="racedata?racecode=<%=raceData.getRaceCode() %>&shubetsu=RA">
					<span class="record">結果</span>
					</a>
					<%}else if(dataKubun == 9){ %>
					<span class="noRecord">中止</span>
					<%} %>
    			</td>
   				</tr>
    			<%
   				}
    			/******** <-- 1レースから12レースまでの繰り返し処理はここまで *******/
    			%>
       		</table>
			<%
    			}
			}
			%>
		<!-- 出馬表はここまで！ -->
    	</div>
	<%
  	}
	%>
	<div data-time="end"></div>
	<div data-time="end"></div>
  </div>
</div>
<!-- ************ <タイムライン表示ここまで> ***************** -->

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/JapanHorseRacingBuilder/js/timeline.min.js"></script>
<script type="text/javascript" src="/JapanHorseRacingBuilder/js/pop.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/iScroll/5.1.3/iscroll.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.1.0/js/drawer.min.js"></script>
<script>
$('.timeline').Timeline({
    mode: 'horizontal',
    // value: horizontal | vertical, default to horizontal
    itemClass: 'timeline-item',
    // value: item class
    dotsClass: 'timeline-dots',
    // value: dots item class
    activeClass: 'slide-active',
    // value: item and dots active class
    prevClass: 'slide-prev',
    // value: item and dots prev class
    nextClass: 'slide-next',
    // value: item and dots next class
    startItem: '<%=schedule.getKaisaiSelectCount(date_) %>', // first|last|number
    // value: first | last | number , default to first
    dotsPosition: 'top',
    // value: bottom | top, default to bottom
});
$(document).ready(function() {
	 $('.drawer').drawer();
});
</script>

</body>
</html>