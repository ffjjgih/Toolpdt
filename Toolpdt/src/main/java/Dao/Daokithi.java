package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Model.KiHoc;
import utils.JpaUtils;


public class Daokithi extends Dao<KiHoc>{
	private JpaUtils conjpa;
	private EntityManager manager;
	private KiHoc k;
	private List<KiHoc> lst;
	public void daokithi() {
		this.conjpa=new JpaUtils();
		this.manager=this.conjpa.getEntityManager();
		this.k=new KiHoc();
		this.lst=new ArrayList<KiHoc>();
	}
	
	/*
	 * public List<KiHoc> getkihoc() { String hql="FROM KiHoc k ORDER BY idhk DESC";
	 * TypedQuery<KiHoc> query=this.manager.createQuery(hql,getclass());
	 * this.lst=query.getResultList(); return this.lst; }
	 */
	
	@Override
	public String getdatabase() {
		return KiHoc.class.getSimpleName();
	}

	@Override
	public Class<KiHoc> getclass() {
		return KiHoc.class;
	}

}
