package com.evision.android.util;

import java.io.File;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.AndroidRuntimeException;

public class ExternalStorageReadOnlyOpenHelper {
	private SQLiteDatabase database;
	private File dbFile;
	private SQLiteDatabase.CursorFactory factory;

//	public static final String KEY_ROWID1= "_id";
//	public static final String KEY_num1= "num1";
//	public static final String KEY_WORD = "word";
	private static final String DATABASE_TABLE = "dictionary";
	private static final String COL_WORDS_WORD = "WORD";
	private static final String COL_WORDS_MEANING = "MEANING";
	private static final String COL_WORDS_BOOK_ID = "BID";
	public static final String TABLE_WORDS = "Words";
	private static final String COL_ROW_ID = "_id";
	
	public ExternalStorageReadOnlyOpenHelper(
			String dbFileName) {

		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			throw new AndroidRuntimeException(
					"External storage (SD-Card) not mounted");
		} 
		File appDbDir = new File(
				Environment.getExternalStorageDirectory() ,            
				DATABASE_TABLE);
		if (!appDbDir.exists()) {
			appDbDir.mkdirs();
		}
		this.dbFile = new File(appDbDir, dbFileName);
	}

	public boolean databaseFileExists() {
		return dbFile.exists();
	}

	private void open() {
		if (dbFile.exists()) {
			database = SQLiteDatabase.openDatabase(
					dbFile.getAbsolutePath(), 
					factory, 
					SQLiteDatabase.OPEN_READONLY);     
		}
	}

	public void close() {
		if (database != null ) {
			database.close();
			database = null;
		}
	}

	public SQLiteDatabase getReadableDatabase() {
		return getDatabase();
	}

	private SQLiteDatabase getDatabase() {
		if (database==null) {
			open();
		}
		return database;
	}    
	public String getMeaning(String word, SQLiteDatabase db) throws SQLException 
	{
		StringBuilder sb = new StringBuilder();
		sb.append(COL_WORDS_WORD).append(" = '").append(word).append("' ");
		// sb.append(" COLLATE NOCASE");
		Cursor mCursor = db.query(false, TABLE_WORDS, new String[]{COL_ROW_ID,
				COL_WORDS_WORD, COL_WORDS_MEANING, COL_WORDS_BOOK_ID}, sb.toString(), null, null, null, null, null);
		String data="";
		if (mCursor != null) {
			mCursor.moveToFirst();
			if (mCursor.moveToFirst()){
				do{
					data = mCursor.getString(2);
					// do what ever you want here
				}while(mCursor.moveToNext());
			}
			mCursor.close();
		}
		return data;
	}


}