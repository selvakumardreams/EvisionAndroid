package com.evision.android.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.util.Log;

class EFileFilter implements FilenameFilter {
	String file_name;

	public boolean accept(File dir, String name) {
		file_name = name.toLowerCase();
		return (file_name.endsWith(BrailleCommonUtil.fileNameExtn));
	}
}

public class BrailleCommonUtil {
	static String TAG = "BrailleCommonUtil";
	static String ascvalue = "";
	static int i = 0;
	static boolean b1 = false;
	private static boolean b2 = false, b3 = false;
	private static boolean b4 = false, b5 = false, b6 = false,b7 = false,b8 = false,b9= false,b0 = false;
	private static boolean bf = false, bg = false, bh = false, bstar = false,bhash = false;
	// Paths
	// Environment.getExternalStorageState();
	private final static String external = Environment
			.getExternalStorageDirectory().toString();
	public final static String MEDIA_PATH_EBOOK = external + "/ebook/";
	public final static String MEDIA_PATH_VR = external + "/voice_recorder/";
	public final static String MEDIA_PATH_ENOTEPAD = external + "/enotepad/";
	public final static String MEDIA_PATH_MP3 = external + "/mp3/";
	public final static String MEDIA_PATH_CELLPHONE = external + "/cellphone/";
	public final static String MEDIA_PATH_CUE = external + "/cue/";
	public final static String MEDIA_PATH_EVISION = external + "/evision/";
	public final static String DICTIONARY_PATH = external + "/dictionary/";
	public final static String CREATE_FOLDER = external;
	// ADD FILE PATH Here

	// APP Name List to create List of directory
	public final static String APP_NAME_EBOOK = "EBOOK";
	public final static String APP_NAME_ENOTEPAD = "ENOTEPAD";
	public final static String APP_NAME_VR = "VOICE_RECORDER";
	public final static String APP_NAME_MP3 = "MP3_PLAYER";
	public final static String APP_NAME_CELLPHONE = "CELLPHONE";
	public final static String APP_NAME_CUE = "CUE";
	public final static String APP_NAME_EVISION = "EVISION";
	public final static String APP_NAME_FOLDER = "CREATE";
	// ADD APP_NAME_XXX PATH Here

	// App Name used for Intent getStringExtra & putStringExtra
	public final static String APP_NAME_INTENT = "Evision";


	// Default extn;
	// File Filter based on Extn
	public static String EXTN_TXT = ".txt";
	public static String EXTN_THREEGP = ".3gp";
	public static String EXTN_MP3 = ".mp3";
	public static String EXTN_PDF = ".pdf";
	// static String EXTN_MP4 = "..mp4";

	// Default files extn .3gp
	public static String fileNameExtn = EXTN_THREEGP;

	// private static List<String> FileNameList = new ArrayList<String>();
	private static List<String> nameSubList = new ArrayList<String>();
	private static List<String> numberSubList = new ArrayList<String>();

	public static final void SetBrailleKeyflag(int key) {
		if (key <= 14 & key >= 0) {

			switch (key) {
			case 1:
				b1 = true;
				break;
			case 2:
				b2 = true;
				break;
			case 3:
				b3 = true;
				break;
			case 4:
				b4 = true;
				break;
			case 5:
				b5 = true;
				break;
			case 6:
				b6 = true;
				break;
			case 7:
				b7 = true;
				break;
			case 8:
				b8 = true;
				break;
			case 9:
				b9 = true;
				break;
			case 0:
				b0 = true;
				break;
			case 10:
				bf = true;
				break;
			case 11:
				bg = true;
				break;
			case 12:
				bh = true;
				break;
			case 13:
				bstar = true;
				break;
			case 14:
				bhash = true;
			}
		}
	}

	public static final void ResetBrailleKeyflag() {
		b1 = false;
		b2 = false;
		b3 = false;
		b4 = false;
		b5 = false;
		b6 = false;
		b7 = false;
		b8 = false;
		b9 = false;
		b0 = false;
		bf  = false;
		bg = false;
		bh = false;
		bstar = false;
		bhash = false;

	}
	public static final void SetBrailleKeyflagn(int key) {
		if (key <= 9 & key >= 0) {

			switch (key) {
			case 1:
				b1 = true;
				break;
			case 2:
				b2 = true;
				break;
			case 3:
				b3 = true;
				break;
			case 4:
				b4 = true;
				break;
			case 5:
				b5 = true;
				break;
			case 6:
				b6 = true;
				break;
			case 7:
				b7 = true;
				break;
			case 8:
				b8 = true;
				break;
			case 9:
				b9 = true;
				break;
			case 0:
				b0 = true;
				break;

			}
		}
	}

