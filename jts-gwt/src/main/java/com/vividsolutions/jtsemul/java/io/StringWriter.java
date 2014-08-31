package java.io;

public class StringWriter extends Writer {

    private StringBuffer b;

    public StringWriter() {
        b = new StringBuffer();
    }

    public StringWriter(int capacity) {
        b = new StringBuffer(capacity);
    }

    public void write(int i) {
        b.append(i);
    }

    public void write(char cbuf[], int off, int len) {
        b.append(cbuf, off, len);
    }

    public void write(String str) {
        b.append(str);
    }

    public void write(String str, int off, int len)  {
        b.append(str.substring(off, off + len));
    }

    public StringWriter append(CharSequence s) {
        b.append(s);
        return this;
    }

    public StringWriter append(CharSequence s, int start, int end) {
        b.append(s, start, end);
        return this;
    }

    public StringWriter append(char c) {
        write(c);
        return this;
    }

    public String toString() {
        return b.toString();
    }

    public StringBuffer getBuffer() {
    	return b;
    }

    public void flush() {
    }

    public void close() throws IOException {
    }
}