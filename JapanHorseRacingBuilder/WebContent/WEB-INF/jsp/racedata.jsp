<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"

	import="java.util.List"
	import="java.time.LocalDateTime"
	import="java.math.BigDecimal"
	import="java.util.Date"
	import="java.time.LocalDate"
	import="java.time.LocalDateTime"
	import="java.time.format.DateTimeFormatter"

    import="com.pckeiba.entity.JvdRaceShosai"
    import="com.pckeiba.entity.JvdUmagotoRaceJoho"
    import="com.pckeiba.datamodel.RaceData"
    import="com.pckeiba.datamodel.HorseData"
    import="com.racing.model.Race"
    import="com.racing.model.Horse"
    import="com.racing.model.RaceShosai"
    import="com.racing.model.UmagotoRaceJoho"
    import="com.racing.model.KakoUmagotoRaceJoho"
    import="com.example.entity.UmaDataView"
    import="com.racing.model.convert.*"
    import="com.pckeiba.enumutil.*"
    import="java.time.ZoneId"
    %>
    <%
    String shubetsu = request.getParameter("shubetsu");
    Race race = (Race)request.getAttribute("raceData");
    Horse horse = (Horse)request.getAttribute("umagoto");
    KakoUmagotoRaceJoho kakoRace = (KakoUmagotoRaceJoho)request.getAttribute("kakoRace");
    JvdRaceShosai raceData = (JvdRaceShosai)race.getList().get(0);
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
     <%
     LocalDate kaisai_Nengappi = raceData.getKaisaiNengappi().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
     LocalDateTime hasso_Jikoku = raceData.getHassoJikoku().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
     //レースデータで使用する変数
     String kaisaiNengappi = DateTimeFormatter.ofPattern("yyyy年MM月dd日 (E)").format(kaisai_Nengappi);
     String yobiCode;
     String keibajoCode;
     Short raceBango;
     Short jushoKaiji;
     String kyosomeiHondai;
     Short kyori;
     String trackCode;
     String hassoJikoku = DateTimeFormatter.ofPattern("HH:mm").format(hasso_Jikoku);
     String kyosoJokenMeisho;
     String kyosoShubetsuCode;
     String kyosoKigoCode;
     String juryoShubetsuCode;
     Short torokuTosu;
     %>

	<div id="title">
			<div id="logo">
				<!-- <img src="../picture/logo.jpg" alt="トップページへのリンク" class="logo"> -->
				<img src="/JapanHorseRacingBuilder/picture/logo.jpg" alt="トップページへのリンク"
					class="logo"> <span class="title">Jockeys->Link</span>
			</div>

		<div id="roundData">
			<span class="kaisai">
				<%
					out.print(kaisaiNengappi);
				%>
			</span> <span class="keibajo">
				<%
					out.print(CodeConvert.valueOf(KeibajoCode.class, raceData.getKeibajoCode()).getContent());
				%>
			</span> <span class="round">
				<%
					out.print(raceData.getRaceBango() + "R");
				%>
			</span>
		</div>
		<div id="kyosomei">
			<%
				String jusho_Kaiji = raceData.getJushoKaiji() == 0 ? "" : "第" + raceData.getJushoKaiji() + "回";
			%>
			<span class="kaiji">
				<%
					out.print(jusho_Kaiji);
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
							out.print(CodeConvert.valueOf(TrackCode.class, raceData.getTrackCode()).getContent());
						%>
					</span>
					-
					<span>
						<%
							out.print(hassoJikoku);
						%>
					</span>
				</div>
				<div class="raceData desctop">
					<span>
						<%
							out.println(CodeConvert.valueOf(KyosoJokenCode.class, raceData.getKyosoJokenCodeSaijakunen()).getContent());
						%>
					</span> <span>
						<%
							out.println(CodeConvert.valueOf(KyosoShubetsuCode.class, raceData.getKyosoShubetsuCode()).getContent());
						%>
					</span> <span>
						<%
							out.println(CodeConvert.valueOf(KyosoKigoCode.class, raceData.getKyosoKigoCode()).getContent());
						%>
					</span> <span>
						<%
							out.print(CodeConvert.valueOf(JuryoShubetsuCode.class, raceData.getJuryoShubetsuCode()).getContent());
						%>
					</span> <span>
						<%
							out.print(raceData.getTorokuTosu() + "頭");
						%>
					</span>
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
			<th>枠番</th>
			<th>馬番</th>
			<th>馬名</th>
			<th>騎手</th>
			<th>斤量</th>
			<th>1走前</th>
			<th>2走前</th>
			<th>3走前</th>
			<th>4走前</th>
		</tr>
		<%
		for(HorseData dataModel : horseData){
			JvdUmagotoRaceJoho data = (JvdUmagotoRaceJoho) dataModel;
			//現出馬表のローカル変数
			String wakuban;
			String umaban;
			String bamei;
			String kishumeiRyakusho;
			BigDecimal futanJuryo;
		%>
		<tr>
			<td><% out.print(data.getWakuban()); %></td>
			<td><% out.print(data.getUmaban()); %></td>
			<td><% out.print(data.getBamei()); %></td>
			<td><% out.print(data.getKishumeiRyakusho()); %></td>
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
			<%
							if(view.getIjoKubunCode().equals("0")){
			%>
							<span><% out.print(new SrunConverter(view.getSrun55(), view.getFutanJuryo()).getConvertSrun()); %></span>
							<p>
							<span><% out.print(view.getTsumeashi()); %></span>
							<p>
							<span><% out.print(view.getKohan3fchakusa()); %></span>
							<span><% out.print(plus); %></span>
			<%
							}else{
			%>
							<span><%out.print(CodeConvert.valueOf(IjoKubunCode.class, view.getIjoKubunCode()).getContent()); %></span>
			<%
							}
			%>
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