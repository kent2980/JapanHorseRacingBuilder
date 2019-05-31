package jhrb.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jhrb.web.netkeiba.UmagotoShosaiJoho;

/**
 * Servlet implementation class UmagotoData
 */
@WebServlet("/UmagotoData")
public class UmagotoData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UmagotoShosaiJoho netKeiba;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UmagotoData() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kettoTorokuBango = request.getParameter("kettoBango");
		netKeiba.setKettoTorokuBango(kettoTorokuBango);
		request.setAttribute("netKeiba", netKeiba);
		RequestDispatcher di = request.getRequestDispatcher("/WEB-INF/jsp/umagotoData.jsp");
		di.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		netKeiba = new UmagotoShosaiJoho();
		super.init();
	}

}
