package com.evision.android;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.ResourceUtil;

public class CallsReceiverActivity extends BaseActivity {

	Button num0Button = null, num1Button = null, num2Button = null,
			num3Button = null, num4Button = null, num5Button = null,
			num6Button = null, num7Button = null, num8Button = null,
			num9Button = null, numFButton = null, numGButton = null,
			numHButton = null, numStarButton = null, numHashButton = null;

	public static final int ACTION_INIT = 1, ACTION_MID = 2, ACTION_FINAL = 3,
			ACTION_DOWN = 0, ACTION_UP = 1;
	private boolean fghHfgkeyPress = false;
	public static int actionSelect = 0, actionBack = 0;
	private boolean scrollDown = false, scrollUp = false;
	private String TAG = "ActiveMode";
	public static int counter = 0;
	CountDownTimer timer = null, startUpTimer = null, doubleClickTimer = null,
			keyPressTimeout = null;
	private boolean doubleClick = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	public static boolean isCallEnd = false;
	String imcomingcallnumber = "", incomingcallername = "", callMode = "";
	String id, num = "", phone, name, number;
	Uri parsedPhoneNumber;
	public List<String> Contacts = new ArrayList<String>();
	Intent launchApp = new Intent();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standbymode_screen);

		num0Button = (Button) findViewById(R.id.num0Button);
		num1Button = (Button) findViewById(R.id.num1Button);
		num2Button = (Button) findViewById(R.id.num2Button);
		num3Button = (Button) findViewById(R.id.num3Button);
		num4Button = (Button) findViewById(R.id.num4Button);
		num5Button = (Button) findViewById(R.id.num5Button);
		num6Button = (Button) findViewById(R.id.num6Button);
		num7Button = (Button) findViewById(R.id.num7Button);
		num8Button = (Button) findViewById(R.id.num8Button);
		num9Button = (Button) findViewById(R.id.num9Button);
		numFButton = (Button) findViewById(R.id.numFButton);
		numGButton = (Button) findViewById(R.id.numGButton);
		numHButton = (Button) findViewById(R.id.numHButton);
		numStarButton = (Button) findViewById(R.id.numStarButton);
		numHashButton = (Button) findViewById(R.id.numHashButton);


		Intent intent = getIntent();
		imcomingcallnumber = intent.getStringExtra("phno");
		Log.i("imcomingcallnumber", "imcomingcallnumber=" + imcomingcallnumber);
		callMode = intent.getStringExtra("incomingcall");
		Log.i("callMode", "callMode=" + callMode);

		startUpTimer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				ttsForActiveMode();
			}
		}.start();
		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (scrollDown == true || scrollUp == true
						|| fghHfgkeyPress == true || isFgKeypress == true
						|| isHgKeypress == true) {

					actionBack = 0;
					actionSelect = 0;
					scrollUp = false;
					scrollDown = false;
					fghHfgkeyPress = false;
					isFgKeypress = false;
					isHgKeypress = false;
				} else {

				}
			}
		};



		timer = new CountDownTimer(5000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				Log.i(TAG, "OnFinish : isSpeaking & Restarting --Global: "
						+ mTts.isSpeaking());
				if (!isCallEnd) {
					cancel();
					start();
					Log.i(TAG, "OnFinish : isSpeaking & Restarting");
				} else {
					Log.i(TAG, "OnFinish : isSpeaking & Restarting else");
					speak("Launching  Stand by Mode");
					isCallEnd = false;
					CallsReceiverActivity.this.finish();
				}
			}
		}.start();

		doubleClickTimer = new CountDownTimer(2000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				// This single key is pressed & timer expires and no docble key
				// pressed
				if (doubleClick == true) {
					doubleClick = false;
					ttsForActiveMode();
				}
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					timer.start();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.digit0));
					num0Button.setBackgroundResource(R.drawable.btn_green);
					// speak(speechOut, TextToSpeech.QUEUE_ADD, null);
				} else if (ACTION_UP == event.getAction()) {
					ttsForActiveMode();
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}

				return true;
			}
		});

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.digit1));
					num1Button.setBackgroundResource(R.drawable.btn_green);
					// speak("launching cell phone",
					// TextToSpeech.QUEUE_ADD,null);

				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);

					/* launchApp.setClassName(PackageUtil.PACKAGE, "com.datapadsystem.CellPhone"); 
					 startActivity(launchApp); finish();
					 */
				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// timer.start();

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.digit2));
					num2Button.setBackgroundResource(R.drawable.btn_green);
					//			speak("NotePad is under delopment",
					//				TextToSpeech.QUEUE_ADD, null);
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					// TODO ADD YOUR PACKAGE NAME & CLASS NAME TO LAUNCH APP

					/* launchApp.setClassName(PackageUtil.PACKAGE, "com.datapadsystem.enotepad"); 
					 startActivity(launchApp); finish();*/

				}
				return true;
			}
		});

		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.digit3));
					num3Button.setBackgroundResource(R.drawable.btn_green);
					//speak("welcome to Ebook", TextToSpeech.QUEUE_ADD, null);
				} else if (ACTION_UP == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					//launchApp.setClassName(PackageUtil.PACKAGE, "com.datapadsystem.Ebook");
					/*startActivity(launchApp);
					finish();*/
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.digit4));
					num4Button.setBackgroundResource(R.drawable.btn_green);
					//	speak("welcome to voice recorder",
					//		TextToSpeech.QUEUE_ADD, null);
				} else if (ACTION_UP == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					/*launchApp.setClassName(PackageUtil.PACKAGE,"com.datapadsystem.VoiceRecordMenu");
					startActivity(launchApp);*/
					//					finish();
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) 
				{
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.digit5));
					num5Button.setBackgroundResource(R.drawable.btn_green);

				} 
				else if (ACTION_UP == event.getAction()) 
				{
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					/*	launchApp.setClassName(PackageUtil.PACKAGE,
					"com.datapadsystem.locator");
				//	speak("locator app is under development",TextToSpeech.QUEUE_ADD, null);
					startActivity(launchApp);*/
					finish();


				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				// timer.start();

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.digit6));
					num6Button.setBackgroundResource(R.drawable.btn_green);
					//	speak("Guide app is under development",
					//		TextToSpeech.QUEUE_ADD, null);
				} else if (ACTION_UP == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
					/*launchApp.setClassName(PackageUtil.PACKAGE,"com.datapadsystem.Guide");
					startActivity(launchApp);*/
					finish();
					// TODO ADD YOUR PACKAGE NAME & CLASS NAME TO LAUNCH APP
					/*
					 * launchApp.setClassName(PackageUtil.PACKAGE,
					 * "ADD Guide APP Class"); startActivity(launchApp);
					 * finish();
					 */
				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.digit7));
					// TODO ACCESSORIES Screen ADD HERE
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num7Button.setBackgroundResource(R.drawable.btn_normal);
					// TODO ADD YOUR PACKAGE NAME & CLASS NAME TO LAUNCH APP

					/* check for the connectivity to opene the browser */





				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.digit8));
					num8Button.setBackgroundResource(R.drawable.btn_green);

				} else if (ACTION_UP == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_normal);
					/*launchApp.setClassName(PackageUtil.PACKAGE,"com.datapadsystem.AccessoryMenu"); 
					 startActivity(launchApp);*/
					finish();
				}
				return true;
			}
		});

		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.digit9));
					num9Button.setBackgroundResource(R.drawable.btn_green);
					//speak("Settings App is under development",
					//	TextToSpeech.QUEUE_ADD, null);
				} else if (ACTION_UP == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.H));
					keyPressTimeout.cancel();
					actionSelect ++;
					actionBack ++;
					isHgKeypress=true;
					//fghHfgkeyPress = true;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(actionBack==ACTION_FINAL)
					{
						actionSelect = 0;	
					}
					if(fghHfgkeyPress == true)
					{
						fghHfgkeyPress = false;
						finish();
					}
				}
				keyPressTimeout.start();
				numHButton.setBackgroundResource(R.drawable.btn_normal);

				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener() {
			CallOperations call = new CallOperations(getApplicationContext());
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.G));
					//	WebViewClientCallback.speak(" g ",0,null);
					keyPressTimeout.cancel();
					actionSelect ++;
					actionBack ++;	
					fghHfgkeyPress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) 
				{
					if (isFgKeypress == true) {
						Bundle bundle = getIntent().getExtras();
						if(bundle.getString("incomingcall").equals("incomingcall")){
							//webview.goForward();
							/*FOR ATTENDING THE CALL */
							/*-----------------------*/
							call.acceptCall();
						}

						isFgKeypress = false;
					}

					else if (isHgKeypress == true) {

						//Toast.makeText(getApplicationContext(), webview.getUrl().toString(), 5).show();
						/*FOR REJECTING THE CALL */
						/*-----------------------*/
						call.denyCall();
						speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.callrec_call));
						isHgKeypress = false;
					}
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(" F ");
					keyPressTimeout.cancel();
					actionSelect ++;
					actionBack ++;
					fghHfgkeyPress = true;
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					keyPressTimeout.start();
					numFButton.setBackgroundResource(R.drawable.btn_normal);
					actionBack = 0;
					//actionSelect = 0;	
				}
				return true;
			}
		});

		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					actionSelect ++;
					actionBack ++;	

					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.F));
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (actionBack ==ACTION_FINAL){
						actionBack = 0;
					}
					keyPressTimeout.start();
					numFButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.star));
					ttsInvalidChoice();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsForActiveMode();
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.hash));

					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					//	   Intent intent = new Intent();
					//     intent.setComponent(new ComponentName("com.mobiwhiz.ebookreader.app", "com.mobiwhiz.ebookreader.app.StartActivity"));
					//  intent.putExtra("destination",lv.getItemAtPosition(arg2).toString().trim());

					//      startActivityForResult(intent, 0);
					ttsInvalidChoice();
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
	}


	@Override
	public void onPause() {
		Log.i(TAG, "Active Mode onPause");

		if (timer != null)
			timer.cancel();
		if (startUpTimer != null)
			startUpTimer.cancel();
		if (doubleClickTimer != null)
			doubleClickTimer.cancel();

		if (mTts != null)
			stop();
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.i(TAG, "Active Mode OnStop");
		super.onStop();
	}

	@Override
	public void onResume() {
		Log.i(TAG, "Active Mode onResume");
		if (timer != null)
			timer.start();
		if (startUpTimer != null)
			startUpTimer.start();
		if (doubleClickTimer != null)
			doubleClickTimer.start();
		super.onResume();
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}

	public void ttsForActiveMode() {
		populateContacts();
		if((incomingcallername!=null && callMode!=null && callMode.equalsIgnoreCase("")) && incomingcallername.equalsIgnoreCase(""))
		{
			speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.callrec_unknown)+incomingcallername+" ");
		}
		else if((callMode!=null && callMode.equalsIgnoreCase("incomingcall")))
		{
			speak(ResourceUtil.getStringFromResource(CallsReceiverActivity.this,R.string.callrec_from)+incomingcallername);
			speak("  "+incomingcallername);
		}

	}

	private void populateContacts() 
	{  

		ContentResolver cr = getContentResolver();  

		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,  null, null, null);  

		if (cur.getCount() > 0) {  

			while (cur.moveToNext())
			{  
				// ID AND NAME FROM CONTACTS CONTRACTS  
				id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));  
				// GET PHONE NUMBERS WITH QUERY STRING  
				if (Integer.parseInt(cur .getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {  
					Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID  
							+ " = ?", new String[] { id }, null);  

					// WHILE WE HAVE CURSOR GET THE PHONE NUMERS  
					while (pCur.moveToNext())
					{  

						name = cur .getString(cur .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));     

						Log.i("callrecAct", "ID :" + id);  
						Log.i("callrecAct", "NAME :" + name);  

						// Do something with phones  
						//             int phoneColumn = pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
						//            number =pCur.getString(phoneColumn);

						phone = pCur.getString(pCur  
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));  
						Log.i("callrecAct", "phone :" + phone);  
						Log.i("callrecAct", "imcomingcallnumber :" + imcomingcallnumber);  
						if(phone.equalsIgnoreCase(imcomingcallnumber)) 
						{
							Log.i("callrecAct", "phone :" + phone);  
							Log.i("callrecAct", "imcomingcallnumber :" + imcomingcallnumber);  
							incomingcallername =name;
							// speak("  "+incomingcallername, TextToSpeech.QUEUE_ADD, null);
							break;
						}

						//  Contacts.add(name+" "+phone); //contacts with name and number
						Contacts.add(name);// only name of the contact

					} 

				}  
				//      pCur.close();  

			}  
		}
	}

}

