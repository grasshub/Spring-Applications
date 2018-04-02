package com.springclass.configuration;

import org.springframework.context.annotation.Bean;

import com.springclass.messenger.*;

public class JavaConfig {

    //-------------------------------------------------------------------//
    // Lab: Create bean for numberOfTimes
	public int numberOfTimes() {
		return 10;
	}

    //-------------------------------------------------------------------//
    // Lab: Create bean for Messenger
	@Bean
	public Messenger messenger() {
		return new HelloMessenger();
	}

    //-------------------------------------------------------------------//
    // Lab:Create bean for MessageLooper
	@Bean
	public MessengerLooper messengerLooper() {
		
		MessengerLooper messengerLooper = new MessengerLooper();
		messengerLooper.setMessenger(messenger());
		messengerLooper.setNumberOfTimes(numberOfTimes());
		return messengerLooper;	
	}

}
