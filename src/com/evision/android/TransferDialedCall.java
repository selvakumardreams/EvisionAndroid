package com.evision.android;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.CallLog;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class TransferDialedCall extends BaseActivity  {


	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	// public final String MEDIA_PATH = new String("/mnt/sdcard/ebook");
	public List<String> contactNameFullList;
	public List<String> contactNumberFullList;
	public static List<String> contactNameSubList;
	public static List<String> contactNumberSubList;

	String saveName ="";
	String saveNumber = "";
	int fileIndex = 0;

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
	Button numE1Button = null;
	Button numE2Button = null;
	Button numFButton = null;
	Button numGButton = null;
	Button numHButton = null;
	//Button resetButton = null;

	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;

	CountDownTimer doubleClickTimer = null;
	// private boolean doubleClick = false;
	private boolean scrollDown = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeyPress = false;
	boolean isACKeypress = false;
	private boolean scrollUp = false;
	boolean b1 = false, b2 = false, b3 = false;
	public List<String> contacts = new ArrayList<String>();	
	public List<String> contactNumber = new ArrayList<String>();	

	Intent launchApp = new Intent();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);

		UpdatedDialCallList dialcall = new UpdatedDialCallList();
		dialcall.execute();

		// Get List of files based on extn local copy is maintained here
		contactNameFullList = contacts;
		contactNumberFullList = contactNumber;
		// Initially Full is same as Sublist
		contactNameSubList = contactNameFullList;
		contactNumberSubList = contactNumberFullList;


		// Initialize Lookup table variable
		ResetBrailleKeys();

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
		numE1Button = (Button) findViewById(R.id.numE1Button);
		numE2Button = (Button) findViewById(R.id.numE2Button);
		numFButton = (Button) findViewById(R.id.numFButton);
		numGButton = (Button) findViewById(R.id.numGButton);
		numHButton = (Button) findViewById(R.id.numHButton);

		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				ttsForEbookListMenu();
			}
		}.start();

		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true || isACKeypress == true || isGFKeyPress == true
						|| scrollUp == true || scrollDown == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
					isACKeypress = false;
					isGFKeyPress = false;
					lookuptable();
				} else {
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					ResetBrailleKeys();
					if (str != null || str != " ") {
						contactNameSubList = BrailleCommonUtil.getFilterFilesByNameString(contactNameFullList, str);
						contactNumberSubList = BrailleCommonUtil.getFilterFilesByNumberString(contactNumberFullList, str);
						if (isFileExit() == true)
							speak(contactNameSubList.get(0));
					}
				}
			}
		};

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(1);
					num1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(2);
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(3);
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(4);
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(5);
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(6);
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		// final Intent IntFilenameSend = new Intent(this, AppClassName);
		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("H");
					b3 = setBoolean();
					keyPressTimeout.cancel();
					isHgKeypress = true;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					lookuptable();
					keyPressTimeout.start();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					b2 = setBoolean();
					speak("G");
					isGFKeyPress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					// Select file to launch
					if (isFgKeypress == true) {
						if ((isFileExit() == true)) {
							stop();
							saveName = contactNameSubList.get(fileIndex);
							saveNumber = contactNumberSubList.get(fileIndex);
							if(saveName == "Unknown") {

								launchApp.putExtra("PhoneNumber", saveNumber);
								launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.ADDCONTACT);
								startActivity(launchApp);
								finish();
							}
							else {
								speak("The Conatct is already Saved");
							}
						} 
					}
					if (isHgKeypress == true) {

						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.CREATEPHONEBOOK); 
						startActivity(launchApp);
						finish();
						isHgKeypress = false;
					}
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
				}
				return true;
			}
		});

		// final Intent intActiveMenu = new Intent(this,
		// com.datapadsystem.ActiveMainMenu.class);
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					isFgKeypress = true;
					b1 = setBoolean();
					stop();
					speak("F");
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					numFButton.setBackgroundResource(R.drawable.btn_normal);
					if (isGFKeyPress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}

				}
				return true;
			}
		});

		numAButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {

					stop();
					speak("A");
					keyPressTimeout.cancel();
					numAButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					if (scrollUp == true) {
						if (isFileExit() == true) {
							if (fileIndex > 0)
								fileIndex--;
							else
								fileIndex = contactNameSubList.size() - 1;
							stop();
							String sayName = contactNameSubList.get(fileIndex);
							if (sayName.equals("Unknown")) {
								speak(contactNumberSubList.get(fileIndex));
							} else {
								speak(contactNameSubList.get(fileIndex));
							}
						} else {
							stop();
							speak(ResourceUtil.getStringFromResource(TransferDialedCall.this,R.string.moveCall_empty));
						}
					}
					numAButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
					isACKeypress = true;
				}
				return true;
			}
		});

		numCButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {

					stop();
					speak("C");
					keyPressTimeout.cancel();
					numCButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (scrollDown == true) {
						if (isFileExit() == true) {

							if ((contactNameSubList.size() - 1) > fileIndex)
								fileIndex++;
							else
								fileIndex = 0;
							stop();
							String sayName = contactNameSubList.get(fileIndex);
							if (sayName.equals("Unknown")) {
								speak(contactNumberSubList.get(fileIndex));
							} else {
								speak(contactNameSubList.get(fileIndex));
							}
						} else {
							stop();
							speak(ResourceUtil.getStringFromResource(TransferDialedCall.this,R.string.moveCall_empty));
						}
					}
					keyPressTimeout.start();
					isACKeypress = true;
					// ResetBrailleKeys();
					numCButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numDButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("D");
					contactNameFullList.clear();
					contactNumberFullList.clear();
					contactNameSubList.clear();
					contactNumberSubList.clear();
					contactNumber.clear();
					contacts.clear();
					numDButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numDButton.setBackgroundResource(R.drawable.btn_normal);

					UpdatedDialCallList dialcall = new UpdatedDialCallList();
					dialcall.execute();
					// Get List of files based on extn local copy is maintained here
					contactNameFullList = contacts;
					contactNumberFullList = contactNumber;
					// Initially Full is same as Sublist
					contactNameSubList = contactNameFullList;
					contactNumberSubList = contactNumberFullList;

				}
				return true;
			}
		});

		numBButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("B");
					scrollUp = true;
					scrollDown = true;
					keyPressTimeout.cancel();
					numBButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					numBButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					numE1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("e 1 ");

					// ttsForEbookListMenu();
					/*String str = BrailleCommonUtil.AlpabeticalLookUpTable(tts);
					ResetBrailleKeys();
					if (str != null) {
						ContactNameSubList = getFilterFilesBystring(ContactNameFullList, str);
					}*/

					numE1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					numE2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("e 2 ");

					// ttsForEbookListMenu();
					/*String str = BrailleCommonUtil.AlpabeticalLookUpTable(tts);
					ResetBrailleKeys();
					if (str != null) {
						ContactNameSubList = getFilterFilesBystring(ContactNameFullList, str);
					}*/
					numE2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

	}


	@Override
	public void onPause() {
		if (timer != null)
			timer.cancel();
		/*
		 * if (startUpTimer!=null) startUpTimer.cancel();
		 */if (doubleClickTimer != null)
			 doubleClickTimer.cancel();

		 if (mTts != null)
			 stop();
		 super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onResume() {
		if (timer != null)
			timer.start();
		/*
		 * if (startUpTimer!=null) startUpTimer.start();
		 */if (doubleClickTimer != null)
			 doubleClickTimer.start();
		 super.onResume();
	}

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

	public boolean isFileExit() {
		boolean flag = true;
		if (contactNameSubList.size() == 0) {
			stop();
			speak(ResourceUtil.getStringFromResource(TransferDialedCall.this,R.string.moveCall_empty));
			flag = false;
		}
		return flag;
	}

	private void ResetBrailleKeys()	{
		BrailleCommonUtil.ResetBrailleKeyflag();
	}


	private void ttsForEbookListMenu() {
		stop();
		speak(ResourceUtil.getStringFromResource(TransferDialedCall.this,R.string.moveCall_welcome));
		speak(ResourceUtil.getStringFromResource(TransferDialedCall.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(TransferDialedCall.this,R.string.scroll_up));
		speak(ResourceUtil.getStringFromResource(TransferDialedCall.this,R.string.scroll_down));
		speak(ResourceUtil.getStringFromResource(TransferDialedCall.this,R.string.fg_save));
	}

	/**
	 *  To get Dial call list from Call history 
	 *  
	 */

	private class UpdatedDialCallList extends AsyncTask<String , Void, String> {  

		@Override
		protected String doInBackground(String... params) {

			final String[] CALL_LOG_PROJECTION = new String[] {
					CallLog.Calls.DATE,
					CallLog.Calls.DURATION,
					CallLog.Calls.NUMBER,
					CallLog.Calls.CACHED_NAME,
					CallLog.Calls.TYPE,
			};

			ContentResolver resolver = getContentResolver();
			String where = CallLog.Calls.TYPE+"="+CallLog.Calls.OUTGOING_TYPE;
			Cursor callCursor = resolver.query(CallLog.Calls.CONTENT_URI, 
					CALL_LOG_PROJECTION,where, null, android.provider.CallLog.Calls.DATE+ " DESC");

			try {
				String contactName = null;
				if (callCursor == null || !callCursor.moveToFirst()) {
					speak( "No call logs at the moment");
				}
				int number = callCursor.getColumnIndex( CallLog.Calls.NUMBER ); 
				int type = callCursor.getColumnIndex( CallLog.Calls.TYPE );
				int date = callCursor.getColumnIndex( CallLog.Calls.DATE);
				int duration = callCursor.getColumnIndex( CallLog.Calls.DURATION);
				int callName = callCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);

				int count = 0;
				if (callCursor.moveToFirst()) {
					do {
						String phNumber = callCursor.getString( number );
						String name = callCursor.getString(callName);
						if (name != null) {
							contactName = name;
						} else {
							contactName = "Unknown";
						}						
						contacts.add(contactName);// only name of the contact
						contactNumber.add(phNumber);						
					} while (callCursor.moveToNext() && callCursor.getCount() > 0);
					if (callCursor != null) 
						callCursor.close();
				}


			} catch (Exception e) {

			} finally {
				if (callCursor != null) {
					callCursor.close();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// execution of result of Long time consuming operation

		}
		@Override
		protected void onPreExecute() {
			//nothing to do
		}

		protected void onProgressUpdate(String... text) {
			//nothing to do
		}
	}

}
