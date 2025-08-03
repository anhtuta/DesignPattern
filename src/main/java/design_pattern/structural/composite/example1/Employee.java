package design_pattern.structural.composite.example1;

import java.util.ArrayList;
import java.util.List;

/*
 * Composite Pattern là một mẫu cấu trúc (Structural Pattern).
 * Composite Pattern cho phép tương tác với tất cả các đối tượng 
 * tương tự nhau giống như là các đối tượng đơn hoặc collections.
 * 
 * Ví dụ: �?ối tượng File sẽ là 1 đối tượng đơn nếu bên trong nó 
 * không có file nào khác, nhưng đối tượng file sẽ được đối xử 
 * giống như 1 collections nếu bên trong nó lại có những File khác.
 * 
 * Khi tính kích thước của File ta sẽ cần tính kích thước của 
 * tất cả các file bên trong nó.
 * 
 * Ví dụ dưới: Trong lớp Employee lại có 1 field cũng có kiểu
 * là Employee (thực chất là 1 list các object Employee).
 * Use the Employee class to create and print employee hierarchy
 */
public class Employee {
	private String name;
	private String dept;
	private int salary;
	private List<Employee> subordinates;

	// constructor
	public Employee(String name, String dept, int sal) {
		this.name = name;
		this.dept = dept;
		this.salary = sal;
		subordinates = new ArrayList<Employee>();
	}

	public void add(Employee e) {
		subordinates.add(e);
	}

	public void remove(Employee e) {
		subordinates.remove(e);
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	public String toString() {
		return ("[ Name : " + name + ", dept : " + dept + ", salary :" + salary + " ]");
	}
}
