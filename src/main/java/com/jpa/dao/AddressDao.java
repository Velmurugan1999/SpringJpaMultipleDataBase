package com.jpa.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.jpa.model.Address;

public class AddressDao {
	public static Logger logger = Logger.getLogger("SpringJpa");
	static EntityManagerFactory entityfactory=Persistence.createEntityManagerFactory("persistence1");
	static EntityManager manager=entityfactory.createEntityManager();
	Scanner sc=new Scanner(System.in);
	public void printall() {
		@SuppressWarnings("unchecked")
		List<Address> list=manager.createQuery("select a from Address a").getResultList();
		if(list==null)
			logger.info("No data found.....");
		else
		{
			System.out.println();
			logger.info(String.format("%-10s %-10s %-10s %-10s %-10s", "DoorNo","Street", "City","Pincode",
					"Employee ID"));
			list.forEach(e->logger.info(e.toString()));
		}
			
	}
	public void end() {
		manager.close();
		entityfactory.close();
	}
	public void update() {
		logger.info("Enter the Employee id and Street name to update address");
		int id=Integer.parseInt(sc.nextLine());
		String street=sc.nextLine();
		Address address=getbyId(id);
		if(address!=null)
		{
			address.setStreet(street);
			manager.merge(address);
			logger.info("Updated Successfully");
		}
	}
	public Address getbyId(int id) {
		List<Address> address=manager.createQuery("select a from Address a where a.eid="+id+"").getResultList();
		if(!address.isEmpty())
		{
			return address.get(0);
		}
		else
		{
			logger.error("Not Record Found");
			return null;
		}
	}
	
	public Address getbyId() {
		logger.info("Enter the Employee id to get address");
		
		int id=Integer.parseInt(sc.nextLine());
		List<Address> address=manager.createQuery("select a from Address a where a.eid="+id+"").getResultList();
		if(!address.isEmpty())
		{
			return address.get(0);
		}
		else
		{
			logger.error("Not Record Found");
			return null;
		}
	}
}
