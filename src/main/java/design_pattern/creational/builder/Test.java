package design_pattern.creational.builder;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<String> langList = new ArrayList<>();
		langList.add("Vietnamese");
		langList.add("English");
		
		Person p = new Person.PersonBuilder()
						.name("Anh tu")
						.age(23)
						.languages(langList)
						.build();
		
		System.out.println(p);
	}
}
