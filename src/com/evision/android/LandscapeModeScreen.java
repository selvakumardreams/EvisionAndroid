package com.evision.android;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;


public class LandscapeModeScreen extends BaseActivity {

	String fileExtn = null;
	String pathName = null;
	String fname = "";
	String voiceFileName = "";
	File storageDir;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;
	Button num1Button = null;
	Button num2Button = null;
	Button num3Button = null;
	Button num4Button = null;
	Button num5Button = null;
	Button num6Button = null;
	Button num7Button = null;
	Button num8Button = null;
	Button num9Button = null;
	Button num0Button = null;
	Button numStarButton = null;
	Button numHashButton = null;
	Button numFButton = null;
	Button numGButton = null;
	Button numHButton = null;
	public String reStr = "";
	Intent launchApp = new Intent();
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isStarHkeypress = false;
	boolean isGFKeypress = false;
	boolean isfilenameEntered = false;
	boolean isFilesExist = false;
	boolean isHGKeyPress = false;
	boolean b1 = false, b2 = false, b3 = false;
	public List<String> FileNameFullList;
	boolean uppressed=false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.landscapemode_screen);
		num1Button = (Button)findViewById(R.id.num1Button);
		num2Button = (Button)findViewById(R.id.num2Button);
		num3Button = (Button)findViewById(R.id.num3Button);
		num4Button = (Button)findViewById(R.id.num4Button);
		num5Button = (Button)findViewById(R.id.num5Button);
		num6Button = (Button)findViewById(R.id.num6Button);
		num7Button = (Button)findViewById(R.id.num7Button);
		num8Button = (Button)findViewById(R.id.num8Button);
		num9Button = (Button)findViewById(R.id.num9Button);
		num0Button = (Button)findViewById(R.id.num0Button);
		numStarButton = (Button)findViewById(R.id.numStarButton);
		numHashButton = (Button)findViewById(R.id.numHashButton);
		numFButton = (Button)findViewById(R.id.numFButton);
		numGButton = (Button)findViewById(R.id.numGButton);
		numHButton = (Button)findViewById(R.id.numHButton);


		pathName = BrailleCommonUtil.MEDIA_PATH_ENOTEPAD;
		fileExtn = BrailleCommonUtil.EXTN_TXT;
		storageDir = new File(Environment.getExternalStorageDirectory(),
				BrailleCommonUtil.APP_NAME_ENOTEPAD);
		storageDir.mkdir();

		/**
		 * 
		 * To Get The All the File name in SD card for checking exiting one
		 * 
		 */


		FileNameFullList = BrailleCommonUtil.getFileListBasedOnExtn(pathName,fileExtn);

		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				ttsForLandscapeModeScreen();
			}
		}.start();

		keyPressTimeout = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}
			@Override
			public void onFinish() {

				if (isFgKeypress == true || isHgKeypress == true || isGFKeypress == true
						|| isHGKeyPress == true || isStarHkeypress == true)  {
					isFgKeypress = false;
					isHgKeypress = false;
					isGFKeypress = false;
					isHGKeyPress = false;
					isStarHkeypress = false;
					Log.i("Enter the ", "on Finish method");
					lookuptable();
				} else {
					String str = BrailleCommonUtil.LandscapeLookUpTable(mTts);
					Log.i("","str=="+str);
					if(uppressed==true) {
						str=str.toUpperCase();
						uppressed=false;
					}
					//check for key press
					if(str.equalsIgnoreCase("backspacepressed")) {
						str = "";
						if(reStr!=null) {
							if(reStr.length()!=0) {
								String lastChar = reStr.substring(reStr.length()-1, reStr.length());
								String delStr = "back space  " + lastChar;
								reStr=reStr.substring(0,reStr.length()-1);
								speak(delStr);
							}
						}
						Log.i("","backspacepressed");

					} 
					//					else if(str.equalsIgnoreCase("deletepressed")) {
					//						str = "";
					//						if(reStr!=null) {
					//							if(reStr.length()!=0){
					//								String lastChar = reStr.substring(reStr.length()-1, reStr.length());
					//								String delStr = "deleting  " + lastChar;
					//								reStr=reStr.substring(0,reStr.length()-1);
					//								speak(delStr);
					//							} else {
					//								speak(ResourceUtil.getStringFromResource(LandscapeModeScreen.this,R.string.nochar_delete));
					//							}
					//						}
					//						Log.i("","deletepressed");
					//					} 
					else if(str.equalsIgnoreCase("space")) {
						str = "";
						str = " ";
						Log.i("","space");
					} else if(str.equalsIgnoreCase("enterpressed")) {
						str = "";
						str = "\n";
						Log.i("","enterpressed");
					} else if(str.equalsIgnoreCase("uppercasepressed")) {
						str = "";
						uppressed=true;
						Log.i("","uppercasepressed");
					}

					if(reStr==null) { 
						reStr="";
						reStr=reStr+str;
						ResetBrailleKeys();
						Log.i("","finally string is=="+reStr);
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
					BrailleCommonUtil.SetBrailleKeyflag(1);
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					num1Button.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
					isGFKeypress = false;
					isHGKeyPress = false;
					lookuptable();
				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					BrailleCommonUtil.SetBrailleKeyflag(2);
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
					isGFKeypress = false;
					isHGKeyPress = false;
					lookuptable();
				}
				return true;
			}
		});

		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					BrailleCommonUtil.SetBrailleKeyflag(3);
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
					isGFKeypress = false;
					isHGKeyPress = false;
					lookuptable();
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					BrailleCommonUtil.SetBrailleKeyflag(4);
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
					isGFKeypress = false;
					isHGKeyPress = false;
					lookuptable();
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					BrailleCommonUtil.SetBrailleKeyflag(5);
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
					isGFKeypress = false;
					isHGKeyPress = false;
					lookuptable();
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					BrailleCommonUtil.SetBrailleKeyflag(6);
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					num6Button.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
					isGFKeypress = false;
					isHGKeyPress = false;
					lookuptable();
				}
				return true;
			}
		});

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					numHButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					BrailleCommonUtil.SetBrailleKeyflag(12);
					b3  = setBoolean();
					isHGKeyPress = true;
				} else if (ACTION_UP == event.getAction()) {
					if (isStarHkeypress == true) {
						if(reStr!=null) {
							if(reStr.length()!=0) {
								String lastChar = reStr.substring(reStr.length()-1, reStr.length());
								String delStr = "deleting  " + lastChar;
								reStr=reStr.substring(0,reStr.length()-1);
								speak(delStr);
							}
							else
								speak(ResourceUtil.getStringFromResource(LandscapeModeScreen.this,R.string.nochar_delete));
						}
						ResetBrailleKeys();
					} 
					lookuptable();
					keyPressTimeout.start();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event){
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					BrailleCommonUtil.SetBrailleKeyflag(11);
					numGButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					isGFKeypress = true;
					b2 = setBoolean();
				} else if (ACTION_UP == event.getAction()) {
					if ((isFgKeypress == true) && (isfilenameEntered == false)) {
						ResetBrailleKeys();
						if (reStr.equalsIgnoreCase("") || reStr.equalsIgnoreCase(" ")) {
							isFgKeypress = false;
							speak(ResourceUtil.getStringFromResource(LandscapeModeScreen.this,R.string.NotepadWrite_fname));
						}  else {
							if (reStr.length() > 8) {
								speak(ResourceUtil.getStringFromResource(LandscapeModeScreen.this,R.string.NotepadWrite_enterchar));
								speak(ResourceUtil.getStringFromResource(LandscapeModeScreen.this,R.string.LandscapeModeScreen_delete));
							} else {
								fname = reStr + fileExtn;
								FilesExist(fname);
							}
						}
					} else if((isFgKeypress == true) && (isfilenameEntered == true)) {
						ResetBrailleKeys();
						if (!reStr.equals("")) {
							try {
								writeAlert(reStr);
								voiceFileName = fname.substring(0,fname.lastIndexOf('.'));
								speak(ResourceUtil.getStringFromResource(LandscapeModeScreen.this,R.string.NotepadWrite_save) + voiceFileName);
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
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					if (isHGKeyPress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADWRITEMENU);
						startActivity(launchApp);
						finish();
					}
				}
				return true;
			}
		});


		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					BrailleCommonUtil.SetBrailleKeyflag(10);
					numFButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					isFgKeypress = true;
					b1 = setBoolean();
				} else if (ACTION_UP == event.getAction()) {
					if (isGFKeypress == true) {
						ResetBrailleKeys();
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

		num7Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(7);
					num7Button.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
					isGFKeypress = false;
					isHGKeyPress = false;
					lookuptable();
				}
				return true;
			}
		});
		num8Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(8);
					num8Button.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
					isGFKeypress = false;
					isHGKeyPress = false;
					lookuptable();
				}
				return true;
			}
		});

		num9Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(9);
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
					isGFKeypress = false;
					isHGKeyPress = false;
					lookuptable();
				}
				return true;
			}
		});

		num0Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(0);
					num0Button.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
					isGFKeypress = false;
					isHGKeyPress = false;
					lookuptable();
				}
				return true;
			}
		});

		numStarButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(13);
					isStarHkeypress = true;
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					numHashButton.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					BrailleCommonUtil.SetBrailleKeyflag(14);
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
					isFgKeypress= false;
				}
				return true;
			}
		});


	}


	public String convertTwoDigit(String convert) {
		StringBuffer buffer=new StringBuffer();
		buffer.append(convert);
		if(buffer.length()<2) {
			convert=buffer.toString();
			buffer.delete(0,buffer.length());
			buffer.append(0);
			buffer.append(convert);
			convert=buffer.toString();
			buffer.delete(0,buffer.length());
		}
		return convert;
	}

	public void writeAlert(String message) {
		FileWriter f;
		try {
			f = new FileWriter(pathName + fname);
			f.write(message);
			f.flush();
			f.close();

			isfilenameEntered = false;
			reStr = "";
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
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
		Log.i("Enetred","file name check");
		for(i=0;i<length;i++) {
			if(FileNameFullList.get(i).equalsIgnoreCase(check)) {
				Log.i("entered","if Condition");
				isFilesExist = true;
			}
		}
		if(isFilesExist == true) {
			speak("The File Name is Already existing . please  Re enter the File Name");
			reStr = "";
			fname = "";
			Log.i("entered ","file isFilesExist");
			isFilesExist = false;
			isfilenameEntered = false;
		}
		else {
			reStr = "";
			isfilenameEntered = true;	
			Log.i("entered ","file else condition");
			speak(ResourceUtil.getStringFromResource(LandscapeModeScreen.this,R.string.NotepadWrite_type));
		}
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

	public void ttsForLandscapeModeScreen() {
		speak(ResourceUtil.getStringFromResource(LandscapeModeScreen.this, R.string.LandscapeModeScreen_welcome));
		speak(ResourceUtil.getStringFromResource(LandscapeModeScreen.this,R.string.LandscapeModeScreen_fname));
		speak(ResourceUtil.getStringFromResource(LandscapeModeScreen.this,R.string.LandscapeModeScreen_delete));
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}
}
