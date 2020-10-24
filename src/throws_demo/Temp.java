package throws_demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Temp {

	public static void main(String[] args) {
		Date d = new Date(1412703605190L);
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		System.out.println(sdfDate.format(d));
	}

}
