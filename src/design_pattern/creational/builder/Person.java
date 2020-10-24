package design_pattern.creational.builder;

import java.util.List;

public class Person {
	private String name;
	private int age;
	private List<String> languages;

	/*
	 * Hàm khởi tạo là private nên chỉ có một cách duy nhất để lấy một đối tượng
	 * Person là thông qua class PersonBuilder.
	 */
	private Person(PersonBuilder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.languages = builder.languages;
	}

	@Override
	public String toString() {
		String kq = "The person info: " + "name is " + name + "; " + //
				"age is " + age + "; " + "languages are: ";
		for(String language : languages) {
			kq += language + ", ";
		}
		
		kq = kq.substring(0, kq.length()-2);
		return kq;
	}

	/*
	 * Thông thường, những trường hợp đơn giản người ta sẽ gộp luôn Builder và
	 * ConcreteBuilder thành static nested class bên trong class Person
	 * Inner class này phải có đủ các field giống như của Person
	 */
	public static class PersonBuilder {
		private String name;
		private int age;
		private List<String> languages;

		public PersonBuilder name(String name) {
			this.name = name;
			return this;
		}

		public PersonBuilder age(int age) {
			this.age = age;
			return this;
		}

		public PersonBuilder languages(List<String> languages) {
			this.languages = languages;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}
}
