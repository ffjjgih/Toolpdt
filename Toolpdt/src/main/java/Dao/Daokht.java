package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Model.DsThi;
import Model.KiHoc;
import utils.JpaUtils;

public class Daokht extends Dao<DsThi>{
	private DsThi kht;
	private List<DsThi> lstkht;
	private JpaUtils jpa;
	private EntityManager entity;
	private EntityManagerFactory factory;
	
	public Daokht() {
		this.kht=new DsThi();
		this.lstkht=new ArrayList<>();
		this.entity=this.jpa.getEntityManager();
		this.factory=entity.getEntityManagerFactory();
	}


	public List<DsThi> findbykihoc(KiHoc k){
		String hql="SELECT d FROM DsThi d WHERE kiHoc=:ky_hoc";
		TypedQuery<DsThi> query=this.entity.createQuery(hql,DsThi.class);
		query.setParameter("ky_hoc", k);
		this.lstkht=query.getResultList();
		return this.lstkht;
	}
	@Override
	public String getdatabase() {
		return DsThi.class.getSimpleName();
	}

	@Override
	public Class<DsThi> getclass() {
		return DsThi.class;
	}
}
