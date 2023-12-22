package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PracticeMain {

	public static void main(String[] args) {
		String size = "10.0 x 20.0 x 30.0";
		
		String[] sizes = size.split(" x ");
		Object[] newSizes = Arrays.stream(sizes).map((item) -> Double.parseDouble(item)).toArray();
		
		System.out.println(Arrays.toString(newSizes));
		System.out.println(Arrays.toString(sizes));
	}

}
