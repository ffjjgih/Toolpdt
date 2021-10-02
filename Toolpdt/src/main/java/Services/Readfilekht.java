package Services;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.DsThi;
import Model.KiHoc;

public class Readfilekht {
	
	private DsThi kht;
	private ArrayList<DsThi> lst;
	
	public Readfilekht() {
		this.kht=new DsThi();
		this.lst=new ArrayList<>();
	}
	public ArrayList<DsThi> read(String namefile,KiHoc k){
		List<Integer> dscolumndiem=new ArrayList<>();
        String loaithi,block,ki,lop,tenmon,giangvien;
        try {
            String mamon, mamonhoc, phongthi;
            java.util.Date ngay = null;
            int cathi;
            FileInputStream excel = new FileInputStream(namefile);
            XSSFWorkbook workbook = new XSSFWorkbook(excel);
            XSSFSheet sheet = workbook.getSheet("KH thi Block 2");
            Iterator<Row> iterator = sheet.iterator();
                sheet.getRow(1).forEach(cellkht -> {
                    if (cellkht.getStringCellValue().equalsIgnoreCase("Ngày thi") || cellkht.getStringCellValue().equalsIgnoreCase("Ca")
                            || cellkht.getStringCellValue().equalsIgnoreCase("Phòng thi") || cellkht.getStringCellValue().equalsIgnoreCase("Mã môn")||
                            cellkht.getStringCellValue().equalsIgnoreCase("Lớp")|| cellkht.getStringCellValue().equalsIgnoreCase("Loại Thi")||cellkht.getStringCellValue().equalsIgnoreCase("Tên Môn")) {
                        dscolumndiem.add(cellkht.getColumnIndex());
                        
                    }
                });
                System.out.println(dscolumndiem.size());
            while (iterator.hasNext()) {
                Row row = iterator.next();
                ki=sheet.getRow(0).getCell(0).getStringCellValue();
                if (row.getRowNum() > 3) {
                    if (row.getCell(6).getCellType() == CellType.STRING) {
                    mamonhoc = row.getCell(6).getStringCellValue();
                    kht.setMaMon(mamonhoc);
                } else {
                    mamonhoc = row.getCell(6).getNumericCellValue() + "";
                    kht.setMaMon(mamonhoc);
                }
                phongthi = row.getCell(3).getStringCellValue();
                ngay = row.getCell(1).getDateCellValue();
                cathi = (int) row.getCell(2).getNumericCellValue();
                loaithi = row.getCell(8).getStringCellValue();
                tenmon = row.getCell(5).getStringCellValue();
                lop=row.getCell(9).getStringCellValue();
                giangvien=row.getCell(10).getStringCellValue();
                if (mamonhoc.length() > 0 && phongthi.length() > 0 && cathi > 0) {
                	lst.add(new DsThi(cathi,giangvien, loaithi,lop,mamonhoc, ngay.toString(),phongthi,tenmon,k));
                }
                }
            }
        }catch (Exception e) {
			e.printStackTrace();
		}
        return this.lst;
	}
}
