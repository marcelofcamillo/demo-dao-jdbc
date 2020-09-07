package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
import model.entities.Department;
import model.entities.Seller;
import model.entities.Department;

public class Program2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DepartmentDAO departmentDAO = DaoFactory.createDepartmentDAO();
		
		System.out.println("=== TEST 1: department findById ===");
		Department department = departmentDAO.findById(1);
		System.out.println(department);
		
		System.out.println("\n=== TEST 2: department findAll ===");
		List<Department> list = departmentDAO.findAll();
		for(Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3: department insert ===");
		Department newDepartment = new Department(null, "Teste");
		departmentDAO.insert(newDepartment);
		System.out.println("Inserted! New id: " + newDepartment.getId());
		
		System.out.println("\n=== TEST 4: department update ===");
		department = departmentDAO.findById(7);
		department.setName("Teste Atualizado");
		departmentDAO.update(department);
		System.out.println("Update completed!");
		
		System.out.println("\n=== TEST 6: department delete ===");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		departmentDAO.deleteById(id);
		System.out.println("Delete completed!");
		
		sc.close();
	}
}
