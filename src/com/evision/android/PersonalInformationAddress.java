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



public class PersonalInformationAddress extends BaseActivity {

	String AppendString=" ";
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer readTimer = null;
	CountDownTimer keyPressTimeout = null;
	String msg = "";
	boolean modChange = false;
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
	Button resetButton = null;
	public String reStr="",PersonalInformationName="",PersonalInformationContact="";
	String delStr = "";

	private boolean scrollDown = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeyPressed = false;
	private boolean scrollUp = false;
	boolean b1 = false, b2 = false, b3 = false;
	Intent launchApp = new Intent();


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


		PersonalInformationName = this.getIntent().getStringExtra("PersonalInformationName");
		PersonalInformationContact = this.getIntent().getStringExtra("PersonalInformationContact");
		/*Intent startIntent = new Intent("com.datapadsystem.ANNIVERSARY_SPEAK");
        startIntent.putExtra("ANNIVERSARY", "");
        this.startService(startIntent);

        Intent intent = getIntent();
        Adate = intent.getStringExtra("Adate");
		Log.i("Adate","Adate=" +Adate);		

		Amonth=intent.getStringExtra("Amonth");
		Log.i("Amonth","Amonth="+Amonth);

		Aalarmhrs=intent.getStringExtra("Aalarmhrs");
		Log.i("Aalarmhrs","Aalarmhrs="+Aalarmhrs);

		Aalarmmin=intent.getStringExtra("Aalarmmin");
		Log.i("Aalarmmin","Aalarmmin="+Aalarmmin);
		 */
		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true || isGFKeyPressed == true
						|| scrollUp == true || scrollDown == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
					isGFKeyPressed = false;
					lookuptable();
				} else {
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					if(reStr==null) { 
						reStr="";
						reStr=reStr+str;
						ResetBrailleKeys();
					} else {
						reStr=reStr+str;
						ResetBrailleKeys();
					}
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
					BrailleCommonUtil.SetBrailleKeyflag(1);
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
					BrailleCommonUtil.SetBrailleKeyflag(2);
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
					BrailleCommonUtil.SetBrailleKeyflag(3);
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
					BrailleCommonUtil.SetBrailleKeyflag(4);
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
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
					speak("h");
					keyPressTimeout.cancel();
					isHgKeypress = true;
					isGFKeyPressed = false;
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

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					isGFKeyPressed = true;
					b2 = setBoolean();
					speak("g");
					numGButton.setBackgroundResource(R.drawable.btn_green);
				}  else if (ACTION_UP == event.getAction()) {
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					if (isFgKeypress == true) {

						stop();
						if((reStr == "") || (reStr == null) || (reStr == " ")) {
							reStr = "";
							speak("please enter the Address ");
						} else {
							speak(" f g pressed");
							Intent launchApp = new Intent();
							launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
							launchApp.putExtra("PersonalInformationName", PersonalInformationName);
							launchApp.putExtra("PersonalInformationContact", PersonalInformationContact);
							launchApp.putExtra("PersonalInformationAddress", reStr);
							launchApp.setClassName("com.evision.android", "com.evision.android.PersonalInformationGPSLocation"); 
							startActivity(launchApp);
							finish();
						}



					}	
					if (isHgKeypress == true) {
						Intent launchApp = new Intent();
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE , PackageUtil.PERSONALINFORMATIONCONTACT);
						startActivity(launchApp);
						finish();
						isHgKeypress = false;
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
					speak("f");
					b1 = setBoolean();
					isFgKeypress = true;
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

		numCButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {        		        				        
				if (ACTION_DOWN == event.getAction()) {
					ttsInvalidChoice();
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
					numAButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numAButton.setBackgroundResource(R.drawable.btn_normal);
					ttsInvalidChoice();
				}
				return true;
			}
		});

		numDButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
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
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE1Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {

				if ( ACTION_DOWN == event.getAction()) {
					numE1Button.setBackgroundResource(R.drawable.btn_green);
					if(modChange){
						BrailleCommonUtil.NumericLookUpTable(mTts);
					}
					else{
						BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					}
					if(reStr!=null){
						reStr.toString().concat(AppendString);
					}
				}
				else if (ACTION_UP == event.getAction()) {
					numE1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE2Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					numE2Button.setBackgroundResource(R.drawable.btn_green);
				}
				else if (ACTION_UP == event.getAction()) {
					numE2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


	}


	public String convertTwoDigit(String convert)
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append(convert);
		if(buffer.length()<2)
		{
			convert=buffer.toString();
			buffer.delete(0,buffer.length());
			buffer.append(0);
			buffer.append(convert);
			convert=buffer.toString();
			buffer.delete(0,buffer.length());
		}
		return convert;
	}

	@Override
	public void onInit(int status) {
		speak(ResourceUtil.getStringFromResource(PersonalInformationAddress.this, R.string.PersonalInformation_address));
		speak("press d to delete a character entry");
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
