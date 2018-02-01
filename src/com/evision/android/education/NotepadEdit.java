package com.evision.android.education;

import com.evision.android.BaseActivity;
import com.evision.android.R;

import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;


public class NotepadEdit extends BaseActivity{

	String filenameNoextn;
	private int MY_DATA_CHECK_CODE = 0;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer keyPressTimeout = null;
	private boolean scrollDown = false;
	private boolean scrollUp = false;

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
	private String pathName = null;;
	String fileExtn = null;
	String AppName = null,FromEbook = null;
	String AppClassName = null;
	final Intent launchApp = new Intent();
	public List<String> FileNameFullList;
	public List<String> FileNameSubList;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);

		Intent intent = getIntent();

		AppName = intent.getStringExtra(BrailleCommonUtil.APP_NAME_INTENT);
		intent.removeExtra(BrailleCommonUtil.APP_NAME_INTENT);


		FromEbook = intent.getStringExtra(BrailleCommonUtil.APP_NAME_INTENT);		

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

		if (FromEbook!=null  && FromEbook.equalsIgnoreCase("FromEbook")) {
			pathName = BrailleCommonUtil.MEDIA_PATH_EBOOK;
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
				if (isFgKeypress == true || isHgKeypress == true
						|| scrollUp == true || scrollDown == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
				} else {
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					ResetBrailleKeys();
					if (str != null) // Invalid character
						FileNameSubList = BrailleCommonUtil
						.getFilterFilesByNameString(FileNameFullList, str);
				}
			}
		};
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
					stop();
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
					stop();
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
					speak("H");
					isHgKeypress = true;
					isGFKeypress = false;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

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
					stop();
					speak("G");
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if ((FilesExist() == true)) {
						// Select file to launch
						if (isFgKeypress == true) {
							stop();
							isGFKeypress = false;
							launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADEDITSCROLL);
							launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
							launchApp.putExtra("Enoteedit", FileNameSubList.get(fileIndex));
							startActivity(launchApp);
							finish();
							isFgKeypress = false;

						} else if (isHgKeypress == true) {
							launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADMAINMENU);
							startActivity(launchApp);
							finish();
							isHgKeypress = false;
						}
						// keyPressTimeout.start();
					}
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
					speak("F");
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					if (isGFKeypress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						isGFKeypress = false;
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
						filenameNoextn = filenameNoextn.replace(".txt", "");
						speak(filenameNoextn);
					}
					scrollDown = false; // Reset the value
					//ResetBrailleKeys();
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
						filenameNoextn = filenameNoextn.replace(".txt", "");
						speak(filenameNoextn);
					}
					//ResetBrailleKeys();
					numAButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
					scrollUp = false;
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
					stop();
					speak("B");
					scrollUp   = true;
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

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
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
	public void onInit(int arg0) {
	}

	public boolean FilesExist() {
		boolean flag = true;
		if ( FileNameSubList.size() == 0) {
			stop();
			speak(ResourceUtil.getStringFromResource(NotepadEdit.this,R.string.nofile_sdcard));
			flag = false;
		}
		return flag;
	}
	public void ttsForEnoteListMenu() {
		stop();
		speak(ResourceUtil.getStringFromResource(NotepadEdit.this,R.string.enotelist_ba));
		speak(ResourceUtil.getStringFromResource(NotepadEdit.this,R.string.enotelist_bc));
		speak(ResourceUtil.getStringFromResource(NotepadEdit.this,R.string.enotelist_fg));
		speak(ResourceUtil.getStringFromResource(NotepadEdit.this,R.string.enotelist_hg));

	}


	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}}
