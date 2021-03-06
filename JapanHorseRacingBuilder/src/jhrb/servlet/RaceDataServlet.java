package jhrb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jhrb.sql.access.KakoUmagotoRaceJoho;
import jhrb.sql.access.KishuMaster;
import jhrb.sql.access.KyosobaMaster;
import jhrb.sql.access.RaceShosai;
import jhrb.sql.input.access.TanpukuOdds;
import jhrb.sql.access.TokubetsuTorokuba;
import jhrb.sql.access.TorokubagotoJoho;
import jhrb.sql.input.access.UmagotoRaceJoho;
import jhrb.sql.session.PckeibaSqlSessionFactory;
import jhrb.sql.session.PckeibalinkSqlSessionFactory;

/**
 * Servlet implementation class RaceDataServlet
 */
@WebServlet("/racedata")
public class RaceDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TokubetsuTorokuba tokubetsuToroku;
	private RaceShosai raceShosai;
	private UmagotoRaceJoho horseData;
	private TorokubagotoJoho torokuba;
	private KakoUmagotoRaceJoho kakoRace;
	private KyosobaMaster kyosobaMaster;
	private String shubetsu;
	private List<String> kettoTorokuBango;
	private TanpukuOdds odds;
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
			tokubetsuToroku = new TokubetsuTorokuba(raceCode);
			torokuba = new TorokubagotoJoho(raceCode);
			request.setAttribute("tokubetsuToroku", tokubetsuToroku);
			request.setAttribute("torokuba", torokuba);
			kettoTorokuBango = torokuba.getKettotorokubango();
			break;
		default:
			odds = new TanpukuOdds(raceCode);
			raceShosai = new RaceShosai(raceCode);
			horseData = new UmagotoRaceJoho(raceCode);
			horseData.setDataKubun(raceShosai.getDataKubun());
			KishuMaster kishuMaster = new KishuMaster(horseData.getKishuCodeList());
			request.setAttribute("kishuMaster", kishuMaster);
			request.setAttribute("raceShosai", raceShosai);
			request.setAttribute("umagoto", horseData);
			request.setAttribute("odds", odds);
			kettoTorokuBango = horseData.getKettotorokubango();
		}
		kakoRace = new KakoUmagotoRaceJoho(raceCode, kettoTorokuBango);
		kyosobaMaster = new KyosobaMaster(kettoTorokuBango);
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
