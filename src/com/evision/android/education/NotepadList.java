package com.evision.android.education;

import com.evision.android.BaseActivity;

import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;


public class NotepadList extends BaseActivity{

	String filenameNoextn;
	String className;
	private int MY_DATA_CHECK_CODE = 0;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer keyPressTimeout = null;
	private boolean scrollDown = false;
	private boolean scrollUp = false;
	boolean b1 = false, b2 = false, b3 = false;
	//private long pos=0;
	int fileIndex = 0;
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
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeypress = false;
	boolean isACKeypress = false;
	private String pathName = null;;
	String fileExtn = null;
	String AppName = null;
	String AppClassName = null;
	final Intent launchApp = new Intent();
	public List<String> FileNameFullList;
	public List<String> FileNameSubList;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);

		Intent intent = getIntent();

		className = this.getIntent().getStringExtra("className");
		AppName = intent.getStringExtra(BrailleCommonUtil.APP_NAME_INTENT);
		intent.removeExtra(BrailleCommonUtil.APP_NAME_INTENT);




		if (AppName.equals(BrailleCommonUtil.APP_NAME_EBOOK)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_EBOOK;
			fileExtn = BrailleCommonUtil.EXTN_TXT;
			AppClassName = "com.evision.android.education.NotepadRead";

		} else if (AppName.equals(BrailleCommonUtil.APP_NAME_MP3)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_MP3;
			fileExtn = BrailleCommonUtil.EXTN_MP3;
			AppClassName = "com.datapadsystem.MediaFilePlayer";

		} else if (AppName.equals(BrailleCommonUtil.APP_NAME_VR)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_VR;
			fileExtn = BrailleCommonUtil.EXTN_THREEGP;
			AppClassName = "com.datapadsystem.MediaFilePlayer";

		} else if (AppName.equals(BrailleCommonUtil.APP_NAME_ENOTEPAD)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_ENOTEPAD;
			fileExtn = BrailleCommonUtil.EXTN_TXT;
			AppClassName = "com.evision.android.education.NotepadRead";
		}

		// Get List of files based on extn local copy is maintained here
		FileNameFullList = BrailleCommonUtil.getFileListBasedOnExtn(pathName,fileExtn);
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
				ttsForEnoteListMenu();
			}

			@Override
			public void onFinish() {

				ttsForEnoteListMenu();
			}
		}.start();

		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true || isGFKeypress == true
						|| scrollUp == true || scrollDown == true || isACKeypress == true ) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
					isGFKeypress = false;
					isACKeypress = false;
					lookuptable();
				} else {
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					ResetBrailleKeys();
					if (str != null) {// Invalid character
						FileNameSubList = BrailleCommonUtil.getFilterFilesByNameString(FileNameFullList, str);
						if (FilesExist() == true)
							speak(FileNameSubList.get(0));
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
					keyPressTimeout.cancel();
					stop();
					b3 = setBoolean();
					speak("H");
					isHgKeypress = true;
					isGFKeypress = false;
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
					isGFKeypress = true;
					b2 = setBoolean();
					stop();
					speak("G");
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if ((FilesExist() == true)) {
						// Select file to launch
						if (isFgKeypress == true) {
							stop();
							waitForShortSpeechFinished();
							launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
							// Putting absolute path name
							launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT,  FileNameSubList.get(fileIndex));
							launchApp.putExtra("className",className);
							launchApp.setClassName(PackageUtil.PACKAGE,AppClassName);
							startActivity(launchApp);
							finish();
						} 

						// keyPressTimeout.start();
					}
					if (isHgKeypress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADMAINMENU);
						startActivity(launchApp);
						finish();
						isHgKeypress = false;
					}
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		// final Intent intActiveMenu = new Intent(this,
		// com.datapadsystem.ActiveMainMenu.class);
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					isFgKeypress = true;
					b1 = setBoolean();
					stop();
					speak("F");
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					if (isGFKeypress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
					numFButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numCButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					speak("C");
					numCButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if ((FilesExist() == true)&& (scrollDown == true)) {
						if ((FileNameSubList.size() - 1) > fileIndex)
							fileIndex++;
						else
							fileIndex = 0;

						stop();
						/*tts.speak(FileNameSubList.get(fileIndex),
									TextToSpeech.QUEUE_ADD, null);*/
						filenameNoextn =FileNameSubList.get(fileIndex);
						filenameNoextn = filenameNoextn.substring(0,filenameNoextn.lastIndexOf('.'));
						speak(filenameNoextn);
					}
					//ResetBrailleKeys();
					isACKeypress = true;
					keyPressTimeout.start();

					numCButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});



		numAButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					//scrollDown = true;
					keyPressTimeout.cancel();
					stop();
					speak("A");
					numAButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if ((FilesExist() == true) && (scrollUp == true)) {
						if (fileIndex > 0)
							fileIndex--;
						else
							fileIndex = FileNameSubList.size() - 1;

						stop();
						filenameNoextn =FileNameSubList.get(fileIndex);
						filenameNoextn = filenameNoextn.substring(0,filenameNoextn.lastIndexOf('.'));
						speak(filenameNoextn);
					}
					//ResetBrailleKeys();
					numAButton.setBackgroundResource(R.drawable.btn_normal);
					isACKeypress = true;
					keyPressTimeout.start();
				}
				return true;
			}
		});

		numDButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					numDButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("D");

				} else if (ACTION_UP == event.getAction()) {
					numDButton.setBackgroundResource(R.drawable.btn_normal);

				}
				return true;
			}
		});


		numBButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					scrollUp   = true;
					scrollDown = true;
					stop();
					speak("B");
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

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					speak(ResourceUtil.getStringFromResource(NotepadList.this,R.string.enter));
					stop();
					speak("E 1");
					numE1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// ttsForEbookListMenu();
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					ResetBrailleKeys();
					if (str != null) {
						FileNameSubList = BrailleCommonUtil
								.getFilterFilesByNameString(FileNameFullList, str);
					}

					numE1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					speak(ResourceUtil.getStringFromResource(NotepadList.this,R.string.enter));
					stop();
					speak("E 2");
					numE2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// ttsForEbookListMenu();
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					ResetBrailleKeys();
					if (str != null) {
						FileNameSubList = BrailleCommonUtil
								.getFilterFilesByNameString(FileNameFullList, str);
					}
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


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				mTts = new TextToSpeech(this, this);
			} else {
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent
				.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}
	}

	public boolean FilesExist() {
		boolean flag = true;
		if ( FileNameSubList.size() == 0) {
			stop();
			speak(ResourceUtil.getStringFromResource(NotepadList.this,R.string.nofile_sdcard));
			flag = false;
		}
		return flag;
	}
	public void ttsForEnoteListMenu() {
		if (AppName.equals(BrailleCommonUtil.APP_NAME_EBOOK)) 
			speak(ResourceUtil.getStringFromResource(NotepadList.this,R.string.ebookread_welcome));
		else if (AppName.equals(BrailleCommonUtil.APP_NAME_ENOTEPAD)) 
			speak(ResourceUtil.getStringFromResource(NotepadList.this,R.string.enoteread_welcome));
		speak("select");
		speak(ResourceUtil.getStringFromResource(NotepadList.this,R.string.scroll_up));
		speak(ResourceUtil.getStringFromResource(NotepadList.this,R.string.scroll_down));
		speak(ResourceUtil.getStringFromResource(NotepadList.this,R.string.enotelist_fg));
	}


	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}
}
