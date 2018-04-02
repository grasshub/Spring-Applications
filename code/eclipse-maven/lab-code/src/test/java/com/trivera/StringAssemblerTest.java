package com.trivera;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to Trivera Technologies, LLC, Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2016 Trivera Technologies, LLC.
 * http://www.triveratech.com   
 * </p>
 * @author Trivera Technologies Tech Team.
 */
public class StringAssemblerTest {

	private StringAssembler assembler;
	
	@Before
	public void setup(){
		this.assembler = new StringAssembler();
	}
	
	@Test
	public void test() {
		
		assembler.add("12345678901234567890123456789012345678901");
		String result = assembler.toString();
		
		assertEquals("1234567890123456789012345678901234567890", result);
	}
	@Test
	public void test1() {
		
		assembler.add("1234567890123456789012345678901234567890");
		String result = assembler.toString();
		
		assertEquals("1234567890123456789012345678901234567890", result);
	}
	
	@Test
	public void test2() {
		
		assembler.add("12345678901234567890123456789");
		String result = assembler.toString();
		
		assertEquals("12345678901234567890123456789...........", result);
		assertEquals(40, result.length());
	}
	
	@Test
	public void test3() {
		
		assembler.add("12345678901234567890123456789");
		assembler.add("12345678901234567890123456789");
		String result = assembler.toString();
		
		assertEquals("12345678901234567890123456789...........;12345678901234567890123456789...........", result);
		assertEquals(81, result.length());
	}
	
	@Test
	public void test4() {
		
		assembler.add("A");
		assembler.add("B");
		assembler.add("C");
		assembler.add("D");
		String result = assembler.toString();
		
		assertEquals(163, result.length());
	}
	
	@Test
	public void testNull() {
		assembler.add(null);
		assembler.add("A");

		String result = assembler.toString();
		
		assertEquals(40, result.length());
		assertEquals('A', result.charAt(0));
	}
	
	@Test
	public void test5() {
		
		assembler.add("ABC;DEF");
		assembler.add("GHI;JKL;MNO");

		String result = assembler.toString();
		
		assertEquals("ABC#DEF.................................;GHI#JKL#MNO.............................", result);
	}
}
