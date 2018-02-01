package com.evision.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class NumberPad extends BaseActivity  {

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
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeyPressed= false;
	private boolean starHPressed = false;
	boolean b1 = false, b2 = false, b3 = false;
	String str ="",reStr="";
	Intent launchApp = new Intent();

	final Intent IntLaunch = new Intent();


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
				if (isFgKeypress == true || isHgKeypress == true || starHPressed == true
						|| isGFKeyPressed == true ) {
					isFgKeypress = false;
					isHgKeypress = false;
					isGFKeyPressed = false;
					starHPressed = false;
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
					keyPressTimeout.cancel();
					stop();
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					//BrailleCommonUtil.SetBrailleKeyflag(0);
					speak("0");
					reStr =reStr+"0";
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		// Replace EbookList.class with notepad.class by Meltronics team
		// final Intent IntNotepad = new Intent(Ebook.this, EbookList.class);
		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("Motion Event", "Action down"+ event.getAction());
				if (ACTION_DOWN == event.getAction()) {
					Log.i("Motion Event", "Action down");
					keyPressTimeout.cancel();
					stop();
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					Log.i("Motion Event", "Action up");
					keyPressTimeout.start();
					speak("1");
					reStr =reStr+"1";
					//BrailleCommonUtil.SetBrailleKeyflag(1);
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
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speak("2");
					reStr =reStr+"2";
					//BrailleCommonUtil.SetBrailleKeyflag(2);
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		// Please create new activity for Editing files
		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speak("3");
					reStr =reStr+"3";
					//BrailleCommonUtil.SetBrailleKeyflag(3);
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
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speak("4");
					reStr = reStr+"4";
					//BrailleCommonUtil.SetBrailleKeyflag(4);
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
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speak("5");
					reStr =reStr+"5";
					//BrailleCommonUtil.SetBrailleKeyflag(5);
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
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speak("6");
					reStr =reStr+"6";
					//BrailleCommonUtil.SetBrailleKeyflag(6);
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speak("7");
					reStr =reStr+"7";
					//BrailleCommonUtil.SetBrailleKeyflag(7);
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speak("8");
					reStr =reStr+"8";
					//BrailleCommonUtil.SetBrailleKeyflag(8);
					num8Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speak("9");
					reStr =reStr+"9";
					//BrailleCommonUtil.SetBrailleKeyflag(9);
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
					isFgKeypress = true;
					b1 = setBoolean();
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
					isGFKeyPressed = true;
					b2 = setBoolean();
					keyPressTimeout.cancel();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isFgKeypress == true) {
						if(reStr==""|| reStr==null) {
							speak(ResourceUtil.getStringFromResource(NumberPad.this,R.string.noPad_enter));
						} else {
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(reStr);
						}
					}
					//	ttsMenu();
					if (isHgKeypress == true) {
						Intent launchApp = new Intent();
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

						// launchApp.putExtra("TDLalarmhrs",reStr);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.CELLPHONE); 
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
					if (starHPressed) {
						if (reStr!=null) {
							if (reStr.length()!=0) {
								String lastChar = reStr.substring(reStr.length()-1, reStr.length());
								String delStr = "deleting  " + lastChar;
								reStr=reStr.substring(0,reStr.length()-1);
								speak(delStr);

							}
							else
								speak(ResourceUtil.getStringFromResource(NumberPad.this,R.string.nonum_delete));
						}
					}
					lookuptable();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
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
					keyPressTimeout.cancel();
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					/*launchApp.setClassName(PackageUtil.PACKAGE, "com.evision.android.SpeedDialing"); 
					 startActivity(launchApp); finish();*/
					starHPressed =true;
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
					ttsInvalidChoice();
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					//	ttsMenu();
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
		speak(ResourceUtil.getStringFromResource(NumberPad.this,R.string.noPad_welcome));
		speak(ResourceUtil.getStringFromResource(NumberPad.this,R.string.noPad_enter));
		speak(ResourceUtil.getStringFromResource(NumberPad.this,R.string.noPad_delete));
		// timer.start();
	}
}
