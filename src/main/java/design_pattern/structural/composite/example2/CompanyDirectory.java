package design_pattern.structural.composite.example2;

import java.util.ArrayList;
import java.util.List;

/*
 * Composite
 */
public class CompanyDirectory implements Employee {
	private List<Employee> employeeList = new ArrayList<Employee>();

	@Override
	public void showEmployeeDetails() {
		for (Employee emp : employeeList) {
			emp.showEmployeeDetails();
		}
	}

	public void addEmployee(Employee emp) {
		employeeList.add(emp);
	}

	public void removeEmployee(Employee emp) {
		employeeList.remove(emp);
	}
}