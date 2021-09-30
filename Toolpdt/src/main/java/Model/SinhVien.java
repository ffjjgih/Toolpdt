package Model;

public class SinhVien {
	private String idSV, nameSV, status;
	private double mark;
	
	public SinhVien() {

	}

	public SinhVien(String idSV, String nameSV, String status, double mark) {
		this.idSV = idSV;
		this.nameSV = nameSV;
		this.status = status;
		this.mark = mark;
	}

	public String getIdSV() {
		return idSV;
	}

	public void setIdSV(String idSV) {
		this.idSV = idSV;
	}

	public String getNameSV() {
		return nameSV;
	}

	public void setNameSV(String nameSV) {
		this.nameSV = nameSV;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "SinhVien [idSV=" + idSV + ", nameSV=" + nameSV + ", status=" + status + ", mark=" + mark + "]";
	}
	
	
}
