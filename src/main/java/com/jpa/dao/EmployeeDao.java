package com.jpa.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.jpa.model.Employee;

public class EmployeeDao {
	public static Logger logger = Logger.getLogger("SpringJpa");
	static EntityManagerFactory entityfactory=Persistence.createEntityManagerFactory("persistence");
	static EntityManager manager=entityfactory.createEntityManager();
	Scanner sc=new Scanner(System.in);
	
	
	public void printall() {
		@SuppressWarnings("unchecked")
		List<Employee> list=manager.createQuery("select e from Employee e").getResultList();
		if(list==null)
			logger.info("No data found.....");
		else
		{
			System.out.println();
			logger.info(String.format("%-10s %-10s %-10s %-10s %-10s", "E-Id","Name", "Department","Age",
					"Salary", "Company"));
			list.forEach(e->logger.info(e.toString()));
		}
			
	}
	public void insert() {
		manager.getTransaction().begin();
		logger.info("Enter the 1)Name 2)Age 3)Department 4)Salary 5)Company Name");
		
		Employee emp=new Employee();
		emp.setEname(sc.nextLine());
		emp.setAge(Integer.parseInt(sc.nextLine()));
		emp.setDepartment(sc.nextLine());
		emp.setSalary(Double.parseDouble(sc.nextLine()));
		emp.setCompanyname(sc.nextLine());
		manager.persist(emp);
		manager.getTransaction().commit();
		logger.info("Inserted successfully!!");
	}
	public void delete() {
		logger.info("Enter the id to delete");
		int id=Integer.parseInt(sc.nextLine());
		Employee e=manager.find(Employee.class, id);
		manager.getTransaction().begin();
		if(e!=null)
		{
			manager.remove(e);
			logger.info("Deleted successfully");
		}
		else
		{
			logger.error("No employee with given id");
		}
		manager.getTransaction().commit();
	}
	public void update() {
		logger.info("Enter the id and salary to update");
		int id=Integer.parseInt(sc.nextLine());
		manager.getTransaction().begin();
		Employee e=manager.find(Employee.class, id);
		
		if(e!=null)
		{
			logger.info("Enter the salary");
			double salary=Double.parseDouble(sc.nextLine());
			e.setSalary(salary);
			manager.merge(e);
			logger.info("Updated successfully");
		}
		else
		{
			logger.error("No employee with given id");
		}
		manager.getTransaction().commit();
		
	}
	public void end() {
		manager.close();
		entityfactory.close();
	}
	public int getbyId() {
		logger.info("Enter the id ");
		int id=Integer.parseInt(sc.nextLine());
		Employee e=manager.find(Employee.class, id);
		System.out.println();
		logger.info(String.format("%-10s %-10s %-10s %-10s %-10s", "E-Id","Name", "Department","Age",
				"Salary", "Company"));
		logger.info(e.toString());
		return id;
	}
}
