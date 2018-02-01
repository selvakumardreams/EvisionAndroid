package com.evision.android.education;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.evision.android.BaseActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.GridLayout.Spec;

import com.evision.android.R;
import com.evision.android.util.BookMark;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.DatabaseHelper;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class NotepadRead extends BaseActivity {

	boolean bf = false,bg = false;
	boolean bhash=false,bh = false,bstar=false;
	boolean isFGKeyPress = false;
	boolean isGFKeyPress = false;
	boolean isHGKeypress = false;
	boolean isPausePress = false;
	boolean b1 = false, b2 = false, b3 = false;
	CountDownTimer keyPressTimeout = null;

	private boolean scrollDown = false;
	private boolean scrollUp = false ;
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
	CountDownTimer timer = null;
	CountDownTimer readTimer = null;
	CountDownTimer doubleClickTimer = null;


	private boolean doubleClick = false;
	private int MY_DATA_CHECK_CODE = 0;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	private long bookMarkPos = 1;
	long pauseValue;
	public List<String> fileNames = new ArrayList<String>();
	int fileIndex = 0;
	String fileName = null;
	String className = null;
	String voiceFileName = "";
	RandomAccessFile accessFile = null , AccessBookMark= null;
	String line = null;	
	Intent launchApp = new Intent();
	private String pathName = null;
	private DatabaseHelper dbHelper;
	private BookMark book;
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

		dbHelper = new DatabaseHelper(this);
		book = new BookMark();

		// intent used of TTS
		Intent intent = getIntent();
		className = this.getIntent().getStringExtra("className");
		fileName = intent.getStringExtra(BrailleCommonUtil.APP_NAME_INTENT);
		if (className.equals("NotepadMainMenu")) {
			pathName = BrailleCommonUtil.MEDIA_PATH_ENOTEPAD;
		} else if (className.equals("EbookMainMenu")) {
			pathName = BrailleCommonUtil.MEDIA_PATH_EBOOK;
		} else {
			pathName = BrailleCommonUtil.MEDIA_PATH_EVISION;
		}



		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {

			}
			@Override
			public void onFinish() {
				ttsFornoteReadMenu();
			}
		}.start();


		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (scrollDown == true || scrollUp == true || isGFKeyPress == true
						|| isFGKeyPress == true || isHGKeypress == true) {

					scrollUp = false;
					scrollDown = false;
					isHGKeypress = false;
					isFGKeyPress = false;
					isGFKeyPress = false;
					lookuptable();
				} 
			}
		};



		readTimer = new CountDownTimer(100, 100) {
			@Override
			public void onTick(long millisUntilFinished) {

			}

			@Override
			public void onFinish() {
				if (mTts != null) {
					if (mTts.isSpeaking()) {

						start();
					} else {

						if (mTts != null) {
							try {
								ttsReadFile();
							} catch (IOException e) {

								e.printStackTrace();
							}
						}
					}
				}
			}
		};


		doubleClickTimer = new CountDownTimer(2000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {

			}

			@Override
			public void onFinish() {
				// This single key is pressed & timer expires and no docble key
				// pressed
				if ( doubleClick == true){
					doubleClick = false;					
					ttsFornoteReadMenu();
				}
			}
		};

		num0Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {

				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak("0");
					num0Button.setBackgroundResource(R.drawable.btn_green);
					//ttsInvalidChoice();
				}
				else if (ACTION_UP == event.getAction()) {
					cancelReading();
					ttsFornoteReadMenu();
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
					speak("1");
					openFile( pathName + fileName, "r");
					// mEngine.openFile("/sdcard/notepad/" + fineName,"r");
					try {
						ttsReadFile();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (ACTION_UP == event.getAction()) {
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
					speak("2");
					readTimer.cancel();
					openFile(pathName+fileName, "r");
					BookMark b =  dbHelper.getBookMark(fileName);
					if (b.getLineNumber() != 0) {
						if (b.getBookName().equals(fileName)) {
							setFilePointer(b.getLineNumber());
						} else {
							setFilePointer(bookMarkPos);
						}
					} else {
						setFilePointer(bookMarkPos);
					}

					try {
						ttsReadFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					dbHelper.close();
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// Save the bookMark
					cancelReading();
					stop();
					speak("3");
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					//bookMarkPos = getFilePointer();
					//					book.setBookName(fileName);
					//					book.setLineNumber(getFilePointer());
					//					BookMark c =  dbHelper.getBookMark(fileName);
					//					if (c.getLineNumber() != 0) {
					//						dbHelper.updateBookMark(book);
					//					} else {
					//						dbHelper.createBookMark(book);
					//					}
					//					dbHelper.close();
					if (isPausePress == false) {
						pauseValue = getFilePointer();

						isPausePress = true;
					} else {
						setFilePointer(pauseValue);
						isPausePress = false;
						try {
							ttsReadFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					//	BookMarkPos = getFilePointer();
					cancelReading();
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					book.setBookName(fileName);
					book.setLineNumber(getFilePointer());
					BookMark c =  dbHelper.getBookMark(fileName);
					if (c.getLineNumber() != 0) {
						dbHelper.updateBookMark(book);
					} else {
						dbHelper.createBookMark(book);
					}
					dbHelper.close();
					speak(voiceFileName + "is closed with book mark");
					waitForSpeechFinished();
					previousMenu();

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
				}
				else if (ACTION_UP == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					cancelReading();
					speak(voiceFileName + "is closed without book mark");
					waitForSpeechFinished();
					previousMenu();
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
					ttsInvalidChoice();
				}
				else if (ACTION_UP == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);            		
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
					ttsInvalidChoice();
				}
				else if (ACTION_UP == event.getAction()) {
					num7Button.setBackgroundResource(R.drawable.btn_normal);            		
				}
				return true;
			}
		});
		//	final Intent exit = new Intent(EnoteRead.this, enotepad.class );
		num8Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {

				if ( ACTION_DOWN == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("8");
				}
				else if (ACTION_UP == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_normal);   
					/*stop();
					cancelReading();
					AccessFile = null;
					line = null;*/
					readTimer.cancel();
					//	EnoteRead.this.startActivity(exit);
					//	EnoteRead.this.finish(); 
				}
				return true;
			}
		});

		num9Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {

				if ( ACTION_DOWN == event.getAction()) {
					stop();
					speak("9");
					num9Button.setBackgroundResource(R.drawable.btn_green);
				}
				else if (ACTION_UP == event.getAction()) {
					ttsInvalidChoice();
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					stop();

				}
				return true;
			}
		});

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					b3 = setBoolean();
					speak("H");
					isHGKeypress = true;
					keyPressTimeout.cancel();
					isGFKeyPress = false;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numHButton.setBackgroundResource(R.drawable.btn_normal);
					if (bstar == true) {
						//					startActivity(exit);
						//						finish();
					}
					lookuptable();
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
					isGFKeyPress = true;
					keyPressTimeout.cancel();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isHGKeypress == true) {
						cancelReading();
						accessFile = null;
						line = null;
						readTimer.cancel();
						previousMenu();
					}
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		//		final Intent intActiveMenu = new Intent(this,
		//				com.datapadsystem.ActiveMainMenu.class);		
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					b1 = setBoolean();
					speak("F");
					keyPressTimeout.cancel();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isGFKeyPress == true) {
						isGFKeyPress = false;
						cancelReading();
						accessFile = null;
						line = null;
						readTimer.cancel();
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
					keyPressTimeout.cancel();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
					bstar = setBoolean();
					stop();
					speak("star");
				}
				else if (ACTION_UP == event.getAction()) {
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {

				if ( ACTION_DOWN == event.getAction()) {
					numHashButton.setBackgroundResource(R.drawable.btn_green);
					bhash=setBoolean();
					stop();
					speak("Hash");
					keyPressTimeout.cancel();
				}
				else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
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
				mTts = new TextToSpeech(this, this);//StandbyScreen.this,StandbyScreen.this);
			} 
			else {
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			} 
		}

	}

	public void onInit(int status) {        
	}

	public void ttsReadFile() throws IOException {
		stop();
		if (accessFile != null) {
			line = accessFile.readLine();
			if (line == null) {
				readTimer.cancel();
			} else {
				speak(line);
				readTimer.start();
			}
		}
	}

	public void ttsFornoteReadMenu() {
		stop();

		voiceFileName = fileName.substring(0,fileName.lastIndexOf('.'));
		speak(voiceFileName + ResourceUtil.getStringFromResource(NotepadRead.this,R.string.isselect));
		speak(ResourceUtil.getStringFromResource(NotepadRead.this,R.string.select));
		speak(ResourceUtil.getStringFromResource(NotepadRead.this,R.string.enoteread_one));
		speak(ResourceUtil.getStringFromResource(NotepadRead.this,R.string.enoteread_two));
		speak(ResourceUtil.getStringFromResource(NotepadRead.this,R.string.enoteread_three));
		speak(ResourceUtil.getStringFromResource(NotepadRead.this,R.string.enoteread_four));
		speak(ResourceUtil.getStringFromResource(NotepadRead.this,R.string.enoteread_five));
		speak(ResourceUtil.getStringFromResource(NotepadRead.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(NotepadRead.this,R.string.previous_hg));
	}


	public void ttsInvalidChoice() {
		cancelReading();
		speak(ResourceUtil.getStringFromResource(NotepadRead.this,R.string.wrong_choice));
	}

	public void cancelReading() {
		if (mTts != null)
			if(line!=null)
				line="";
		readTimer.cancel();
		stop();
	}
	public void openFile(String fileName, String mode) {

		cancelReading();

		try {
			accessFile = new RandomAccessFile(fileName, mode);
		} catch (FileNotFoundException e) {

		}
	}
	public long getFilePointer() {
		long pos = -1;
		try {
			if (accessFile!=null)
				pos = accessFile.getFilePointer();
		} catch (IOException e) {
		}
		return pos;

	}
	public void setFilePointer(long pos) {
		try {
			if (accessFile!=null)
				accessFile.seek(pos - 1);
		} catch (IOException e) {
		}
	}
	public String readLine() {
		if (accessFile != null) {
			try {
				return line = accessFile.readLine();
			} catch (IOException e) {
			}
		}
		return null;

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
	public void previousMenu() {

		if (className.equals("EbookMainMenu")) {
			launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.EBOOKMAINMENU);
		} else if (className.equals("ManualMenu")){
			launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.MANUALMENU);
		} else if(className.equals("TutorMenu")) {
			launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.TUTORMENU);
		} else {
			launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT, BrailleCommonUtil.APP_NAME_ENOTEPAD);
			launchApp.putExtra("className",className);
			launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADLIST);
		}
		startActivity(launchApp);
		finish();
	}

	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}
}
