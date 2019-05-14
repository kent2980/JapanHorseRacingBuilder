package com.racing.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.access.PckeibaSqlSessionFactory;
import com.database.access.PckeibalinkSqlSessionFactory;
import com.racing.model.Horse;
import com.racing.model.Race;
import com.racing.model.pckeiba.KakoUmagotoRaceJoho;
import com.racing.model.pckeiba.KishuMaster;
import com.racing.model.pckeiba.KyosobaMaster;
import com.racing.model.pckeiba.RaceShosai;
import com.racing.model.pckeiba.TokubetsuTorokuba;
import com.racing.model.pckeiba.TorokubagotoJoho;
import com.racing.model.pckeiba.UmagotoRaceJoho;

/**
 * Servlet implementation class RaceDataServlet
 */
@WebServlet("/racedata")
public class RaceDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TokubetsuTorokuba torokuba;
	private RaceShosai raceShosai;
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
		//MySQLのセッションを確認します
		PckeibaSqlSessionFactory.openSession();
		PckeibalinkSqlSessionFactory.openSession();

		String raceCode = request.getParameter("racecode");
		shubetsu = request.getParameter("shubetsu");

		switch(shubetsu) {
		case "TK":
			torokuba = new TokubetsuTorokuba(raceCode);
			horseData = new TorokubagotoJoho(raceCode);
			System.out.println(torokuba.getList().size());
			request.setAttribute("torokuba", torokuba);
			break;
		default:
			raceShosai = new RaceShosai(raceCode);
			UmagotoRaceJoho umagotoJoho = new UmagotoRaceJoho(raceCode);
			horseData = umagotoJoho;
			KishuMaster kishuMaster = new KishuMaster(umagotoJoho.getKishuList());
			request.setAttribute("kishuMaster", kishuMaster);
			request.setAttribute("raceShosai", raceShosai);
		}

		List<String> kettoTorokuBango = horseData.getKettotorokubango();
		kakoRace = new KakoUmagotoRaceJoho(raceCode, kettoTorokuBango);
		kyosobaMaster = new KyosobaMaster(kettoTorokuBango);
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
