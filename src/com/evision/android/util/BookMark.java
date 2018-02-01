package com.evision.android.util;

public class BookMark {
	
	String bookName;
	long lineNumber;
	
	public BookMark() {
		
	}
	
	public BookMark(String bookName, long lineNumber) {
		this.bookName = bookName;
		this.lineNumber = lineNumber;
	}
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public long getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(long lineNumber) {
		this.lineNumber = lineNumber;
	}

	
	
	

}
