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

    import="com.pckeiba.entity.JvdRaceShosai"
    import="com.pckeiba.entity.JvdUmagotoRaceJoho"
    import="com.pckeiba.entity.JvdKyosobaMaster"
    import="com.pckeiba.entity.JvdKishuMaster"
    import="com.pckeiba.entity.JvdChokyoshiMaster"
    import="com.pckeiba.entity.JvdBanushiMaster"

    import="com.pckeiba.datamodel.RaceData"
    import="com.pckeiba.datamodel.HorseData"

    import="com.racing.model.Race"
    import="com.racing.model.Horse"
    import="com.racing.model.RaceShosai"
    import="com.racing.model.UmagotoRaceJoho"
    import="com.racing.model.KakoUmagotoRaceJoho"
    import="com.racing.model.KyosobaMaster"
    import="com.racing.model.KishuMaster"
    import="com.racing.model.ChokyoshiMaster"
    import="com.racing.model.BanushiMaster"

    import="com.example.entity.UmaDataView"
    import="com.racing.model.convert.*"
    import="com.pckeiba.enumutil.*"
    import="java.time.ZoneId"
    %>
    <%
    //変数を宣言します
    String shubetsu = request.getParameter("shubetsu");
    Race race = (Race)request.getAttribute("raceData");
    UmagotoRaceJoho horse = (UmagotoRaceJoho)request.getAttribute("umagoto");
    KakoUmagotoRaceJoho kakoRace = (KakoUmagotoRaceJoho)request.getAttribute("kakoRace");
    KyosobaMaster kyosobaMaster = (KyosobaMaster)request.getAttribute("kyosobaMaster");
    JvdRaceShosai raceData = (JvdRaceShosai)race.getList().get(0);
    List<HorseData> horseData = horse.getList();
    List<UmaDataView> kakoList = kakoRace.getList();
    List<JvdKyosobaMaster> kyosobaMasterList = kyosobaMaster.getList();
    //騎手マスター
    KishuMaster kishuMaster = (KishuMaster)request.getAttribute("kishuMaster");
    List<JvdKishuMaster> kishuMasterList = kishuMaster.getList();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<%out.print(new KyosomeiConverter(raceData.getKyosomeiHondai(),raceData.getKyosoShubetsuCode(), raceData.getKyosoJokenCodeSaijakunen()).getConvertKyosomei());%>
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
     LocalDateTime hasso_Jikoku = raceData.getHassoJikoku().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
     //レースデータで使用する変数
     kaisaiNengappi = DateTimeFormatter.ofPattern("yyyy年MM月dd日 (E)").format(kaisai_Nengappi);
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

	<div id="pc title">
			<div id="logo">
			<a href="Index?date=<%out.print(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now())); %>">
				<!-- <img src="../picture/logo.jpg" alt="トップページへのリンク" class="logo"> -->
				<img src="/JapanHorseRacingBuilder/picture/logo.jpg" alt="トップページへのリンク"
					class="logo"> <span class="title">Jockeys->Link</span>
			</a>
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
					out.print(new KyosomeiConverter(raceData.getKyosomeiHondai(), raceData.getKyosoShubetsuCode(), raceData.getKyosoJokenCodeSaijakunen()).getConvertKyosomei());
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
			<th class="umaban">馬<p>番</th>
			<th class="bamei">馬名</th>
			<th class="pc kishu">騎手</th>
			<th class="pc odds">人<p>気</th>
			<th colspan="2" class="pc kako">1走前</th>
			<th colspan="2" class="pc kako">2走前</th>
			<th colspan="2" class="pc kako">3走前</th>
			<th colspan="2" class="pc kako">4走前</th>
			<th class="drun">DRun</th>
		</tr>

