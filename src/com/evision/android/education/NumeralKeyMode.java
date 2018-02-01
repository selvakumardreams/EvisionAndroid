package com.evision.android.education;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.evision.android.BaseActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class NumeralKeyMode extends BaseActivity{

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
	String fileExtn = null;
	String pathName = null;
	String fname = "";
	String voiceFileName = "";
	File storageDir;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;
	public String reStr = "";
	Intent launchApp = new Intent();
	boolean isfilenameEntered = false;
	boolean isFilesExist = false;
	boolean isFkeyPress = false;
	boolean isGkeyPress = false;
	boolean isHkeyPress = false;
	boolean isHashKeyPress = false;
	boolean isStarKeyPress = false;
	static boolean b1 = false, b2 = false, b3 = false, b4 = false, b5 = false;
	static boolean b6 = false, b7 = false, b8 = false, b9 = false, b0 = false;
	public List<String> FileNameFullList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.landscapemode_screen);

		ttsForNumeralKeyMode();
		pathName = BrailleCommonUtil.MEDIA_PATH_ENOTEPAD;
		fileExtn = BrailleCommonUtil.EXTN_TXT;
		storageDir = new File(Environment.getExternalStorageDirectory(),BrailleCommonUtil.APP_NAME_ENOTEPAD);
		storageDir.mkdir();
		speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.LandscapeModeScreen_welcome));
		speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.LandscapeModeScreen_fname));
		speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.LandscapeModeScreen_delete));
		initialize(); //Initialize the buttons

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
				ttsForNumeralKeyMode();
			}
		}.start();

		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (isFkeyPress == true || isGkeyPress == true || isHkeyPress == true
						|| isHashKeyPress == true || isStarKeyPress == true) {
					isFkeyPress = false;
					isGkeyPress = false;
					isHkeyPress = false;
					isHashKeyPress = false;
					isStarKeyPress = false;
				}

			}
		};
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
		for(i=0;i<length;i++) {
			if(FileNameFullList.get(i).equalsIgnoreCase(check)) {
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
			speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.NotepadWrite_type));
		}
	}

	public void ttsForNumeralKeyMode() {
		speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.LandscapeModeScreen_welcome));
		speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.LandscapeModeScreen_fname));
		speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.LandscapeModeScreen_delete));
	}


	private void initialize() {
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
		num0Button.setOnTouchListener(buttonOnTouchListener);
		num1Button.setOnTouchListener(buttonOnTouchListener);
		num2Button.setOnTouchListener(buttonOnTouchListener);
		num3Button.setOnTouchListener(buttonOnTouchListener);
		num4Button.setOnTouchListener(buttonOnTouchListener);
		num5Button.setOnTouchListener(buttonOnTouchListener);
		num6Button.setOnTouchListener(buttonOnTouchListener);
		num7Button.setOnTouchListener(buttonOnTouchListener);
		num8Button.setOnTouchListener(buttonOnTouchListener);
		num9Button.setOnTouchListener(buttonOnTouchListener);
		numFButton.setOnTouchListener(buttonOnTouchListener);
		numGButton.setOnTouchListener(buttonOnTouchListener);
		numHashButton.setOnTouchListener(buttonOnTouchListener);
		numHButton.setOnTouchListener(buttonOnTouchListener);
		numStarButton.setOnTouchListener(buttonOnTouchListener);
	}

	private OnTouchListener buttonOnTouchListener  = new OnTouchListener() {

		@SuppressLint("NewApi")
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (v.getId()) {
			case R.id.num0Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					b0= true;
					num0Button.setBackgroundResource(R.drawable.btn_green);  
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					numeralLookUpTable();
					num0Button.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
				} 
				break;

			case R.id.num1Button:

				if (ACTION_DOWN == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					b1 = true;
					keyPressTimeout.cancel();
				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);
					numeralLookUpTable();
					keyPressTimeout.start();
				}
				break;

			case R.id.num2Button:

				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					b2 = true;
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					numeralLookUpTable();
					keyPressTimeout.start();
				} 
				break;

			case R.id.num3Button:

				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					b3 = true;
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					numeralLookUpTable();
					keyPressTimeout.start();
				} 
				break;

			case R.id.num4Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					b4 = true;
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					numeralLookUpTable();
					keyPressTimeout.start();
				} 
				break;

			case R.id.num5Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					b5 = true;
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					numeralLookUpTable();
					keyPressTimeout.start();
				} 
				break;

			case R.id.num6Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					b6 = true;
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
					numeralLookUpTable();
					keyPressTimeout.start();
				} 
				break;

			case R.id.num7Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					b7 = true;
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num7Button.setBackgroundResource(R.drawable.btn_normal);
					numeralLookUpTable();
					keyPressTimeout.start();
				} 
				break;

			case R.id.num8Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					b8 = true;
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num8Button.setBackgroundResource(R.drawable.btn_normal);
					numeralLookUpTable();
					keyPressTimeout.start();
				} 
				break;

			case R.id.num9Button:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					b9 = true;
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					numeralLookUpTable();
				} 
				break;

			case R.id.numFButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					numFButton.setBackgroundResource(R.drawable.btn_green);
					isFkeyPress = true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					if (isGkeyPress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();
					numFButton.setBackgroundResource(R.drawable.btn_normal);
				} 
				break;

			case R.id.numGButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					isGkeyPress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					if ((isFkeyPress == true) && (isfilenameEntered == false)) {
						if (reStr.equalsIgnoreCase("") || reStr.equalsIgnoreCase(" ")) {
							isFkeyPress = false;
							speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.NotepadWrite_fname));
						}  else {
							if (reStr.length() > 8) {
								speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.NotepadWrite_enterchar));
								speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.LandscapeModeScreen_delete));
							} else {
								fname = reStr + fileExtn;
								FilesExist(fname);
							}
						}
					} else if((isFkeyPress == true) && (isfilenameEntered == true)) {
						if (!reStr.equals("")) {
							try {
								writeAlert(reStr);
								voiceFileName = fname.substring(0,fname.lastIndexOf('.'));
								speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.NotepadWrite_save) + voiceFileName);
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
					if (isHkeyPress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADWRITEMENU);
						startActivity(launchApp);
						finish();
					}
				} 
				break;

			case R.id.numHButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					isHkeyPress = true;
					keyPressTimeout.cancel();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					keyPressTimeout.start();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				} 

				break;
			case R.id.numStarButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					isStarKeyPress = true;
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				} 
				break;

			case R.id.numHashButton:
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					keyPressTimeout.cancel();
					isHashKeyPress = true;
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
				} 
				break;

			default:
				break;

			}
			return true;
		}
	};

	public void numeralLookUpTable() {

		if ( isHashKeyPress == false && isFkeyPress == false && isGkeyPress == false 
				&& isStarKeyPress == false && b0 == true && isHkeyPress == false) {
			speak("J");
			reStr = reStr + "J";
		} else if (isHashKeyPress== false && isFkeyPress == true && isGkeyPress == false 
				&& isStarKeyPress == false &&  b0 == true && isHkeyPress == false) {
			speak("T");
			reStr = reStr + "T";
		} else if ( isHashKeyPress== false && isGkeyPress == true && isFkeyPress == false 
				&& isStarKeyPress == false &&  b0== true && isHkeyPress == false) {
			speak("question");
			reStr = reStr + "?";
		} else if (isHashKeyPress == false && isGkeyPress == true && isFkeyPress == false 
				&& isStarKeyPress == false &&  b0== true && isHkeyPress == false) {
			speak(")");
			reStr = reStr + ")";
		} else if (isHashKeyPress == false && isFkeyPress == false && isGkeyPress == false 
				&&  b1== true && isStarKeyPress == false && isHkeyPress == false) {
			speak("A");
			reStr = reStr + "A";
		} else if ( isFkeyPress == true && isGkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && b1 == true && isHkeyPress == false) {
			speak("K");
			reStr = reStr + "K";
		} else if ( isGkeyPress == true && isFkeyPress == false  &&isHkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b1 == true) {
			speak("U");
			reStr = reStr + "U";
		} else if ( isHashKeyPress == true && isFkeyPress == false &&isGkeyPress == false
				&& b1 == true  && isStarKeyPress == false && isHkeyPress == false) {
			speak(":");
			reStr = reStr + ":";
		} else if (isStarKeyPress == true && isGkeyPress == false && isFkeyPress == false 
				&& isHkeyPress == false && b1 == true && isHashKeyPress == false) {
			speak("plus");
			reStr = reStr + "+";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b2 == true) {
			speak("B");
			reStr = reStr + "B";
		}else if (isFkeyPress == true && isGkeyPress == false && isHashKeyPress == false 
				&& isStarKeyPress == false && isHkeyPress == false && b2 == true) {
			speak("L");
			reStr = reStr + "L";
		} else if (isGkeyPress == true && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b2 == true) {
			speak("V");
			reStr = reStr + "V";
		} else if (isGkeyPress == false && isFkeyPress == false &&isHashKeyPress == true
				&& isStarKeyPress == false && isHkeyPress == false && b2 == true) {
			speak("@");
			reStr = reStr + "@";
		} else if (isGkeyPress == false && isHashKeyPress == false && isFkeyPress == false 
				&&isStarKeyPress == true && isHkeyPress == false && b2 == true) {
			speak("Subtraction");
			reStr = reStr + "-";
		} else if (isFkeyPress == false && isGkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b3== true) {
			speak("C");
			reStr = reStr + "C";
		} else if (isFkeyPress == true && isGkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b3== true) {
			speak("M");
			reStr = reStr + "M";
		} else if (isGkeyPress == true && isHashKeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b3 == true) {
			speak("W");
			reStr = reStr + "W";
		} else if (isHashKeyPress == true && isGkeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isFkeyPress == false && b3 == true) {
			speak("&");
			reStr = reStr + "&";
		} else if (isGkeyPress == false && isHashKeyPress == false && isFkeyPress == false 
				&& isStarKeyPress == true && isFkeyPress == false && b3 ==true) {
			speak("Multiplication");
			reStr = reStr + "*";
		} else if (isFkeyPress == true && isGkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b4 == true) {
			speak("N");
			reStr = reStr + "N";
		} else if (isGkeyPress == true && isHashKeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b4 == true) {
			speak("X");
			reStr = reStr + "X";
		} else if (isHashKeyPress == true && isGkeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b4 == true) {
			speak("%");
			reStr = reStr + "%";
		} else if (isGkeyPress == false && isHashKeyPress == false && isFkeyPress == false
				&&isStarKeyPress == true && isHkeyPress == false && b4 == true) {
			speak("Division");
			reStr = reStr + "/";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b4 == true) {
			speak("D");
			reStr = reStr + "D";
		} else if (isFkeyPress == true && isGkeyPress == false && isHashKeyPress == false 
				&& isStarKeyPress == false && isHkeyPress == false && b5 == true) {
			speak("O");
			reStr = reStr + "O";
		} else if (isGkeyPress == true && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b5 == true) {
			speak("Y");
			reStr = reStr + "Y";
		} else if (isHashKeyPress == true && isGkeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b5 == true) {
			speak("'");
			reStr = reStr + "'";
		} else if (isGkeyPress == false && isHashKeyPress == false && isFkeyPress == false
				&& isStarKeyPress == true && isHkeyPress == false && b5 == true) {
			speak("Equal");
			reStr = reStr + "=";
		} else if (isGkeyPress == false && isHashKeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b5 == true) {
			speak("E");
			reStr = reStr + "E";
		} else if (isFkeyPress == true && isGkeyPress == false && isHashKeyPress == false 
				&& isStarKeyPress == false && isHkeyPress == false && b6 == true) {
			speak("P");
			reStr = reStr + "P";
		} else if (isGkeyPress == true && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b6 == true) {
			speak("Z");
			reStr = reStr + "Z";
		} else if (isHashKeyPress == true && isGkeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b6 == true) {
			speak(Character.toString('"'));
			reStr = reStr + Character.toString('"');
		} else if (isGkeyPress == false && isHashKeyPress == false && isFkeyPress == false
				&& isStarKeyPress == true && isHkeyPress == false && b6 == true) {
			speak("BACK SPACE");
			if(reStr!=null) {
				if(reStr.length()!=0) {
					String lastChar = reStr.substring(reStr.length()-1, reStr.length());
					String delStr = "deleting  " + lastChar;
					reStr = reStr.substring(0,reStr.length()-1);
					speak(delStr);
				}
				else
					speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.nochar_delete));
			}
		} else if (isGkeyPress == false && isHashKeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b6 == true) {
			speak("F");
			reStr = reStr + "F";
		} else if (isFkeyPress == true && isGkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b7 == true) {
			speak("Q");
			reStr = reStr + "Q";
		} else if (isGkeyPress == true && isHashKeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b7 == true) {
			speak(",");
			reStr = reStr + ",";
		} else if (isHashKeyPress == true && isGkeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b7 == true) {
			speak("<");
			reStr = reStr + "<";
		} else if (isGkeyPress == false && isHashKeyPress == false && isFkeyPress == false
				&&isStarKeyPress == true && isHkeyPress == false && b7 == true) {
			speak("Space");
			reStr = reStr + " ";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b7 == true) {
			speak("G");
			reStr = reStr + "G";
		} else if (isFkeyPress == true && isGkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b8 == true) {
			speak("R");
			reStr = reStr + "R";
		} else if (isGkeyPress == true && isHashKeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b8 == true) {
			speak(".");
			reStr = reStr + ".";
		} else if (isHashKeyPress == true && isGkeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b8 == true) {
			speak(">");
			reStr = reStr + ">";
		} else if (isGkeyPress == false && isHashKeyPress == false && isFkeyPress == false
				&&isStarKeyPress == true && isHkeyPress == false && b8 == true) {
			speak("DELETE");
			if(reStr!=null) {
				if(reStr.length()!=0) {
					String lastChar = reStr.substring(reStr.length()-1, reStr.length());
					String delStr = "deleting  " + lastChar;
					reStr=reStr.substring(0,reStr.length()-1);
					speak(delStr);
				}
				else
					speak(ResourceUtil.getStringFromResource(NumeralKeyMode.this,R.string.nochar_delete));
			}
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b8 == true) {
			speak("H");
			reStr = reStr + "H";
		} else if (isFkeyPress == true && isGkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b9 == true) {
			speak("S");
			reStr = reStr + "S";
		} else if (isGkeyPress == true && isHashKeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b9 == true) {
			speak(";");
			reStr = reStr + ";";
		} else if (isHashKeyPress == true && isGkeyPress == false && isFkeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b9 == true) {
			speak("(");
			reStr = reStr + "(";
		} else if (isGkeyPress == false && isHashKeyPress == false && isFkeyPress == false
				&&isStarKeyPress == true && isHkeyPress == false && b9 == true) {
			speak("Enter");
			reStr = reStr + "\n";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == false && b9 == true) {
			speak("I");
			reStr = reStr + "I";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == true && b1 == true) {
			speak("1");
			reStr = reStr + "1";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == true && b2 == true) {
			speak("2");
			reStr = reStr + "2";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == true && b3 == true) {
			speak("3");
			reStr = reStr + "3";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == true && b4 == true) {
			speak("4");
			reStr = reStr + "4";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == true && b5 == true) {
			speak("5");
			reStr = reStr + "5";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == true && b6 == true) {
			speak("6");
			reStr = reStr + "6";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == true && b7 == true) {
			speak("7");
			reStr = reStr + "7";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == true && b8 == true) {
			speak("8");
			reStr = reStr + "8";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == true && b9 == true) {
			speak("9");
			reStr = reStr + "9";
		} else if (isGkeyPress == false && isFkeyPress == false && isHashKeyPress == false
				&& isStarKeyPress == false && isHkeyPress == true && b0 == true) {
			speak("0");
			reStr = reStr + "0";
		} else 
			speak("wrong key press");

		ResetNumeralKeys();
	}
	public void ResetNumeralKeys() {
		b1 = false;
		b2 = false;
		b3 = false;
		b4 = false;
		b5 = false;
		b6 = false;
		b7 = false;
		b8 = false;
		b9 = false;
		b0 = false;
		isGkeyPress = false;
		isHashKeyPress = false;
		isFkeyPress = false;
		isHkeyPress = false;
		isStarKeyPress = false;
	}
}
