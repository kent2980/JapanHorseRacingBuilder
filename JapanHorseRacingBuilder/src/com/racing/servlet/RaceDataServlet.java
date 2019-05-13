package com.racing.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.racing.model.Horse;
import com.racing.model.KakoUmagotoRaceJoho;
import com.racing.model.KishuMaster;
import com.racing.model.KyosobaMaster;
import com.racing.model.Race;
import com.racing.model.RaceShosai;
import com.racing.model.TokubetsuTorokuba;
import com.racing.model.TorokubagotoJoho;
import com.racing.model.UmagotoRaceJoho;

/**
 * Servlet implementation class RaceDataServlet
 */
@WebServlet("/racedata")
public class RaceDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final SqlSession pckeibaSession = Init.getPckeibaSession();
	private final SqlSession pckeibalinkSession = Init.getPckeibaLinkSession();

	private Race raceData;
	private Horse horseData;
	private KakoUmagotoRaceJoho kakoRace;
	private KyosobaMaster kyosobaMaster;
	private String shubetsu;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RaceDataServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String raceCode = request.getParameter("racecode");
		shubetsu = request.getParameter("shubetsu");

		switch(shubetsu) {
		case "TK":
			raceData = new TokubetsuTorokuba(pckeibaSession, raceCode);
			horseData = new TorokubagotoJoho(pckeibaSession, raceCode);
			break;
		default:
			raceData = new RaceShosai(pckeibaSession, raceCode);
			UmagotoRaceJoho umagotoJoho = new UmagotoRaceJoho(pckeibaSession, raceCode);
			horseData = umagotoJoho;
			KishuMaster kishuMaster = new KishuMaster(pckeibaSession, umagotoJoho.getKishuList());
			request.setAttribute("kishuMaster", kishuMaster);
		}

		List<String> kettoTorokuBango = horseData.getKettotorokubango();
		kakoRace = new KakoUmagotoRaceJoho(pckeibalinkSession, raceCode, kettoTorokuBango);
		kyosobaMaster = new KyosobaMaster(pckeibaSession, kettoTorokuBango);
		request.setAttribute("raceData", raceData);
		request.setAttribute("umagoto", horseData);
		request.setAttribute("kakoRace", kakoRace);
		request.setAttribute("kyosobaMaster", kyosobaMaster);
		RequestDispatcher di = null;

		switch(shubetsu) {
		case "TK":
			di = request.getRequestDispatcher("/WEB-INF/jsp/torokudata.jsp");
			break;
		default:
			di = request.getRequestDispatcher("/WEB-INF/jsp/racedata.jsp");
		}

		di.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

}
