package com.evision.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.ResourceUtil;

public class CallsDialerActivity extends BaseActivity  {

	Button num0Button = null, num1Button = null, num2Button = null,
			num3Button = null, num4Button = null, num5Button = null,
			num6Button = null, num7Button = null, num8Button = null,
			num9Button = null, numFButton = null, numGButton = null,
			numHButton = null, numStarButton = null, numHashButton = null;

	CountDownTimer keyPressTimeout = null;
	public static final int ACTION_INIT = 1;
	public static final int ACTION_MID = 2;
	public static final int ACTION_FINAL = 3;
	private boolean fghHfgkeyPress = false;
	public static int actionSelect = 0;
	public static int actionBack = 0;
	private boolean scrollDown = false;
	private boolean scrollUp = false;
	private int MY_DATA_CHECK_CODE = 0;
	private String TAG = "ActiveMode";
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	public static int counter = 0;
	CountDownTimer timer = null, startUpTimer = null;
	CountDownTimer doubleClickTimer = null;
	private boolean doubleClick = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	static boolean isCallEnd = false;

	Intent launchApp = new Intent();

	private TelephonyManager tm;

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


		startUpTimer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				ttsForActiveMode();
			}
		}.start();
		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (scrollDown == true || scrollUp == true
						|| fghHfgkeyPress == true || isFgKeypress == true || isHgKeypress == true ) {

					actionBack = 0;
					actionSelect = 0;
					scrollUp = false;
					scrollDown = false;
					fghHfgkeyPress = false;
					isFgKeypress = false;
					isHgKeypress = false;
				} else {
					//	String str = BrailleCommonUtil.AlpabeticalLookUpTable();
					//	ResetBrailleKeys();

				}
			}
		};


		timer = new CountDownTimer(5000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (!isCallEnd) {
					cancel();
					start();
				} else {
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.active_launch));
					isCallEnd = false;
					CallsDialerActivity.this.finish();
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.digit0));
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.digit1));
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.digit2));
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.digit3));
					num3Button.setBackgroundResource(R.drawable.btn_green);
					speak("welcome to Ebook");
				} else if (ACTION_UP == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					//					launchApp.setClassName(PackageUtil.PACKAGE, "com.datapadsystem.Ebook");
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.digit4));
					num4Button.setBackgroundResource(R.drawable.btn_green);
					speak("welcome to voice recorder");
				} else if (ACTION_UP == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					/*launchApp.setClassName(PackageUtil.PACKAGE,"com.datapadsystem.VoiceRecordMenu");
					startActivity(launchApp);*/
					finish();
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.digit5));
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.digit6));
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.digit7));
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.digit8));
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.digit9));
					num9Button.setBackgroundResource(R.drawable.btn_green);
					speak("Settings App is under development");
				} else if (ACTION_UP == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		final Intent intEbookRead = new Intent(this,
				com.evision.android.StandbyMainMenu.class);

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.H));
					keyPressTimeout.cancel();
					actionSelect ++;
					actionBack ++;
					isHgKeypress=true;
					//fghHfgkeyPress = true;
					boolean bh=true;
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
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.G));
					//	WebViewClientCallback.speak(" g ",0,null);
					keyPressTimeout.cancel();
					actionSelect ++;
					actionBack ++;	
					fghHfgkeyPress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) 
				{
					if (isFgKeypress == true) {
						isFgKeypress = false;
					}

					else if (isHgKeypress == true) {

						//Toast.makeText(getApplicationContext(), webview.getUrl().toString(), 5).show();
						/*FOR ENDING THE CALL */
						/*-----------------------*/
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

		//final Intent intActiveMenu = new Intent(this,com.datapadsystem.ActiveMainMenu.class);

		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					actionSelect ++;
					actionBack ++;	

					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.F));
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (actionBack ==ACTION_FINAL){
						//startActivity(intActiveMenu);
						//finish();
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.star));
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
					speak(ResourceUtil.getStringFromResource(CallsDialerActivity.this,R.string.hash));

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
		// singleClick = true;
		/*		timer.cancel();
		speak("Please select ", TextToSpeech.QUEUE_ADD, null);
		speak(" 1 for cell phone operation", TextToSpeech.QUEUE_ADD, null);
		speak(" 2 for notepad", TextToSpeech.QUEUE_ADD, null);
		speak(" 3 for e book reader", TextToSpeech.QUEUE_ADD, null);
		// speak(" 4 for optical reader",TextToSpeech.QUEUE_ADD, null);
		speak(" 4 for voice recorder", TextToSpeech.QUEUE_ADD, null);
		// timer.cancel();
		timer.start();
		speak(" 5 for LOCATOR ", TextToSpeech.QUEUE_ADD, null);
		// speak("Press 7 no operation available",TextToSpeech.QUEUE_ADD,
		// null);
		speak(" 6 for Guide", TextToSpeech.QUEUE_ADD, null);
		speak(" 7 for  Browser", TextToSpeech.QUEUE_ADD, null);
		speak(" 8 for accessories", TextToSpeech.QUEUE_ADD, null);
		speak(" 9 to settings ", TextToSpeech.QUEUE_ADD, null);
		speak(" 0 for repeat menu ", TextToSpeech.QUEUE_ADD, null);
		speak(" f g h go to stand by mode",TextToSpeech.QUEUE_ADD, null);
		timer.cancel();
		timer.start();
		 */	}

	/*private void getTeleService(Context context)
	  {
	    TelephonyManager localTelephonyManager1 = (TelephonyManager)context.getSystemService("phone");
	    this.tm = localTelephonyManager1;
	    try
	    {
	      int i = Log.v(this.TAG, "Get getTeleService...");
	      Class localClass = Class.forName(this.tm.getClass().getName());
	      Class[] arrayOfClass = new Class[0];
	      Method localMethod = localClass.getDeclaredMethod("getITelephony", arrayOfClass);
	      localMethod.setAccessible(true);
	      TelephonyManager localTelephonyManager2 = this.tm;
	      Object[] arrayOfObject = new Object[0];
	      ITelephony localITelephony = (ITelephony)localMethod.invoke(localTelephonyManager2, arrayOfObject);
	      this.telephonyService = localITelephony;
	      return;
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	      int j = Log.e(this.TAG, "FATAL ERROR: could not connect to telephony subsystem");
	      String str1 = this.TAG;
	      String str2 = "Exception object: " + localException;
	      int k = Log.e(str1, str2);
	    }
	  }*/

}

