<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"

	import="java.util.List"
	import="java.util.ArrayList"
	import="java.time.LocalDateTime"
	import="java.math.BigDecimal"
	import="java.util.Date"
	import="java.time.LocalDate"
	import="java.time.LocalDateTime"
	import="java.time.format.DateTimeFormatter"
	import="java.util.stream.DoubleStream"
	import="java.util.stream.Collectors"
	import="java.util.NoSuchElementException"
	import="java.text.Normalizer"

    import="com.pckeiba.entity.JvdKyosobaMaster"
    import="com.pckeiba.entity.JvdRaceShosai"
    import="com.pckeiba.entity.JvdTokubetsuTorokuba"
    import="com.pckeiba.entity.JvdTorokubagotoJoho"

    import="com.racing.model.pckeiba.RaceShosai"
    import="com.racing.model.pckeiba.KyosobaMaster"
    import="com.racing.model.pckeiba.TokubetsuTorokuba"
    import="com.racing.model.pckeiba.TorokubagotoJoho"
    import="com.racing.model.pckeiba.KakoUmagotoRaceJoho"

    import="com.example.entity.UmaDataView"
    import="com.pckeiba.datamodel.RaceData"
    import="com.pckeiba.datamodel.HorseData"
    import="com.racing.model.Race"
    import="com.racing.model.Horse"
    import="com.web.load.HtmlDownload"

    import="com.example.entity.UmaDataView"
    import="com.racing.model.convert.*"
    import="com.pckeiba.enumutil.*"
    import="java.time.ZoneId"
    %>
    <%
    //変数を宣言します
    String shubetsu = request.getParameter("shubetsu");
    TokubetsuTorokuba race = (TokubetsuTorokuba)request.getAttribute("torokuba");
    TorokubagotoJoho horse = (TorokubagotoJoho)request.getAttribute("umagoto");
    KakoUmagotoRaceJoho kakoRace = (KakoUmagotoRaceJoho)request.getAttribute("kakoRace");
    KyosobaMaster kyosobaMaster = (KyosobaMaster)request.getAttribute("kyosobaMaster");
    JvdTokubetsuTorokuba raceData = race.getTokubetsuTorokuba();
    List<HorseData> horseData = horse.getList();
    List<UmaDataView> kakoList = kakoRace.getList();
    List<JvdKyosobaMaster> kyosobaMasterList = kyosobaMaster.getList();
    %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css"
	rel="stylesheet" />
<link href="/JapanHorseRacingBuilder/css/danceTableGraph.css" rel="stylesheet">
<link rel="shortcut icon" href="/JapanHorseRacingBuilder/icon/kyosoba_3.ico">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/JapanHorseRacingBuilder/js/pop.js"></script>
<title>
	<%LocalDate kaisai_Nengappi = raceData.getKaisaiNengappi().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	  String kaisaiNengappi = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(kaisai_Nengappi);%>
	 <%out.print(CodeConvert.valueOf(KeibajoCode.class, raceData.getKeibajoCode()).getContent() + raceData.getRaceBango() + "R "); %>
	<%out.print(PckeibaConvert.KyosomeiConvert(raceData.getKyosomeiHondai(),raceData.getKyosoShubetsuCode(), raceData.getKyosoJokenCodeSaijakunen()));%>
	出馬表 | <%out.print(kaisaiNengappi); %>
</title>
</head>
<body id="JHRdance">

	<!-- *****************************************************************************************
     *****************************************************************************************
     							レースデータを記述します
     *****************************************************************************************
     ***************************************************************************************** -->
     <%
     //レースデータで使用する変数
     kaisaiNengappi = DateTimeFormatter.ofPattern("yyyy年MM月dd日 (E)").format(kaisai_Nengappi);
     String yobiCode;
     String keibajoCode;
     Short raceBango;
     Short jushoKaiji;
     String kyosomeiHondai = PckeibaConvert.KyosomeiConvert(raceData.getKyosomeiRyakusho10(), raceData.getKyosoShubetsuCode(), raceData.getKyosoJokenCodeSaijakunen());
     Short kyori;
     String trackCode;
     String kyosoJokenMeisho;
     String kyosoShubetsuCode;
     String kyosoKigoCode;
     String juryoShubetsuCode;
     Short torokuTosu;
     //最新ニュース
     List<String> news = null;
     try{
         String url = "https://news.yahoo.co.jp/search/?ei=UTF-8&p=" + kyosomeiHondai + "&fr=crmas";
         HtmlDownload newsLoad = new HtmlDownload();
         news = newsLoad.read(url, "UTF-8");
     }catch(Exception e){
    	 e.printStackTrace();
     }
