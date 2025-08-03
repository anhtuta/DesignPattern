package design_pattern.behavior.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Menu {
	private List<Item> menuItems = new ArrayList<>();

	public void addItem(Item item) {
		menuItems.add(item);
	}

	public Iterator<Item> iterator() {
		return new MenuItemIterator();
	}

	/*
	 Có thể tự tạo 1 interface iterator như sau (Trong bài viết ở trang
	 gpcoder.com dùng cái này):
	 public interface ItemIterator<T> {
		 boolean hasNext();
		 T next();
	 }
	 Nhưng trong project này ta sẽ dùng luôn thằng Iterator của Java
	 */
	private class MenuItemIterator implements Iterator<Item> {
		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex < menuItems.size();
		}

		@Override
		public Item next() {
			return menuItems.get(currentIndex++);
		}
	}
}
