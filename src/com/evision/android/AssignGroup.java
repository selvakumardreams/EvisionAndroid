package com.evision.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class AssignGroup extends BaseActivity {


	Button num0Button = null, num1Button = null, num2Button = null,
			num3Button = null, num4Button = null, num5Button = null,
			num6Button = null, num7Button = null, num8Button = null,
			num9Button = null, numFButton = null, numGButton = null,
			numHButton = null, numStarButton = null, numHashButton = null;
	boolean isGFKeyPressed = false;
	boolean isHGKeypress = false;
	boolean b1 = false, b2 = false, b3 = false;
	CountDownTimer keyPressTimeout = null;

	private String TAG = "ActiveMode";
	public static final int ACTION_DOWN = 0,ACTION_UP = 1;
	CountDownTimer timer = null, startUpTimer = null;

	String emergency;
	String speed;
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

		emergency = "emergency";
		speed = "speed";


		startUpTimer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				Log.i(TAG, "OnFinish : startUpTimer Global: " + mTts);
				ttsForActiveMode();
			}
		}.start();
		keyPressTimeout = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (isGFKeyPressed == true || isHGKeypress == true) {
					isGFKeyPressed = false;
					isHGKeypress = false;
					lookuptable();
				}
			}
		};


		timer = new CountDownTimer(30000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (isSpeaking()) {
					cancel();
					start();
				} 
			}
		}.start();


		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					timer.start();
					stop();
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.digit0));
					num0Button.setBackgroundResource(R.drawable.btn_green);
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
					speak("1");
					//tts.speak("1");
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.putExtra("assignValue",speed);
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.EMERGENCYDIALING); 
					startActivity(launchApp); 
					finish();

				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.digit2));
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.EMERGENCYDIALING); 
					launchApp.putExtra("assignValue",emergency);
					startActivity(launchApp); 
					finish();
				}
				return true;
			}
		});

		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.digit3));
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.setClassName(PackageUtil.PACKAGE,"com.evision.android.PersonalInformationName");
					startActivity(launchApp);
					finish();

				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.digit4));
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.wrong_choice));

				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.digit5));
					num5Button.setBackgroundResource(R.drawable.btn_green);

				} else if (ACTION_UP == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.wrong_choice));

				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.digit6));
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.wrong_choice));

				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.digit7));
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num7Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.wrong_choice));

				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.digit8));
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.wrong_choice));

				}
				return true;
			}
		});

		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.digit9));
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.wrong_choice));

				}
				return true;
			}
		});


		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					numHButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.H));
					keyPressTimeout.cancel();
					b3 = setBoolean();
					isHGKeypress = true;
				} else if (ACTION_UP == event.getAction()) {
					numHButton.setBackgroundResource(R.drawable.btn_normal);
					lookuptable();
					keyPressTimeout.start();
				}
				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.G));
					keyPressTimeout.cancel();
					isGFKeyPressed = true;
					b2 = setBoolean();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isHGKeypress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.CELLPHONE);
						startActivity(launchApp);
						finish();
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
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.F));
					keyPressTimeout.cancel();
					b1 = setBoolean();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(isGFKeyPressed == true) {
						isGFKeyPressed = false;
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
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
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.star));
					//ttsInvalidChoice();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
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
					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.hash));
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
		Log.i(TAG, "Active Mode onPause");

		if (timer != null)
			timer.cancel();
		if (startUpTimer != null)
			startUpTimer.cancel();


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

		super.onResume();
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
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

	public void ttsForActiveMode() {
		timer.cancel();
		speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.assignGroup_welcome));
		speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.assignGroup_one));
		speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.assignGroup_two));
		speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.assgnGroup_three));
		//		tts.speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.active_4));
		//		timer.start();
		//		tts.speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.active_5));
		//		tts.speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.active_6));
		//		tts.speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.active_7));
		//		tts.speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.active_8));
		//		tts.speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.active_9));*/
		speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.previous_hg));
		timer.cancel();
		timer.start();
	}

	//	public final void sendMessage(final String Str, String message)
	//	{    
	//		Log.i("manju", "sendMessage function");
	//		String SENT = "SMS_SENT";
	//		String DELIVERED = "SMS_DELIVERED";
	//
	//		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
	//				new Intent(SENT), 0);
	//
	//		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
	//				new Intent(DELIVERED), 0);
	//
	//		//---when the SMS has been sent---
	//		registerReceiver(new BroadcastReceiver()
	//		{
	//			public void onReceive(Context arg0, Intent arg1) {
	//				switch (getResultCode())
	//				{
	//				case Activity.RESULT_OK:
	//					//    Toast.makeText(getBaseContext(), "SMS sent", 
	//					//           Toast.LENGTH_SHORT).show();
	//					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.sms_send)+Str);
	//					break;
	//
	//				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	//					//   Toast.makeText(getBaseContext(), "Generic failure", 
	//					//           Toast.LENGTH_SHORT).show();
	//					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.generic_failure));
	//					break;
	//				case SmsManager.RESULT_ERROR_NO_SERVICE:
	//					Toast.makeText(getBaseContext(), "No service", 
	//							Toast.LENGTH_SHORT).show();
	//					break;
	//				case SmsManager.RESULT_ERROR_NULL_PDU:
	//					Toast.makeText(getBaseContext(), "Null PDU", 
	//							Toast.LENGTH_SHORT).show();
	//					break;
	//
	//				case SmsManager.RESULT_ERROR_RADIO_OFF:
	//					Toast.makeText(getBaseContext(), "Radio off", 
	//							Toast.LENGTH_SHORT).show();
	//					break;
	//				}
	//			}
	//
	//		}, new IntentFilter(SENT));
	//		//---when the SMS has been delivered---
	//		registerReceiver(new BroadcastReceiver(){
	//			@Override
	//			public void onReceive(Context arg0, Intent arg1) {
	//				switch (getResultCode())
	//				{
	//				case Activity.RESULT_OK:
	//					speak(ResourceUtil.getStringFromResource(AssignGroup.this,R.string.active_deliversms)+Str);
	//
	//					Toast.makeText(getBaseContext(), "SMS delivered", 
	//							Toast.LENGTH_SHORT).show();
	//					break;
	//				case Activity.RESULT_CANCELED:
	//					Toast.makeText(getBaseContext(), "SMS not delivered", 
	//							Toast.LENGTH_SHORT).show();
	//					break;                        
	//				}
	//			}
	//		},new IntentFilter(DELIVERED));        
	//
	//		SmsManager sms = SmsManager.getDefault();
	//		sms.sendTextMessage("", null, "robert in emergency situation at murgeshpalya, bangalore", sentPI, deliveredPI);
	//		sms.sendTextMessage("", null, "robert in emergency situation at murgeshpalya, bangalore", sentPI, deliveredPI);
	//	}

}
