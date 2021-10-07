package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Daokht;
import Model.DsThi;
import Model.KiHoc;

/**
 * Servlet implementation class LoadToUpdateController
 */
@WebServlet("/loadtoupdate")
public class LoadToUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Daokht dao;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadToUpdateController() {
        super();
        this.dao = new Daokht();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("khtID");
		int idkh = Integer.parseInt(id);
		
		DsThi kht = this.dao.getKHTById(idkh);
		int IdKH = kht.getKiHoc().getIdkh();
		if (kht != null) {
			request.setAttribute("detail", kht);
			request.setAttribute("id", IdKH);
			request.getRequestDispatcher("views/loadToUpdate.jsp").forward(request, response);
			request.setAttribute("suc", "GET KẾ HOẠCH THI SUCCESSFUL!");
		} else {
			request.setAttribute("fail", "GET FAIL, PLEASE TRY AGAIN!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
