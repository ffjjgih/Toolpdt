package Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the KI_HOC database table.
 * 
 */
@Entity
@Table(name="KI_HOC")
@NamedQuery(name="KiHoc.findAll", query="SELECT k FROM KiHoc k")
public class KiHoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDHK")
	private int idhk;

	@Column(name="BLOCKID")
	private String blockid;

	@Column(name="NAM_HOC")
	private String namHoc;

	@Column(name="NGAY_TAO")
	private String ngayTao;

	@Column(name="TRANG_THAI")
	private String trangThai;

	//bi-directional many-to-one association to DsThi
	@OneToMany(mappedBy="kiHoc")
	private List<DsThi> dsThis;

	public KiHoc() {
	}

	public int getIdhk() {
		return this.idhk;
	}

	public void setIdhk(int idhk) {
		this.idhk = idhk;
	}

	public String getBlockid() {
		return this.blockid;
	}

	public void setBlockid(String blockid) {
		this.blockid = blockid;
	}

	public String getNamHoc() {
		return this.namHoc;
	}

	public void setNamHoc(String namHoc) {
		this.namHoc = namHoc;
	}

	public String getNgayTao() {
		return this.ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public String getTrangThai() {
		return this.trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public List<DsThi> getDsThis() {
		return this.dsThis;
	}

	public void setDsThis(List<DsThi> dsThis) {
		this.dsThis = dsThis;
	}

	public DsThi addDsThi(DsThi dsThi) {
		getDsThis().add(dsThi);
		dsThi.setKiHoc(this);

		return dsThi;
	}

	public DsThi removeDsThi(DsThi dsThi) {
		getDsThis().remove(dsThi);
		dsThi.setKiHoc(null);

		return dsThi;
	}

}