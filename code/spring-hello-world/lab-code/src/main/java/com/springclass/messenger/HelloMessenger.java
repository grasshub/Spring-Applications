package com.springclass.messenger;

public class HelloMessenger implements Messenger {
	
	public String greet() {
		final String greetString = "Hello World!";
		System.out.println(greetString);
		return greetString;
	}
}
