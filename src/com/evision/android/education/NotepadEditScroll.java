package com.evision.android.education;

import com.evision.android.BaseActivity;
import com.evision.android.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import java.util.List;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;


public class NotepadEditScroll extends BaseActivity {

	CountDownTimer keyPressTimeout = null;
	private boolean scrollDown = false;
	private boolean scrollUp = false ,fghHfgkeyPress = false;
	RandomAccessFile AccessFile = null;
	CountDownTimer readTimer = null;
	private int MY_DATA_CHECK_CODE = 0;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	public final String MEDIA_PATH = new String("/sdcard/notepad/");
	public List<String> FileNames = new ArrayList<String>();
	Boolean b1=false,b2=false,b3=false,b4=false,b5=false;
	Boolean b6=false,bf=false,bg=false,bh=false;
	Boolean ba=false,bb=false,bc=false;
	Boolean be1=false,be2=false;
	String ascvalue = null;	
	boolean isHGkeypress = false;
	boolean isGFKeypress = false;
	private long Pos = 0;
	String fnamefromedit="";
	//private long pos=0;
	String str="";
	String fname;
	int fileIndex = 0;
	String line = null;
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
	final Intent launchApp = new Intent();
	CountDownTimer timer = null;
	CountDownTimer doubleClickTimer = null;
	private boolean doubleClick = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);

		updateFileList();

		// intent used of TTS


		Intent intent = getIntent();
		fnamefromedit = intent.getStringExtra("Enoteedit");

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
				ttsForEnoteeditListMenu();
			}

			@Override
			public void onFinish() {

				ttsForEnoteeditListMenu();
			}
		}.start();


		readTimer = new CountDownTimer(1000, 100) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
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
								// TODO Auto-generated catch block
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
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				// This single key is pressed & timer expires and no docble key
				// pressed
				if ( doubleClick == true){
					doubleClick = false;					
					ttsForEnoteeditListMenu();
				}
			}
		};

		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (scrollDown == true || scrollUp == true
						|| fghHfgkeyPress == true) {

					scrollUp = false;
					scrollDown = false;
					fghHfgkeyPress = false;
				} else {
					//String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					ResetBrailleKeys();

				}
			}
		};
		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("1");
					num1Button.setBackgroundResource(R.drawable.btn_green);
					b1=setBoolean();
				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("2");
					num2Button.setBackgroundResource(R.drawable.btn_green);
					b2=setBoolean();
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("3");
					num3Button.setBackgroundResource(R.drawable.btn_green);
					b3=setBoolean();
				} else if (ACTION_UP == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					b4=setBoolean();
					stop();
					speak("4");
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.digit5));
					num5Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("5");
					b5=setBoolean();
				} else if (ACTION_UP == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_green);
					b6=setBoolean();
					stop();
					speak("6");
				} else if (ACTION_UP == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		//		final Intent intEbookRead = new Intent(this,
		//				com.datapadsystem.ActiveMainMenu.class);
		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("H");
					keyPressTimeout.cancel();
					fghHfgkeyPress = true;
					isHGkeypress = true;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
				}
				keyPressTimeout.start();
				numHButton.setBackgroundResource(R.drawable.btn_normal);

				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("G");
					keyPressTimeout.cancel();
					fghHfgkeyPress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					if(isHGkeypress == true) {
						isHGkeypress = false;
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.NOTEPADMAINMENU);
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		//		final Intent intActiveMenu = new Intent(this,
		//				com.datapadsystem.Enoteedit.class);		
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("F");
					keyPressTimeout.cancel();
					fghHfgkeyPress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isGFKeypress == true) {
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

		numCButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					numCButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("C");
					bc=setBoolean();
				} else if (ACTION_UP == event.getAction()) {
					numCButton.setBackgroundResource(R.drawable.btn_normal);
					//	numCButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});



		numAButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("A");
					numAButton.setBackgroundResource(R.drawable.btn_green);
					ba=setBoolean();
				} else if (ACTION_UP == event.getAction()) {
					numAButton.setBackgroundResource(R.drawable.btn_normal);

				}
				// is is required to launch first screen on press of A..?

				return true;
			}
		});

		numDButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("D");
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
					speak("B");
					numBButton.setBackgroundResource(R.drawable.btn_green);
					bb=setBoolean();
				} else if (ACTION_UP == event.getAction()) {
					numBButton.setBackgroundResource(R.drawable.btn_normal);

					if(bb==true && b5==true)
					{
						//move right
						stop();
						Pos = getFilePointer();
						setFilePointer(Pos);

						try {
							ttsReadFile();
						} catch (IOException e) {

							e.printStackTrace();
						}
						speak("");	

					}
				}
				return true;
			}
		});

		numE1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if ( ACTION_DOWN == event.getAction())
				{
					be1=setBoolean();
					numE1Button.setBackgroundResource(R.drawable.btn_green);

					stop();
					speak("E 1");
					AlpabeticalLookUpTable ();
					//	NumericLookUpTable();
					if(ascvalue!=null){
						speak(ascvalue);						
					}

				}
				else if (ACTION_UP == event.getAction()) {
					numE1Button.setBackgroundResource(R.drawable.btn_normal);

				}
				return true;
			}
		});

		numE2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("E 2");
					numE2Button.setBackgroundResource(R.drawable.btn_green);
					be2=setBoolean();
				} else if (ACTION_UP == event.getAction()) {
					numE2Button.setBackgroundResource(R.drawable.btn_normal);
					eepress();
				}
				return true;
			}


		});

		/*		resetButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				timer.cancel();
				// timer.start();

				if (ACTION_DOWN == event.getAction()) {
					resetButton.setBackgroundResource(R.drawable.lower2);
					speak("you are in E note for editing");
					speak("you can cut copy paste delete rename your note");
					} else if (ACTION_UP == event.getAction()) {
					resetButton.setBackgroundResource(R.drawable.lower);

				}
				return true;
			}
		});
		 */
	}
	class EFileFilter implements FilenameFilter {
		String file_name;

		public boolean accept(File dir, String name) {
			file_name = name.toLowerCase();
			return (file_name.endsWith(BrailleCommonUtil.fileNameExtn));
		}
	}

	public void updateFileList() {

		File home = new File(MEDIA_PATH);
		File[] fileList = home.listFiles(new EFileFilter());
		if (fileList != null && fileList.length > 0) {
			for (File file : home.listFiles(new EFileFilter())) {
				FileNames.add(file.getName());
			}
		}
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

	private boolean setBoolean()
	{
		return true;
	}


	public boolean FilesExist() {
		boolean flag = true;
		if ( FileNames.size() == 0){
			stop();

			speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.nofile_sdcard));
			flag = false;
		}
		return flag;
	}
	public void ttsForEnoteeditListMenu() {
		stop();
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.file)+ fnamefromedit + ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.isselect));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_ee));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_eg));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.scroll_up));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.scroll_down));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_b5));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_5b));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_ctrlx));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_ctrlc));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_ctrlv));
		speak("");
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_ctrls));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_ctrlr));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.exit_8));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_9));
	}

	public void   AlpabeticalLookUpTable (){

		if (   b1 == true  && b2 == false && b3 == false 
				&& b4 == false && b5 == false && b6 == false)
		{
			ascvalue = "a";str = str + ascvalue;
		}
		else if (   b1 == true  && b2 == true  && b3 == false 
				&& b4 == false && b5 == false && b6 == false)
		{			ascvalue = "b";str = str + ascvalue;}
		else if (  bc==true && b1 == true  && b2 == false && b3 == false 
				&& b4 == true  && b5 == false && b6 == false)
		{		ascvalue = "c";
		//to copy
		//str = str + ascvalue;
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_select));
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_ctrl));
		//	
		//ClipboardManager clipboard = 
		//            (ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
		//       clipboard.setText(selected text);


		}
		else if (   b1 == true  && b2 == false && b3 == false 
				&& b4 == true  && b5 == true && b6 == false)
		{	ascvalue = "d";str = str + ascvalue;}
		else if (   b1 == true  && b2 == false && b3 == false 
				&& b4 == false && b5 == true && b6 == false)
		{		ascvalue = "e";str = str + ascvalue;}
		else if (   b1 == true  && b2 == true && b3 == false  
				&& b4 == true && b5 == false && b6 == false)
		{		ascvalue = "f";str = str + ascvalue;}
		else if (   b1 == true  && b2 == true && b3 == false  
				&& b4 == true && b5 == true && b6 == false)
		{	ascvalue = "g";str = str + ascvalue;}
		else if (   b1 == true  && b2 == true && b3 == false 
				&& b4 ==false  && b5 == true && b6 == false)
		{	ascvalue = "h";str = str + ascvalue;}
		else if (   b1 == false  && b2 == true && b3 == false 
				&& b4 == true && b5 == false && b6 == false)
		{			ascvalue = "i";str = str + ascvalue;}
		else if (   b1 == false  && b2 == true && b3 ==false  
				&& b4 == true && b5 == true  && b6 == false)
		{			ascvalue = "j";str = str + ascvalue;}
		else if (   b1 == true  && b2 == false && b3 ==true  
				&& b4 == false && b5 ==false  && b6 == false)
		{		ascvalue = "k";str = str + ascvalue;}
		else if (   b1 == true  && b2 == true && b3 == true 
				&& b4 == false && b5 == false && b6 == false)
		{			ascvalue = "l";str = str + ascvalue;}
		else if (   b1 == true  && b2 == false && b3 == true 
				&& b4 == true && b5 == false && b6 == false)
		{			ascvalue = "m";str = str + ascvalue;}
		else if (   b1 == true  && b2 == false && b3 ==true  
				&& b4 == true && b5 == true && b6 == false)
		{		ascvalue = "n";str = str + ascvalue;}
		else if (   b1 == true  && b2 == false && b3 == true 
				&& b4 == false && b5 == true && b6 == false)
		{	ascvalue = "o";str = str + ascvalue;}
		else if (   b1 == true  && b2 == true && b3 == true 
				&& b4 == true && b5 == false && b6 == false)
		{			ascvalue = "p";
		//str = str + ascvalue;
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_position));


		// position.setText( clipboard.getText().toString());
		}
		else if (   b1 == true  && b2 == true && b3 == true 
				&& b4 == true && b5 == true && b6 == false)
		{ascvalue = "q";str = str + ascvalue;}
		else if (   b1 == true  && b2 == true && b3 == true 
				&& b4 == false && b5 == true && b6 == false )
		{
			ascvalue = "r";
			if( ascvalue=="r" && bc== true)//str = str + ascvalue;
			{
				stop();
				speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_name));
				//	final Intent rename = new Intent(this,com.example.Rename.class);
				//	this.startActivity(rename);

			}
		}
		else if (   b1 == false  && b2 == true && b3 == true 
				&& b4 ==true  && b5 == false  && b6 == false)

		{	ascvalue = "s";str = str + ascvalue;}
		else if (   b1 == false  && b2 == true && b3 == true 
				&& b4 == true && b5 == true && b6 == false)
		{ascvalue = "t";str = str + ascvalue;}
		else if (   b1 == true  && b2 == false && b3 == true  
				&& b4 == false && b5 == false && b6 == true)
		{	ascvalue = "u";str = str + ascvalue;}
		else if (   b1 == true  && b2 == true && b3 == true 
				&& b4 == false && b5 == false && b6 == true)
		{	ascvalue = "v";str = str + ascvalue;}
		else if (   b1 == false  && b2 == true && b3 == false  
				&& b4 == true && b5 ==true  && b6 == true)
		{	ascvalue = "w";str = str + ascvalue;}
		else if (   b1 == true  && b2 ==false  && b3 == true  
				&& b4 == true && b5 ==false  && b6 == true)
		{	ascvalue = "x";
		//str = str + ascvalue;
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_cut));
		}
		else if (   b1 == true  && b2 == false && b3 == true  
				&& b4 == true && b5 == true && b6 == true)
		{			ascvalue = "y";str = str + ascvalue;}
		else if (   b1 == true  && b2 == false && b3 == true 
				&& b4 == false && b5 == true && b6 == true)
		{			ascvalue = "z";str = str + ascvalue;}
		else if (   b1 == false  && b2 == false && b3 == false 
				&& b4 ==false  && b5 == true && b6 == true)
		{			
			ascvalue = " ";
			str = str + ascvalue;
		}

		else
			ascvalue = null;

		// Reseting button values
		b1 = false ; b2 = false ; b3 = false;
		b4 = false; b5 = false;b6 = false;


	}
	public void   NumericLookUpTable (){
		if (   b1 == true  && b2 == false && b3 == false 
				&& b4 == false && b5 == false && b6 == false)
		{
			ascvalue = "1";

		}
		else if (   b1 == true  && b2 == true  && b3 == false 
				&& b4 == false && b5 == false && b6 == false)
		{	
			//to copy
			ascvalue = "2";

		}
		else if (   b1 == true  && b2 == false && b3 == false 
				&& b4 == true  && b5 == false && b6 == false)
		{		ascvalue = "3";
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_paste));
		}
		else if (   b1 == true  && b2 == false && b3 == false 
				&& b4 == true  && b5 == true && b6 == false)
		{			ascvalue = "4";
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_delete));
		}
		else if (   b1 == true  && b2 == false && b3 == false 
				&& b4 == false && b5 == true && b6 == false)
		{			ascvalue = "5";
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_insert));
		}
		else if (   b1 == true  && b2 == true && b3 == false  
				&& b4 == true && b5 == false && b6 == false)
		{		ascvalue = "6";
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_rename));
		}
		else if (   b1 == true  && b2 == true && b3 == false  
				&& b4 == true && b5 == true && b6 == false)
		{		ascvalue = "7";
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_sorry));
		}
		else if (   b1 == true  && b2 == true && b3 == false 
				&& b4 ==false  && b5 == true && b6 == false)
		{			ascvalue = "8";
		final Intent Intenote8 = new Intent(NotepadEditScroll.this, NotepadEdit.class );
		startActivity(Intenote8);
		}
		else if (   b1 == false  && b2 == true && b3 == false 
				&& b4 == true && b5 == false && b6 == false)
		{			ascvalue = "9";
		final Intent Intenote9 = new Intent(NotepadEditScroll.this, NotepadEditScroll.class );
		startActivity(Intenote9);
		}
		else if (   b1 == false  && b2 == true && b3 ==false  
				&& b4 == true && b5 == true  && b6 == false)
		{			ascvalue = "0";
		speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_sorry));
		}

		else 
			ascvalue = null;

		b1 = false ; b2 = false ; b3 = false;
		b4 = false; b5 = false;b6 = false;



	}
	public void ttsReadFile() throws IOException {
		stop();

		if (AccessFile != null) {
			line = AccessFile.readLine();

			if (line == null) {
				readTimer.cancel();
			} else {
				speak(line);
				readTimer.start();
			}
		}
	}
	public void openFile(String fileName, String mode) {


		try {
			AccessFile = new RandomAccessFile(fileName, mode);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
	public void cancelReading() {
		if (mTts != null)
			stop();
	}
	public void lookuptable()
	{

		if (   b1 == true  && b2 == true && b3 == true )
		{
			//			final Intent resetIntent = new Intent(this,com.datapadsystem.ActiveMainMenu.class);
			//			startActivity(resetIntent);

		}
		else if(b1==false && b2==true && b3==true)
		{
			speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_pressf));	
			speak("         ");
			speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_fgh));
		}
		else if(b1==true && b2==false && b3==true)
		{
			speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_pressg));	
			speak("         ");
			speak(ResourceUtil.getStringFromResource(NotepadEditScroll.this,R.string.enotescr_fgh));
		}
		b1 = false ; b2 = false ; b3 = false;
	}
	public void hgf()
	{

		if (   bh == true  && bg == true && bf == true )
		{
			//			final Intent resetIntent = new Intent(this,com.datapadsystem.Enoteedit.class);
			//			startActivity(resetIntent);

		}
		bf = false ; bg = false ; bh = false;
	}

	public void eepress() {
		stop();
		openFile("/sdcard/enotepad/" + fnamefromedit, "rw");
		// mEngine.openFile("/sdcard/notepad/" + fineName,"r");
		try {

			ttsReadFile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public long getFilePointer() {
		long pos = -1;
		try {
			if (AccessFile!=null)
				pos = AccessFile.getFilePointer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pos;

	}

	public void setFilePointer(long pos) {
		try {
			if (AccessFile!=null)
				AccessFile.seek(pos);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}
}
