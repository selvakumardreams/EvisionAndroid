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


public class CreateFolder extends BaseActivity {

	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer keyPressTimeout = null;
	CountDownTimer timer = null;
	public String reStr = "";


	//private long seekPos = 0,pos=0;

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
	// Button resetButton = null;

	Intent launchApp = new Intent();
	private String pathName = null;;
	String AppName = null;
	private boolean scrollDown = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeypress = false;
	private boolean scrollUp = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);

		Intent intent = getIntent();

		AppName = intent.getStringExtra(BrailleCommonUtil.APP_NAME_INTENT);
		intent.removeExtra(BrailleCommonUtil.APP_NAME_INTENT);


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

		// intent used of TTS


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
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					reStr= reStr + str;
					ResetBrailleKeys();

					//	if (str != null) // Invalid character
					//	FileNameSubList = BrailleCommonUtil
					//			.getFilterFilesBystring(FileNameFullList, str);
				}
			}
		};


		////////-->> Touch Functionality for every button <<-- ////////////
		////////================================================///////////

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					speak("1");
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
					speak("2");
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
					speak("3");
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
					stop();
					speak("4");
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
					speak("5");
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
					speak("6");
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
					speak("H");

					keyPressTimeout.cancel();
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
					stop();
					speak("G");
					isGFKeypress = true;
					keyPressTimeout.cancel();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					if (isFgKeypress == true)
					{
						stop();
						String foldreName = getpath();
						BrailleCommonUtil.create_directory(reStr,foldreName,mTts);
						reStr = "";
						//create_directory(dirname,ppathname,tts);
						isFgKeypress = false;
					} 
					else if (isHgKeypress == true) {
						isHgKeypress = false;
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADMAINMENU);
						startActivity(launchApp);
						finish();
						finish();
						isHgKeypress = false;
					}

				}
				// keyPressTimeout.start();
				//	numGButton.setBackgroundResource(R.drawable.btn_normal);

				return true;
			}
		});

		// final Intent intActiveMenu = new Intent(this,
		// com.datapadsystem.ActiveMainMenu.class);
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {

					stop();
					speak(ResourceUtil.getStringFromResource(CreateFolder.this,R.string.F));

					keyPressTimeout.cancel();
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					numFButton.setBackgroundResource(R.drawable.btn_normal);
					if(isGFKeypress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
				}
				return true;
			}
		});

		numCButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {        		        				        

				if ( ACTION_DOWN == event.getAction()) {

					stop();
					speak(ResourceUtil.getStringFromResource(CreateFolder.this,R.string.C));

					numCButton.setBackgroundResource(R.drawable.btn_green);    
					//	speak(str,TextToSpeech.QUEUE_ADD,null);
				}

				else if (ACTION_UP == event.getAction()) {
					numCButton.setBackgroundResource(R.drawable.btn_normal); 

				}

				return true;
			}
		});



		numAButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {


				if (ACTION_DOWN == event.getAction()) {

					stop();
					speak(ResourceUtil.getStringFromResource(CreateFolder.this,R.string.A));


					numAButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numAButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numDButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) 
				{	
					//stop();
					stop();
					speak(ResourceUtil.getStringFromResource(CreateFolder.this,R.string.D));
					numDButton.setBackgroundResource(R.drawable.btn_green);
				}
				else if (ACTION_UP == event.getAction()) {
					numDButton.setBackgroundResource(R.drawable.btn_normal);
					if(reStr!=null){
						if(reStr.length()!=0){
							String lastChar = reStr.substring(reStr.length()-1, reStr.length());
							String delStr = "deleting  " + lastChar;
							reStr=reStr.substring(0,reStr.length()-1);
							speak(delStr);
						}
						else
							speak(ResourceUtil.getStringFromResource(CreateFolder.this,R.string.nochar_delete));
					}
				}
				return true;
			}
		});

		numBButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction())
				{
					stop();
					speak(ResourceUtil.getStringFromResource(CreateFolder.this,R.string.B));
					numBButton.setBackgroundResource(R.drawable.btn_green);
					//	stop();
				}
				else if (ACTION_UP == event.getAction()) {
					numBButton.setBackgroundResource(R.drawable.btn_normal);
				}

				return true;
			}
		});

		numE1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(CreateFolder.this,R.string.e1));
					numE1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					numE1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak(ResourceUtil.getStringFromResource(CreateFolder.this,R.string.e2));
					numE2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numE2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
	}



	public void ttsMenu() {        

		speak(ResourceUtil.getStringFromResource(CreateFolder.this,R.string.CreateFolder_welcome));
		speak(ResourceUtil.getStringFromResource(CreateFolder.this,R.string.NotePad_folder));

	}



	public void ResetBrailleKeys() 
	{
		BrailleCommonUtil.ResetBrailleKeyflag();
	}



	public String getpath()
	{
		if (AppName.equals(BrailleCommonUtil.APP_NAME_EBOOK)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_EBOOK;

			//create_directory(dirname,pathName);

		} else if (AppName.equals(BrailleCommonUtil.APP_NAME_MP3)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_MP3;
			//create_directory(dirname,pathName);

		} else if (AppName.equals(BrailleCommonUtil.APP_NAME_VR)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_VR;
			//create_directory(dirname,pathName);

		} else if (AppName.equals(BrailleCommonUtil.APP_NAME_ENOTEPAD)) {
			pathName = BrailleCommonUtil.MEDIA_PATH_ENOTEPAD;
			//create_directory(dirname,pathName);
		}
		else if (AppName.equals(BrailleCommonUtil.APP_NAME_FOLDER)) {
			pathName = BrailleCommonUtil.CREATE_FOLDER;
			//create_directory(dirname,pathName);
		}
		return pathName;
	}
	/*	
	public void create_directory(String dirname,String path,TextToSpeech tts)
	{   

		 File f = new File("path"+dirname);
	        try{
	        if(f.mkdir())
	        {
	            speak("Directory  "+dirname+ " created");

	        }
	        else
	        {
	   	 speak("Directory  "+dirname+ "is not created");


	        }
	        }

	        catch(Exception e){
	        e.printStackTrace();
	        }

	}*/

}
