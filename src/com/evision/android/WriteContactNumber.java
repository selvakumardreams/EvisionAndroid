package com.evision.android;

import java.util.ArrayList;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class WriteContactNumber extends BaseActivity {

	Button num0Button = null;
	Button num1Button = null;
	Button num2Button = null;
	Button num3Button = null;
	Button num4Button = null;
	Button num5Button = null;
	Button num6Button = null;
	Button num7Button = null;
	Button num8Button = null;
	Button num9Button = null;
	Button numFButton = null;
	Button numGButton = null;
	Button numHButton = null;
	Button numStarButton = null;
	Button numHashButton = null;

	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;
	private boolean isStarHPressed = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeyPressed = false;
	boolean b1 = false, b2 = false, b3 = false;
	String str ="",reStr="";
	ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
	String name ="";
	String PhoneNumber;
	final Intent launchApp = new Intent();


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


		//  ** Get the Contact Name and Number ** // 
		Intent intent = getIntent();
		name = intent.getStringExtra("contactname");
		PhoneNumber = intent.getStringExtra("PhoneNumber");
		if ((PhoneNumber == null) || (PhoneNumber == "")) {
			Log.i("Phone Numbre is", "Direct save number");

		} else {
			Log.i("Phone Numbre is", PhoneNumber);
			reStr = PhoneNumber;
		}



		// *Initially Set the Emergency , Speed Dial Array Value *

		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				ttsMenu();
			}
		}.start();


		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true || isGFKeyPressed == true
						|| isStarHPressed == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					isGFKeyPressed = false;
					isStarHPressed = false;
					lookuptable();
				} else {/*
					str = BrailleCommonUtil.NumericLookUpTable(tts);
					reStr =reStr+str;
				//	ResetBrailleKeys();

				 */}
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("0");
					reStr =reStr+"0";
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("1");
					reStr =reStr+"1";
					num1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("2");
					reStr =reStr+"2";
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});



		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("3");
					reStr =reStr+"3";
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("4");
					reStr =reStr+"4";
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("5");
					reStr =reStr+"5";
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("6");
					reStr =reStr+"6";
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("7");
					reStr =reStr+"7";
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("8");
					reStr =reStr+"8";
					num8Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("9");
					reStr =reStr+"9";
					num9Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});



		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("f");
					b1 = setBoolean();
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(isGFKeyPressed == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();
					numFButton.setBackgroundResource(R.drawable.btn_normal);				}
				return true;
			}
		});

		/** 
		 * 
		 * To save the Contact Name and Number
		 */

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					speak("g" );
					isGFKeyPressed = true;
					b2 = setBoolean();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} 
				else if (ACTION_UP == event.getAction()) {
					if (isFgKeypress == true) {
						stop();
						if(reStr == "" || reStr == null || reStr == "0") {
							speak(ResourceUtil.getStringFromResource(WriteContactNumber.this,R.string.writeContact_contNo));

						}
						else {
							Context	myContext = null;
							ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
									.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
									.withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
									.build());

							//------------------------------------------------------ Names
							if(name != null) {
								ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
										.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
										.withValue(ContactsContract.Data.MIMETYPE,
												ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
												.withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name).build());
							}                       
							//------------------------------------------------------ Mobile Number
							if(reStr != null) {
								ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
										.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
										.withValue(ContactsContract.Data.MIMETYPE,
												ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
												.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, reStr)
												.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, 
														ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
														.build());

							}

							// Asking the Contact provider to create a new contact                  
							try {
								getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
								speak("contact name" + name +" Number."+ reStr +"added to phone book" );
								waitForSpeechFinished();
								Intent launchApp = new Intent();
								launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
								launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.PHONEBOOK); 
								startActivity(launchApp);
								finish();

							} 
							catch (Exception e)  {               
								e.printStackTrace();
								Toast.makeText(myContext, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
							}
						}
					} 
					/*if (isHgKeypress == true) {

						Intent launchApp = new Intent();
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						// launchApp.putExtra("TDLalarmhrs",reStr);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.ADDCONTACT); 
						startActivity(launchApp);
						finish();

						isHgKeypress = false;
					}*/
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;

			}
		});

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("h");
					keyPressTimeout.cancel();
					isHgKeypress=true;
					b3 = setBoolean();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isStarHPressed) {
						if (reStr!=null) {
							if (reStr.length()!=0) {
								String lastChar = reStr.substring(reStr.length()-1, reStr.length());
								String delStr = "deleting  " + lastChar;
								reStr=reStr.substring(0,reStr.length()-1);
								speak(delStr);
							}
							else
								speak(ResourceUtil.getStringFromResource(WriteContactNumber.this,R.string.nonum_delete));
						}
					}
					lookuptable();
					keyPressTimeout.start();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}

				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("star");
					numStarButton.setBackgroundResource(R.drawable.btn_green);
					isStarHPressed =true;
					keyPressTimeout.cancel();
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					/*launchApp.setClassName(PackageUtil.PACKAGE, "com.evision.android.SpeedDialing"); 
					 startActivity(launchApp); finish();*/
					keyPressTimeout.start();
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				} 
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("hash");
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
	}

	@Override
	public void onPause() {
		super.onPause();
		//reStr = "";
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

	private void ttsMenu() {

		if((PhoneNumber == null) || (PhoneNumber =="")) {
			speak(ResourceUtil.getStringFromResource(WriteContactNumber.this, R.string.writeContact_contNo));
			speak(ResourceUtil.getStringFromResource(WriteContactNumber.this, R.string.noPad_delete));
		} else {
			speak(ResourceUtil.getStringFromResource(WriteContactNumber.this, R.string.writeContact_save));
		}

	}
}