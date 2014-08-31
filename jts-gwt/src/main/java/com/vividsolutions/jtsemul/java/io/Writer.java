package java.io;

public abstract class Writer implements Appendable, Closeable, Flushable {

    protected Writer() {
    }
    
    protected Writer(Object o) {
    	this();
    }
	
    public void write(int c) throws IOException {

    }

    public void write(char chars[]) throws IOException {
        write(chars, 0, chars.length);
    }

    abstract public void write(char chars[], int off, int len) throws IOException;

    public void write(String str) throws IOException {

    }

    public void write(String str, int off, int len) throws IOException {

    }

    public Writer append(CharSequence csq) throws IOException {
    	return this;
    }

    public Writer append(CharSequence csq, int start, int end) throws IOException {
    	return this;
    }

    public Writer append(char c) throws IOException {
        return this;
    }

    abstract public void flush() throws IOException;

    abstract public void close() throws IOException;
}
