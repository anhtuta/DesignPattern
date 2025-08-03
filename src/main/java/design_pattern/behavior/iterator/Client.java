package design_pattern.behavior.iterator;

import java.util.Iterator;

public class Client {
	public static void main(String[] args) {

		Menu menu = new Menu();
		menu.addItem(new Item("Home", "/home"));
		menu.addItem(new Item("Java", "/java"));
		menu.addItem(new Item("Spring Boot", "/spring-boot"));

		Iterator<Item> iterator = menu.iterator();
		while (iterator.hasNext()) {
			Item item = iterator.next();
			System.out.println(item);
		}
	}
}
