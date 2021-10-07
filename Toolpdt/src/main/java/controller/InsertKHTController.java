package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Daokht;
import Model.DsThi;
import Model.KiHoc;

/**
 * Servlet implementation class InsertKHTController
 */
@WebServlet("/insertkht")
public class InsertKHTController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Daokht dao;
	private DsThi kht;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertKHTController() {
		super();
		// TODO Auto-generated constructor stub
		this.dao = new Daokht();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		try {
			String ngaythiInput = request.getParameter("ngaythi");
			
			Date ngaythi = this.changeDate(ngaythiInput);

			int cathi = Integer.parseInt(request.getParameter("cathi"));

			String phongthi = request.getParameter("phongthi");

			String tenmon = request.getParameter("tenmon");

			String mamon = request.getParameter("mamon");

			String loaithi = request.getParameter("loaithi");

			String lop = request.getParameter("lop");

			String giangvien = request.getParameter("giaovien");

			String idkh = request.getParameter("idkh");
			KiHoc kh = new KiHoc();
			kh.setIdkh(Integer.parseInt(idkh));

			this.insert(kh, ngaythi, cathi, phongthi, tenmon, mamon, loaithi, lop, giangvien);
			request.setAttribute("suc", "INSERT SUCCESSFUL!");
			request.setAttribute("id", idkh);
			//response.sendRedirect("http://localhost:8080/Toolpdt/Updatekihoc?id="+idkh);
			//request.getRequestDispatcher("views/formKHT.jsp").forward(request, response);
			System.out.println("insert successful");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "INSERT FAIL, TRY AGAIN!");
			//request.getRequestDispatcher("views/ErrorForm.jsp").forward(request, response);
		}

	}

	private void insert(KiHoc idkh, Date ngayThi, int caThi, String phongThi, String tenMon, String maMon,
			String loaiThi, String lop, String giangVien) {
		this.kht = new DsThi();
		kht.setKiHoc(idkh);
		kht.setNgayThi(ngayThi);
		kht.setCaThi(caThi);
		kht.setPhongThi(phongThi);
		kht.setTenMon(tenMon);
		kht.setMaMon(maMon);
		kht.setLoaiThi(loaiThi);
		kht.setLop(lop);
		kht.setGiangVien(giangVien);
		dao.insertKHT(kht);
	}

	private Date changeDate(String dateInput) throws ParseException {
		String[] dateM = dateInput.split("-");

		String dateOut = dateM[2] + "-" + dateM[1] + "-" + dateM[0];

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = sdf.parse(dateOut);
		return date;
	}


}
