package java.io;

public class StringReader extends Reader {

	private char[] mChars;
	private int mLength;
	private int mNext = 0;

	public StringReader(String s) {
		if (s == null) {
			return;
		}

		mChars = s.toCharArray();
		mLength = mChars.length;
	}

	private void checkOpen() throws IOException {
		if (mChars == null)
			throw new IOException("stream is closed");
	}

	public int read() throws IOException {
		checkOpen();

		if (mNext >= mLength) {
			return -1;
		}

		char c = mChars[mNext];
		mNext++;

		return c;
	}

	public int read(char cbuf[], int off, int len) throws IOException {
		throw new UnsupportedOperationException("read() with buffer not supported");
	}

	public boolean ready() throws IOException {
		checkOpen();
		return true;
	}

	public void close() {
		mChars = null;
	}
}
