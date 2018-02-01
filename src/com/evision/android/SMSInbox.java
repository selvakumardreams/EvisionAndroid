package com.evision.android;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract.PhoneLookup;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class SMSInbox extends BaseActivity {

	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	public List<String> smsNameFullList;
	public List<String> smsNameFullList1;
	public static List<String> smsNameSubList;
	public static List<String> smsNameSubList1;
	String phonenameNum="";
	int fileIndex = 0;
	int smsId;

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

	private String TAG = "EBookList";
	// private boolean doubleClick = false;
	private boolean scrollDown = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeyPressed = false;
	boolean isConform = false;
	boolean isACKeypress = false;
	private boolean scrollUp = false;
	boolean b1 = false, b2 = false, b3 = false;

	String phone;
	String name;
	String number;
	String str = "";
	//	String selectedfile;
	String id;
	String smsNumber;
	public List<String> contacts = new ArrayList<String>();	
	public List<String> contactsNumber = new ArrayList<String>();
	public static List<Integer> smsThreadId = new ArrayList<Integer>();
	// 	public List<String> Number = new ArrayList<String>();	
	Intent launchApp = new Intent();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);
		Log.i(TAG, "EBook App launched");

		ReadSMSData sms = new ReadSMSData();
		sms.execute();

		// Get List of files based on extn local copy is maintained here
		smsNameFullList = contacts;
		smsNameFullList1 = contactsNumber;

		// Initially Full is same as Sublist
		smsNameSubList = smsNameFullList;
		smsNameSubList1 = smsNameFullList1;




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
				if (isFgKeypress == true || isHgKeypress == true || isACKeypress == true || isGFKeyPressed == true
						|| scrollUp == true || scrollDown == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
					isACKeypress = false;
					isGFKeyPressed  = false;
					lookuptable();
				}
				//				else {
				//					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
				//					ResetBrailleKeys();
				//										if (str != null || str != " ") // Invalid character
				//											try{
				//												//	ContactNameSubList = getFilterFilesBystring(ContactNameFullList, str);
				//											}
				//										catch(Exception e)
				//										{
				//											e.printStackTrace();
				//										}
				//				}

			}
		};

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					speak("1");
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
					stop();
					speak("2");
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
					stop();
					speak("3");
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
					stop();
					speak("4");
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
					stop();
					speak("5");
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
					stop();
					speak("6");
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
					keyPressTimeout.cancel();
					b3 = setBoolean();
					isHgKeypress = true;
					stop();
					speak("H");
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
					speak("G");
					b2 = setBoolean();
					isGFKeyPressed = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					// Select file to launch
					if (isFgKeypress == true) {
						if ((FilesExist() == true)) {
							stop();
							phonenameNum=smsNameSubList1.get(fileIndex);
							speak("Calling  "+phonenameNum);
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(phonenameNum);
							//make call to phonenumbe

						} 
					}
					if (isHgKeypress == true) {

						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.MESSAGEMAIN); 
						startActivity(launchApp);
						finish();
						isHgKeypress = false;
					}
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
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
					stop();
					b1 = setBoolean();
					speak("F");
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					if(isGFKeyPressed == true) {
						isGFKeyPressed = false;
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
					numFButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
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
						if (FilesExist() == true) {
							if (fileIndex > 0)
								fileIndex--;
							else
								fileIndex = smsNameSubList.size() - 1;
							Log.i(TAG, "EBook Up FileIndex= " + fileIndex);
							stop();
							speak("S M S From ."+ smsNameSubList.get(fileIndex));
						} else {
							stop();
							speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.smsInbox_nosms));
						}
					}
					numAButton.setBackgroundResource(R.drawable.btn_normal);
					isACKeypress = true;
					keyPressTimeout.start();
				}
				return true;
			}
		});

		numCButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					speak("C");
					numCButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(scrollDown == true) {
						if (FilesExist() == true) {
							if ((smsNameSubList.size() - 1) > fileIndex)
								fileIndex++;
							else
								fileIndex = 0;
							Log.i(TAG, "EBook Down FileIndex= " + fileIndex);
							stop();
							speak("S M S From ." +smsNameSubList.get(fileIndex));
						} else {
							stop();
							speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.smsInbox_nosms));
						}
					}
					isACKeypress = true;
					keyPressTimeout.start();
					numCButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numDButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					numDButton.setBackgroundResource(R.drawable.btn_green);
					stop();

					speak("D");
				} else if (ACTION_UP == event.getAction()) {
					if(isConform == true) {
						isConform = false;
						int threadId = smsThreadId.get(fileIndex);					
						/** 
						 * Delete the SMS from Inbox Based Threadid
						 *  **/
						getContentResolver().delete(Uri.parse("content://sms/conversations/" + threadId),null,null);
						speak(" Message Deleted");
					} else {
						isConform = true;
						speak(" press D to confirm delete ");
					}
					numDButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numBButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					scrollUp = true;
					scrollDown = true;
					stop();
					speak("B");
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
					stop();
					speak("E1");
					numE1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
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
					stop();
					speak("E 2");
					numE2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
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
	//	public final void sendMessage(final String Str, String message)
	//	{    
	//	Log.i("manju", "sendMessage function");
	//	    String SENT = "SMS_SENT";
	//	    String DELIVERED = "SMS_DELIVERED";
	//	    
	//	    PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
	//	    new Intent(SENT), 0);
	//
	//	    PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
	//	      new Intent(DELIVERED), 0);
	//
	//	    //---when the SMS has been sent---
	//	    registerReceiver(new BroadcastReceiver()
	//	    {
	//	 	   public void onReceive(Context arg0, Intent arg1) {
	//	            switch (getResultCode())
	//	            {
	//	                case Activity.RESULT_OK:
	//	                //    Toast.makeText(getBaseContext(), "SMS sent", 
	//	                 //           Toast.LENGTH_SHORT).show();
	//	                    speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.sms_send)+ Str );
	//	                   break;
	//	                    
	//	                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	//	                 //   Toast.makeText(getBaseContext(), "Generic failure", 
	//	                 //           Toast.LENGTH_SHORT).show();
	//	                    speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.generic_failure));
	//	                    break;
	//	                case SmsManager.RESULT_ERROR_NO_SERVICE:
	//	                    Toast.makeText(getBaseContext(), "No service", 
	//	                            Toast.LENGTH_SHORT).show();
	//	                    break;
	//	                case SmsManager.RESULT_ERROR_NULL_PDU:
	//	                    Toast.makeText(getBaseContext(), "Null PDU", 
	//	                            Toast.LENGTH_SHORT).show();
	//	                    break;
	//	                    
	//	                case SmsManager.RESULT_ERROR_RADIO_OFF:
	//	                    Toast.makeText(getBaseContext(), "Radio off", 
	//	                            Toast.LENGTH_SHORT).show();
	//	                    break;
	//	            }
	//	        }
	//			
	//	}, new IntentFilter(SENT));
	//	    //---when the SMS has been delivered---
	//	    registerReceiver(new BroadcastReceiver(){
	//	        @Override
	//	        public void onReceive(Context arg0, Intent arg1) {
	//	            switch (getResultCode())
	//	            {
	//	                case Activity.RESULT_OK:
	//	                	  speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.active_deliversms)+ Str );
	//
	//	                    Toast.makeText(getBaseContext(), "SMS delivered", 
	//	                    Toast.LENGTH_SHORT).show();
	//	                    break;
	//	                case Activity.RESULT_CANCELED:
	//	                    Toast.makeText(getBaseContext(), "SMS not delivered", 
	//	                    Toast.LENGTH_SHORT).show();
	//	                    break;                        
	//	            }
	//	        }
	//	},new IntentFilter(DELIVERED));        
	//
	////	SmsManager sms = SmsManager.getDefault();
	////	sms.sendTextMessage( phonenameNum, null, msgtwo, sentPI, deliveredPI);
	//
	//	}


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


	@Override
	public void onPause() {
		Log.i(TAG, "Active Mode onPause");

		if (timer != null)
			timer.cancel();
		/*
		 * if (startUpTimer!=null) startUpTimer.cancel();
		 */

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
		 */
		super.onResume();
	}

	public boolean FilesExist() {
		boolean flag = true;
		Log.i("TAG", "No contacts present :" + smsNameSubList.size());
		if (smsNameSubList.size() == 0) {
			stop();
			speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.smsInbox_nosms));
			flag = false;
		}
		return flag;
	}

	public void ResetBrailleKeys() 
	{
		BrailleCommonUtil.ResetBrailleKeyflag();
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}

	private void ttsForEbookListMenu() {
		stop();
		speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.smsInbox_welcome));
		speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.scroll_up));
		speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.smsInbox_read));
		speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.smsInbox_fg));
		speak(ResourceUtil.getStringFromResource(SMSInbox.this, R.string.smsInbox_delete));
		speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.previous_hg));
	}

	//	public static final List<String> getFilterFilesBystring(
	//			List<String> fileNameList, String str) {
	//
	//		ContactNameSubList.clear();
	//		int size = fileNameList.size();
	//
	//		String tempStr = null;
	//		char tmpStr = str.charAt(0);
	//		for (int i = 0; size > i; i++) {
	//			tempStr = fileNameList.get(i).toLowerCase();
	//			char chr = tempStr.charAt(0);
	//			// Compare the first letter of filename and make separate sublist
	//			if (chr == tmpStr) {
	//				ContactNameSubList.add(tempStr);
	//				Log.i("hi", "getFilterFilesBystring  : " + tempStr);
	//			}
	//		}
	//		return ContactNameSubList;
	//	}

	/**
	 * 
	 * TO read and Save the All the SMS
	 */
	private class ReadSMSData extends AsyncTask<String, Void, String>
	{
		//  TextView view = new TextView(this);

		@Override
		protected String doInBackground(String... params) {
			String smsName;
			Uri uriSMSURI = Uri.parse("content://sms/inbox");
			Cursor cur = getContentResolver().query(uriSMSURI,null,null,null,null);
			String sms="";
			if(cur.getCount() > 0) {
				while(cur.moveToNext() && cur.getCount() > 0) {
					smsNumber = cur.getString(cur.getColumnIndexOrThrow("address")).toString();
					name = getContactName(smsNumber);
					sms = cur.getString(cur.getColumnIndexOrThrow("body")).toString();			// Get the Contact Name
					smsId = cur.getInt(1);
					if(name != null) {
						smsName = name;
					} 	else {
						smsName = smsNumber;
					}
					contacts.add(smsName +" ."+ sms);
					contactsNumber.add(smsNumber);
					smsThreadId.add(smsId);
					//   speak(ResourceUtil.getStringFromResource(SMSInbox.this,R.string.smsInbox_inbox)+ sms );

				}
				if (cur != null)
					cur.close();
			}
			return null;
		}


	}

	/** 
	 * TO get the SMS Inbox Contact Name 
	 * @param phoneNumber
	 * @return
	 */

	private String getContactName(String phoneNumber) {

		Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
				Uri.encode(phoneNumber));
		Cursor cursor = getContentResolver().query(uri,
				new String[] { PhoneLookup.DISPLAY_NAME }, null, null, null);
		if (cursor == null) {
			return null;
		}
		String contactName = null;
		if (cursor.moveToFirst()) {
			contactName = cursor.getString(cursor
					.getColumnIndex(PhoneLookup.DISPLAY_NAME));
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return contactName;
	}

}
