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

public class ReadDiemOnline {
	private List<SinhVien> listSinhVien = new ArrayList<>();
	private List<SinhVien> listSVThi = new ArrayList<>();
	private List<SinhVien> listSVKhongThi = new ArrayList<>();
	private SinhVien sv;
	private String lop, mamon, mua;
	private Cell cell;
	private String maFile;
	
	public void kiemTra(String fileName) throws IOException {
		List<Integer> listColumn = new ArrayList<>();
		XSSFSheet sheet = this.createSheet(fileName);
		Iterator<Row> iterator = this.createIterator(sheet);
		sheet.getRow(6).forEach(cellHeader ->{
			if (cellHeader.getStringCellValue().equalsIgnoreCase("BÃ i há»�c online")) {
				sheet.getRow(6).forEach(cellOn ->{
					if (cellOn.getStringCellValue().equalsIgnoreCase("MSSV")
							|| cellOn.getStringCellValue().equalsIgnoreCase("Há»� vÃ  tÃªn")
							|| cellOn.getStringCellValue().equalsIgnoreCase("BÃ i há»�c online")
							|| cellOn.getStringCellValue().equalsIgnoreCase("Tráº¡ng thÃ¡i")) {
						listColumn.add(cellOn.getColumnIndex());
					}
				});
				try {
					listSinhVien = this.docDiemOnline(iterator, listColumn);
					this.checkDiemOnl(listSinhVien);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
	
	public List<SinhVien> docDiemOnline(Iterator<Row> iteratorRow, List<Integer> listCell){
		List<SinhVien> listSV = new ArrayList<>();
		try {
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
							this.sv.setMark((double) row.getCell(listCell.get(2)).getNumericCellValue());
						}
						if (cell.getColumnIndex() == listCell.get(3)) {
							this.sv.setStatus(row.getCell(listCell.get(3)).getStringCellValue());
						}
					}
					listSV.add(sv);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return listSV;
	}
	
	
	public void checkDiemOnl(List<SinhVien> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMark() > 7.5
					&& list.get(i).getStatus().equalsIgnoreCase("Passed")) {
				this.listSVThi.add(new SinhVien(
						list.get(i).getIdSV(), 
						list.get(i).getNameSV(), 
						"Ä�Æ°á»£c Ä‘i thi",
						list.get(i).getMark()));
			} else if(list.get(i).getMark() < 7.5 && list.get(i).getStatus().equalsIgnoreCase("Attendance failed")) {
				this.listSVKhongThi.add(new SinhVien(
						list.get(i).getIdSV(), 
						list.get(i).getNameSV(), 
						"KhÃ´ng Ä‘Æ°á»£c Ä‘i thi",
						list.get(i).getMark()));
			}
		}
		for (SinhVien o : listSVKhongThi) {
			System.out.println(o.toString());
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
}
