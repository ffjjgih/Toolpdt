package Services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.SinhVien;

public class Readfilediemdanh {
	private ArrayList<SinhVien> listSV;
	private ArrayList<SinhVien> listThi;
	private ArrayList<SinhVien> listSVDiemDanhFile;
	private ArrayList<SinhVien> lstsvcamthi;
	private SinhVien sv;
	private String lop, mamon, mua;
	private Cell cell;
	public Readfilediemdanh() {
		this.sv = new SinhVien();
		this.listSV = new ArrayList<>();
		this.listThi = new ArrayList<>();
		this.listSVDiemDanhFile = new ArrayList<>();
		this.lstsvcamthi=new ArrayList<SinhVien>();
	}
	public ArrayList<SinhVien> readDiemDanhFile(Iterator<Row> iteratorRow, List<Integer> listCell) {
		while (iteratorRow.hasNext()) {
			Row row = iteratorRow.next();
			this.sv = new SinhVien();
			Iterator<Cell> iteratorCell = row.cellIterator();
			if (row.getRowNum() == 2) {
				this.lop = row.getCell(3).getStringCellValue();
			}
			if (row.getRowNum() == 3) {
				this.mamon = row.getCell(3).getStringCellValue();
			}
			if (row.getRowNum() == 4) {
				this.mua = row.getCell(3).getStringCellValue();
			}

			if (row.getRowNum() > 7) {
				while (iteratorCell.hasNext()) {
					this.cell = iteratorCell.next();
					if (cell.getColumnIndex() == listCell.get(0)) {
						this.sv.setIdSV(row.getCell(listCell.get(0)).getStringCellValue());
					}
					if (cell.getColumnIndex() == listCell.get(1)) {
						this.sv.setNameSV(row.getCell(listCell.get(1)).getStringCellValue());
					}
					if (cell.getColumnIndex() == listCell.get(2)) {
						this.sv.setStatus(row.getCell(listCell.get(2)).getStringCellValue());
					}
				}
				this.sv.setMark(0);
				this.listSVDiemDanhFile.add(sv);
			}
		}
		return listSVDiemDanhFile;
	}

	public void kiemTra(String nameFile) throws IOException {
		List<Integer> listColumn = new ArrayList<>();
		XSSFSheet sheet = this.createSheet(nameFile);
		Iterator<Row> iterator = this.createIterator(sheet);
		sheet.getRow(6).forEach(cellHeader -> {
			if (cellHeader.getStringCellValue().equalsIgnoreCase("MSSV")
					|| cellHeader.getStringCellValue().equalsIgnoreCase("Họ Và Tên")
					|| cellHeader.getStringCellValue().equalsIgnoreCase("Trạng Thái")) {
				listColumn.add(cellHeader.getColumnIndex());
			}
		});
		try {
			listSV = this.readDiemDanhFile(iterator, listColumn);
			this.checkDiemDanh(listSV);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private XSSFSheet createSheet(String nameFile) throws IOException {
		FileInputStream fis = new FileInputStream(nameFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		return sheet;
	}

	private Iterator createIterator(XSSFSheet sheet) {
		Iterator<Row> iterator = sheet.iterator();
		return iterator;
	}

	private void checkDiemDanh(ArrayList<SinhVien> lstSV) {
		if (lstSV == null) {

		} else {
			for (int i = 0; i < lstSV.size(); i++) {
				if (!lstSV.get(i).getStatus().equalsIgnoreCase("Attendance failed")) {
					this.listThi.add(new SinhVien(lstSV.get(i).getIdSV(), lstSV.get(i).getNameSV(),
							lstSV.get(i).getStatus(), lstSV.get(i).getMark()));
				} else {
					this.lstsvcamthi.add(new SinhVien(lstSV.get(i).getIdSV(), lstSV.get(i).getNameSV(),
							lstSV.get(i).getStatus(), lstSV.get(i).getMark()));
				}
			}
		}
	}
	
	public ArrayList<SinhVien> xuatsvthi(){
		return this.listThi;
	}
	
	public ArrayList<SinhVien> xuatsvcamthi(){
		return this.lstsvcamthi;
	}
}
