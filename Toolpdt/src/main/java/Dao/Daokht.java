package Dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import Model.DsThi;
import Services.Readfilekht;
import utils.JpaUtils;

public class Daokht implements Dao{
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

	@Override
	public void createkehoachthi(String name) {
		this.lstkht=this.readkht.read(name);
		EntityTransaction transaction=entity.getTransaction();
		try {
			transaction.begin();
			entity.persist(this.lstkht);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
