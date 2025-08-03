package random_demo;

import java.util.Arrays;
import java.util.Random;

/*
 * lấy random 255 số trong khoảng từ 0-65000, check xem sau bao nhiêu
 * lần lấy thì tồn tại 2 số trùng nhau
 */
public class RandomDemo {
	int[] arr;

	public RandomDemo() {
		arr = new int[256];
	}

	public void setRandomValue() {
		Random rd = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rd.nextInt(65000);
		}
	}

	public void sortArray() {
		Arrays.sort(arr);
	}
	
	public int twoElementsEqual() {
		for(int i=0; i<arr.length-1; i++) {
			if(arr[i] == arr[i+1]) return i;
		}
		
		return -1;
	}

	public static void main(String[] args) {
		RandomDemo rd = new RandomDemo();
		int loopTime = 0;
		int indexOf2ElementsEqual = -1;

		while (true) {
			loopTime++;
			rd.setRandomValue();
			rd.sortArray();
			indexOf2ElementsEqual = rd.twoElementsEqual();
			if(indexOf2ElementsEqual > -1) {
				break;
			}
		}

		System.out.println("loopTime = " + loopTime);
		for(int i=0; i<rd.arr.length-1; i++) {
			System.out.println(rd.arr[i]);
		}
		
		System.out.println("================");
		System.out.println(rd.arr[indexOf2ElementsEqual] + " - " + rd.arr[indexOf2ElementsEqual+1]);
	}
}
