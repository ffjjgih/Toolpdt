package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//import Dao.Dao;
//import Dao.Daokht;
import Model.DsThi;
import Services.Readfilekht;

@MultipartConfig()
@WebServlet("/Uploadkht")
public class Uploadkht extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String namefile,tenfile;
	private Readfilekht read;
	private ArrayList<DsThi> lst;
    public Uploadkht() {
    	this.read=new Readfilekht();
    	this.lst=new ArrayList<DsThi>();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("/views/formDoiLichThi.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			readfilekht(request, response);
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			
		} catch (IOException er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/formDoiLichThi.jsp").forward(request, response);
	}

	private void readfilekht(HttpServletRequest request, HttpServletResponse response)throws NullPointerException, IOException, ServletException {
		
		String namefile=request.getParameter("namefile");
		System.out.print(namefile);
		if(namefile==null) {
		String name=readurlfile(request, response);
		lst=this.read.read(name);
		for(DsThi x:lst) {
			System.out.print(x.getNgayThi() +"\t"+x.getCaThi() +"\t"+x.getPhongThi() +"\t"+x.getTenMon() +"\t"+x.getMaMon() +"\t"+x.getLoaiThi() +"\t"+x.getLop() +"\n");
		}
		}else {
			
		}
		
	}
	private String readurlfile(HttpServletRequest request, HttpServletResponse response)throws NullPointerException, IOException, ServletException{
		String filename = null;
			Part part = request.getPart("namefile");
			String realpath = request.getServletContext().getRealPath(System.getProperty("file.separator") +"filemarks");
			String namefile = Path.of(part.getSubmittedFileName()).getFileName().toString();
			if (!Files.exists(Path.of(realpath))) {
				Files.createDirectory(Path.of(realpath));
			}
			part.write(realpath + System.getProperty("file.separator") + namefile);
			System.out.print(realpath + System.getProperty("file.separator") + namefile);
			filename = realpath + System.getProperty("file.separator") + namefile;
		return filename;
	}
}
