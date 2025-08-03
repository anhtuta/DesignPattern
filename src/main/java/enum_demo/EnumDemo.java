package enum_demo;

public class EnumDemo {
	// http://vietnamtravelinformation.net/four-seasons-in-hanoi/
	public static String getWeatherInHanoi(Season s) {
		String kq;
		switch (s) {
		case SPRING:
			kq = "Spring in Hanoi is short and often from the last days of January to March "
					+ "or first days of April. Spring coming means a new lunar year is coming. "
					+ "People in Hanoi as well as other places in Vietnam are eager to enjoy "
					+ "the Tet holiday – the biggest holiday and festival of Vietnam every year. "
					+ "On Tet holiday, Hanoi is quiet and peaceful, different from the noisiness "
					+ "of daily life. Despite enjoying the Tet holiday of Vietnam, tourists "
					+ "travel to Vietnam at that time will have to meet some difficulties, "
					+ "because there are little services, so the price is much higher.\n" + 
					"The weather in spring is much warmer, humid and has drizzling rains. "
					+ "This kind of weather is suitable for the plants to grow and flowers "
					+ "to blossom. The green buds appear on the trees and flowers seem to "
					+ "be more beautiful in the spring. Peach-blossom, especially Nhat Tan "
					+ "peach-blossom, is a very famous kind of flower of Hanoi in the occasion "
					+ "of Tet holiday. Other kinds of flower, such as rose, gladiolus, narcissus…, "
					+ "are also more beautiful than in other seasons. Tourists who love taking "
					+ "photos can have a chance to take photos with “Sưa flower” or “Mountain-ebony "
					+ "flower” (Ban Flower) in some streets of Hanoi.";
			break;
		case SUMMER:
			kq = "Hanoi’s summers often begin on the middle April but it starts to get hot from "
					+ "May and lasts to the end of August. It is hotter and hotter (always over 30°C), "
					+ "and July is usually the hottest month of a year. Sometimes the temperature "
					+ "outside can reach 40°C. Moreover, heavy rain is one feature of Hanoi’s summer, "
					+ "along with high humidity. Be ready to be sweat under the sun during days, "
					+ "event at night, it is still too hot.\n" +
					"Based on the uncomfortable weather conditions, summer seems to be the hardest "
					+ "season for tourists to travel to Hanoi. However, Hanoi in summer is colorful "
					+ "with many kinds of flower. White Madonna lily on April, red flamboyant and "
					+ "violet Lagerstroemia speciosa (Bang Lang flower) on May, and WestLake lotus "
					+ "on June will attract people to take photos. Summer is a season of variety "
					+ "of fruits in the north of Vietnam. And you can taste them in the capital "
					+ "of Hanoi, such as: grapefruit, litchi, mango, watermelon, pineapple…";
			break;
		case AUTUMN:
			kq = "With me, autumn is the most beautiful season in Hanoi, when the climate is cool "
					+ "and dry but still sunny. Autumn in Hanoi begins when people can feel the "
					+ "smell of alstonia scholaris (that means milk flowers) in the streets at "
					+ "cool nights and it lasts from September to November before getting cold. "
					+ "People need to wear a light overcoat or a cardigan when go out. Hanoi in "
					+ "autumn is truly romantic and peaceful. You can see the many yellow and "
					+ "red leaf trees along the streets, the sky is bluer, and the water in many "
					+ "lakes of Hanoi is greener. It is also good time to enjoy the mid-autumn "
					+ "festival, one of the biggest festivals each year in Vietnam celebrated "
					+ "on the lunar August, 15th. Besides milk flowers with special smell, "
					+ "daisy is an autumn’s specific flower, along with Cốm (green rice flakes) – a "
					+ "specialty of Hanoi in this season.";
			break;
		case WINTER:
			kq = "The winter of Hanoi starts from December and may last until the February, with "
					+ "the average temperature of 15-17°C. Sometimes it drops to below 10°C and "
					+ "the high humidity will make the weather a little bit colder. Remember to "
					+ "bring warm clothes if you don’t want to get a cold when visiting a tropical country.\n" +
					"Winter often brings a sad feeling, because the sky is often dark and gloomy. "
					+ "However, Hanoi seems to have its busiest days of the year and streets are "
					+ "more crowded than usual during working hours as people are in rush to "
					+ "complete all the remaining works and prepare for Tet holiday. The cold "
					+ "weather enables people living in Hanoi or visitors to enjoy the tasty hot "
					+ "food like hotpot or grill dishes.";
			break;
		default:
			kq = "Unknown weather!";
			break;
		}
		
		return kq;
	}

	public static void main(String[] args) {
		Season s1 = Season.SPRING;
		Season s2 = Season.AUTUMN;
		Season s3 = Season.SPRING;

		if (s1.equals(s2)) {
			System.out.println("s1 == s2");
		}

		if (s1.equals(s3)) {
			System.out.println("s1 == s3");
		}

		System.out.println(EnumDemo.getWeatherInHanoi(s1));
		System.out.println(EnumDemo.getWeatherInHanoi(Season.SUMMER));
	}
}