<!-- ********************************************************************************************************* -->
<!-- ここから出馬表の記述を始めます -->
<!-- ********************************************************************************************************* -->

		<%
		for(HorseData dataModel : horseData){

		/*************************************************************************************/
		/** 現在のレース情報を記述します	**********************************************************/
		/*************************************************************************************/

			JvdUmagotoRaceJoho data = (JvdUmagotoRaceJoho) dataModel;
			//競走馬マスタ
			JvdKyosobaMaster kyosobaMasterSet = kyosobaMasterList.stream()
													   			 .filter(s -> s.getKettoTorokuBango().equals(data.getKettoTorokuBango()))
													   			 .findFirst().get();
			//騎手マスタ
			JvdKishuMaster kishuMasterSet = kishuMasterList.stream()
														   .filter(s -> s.getKishuCode().equals(data.getKishuCode()))
														   .findFirst().get();
			//現出馬表のローカル変数
			String wakuban;
			String umaban;
			//馬名を９文字で生成します
			String bamei = new BameiConverter(data.getBamei()).getBameiConvert();
			//性齢を生成します
			String seirei = CodeConvert.valueOf(SeibetsuCode.class, data.getSeibetsuCode()).getContentIsShort() + data.getBarei();
			//父馬名を９文字で生成します
			String father = new BameiConverter(kyosobaMasterSet.getKetto1Bamei()).getBameiConvert();
			//母馬名を９文字で生成します
			String mother = new BameiConverter(kyosobaMasterSet.getKetto2Bamei()).getBameiConvert();
			String kishumei = Normalizer.normalize(kishuMasterSet.getKishumei().replaceAll("　", ""), Normalizer.Form.NFKC);
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

			<td class="pc waku<%out.print(data.getWakuban()); %> bold"><% out.print(data.getUmaban()); %></td>
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
			<td class="pc kishumei">
				<span class="kishumei"><% out.print(kishumei); %></span>
				<br>
				<span>(<%out.print(CodeConvert.valueOf(TozaiShozokuCode.class, kishuMasterSet.getTozaiShozokuCode()).getContentIsShort()); %>)</span>
				<br>
				<span><%out.print(CodeConvert.valueOf(KishuMinaraiCode.class, data.getKishuMinaraiCode()).getContentKigo() + data.getFutanJuryo() + "kg"); %></span>
			</td>
			<td class="pc">
				<span><% out.print(data.getTanshoNinkijun()); %></span>
				<br>
				<span>(<%out.print(data.getTanshoOdds()); %>)</span>
			</td>

		<!-- モバイル表示用のHTML -->
			<!-- 馬番 セル-->
			<td class="sp waku<%out.print(data.getWakuban()); %> bold"><% out.print(data.getUmaban()); %></td>
			<!-- 馬名セル -->
			<td class="sp mobile bamei">
				<!-- 馬名、性齢 、調教師、所属、騎手、斤量、人気-->
				<div class="bamei">
					<span class="bamei"><%out.print(bamei); %></span>
					<span class="seirei"><%out.print(seirei); %></span>
					<br>
					<span class="chokyoshi"><%out.print(chokyoshi); %>(<%out.print(tozaiShozoku); %>)</span>
					<br>
					<span class="kishumei"><%out.print(kishumei); %></span>
					<span class="futanJuryo"><%out.print(data.getFutanJuryo()); %></span>
					<br>
					<span class="ninki"><%out.print(data.getTanshoNinkijun() + "人気"); %>(<%out.print(data.getTanshoOdds()); %>)</span>
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

					String kakoKyosomei = new KyosomeiConverter(view.getKyosomeiRyakusho6(), view.getKyosoShubetsuCode(), view.getKyosoJokenCodeSaijakunen()).getConvertKyosomei();
					String kakoResult = view.getIjoKubunCode().equals("0")?view.getKakuteiChakujun() + "着":CodeConvert.valueOf(IjoKubunCode.class, view.getIjoKubunCode()).getContentIsShort();
					String kakoKaisaiNengappi = DateTimeFormatter.ofPattern("yyyy年MM月dd日").format(view.getKaisaiNengappi().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
					String kakoSohaTime = view.getIjoKubunCode().equals("0")?new SohaTimeConverter(view.getSohaTime()).getSohaTimeHour():"";
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
						SrunConverter srun = new SrunConverter(view.getSrun55(), data.getFutanJuryo());		/**SRun**/
						BigDecimal tsumeashi = view.getTsumeashi();		/**詰脚**/
						BigDecimal kohan3fChakusa = view.getKohan3fchakusa();		/**後半3F地点着差**/
						String kyoriCompare = null;		//距離比較
						String futanJuryoCompare = null;		//斤量比較

						//現レースと過去レースの距離を比較します
						if(raceData.getKyori() > view.getKyori()){
							kyoriCompare = "<div class=\"content kyoriCompare chaBlue bold\"><span>↙</span></div>";
						}else if(raceData.getKyori() < view.getKyori()){
							kyoriCompare = "<div class=\"content kyoriCompare chaRed bold\"><span>↖</span></div>";
						}else{
							kyoriCompare = "<div class=\"content bold\"><span>-</span></div>";
						}
						//現レースと過去レースの斤量を比較します
						if(data.getFutanJuryo().compareTo(view.getFutanJuryo()) > 0){
							futanJuryoCompare = "<div class=\"content futanJuryoCompare chaBlue bold\"><span>↙</span></div>";
						}else if(data.getFutanJuryo().compareTo(view.getFutanJuryo()) < 0){
							futanJuryoCompare = "<div class=\"content futanJuryoCompare chaRed bold\"><span>↖</span></div>";
						}else{
							futanJuryoCompare = "<div class=\"content bold\"><span>-</span></div>";
						}

						//過去4走の合算値(SRun,詰脚,後半3F地点着差)
						if(srun.getConvertSrun() != null & ijoKubun.equals("0")){
							srunAll.add(srun.getConvertSrun().doubleValue());
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
						<td class="pc srun<%out.print(srunBack); %>">
			<%
							if(view.getIjoKubunCode().equals("0")){
			%>
							<div class="tsumeashi"><span>詰脚</span></div>
							<div class="content"><span><% out.print(tsumeashi); %></span></div>
							<div class="senko"><span>行脚</span></div>
							<div class="content"><span><% out.print(kohan3fChakusa); %></span></div>
							<div class="kyoriCompare"><span>距離</span></div>
							<%out.print(kyoriCompare); %>
							<div class="futanJuryoCompare"><span>斤量</span></div>
							<%out.print(futanJuryoCompare); %>
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
							<span class="kyosomei"><%out.print(kakoKyosomei); %></span>
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
								<%//SRunの値によってクラスを変化させます
								String srunClass = "";
								try{
									if(srun.getConvertSrun().compareTo(BigDecimal.valueOf(60)) >= 0){
										srunClass = " redBack";
									}else if(srun.getConvertSrun().compareTo(BigDecimal.valueOf(55)) >= 0){
										srunClass= " blueBack";
									}else if(srun.getConvertSrun().compareTo(BigDecimal.valueOf(50)) >= 0){
										srunClass = " yellowBack";
									}else if(srun.getConvertSrun().compareTo(BigDecimal.valueOf(45)) >= 0){
										srunClass = " greenBack";
									}
								}catch(NullPointerException e){
									srunClass = " brackBack";
								}
								%>
								<div class="srun<%out.print(srunClass); %>">
									<span><%out.print(srun); %></span>
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
						break;
					case 1:
						out.print("<td class=\"pc\"></td><td class=\"pc\"></td><td class=\"pc\"></td>");
						out.print("<td class=\"pc\"></td><td class=\"pc\"></td><td class=\"pc\"></td>");
						break;
					case 2:
						out.print("<td class=\"pc\"></td><td class=\"pc\"></td>");
						out.print("<td class=\"pc\"></td><td class=\"pc\"></td>");
						break;
					case 3:
						out.print("<td class=\"pc\"></td>");
						out.print("<td class=\"pc\"></td>");
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
</body>
</html>