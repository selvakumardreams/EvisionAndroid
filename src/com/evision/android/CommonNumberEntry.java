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

public class CommonNumberEntry extends BaseActivity  {
	/** Called when the activity is first created. */

	String AppendString=" ";

	private int MY_DATA_CHECK_CODE = 0;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	public static final int ACTION_INIT =1;
	public static final int ACTION_MID =2;
	public static final int ACTION_FINAL =3;

	
	CountDownTimer readTimer = null;
	CountDownTimer keyPressTimeout = null;
	String msg = "";
	public static int actionSelect= 0;
	public static int actionBack = 0;
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

	private boolean scrollDown = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	private boolean scrollUp = false;
	String AppName = null;
	String AppClassName = null;
	private String pathName = null;
	String TDLdate="";
	String classname="";

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
		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

		Intent intent = getIntent();
		TDLdate = intent.getStringExtra("alphaentry");
		Log.i("TDLdate","TDLdate=" +TDLdate);		


		classname = intent.getStringExtra("classname");
		Log.i("classname","classname=" +classname);		


		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true
						|| scrollUp == true || scrollDown == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
				} else {
					String str = BrailleCommonUtil.NumericLookUpTable(mTts);
					TDLdate = TDLdate + str;
					ResetBrailleKeys();
				}
			}
		};

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("1");
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
					stop();
					speak("2");
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
					stop();
					speak("3");
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
					stop();
					speak("4");
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
					stop();
					speak("5");
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
					stop();
					speak("6");
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
					stop();
					speak("7");
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
					stop();
					speak("8");
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
					stop();
					speak("9");
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
					stop();
					speak("0");
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
					speak(ResourceUtil.getStringFromResource(CommonNumberEntry.this,R.string.star));
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					Intent launchApp = new Intent();
					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					launchApp.putExtra("alphanumentry", TDLdate);
					launchApp.putExtra("star", "true");
					launchApp.setClassName(PackageUtil.PACKAGE, "com.evision.android."+classname); 
					startActivity(launchApp);
					finish();
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
					speak(ResourceUtil.getStringFromResource(CommonNumberEntry.this,R.string.hash));

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
					speak(ResourceUtil.getStringFromResource(CommonNumberEntry.this,R.string.H));
					keyPressTimeout.cancel();
					actionSelect ++;
					actionBack ++;
					isHgKeypress=true;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(actionBack==ACTION_FINAL )
					{
						/*startActivity(intEbookRead);
						finish();
						 */						actionSelect = 0;	
					}}
				keyPressTimeout.start();
				numHButton.setBackgroundResource(R.drawable.btn_normal);

				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener() 
		{
			public boolean onTouch(View v, MotionEvent event)
			{
				actionSelect ++;
				actionBack ++;	

				if (ACTION_DOWN == event.getAction())
				{
					keyPressTimeout.cancel();
					stop();

					speak(ResourceUtil.getStringFromResource(CommonNumberEntry.this,R.string.G));
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} 
				else if (ACTION_UP == event.getAction())
				{
					if (isFgKeypress == true) {
						stop();

						speak(ResourceUtil.getStringFromResource(CommonNumberEntry.this,R.string.alarmday_hourenter)+reStr);

						/* Intent launchApp = new Intent();
						 launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);


						 launchApp.putExtra("TDLalarmhrs",reStr);
						 launchApp.setClassName(PackageUtil.PACKAGE, "com.datapadsystem.AlarmMin"); 
						 startActivity(launchApp);
						 finish();*/


						isFgKeypress = false;

					} else if (isHgKeypress == true) {
						/* Intent launchApp = new Intent();
						 launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);


						// launchApp.putExtra("TDLalarmhrs",reStr);
						 launchApp.setClassName(PackageUtil.PACKAGE, "com.datapadsystem.AccessoryMenu"); 
						 startActivity(launchApp);
						 finish();
						isHgKeypress = false;*/
					}
				}
				else if (isHgKeypress == true) {
					//					 Intent launchApp = new Intent();
					//					 launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					//					
					//					
					//					// launchApp.putExtra("TDLalarmhrs",reStr);
					//					 launchApp.setClassName(PackageUtil.PACKAGE, "com.datapadsystem.AccessoryMenu"); 
					//					 startActivity(launchApp);
					//					 finish();
					isHgKeypress = false;
				}
				numGButton.setBackgroundResource(R.drawable.btn_normal);

				return true;

			}});

		/*final Intent intActiveMenu = new Intent(this,
				com.datapadsystem.enotepad.class);	
		 */
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					actionSelect ++;
					actionBack ++;	

					speak(ResourceUtil.getStringFromResource(CommonNumberEntry.this,R.string.F));
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (actionBack ==ACTION_FINAL){
						/*startActivity(intActiveMenu);
						finish();*/
						actionBack = 0;
					}
					keyPressTimeout.start();
					numFButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});




	}


	@Override
	public void onInit(int status) {
		speak(ResourceUtil.getStringFromResource(CommonNumberEntry.this,R.string.numeric_mode));
		speak(ResourceUtil.getStringFromResource(CommonNumberEntry.this,R.string.press_star));
		super.onInit(status);
	}
	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}
}
