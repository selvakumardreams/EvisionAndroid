package com.evision.android;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.util.Log;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

/**
 * 
 * @author selvakumar
 *
 */
public class SpeedDialing extends BaseActivity {

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
	Boolean starPressed =false;
	private TextToSpeech tts;
	private int MY_DATA_CHECK_CODE = 0;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;
	private boolean scrollDown = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeypress = false;
	private boolean scrollUp = false;
	String str ="",reStr="";
	int speedNumber;
	private String TAG = "Speed";
	Intent launchApp = new Intent();
	List<String> speedDialList=new ArrayList<String>();

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

		// intent used of TTS
		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);


		try {
			SpeedDialDBHandler dbHandler=new SpeedDialDBHandler(this);
			dbHandler = new SpeedDialDBHandler(getBaseContext());
			speedDialList=dbHandler.fetchAllToDoList();
			//	toDoListListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,dbHandler.fetchAllToDoList()));
			dbHandler.closeDataBase();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
				if (isFgKeypress == true || isHgKeypress == true
						|| scrollUp == true || scrollDown == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
				} else {
					str = BrailleCommonUtil.NumericLookUpTable(tts);
					reStr =reStr+str;
					//	ResetBrailleKeys();

				}
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					//BrailleCommonUtil.SetBrailleKeyflag(0);
					speedNumber = 0;
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
					keyPressTimeout.cancel();
					speak("1");
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speedNumber = 1;

					if(starPressed==true)
					{
						speak(ResourceUtil.getStringFromResource(SpeedDialing.this,R.string.active_calldeena));
						CallOperations call = new CallOperations(getApplicationContext());
						call.makeCall("9845381777"); //
					}
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
					speak("2");
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speedNumber = 2;
					if(starPressed==true)
					{
						Log.i(TAG,"str==="+reStr);
						speak(ResourceUtil.getStringFromResource(SpeedDialing.this,R.string.active_calljaga));
						CallOperations call = new CallOperations(getApplicationContext());
						call.makeCall("9242838716"); //
					}
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
					speak("3");
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speedNumber = 3;
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
					speak("4");
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speedNumber = 4;
					//	BrailleCommonUtil.SetBrailleKeyflag(4);
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					speak("5");
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speedNumber = 5;
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
					speak("6");
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speedNumber = 6;
					//	BrailleCommonUtil.SetBrailleKeyflag(6);
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					speak("7");
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speedNumber = 7;
					//	BrailleCommonUtil.SetBrailleKeyflag(7);
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					speak("8");
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speedNumber = 8;
					//	BrailleCommonUtil.SetBrailleKeyflag(8);
					num8Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					speak("9");
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					speedNumber = 9;
					//	BrailleCommonUtil.SetBrailleKeyflag(9);
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

					//	ttsInvalidChoice();
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					if (isGFKeypress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
					//	ttsMenu();
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
					isGFKeypress = true;
					//	ttsInvalidChoice();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isFgKeypress == true){
						if(reStr==""|| reStr==null)
						{
							speak(ResourceUtil.getStringFromResource(SpeedDialing.this,R.string.noPad_enter));

						}
						else
						{
							Log.i(TAG,"str==="+reStr);
							CallOperations call = new CallOperations(getApplicationContext());
							call.makeCall(reStr);
						}
						isFgKeypress = false;

					}
					//	ttsMenu();
					if (isHgKeypress == true) {

						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);


						// launchApp.putExtra("TDLalarmhrs",reStr);
						launchApp.setClassName("com.datapadsystem", "com.datapadsystem.cellphone"); 
						startActivity(launchApp);
						finish();
						isHgKeypress = false;
					}
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
					isHgKeypress = true;

					//ttsInvalidChoice();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					//ttsMenu();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("star");
					starPressed =true;
					//	ttsInvalidChoice();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					//	ttsMenu();
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
					isGFKeypress = false;
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					//	ttsMenu();
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				tts = new TextToSpeech(this, this);// StandbyScreen.this,StandbyScreen.this);
			} else {
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent
				.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}
	}

	public void onInit(int status) {

	}




	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(tts);
	}

	public void ttsMenu() {
		speak(ResourceUtil.getStringFromResource(SpeedDialing.this,R.string.speedDial_speedDial));


		// timer.start();
	}

	public void getAssignedSpeedDial(int val)
	{
		for(int i=0;i<speedDialList.size();i++)
		{
			if( String.valueOf(val) ==speedDialList.get(i) )
			{
				speak(ResourceUtil.getStringFromResource(SpeedDialing.this,R.string.speedDial_assign));

			}
			else
			{
				speak(ResourceUtil.getStringFromResource(SpeedDialing.this,R.string.active_notassign));
			}
		}
	}
}
