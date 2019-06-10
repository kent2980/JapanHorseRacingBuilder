package jhrb.servlet;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jhrb.sql.access.KaisaiSchedule;
import jhrb.sql.access.SelectYearRaceShosai;
import jhrb.sql.access.TokubetsuTorokuba;
import jhrb.sql.session.PckeibaSqlSessionFactory;

/**
 * Servlet implementation class IndexServlet
 */
//@WebServlet(description = "トップページのサーブレットクラスです。", urlPatterns = { "/Index" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TokubetsuTorokuba torokuba;
	private KaisaiSchedule schedule;
	private SelectYearRaceShosai selectRaceShosai;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PckeibaSqlSessionFactory.openSession();		//MySQLのセッションを確認します

		int year;
		try {
			year = Integer.valueOf(req.getParameter("date").substring(0, 4));
		}catch(NumberFormatException|NullPointerException e) {
			year = LocalDate.now().getYear();
		}

		/**
		 * 変数の初期化を行います。
		 */
		torokuba = new TokubetsuTorokuba();		//特別登録馬テーブル
		schedule = new KaisaiSchedule(year);		//開催スケジュールテーブル（指定年）
		selectRaceShosai = new SelectYearRaceShosai(year);		//レース詳細テーブルの一覧（指定年）

		/**
		 * オブジェクト固有の情報をサーブレットリクエストにセットします
		 */
		req.setAttribute("torokuba", torokuba);
		req.setAttribute("schedule", schedule);
		req.setAttribute("selectRaceShosai", selectRaceShosai);

		/**
		 * サーブレットのリクエスト・レスポンスをjspに渡します
		 */
		RequestDispatcher di = req.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		di.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

}
