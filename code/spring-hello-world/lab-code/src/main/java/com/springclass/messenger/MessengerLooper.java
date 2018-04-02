package com.springclass.messenger;

public class MessengerLooper {
	
	private Messenger messenger;
	private int numberOfTimes;
	private final String EMPTY = "";
	
	public int getNumberOfTimes() {
		return numberOfTimes;
	}
	public void setNumberOfTimes(int numberOfTimes) {
		this.numberOfTimes = numberOfTimes;
	}
	
	public Messenger getMessenger() {
		return messenger;
	}
	public void setMessenger(Messenger messenger) {
		this.messenger = messenger;
	}
	
	public String doIt() {
		int count = getNumberOfTimes();
		String result = EMPTY;
		
		while (count-- > 0) {
			result = getMessenger().greet();
		}	
		
		return result;
	}

}