	public static final void ResetBrailleKeyflagn() {
		b1 = false;
		b2 = false;
		b3 = false;
		b4 = false;
		b5 = false;
		b6 = false;
		b7 = false;
		b8 = false;
		b9 = false;
		b0 = false;
	}

	public static final void ttsInvalidChoice(TextToSpeech tts) {
		if (tts != null)
		{
			tts.stop();
			tts.speak("Invalid choice ",TextToSpeech.QUEUE_ADD, null);
		}
	}

	public static final String AlpabeticalLookUpTable(TextToSpeech tts) {

		if (b1 == true && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false)
			ascvalue = "a";
		else if (b1 == true && b2 == true && b3 == false && b4 == false
				&& b5 == false && b6 == false)
			ascvalue = "b";
		else if (b1 == true && b2 == false && b3 == false && b4 == true
				&& b5 == false && b6 == false)
			ascvalue = "c";// "c";
		else if (b1 == true && b2 == false && b3 == false && b4 == true
				&& b5 == true && b6 == false)
			ascvalue = "d";
		else if (b1 == true && b2 == false && b3 == false && b4 == false
				&& b5 == true && b6 == false)
			ascvalue = "e";
		else if (b1 == true && b2 == true && b3 == false && b4 == true
				&& b5 == false && b6 == false)
			ascvalue = "f";
		else if (b1 == true && b2 == true && b3 == false && b4 == true
				&& b5 == true && b6 == false)
			ascvalue = "g";
		else if (b1 == true && b2 == true && b3 == false && b4 == false
				&& b5 == true && b6 == false)
			ascvalue = "h";
		else if (b1 == false && b2 == true && b3 == false && b4 == true
				&& b5 == false && b6 == false)
			ascvalue = "i";
		else if (b1 == false && b2 == true && b3 == false && b4 == true
				&& b5 == true && b6 == false)
			ascvalue = "j";
		else if (b1 == true && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == false)
			ascvalue = "k";
		else if (b1 == true && b2 == true && b3 == true && b4 == false
				&& b5 == false && b6 == false)
			ascvalue = "l";
		else if (b1 == true && b2 == false && b3 == true && b4 == true
				&& b5 == false && b6 == false)
			ascvalue = "m";
		else if (b1 == true && b2 == false && b3 == true && b4 == true
				&& b5 == true && b6 == false)
			ascvalue = "n";
		else if (b1 == true && b2 == false && b3 == true && b4 == false
				&& b5 == true && b6 == false)
			ascvalue = "o";
		else if (b1 == true && b2 == true && b3 == true && b4 == true
				&& b5 == false && b6 == false)
			ascvalue = "p";
		else if (b1 == true && b2 == true && b3 == true && b4 == true
				&& b5 == true && b6 == false)
			ascvalue = "q";
		else if (b1 == true && b2 == true && b3 == true && b4 == false
				&& b5 == true && b6 == false)
			ascvalue = "r";
		else if (b1 == false && b2 == true && b3 == true && b4 == true
				&& b5 == false && b6 == false)
			ascvalue = "s";
		else if (b1 == false && b2 == true && b3 == true && b4 == true
				&& b5 == true && b6 == false)
			ascvalue = "t";
		else if (b1 == true && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == true)
			ascvalue = "u";
		else if (b1 == true && b2 == true && b3 == true && b4 == false
				&& b5 == false && b6 == true)
			ascvalue = "v";
		else if (b1 == false && b2 == true && b3 == false && b4 == true
				&& b5 == true && b6 == true)
			ascvalue = "w";
		else if (b1 == true && b2 == false && b3 == true && b4 == true
				&& b5 == false && b6 == true)
			ascvalue = "x";
		else if (b1 == true && b2 == false && b3 == true && b4 == true
				&& b5 == true && b6 == true)
			ascvalue = "y";
		else if (b1 == true && b2 == false && b3 == true && b4 == false
				&& b5 == true && b6 == true)
			ascvalue = "z";
		else if (b1 == false && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == true) {
			ascvalue = " ";

		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false) {
			ascvalue = "";
			//tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}		
		else
			ascvalue = "";
		if (tts != null) {
			tts.stop();
			if (ascvalue.equalsIgnoreCase(" "))
				tts.speak("space", TextToSpeech.QUEUE_ADD, null);
			else if (ascvalue !="")
				tts.speak(ascvalue, TextToSpeech.QUEUE_ADD, null);
			else
				tts.speak("wrong key press", TextToSpeech.QUEUE_ADD, null);
		}
		// Reseting button values
		b1 = false;
		b2 = false;
		b3 = false;
		b4 = false;
		b5 = false;
		b6 = false;
		return ascvalue;
	}

	public static final String NumericLookUpTable(TextToSpeech tts) {
		if (b1 == true && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false){
			ascvalue = "1";
		}
		else if (b1 == false && b2 == true && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false){
			ascvalue = "2";
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false){
			ascvalue = "3";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == true
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false){
			ascvalue = "4";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == true && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false){
			ascvalue = "5";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false){
			ascvalue = "6";
		}
		else if (b1 ==false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == true 
				&& b8 == false && b9 == false && b0 == false ){
			ascvalue = "7";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == true && b9 == false && b0 == false){
			ascvalue = "8";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 ==false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == true && b0 == false){
			ascvalue = "9";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == true){
			ascvalue = "0";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false){
			ascvalue = "";
		}
		else
			ascvalue = "";

		if (tts != null) {
			tts.stop();
			if (ascvalue != null)
				tts.speak(ascvalue, TextToSpeech.QUEUE_ADD, null);

			else
				tts.speak("wrong key press", TextToSpeech.QUEUE_ADD, null);
		}
		// Reseting button values
		b1 = false;
		b2 = false;
		b3 = false;
		b4 = false;
		b5 = false;
		b6 = false;
		b7 = false;
		b8 = false;
		b9 = false;
		b0 = false;
		return ascvalue;
	}

	public static final String SpecialCharacterLookUpTable(TextToSpeech tts) {

		if (b1 == false && b2 == true && b3 == false && b4 == true
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "@";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == false && b3 == false  && b4 == true 
				&& b5 == false  && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = ".";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == true && b3 == false && b4 == false
				&& b5 == true && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = ":";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == true && b3 == true && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = ";";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == true
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "/";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}

		else if (b1 == false && b2 == true && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = ",";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{//ascvalue = """;//double quotes
			//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "'";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == true && b3 == true && b4 == false
				&& b5 == false && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "?";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{//ascvalue = "\";//back slash
			//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == true && b3 == true && b4 == false
				&& b5 == true && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "(";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{//ascvalue = ")";
			//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == true && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "+";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "-";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == false
				&& b5 == true && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "*";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == true && b3 == false && b4 == false
				&& b5 == true && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "$";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == true && b3 == true && b4 == false
				&& b5 == true && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "!";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == true
				&& b5 == true && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "#";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
		{ascvalue = "";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
		}
		/*else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false)
			{ascvalue = "(";
		//	tts.speak("space", TextToSpeech.QUEUE_ADD, null);
			}*/
		else
			ascvalue = "";

		//		Log.i(TAG, "NumericLookUpTable : b1= " + b1 + " b2= " + b2 + " b3= "
		//				+ b3 + " b4= " + b4 + " b5= " + b5 + " b6= " + b6
		//				+ " String value: " + ascvalue);
		if (tts != null) {
			tts.stop();
			if (ascvalue != null)
				tts.speak(ascvalue, TextToSpeech.QUEUE_ADD, null);

			else
				tts.speak("wrong key press", TextToSpeech.QUEUE_ADD, null);
		}
		// Reseting button values
		b1 = false;
		b2 = false;
		b3 = false;
		b4 = false;
		b5 = false;
		b6 = false;
		return ascvalue;
	}

	public static final List<String> getFileListBasedOnExtn(String path,
			String extn) {

		fileNameExtn = extn;
		List<String> FileNameList = new ArrayList<String>();

		File home = new File(path);
		if (!(home.exists()) && (!(home.mkdir()))) {
			Log.e(TAG, "getFileListBasedOnExtn:Dir create failed:" + path
					+ "GetPath =: " + home.getPath());
		}
		Log.e(TAG, "getFileListBasedOnExtn: Dir:" + path + " GetPath =: "
				+ home.getPath() + " based on extn:" + extn);

		File[] fileList = home.listFiles(new EFileFilter());
		if (fileList != null && fileList.length > 0) {
			for (File file : home.listFiles(new EFileFilter())) {
				FileNameList.add(file.getName());
				Log.i(TAG,"getFileListBasedOnExtn file Name: " + file.getName());
			}
		}
		Log.i(TAG, "getFileListBasedOnExtn file Name: " + FileNameList.size());
		return FileNameList;
	}

	public static final List<String> getFilterFilesByNameString(
			List<String> fileNameList, String str) {
		try {
			nameSubList.clear();
			char tmpStr = 0;
			int size = fileNameList.size();
			String tempStr = null;
			if (str.length() > 0) 
				tmpStr = str.charAt(0);
			for (int i = 0; size > i; i++) {
				tempStr = fileNameList.get(i).toLowerCase();
				char chr = tempStr.charAt(0);
				// Compare the first letter of filename and make separate sublist
				if (chr == tmpStr) {
					nameSubList.add(tempStr);
				}
			}
		}catch (StringIndexOutOfBoundsException e) {
			Log.e("Braille", e.toString());
		}
		return nameSubList;
	}


	public static final List<String> getFilterFilesByNumberString(
			List<String> fileNameList, String str) {

		try {
			numberSubList.clear();
			char tmpStr = 0;
			int size = fileNameList.size();
			String tempStr = null;
			if (str.length() > 0) 
				tmpStr = str.charAt(0);
			for (int i = 0; size > i; i++) {
				tempStr = fileNameList.get(i).toLowerCase();
				char chr = tempStr.charAt(0);
				// Compare the first letter of filename and make separate sublist
				if (chr == tmpStr) {
					numberSubList.add(tempStr);
				}
			} 
		} catch (StringIndexOutOfBoundsException e) {
			Log.e("Braille", e.toString());
		}
		return numberSubList;
	}

	/*public static final void create_directory(String dirname,String path,TextToSpeech tts)
	{   

		 File f = new File("path"+dirname);
		 Log.i(TAG, "create_directory  : "+ path +" : " + dirname+"/");

	        try{
	        if(f.mkdir())
	        {
	        	Log.i("jinsi", "dir created "  );
	            tts.speak("Directory  "+dirname+ " created", TextToSpeech.QUEUE_ADD, null);
	        }
	        else
	        {
	   	 tts.speak("Directory  "+dirname+ "is not created", TextToSpeech.QUEUE_ADD, null);
			     }
	        }

	        catch(Exception e){
	        e.printStackTrace();
	        }

	}*/

	public static final void create_directory(String dirname,String path,TextToSpeech tts)
	{   

		try {
			String dirName = path + dirname;
			File newFile = new File(dirName);

			if(newFile.exists()){
				tts.speak("Directory  "+ dirname + " is all ready created , plase Enter the New File Name", TextToSpeech.QUEUE_ADD, null);

				if(newFile.isDirectory()){
				}// else Toast.makeText(this, "isDirectory = false",Toast.LENGTH_LONG).show();
			} else {
				newFile.mkdirs();
				tts.speak("Directory  "+ dirname + ". is created", TextToSpeech.QUEUE_ADD, null);

			}
		} catch(Exception e){
		} 
	}


	///////landscape mode braille screen
	public static final String LandscapeLookUpTable(TextToSpeech tts) {
		if (b1 == true && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "A";
		}
		else if (b1 == false && b2 == true && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "B";
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "C";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == true
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "D";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == true && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "E";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "F";
		}
		else if (b1 ==false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == true 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "G";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == true && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "H";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 ==false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == true && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "I";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == true && 
				bf == false && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "J";
		}
		//////////////////
		else if (b1 == true && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == true && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "K";
		}
		else if (b1 == false && b2 == true && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == true && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "L";
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == true && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "M";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == true
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == true && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "N";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == true && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == true && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "O";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == true && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "P";
		}
		else if (b1 ==false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == true 
				&& b8 == false && b9 == false && b0 == false && 
				bf == true && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "Q";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == true && b9 == false && b0 == false && 
				bf == true && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "R";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 ==false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == true && b0 == false && 
				bf == true && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "S";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == true && 
				bf == true && bg == false && bh == false && bstar == false && bhash == false){
			ascvalue = "T";
		}
		///////////////

		else if (b1 == true && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == true && bh == false && bstar == false && bhash == false){
			ascvalue = "U";
		}
		else if (b1 == false && b2 == true && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == true && bh == false && bstar == false && bhash == false){
			ascvalue = "V";
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == true && bh == false && bstar == false && bhash == false){
			ascvalue = "W";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == true
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == true && bh == false && bstar == false && bhash == false){
			ascvalue = "X";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == true && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == true && bh == false && bstar == false && bhash == false){
			ascvalue = "Y";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == true && bh == false && bstar == false && bhash == false){
			ascvalue = "Z";
		}
		else if (b1 ==false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == true 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == true && bh == false && bstar == false && bhash == false){
			ascvalue = ",";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == true && b9 == false && b0 == false && 
				bf == false && bg == true && bh == false && bstar == false && bhash == false){
			ascvalue = ".";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 ==false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == true && b0 == false && 
				bf == false && bg == true && bh == false && bstar == false && bhash == false){
			ascvalue = ";";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == true && 
				bf == false && bg == true && bh == false && bstar == false && bhash == false){
			ascvalue = "\\?";
		}

		/////////

		else if (b1 == true && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == true && bstar == false && bhash == false){
			ascvalue = "1";
		}
		else if (b1 == false && b2 == true && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == true && bstar == false && bhash == false){
			ascvalue = "2";
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == true && bstar == false && bhash == false){
			ascvalue = "3";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == true
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == true && bstar == false && bhash == false){
			ascvalue = "4";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == true && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == true && bstar == false && bhash == false){
			ascvalue = "5";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == true && bstar == false && bhash == false){
			ascvalue = "6";
		}
		else if (b1 ==false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == true 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == true && bstar == false && bhash == false){
			ascvalue = "7";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == true && b9 == false && b0 == false && 
				bf == false && bg == false && bh == true && bstar == false && bhash == false){
			ascvalue = "8";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 ==false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == true && b0 == false && 
				bf == false && bg == false && bh == true && bstar == false && bhash == false){
			ascvalue = "9";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == true && 
				bf == false && bg == false && bh == true && bstar == false && bhash == false){
			ascvalue = "0";
		}
		///////////////////////////

		else if (b1 == true && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == true){
			ascvalue = ":";
		}
		else if (b1 == false && b2 == true && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == true){
			ascvalue = "@";
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == true){
			ascvalue = "&";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == true
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == true){
			ascvalue = "%";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == true && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == true){
			ascvalue = "'";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == true){
			ascvalue = Character.toString('"');
		}
		else if (b1 ==false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == true 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == true){
			ascvalue = "<";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == true && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == true){
			ascvalue = ">";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 ==false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == true && b0 == false && 
				bf == false && bg == false && bh == false && bstar == false && bhash == true){
			ascvalue = "(";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == true && 
				bf == false && bg == false && bh == false && bstar == false && bhash == true){
			ascvalue = ")";
		}

		/////////////

		else if (b1 == true && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == true && bhash == false){
			ascvalue = "+";
		}
		else if (b1 == false && b2 == true && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == true && bhash == false){
			ascvalue = "-";
		}
		else if (b1 == false && b2 == false && b3 == true && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == true && bhash == false){
			ascvalue = "*";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == true
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == true && bhash == false){
			ascvalue = "/";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == true && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == true && bhash == false){
			ascvalue = "=";
		}
		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == true && b7 == false 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == true && bhash == false){
			ascvalue = "backspacepressed";
		}

		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == true 
				&& b8 == false && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == true && bhash == false){
			ascvalue = "space";
		}

		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == true && b9 == false && b0 == false && 
				bf == false && bg == false && bh == false && bstar == true && bhash == false){
			ascvalue = "deletepressed";//space
		}

		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == true && b0 == false && 
				bf == false && bg == false && bh == false && bstar == true && bhash == false){
			ascvalue = "enterpressed";//enterpressed
		}

		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == true && 
				bf == false && bg == false && bh == false && bstar == true && bhash == false){
			ascvalue = "uppercasepressed";//uppercase
		}



		else if (b1 == false && b2 == false && b3 == false && b4 == false
				&& b5 == false && b6 == false && b7 == false 
				&& b8 == false && b9 == false && b0 == false){
			ascvalue = "";
		} else
			ascvalue = "";

		Log.i(TAG, "NumericLookUpTable : b1= " + b1 + " b2= " + b2 + " b3= "
				+ b3 + " b4= " + b4 + " b5= " + b5 + " b6= " + b6
				+ " String value: " + ascvalue);
		if (tts != null) {
			tts.stop();
			if (ascvalue != null) {
				if(ascvalue.equalsIgnoreCase("deletepressed")) {
					tts.speak("delete key pressed", TextToSpeech.QUEUE_ADD, null);
				} else if(ascvalue.equalsIgnoreCase("")) {
					tts.speak("wrong key press", TextToSpeech.QUEUE_ADD, null);
				} else if(ascvalue =="\\?") {
					tts.speak("Question mark", TextToSpeech.QUEUE_ADD, null);
				} else
					tts.speak(ascvalue, TextToSpeech.QUEUE_ADD, null);
			}

		}
		// Reseting button values
		b1 = false;
		b2 = false;
		b3 = false;
		b4 = false;
		b5 = false;
		b6 = false;
		b7 = false;
		b8 = false;
		b9 = false;
		b0 = false;
		bf  = false;
		bg = false;
		bh = false;
		bstar = false;
		bhash = false;
		return ascvalue;
	}


}