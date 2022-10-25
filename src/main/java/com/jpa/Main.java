package com.jpa;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.jpa.dao.AddressDao;
import com.jpa.dao.EmployeeDao;
import com.jpa.model.Address;

public class Main {
	public static Logger logger = Logger.getLogger("SpringJpa");
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PropertyConfigurator.configure("C:\\Users\\hai\\SiemensTraining\\SpringJpaExampleMulDb\\src\\main\\resources\\application.properties");
		
		AddressDao ado=new AddressDao();
		ado.printall();
		
		EmployeeDao obj = new EmployeeDao();
		while (true) {
			
			logger.info(
					"Enter\n                                         1->Print all Employees"
					+ "\n                                         2->Insert new Employee"
					+ "\n                                         3->Delete the employee"
					+ "\n                                         4->update salary"
					+ "\n                                         5->Get Details by id"
					+ "\n                                         6->Print all Address"
					+ "\n                                         7->update Address "
					+ "\n                                         8->Get Address by eid");
			int i = Integer.parseInt(sc.nextLine());
			if (i == 1)
				obj.printall();
			else if (i == 2)
				obj.insert();
			else if (i == 3)
				obj.delete();
			else if (i == 4)
				obj.update();
			else if (i == 5)
			{
				int eid=obj.getbyId();
				Address addres=ado.getbyId(eid);
				logger.info("Address");
				logger.info(addres.toString());
			}
			else if (i == 6)
				ado.printall();
			else if (i == 7)
				ado.update();
			else if (i == 8)
			{
				Address add=ado.getbyId();
				if(add!=null)
				{
					logger.info(add.toString());
				}
					
			}
			else
				break;

		}
		obj.end();
		ado.end();
		
	}
}
