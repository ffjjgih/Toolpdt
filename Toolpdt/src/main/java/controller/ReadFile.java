package controller;

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

public class ReadFile {
	private ArrayList<SinhVien> listSV = new ArrayList<>();
	private ArrayList<SinhVien> listThi = new ArrayList<>();
	private ArrayList<SinhVien> listSVDiemDanhFile = new ArrayList<>();
	private ArrayList<SinhVien> listSVKhongThi = new ArrayList<>();
	private SinhVien sv;
	private String lop, mamon, mua;
	private Cell cell;

	public ArrayList<SinhVien> readDiemDanhFile(Iterator<Row> iteratorRow, List<Integer> listCell) {
		while (iteratorRow.hasNext()) {
			Row row = iteratorRow.next();
			this.sv = new SinhVien();
			Iterator<Cell> iteratorCell = row.cellIterator();

			if (row.getRowNum() == 2) {
				this.lop = row.getCell(3).getStringCellValue();
				// System.out.print(lop);
			}
			if (row.getRowNum() == 3) {
				this.mamon = row.getCell(3).getStringCellValue();
				// System.out.print(mamon);
			}
			if (row.getRowNum() == 4) {
				this.mua = row.getCell(3).getStringCellValue();
				// System.out.print(mua);
			}

			if (row.getRowNum() > 7) {
				while (iteratorCell.hasNext()) {
					this.cell = iteratorCell.next();
					if (cell.getColumnIndex() == listCell.get(0)) {
						this.sv.setIdSV(row.getCell(listCell.get(0)).getStringCellValue());
//						System.out.println(sv.getIdSV());
					}
					if (cell.getColumnIndex() == listCell.get(1)) {
						this.sv.setNameSV(row.getCell(listCell.get(1)).getStringCellValue());
//						System.out.println(sv.getNameSV());
					}
					if (cell.getColumnIndex() == listCell.get(2)) {
						this.sv.setStatus(row.getCell(listCell.get(2)).getStringCellValue());
//						System.out.println(sv.getStatus());
					}
				}
				this.sv.setMark(0);
				this.listSVDiemDanhFile.add(sv);
//				System.out.println("Hi 4");
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
					|| cellHeader.getStringCellValue().equalsIgnoreCase("Há»� vÃ  tÃªn")
					|| cellHeader.getStringCellValue().equalsIgnoreCase("Tráº¡ng thÃ¡i")) {
				listColumn.add(cellHeader.getColumnIndex());
			}
		});

		try {
			listSV = this.readDiemDanhFile(iterator, listColumn);
			this.checkDiemDanh(listSV);
			
		} catch (Exception e) {
			// TODO: handle exception
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
			System.out.print("Rá»—ng");
		} else {
			for (int i = 0; i < lstSV.size(); i++) {
				if (lstSV.get(i).getStatus().equalsIgnoreCase("Passed")) {
					this.listThi.add(new SinhVien(lstSV.get(i).getIdSV(), lstSV.get(i).getNameSV(),
							lstSV.get(i).getStatus(), lstSV.get(i).getMark()));
				} else if(lstSV.get(i).getStatus().equalsIgnoreCase("Attendance failed")) {
					this.listSVKhongThi.add(new SinhVien(
							lstSV.get(i).getIdSV(), 
							lstSV.get(i).getNameSV(), 
							"KhÃ´ng Ä‘Æ°á»£c Ä‘i thi",
							lstSV.get(i).getMark()));
				}
			}
			for (SinhVien o : listSVKhongThi) {
				System.out.println(o.toString());
			}
		}

	}
}
