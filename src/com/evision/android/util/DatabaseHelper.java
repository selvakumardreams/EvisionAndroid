package com.evision.android.util;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "ebook";

	// Table Names
	private static final String TABLE_BOOKMARK = "bookmark";

	// Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_BOOKNAME= "book_name";
	private static final String KEY_LINE_NUMBER= "line_number";
	private final ArrayList<BookMark> book_list = new ArrayList<BookMark>();


	// Table Create Statements
	// Bookmark table create statement
	private static final String CREATE_TABLE_BOOKMARK = "CREATE TABLE "
			+ TABLE_BOOKMARK + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BOOKNAME
			+ " TEXT," + KEY_LINE_NUMBER + " INTEGER" + ")";


	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(CREATE_TABLE_BOOKMARK);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_BOOKMARK);

		// create new tables
		onCreate(db);
	}

	public void createBookMark(BookMark book) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_BOOKNAME, book.getBookName());
		values.put(KEY_LINE_NUMBER, book.getLineNumber());
		// insert row
		db.insert(TABLE_BOOKMARK, null, values);
		db.close();

	}

	/**
	 * get single book
	 */
	public BookMark getBookMark(String bookName) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_BOOKMARK + " WHERE "
				+ KEY_BOOKNAME + " =? ";
		
		Cursor c = db.query(TABLE_BOOKMARK, new String[] { KEY_BOOKNAME,
				KEY_LINE_NUMBER}, KEY_BOOKNAME + "=?",
				new String[] {String.valueOf(bookName)},
				null, null, null, null);
		if (c != null ) {
			c.moveToFirst();
		}
		
		BookMark book= new BookMark();
		if (c.getCount() > 0) {
			book.setBookName(c.getString(c.getColumnIndex(KEY_BOOKNAME)));
			book.setLineNumber((c.getInt(c.getColumnIndex(KEY_LINE_NUMBER))));	
		} else {
			book.setBookName(bookName);
			book.setLineNumber(0);
		}

		c.close();
		db.close();
		return book;
	}

	/**
	 * Updating a bookmark
	 */
	public int updateBookMark(BookMark book) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_BOOKNAME, book.getBookName());
		values.put(KEY_LINE_NUMBER, book.getLineNumber());

		// updating row
		return db.update(TABLE_BOOKMARK, values, KEY_BOOKNAME + " = ?",
				new String[] { String.valueOf(book.getBookName()) });
	}

}