%>
<header id="title">
	<a href="/JapanHorseRacingBuilder/Index"><img class="topIcon" alt="競走馬" src="/JapanHorseRacingBuilder/picture/topIcon.png" title="トップページのアイコン"></a>
	<a href="/JapanHorseRacingBuilder/Index"><span class="title">うまポス！</span></a>
	<a href="https://twitter.com/search?q=%23<%out.print(kyosomeiHondai); %>&src=typd&lang=ja" target="_blank"><img class="twitter" alt="twitter" src="/JapanHorseRacingBuilder/picture/twitter.png"></a>
</header>

	<!-- URLジャンプのJavaScript -->
	<script type="text/javascript">
		function urlJump() {
			var browser = document.fm.s.value;
			location.href = browser;
		}
	</script>
<div class="contentArea">
<div class="tableTitle">
			<div class="news pc">
				<%
					try {
						int i = 0;
						for (String data : news) {
							if (data.startsWith("<h2 class=\"t\">")) {
								data = data.replace("h2", "span");
								out.print(data);
							} else if (data.startsWith("<span class=\"iS\">")) {
								out.print(data);
								i++;
							}
							if (i > 9) {
								break;
							}
						}
					} catch (NullPointerException e) {
						out.print("<span>最新のニュースはありません</span>");
					}
				%>
			</div>
			<table id="kako4sou">
		<tr>
			<th class="pc bamei" colspan="1">馬名</th>
			<th class="sp sp_horizen bamei" colspan="1">馬名</th>
			<th colspan="2" class="pc kako">1走前</th>
			<th colspan="2" class="pc kako">2走前</th>
			<th colspan="2" class="pc kako">3走前</th>
			<th colspan="2" class="pc kako">4走前</th>
			<th class="sp_horizen kako">1走前</th>
			<th class="sp_horizen kako">2走前</th>
			<th class="sp_horizen kako">3走前</th>
			<th class="sp_horizen kako">4走前</th>
			<th class="drun">All</th>
		</tr>

