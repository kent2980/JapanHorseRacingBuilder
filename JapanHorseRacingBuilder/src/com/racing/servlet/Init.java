package com.racing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import com.database.access.PckeibaSqlSessionFactory;
import com.database.access.PckeibalinkSqlSessionFactory;

/**
 * Servlet implementation class Init
 */
//@WebServlet("/Init")
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SqlSession pckeibaSession;
	private static SqlSession pckeibaLinkSession;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Init() {
        super();
        pckeibaSession = PckeibaSqlSessionFactory.openSession();
        pckeibaLinkSession = PckeibalinkSqlSessionFactory.openSession();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static SqlSession getPckeibaSession() {
		return pckeibaSession;
	}

	public static SqlSession getPckeibaLinkSession() {
		return pckeibaLinkSession;
	}

}
