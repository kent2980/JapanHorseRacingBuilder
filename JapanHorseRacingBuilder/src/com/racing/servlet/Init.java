package com.racing.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.access.PckaibaSqlSessionFactory;
import com.database.access.PckaibalinkSqlSessionFactory;

/**
 * Servlet implementation class Init
 */
//@WebServlet("/Init")
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Init() {
        super();
        PckaibaSqlSessionFactory.openSession();
        PckaibalinkSqlSessionFactory.openSession();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
