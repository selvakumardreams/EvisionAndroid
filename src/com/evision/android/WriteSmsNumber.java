package com.evision.android;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class WriteSmsNumber extends BaseActivity {

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

	String msg;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;
	boolean isGFKeyPressed = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isStarHPressed = false;
	boolean b1 = false, b2 = false, b3 = false;
	String str ="",reStr="";
	final Intent launchApp = new Intent();
	private static final String MSGBODYMAIN = "msgbodymainone";

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
		msg = intent.getStringExtra(MSGBODYMAIN);

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
				}
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
					stop();
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
					stop();
				} else if (ACTION_UP == event.getAction()) {
					speak("2");
					reStr =reStr+"2";
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		// Please create new activity for Editing files
		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_green);
					stop();
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
					stop();
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
					stop();
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
					stop();
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
					stop();
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
					stop();
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
					stop();
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
					keyPressTimeout.cancel();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(isGFKeyPressed == true) {
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

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("g");
					keyPressTimeout.cancel();
					b2 = setBoolean();
					isGFKeyPressed = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isFgKeypress == true){
						if(reStr==""|| reStr==null){
							speak(ResourceUtil.getStringFromResource(WriteSmsNumber.this,R.string.PersonalInformation_enter));
						} else {
							if ((reStr.length()>0) && (msg.length()>0)) {
								speak("sending Message");
								sendMessage(reStr, msg);
							} else
								speak("Please enter both phone number and message.");
						}

					}
					//	ttsMenu();
					if (isHgKeypress == true) {
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.MESSAGEMENU); 
						launchApp.putExtra("msgbody", msg);
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

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("h");
					b3 = setBoolean();
					isHgKeypress = true;
					keyPressTimeout.cancel();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numHButton.setBackgroundResource(R.drawable.btn_normal);
					if (isStarHPressed) {
						if (reStr!=null) {
							if (reStr.length()!=0) {
								String lastChar = reStr.substring(reStr.length()-1, reStr.length());
								String delStr = "deleting  " + lastChar;
								reStr=reStr.substring(0,reStr.length()-1);
								speak(delStr);
							}
							else 
								speak(ResourceUtil.getStringFromResource(WriteSmsNumber.this,R.string.nonum_delete));
						}
					}
					lookuptable();
					keyPressTimeout.start();
				}
				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("star");
					keyPressTimeout.cancel();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					isStarHPressed =true;
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
		reStr = "";
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

	private void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}

	private void ttsMenu() {
		speak(ResourceUtil.getStringFromResource(WriteSmsNumber.this,R.string.WriteSmsNumber_select));
		speak(ResourceUtil.getStringFromResource(WriteSmsNumber.this,R.string.noPad_delete));
	}

	private final void sendMessage(final String Str, String message)
	{    
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";

		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
				new Intent(SENT), 0);

		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
				new Intent(DELIVERED), 0);

		//---when the SMS has been sent---
		registerReceiver(new BroadcastReceiver()
		{
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode())
				{
				case Activity.RESULT_OK:
					//    Toast.makeText(getBaseContext(), "SMS sent", 
					//           Toast.LENGTH_SHORT).show();
					speak(ResourceUtil.getStringFromResource(WriteSmsNumber.this,R.string.sms_send)+Str);
					break;

				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					//   Toast.makeText(getBaseContext(), "Generic failure", 
					//           Toast.LENGTH_SHORT).show();
					speak(ResourceUtil.getStringFromResource(WriteSmsNumber.this,R.string.generic_failure)
							);
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), "No service", 
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getBaseContext(), "Null PDU", 
							Toast.LENGTH_SHORT).show();
					break;

				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getBaseContext(), "Radio off", 
							Toast.LENGTH_SHORT).show();
					break;
				}
			}

		}, new IntentFilter(SENT));
		//---when the SMS has been delivered---
		registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode())
				{
				case Activity.RESULT_OK:
					speak(ResourceUtil.getStringFromResource(WriteSmsNumber.this,R.string.active_deliversms)+Str
							);
					break;
				case Activity.RESULT_CANCELED:
					break;                        
				}
			}
		},new IntentFilter(DELIVERED));        

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(reStr, null, msg, sentPI, deliveredPI);
	}
}
