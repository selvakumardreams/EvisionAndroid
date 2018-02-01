package com.evision.android.voicerecord;


import com.evision.android.BaseActivity;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.ResourceUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class VoiceRecordMenu extends BaseActivity {

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
	/*	
	TextView txtAppName = null;

	Button resetButton = null;
	 */
	private int MY_DATA_CHECK_CODE = 0;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null;
	final Intent IntLaunch = new Intent();
	boolean isHgKeypress = false;
	private String TAG = "VoiceRecordMenu";
	CountDownTimer keyPressTimeout = null;
	private boolean scrollDown = false;
	private boolean scrollUp = false ;
	public static final int ACTION_INIT =1;
	public static final int ACTION_MID =2;
	public static final int ACTION_FINAL =3;
	private boolean fghHfgkeyPress = false;
	public static int actionSelect= 0;
	public static int actionBack = 0;
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
		//txtAppName.setText("Voice Recorder Menu");

		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				ttsMenu();
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
						|| fghHfgkeyPress == true) {

					actionBack = 0;
					actionSelect = 0;
					scrollUp = false;
					scrollDown = false;
					fghHfgkeyPress = false;
				} else {
					//String str = BrailleCommonUtil.AlpabeticalLookUpTable(tts);
					//ResetBrailleKeys();

				}
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					//ttsInvalidChoice();
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsMenu();
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		// Replace EbookList.class with notepad.class by Meltronics team
		// final Intent IntNotepad = new Intent(Ebook.this, EbookList.class);
		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.voiceMenu_notepad));
				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);

					// Launch Voice Recorder
					IntLaunch.setClassName("com.evision.android.voicerecord",
							"com.evision.android.voicerecord.VoiceRecorder");
					waitForShortSpeechFinished();
					startActivity(IntLaunch);
					finish();
				}
				return true;

			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					stop();
//					IntLaunch.setClassName("com.datapadsystem",
//							"com.datapadsystem.CreateFileList");
//
//					IntLaunch
//					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//					IntLaunch.putExtra(BrailleCommonUtil.APP_NAME_INTENT,
//							BrailleCommonUtil.APP_NAME_VR);
//					IntLaunch.putExtra("classname", "VoiceRecordMenu");
//					waitForShortSpeechFinished();
//					startActivity(IntLaunch);
//					finish();
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
					speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.voiceMenu_feature));
				} else if (ACTION_UP == event.getAction()) {
					ttsMenu();
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.voiceMenu_hardware));
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsMenu();
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.voiceMenu_hardware));
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsMenu();
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.create_dir));
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsMenu();
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					ttsInvalidChoice();
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsMenu();
					// Searching for what ??
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		final Intent intActive = new Intent(this,
				com.evision.android.ActiveMainMenu.class);
		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// Exit Ebook and start Active mode
					waitForShortSpeechFinished();
					startActivity(intActive);
					finish();
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

					num9Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		final Intent intEbookRead = new Intent(this,
				com.evision.android.ActiveMainMenu.class);
		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("h");

					keyPressTimeout.cancel();
					actionSelect ++;
					actionBack ++;
					isHgKeypress=true;
					//fghHfgkeyPress = true;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(actionBack==ACTION_FINAL)
					{
						waitForShortSpeechFinished();
						startActivity(intEbookRead);
						finish();
						actionSelect = 0;	
					}}
				keyPressTimeout.start();
				numHButton.setBackgroundResource(R.drawable.btn_normal);

				return true;
			}
		});
		final Intent Inthg = new Intent(this,
				com.evision.android.ActiveMainMenu.class);
		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("g");
					keyPressTimeout.cancel();
					actionSelect ++;
					actionBack ++;	
					fghHfgkeyPress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isHgKeypress == true) {
						//						Intent launchApp = new Intent();
						//						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						//						// launchApp.putExtra("TDLalarmhrs",reStr);
						//						launchApp.setClassName("com.datapadsystem", "com.datapadsystem.EducationMenu"); 
						//						startActivity(launchApp);
						//						finish();
						isHgKeypress = false;
					}
					keyPressTimeout.start();

					numGButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		final Intent intActiveMenu = new Intent(this,
				com.evision.android.ActiveMainMenu.class);		
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("f");
					keyPressTimeout.cancel();
					actionSelect ++;
					actionBack ++;
					fghHfgkeyPress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (actionBack ==ACTION_FINAL){
						startActivity(intActiveMenu);
						finish();
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
					ttsInvalidChoice();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsMenu();
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					ttsInvalidChoice();
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsMenu();
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
	}


	@Override
	public void onPause() {
		Log.i(TAG, "EBook onPause");

		if (timer != null)
			timer.cancel();
		if (mTts != null)
			stop();
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.i(TAG, "Voice Recorder OnStop");
		super.onStop();
	}

	@Override
	public void onResume() {
		Log.i(TAG, "Voice Reocrder onResume");
		if (timer != null)
			timer.start();

		super.onResume();
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}

	public void ttsMenu() {
		stop();
		timer.cancel();
		speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.infoMenu_num4));

		speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.voiceMenu_one));
		speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.voiceMenu_two));
		speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.voiceMenu_three));
		speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.voiceMenu_four));
		speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.voiceMenu_five));
		speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.voiceMenu_six));
		// speak("7 for searching", TextToSpeech.QUEUE_ADD, null);
		speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.exit_8));
		speak(ResourceUtil.getStringFromResource(VoiceRecordMenu.this,R.string.repeat_0));
		// timer.start();
	}
}
