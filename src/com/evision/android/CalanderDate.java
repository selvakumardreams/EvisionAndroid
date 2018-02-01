package com.evision.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;


public class CalanderDate extends BaseActivity {
	/** Called when the activity is first created. */


	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer readTimer = null;
	CountDownTimer keyPressTimeout = null;

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
	boolean isGFKeyPressed = false;
	boolean b1 = false, b2 = false, b3 = false;
	private boolean starPressed = false;
	Intent launchApp = new Intent();


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


		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true | isGFKeyPressed == true
						|| starPressed == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					isGFKeyPressed = false;
					starPressed = false;
					lookuptable();
				} else {
					String str = BrailleCommonUtil.NumericLookUpTable(mTts);
					reStr=reStr+str;
					ResetBrailleKeys();
				}
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("0");
					reStr = reStr + "0";
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					speak("1");
					reStr = reStr + "1";
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
					reStr = reStr + "2";
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					speak("3");
					reStr = reStr + "3";
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
					reStr = reStr + "4";
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
					reStr = reStr + "5";
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
					reStr = reStr + "6";
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
					reStr = reStr + "7";
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
					reStr = reStr + "8";
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
					reStr = reStr + "9";
					num9Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		//		final Intent intEbookRead = new Intent(this,
		//				com.evision.android.ActiveMainMenu.class);

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("h");
					keyPressTimeout.cancel();
					isHgKeypress=setBoolean();
					b3 = setBoolean();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					if (starPressed) {
						if (reStr!=null) {
							if (reStr.length()!=0) {
								String lastChar = reStr.substring(reStr.length()-1, reStr.length());
								String delStr = "deleting  " + lastChar;
								reStr=reStr.substring(0,reStr.length()-1);
								speak(delStr);
								starPressed =false;
							}
							else
								speak(ResourceUtil.getStringFromResource(CalanderDate.this,R.string.nonum_delete));
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
					starPressed =setBoolean();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
					if (keyPressTimeout != null)
						keyPressTimeout.cancel();
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					keyPressTimeout.start();
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				} 
				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					isGFKeyPressed = setBoolean();
					b2 = setBoolean();
					speak("g");
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isFgKeypress == true) {
						stop();
						if (reStr.length() <= 2 && reStr != null) { // fix for Monkey Tool testing

							if(reStr.length() == 0) {
								speak("please enter the date "+reStr);
							} else if(Integer.parseInt(reStr) >= 1 && Integer.parseInt(reStr) <=31) {
								speak("date entered is "+reStr);
								waitForSpeechFinished();
								launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
								launchApp.putExtra("Caldate", reStr);
								launchApp.setClassName("com.evision.android", "com.evision.android.CalanderMonth"); 
								startActivity(launchApp); finish();

							} else	{
								speak("please enter the date bewteen 0 and 31"); 
								reStr="";
							}
						} else {
							speak("please enter the date bewteen 0 and 31");
							reStr="";
						}


					} else if (isHgKeypress == true) {
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.ACCESSORIESMENU); 
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;

			}
		});

		/*final Intent intActiveMenu = new Intent(this,
				com.datapadsystem.enotepad.class);	*/
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					speak("f");
					b1 = setBoolean();
					isFgKeypress = setBoolean();
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


	@Override
	public void onInit(int status) {
		super.onInit(status);
		speak(ResourceUtil.getStringFromResource(CalanderDate.this, R.string.Calanderdate_welcome));
		speak(ResourceUtil.getStringFromResource(CalanderDate.this, R.string.Calanderdate_fg));
	}
	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}
}
