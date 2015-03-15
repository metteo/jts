package com.vividsolutions.jts.io;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class WKTTokenizerTest {
	
	@Test
	public void itShouldReadNextChar() throws IOException {
		String s = "POINT (0 0)";
		char[] ca = s.toCharArray();
		
		WKTTokenizer wktt = new WKTTokenizer(s);
		
		int i = 0;
		for(; i < ca.length; i++) {
			char c = (char) wktt.getNextChar();
			assertEquals(ca[i], c);
		}
		
		assertEquals(-1, wktt.getNextChar());

	}
	
	@Test
	public void itShouldReadPrevChar() throws IOException {
		String s = "POINT (0 0)";
		char[] ca = s.toCharArray();
		
		WKTTokenizer wktt = new WKTTokenizer(s);
		
		int i = 0;
		for(; i < ca.length; i++) {
			char c = (char) wktt.getNextChar();
			assertEquals(ca[i], c);
			char prev = (char) wktt.getPrevChar();
			if(i == 0) {
				assertEquals('\0', prev);
			} else {
				assertEquals(ca[i-1], prev);
			}
			
			
		}
		
		assertEquals(-1, wktt.getNextChar());

	}
	
	@Test
	public void itShouldReadCurrChar() throws IOException {
		String s = "POINT (0 0)";
		char[] ca = s.toCharArray();
		
		WKTTokenizer wktt = new WKTTokenizer(s);
		
		int i = 0;
		for(; i < ca.length; i++) {
			char c = (char) wktt.getCurrChar();
			assertEquals(ca[i], c);
			
			wktt.getNextChar();
		}
		
		assertEquals(-1, wktt.getCurrChar());

	}
	
	@Test
	public void itShouldCountLineNo() throws IOException {
		String s = "P \n ( \r 8 \r\n ,";
		
		WKTTokenizer wktt = new WKTTokenizer(s);

		assertEquals(1, wktt.lineno());
		wktt.nextToken();
		assertEquals(1, wktt.lineno());
		wktt.nextToken();
		assertEquals(2, wktt.lineno());
		wktt.nextToken();
		assertEquals(2, wktt.lineno());
		wktt.nextToken();
		assertEquals(3, wktt.lineno());
		
		assertEquals(-1, wktt.nextToken());
		
	}
	
	@Test
	public void itShouldIgnoreComments() throws IOException {
		String s = "#P \n #( \r , \r\n# ,";
		
		WKTTokenizer wktt = new WKTTokenizer(s);
		
		wktt.nextToken();
		assertEquals("#", wktt.sval);
		wktt.nextToken();
		assertEquals("(", wktt.sval);
		wktt.nextToken();
		assertEquals(",", wktt.sval);
		
		assertEquals(-1, wktt.nextToken());
	}
	
	@Test
	public void itShouldReadWords() throws IOException {
		String s = "one two tree four";
		
		WKTTokenizer wktt = new WKTTokenizer(s);
		
		wktt.nextToken();
		assertEquals("one", wktt.sval);
		wktt.nextToken();
		assertEquals("two", wktt.sval);
		wktt.nextToken();
		assertEquals("tree", wktt.sval);
		wktt.nextToken();
		assertEquals("four", wktt.sval);
		
		assertEquals(-1, wktt.nextToken());
	}
	
	@Test
	public void itShouldPushBack() throws IOException {
		String s = "one two tree four";
		
		WKTTokenizer wktt = new WKTTokenizer(s);
		
		wktt.nextToken();
		assertEquals("one", wktt.sval);
		wktt.pushBack();
		wktt.nextToken();
		assertEquals("one", wktt.sval);
		wktt.nextToken();
		assertEquals("two", wktt.sval);
		wktt.nextToken();
		assertEquals("tree", wktt.sval);
		wktt.nextToken();
		assertEquals("four", wktt.sval);
		
		assertEquals(-1, wktt.nextToken());
	}
	
	@Test
	public void itShouldTokenizePolygon() throws IOException {
		String s = "POLYGON ((10 10, 10 20))";
		
		WKTTokenizer wktt = new WKTTokenizer(s);
		
		wktt.nextToken();
		assertEquals("POLYGON", wktt.sval);
		wktt.nextToken();
		assertEquals("(", wktt.sval);
		wktt.nextToken();
		assertEquals("(", wktt.sval);
		wktt.nextToken();
		assertEquals("10", wktt.sval);
		wktt.nextToken();
		assertEquals("10", wktt.sval);
		wktt.nextToken();
		assertEquals(",", wktt.sval);
		wktt.nextToken();
		assertEquals("10", wktt.sval);
		wktt.nextToken();
		assertEquals("20", wktt.sval);
		wktt.nextToken();
		assertEquals(")", wktt.sval);
		wktt.nextToken();
		assertEquals(")", wktt.sval);
		
		assertEquals(-1, wktt.nextToken());
	}
	
	
}
