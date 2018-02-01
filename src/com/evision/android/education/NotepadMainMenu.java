package com.evision.android.education;

import com.evision.android.BaseActivity;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class NotepadMainMenu extends BaseActivity {

	CountDownTimer keyPressTimeout = null;
	private boolean isHgKeypress = false;

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
	CountDownTimer doubleClickTimer = null;
	private boolean isGFKeyPressed = false;
	boolean b1 = false, b2 = false, b3 = false;
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

		timer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub

			}
			@Override
			public void onFinish() {
				ttsForEnoteMenu();
			}
		}.start();
		doubleClickTimer = new CountDownTimer(2000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onFinish() {

			}
		};
		keyPressTimeout = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onFinish() {
				if (isGFKeyPressed == true || isHgKeypress == true) {
					isGFKeyPressed = false;
					isHgKeypress = false;
					lookuptable();
				}
			}
		};

		num0Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					if(mTts!=null)
						stop();
					speak("0");
					num0Button.setBackgroundResource(R.drawable.btn_green);
					//ttsInvalidChoice();
				}
				else if (ACTION_UP == event.getAction()) {
					num0Button.setBackgroundResource(R.drawable.btn_normal);       
					ttsForEnoteMenu();
				}
				return true;
			}
		});

		num1Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak("1");
					num1Button.setBackgroundResource(R.drawable.btn_green);
				}
				else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);  
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADWRITEMENU);
					launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT, BrailleCommonUtil.APP_NAME_ENOTEPAD);
					launchApp.putExtra("classname", "NotepadMainMenu");
					startActivity(launchApp); 
					finish();
				}
				return true;
			}
		});


		num2Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {        		
				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak("2");
					num2Button.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					//	Intent openNotePadread = new Intent(NotePad.this,EnoteList.class);
					//	NotePad.this.startActivity(openNotePadread); 
					//	NotePad.this.finish();

				}
				else if (ACTION_UP == event.getAction()) {

					num2Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADLIST);
					launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT, BrailleCommonUtil.APP_NAME_ENOTEPAD);
					launchApp.putExtra("className", "NotepadMainMenu");
					startActivity(launchApp); 
					finish();
				}
				return true;
			}
		});

		num3Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak("3");
					num3Button.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();

				}
				else if (ACTION_UP == event.getAction()) {

					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.DELETEVOICERECORD);
					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT,BrailleCommonUtil.APP_NAME_ENOTEPAD);
					launchApp.putExtra("className", "NotepadMainMenu");
					startActivity(launchApp);
					finish();
					//            		Intent intenoteedit = new Intent(NotePad.this,Enoteedit.class);
					//            		intenoteedit
					//					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					//            		intenoteedit.putExtra(BrailleCommonUtil.APP_NAME_INTENT,
					//					BrailleCommonUtil.APP_NAME_NotePad);
					//            		intenoteedit.putExtra("classname", "NotePad");
					//            		
					//            		NotePad.this.startActivity(intenoteedit); 
					//    				NotePad.this.finish();
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					stop();
				}
				return true;
			}
		});

		num4Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak("4");
					num4Button.setBackgroundResource(R.drawable.btn_green);
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.NotePad_usb));
				}
				else if (ACTION_UP == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);     
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this, R.string.inval_choice));
				}
				return true;
			}
		});

		num5Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak("5");
					num5Button.setBackgroundResource(R.drawable.btn_green);
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.NotePad_usb));
				}
				else if (ACTION_UP == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);     
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this, R.string.inval_choice));
				}
				return true;
			}
		});

		num6Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak("6");
					num6Button.setBackgroundResource(R.drawable.btn_green);

				}
				else if (ACTION_UP == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);    
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this, R.string.inval_choice));
					//					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.CREATEFOLDER);
					//					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					//					launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT,BrailleCommonUtil.APP_NAME_FOLDER);
					//					launchApp.putExtra("classname", "NotepadMainMenu");
					//					startActivity(launchApp);
					//					finish();

					//            		Intent openefolder = new Intent(NotePad.this,create_dir.class);
					//            		openefolder
					//					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					//            		openefolder.putExtra(BrailleCommonUtil.APP_NAME_INTENT,
					//					BrailleCommonUtil.APP_NAME_NotePad);
					//            		openefolder.putExtra("classname", "NotePad");
					//            		
					//     				NotePad.this.startActivity(openefolder); 
					//    				NotePad.this.finish();

				}
				return true;
			}
		});

		num7Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak("7");
					num7Button.setBackgroundResource(R.drawable.btn_green);
					//tts.speak("Under Development");
				}
				else if (ACTION_UP == event.getAction()) {
					//            		Intent intenoteread = new Intent(NotePad.this,EnoteList.class);
					//            		intenoteread
					//					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					//            		intenoteread.putExtra(BrailleCommonUtil.APP_NAME_INTENT,
					//					BrailleCommonUtil.APP_NAME_NotePad);
					//            		intenoteread.putExtra("classname", "NotePad");
					//            		NotePad.this.startActivity(intenoteread); 
					//    				NotePad.this.finish();
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this, R.string.inval_choice));
					num7Button.setBackgroundResource(R.drawable.btn_normal);            		
				}
				return true;
			}
		});
		// final Intent Intenote8 = new Intent(NotePad.this, EnoteDelete.class );
		num8Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak("8");
					num8Button.setBackgroundResource(R.drawable.btn_green);
				}
				else if (ACTION_UP == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_normal); 
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this, R.string.inval_choice));
					//            		Intent intenoteread = new Intent(NotePad.this,EnoteDelete.class);
					//            		intenoteread
					//					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					//            		intenoteread.putExtra("classname", "NotePad");
					//            		NotePad.this.startActivity(intenoteread); 
					//    				NotePad.this.finish();
					/*stop();
        			startActivity(Intenote8);
        			finish();*/
				}
				return true;
			}
		});
		// final Intent Intenote9 = new Intent(NotePad.this, NotePad.class );
		num9Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak("9");
					num9Button.setBackgroundResource(R.drawable.btn_green);
				}
				else if (ACTION_UP == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this, R.string.inval_choice));

					//		startActivity(Intenote9);
					//		finish();
				}
				return true;
			}
		});


		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					b3 = setBoolean();
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.H));
					keyPressTimeout.cancel();

					isHgKeypress = true;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					lookuptable();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
				}

				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("G");
					b2 = setBoolean();
					keyPressTimeout.cancel();
					isGFKeyPressed = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(isHgKeypress==true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.EDUCATIONMENU);
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
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.F));
					keyPressTimeout.cancel();
					isHgKeypress = true;
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

		numStarButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.star));
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				}
				else if (ACTION_UP == event.getAction()) {
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.hash));
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				}
				else if (ACTION_UP == event.getAction()) {
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

	public void ttsForEnoteMenu() {

		stop();
		speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.NotePad_welcome));
		speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.NotePad_one));
		speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.NotePad_two));
		speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.NotePad_three));
		//		speak(ResourceUtil.getStringFromResource(NotepadMain.this,R.string.NotePad_four));
		//		speak(ResourceUtil.getStringFromResource(NotepadMain.this,R.string.NotePad_five));
		//speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.NotePad_six));
		//		speak(ResourceUtil.getStringFromResource(NotepadMain.this,R.string.NotePad_seven));
		//	speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.NotePad_eight));
		speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.previous_hg));
	}

	public void ttsInvalidChoice() {
		cancelReading();
		speak(ResourceUtil.getStringFromResource(NotepadMainMenu.this,R.string.wrong_choice));
	}

	public void cancelReading()
	{
		if (mTts != null)
			stop();
	}

	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}
}