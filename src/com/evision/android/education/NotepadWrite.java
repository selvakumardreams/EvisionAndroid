package com.evision.android.education;

import com.evision.android.BaseActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class NotepadWrite extends BaseActivity {

	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;

	public List<String> FileNames = new ArrayList<String>();	
	public List<String> FileNameSubList;
	public List<String> FileNameFullList;

	static boolean isfilenameEntered = false;
	CountDownTimer readTimer = null;
	static String fname = "";
	String voiceFileName = "";
	String delStr = "";
	int fileIndex = 0;	

	Intent launchApp = new Intent();
	CountDownTimer keyPressTimeout = null;
	CountDownTimer timer = null;

	private boolean scrollDown = false;
	private boolean scrollUp = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeypress = false;
	boolean b1 = false, b2 = false, b3 = false;
	boolean isFilesExist = false;

	String fileExtn = null;
	String AppName = null;
	File storageDir;

	public static String reStr = "";
	String classname = null;
	private String pathName = null;
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

		AppName = this.getIntent().getStringExtra(BrailleCommonUtil.APP_NAME_INTENT);
		this.getIntent().removeExtra(BrailleCommonUtil.APP_NAME_INTENT);

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

		classname = this.getIntent().getStringExtra("classname");
		if (classname.equalsIgnoreCase("NotepadMainMenu")) {
			pathName = BrailleCommonUtil.MEDIA_PATH_ENOTEPAD;
			fileExtn = BrailleCommonUtil.EXTN_TXT;
		} else if (classname.equalsIgnoreCase("EbookMainMenu")) {
			pathName = BrailleCommonUtil.MEDIA_PATH_EBOOK;
			fileExtn = BrailleCommonUtil.EXTN_PDF;
		}

		storageDir = new File(Environment.getExternalStorageDirectory(),
				BrailleCommonUtil.APP_NAME_ENOTEPAD);
		storageDir.mkdir();
		/**
		 * 
		 * To Get The All the File name in SD card for checking exiting one
		 * 
		 * 
		 */

		FileNameFullList = BrailleCommonUtil.getFileListBasedOnExtn(pathName,fileExtn);


		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				ttsForNotepadwrite();
			}
		}.start();

		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true || isGFKeypress == true
						|| scrollUp == true || scrollDown == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
					isGFKeypress = false;
					lookuptable();
				} else {
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					ResetBrailleKeys();
					reStr = reStr + str;
				}
			}
		};


		////////-->> Touch Functionality for every button <<-- ////////////
		////////================================================///////////

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
					keyPressTimeout.cancel();
					stop();
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
		//        final Intent intEbookRead = new Intent(this,
		//				com.datapadsystem.ActiveMainMenu.class);
		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					b3 = setBoolean();
					speak("h");
					keyPressTimeout.cancel();
					isHgKeypress=true;
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
					speak("g");
					b2 = setBoolean();
					isGFKeypress = true;
					keyPressTimeout.cancel();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} 
				else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);

					if ((isFgKeypress == true) && (isfilenameEntered == false)) {
						if (reStr.equalsIgnoreCase("") || reStr.equalsIgnoreCase(" ")) {
							reStr = "";
							speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.NotepadWrite_fname));
						} else {
							if (reStr.length() > 8) {
								speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.NotepadWrite_enterchar));
								speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.NotepadWrite_delete));
							} else {
								fname = reStr + fileExtn;
								FilesExist(fname);
							}
						}
						//						if (!reStr.equals("") || !reStr.equals(" ")) {
						//							if (reStr.length() > 8) {
						//								speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.NotepadWrite_enterchar));
						//								speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.NotepadWrite_delete));
						//							} else {
						//								fname = reStr + fileExtn;
						//								FilesExist(fname);
						//							}
						//						} else {
						//							reStr = "";
						//							speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.NotepadWrite_fname));
						//						}
					} else if((isFgKeypress == true) && (isfilenameEntered == true)) {
						if (!reStr.equals("")) {
							try {
								writeAlert(reStr);
								voiceFileName = fname.substring(0,fname.lastIndexOf('.'));
								speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.NotepadWrite_save) + voiceFileName);
								waitForSpeechFinished();
								fname = "";
								launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADMAINMENU);
								startActivity(launchApp);
								finish();
							} 
							catch(Exception e) {
								e.printStackTrace();
							}
						} else {
							speak("Please Enter the content ");
						}
					}
					if (isHgKeypress == true) {

						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADWRITEMENU); 
						startActivity(launchApp);
						finish();
					}
				}
				return true;
			}
		});

		//		final Intent intActiveMenu = new Intent(this,
		//				com.datapadsystem.enotepad.class);	
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					b1 = setBoolean();
					speak("f");
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} 
				else if (ACTION_UP == event.getAction()) {
					if (isGFKeypress ==true) {
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
					stop();
					speak("C");
					numCButton.setBackgroundResource(R.drawable.btn_green);
				} 
				else if (ACTION_UP == event.getAction()) {
					numCButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});



		numAButton.setOnTouchListener( new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("A");
					numAButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					/*launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NUMBERENTRY);
					launchApp.putExtra("classname", "NotepadWrite");
					launchApp.putExtra("alphaentry", reStr);
					startActivity(launchApp);
					finish();			*/
					numAButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numDButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					numDButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("D");
					if(reStr!=null) {
						if(reStr.length()!=0) {
							String lastChar = reStr.substring(reStr.length()-1, reStr.length());
							if (lastChar.equals(" ")) 
								delStr = "deleting space";
							else
								delStr = "deleting  " + lastChar;
							reStr=reStr.substring(0,reStr.length()-1);
							speak(delStr);
						}
						else
							speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.nochar_delete));
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
					ttsInvalidChoice();
					numBButton.setBackgroundResource(R.drawable.btn_green);
				} 
				else if (ACTION_UP == event.getAction()) {
					//					Intent launchApp11 = new Intent();
					//					launchApp11.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					//					launchApp11.putExtra("alphaentry", reStr);
					//					launchApp11.putExtra("classname", "NotepadWrite");
					//					launchApp11.setClassName("com.datapadsystem", "com.datapadsystem.commonSpecialCharEntry"); 
					//					startActivity(launchApp11);
					//					finish();
					numBButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		numE1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					numE1Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("E 1");
				} 
				else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(NotepadWrite.this, R.string.not_assign));
					numE1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					numE2Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("E 2");
				} 
				else if (ACTION_UP == event.getAction()) {

					speak(ResourceUtil.getStringFromResource(NotepadWrite.this, R.string.not_assign));
					numE2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

	}



	public void ttsForNotepadwrite() {        
		if (classname.equals("NotepadMainMenu")) {
			speak(ResourceUtil.getStringFromResource(NotepadWrite.this, R.string.NotepadWrite_welcome));
		} else {
			speak(ResourceUtil.getStringFromResource(NotepadWrite.this, R.string.ebookWrite_welcome));
		}
		speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.NotepadWrite_fname));
		speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.NotepadWrite_delete));
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

	public void writeAlert(String message)
	{
		FileWriter f;
		try {
			f = new FileWriter(pathName + fname);
			f.write(message);
			f.flush();
			f.close();

			isfilenameEntered = false;
			reStr = "";
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
	} 

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}

	/**
	 * 
	 * To Check The Exiting File Name
	 * 
	 * 
	 * @param File Name
	 */
	public void FilesExist(String check) {

		int length = FileNameFullList.size();
		int i = 0; 
		for(i=0;i<length;i++) {
			if(FileNameFullList.get(i).equals(check)) {
				isFilesExist = true;
			}
		}
		if(isFilesExist == true) {
			speak("The File Name is Already existing . please  Re enter the File Name");
			reStr = "";
			fname = "";
			isFilesExist = false;
			isfilenameEntered = false;
		}
		else {
			reStr = "";
			isfilenameEntered = true;	
			speak(ResourceUtil.getStringFromResource(NotepadWrite.this,R.string.NotepadWrite_type));
		}
	}
}