<!-- ********************************************************************************************************* -->
<!-- ここから出馬表の記述を始めます -->
<!-- ********************************************************************************************************* -->

		<%
			for(HorseData dataModel : horseData){

				/*************************************************************************************/
				/** 現在のレース情報を記述します	**********************************************************/
				/*************************************************************************************/

			JvdTorokubagotoJoho data = (JvdTorokubagotoJoho) dataModel;
			//競走馬マスタ
			JvdKyosobaMaster kyosobaMasterSet = kyosobaMasterList.stream()
													   			 .filter(s -> s.getKettoTorokuBango().equals(data.getKettoTorokuBango()))
													   			 .findFirst().get();
			//現出馬表のローカル変数
			String wakuban;
			String umaban;
			//馬名を９文字で生成します
			String bamei = PckeibaConvert.NameConvert(data.getBamei(),9);
			//性齢を生成します
			String seirei = CodeConvert.valueOf(SeibetsuCode.class, data.getSeibetsuCode()).getContentIsShort();
			//父馬名を９文字で生成します
			String father = PckeibaConvert.NameConvert(kyosobaMasterSet.getKetto1Bamei(),9);
			//母馬名を９文字で生成します
			String mother = PckeibaConvert.NameConvert(kyosobaMasterSet.getKetto2Bamei(),9);
			String chokyoshi = data.getChokyoshimeiRyakusho().replaceAll("　", "");
			String tozaiShozoku = CodeConvert.valueOf(TozaiShozokuCode.class, data.getTozaiShozokuCode()).getContentIsShort();
			BigDecimal futanJuryo;
			//各データ合算用の変数を準備します
			List<Double> srunAll = new ArrayList<>();
			List<Double> tsumeashiAll = new ArrayList<>();
			List<Double> kohan3fChakusaAll = new ArrayList<>();
		%>
		<tr>

		<!-- デスクトップ表示用のHTML -->

			<td class="pc bamei">
				<div class="bamei">
					<span class="bamei"><% out.print(bamei); %></span>
					<span class="seirei"><%out.print(seirei); %></span>
				</div>
				<span class="parent">父：<%out.print(father); %></span>
				<br>
				<span class="parent">母：<%out.print(mother); %></span>
				<br>
				<span class="parent">(<%out.print(kyosobaMasterSet.getKetto5Bamei()); %>)</span>
				<br>
				<span class="chokyoshi"><%out.print(chokyoshi); %>(<%out.print(tozaiShozoku); %>)
				</span>
			</td>

		<!-- モバイル表示用のHTML -->
			<!-- 着順セル-->
			<!-- 馬番 セル-->
			<!-- 馬名セル -->
			<td class="sp sp_horizen bamei">
				<!-- 馬名、性齢 、調教師、所属、騎手、斤量、人気-->
				<div class="bamei">
					<span class="bamei"><%out.print(bamei); %></span>
					<br>
					<span class="seirei"><%out.print(seirei); %></span>
					<br>
					<span class="chokyoshi"><%out.print(chokyoshi); %>(<%out.print(tozaiShozoku); %>)</span>
					<span class="futanJuryo"><%out.print(data.getFutanJuryo()); %></span>
				</div>
			</td>

			<!-- *************************************************************************************** -->
			<!--	ここから過去レースの記述を始めます	******************************************************** -->
			<!-- *************************************************************************************** -->

			<%
			for(int i = 0, t = 0; t < 4; i++){
				try{
					UmaDataView view = kakoList.get(i);
					//	<<過去レースローカル変数>>	******************************************************************

					String kakoKyosomei = PckeibaConvert.KyosomeiConvert(view.getKyosomeiRyakusho6(), view.getKyosoShubetsuCode(), view.getKyosoJokenCodeSaijakunen());
					String kakoResult = view.getIjoKubunCode().equals("0")?view.getKakuteiChakujun() + "着":CodeConvert.valueOf(IjoKubunCode.class, view.getIjoKubunCode()).getContentIsShort();
					String kakoKaisaiNengappi = DateTimeFormatter.ofPattern("yyyy年MM月dd日").format(view.getKaisaiNengappi().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
					String kakoSohaTime = view.getIjoKubunCode().equals("0")?PckeibaConvert.SohaTimeHour(view.getSohaTime()):"";
					String kakoKyosoUrl = "/JapanHorseRacingBuilder/racedata?racecode=" + view.getRaceCode() + "&shubetsu=RA";
					//************************************************************************************
					if(view.getKettoTorokuBango().equals(data.getKettoTorokuBango())){
						t++;
						String plus = null;
						try{
							plus = view.getTsumeashi().subtract(view.getKohan3fchakusa()).compareTo(BigDecimal.ZERO) >= 0 ? "+":"-";
						}catch(NullPointerException e){
							plus = "*";
						}
						//過去走のローカル変数
						String ijoKubun = view.getIjoKubunCode();		/**異常区分**/
						BigDecimal srun = PckeibaConvert.ConvertSrun(view.getSrun55(), data.getFutanJuryo());		/**SRun**/
						BigDecimal tsumeashi = view.getTsumeashi();		/**詰脚**/
						BigDecimal kohan3fChakusa = view.getKohan3fchakusa();		/**後半3F地点着差**/
						String kyoriCompare = null;		//距離比較
						String kyoriCompareClass = null;		//距離比較
						String futanJuryoCompare = null;		//斤量比較
						String futanJuryoCompareClass = null;		//斤量比較

						//現レースと過去レースの距離を比較します
						if(raceData.getKyori() > view.getKyori()){
							kyoriCompare = "↙";
							kyoriCompareClass = "content kyoriCompare chaBlue bold";
						}else if(raceData.getKyori() < view.getKyori()){
							kyoriCompare = "↖";
							kyoriCompareClass = "content kyoriCompare chaRed bold";
						}else{
							kyoriCompare = "-";
							kyoriCompareClass = "content bold";
						}
						//現レースと過去レースの斤量を比較します
						if(data.getFutanJuryo().compareTo(view.getFutanJuryo()) > 0){
							futanJuryoCompare = "↙";
							futanJuryoCompareClass = "content futanJuryoCompare chaBlue bold";
						}else if(data.getFutanJuryo().compareTo(view.getFutanJuryo()) < 0){
							futanJuryoCompare = "↖";
							futanJuryoCompareClass = "content futanJuryoCompare chaRed bold";
						}else{
							futanJuryoCompare = "-";
							futanJuryoCompareClass = "content bold";
						}

						//過去4走の合算値(SRun,詰脚,後半3F地点着差)
						if(srun != null & ijoKubun.equals("0")){
							srunAll.add(srun.doubleValue());
						}
						if(tsumeashi != null & ijoKubun.equals("0")){
							tsumeashiAll.add(tsumeashi.doubleValue());
						}
						if(kohan3fChakusa != null & ijoKubun.equals("0")){
							kohan3fChakusaAll.add(kohan3fChakusa.doubleValue());
						}
						//詰脚-行脚が正の数だったら背景を変化させます
						String srunBack = "";
						try{
							if(tsumeashi.subtract(kohan3fChakusa).compareTo(BigDecimal.ZERO) >= 0){
								srunBack = " redBack";
							}else{
								srunBack = " blueBack";
							}
						}catch(NullPointerException e){
							srunBack = " brackBack";
						}
			%>

				<!-- デスクトップ表示用のHTML -->
						<td class="pc srun<%out.print(srunBack); %>">
			<%
							if(view.getIjoKubunCode().equals("0")){
			%>
							<div class="tsumeashi"><span>詰脚</span></div>
							<div class="content"><span><% out.print(tsumeashi); %></span></div>
							<div class="senko"><span>行脚</span></div>
							<div class="content"><span><% out.print(kohan3fChakusa); %></span></div>
							<div class="kyoriCompare"><span>距離</span></div>
							<div class ="<%out.print(kyoriCompareClass); %>"><span><%out.print(kyoriCompare); %></span></div>
							<div class="futanJuryoCompare"><span>斤量</span></div>
							<div class ="<%out.print(futanJuryoCompareClass); %>"><span><%out.print(futanJuryoCompare); %></span></div>
			<%
							}else{
			%>
							<span>即<br>定<br>不<br>能</span>
			<%
							}

			//SRunの値によってクラスを変化させます
			String srunClass = "";
			try{
				if(srun.compareTo(BigDecimal.valueOf(60)) >= 0){
					srunClass = " redBack";
				}else if(srun.compareTo(BigDecimal.valueOf(55)) >= 0){
					srunClass= " blueBack";
				}else if(srun.compareTo(BigDecimal.valueOf(50)) >= 0){
					srunClass = " yellowBack";
				}else if(srun.compareTo(BigDecimal.valueOf(45)) >= 0){
					srunClass = " greenBack";
				}
			}catch(NullPointerException e){
				srunClass = " brackBack";
			}
			%>
						</td>

				<!-- スマホ横画面表示用のHTML -->
				<%
					String kakoKyosomeiShort = PckeibaConvert.NameConvert(kakoKyosomei, 6);
				%>
							<td class="srun sp_horizen<%out.print(srunBack); %>">
								<a href="<%out.print(kakoKyosoUrl); %>"><span class="kyosomei"><%out.print(kakoKyosomei); %></span></a>
								<span class="chakujun"><%out.print(view.getKakuteiChakujun() + "着"); %></span>
				<%
								if(view.getIjoKubunCode().equals("0")){
				%>				<div class="kyakushitsu">
									<div>
										<span class="content">詰脚</span>
										<span class="content">距離</span>
									</div>
									<div>
										<span class="content"><% out.print(tsumeashi); %></span>
										<span class ="content <%out.print(kyoriCompareClass); %>"><%out.print(kyoriCompare); %></span>
									</div>
									<div>
										<span class="content">行脚</span>
										<span class="content">斤量</span>
									</div>
									<div>
										<span class="content"><% out.print(kohan3fChakusa); %></span>
										<span class ="content <%out.print(futanJuryoCompareClass); %>"><%out.print(futanJuryoCompare); %></span>
									</div>
								</div>
								<div class="srun<%out.print(srunClass); %>">
									<span><%out.print("SRun : " + srun); %></span>
								</div>
				<%
								}else{
				%>
								<span>即<br>定<br>不<br>能</span>
				<%
								}
				%>
							</td>

					<!-- デスクトップ表示用のHTML -->
						<td class="pc kakoRace">
							<span class="kaisaiNengappi"><%out.print(kakoKaisaiNengappi); %></span>
							<br>
							<a href="<%out.print(kakoKyosoUrl); %>"><span class="kyosomei"><%out.print(kakoKyosomei); %></span></a>
							<br>
							<div class="kyosoData">
								<span><%out.print(CodeConvert.valueOf(KeibajoCode.class, view.getKeibajoCode()).getContent()); %></span>
								<span><%out.print(CodeConvert.valueOf(TrackCode.class, view.getTrackCode()).getBaba().substring(0, 1) + "・" + view.getKyori() + "m"); %></span>
								<span><%out.print(CodeConvert.valueOf(TenkoCode.class, view.getTenkoCode()).getContent() + "・" + CodeConvert.valueOf(BabaJotaiCode.class,
										view.getShibaBabajotaiCode().equals("0")?view.getDirtBabajotaiCode():view.getShibaBabajotaiCode()).getContent()); %></span>
								<br>
								<span><%out.print(view.getShussoTosu() + "頭"); %></span>
								<span><%out.print(view.getUmaban() + "番"); %></span>
								<span><%out.print(view.getFutanJuryo()); %></span>
								<span><%out.print(view.getTanshoNinkijun() + "人"); %></span>
								<span><%out.print(CodeConvert.valueOf(KyakushitsuCode.class, view.getKyakushitsuHantei()).getContentIsShort()); %></span>
							</div>
							<div class="resultData">
								<span><%out.print(kakoSohaTime); %> (<%out.print(view.getKohan3f().compareTo(BigDecimal.ZERO)==0?"-":String.valueOf(view.getKohan3f())); %>)</span>
								<span class="result"><%out.print(kakoResult); %></span>
								<br>
								<div class="srun<%out.print(srunClass); %>">
									<span><%out.print("SRun : " + srun); %></span>
								</div>
							</div>
						</td>

					<!-- モバイル表示用のHTML -->
<!--
						<td class="sp kakoRace">
							<span><%//out.print(kakoKyosomei); %></span>
						</td>
  -->
<%						}
				}catch(IndexOutOfBoundsException e){
					switch(t){
					case 0:
						out.print("<td class=\"pc\"></td><td class=\"pc\"></td><td class=\"pc\"></td><td class=\"pc\"></td>");
						out.print("<td class=\"pc\"></td><td class=\"pc\"></td><td class=\"pc\"></td><td class=\"pc\"></td>");
						out.print("<td class=\"sp_horizen\"></td><td class=\"sp_horizen\"></td><td class=\"sp_horizen\"></td><td class=\"sp_horizen\"></td>");
						break;
					case 1:
						out.print("<td class=\"pc\"></td><td class=\"pc\"></td><td class=\"pc\"></td>");
						out.print("<td class=\"pc\"></td><td class=\"pc\"></td><td class=\"pc\"></td>");
						out.print("<td class=\"sp_horizen\"></td><td class=\"sp_horizen\"></td><td class=\"sp_horizen\"></td>");
						break;
					case 2:
						out.print("<td class=\"pc\"></td><td class=\"pc\"></td>");
						out.print("<td class=\"pc\"></td><td class=\"pc\"></td>");
						out.print("<td class=\"sp_horizen\"></td><td class=\"sp_horizen\"></td>");
						break;
					case 3:
						out.print("<td class=\"pc\"></td>");
						out.print("<td class=\"pc\"></td>");
						out.print("<td class=\"sp_horizen\"></td>");
					}
					break;
				}
			}
			BigDecimal drun = null;
			BigDecimal TsumeashiAve = null;
			BigDecimal Kohan3fchakusaAve = null;
			DoubleStream srunStream = srunAll.stream()
										.mapToDouble(s->s.doubleValue());
			DoubleStream TsumeashiStream = tsumeashiAll.stream()
													   .mapToDouble(s->s.doubleValue());
			DoubleStream Kohan3fchakusaStream = kohan3fChakusaAll.stream()
										 						 .mapToDouble(s->s.doubleValue());
			try{
				drun = BigDecimal.valueOf(srunStream.average().getAsDouble()).setScale(2,BigDecimal.ROUND_HALF_UP);
			}catch(NoSuchElementException e){
				drun = null;
			}
			try{
				TsumeashiAve = BigDecimal.valueOf(TsumeashiStream.average().getAsDouble()).setScale(1,BigDecimal.ROUND_HALF_UP);
			}catch(NoSuchElementException e){
				TsumeashiAve = null;
			}
			try{
				Kohan3fchakusaAve = BigDecimal.valueOf(Kohan3fchakusaStream.average().getAsDouble()).setScale(1,BigDecimal.ROUND_HALF_UP);
			}catch(NoSuchElementException e){
				Kohan3fchakusaAve = null;
			}
%>
			<td class="drun">
				<div class="drun"><span>DRun</span></div>
				<div class="content"><span><% out.print(drun); %></span></div>
				<div class="tsumeashi"><span>詰脚</span></div>
				<div class="content"><span><% out.print(TsumeashiAve); %></span></div>
				<div class="senko"><span>行脚</span></div>
				<div class="content"><span><% out.print(Kohan3fchakusaAve); %></span></div>
			</td>
	</tr>
	<%
	}
	%>
</table>
</div>

<!--*****************************	 サイドバーの設置	*********************************** -->

<aside class="pc sidebar">
	<section>
			<div class="racedata">
				<span>
					<%out.print(raceData.getKaisaiKaiji()); %>回<%out.print(raceData.getKaisaiNichiji()); %>日目
					<%out.print(CodeConvert.valueOf(KeibajoCode.class, raceData.getKeibajoCode()).getContent()); %><%out.print(raceData.getRaceBango()); %>R
				</span>
				<h2 class="kyosomei"><%out.print(kyosomeiHondai); %></h2>
				<span class="kyosoJoho">
					<% %>
					<%out.print(CodeConvert.valueOf(KyosoShubetsuCode.class, raceData.getKyosoShubetsuCode()).getContent()); %>
					<%out.print(CodeConvert.valueOf(KyosoJokenCode.class, raceData.getKyosoJokenCodeSaijakunen()).getContent()); %>
				</span>
				<span>
					<% %>
					<%out.print(CodeConvert.valueOf(KyosoKigoCode.class, raceData.getKyosoKigoCode()).getContent()); %>
					<%out.print(CodeConvert.valueOf(JuryoShubetsuCode.class, raceData.getJuryoShubetsuCode()).getContent()); %>
					<%out.print(raceData.getTorokuTosu()); %>頭
				</span>
				<span class="baba">
					<%out.print(CodeConvert.valueOf(TrackCode.class, raceData.getTrackCode()).getBaba()); %> <%out.print(raceData.getKyori()); %>m
				</span>
			</div>
			<ul>
				<li>出馬表</li>
				<li>レース結果</li>
			</ul>
			<ul>
				<li>重賞スケジュール</li>
				<li>開催スケジュール</li>
			</ul>
			<ul>
				<li>本日のレース</li>
				<li>特別登録</li>
			</ul>
	</section>
</aside>

</div>
</body>

</html>