package com.evision.android;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class SpeedDialDBHandler {



	//public static final String DATABASE_CREATE= "create table if not exists TODOLISTDETAILS(todolist_priority text not null,todolist_details text not null,todolist_timedetails text not null,request_code integer not null,todolist text not null)";
	public static final String DATABASE_CREATE= "create table if not exists SPEEDDIALDETAILS(speed_dial_number text not null,phone_number text not null)";

	///	public static final String DATABASE_NAME = "TODOLIST";

	//	public static final String DATABASE_TABLE = "TODOLISTDETAILS" ;

	public static final String DATABASE_NAME = "SPEEDDIAL";

	public static final String DATABASE_TABLE = "SPEEDDIALDETAILS" ;

	public static final int DATABASE_VERSION = 1;

	private SQLiteDatabase db;
	private Context context;
	public int code;
	public String timeDetails;	
	public SpeedDialDBHandler(Context context)throws FileNotFoundException{
		if(db!=null){
			db.close();

		}
		db=context.openOrCreateDatabase(DATABASE_NAME, 0, null);
		db.execSQL(DATABASE_CREATE);

		this.context=context;		
	}

	public void closeDataBase(){
		if(db!=null)
			db.close();
	}

	public void createRow(String speedDialNumber,String phonenumber){
		ContentValues values= new ContentValues();
		values.put("speedDialNumber",speedDialNumber);
		values.put("phonenumber",phonenumber);
		//values.put("todolist_timedetails",toDoListTimeDetails);
		//values.put("request_code",requestCode);
		//values.put("todolist",toDoList);
		long rowId=db.insert(DATABASE_TABLE, null, values);
		if(rowId!=-1)
		{

		}		
	}


	public List<String> fetchAllToDoList(){
		List<String> arrayList=new ArrayList<String>();
		try{
			Cursor c=db.query(DATABASE_TABLE,new String[]{"speedDialNumber","phonenumber"},null, null, null, null,null);
			int numRows=c.getCount();
			c.moveToFirst();
			for(int i = 0 ;i < numRows ; i++ ){

				arrayList.add(c.getString(0)+":"+c.getString(1)+" by "+c.getString(2));
				c.moveToNext();
			}
			c.close();
		}catch(Exception e){
			Log.e("Exception on query",e.toString());
		}

		return arrayList;

	}

	public int fetchRequestCode(String toDoListPriority,String toDoListDetails,String toDoListTimeDetails){

		try{
			Cursor c=db.query(DATABASE_TABLE,new String[]{"speedDialNumber","phonenumber"},null, null, null, null, null);
			int numRows=c.getCount();
			c.moveToFirst();
			for(int i = 0 ;i < numRows ; i++ ){				
				if(c.getString(0).equals(toDoListPriority))
				{
					if(c.getString(1).equals(toDoListDetails))
					{
						if(c.getString(2).equals(toDoListTimeDetails))
						{
							code=c.getInt(3);
						}
					}
				}
				c.moveToNext();
			}
			c.close();
		}catch(Exception e){
			Log.e("Exception on query",e.toString());
		}

		return code;

	}
	public String fetchToDoListTimeDetails(String toDoListDetails){

		try{
			Cursor c=db.query(DATABASE_TABLE,new String[]{"speedDialNumber","phonenumber"},null, null, null, null, null);
			int numRows=c.getCount();
			c.moveToFirst();
			for(int i = 0 ;i < numRows ; i++ ){				
				if(c.getString(2).equals(toDoListDetails))
				{
					timeDetails=c.getString(2);

				}
				c.moveToNext();
			}
			c.close();
		}catch(Exception e){
			Log.e("Exception on query",e.toString());
		}

		return timeDetails;

	}

	public void deleteRow(int requestCode){	
		int rows=db.delete(DATABASE_TABLE,"request_code="+requestCode,null);
	}

	/*   UPDATING TABLE
      -------------	
	public void updateRow(String fileName,int currentPage){
		ContentValues values=new ContentValues();
		values.put("current_page", currentPage);
		db.update(DATABASE_TABLE, values, "filename"+"=?",new String[]{String.valueOf(fileName)});
		//fetchAllRows();
	}
	 */	

}
