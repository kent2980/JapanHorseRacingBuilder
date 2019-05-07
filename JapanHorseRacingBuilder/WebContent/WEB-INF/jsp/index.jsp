<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List" import="java.util.stream.Collectors" import="java.time.LocalDateTime"
	import="java.time.format.DateTimeFormatter" import="java.time.ZoneId" import="com.google.common.base.Function" import="com.google.common.collect.Lists" import="com.pckeiba.entity.JvdRaceShosai"
	import="com.pckeiba.entity.JvdTokubetsuTorokuba" import="com.racing.model.RaceShosai" import="com.racing.model.TokubetsuTorokuba" import="com.pckeiba.datamodel.RaceData"
	import="com.pckeiba.enumutil.*" import="com.racing.model.convert.*" import="java.time.LocalDate"  import="com.racing.model.convert.PckeibaConvert"%>
<%
	RaceShosai raceShosai = (RaceShosai) request.getAttribute("raceShosai");
	TokubetsuTorokuba torokuba = (TokubetsuTorokuba) request.getAttribute("torokuba");
	List<JvdRaceShosai> raceList = Lists.transform(raceShosai.getList(),
			new Function<RaceData, JvdRaceShosai>() {
				@Override
				public JvdRaceShosai apply(RaceData arg0) {
					return (JvdRaceShosai) arg0;
				}
			});
	List<JvdTokubetsuTorokuba> torokuList = Lists.transform(torokuba.getList(),
			new Function<RaceData, JvdTokubetsuTorokuba>() {
				@Override
				public JvdTokubetsuTorokuba apply(RaceData arg0) {
					return (JvdTokubetsuTorokuba) arg0;
				}
			});
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css"
	rel="stylesheet" />
