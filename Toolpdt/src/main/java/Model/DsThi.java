package Model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DS_THI database table.
 * 
 */
@Entity
@Table(name="DS_THI")
@NamedQuery(name="DsThi.findAll", query="SELECT d FROM DsThi d")
public class DsThi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="CA_THI")
	private int caThi;

	@Column(name="Giang_Vien")
	private String giang_Vien;

	@Column(name="LOAI_THI")
	private String loaiThi;

	@Column(name="LOP")
	private String lop;

	@Column(name="MA_MON")
	private String maMon;

	@Column(name="NGAY_THI")
	private String ngayThi;

	@Column(name="PHONG_THI")
	private String phongThi;

	@Column(name="TEN_MON")
	private String tenMon;

	//bi-directional many-to-one association to KiHoc
	@ManyToOne
	@JoinColumn(name="idhk")
	private KiHoc kiHoc;

	public DsThi() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCaThi() {
		return this.caThi;
	}

	public void setCaThi(int caThi) {
		this.caThi = caThi;
	}

	public String getGiang_Vien() {
		return this.giang_Vien;
	}

	public void setGiang_Vien(String giang_Vien) {
		this.giang_Vien = giang_Vien;
	}

	public String getLoaiThi() {
		return this.loaiThi;
	}

	public void setLoaiThi(String loaiThi) {
		this.loaiThi = loaiThi;
	}

	public String getLop() {
		return this.lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public String getMaMon() {
		return this.maMon;
	}

	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}

	public String getNgayThi() {
		return this.ngayThi;
	}

	public void setNgayThi(String ngayThi) {
		this.ngayThi = ngayThi;
	}

	public String getPhongThi() {
		return this.phongThi;
	}

	public DsThi(int caThi, String giang_Vien, String loaiThi, String lop, String maMon, String ngayThi,
			String phongThi, String tenMon,KiHoc kihoc) {
		super();
		this.caThi = caThi;
		this.giang_Vien = giang_Vien;
		this.loaiThi = loaiThi;
		this.lop = lop;
		this.maMon = maMon;
		this.ngayThi = ngayThi;
		this.phongThi = phongThi;
		this.tenMon = tenMon;
		this.kiHoc=kihoc;
	}

	public void setPhongThi(String phongThi) {
		this.phongThi = phongThi;
	}

	public String getTenMon() {
		return this.tenMon;
	}

	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}

	public KiHoc getKiHoc() {
		return this.kiHoc;
	}

	public void setKiHoc(KiHoc kiHoc) {
		this.kiHoc = kiHoc;
	}

}