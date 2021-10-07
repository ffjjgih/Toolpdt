package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Daokht;
import Model.DsThi;
import Model.KiHoc;

/**
 * Servlet implementation class UpdateKHTController
 */
@WebServlet("/updatekht")
public class UpdateKHTController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Daokht dao;
	private DsThi kht;
	private int id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateKHTController() {
        super();
        this.dao = new Daokht();
        this.kht = new DsThi();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		id = Integer.parseInt(request.getParameter("id"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			String idkht = request.getParameter("idkht");
			int id = Integer.parseInt(idkht);
			
			String ngaythiInput = request.getParameter("ngaythi");
			Date ngaythi = this.changeDate(ngaythiInput);
			
			int cathi = Integer.parseInt(request.getParameter("cathi"));
			
			String phongthi = request.getParameter("phongthi");
			
			String tenmon = request.getParameter("tenmon");
			
			String mamon = request.getParameter("mamon");
			
			String loaithi = request.getParameter("loaithi");
			
			String lop = request.getParameter("lop");
			
			String giangvien = request.getParameter("giaovien");
			
			String idkh = request.getParameter("idKiHoc");
			KiHoc kh = new KiHoc();
			kh.setIdkh(Integer.parseInt(idkh));

			this.update(id, kh, ngaythi, cathi, phongthi, tenmon, mamon, loaithi, lop, giangvien);
			request.setAttribute("suc", "UPDATE SUCCESSFUL!");
			response.sendRedirect("http://localhost:8080/Toolpdt/Updatekihoc?id=" + id);
			System.out.println("update successful");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "INSERT FAIL, TRY AGAIN!");
			request.getRequestDispatcher("views/ErrorForm.jsp").forward(request, response);
		}
	}

	private void update(int idkht, KiHoc idkh, Date ngayThi, int caThi, String phongThi, String tenMon, String maMon,
			String loaiThi, String lop, String giangVien) {
		this.kht = new DsThi();
		kht.setId(idkht);
		kht.setKiHoc(idkh);
		kht.setNgayThi(ngayThi);
		kht.setCaThi(caThi);
		kht.setPhongThi(phongThi);
		kht.setTenMon(tenMon);
		kht.setMaMon(maMon);
		kht.setLoaiThi(loaiThi);
		kht.setLop(lop);
		kht.setGiangVien(giangVien);
		dao.updateKHT(kht);
	}

	private Date changeDate(String dateInput) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = sdf.parse(dateInput);
		return date;
	}
}
