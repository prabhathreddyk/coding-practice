package edu.code.process;

import java.time.LocalDateTime;

public class TimeConversions {

	public static void main(String[] args) {
		String time = LocalDateTime.now().toString();
		time = time.substring(time.indexOf("T")+1, time.indexOf("T")+6).replaceFirst("^0+(?!$)", "").replace(":", "");
		String givenTime = "04:00".replaceFirst("^0+(?!$)", "").replace(":", "");
		System.out.println(time);
		System.out.println(givenTime);
		System.out.println(givenTime.compareTo(time) < 0);
	}

}
