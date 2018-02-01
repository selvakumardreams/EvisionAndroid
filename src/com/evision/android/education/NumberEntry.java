package com.evision.android.education;

import com.evision.android.BaseActivity;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.ResourceUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.speech.tts.TextToSpeech;

public class NumberEntry extends BaseActivity {


	/** Called when the activity is first created. */


	private int MY_DATA_CHECK_CODE = 0;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;


	CountDownTimer readTimer = null;
	CountDownTimer keyPressTimeout = null;
	String msg = "";
	boolean modChange = false;

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
	public String reStr="";

	boolean isFgKeypress = false;
	boolean isHgKeypress = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standbymode_screen);


		num0Button = (Button)findViewById(R.id.num0Button);
		num1Button = (Button)findViewById(R.id.num1Button);
		num2Button = (Button)findViewById(R.id.num2Button);
		num3Button = (Button)findViewById(R.id.num3Button);
		num4Button = (Button)findViewById(R.id.num4Button);
		num5Button = (Button)findViewById(R.id.num5Button);
		num6Button = (Button)findViewById(R.id.num6Button);
		num7Button = (Button)findViewById(R.id.num7Button);
		num8Button = (Button)findViewById(R.id.num8Button);
		num9Button = (Button)findViewById(R.id.num9Button);
		numFButton = (Button)findViewById(R.id.numFButton);
		numGButton = (Button)findViewById(R.id.numGButton);
		numHButton = (Button)findViewById(R.id.numHButton);
		numStarButton = (Button)findViewById(R.id.numStarButton);
		numHashButton = (Button)findViewById(R.id.numHashButton);
		// intent used of TTS


		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true) {
					isFgKeypress = false;
					isHgKeypress = false;
				} else {
					//String str = BrailleCommonUtil.NumericLookUpTable(mTts);
					ResetBrailleKeys();
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
					BrailleCommonUtil.SetBrailleKeyflagn(1);
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
					BrailleCommonUtil.SetBrailleKeyflagn(2);
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
					BrailleCommonUtil.SetBrailleKeyflagn(3);
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
					BrailleCommonUtil.SetBrailleKeyflagn(4);
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					//	stop();
					//	 tts.speak("5",TextToSpeech.QUEUE_ADD, null);
					keyPressTimeout.cancel();
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();

					BrailleCommonUtil.SetBrailleKeyflagn(5);
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					//stop();
					// tts.speak("6",TextToSpeech.QUEUE_ADD, null);
					keyPressTimeout.cancel();
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();

					BrailleCommonUtil.SetBrailleKeyflagn(6);
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					//	stop();
					//	 tts.speak("7",TextToSpeech.QUEUE_ADD, null);
					keyPressTimeout.cancel();
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();

					BrailleCommonUtil.SetBrailleKeyflagn(7);
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					//		stop();
					//		 tts.speak("8",TextToSpeech.QUEUE_ADD, null);
					keyPressTimeout.cancel();
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();

					BrailleCommonUtil.SetBrailleKeyflagn(8);
					num8Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					//		stop();
					//		 tts.speak("9",TextToSpeech.QUEUE_ADD, null);
					keyPressTimeout.cancel();
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflagn(9);
					num9Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					//		stop();
					//		 tts.speak("0",TextToSpeech.QUEUE_ADD, null);
					keyPressTimeout.cancel();
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();

					BrailleCommonUtil.SetBrailleKeyflagn(0);
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					//	timer.cancel();
					stop();
					speak("star");
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					//					Intent launchApp = new Intent();
					//					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					//					launchApp.putExtra("alphanumentry", TDLdate);
					//					launchApp.putExtra("Adate", Adate );
					//					launchApp.putExtra("Amonth", Amonth);
					//
					//					launchApp.putExtra("Aalarmhrs",Aalarmhrs);
					//					launchApp.putExtra("Aalarmmin",Aalarmmin);
					//					launchApp.putExtra("star", "true");
					//					launchApp.setClassName("com.datapadsystem", "com.datapadsystem.AnniversaryDescription"); 
					//					startActivity(launchApp);
					//					finish();
					//ttsInvalidChoice();
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					//timer.cancel();
					stop();
					speak("hash");
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsInvalidChoice();
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});



		/*final Intent intEbookRead = new Intent(this,
					com.datapadsystem.ActiveMainMenu.class);*/
		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("h");
					keyPressTimeout.cancel();
					isHgKeypress=true;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
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
					speak("g");
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} 	else if (ACTION_UP == event.getAction()) {
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					if (isFgKeypress == true) {
						stop();
						speak(ResourceUtil.getStringFromResource(NumberEntry.this,R.string.alarmday_hourenter) + reStr);
						isFgKeypress = false;
					} 	
				} else if (isHgKeypress == true) {
					//					Intent launchApp = new Intent();
					//					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					//					launchApp.setClassName("com.datapadsystem", "com.datapadsystem.AccessoryMenu"); 
					//					startActivity(launchApp);
					//					finish();
					isHgKeypress = false;
				}


				return true;

			}
		});

		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					speak("f");
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					keyPressTimeout.start();
					numFButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				mTts = new TextToSpeech(this,this);
			} 
			else {
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			} 
		}

	}

	public void onInit(int status) {        
		speak(ResourceUtil.getStringFromResource(NumberEntry.this,R.string.numeric_mode));
		speak(ResourceUtil.getStringFromResource(NumberEntry.this,R.string.press_star));
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}
}
