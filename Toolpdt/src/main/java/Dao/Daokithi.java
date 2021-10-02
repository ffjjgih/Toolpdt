package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Model.KiHoc;
import utils.JpaUtils;

public class Daokithi extends Dao<KiHoc>{
	private JpaUtils conn;
	private EntityManager entity;
	private EntityTransaction transaction;
	private KiHoc k;
	private List<KiHoc> lst;
	public void daokithi() {
		this.conn=new JpaUtils();
		this.entity=conn.getEntityManager();
		this.transaction=this.entity.getTransaction();
		this.k=new KiHoc();
		this.lst=new ArrayList<KiHoc>();
	}
	
	
	@Override
	public String getdatabase() {
		return KiHoc.class.getSimpleName();
	}

	@Override
	public Class<KiHoc> getclass() {
		return KiHoc.class;
	}

}
