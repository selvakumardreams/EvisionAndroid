package com.evision.android.education;


import com.evision.android.BaseActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.ExternalStorageReadOnlyOpenHelper;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;


public class DictionarySearch extends BaseActivity {

	String AppendString=" ";
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer readTimer = null;
	CountDownTimer keyPressTimeout = null;
	CountDownTimer doubleClickTimer = null;
	CountDownTimer timer = null;
	Intent launchApp = new Intent();

	private SQLiteDatabase db;
	private static final String DBFILE = "dictionary";
	private ExternalStorageReadOnlyOpenHelper obj;

	boolean modChange = false;
	boolean doubleClick = false;
	boolean b1 = false, b2 = false, b3 = false;

	Button num1Button = null;
	Button num2Button = null;
	Button num3Button = null;
	Button num4Button = null;
	Button num5Button = null;
	Button num6Button = null;
	Button numAButton = null;
	Button numBButton = null;
	Button numCButton = null;
	Button numDButton = null;
	Button numFButton = null;
	Button numGButton = null;
	Button numHButton = null;
	Button numE1Button = null;
	Button numE2Button = null;
	public String reStr="";
	String delStr = "";

	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeypress = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);


		initialize(); //Initialize the buttons
		obj = new ExternalStorageReadOnlyOpenHelper(DBFILE);

		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {

			}
			@Override
			public void onFinish() {
				ttsFornoteReadMenu();
			}
		}.start();

		doubleClickTimer = new CountDownTimer(2000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {

			}

			@Override
			public void onFinish() {
				// This single key is pressed & timer expires and no docble key
				// pressed
				if ( doubleClick == true){
					doubleClick = false;					
				}
			}
		};


		keyPressTimeout = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}
			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true || isGFKeypress == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					isGFKeypress = false;
					lookuptable();
				} else{
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					if(reStr==null) { 
						reStr="";
						reStr = reStr + str;
						ResetBrailleKeys();
					} else {  
						reStr=reStr+str;
						ResetBrailleKeys();
					}
				}
			}
		};
	}
	private void initialize() {

		num1Button = (Button) findViewById(R.id.num1Button);
		num2Button = (Button) findViewById(R.id.num2Button);
		num3Button = (Button) findViewById(R.id.num3Button);
		num4Button = (Button) findViewById(R.id.num4Button);
		num5Button = (Button) findViewById(R.id.num5Button);
		num6Button = (Button) findViewById(R.id.num6Button);
		numAButton = (Button) findViewById(R.id.numAButton);
		numBButton = (Button) findViewById(R.id.numBButton);
		numCButton = (Button) findViewById(R.id.numCButton);
		numDButton = (Button) findViewById(R.id.numDButton);
		numFButton = (Button) findViewById(R.id.numFButton);
		numGButton = (Button) findViewById(R.id.numGButton);
		numE1Button = (Button) findViewById(R.id.numE1Button);
		numE2Button = (Button) findViewById(R.id.numE2Button);
		numHButton = (Button) findViewById(R.id.numHButton);

		num1Button.setOnTouchListener(buttonOnTouchListener);
		num2Button.setOnTouchListener(buttonOnTouchListener);
		num3Button.setOnTouchListener(buttonOnTouchListener);
		num4Button.setOnTouchListener(buttonOnTouchListener);
		num5Button.setOnTouchListener(buttonOnTouchListener);
		num6Button.setOnTouchListener(buttonOnTouchListener);
		numAButton.setOnTouchListener(buttonOnTouchListener);
		numBButton.setOnTouchListener(buttonOnTouchListener);
		numCButton.setOnTouchListener(buttonOnTouchListener);
		numDButton.setOnTouchListener(buttonOnTouchListener);
		numFButton.setOnTouchListener(buttonOnTouchListener);
		numGButton.setOnTouchListener(buttonOnTouchListener);
		numE1Button.setOnTouchListener(buttonOnTouchListener);
		numE2Button.setOnTouchListener(buttonOnTouchListener);
		numHButton.setOnTouchListener(buttonOnTouchListener);
	}

	private OnTouchListener buttonOnTouchListener  = new OnTouchListener() {


		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (v.getId()) {
			case R.id.num1Button:

				if (ACTION_DOWN == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					//speak("1");
					keyPressTimeout.cancel();
				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(1);
				}
				break;
			case R.id.num2Button:

				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					//speak("2");
					keyPressTimeout.cancel();
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(2);
				} 

				break;

			case R.id.num3Button:

				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					//speak("3");
					keyPressTimeout.cancel();
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(3);
				} 

				break;
			case R.id.num4Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					//speak("4");
					keyPressTimeout.cancel();
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(4);
				} 

				break;
			case R.id.num5Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					//speak("5");
					keyPressTimeout.cancel();
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(5);
				} 

				break;
			case R.id.num6Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					//speak("6");
					keyPressTimeout.cancel();
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(6);
				} 

				break;
			case R.id.numAButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("A");
					keyPressTimeout.cancel();
					numAButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					numAButton.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(DictionarySearch.this,R.string.not_assign));
				} 

				break;
			case R.id.numBButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("B");
					keyPressTimeout.cancel();
					numBButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					numBButton.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(DictionarySearch.this,R.string.not_assign));
				} 

				break;
			case R.id.numCButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("C");
					keyPressTimeout.cancel();
					numCButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					numCButton.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(DictionarySearch.this,R.string.not_assign));
				} 

				break;
			case R.id.numDButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("D");
					numDButton.setBackgroundResource(R.drawable.btn_green);  
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					numDButton.setBackgroundResource(R.drawable.btn_normal);
					if((reStr!=null)){
						if(reStr.length()!=0){
							String lastChar = reStr.substring(reStr.length()-1, reStr.length());
							if(lastChar.equalsIgnoreCase(" "))
								delStr = "deleting Space";
							else
								delStr = "deleting  " + lastChar;
							reStr=reStr.substring(0,reStr.length()-1);
							speak(delStr);
						}
						else
							speak(ResourceUtil.getStringFromResource(DictionarySearch.this,R.string.nochar_delete));
					}
				} 
				break;
			case R.id.numFButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("F");
					keyPressTimeout.cancel();
					isFgKeypress = true;
					b1 = setBoolean();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					if(isGFKeypress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
					numFButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
				} 
				break;

			case R.id.numGButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					b2 = setBoolean();
					speak("G");
					keyPressTimeout.cancel();
					isGFKeypress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					if (isFgKeypress == true) {
						stop();
						if (reStr == "" || reStr == null) {
							speak("please enter the Search word ");
						}
						else {
							searchWord(reStr);
							reStr = "";
						}
					}
					keyPressTimeout.start();
				}
				else if (isHgKeypress == true) {
					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					launchApp.setClassName(PackageUtil.PACKAGE , PackageUtil.EDUCATIONMENU); 
					startActivity(launchApp);
					finish();
					isHgKeypress = false;
				}
				break;

			case R.id.numHButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("H");
					b3 = setBoolean();
					keyPressTimeout.cancel();
					isHgKeypress = true;
					isGFKeypress = false;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					lookuptable();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
				} 


				break;
			case R.id.numE1Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("E 1");
					keyPressTimeout.cancel();
					numE1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					speak(ResourceUtil.getStringFromResource(DictionarySearch.this,R.string.not_assign));
					numE1Button.setBackgroundResource(R.drawable.btn_normal);
				} 

				break;
			case R.id.numE2Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("E 2");
					keyPressTimeout.cancel();
					numE2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					speak(ResourceUtil.getStringFromResource(DictionarySearch.this,R.string.not_assign));
					numE2Button.setBackgroundResource(R.drawable.btn_normal);
				} 
				break;
			default:
				break;
			}
			return true;
		}
	};

	private boolean setBoolean() {
		return true;
	}

	public void lookuptable() {

		if (b1 == true && b2 == true && b3 == true) {
			goActiveMode();
		}
		b1 = false;
		b2 = false;
		b3 = false;
	}

	public void ttsFornoteReadMenu() {
		speak(ResourceUtil.getStringFromResource(DictionarySearch.this, R.string.dictinatysearch_welcome));
		speak(ResourceUtil.getStringFromResource(DictionarySearch.this, R.string.dictinatysearch_search));
		speak(ResourceUtil.getStringFromResource(DictionarySearch.this, R.string.delete_d));
		speak(ResourceUtil.getStringFromResource(DictionarySearch.this, R.string.previous_hg));

	}
	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}

	public void searchWord(String value) {
		String toSpeak = "";
		String replaceString="<br />";
		if(obj.databaseFileExists()) {
			db = obj.getReadableDatabase();
			toSpeak = obj.getMeaning(value, db);
			obj.close();

			if (toSpeak.length() !=0 ) {
				speak(toSpeak.replace(replaceString, ""));
			} else {
				speak("No Meaning found for word" + value);	
			}
		} else {
			speak("Dictionary not downloaded properly" + value);	
		}

	}
}