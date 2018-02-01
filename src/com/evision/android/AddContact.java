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



public class AddContact extends BaseActivity {
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer keyPressTimeout = null;
	CountDownTimer timer = null;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeyPressed = false;
	boolean b1 = false, b2 = false, b3 = false;
	String PhoneNumber;
	Intent launchApp = new Intent();
	public String reStr="";
	String delStr = "";

	Button num1Button = null;
	Button num2Button = null;
	Button num3Button = null;
	Button num4Button = null;
	Button num5Button = null;
	Button num6Button = null;
	Button numAButton = null;
	Button numBButton = null;
	Button numCButton = null;
	Button numDButton = null;
	Button numE1Button = null;
	Button numE2Button = null;
	Button numFButton = null;
	Button numGButton = null;
	Button numHButton = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);

		num1Button = (Button)findViewById(R.id.num1Button);
		num2Button = (Button)findViewById(R.id.num2Button);
		num3Button = (Button)findViewById(R.id.num3Button);
		num4Button = (Button)findViewById(R.id.num4Button);
		num5Button = (Button)findViewById(R.id.num5Button);
		num6Button = (Button)findViewById(R.id.num6Button);
		numAButton = (Button)findViewById(R.id.numAButton);
		numBButton = (Button)findViewById(R.id.numBButton);
		numCButton = (Button)findViewById(R.id.numCButton);
		numDButton = (Button)findViewById(R.id.numDButton);
		numE1Button = (Button)findViewById(R.id.numE1Button);
		numE2Button = (Button)findViewById(R.id.numE2Button);
		numFButton = (Button)findViewById(R.id.numFButton);
		numGButton = (Button)findViewById(R.id.numGButton);
		numHButton = (Button)findViewById(R.id.numHButton);

		PhoneNumber = this.getIntent().getStringExtra("PhoneNumber");
		if (PhoneNumber !=null) {
			Log.i("The Phone Number", PhoneNumber);
		}

		timer = new CountDownTimer(30000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}
			@Override
			public void onFinish() {
			}
		}.start();

		keyPressTimeout = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}
			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true || isGFKeyPressed == true)  {
					isFgKeypress = false;
					isHgKeypress = false;
					isGFKeyPressed = false;
					lookuptable();
				} else{
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					if(reStr==null) { 
						reStr="";
						reStr = reStr + str;
						ResetBrailleKeys();
					} else {
						reStr=reStr+str;
						ResetBrailleKeys();
					}
				}
			}
		};


		////////-->> Touch Functionality for every button <<-- ////////////
		////////================================================///////////

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(1);
					num1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					keyPressTimeout.cancel();
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(2);
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					keyPressTimeout.cancel();
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(3);
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					keyPressTimeout.cancel();
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(4);
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					keyPressTimeout.cancel();
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();

					BrailleCommonUtil.SetBrailleKeyflag(5);
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					keyPressTimeout.cancel();
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(6);
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.H));
					keyPressTimeout.cancel();
					isHgKeypress =true;
					b3 = setBoolean();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					lookuptable();
					keyPressTimeout.start();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}


				return true;
			}
		});

		//final Intent kk = new Intent(this,com.evision.android.EnotePad.class);
		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					isGFKeyPressed = true;
					speak("g" );
					b2 = setBoolean();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} 
				else if (ACTION_UP == event.getAction()) {
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					if (isFgKeypress == true) {

						stop();
						if(reStr == "" || reStr == null || reStr == " ") {
							reStr = "";
							speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.contact_savename));
						}
						else {
							speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.contact_savedname)+reStr);
							Intent launchApp = new Intent();
							launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
							launchApp.putExtra("contactname", reStr);
							launchApp.putExtra("PhoneNumber", PhoneNumber);
							launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.WRITECONTACTNUMBER); 
							startActivity(launchApp); 
							finish();
						}

					} 
					if (isHgKeypress == true) {
						Intent launchApp = new Intent();
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						// launchApp.putExtra("TDLalarmhrs",reStr);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.CREATEPHONEBOOK); 
						startActivity(launchApp);
						finish();

					}
					keyPressTimeout.start();
				}

				return true;
			}
		});


		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.F));
					isFgKeypress = true;
					b1 = setBoolean();
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

		numCButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {        		        				        
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.C));
					numCButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numCButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		numAButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.A));
					numAButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					//ttsInvalidChoice();
					//					Intent launchApp11 = new Intent();
					//					launchApp11.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					//					launchApp11.putExtra("alphaentry", reStr);
					//					launchApp11.putExtra("classname", "AddContact");
					//					launchApp11.setClassName(PackageUtil.PACKAGE, PackageUtil.COMMONNUMBERENTRY); 
					//					startActivity(launchApp11);
					//					finish();
					numAButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numDButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.D));
					numDButton.setBackgroundResource(R.drawable.btn_green);
					if(reStr!=null){
						if(reStr.length()!=0){
							String lastChar = reStr.substring(reStr.length()-1, reStr.length());
							if (lastChar.equalsIgnoreCase(" ")) 
								delStr = "deleting space";
							else
								delStr = "deleting  " + lastChar;
							reStr=reStr.substring(0,reStr.length()-1);
							speak(delStr);
						}
						else
							speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.nochar_delete));
					}
				}
				else if (ACTION_UP == event.getAction()) {
					numDButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numBButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.B));
					numBButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsInvalidChoice();
					numBButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.E));
					numE1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numE1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.E));
					numE2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numE2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
	}

	@Override
	public void onInit(int status) {
		stop();
		if (PhoneNumber == null) {
			speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.contact_welcome));
		}
		speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.contact_savename));
		speak(ResourceUtil.getStringFromResource(AddContact.this,R.string.delete_d));
		super.onInit(status);
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


	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}
}
