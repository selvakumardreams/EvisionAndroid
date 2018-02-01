package com.evision.android.education;


import java.util.List;
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


public class VoiceRecordFileList extends BaseActivity {

	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	// public final String MEDIA_PATH = new String("/mnt/sdcard/ebook");
	public List<String> FileNameFullList;
	public List<String> FileNameSubList;

	int fileIndex = 0;
	String filenameNoextn = "";

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
	//Button resetButton = null;

	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;

	CountDownTimer doubleClickTimer = null;
	// private boolean doubleClick = false;
	private boolean scrollDown = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeypress = false;
	boolean isACKeypress = false;
	boolean b1 = false, b2 = false, b3 = false;
	private boolean scrollUp = false;
	Intent launchApp = new Intent();
	private String pathName = null;
	String fileExtn = null;
	String appName = null;
	String appClassName = null;
	String className = "";
	String sendFileName = "";
	String voiceFileName ="";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);
		Intent intentcla = getIntent();
		className = intentcla.getStringExtra("className");

		// Get the Intent
		Intent intent = getIntent();

		appName = intent.getStringExtra(BrailleCommonUtil.APP_NAME_INTENT);
		intent.removeExtra(BrailleCommonUtil.APP_NAME_INTENT);

		if (appName.equals(BrailleCommonUtil.APP_NAME_EBOOK)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_EBOOK;
			fileExtn = BrailleCommonUtil.EXTN_TXT;
			appClassName = "com.datapadsystem.EbookRead";

		} else if (appName.equals(BrailleCommonUtil.APP_NAME_MP3)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_MP3;
			fileExtn = BrailleCommonUtil.EXTN_MP3;
			appClassName = "com.datapadsystem.MediaFilePlayer";

		} else if (appName.equals(BrailleCommonUtil.APP_NAME_VR)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_VR;
			fileExtn = BrailleCommonUtil.EXTN_THREEGP;
			appClassName = "com.datapadsystem.MediaFilePlayer";

		} else if (appName.equals(BrailleCommonUtil.APP_NAME_ENOTEPAD)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_ENOTEPAD;
			fileExtn = BrailleCommonUtil.EXTN_TXT;
			appClassName = "com.datapadsystem.EnoteRead";
		}
		// Get List of files based on extn local copy is maintained here
		FileNameFullList = BrailleCommonUtil.getFileListBasedOnExtn(pathName,
				fileExtn);
		// Initially Full is same as Sublist
		FileNameSubList = FileNameFullList;

		ResetBrailleKeys();

		num1Button = (Button) findViewById(R.id.num1Button);
		num2Button = (Button) findViewById(R.id.num2Button);
		num3Button = (Button) findViewById(R.id.num3Button);
		num4Button = (Button) findViewById(R.id.num4Button);
		num5Button = (Button) findViewById(R.id.num5Button);
		num6Button = (Button) findViewById(R.id.num6Button);
		numAButton = (Button) findViewById(R.id.numAButton);
		numBButton = (Button) findViewById(R.id.numBButton);
		numCButton = (Button) findViewById(R.id.numCButton);
		numDButton = (Button) findViewById(R.id.numDButton);
		numE1Button = (Button) findViewById(R.id.numE1Button);
		numE2Button = (Button) findViewById(R.id.numE2Button);
		numFButton = (Button) findViewById(R.id.numFButton);
		numGButton = (Button) findViewById(R.id.numGButton);
		numHButton = (Button) findViewById(R.id.numHButton);

		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				ttsForEbookListMenu();
			}
		}.start();

		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true || isACKeypress == true || isGFKeypress == true
						|| scrollUp == true || scrollDown == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
					isACKeypress = false;
					isGFKeypress = false;
					lookuptable();
				} else {
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					ResetBrailleKeys();
					if (str != null) // Invalid character
						try {
							FileNameSubList = BrailleCommonUtil.getFilterFilesByNameString(FileNameFullList, str);
							if (FilesExist() == true)
								speak(FileNameSubList.get(0));
						}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		};

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
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
					stop();
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
					b3 = setBoolean();
					speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.H));
					isGFKeypress = false;
					keyPressTimeout.cancel();
					isHgKeypress = true;
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
					b2 = setBoolean();
					stop();
					speak("G");
					isGFKeypress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				}  else if (ACTION_UP == event.getAction()) {
					//if(timer!=null)
					//keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					if (isHgKeypress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.VOICERECORDMAINMENU);
						startActivity(launchApp);
						finish();
						isHgKeypress = false;
					} 


					// Select file to launch
					if (isFgKeypress == true  ) {
						stop();
						if ((FilesExist() == true) ) {
							sendFileName = FileNameSubList.get(fileIndex);
							voiceFileName = sendFileName.substring(0,sendFileName.lastIndexOf('.'));
							speak(voiceFileName + " is selected for playback");
							waitForShortSpeechFinished();
							launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
							// Putting absolute path name
							launchApp.setClassName(PackageUtil.PACKAGE,PackageUtil.MEDIAFILEPLAYER);
							launchApp.putExtra("className", className);
							launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT, pathName+ FileNameSubList.get(fileIndex));
							startActivity(launchApp);
							finish();
						} 
					} 
					keyPressTimeout.start();
				}
				return true;
			}
		});

		// final Intent intActiveMenu = new Intent(this,
		// com.datapadsystem.ActiveMainMenu.class);
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					b1 = setBoolean();
					speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.F));
					keyPressTimeout.cancel();
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					numFButton.setBackgroundResource(R.drawable.btn_normal);
					if (isGFKeypress == true) {
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						// Putting absolute path name
						launchApp.setClassName(PackageUtil.PACKAGE,PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
				}
				return true;
			}
		});

		numAButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					// scrollDown = true;
					stop();
					speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.A));
					keyPressTimeout.cancel();
					numAButton.setBackgroundResource(R.drawable.btn_green);

				} else if (ACTION_UP == event.getAction()) {
					if (scrollUp == true) {
						if (FilesExist() == true) {
							if (fileIndex > 0)
								fileIndex--;
							else
								fileIndex = FileNameSubList.size() - 1;
							stop();
							filenameNoextn =FileNameSubList.get(fileIndex);
							filenameNoextn = filenameNoextn.replace(".3gp", "");
							speak(filenameNoextn);
						}
					}
					// ResetBrailleKeys();
					numAButton.setBackgroundResource(R.drawable.btn_normal);
					isACKeypress = true;
					keyPressTimeout.start();
				}
				return true;
			}
		});

		numCButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.C));

					keyPressTimeout.cancel();
					numCButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (scrollDown == true) {
						if (FilesExist() == true) {
							if ((FileNameSubList.size() - 1) > fileIndex)
								fileIndex++;
							else
								fileIndex = 0;
							stop();
							filenameNoextn =FileNameSubList.get(fileIndex);
							filenameNoextn = filenameNoextn.replace(".3gp", "");
							speak(filenameNoextn);
						}
					}
					isACKeypress = true;
					keyPressTimeout.start();
					numCButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		numDButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.D));
					numDButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numDButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numBButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.B));

					scrollUp = true;
					scrollDown = true;
					keyPressTimeout.cancel();
					numBButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					numBButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()){
					// ttsInvalidChoice();
					stop();
					speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.e1));

					numE1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()){
					// ttsForEbookListMenu();
					/*String str = BrailleCommonUtil.AlpabeticalLookUpTable(tts);
					ResetBrailleKeys();
					if (str != null)
					{
						FileNameSubList = BrailleCommonUtil
								.getFilterFilesBystring(FileNameFullList, str);
					}
					 */
					numE1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		numE2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					stop();
					speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.e2));

					numE2Button.setBackgroundResource(R.drawable.btn_green);
				}
				else if (ACTION_UP == event.getAction()) {
					// ttsForEbookListMenu();
					/*String str = BrailleCommonUtil.AlpabeticalLookUpTable(tts);
					ResetBrailleKeys();
					if (str != null) 
					{
						FileNameSubList = BrailleCommonUtil
								.getFilterFilesBystring(FileNameFullList, str);
					}*/
					numE2Button.setBackgroundResource(R.drawable.btn_normal);
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

	public void onInit(int arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPause() {

		if (timer != null)
			timer.cancel();
		/*
		 * if (startUpTimer!=null) startUpTimer.cancel();
		 */if (doubleClickTimer != null)
			 doubleClickTimer.cancel();

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
		/*
		 * if (startUpTimer!=null) startUpTimer.start();
		 */if (doubleClickTimer != null)
			 doubleClickTimer.start();
		 super.onResume();
	}

	public boolean FilesExist() {
		boolean flag = true;
		if (FileNameSubList.size() == 0) {
			stop();
			speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.crtfile_nof));
			flag = false;
		}
		return flag;
	}

	public void ResetBrailleKeys() 
	{
		BrailleCommonUtil.ResetBrailleKeyflag();
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}

	public void ttsForEbookListMenu() {
		stop();
		speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.voiceregord_welcome));
		speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.scroll_up));
		speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.scroll_down));
		speak(ResourceUtil.getStringFromResource(VoiceRecordFileList.this,R.string.select_fg));
	}

}

