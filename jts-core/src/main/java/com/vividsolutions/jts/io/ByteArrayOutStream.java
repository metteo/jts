package com.vividsolutions.jts.io;

import java.io.IOException;

public class ByteArrayOutStream implements OutStream {
	
    protected byte buf[];
    private int len;
    
    public ByteArrayOutStream() {
    	this(64);
	}
    
    public ByteArrayOutStream(int size) {
    	if(size < 0){
    		throw new IllegalArgumentException("Size can't be < 0");
    	}
    	
    	buf = new byte[size];
	}
	
	public void write(byte[] buf, int len) throws IOException {
		if(len < 0 || len > buf.length){
			throw new IndexOutOfBoundsException();
		}

		ensureSize(this.len + len);

		System.arraycopy(buf, 0, this.buf, this.len, len);
		this.len +=len;
	}

	private void ensureSize(int size) {
		if(size < buf.length) {
			//there is enough space
			return;
		}
		
		grow(size);
	}

	private void grow(int minSize) {
		int currentSize = buf.length;
		int newSize = currentSize;
		
		while(newSize < minSize) {
			int untilMaxVal = Integer.MAX_VALUE - newSize;
			if(untilMaxVal <= newSize) {
				throw new IllegalStateException("we are about to overflow !");
			}
			
			newSize *= 2;
		}

		byte[] newBuf = new byte[newSize];

		System.arraycopy(buf, 0, newBuf, 0, len);
		
		buf = newBuf;
	}
	
	public void reset() {
		len = 0;
	}
	
	public int size() {
		return len;
	}
	
	@Override
	public String toString() {
		return new String(buf, 0, len);
	}
	
    public byte[] toByteArray() {
        byte[] result = new byte[len];
        System.arraycopy(buf, 0, result, 0, len);
        return result;
    }

}
