package design_pattern.structural.adapter.example2;

public class CheckNumberAdaptee {
	public boolean checkNumber(String input) {
		String regex = "[0-9]+";
		return input.matches(regex);
	}
}
