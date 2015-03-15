package com.vividsolutions.jts.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * GWT compatible StreamTokenizer replacement for {@link WKTReader}.
 * <br>
 * It has hard coded behavior for WKT
 * 
 * @see java.io.StreamTokenizer
 * @author Grzegorz Nowak
 */
public class WKTTokenizer {

	public int ttype = TT_NOTHING;

	public static final int TT_EOF = -1;

	public static final int TT_EOL = '\n';

	public static final int TT_NUMBER = -2;

	public static final int TT_WORD = -3;

	private static final int TT_NOTHING = -4;

	public String sval;

	private int mLineNo = 1;

	private boolean mPushBack;

	private Reader mReader;

	protected int mCurrChar = '\0';
	protected int mPrevChar = '\0';

	public WKTTokenizer(Reader reader) {
		mReader = reader;
	}

	public WKTTokenizer(String s) {
		mReader = new StringReader(s);
	}

	public int nextToken() throws IOException {
		if (mPushBack) {
			mPushBack = false;
			return ttype;
		}

		read();

		return ttype;
	}

	private void read() throws IOException {

		int c = -1;
		while (true) {
			c = getCurrChar();

			switch (c) {
			case TT_EOF:
				ttype = TT_EOF;
				sval = null;
				return;

			case '\n':
				mLineNo++;
				getNextChar();
				continue;
				
			case '\r':
				getNextChar();
				continue;
				
			case '(':
				singleLetterToken('(');
				return;
				
			case ')':
				singleLetterToken(')');
				return;
				
			case ',':
				singleLetterToken(',');
				return;
				
			case '#':
				if(mPrevChar == '\0' || mPrevChar == TT_EOL) {
					readComment();
					continue;
				} else {
					singleLetterToken('#');
					return;
				}
			
			default:
				if(isWhitespace(c)) {
					getNextChar();
					continue;
				} else {
					readWord();
					return;
				}
			}

		}
	}
	
	private boolean isWhitespace(int c) {
		return 0 <= c && c <= ' ';
	}
	
	private boolean isSpecial(int c) {
		switch(c) {
		case TT_EOF:
		case '\n':
		case '\r':
		case '(':
		case ')':
		case ',':
		case '#':
			return true;
		default:
			return false;
		}
	}

	private void singleLetterToken(int c) throws IOException {
		ttype = c;
		sval = String.valueOf((char)c);
		getNextChar();
	}

	private void readComment() throws IOException {
		int c = getCurrChar();
		while(c != TT_EOL && c != TT_EOF) {
			c = getNextChar();
		}
	}

	private void readWord() throws IOException {
		StringBuilder sb = new StringBuilder();
		int c = getCurrChar();
		while (!isSpecial(c) && !isWhitespace(c)) {
			sb.append((char)c);
			c = getNextChar();
		}
		
		ttype = TT_WORD;
		sval = sb.toString();
	}

	protected int getNextChar() throws IOException {
		mPrevChar = mCurrChar;
		mCurrChar = mReader.read();
		return mCurrChar;
	}
	
	protected int getCurrChar() throws IOException {
		if(mCurrChar == '\0') {
			return getNextChar();
		}
		
		return mCurrChar;
	}
	
	protected int getPrevChar() throws IOException {
		return mPrevChar;
	}

	public void pushBack() {
		if (ttype != TT_NOTHING) {
			mPushBack = true;
		}
	}

	public int lineno() {
		return mLineNo;
	}

	/**
	 * Exists to have API compatible with StreamTokenizer
	 */
	public void resetSyntax() {
		//NOOP
	}

	/**
	 * Exists to have API compatible with StreamTokenizer
	 */
	public void wordChars(char c, char d) {
		//NOOP
	}

	/**
	 * Exists to have API compatible with StreamTokenizer
	 */
	public void wordChars(int i, int j) {
		//NOOP
	}

	/**
	 * Exists to have API compatible with StreamTokenizer
	 */
	public void whitespaceChars(int i, char c) {
		//NOOP
	}

	/**
	 * Exists to have API compatible with StreamTokenizer
	 */
	public void commentChar(char c) {
		//NOOP
	}
}
