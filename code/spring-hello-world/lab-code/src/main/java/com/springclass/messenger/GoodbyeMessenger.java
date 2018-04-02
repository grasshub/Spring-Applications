package com.springclass.messenger;

public class GoodbyeMessenger implements Messenger {

	public String greet() {
		
		final String greetString = "Goodbye World!";
		System.out.println(greetString);
		return greetString;
	}
}
