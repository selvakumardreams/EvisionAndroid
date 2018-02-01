package com.evision.android.education;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.BaseActivity;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class VoiceRecordMainMenu extends BaseActivity {

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
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null;
	boolean isHgKeypress = false;
	boolean isGFKeypress = false;
	boolean b1 = false, b2 = false, b3 = false;
	CountDownTimer keyPressTimeout = null;
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
		//txtAppName.setText("Voice Recorder Menu");

		// intent used of TTS


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
				if (isGFKeypress == true || isHgKeypress == true) {
					isGFKeypress = false;
					isHgKeypress = false;
					lookuptable();
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
					speak("1");
				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.setClassName(PackageUtil.PACKAGE,PackageUtil.VOICERECORDER);
					startActivity(launchApp);
					finish();
				}
				return true;

			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("2");
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.VOICERECORDERFILELIST);
					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT,BrailleCommonUtil.APP_NAME_VR);
					launchApp.putExtra("className", "VoiceRecordMainMenu");
					startActivity(launchApp);
					finish();
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
					speak("3");
				} else if (ACTION_UP == event.getAction()) {

					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.DELETEVOICERECORD);
					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT,BrailleCommonUtil.APP_NAME_VR);
					launchApp.putExtra("classname", "VoiceRecordMainMenu");
					startActivity(launchApp);
					finish();
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					stop();
					speak("4");
					speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.voiceMenu_hardware));
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					stop();
					speak("5");
					speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.voiceMenu_hardware));
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					stop();
					speak("6");
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);

					speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.voiceMenu_hardware));
				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("7");
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// Searching for what ??
					speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this, R.string.inval_choice));
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					stop();
					speak("8");
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// Exit Ebook and start Active mode
					speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this, R.string.inval_choice));
					num8Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("9");
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this, R.string.inval_choice));
					num9Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					b3 = setBoolean();
					speak("h");
					keyPressTimeout.cancel();
					isHgKeypress=true;
					//fghHfgkeyPress = true;
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
					stop();
					speak("g");
					b2 = setBoolean();
					keyPressTimeout.cancel();
					isGFKeypress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isHgKeypress == true) {
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE,PackageUtil.EDUCATIONMENU); 
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
					b1 = setBoolean();
					speak("f");
					keyPressTimeout.cancel();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isGFKeypress == true) {
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

	public void onInit(int status) {

	}

	@Override
	public void onPause() {

		if (timer != null)
			timer.cancel();
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

		super.onResume();
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}

	public void ttsMenu() {
		stop();
		timer.cancel();
		speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.infoMenu_num4));

		speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.voiceMenu_one));
		speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.voiceMenu_two));
		speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.voiceMenu_three));
		//	speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.voiceMenu_four));
		//	speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.voiceMenu_five));
		//speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.voiceMenu_six));
		// speak("7 for searching");
		speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(VoiceRecordMainMenu.this,R.string.previous_hg));
		// timer.start();
	}
}
