package Dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import Model.DsThi;
import Services.Readfilekht;
import utils.JpaUtils;

public class Daokht extends Dao<DsThi>{
	private DsThi kht;
	private Readfilekht readkht;
	private ArrayList<DsThi> lstkht;
	private JpaUtils jpa;
	private EntityManager entity;
	private EntityManagerFactory factory;
	
	public Daokht() {
		this.kht=new DsThi();
		this.readkht=new Readfilekht();
		this.lstkht=new ArrayList<>();
		this.entity=this.jpa.getEntityManager();
		this.factory=entity.getEntityManagerFactory();
	}

	public void createkehoachthi(String name) {
		//this.lstkht=this.readkht.read(name);
		EntityTransaction transaction=entity.getTransaction();
		try {
			transaction.begin();
			entity.persist(this.lstkht);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
