package com.racing.servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.racing.model.RaceShosai;
import com.racing.model.TokubetsuTorokuba;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(description = "トップページのサーブレットクラスです。", urlPatterns = { "/Index" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RaceShosai raceShosai;
	private TokubetsuTorokuba torokuba;

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
		String date = req.getParameter("date");
		raceShosai = new RaceShosai(Date.valueOf(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
		torokuba = new TokubetsuTorokuba();
		req.setAttribute("raceShosai", raceShosai);
		req.setAttribute("torokuba", torokuba);
		if(LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) {

		}
		RequestDispatcher di = req.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		di.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
