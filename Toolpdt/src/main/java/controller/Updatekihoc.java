package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.Daokht;
import Dao.Daokithi;
import Model.DsThi;
import Model.KiHoc;

/**
 * Servlet implementation class Updatekihoc
 */
@WebServlet("/Updatekihoc")
public class Updatekihoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Daokht daokht;
    private Daokithi daokithi;
    private List<DsThi> lstdsthi;
    private KiHoc k;
    int index;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updatekihoc() {
        super();
        this.daokht=new Daokht();
        this.daokithi=new Daokithi();
        this.lstdsthi=new ArrayList<DsThi>();
        this.k=new KiHoc();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idkh = request.getParameter("id");
		index=Integer.parseInt(idkh);
		this.k=this.daokithi.findid(index);
		this.lstdsthi=this.daokht.findbykihoc(k);
		request.setAttribute("lst", this.lstdsthi);
		request.setAttribute("id", index);
		request.getRequestDispatcher("/views/formKHT.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