<link href="/JapanHorseRacingBuilder/css/danceTableGraph.css" rel="stylesheet">
<link rel="shortcut icon" href="/JapanHorseRacingBuilder/icon/kyosoba_3.ico">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/JapanHorseRacingBuilder/js/pop.js"></script>
<title>Insert title here</title>
</head>
<body id="index">
	<button class="torokubaButton"><span>特別登録</span></button>
	<div id="nowRacing">
		<h1>本日のレース</h1>
		<%
			try {
				if (raceList.size() == 0) {
					throw new IndexOutOfBoundsException();
				}
				List<String> keibajoCode = raceList.stream().map(s -> s.getKeibajoCode()).distinct()
						.collect(Collectors.toList());
		%>
		<div>

<!-- *******************************************************************
--		第1競馬場テーブル		********************************************
********************************************************************* -->
			<h2><%out.print(CodeConvert.valueOf(KeibajoCode.class, keibajoCode.get(0)).getContent()); %></h2>
			<table>
				<tr>
					<th>R</th>
					<th>時間</th>
					<th>レース名</th>
					<th>競争条件</th>
				</tr>
				<%
					List<JvdRaceShosai> raceDataList = raceList.stream()
								.filter(s -> s.getKeibajoCode().equals(keibajoCode.get(0))).collect(Collectors.toList());
						for (JvdRaceShosai data : raceDataList) {
							LocalDateTime hassoJikoku = data.getHassoJikoku().toInstant().atZone(ZoneId.systemDefault())
									.toLocalDateTime();
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
							String kyosomei = PckeibaConvert.KyosomeiConvert(data.getKyosomeiHondai(), data.getKyosoShubetsuCode(), data.getKyosoJokenCodeSaijakunen());
				%>
				<tr>
					<td>
						<%
							out.print(data.getRaceBango() + "R");
						%>
					</td>
					<td>
						<%
							out.print(dtf.format(hassoJikoku));
						%>
					</td>
					<td>
						<a href="racedata?racecode=<%out.print(data.getRaceCode()); %>&shubetsu=RA">
							<%
								out.print(kyosomei);
							%>
						</a>
					</td>
					<td>
						<%
							out.print(CodeConvert.valueOf(TrackCode.class, data.getTrackCode()).getBaba() + " ");
							out.print(data.getKyori() + "m ");
							out.print(data.getTorokuTosu() + "頭");
						%>
					</td>
				</tr>
				<%
					}

						if (keibajoCode.size() > 1) {
				%>
			</table>

<!-- *******************************************************************
--		第2競馬場テーブル		********************************************
********************************************************************* -->
			<h2><%out.print(CodeConvert.valueOf(KeibajoCode.class, keibajoCode.get(1)).getContent()); %></h2>
			<table>
				<tr>
					<th>R</th>
					<th>時間</th>
					<th>レース名</th>
					<th>競争条件</th>
				</tr>
				<%
					raceDataList = raceList.stream().filter(s -> s.getKeibajoCode().equals(keibajoCode.get(1)))
									.collect(Collectors.toList());
							for (JvdRaceShosai data : raceDataList) {
								LocalDateTime hassoJikoku = data.getHassoJikoku().toInstant().atZone(ZoneId.systemDefault())
										.toLocalDateTime();
								DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
								String kyosomei = PckeibaConvert.KyosomeiConvert(data.getKyosomeiHondai(), data.getKyosoShubetsuCode(), data.getKyosoJokenCodeSaijakunen());
				%>
				<tr>
					<td>
						<%
							out.print(data.getRaceBango() + "R");
						%>
					</td>
					<td>
						<%
							out.print(dtf.format(hassoJikoku));
						%>
					</td>
					<td>
						<a href="racedata?racecode=<%out.print(data.getRaceCode()); %>&shubetsu=RA">
							<%
								out.print(kyosomei);
							%>
						</a>
					</td>
					<td>
						<%
							out.print(CodeConvert.valueOf(TrackCode.class, data.getTrackCode()).getBaba() + " ");
							out.print(data.getKyori() + "m ");
							out.print(data.getTorokuTosu() + "頭");
						%>
					</td>
				</tr>
				<%
					}
				%>
			</table>
			<%
				}
					if (keibajoCode.size() > 2) {
			%>

<!-- *******************************************************************
--		第3競馬場テーブル		********************************************
********************************************************************* -->
			<h2><%out.print(CodeConvert.valueOf(KeibajoCode.class, keibajoCode.get(2)).getContent()); %></h2>
			<table>
				<tr>
					<th>R</th>
					<th>時間</th>
					<th>レース名</th>
					<th>競争条件</th>
				</tr>
				<%
					raceDataList = raceList.stream().filter(s -> s.getKeibajoCode().equals(keibajoCode.get(2)))
									.collect(Collectors.toList());
							for (JvdRaceShosai data : raceDataList) {
								LocalDateTime hassoJikoku = data.getHassoJikoku().toInstant().atZone(ZoneId.systemDefault())
										.toLocalDateTime();
								DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
								String kyosomei = PckeibaConvert.KyosomeiConvert(data.getKyosomeiHondai(), data.getKyosoShubetsuCode(), data.getKyosoJokenCodeSaijakunen());
				%>
				<tr>
					<td>
						<%
							out.print(data.getRaceBango() + "R");
						%>
					</td>
					<td>
						<%
							out.print(dtf.format(hassoJikoku));
						%>
					</td>
					<td>
						<a href="racedata?racecode=<%out.print(data.getRaceCode()); %>&shubetsu=RA">
							<%
								out.print(kyosomei);
							%>
						</a>
					</td>
					<td>
						<%
							out.print(CodeConvert.valueOf(TrackCode.class, data.getTrackCode()).getBaba() + " ");
							out.print(data.getKyori() + "m ");
							out.print(data.getTorokuTosu() + "頭");
						%>
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<%
			}
			} catch (IndexOutOfBoundsException e) {
				out.print("本日開催されているレースはありません");
			}
		%>

	</div>
	<div id="torokuRace">
		<h1>特別登録</h1>
		<table>
			<tr>
				<th>開催日</th>
				<th>競馬場</th>
				<th>レース名</th>
				<th>競争条件</th>
				<th>登録頭数</th>
			</tr>
<%
			for(JvdTokubetsuTorokuba data : torokuList){

				LocalDate kaisaiNengappi = data.getKaisaiNengappi().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd (E)");
%>
			<tr>
				<td><%out.print(dtf.format(kaisaiNengappi)); %></td>
				<td><%out.print(CodeConvert.valueOf(KeibajoCode.class, data.getKeibajoCode()).getContent()); %></td>
				<td><a href="racedata?racecode=<%out.print(data.getRaceCode()); %>&shubetsu=TK"><%out.print(data.getKyosomeiHondai()); %></a></td>
				<td>
						<%
							out.print(CodeConvert.valueOf(TrackCode.class, data.getTrackCode()).getBaba() + " ");
							out.print(data.getKyori() + "m ");
						%>
				</td>
				<td><%out.print(data.getTorokuTosu() + "頭"); %></td>
			</tr>
<%
			}
%>
		</table>
	</div>
</body>
</html>