<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"

	import="java.util.List"
	import="java.math.BigDecimal"

    import="com.pckeiba.entity.JvdRaceShosai"
    import="com.pckeiba.entity.JvdTokubetsuTorokuba"
    import="com.pckeiba.entity.JvdTorokubagotoJoho"
    import="com.racing.model.RaceShosai"
    import="com.racing.model.TokubetsuTorokuba"
    import="com.racing.model.TorokubagotoJoho"
    import="com.racing.model.KakoUmagotoRaceJoho"
    import="com.example.entity.UmaDataView"
    import="com.pckeiba.datamodel.RaceData"
    import="com.pckeiba.datamodel.HorseData"
    import="com.racing.model.Race"
    import="com.racing.model.Horse"
    %>
    <%
    String shubetsu = request.getParameter("shubetsu");
    Race race = (Race)request.getAttribute("raceData");
    Horse horse = (Horse)request.getAttribute("umagoto");
    KakoUmagotoRaceJoho kakoRace = (KakoUmagotoRaceJoho)request.getAttribute("kakoRace");
    JvdTokubetsuTorokuba raceData = (JvdTokubetsuTorokuba)race.getList().get(0);
    List<HorseData> horseData = horse.getList();
    List<UmaDataView> kakoList = kakoRace.getList();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css"
	rel="stylesheet" />
<link href="../css/danceTableGraph.css" rel="stylesheet">
<link href="/JapanHorseRacingBuilder/css/danceTableGraph.css" rel="stylesheet">
<link rel="shortcut icon" href="/JapanHorseRacingBuilder/icon/kyosoba_3.ico">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/pop.js"></script>
<script type="text/javascript" src="/JapanHorseRacingBuilder/js/pop.js"></script>
<title>Insert title here</title>
</head>
<body id="dance">

	<!-- *****************************************************************************************
     *****************************************************************************************
     							レースデータを記述します
     *****************************************************************************************
     ***************************************************************************************** -->
	<div id="title">
			<div id="logo">
				<!-- <img src="../picture/logo.jpg" alt="トップページへのリンク" class="logo"> -->
				<img src="/JapanHorseRacingBuilder/picture/logo.jpg" alt="トップページへのリンク"
					class="logo"> <span class="title">Jockeys->Link</span>
			</div>

		<div id="roundData">
			<span class="kaisai">
				<%
					out.print(raceData.getKaisaiNengappi() + "（" + raceData.getYobiCode() + "）");
				%>
			</span> <span class="keibajo">
				<%
					out.print(raceData.getKeibajoCode());
				%>
			</span> <span class="round">
				<%
					out.print(raceData.getRaceBango() + "R");
				%>
			</span>
		</div>
		<div id="kyosomei">
			<%
				String jushoKaiji = raceData.getJushoKaiji() == 0 ? "" : "第" + raceData.getJushoKaiji() + "回";
			%>
			<span class="kaiji">
				<%
					out.print(jushoKaiji);
				%>
			</span> <span class="kyosomei">
				<%
					out.print(raceData.getKyosomeiHondai());
				%>
			</span>
		</div>
		<div id="raceData">
			<div id="data">
				<div class="courseData desctop">
					<span>
						<%
							out.print(raceData.getKyori() + "m");
						%>
					</span>
					-
					<span>
						<%
							out.print(raceData.getTrackCode());
						%>
					</span>
				</div>
				<div class="raceData desctop">
					<span>
						<%
							out.println(raceData.getKyosoShubetsuCode());
						%>
					</span> <span>
						<%
							out.println(raceData.getKyosoKigoCode());
						%>
					</span> <span>
						<%
							out.print(raceData.getJuryoShubetsuCode());
						%>
					</span> <span>
						<%
							out.print(raceData.getTorokuTosu() + "頭");
						%>
					</span>
					<!--  <span>＜<%//out.print(indexLoad.getDrunMargin(1) + "pt");%>＞</span> -->
				</div>
			</div>
		</div>
	</div>

	<!-- URLジャンプのJavaScript -->
	<script type="text/javascript">
		function urlJump() {
			var browser = document.fm.s.value;
			location.href = browser;
		}
	</script>
<div class="tableTitle">
	<table id="kako4sou">
		<tr>
			<th>馬名</th>
			<th>斤量</th>
			<th>1走前</th>
			<th>2走前</th>
			<th>3走前</th>
			<th>4走前</th>
		</tr>
		<%
		for(HorseData dataModel : horseData){
			JvdTorokubagotoJoho data = (JvdTorokubagotoJoho) dataModel;
		%>
		<tr>
			<td><% out.print(data.getBamei()); %></td>
			<td><% out.print(data.getFutanJuryo()); %></td>
			<%
				int t = 0;
				for(int i = 0; t < 4; i++){
					try{
						UmaDataView view = kakoList.get(i);
						if(view.getKettoTorokuBango().equals(data.getKettoTorokuBango())){
							t++;
							String plus = null;
							try{
								plus = view.getTsumeashi().subtract(view.getKohan3fchakusa()).compareTo(BigDecimal.ZERO) >= 0 ? "+":"-";
							}catch(NullPointerException e){
								plus = "*";
							}
				%>
							<td>
								<span><% out.print(view.getSrun55()); %></span>
								<p>
								<span><% out.print(view.getTsumeashi()); %></span>
								<p>
								<span><% out.print(view.getKohan3fchakusa()); %></span>
								<span><% out.print(plus); %></span>
							</td>
<%						}
					}catch(IndexOutOfBoundsException e){
						break;
					}
				}
%>
		</tr>
		<%
		}
		%>
	</table>
</div>
</body>
</html>