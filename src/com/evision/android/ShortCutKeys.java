package com.evision.android;


import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.ResourceUtil;


public class ShortCutKeys extends BaseActivity {
	/** Called when the activity is first created. */

	String AppendString=" ";
	Boolean RMCAppPresent;
	boolean PedestrianCrossAppPresent,BlueToothChatAppPresent,FacebookAppPresent,TweeterAppPresent,baggageMonitorAppPresent,SettingsAppPresent;
	boolean baggageLocatorAppPresent, beaconAppPresent,BillschAppPresent,CookBookAppPresent,FirstAidAppPresent,MedschAppPresent,GameAppPresent;
	private int MY_DATA_CHECK_CODE = 0;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	public static final int ACTION_INIT =1;
	public static final int ACTION_MID =2;
	public static final int ACTION_FINAL =3;
	String mylocn=null; String locationAddress="murgeshpalya, Bangalore, karnataka";
	private TextToSpeech tts;
	CountDownTimer readTimer = null;
	CountDownTimer keyPressTimeout = null;
	String msg = "";
	public static int actionSelect= 0;
	public static int actionBack = 0;
	boolean modChange = false;

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
	Button resetButton = null;
	public String reStr="";

	private boolean scrollDown = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	private boolean scrollUp = false;
	String AppName = null;
	String AppClassName = null;
	private String pathName = null;
	String function="";Boolean btAforSpecialCharacter=false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);

		PedestrianCrossAppPresent = isAppInstalled("EvisionTrafficGuide");
		BlueToothChatAppPresent = isAppInstalled("EvisionBlueToothChat");
		RMCAppPresent = isAppInstalled("EvisionRestaurantMenuCard");
		baggageMonitorAppPresent = isAppInstalled("EvisionBaggageMonitor");
		baggageLocatorAppPresent = isAppInstalled("EvisionBaggageLocator");
		beaconAppPresent = isAppInstalled("EvisionStaticBeacon");
		MedschAppPresent = isAppInstalled("Evision_MedicineSchedule");
		CookBookAppPresent = isAppInstalled("CookBook");
		FirstAidAppPresent = isAppInstalled("FirstAid");
		GameAppPresent  = isAppInstalled("EvisionWordJumbleV1");
		SettingsAppPresent = isAppInstalled("Evision_Settings");

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
		//   resetButton = (Button)findViewById(R.id.resetButton);

		// intent used of TTS
		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

		Intent intenta = getIntent();
		function = intenta.getStringExtra("function");
		Log.i("function","function=" +function);		


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
				} else{
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(tts);
					if(reStr==null)
					{ reStr="";
					reStr=reStr+str;
					ResetBrailleKeys();
					}
					else{
						reStr=reStr+str;
						ResetBrailleKeys();
					}}
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

		//final Intent intEbookRead = new Intent(this,
		//		com.datapadsystem.ActiveMainMenu.class);
		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					tts.stop();
					tts.speak("h", TextToSpeech.QUEUE_ADD, null);
					keyPressTimeout.cancel();
					actionSelect ++;
					actionBack ++;
					isHgKeypress=true;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(actionBack==ACTION_FINAL )
					{
						//	startActivity(intEbookRead);
						//	finish();
						actionSelect = 0;	
					}}
				keyPressTimeout.start();
				numHButton.setBackgroundResource(R.drawable.btn_normal);

				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener() 
		{
			public boolean onTouch(View v, MotionEvent event)
			{
				actionSelect ++;
				actionBack ++;	

				if (ACTION_DOWN == event.getAction())
				{
					keyPressTimeout.cancel();
					tts.stop();

					tts.speak("g", TextToSpeech.QUEUE_ADD, null);
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} 
				else if (ACTION_UP == event.getAction())
				{
					if (isFgKeypress == true) {
						tts.stop();
						if(reStr=="")
						{
							tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_enterwords)+reStr
									,TextToSpeech.QUEUE_ADD, null);
						}
						else{
							callApplicationForShortCut(reStr);
						}
						isFgKeypress = false;

					} else if (isHgKeypress == true) {
					}
				}
				else if (isHgKeypress == true) {
					Intent launchApp = new Intent();
					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					// launchApp.putExtra("TDLalarmhrs",reStr);
					launchApp.setClassName("com.evision.MSWord", "com.evision.MSWord.BluetoothChat"); 
					startActivity(launchApp);
					finish();
					isHgKeypress = false;
				}
				numGButton.setBackgroundResource(R.drawable.btn_normal);

				return true;

			}});

		//	final Intent intActiveMenu = new Intent(this,
		//			com.datapadsystem.enotepad.class);	
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					tts.stop();
					actionSelect ++;
					actionBack ++;	

					tts.speak("f", TextToSpeech.QUEUE_ADD, null);
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (actionBack ==ACTION_FINAL){
						//	startActivity(intActiveMenu);
						//	finish();
						actionBack = 0;
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
					ttsInvalidChoice();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});



		numAButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					//ttsInvalidChoice();
					btAforSpecialCharacter=true;
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					/*Intent launchApp11 = new Intent();
					 launchApp11.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					 launchApp11.putExtra("alphaentry", reStr);
					 launchApp11.putExtra("classname", "FMFNewUserName");

					 launchApp11.setClassName("com.datapadsystem", "com.datapadsystem.FMFNumberEntry"); 
					 startActivity(launchApp11);
					 finish();*/
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numDButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					numDButton.setBackgroundResource(R.drawable.btn_green);

					if(reStr!=null){
						if(reStr.length()!=0){
							String lastChar = reStr.substring(reStr.length()-1, reStr.length());
							String delStr = "deleting  " + lastChar;
							reStr=reStr.substring(0,reStr.length()-1);
							tts.speak(delStr, TextToSpeech.QUEUE_ADD, null);
						}
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
					//ttsInvalidChoice();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					/*Intent launchApp11 = new Intent();
					 launchApp11.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					 launchApp11.putExtra("alphaentry", reStr);
					 launchApp11.putExtra("classname", "FMFNewUserName");

					 launchApp11.setClassName("com.datapadsystem", "com.datapadsystem.FMFSpecialCharacterEntry"); 
					 startActivity(launchApp11);
					 finish();*/
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE1Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {

				if ( ACTION_DOWN == event.getAction()) {
					numE1Button.setBackgroundResource(R.drawable.btn_green);
					if(reStr=="")
					{
						tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_enterwords)+reStr
								,TextToSpeech.QUEUE_ADD, null);
					}
					else{
						callApplicationForShortCut(reStr);
					}

				}
				else if (ACTION_UP == event.getAction()) {
					numE1Button.setBackgroundResource(R.drawable.btn_normal);

				}
				return true;
			}
		});

		numE2Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if ( ACTION_DOWN == event.getAction()) {
					numE2Button.setBackgroundResource(R.drawable.btn_green);
					if(reStr=="")
					{
						tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_enterwords)+reStr
								,TextToSpeech.QUEUE_ADD, null);
					}
					else{
						callApplicationForShortCut(reStr);
					}
					///		if(ascvalue!=null){
					//			EditText txtDisplay = (EditText)findViewById(R.id.txtDisplay);
					//			txtDisplay.setText(txtDisplay.getText().append(ascvalue));
					//			/			Toast.makeText(pharynxNew.this, "Saying: " + ascvalue, Toast.LENGTH_LONG).show();
					//			tts.speak(ascvalue, TextToSpeech.QUEUE_ADD, null);
					//		}

				}
				else if (ACTION_UP == event.getAction()) {
					numE2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

	}


	@Override
	public void onInit(int status) {
		tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_enter),TextToSpeech.QUEUE_ADD, null);
		tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.delete_d), TextToSpeech.QUEUE_ADD, null);
		super.onInit(status);
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(tts);
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}

	public void callApplicationForShortCut(String shortkey)
	{
		Intent goTofbInfo = new Intent();
		if(shortkey.equalsIgnoreCase("NPD")|| shortkey.equalsIgnoreCase("NUMBERPAD") || shortkey.equalsIgnoreCase("NUMBERPADDIALING") )
		{
			goTofbInfo.setClassName("com.datapadsystem", "com.datapadsystem.number_pad"); 
			startActivity(goTofbInfo); finish();
		}
		else if(shortkey.equalsIgnoreCase("PBD")|| shortkey.equalsIgnoreCase("phone book") || shortkey.equalsIgnoreCase("phonebook") )
		{

			goTofbInfo.setClassName("com.datapadsystem", "com.datapadsystem.PhoneBook"); 
			startActivity(goTofbInfo); finish();
		}
		else if(shortkey.equalsIgnoreCase("SMS")|| shortkey.equalsIgnoreCase("MESSAGE") || shortkey.equalsIgnoreCase("MES") )
		{
			goTofbInfo.setClassName("com.datapadsystem", "com.datapadsystem.MessageMain"); 
			startActivity(goTofbInfo); finish();
		}
		else if(shortkey.equalsIgnoreCase("SPE")|| shortkey.equalsIgnoreCase("SPEEDDIAL") || shortkey.equalsIgnoreCase("SPEED") )
		{

		}
		else if(shortkey.equalsIgnoreCase("NOT")|| shortkey.equalsIgnoreCase("NOTE") || shortkey.equalsIgnoreCase("NOTEPAD")|| shortkey.equalsIgnoreCase("ENOTE") )
		{
			goTofbInfo.setClassName("com.datapadsystem", "com.datapadsystem.enotepad");
			startActivity(goTofbInfo);
			finish();
		}
		else if(shortkey.equalsIgnoreCase("VRE")|| shortkey.equalsIgnoreCase("VOICERECORDER") || shortkey.equalsIgnoreCase("VR") )
		{
			goTofbInfo.setClassName("com.datapadsystem", "com.datapadsystem.VoiceRecordMenu");
			startActivity(goTofbInfo);
			finish();
		}
		else if(shortkey.equalsIgnoreCase("EBO")|| shortkey.equalsIgnoreCase("EBOOK") || shortkey.equalsIgnoreCase("EB") )
		{

		}
		else if(shortkey.equalsIgnoreCase("VBO")|| shortkey.equalsIgnoreCase("VOICEBOOK") || shortkey.equalsIgnoreCase("VB") )
		{

		}
		else if(shortkey.equalsIgnoreCase("OCR")|| shortkey.equalsIgnoreCase("OPTICALCHARACTERRECOGNITION") || shortkey.equalsIgnoreCase("OPT") )
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_ocr), TextToSpeech.QUEUE_ADD, null);
		}
		else if(shortkey.equalsIgnoreCase("DIC")|| shortkey.equalsIgnoreCase("DICTIONARY") || shortkey.equalsIgnoreCase("SPELLCHECK") || shortkey.equalsIgnoreCase("MEANING"))
		{

		}
		else if(shortkey.equalsIgnoreCase("CUE")|| shortkey.equalsIgnoreCase("PROMPT") || shortkey.equalsIgnoreCase("PRO"))
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_cue), TextToSpeech.QUEUE_ADD, null);
		}
		else if(shortkey.equalsIgnoreCase("BRA")|| shortkey.equalsIgnoreCase("BRAILLE") || shortkey.equalsIgnoreCase("TUTOR") )
		{

		}
		else if(shortkey.equalsIgnoreCase("COM")|| shortkey.equalsIgnoreCase("COMPUTERKEYBOARD") || shortkey.equalsIgnoreCase("KEYBOARD"))
		{

		}
		else if(shortkey.equalsIgnoreCase("NEW")|| shortkey.equalsIgnoreCase("NEWS") || shortkey.equalsIgnoreCase("NEWSPAPER"))
		{
			goTofbInfo.setClassName("com.datapadsystem","com.datapadsystem.browser.Newspaper"); 
			startActivity(goTofbInfo);
			finish();

		}
		else if(shortkey.equalsIgnoreCase("TVN")|| shortkey.equalsIgnoreCase("TVNEWS") )
		{
			goTofbInfo.setClassName("com.datapadsystem","com.datapadsystem.browser.TVnews"); 
			startActivity(goTofbInfo);
			finish();
		}
		else if(shortkey.equalsIgnoreCase("WEA")|| shortkey.equalsIgnoreCase("WEATHER") || shortkey.equalsIgnoreCase("WEATHERREPORT")|| shortkey.equalsIgnoreCase("WR"))
		{

		}
		else if(shortkey.equalsIgnoreCase("WEB")|| shortkey.equalsIgnoreCase("BROWSER") || shortkey.equalsIgnoreCase("BROWSING")|| shortkey.equalsIgnoreCase("WEB BROWSER"))
		{
			goTofbInfo.setClassName("com.datapadsystem", "com.datapadsystem.browser.EnterSpecificUrl"); 
			startActivity(goTofbInfo); 
			finish();
		}
		else if(shortkey.equalsIgnoreCase("NAR")|| shortkey.equalsIgnoreCase("NARATOR") || shortkey.equalsIgnoreCase("DESCRIPTION"))
		{

		}
		else if(shortkey.equalsIgnoreCase("RES")|| shortkey.equalsIgnoreCase("RMC") || shortkey.equalsIgnoreCase("RESTAURANT")|| shortkey.equalsIgnoreCase("RESTAURANT MENU CARD"))
		{
			if (RMCAppPresent) {
				goTofbInfo.setClassName("com.evision.RMC", "com.evision.RMC.BluetoothChat");
				startActivity(goTofbInfo);
			} 
			else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_restaurant), TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if(shortkey.equalsIgnoreCase("PRO")|| shortkey.equalsIgnoreCase("PRODUCT INFO") || shortkey.equalsIgnoreCase("PI")|| shortkey.equalsIgnoreCase("PRODUCT INFORMATION"))
		{

		}
		else if(shortkey.equalsIgnoreCase("CLR")|| shortkey.equalsIgnoreCase("CALANDER") || shortkey.equalsIgnoreCase("DATE") || shortkey.equalsIgnoreCase("YEAR")|| shortkey.equalsIgnoreCase("MONTH"))
		{
			goTofbInfo.setClassName("com.datapadsystem",	"com.datapadsystem.CalanderDate");
			goTofbInfo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			goTofbInfo.putExtra("classname","ShortCutKeys");
			startActivity(goTofbInfo);
			finish();
		}
		else if(shortkey.equalsIgnoreCase("WAL")|| shortkey.equalsIgnoreCase("WALK") || shortkey.equalsIgnoreCase("WALKY") || shortkey.equalsIgnoreCase("WALKYTALKY")|| shortkey.equalsIgnoreCase("WT"))
		{
			goTofbInfo.setClassName("com.datapadsystem", "com.datapadsystem.WalkyTalky"); 
			startActivity(goTofbInfo); finish();
		}
		else if(shortkey.equalsIgnoreCase("PED")|| shortkey.equalsIgnoreCase("PEDESTRIAN") || shortkey.equalsIgnoreCase("CROSSING") || shortkey.equalsIgnoreCase("ROAD CROSSING") || shortkey.equalsIgnoreCase("PC"))
		{
			if (PedestrianCrossAppPresent) {
				goTofbInfo.setClassName("com.evision.trafficguide", "com.evision.trafficguide.BluetoothChat");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.outGuide_num2), TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if(shortkey.equalsIgnoreCase("LOC")|| shortkey.equalsIgnoreCase("LOCALDIR") || shortkey.equalsIgnoreCase("LOCALDIRECTORY") || shortkey.equalsIgnoreCase("LD")|| shortkey.equalsIgnoreCase("PLACES") || shortkey.equalsIgnoreCase("NEAR BY PLACES"))
		{
			goTofbInfo.setClassName("com.datapadsystem", "com.datapadsystem.NearbyPlaceName"); 
			startActivity(goTofbInfo); finish();
		}
		else if(shortkey.equalsIgnoreCase("ROU")|| shortkey.equalsIgnoreCase("BUS ROU") || shortkey.equalsIgnoreCase("BUS ROUTE NUMBER")||shortkey.equalsIgnoreCase("BUSROUTENUMBER")|| shortkey.equalsIgnoreCase("BUS ROUTE NO")|| shortkey.equalsIgnoreCase("BRN"))
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.outGuide_num4), TextToSpeech.QUEUE_ADD, null);

		}
		else if(shortkey.equalsIgnoreCase("DES")|| shortkey.equalsIgnoreCase("BUS DESTINATION") || shortkey.equalsIgnoreCase("BD"))
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.outGuide_num5), TextToSpeech.QUEUE_ADD, null);

		}
		else if(shortkey.equalsIgnoreCase("TRAIN")|| shortkey.equalsIgnoreCase("TRAIN TIME") || shortkey.equalsIgnoreCase("BUS TIME") || shortkey.equalsIgnoreCase(" TIME TABLE")|| shortkey.equalsIgnoreCase("TT") )
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.outGuide_num6), TextToSpeech.QUEUE_ADD, null);

		}
		else if(shortkey.equalsIgnoreCase("CUR")|| shortkey.equalsIgnoreCase("CURRENT LOCATION") || shortkey.equalsIgnoreCase("MY LOCATION") || shortkey.equalsIgnoreCase("THIS") )
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.locat_num1)+ mylocn,TextToSpeech.QUEUE_ADD, null);
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.locat_num1)+ locationAddress,TextToSpeech.QUEUE_ADD, null);

		}
		else if(shortkey.equalsIgnoreCase("FRI")|| shortkey.equalsIgnoreCase("FRIEND") || shortkey.equalsIgnoreCase("FRIENDS LOC")|| shortkey.equalsIgnoreCase("FRIENDS LOCATION") || shortkey.equalsIgnoreCase("LOCATE FRIEND")|| shortkey.equalsIgnoreCase("FIND MY FRIEND"))
		{
			goTofbInfo.setClassName("com.datapadsystem","com.datapadsystem.FMFriendMenu");
			startActivity(goTofbInfo);
			finish();
		}
		else if(shortkey.equalsIgnoreCase("CAM")|| shortkey.equalsIgnoreCase("CAMPUS")|| shortkey.equalsIgnoreCase("CAMPUS GUIDE")|| shortkey.equalsIgnoreCase("CG") )
		{

		}
		else if(shortkey.equalsIgnoreCase("FAV")|| shortkey.equalsIgnoreCase("FAV SPOT") || shortkey.equalsIgnoreCase("MY FAVOURITE SPOT")|| shortkey.equalsIgnoreCase("MFS"))
		{

		}
		else if(shortkey.equalsIgnoreCase("PATH")|| shortkey.equalsIgnoreCase("PAT") || shortkey.equalsIgnoreCase("MAKEMYPATH")|| shortkey.equalsIgnoreCase("MMP"))
		{

		}
		else if(shortkey.equalsIgnoreCase("HOM")|| shortkey.equalsIgnoreCase("GETHOME") || shortkey.equalsIgnoreCase("MY HOME")|| shortkey.equalsIgnoreCase("GMH")|| shortkey.equalsIgnoreCase("HOME"))
		{
			goTofbInfo.setClassName("com.datapadsystem", "com.datapadsystem.getMeHome"); 
			startActivity(goTofbInfo); finish();

		}
		else if(shortkey.equalsIgnoreCase("RAD")|| shortkey.equalsIgnoreCase("RADIO") || shortkey.equalsIgnoreCase("RADIOCHAT")|| shortkey.equalsIgnoreCase("RC")|| shortkey.equalsIgnoreCase("CHATTING"))
		{
			if (BlueToothChatAppPresent) {
				goTofbInfo.setClassName("com.evision.chat", "com.evision.chat.BluetoothChat");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_radio), TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if(shortkey.equalsIgnoreCase("FAC")|| shortkey.equalsIgnoreCase("FACEBOOK")|| shortkey.equalsIgnoreCase("FB")|| shortkey.equalsIgnoreCase("FACE") )
		{
			if (FacebookAppPresent) {
				goTofbInfo.setClassName("com.evision.facebook", "com.evision.facebook.startAct");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_facebook), TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if(shortkey.equalsIgnoreCase("TWE")|| shortkey.equalsIgnoreCase("TWEETER")|| shortkey.equalsIgnoreCase("TWEET") )
		{
			if (TweeterAppPresent) {
				goTofbInfo.setClassName("com.evision.tweeter", "com.evision.tweeter.startAct");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_tweeter), TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if(shortkey.equalsIgnoreCase("EMA")|| shortkey.equalsIgnoreCase("EMAIL")|| shortkey.equalsIgnoreCase("MAIL")|| shortkey.equalsIgnoreCase("EM"))
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_email), TextToSpeech.QUEUE_ADD, null);
		}
		else if(shortkey.equalsIgnoreCase("MON")|| shortkey.equalsIgnoreCase("BAGGAGEMONITOR")|| shortkey.equalsIgnoreCase("BAGMONITOR")|| shortkey.equalsIgnoreCase("BM"))
		{
			if (baggageMonitorAppPresent) {
				goTofbInfo.setClassName("com.evision.BaggageMonitor", "com.evision.BaggageMonitor.BluetoothChat");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.safeMenu_num1), TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if(shortkey.equalsIgnoreCase("LOC")|| shortkey.equalsIgnoreCase("BAGGAGELOCATOR")|| shortkey.equalsIgnoreCase("BAGMLOCATOR")|| shortkey.equalsIgnoreCase("BL"))
		{
			if (baggageMonitorAppPresent) {
				goTofbInfo.setClassName("com.evision.baggageLocator", "com.evision.baggageLocator.BluetoothChat");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.safeMenu_num2), TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if(shortkey.equalsIgnoreCase("WAR")|| shortkey.equalsIgnoreCase("WARNING")|| shortkey.equalsIgnoreCase("WARNING RECEIVER")|| shortkey.equalsIgnoreCase("WRE"))
		{
			if (beaconAppPresent) {
				goTofbInfo.setClassName("com.evision.beacon", "com.evision.beacon.BluetoothChat");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.safeMenu_num3), TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if(shortkey.equalsIgnoreCase("FIR")|| shortkey.equalsIgnoreCase("FIRE")|| shortkey.equalsIgnoreCase("FIRE ALARM")|| shortkey.equalsIgnoreCase("FA"))
		{

		}
		else if(shortkey.equalsIgnoreCase("ALA")|| shortkey.equalsIgnoreCase("ALARM"))
		{
			Intent intent = new Intent();
			intent.setClassName("com.datapadsystem","com.datapadsystem.AlarmHr");
			startActivity(intent);
			finish();
		}
		else if(shortkey.equalsIgnoreCase("APP")|| shortkey.equalsIgnoreCase("APPOINT")|| shortkey.equalsIgnoreCase("APPOINTMENT")|| shortkey.equalsIgnoreCase("MEETING"))
		{
			Intent launchApp = new Intent();
			launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			launchApp.setClassName("com.datapadsystem", "com.datapadsystem.Appointmentmenu"); 
			startActivity(launchApp);
			finish();
		}
		else if(shortkey.equalsIgnoreCase("TOD")|| shortkey.equalsIgnoreCase("TDL")|| shortkey.equalsIgnoreCase("TO DO")|| shortkey.equalsIgnoreCase("TO DO LIST")|| shortkey.equalsIgnoreCase("LIST TO DO")||shortkey.equalsIgnoreCase("THINGS TO DO"))
		{
			goTofbInfo.setClassName("com.datapadsystem","com.datapadsystem.ToDoListMenu");
			startActivity(goTofbInfo);
			finish();		
		}
		else if(shortkey.equalsIgnoreCase("DIA")|| shortkey.equalsIgnoreCase("TEXT DIARY")|| shortkey.equalsIgnoreCase("TD")|| shortkey.equalsIgnoreCase("DIARY"))
		{
			goTofbInfo.setClassName("com.evision.textDiary", "com.evision.textDiary.textdiary");
			startActivity(goTofbInfo);
		}
		else if(shortkey.equalsIgnoreCase("VDA")|| shortkey.equalsIgnoreCase("VOICE DIARY")|| shortkey.equalsIgnoreCase("VD")|| shortkey.equalsIgnoreCase("DIARY VOICE"))
		{
			goTofbInfo.setClassName("com.datapadsystem",	"com.datapadsystem.VoiceDiary");
			startActivity(goTofbInfo);
			finish();
		}
		else if(shortkey.equalsIgnoreCase("BILL")|| shortkey.equalsIgnoreCase("BILL SCHEDULES")|| shortkey.equalsIgnoreCase("BS")|| shortkey.equalsIgnoreCase("BILLS"))
		{
			if (BillschAppPresent) 
			{
				goTofbInfo.setClassName("com.bill.billschedule.app", "com.bill.billschedule.app.BillScheduleActivity");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.mediAdvice_num4), TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if(shortkey.equalsIgnoreCase("WAL")|| shortkey.equalsIgnoreCase("WALLET")|| shortkey.equalsIgnoreCase("ELECTRONIC WALLET")|| shortkey.equalsIgnoreCase("EW"))
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_electronic), TextToSpeech.QUEUE_ADD, null);
		}
		else if(shortkey.equalsIgnoreCase("CUR")|| shortkey.equalsIgnoreCase("CURRENCY")|| shortkey.equalsIgnoreCase("CURRENCY IDENTIFIER")|| shortkey.equalsIgnoreCase("CI"))
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_currency), TextToSpeech.QUEUE_ADD, null);
		}
		else if(shortkey.equalsIgnoreCase("COL")|| shortkey.equalsIgnoreCase("COLOR SENSOR")|| shortkey.equalsIgnoreCase("CS")|| shortkey.equalsIgnoreCase("COLOR"))
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_color), TextToSpeech.QUEUE_ADD, null);
		}
		else if(shortkey.equalsIgnoreCase("COO")|| shortkey.equalsIgnoreCase("COOK")|| shortkey.equalsIgnoreCase("COOK BOOK")|| shortkey.equalsIgnoreCase("COOKING"))
		{
			if (CookBookAppPresent) {
				goTofbInfo.setClassName("com.mobiwhiz.cookbook.appstart", "com.mobiwhiz.cookbook.appstart.StartActivity");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.persMenu_num7), TextToSpeech.QUEUE_ADD, null);
			}			
		}
		else if(shortkey.equalsIgnoreCase("MEDI")|| shortkey.equalsIgnoreCase("MEDICINE")|| shortkey.equalsIgnoreCase("MEDICINE IDENTIFIER")|| shortkey.equalsIgnoreCase("MI"))
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.mediAdvice_num1), TextToSpeech.QUEUE_ADD, null);

		}
		else if(shortkey.equalsIgnoreCase("PRES")|| shortkey.equalsIgnoreCase("PRESCRIPTION")|| shortkey.equalsIgnoreCase("MEDICINE PRESCIPTION")|| shortkey.equalsIgnoreCase("PRE"))
		{
			/*Intent launchApp = new Intent();
			launchApp.setClassName("com.datapadsystem", "com.datapadsystem.Medicalprescription"); 
			 startActivity(launchApp); finish();*/
		}
		else if(shortkey.equalsIgnoreCase("FIR")|| shortkey.equalsIgnoreCase("FIRST")|| shortkey.equalsIgnoreCase("FIRST AID")|| shortkey.equalsIgnoreCase("FA"))
		{
			if (FirstAidAppPresent) {
				goTofbInfo.setClassName("com.evision.medicalprescription", "com.evision.medicalprescription.MedicalPrescriptionActivity");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.mediAdvice_num3), TextToSpeech.QUEUE_ADD, null);
			}	
		}
		else if(shortkey.equalsIgnoreCase("MEDS")|| shortkey.equalsIgnoreCase("MEDICINES")|| shortkey.equalsIgnoreCase("MEDICINE SCHEDULE")|| shortkey.equalsIgnoreCase("MS"))
		{
			if (MedschAppPresent) {
				goTofbInfo.setClassName("com.medicine.medicineschedule.app", "com.medicine.medicineschedule.app.MedicineScheduleActivity");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.mediAdvice_num4), TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if(shortkey.equalsIgnoreCase("HIS")|| shortkey.equalsIgnoreCase("MEDICINE HISTORY")|| shortkey.equalsIgnoreCase("MEDICAL HISTORY")|| shortkey.equalsIgnoreCase("MH"))
		{

		}
		else if(shortkey.equalsIgnoreCase("FM")|| shortkey.equalsIgnoreCase("FM RADIO")|| shortkey.equalsIgnoreCase("FMR"))
		{

		}
		else if(shortkey.equalsIgnoreCase("MPT")|| shortkey.equalsIgnoreCase("SONGS")|| shortkey.equalsIgnoreCase("MPTHREE")|| shortkey.equalsIgnoreCase("MP3")|| shortkey.equalsIgnoreCase("MUSIC")|| shortkey.equalsIgnoreCase("MUSIC PLAYER"))
		{
			goTofbInfo.setClassName("com.datapadsystem","com.datapadsystem.CreateFileList");
			goTofbInfo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			goTofbInfo.putExtra(BrailleCommonUtil.APP_NAME_INTENT,BrailleCommonUtil.APP_NAME_MP3);
			goTofbInfo.putExtra("classname","ShortCutKeys");
			startActivity(goTofbInfo);
			finish();
		}
		else if(shortkey.equalsIgnoreCase("MUSICAL")|| shortkey.equalsIgnoreCase("MUSICAL INSTRUMENT")|| shortkey.equalsIgnoreCase("INSTRUMENT"))
		{

		}
		else if(shortkey.equalsIgnoreCase("GAM")|| shortkey.equalsIgnoreCase("WORD JUMBLE")|| shortkey.equalsIgnoreCase("GAME")|| shortkey.equalsIgnoreCase("JUMBLE WORDS"))
		{
			if (GameAppPresent) {
				goTofbInfo.setClassName("com.mobiwhiz.worjumble.app", "com.mobiwhiz.worjumble.app.StartActivity");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.entermenu_install), TextToSpeech.QUEUE_ADD, null);
			}
		}

		else if(shortkey.equalsIgnoreCase("CAL")|| shortkey.equalsIgnoreCase("CALCULATOR")|| shortkey.equalsIgnoreCase("CALCI")|| shortkey.equalsIgnoreCase("CALC"))
		{
			goTofbInfo.setClassName("com.datapadsystem","com.datapadsystem.CalculatorScreen");
			startActivity(goTofbInfo);
			finish();
		}
		else if(shortkey.equalsIgnoreCase("DIS")|| shortkey.equalsIgnoreCase("DIST")|| shortkey.equalsIgnoreCase("DISTANCE")|| shortkey.equalsIgnoreCase("DISTANCE ESTIMATOR"))
		{

		}
		else if(shortkey.equalsIgnoreCase("PHO")|| shortkey.equalsIgnoreCase("PHOTOMETER")|| shortkey.equalsIgnoreCase("LIGHT")|| shortkey.equalsIgnoreCase("BRIGHTNESS"))
		{

		}
		else if(shortkey.equalsIgnoreCase("THE")|| shortkey.equalsIgnoreCase("THERMOMETER")|| shortkey.equalsIgnoreCase("THERMO")|| shortkey.equalsIgnoreCase("HEAT"))
		{

		}
		else if(shortkey.equalsIgnoreCase("ECO")|| shortkey.equalsIgnoreCase("ECOMPASS")|| shortkey.equalsIgnoreCase("COMPASS")|| shortkey.equalsIgnoreCase("EC"))
		{

		}
		else if(shortkey.equalsIgnoreCase("RCO")|| shortkey.equalsIgnoreCase("REMOTE CONTROL")|| shortkey.equalsIgnoreCase("REMOTE")|| shortkey.equalsIgnoreCase("RC"))
		{
			//remote control
		}
		else if(shortkey.equalsIgnoreCase("SCO")|| shortkey.equalsIgnoreCase("SMS CONTROL")|| shortkey.equalsIgnoreCase("SMSC")|| shortkey.equalsIgnoreCase("SC"))
		{

		}
		else if(shortkey.equalsIgnoreCase("ACO")|| shortkey.equalsIgnoreCase("ACCESS CONTROL")|| shortkey.equalsIgnoreCase("ACCESS")|| shortkey.equalsIgnoreCase("AC"))
		{

		}
		else if(shortkey.equalsIgnoreCase("DLO")|| shortkey.equalsIgnoreCase("DATA LOGGER")|| shortkey.equalsIgnoreCase("DATA")|| shortkey.equalsIgnoreCase("DL"))
		{

		}
		else if(shortkey.equalsIgnoreCase("HEL")|| shortkey.equalsIgnoreCase("HELP"))
		{

		}
		else if(shortkey.equalsIgnoreCase("SET")|| shortkey.equalsIgnoreCase("SET UP")|| shortkey.equalsIgnoreCase("SETUP") || shortkey.equalsIgnoreCase("SETTINGS") || shortkey.equalsIgnoreCase("SP"))
		{
			if (SettingsAppPresent) {
				goTofbInfo.setClassName("com.evision.settings", "com.evision.settings.StartActivity");
				startActivity(goTofbInfo);
			} else {
				tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.acce_settings), TextToSpeech.QUEUE_ADD, null);
			}
		}
		else
		{
			tts.speak(ResourceUtil.getStringFromResource(ShortCutKeys.this,R.string.shortCut_nokey), TextToSpeech.QUEUE_ADD, null);
		}
	}

	public boolean isAppInstalled(String appName) {
		Log.w("onCreate", "4");
		boolean check = false;
		ArrayList<PInfo> apps = getInstalledApps(false);
		final int max = apps.size();
		for (int i = 0; i < max; i++) {
			Log.w("onCreate", "4a;" + apps.get(i).appname.toString());
			if (apps.get(i).appname.toString().equals(appName.toString())) {
				Log.w("onCreate", "5");
				check = true;
				break;
			}
		}
		Log.w("onCreate", "5a:" + check);
		return check;
	}

	class PInfo {
		private String appname = "";
		private String pname = "";
		private String versionName = "";
		private int versionCode = 0;
	}

	private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
		Log.w("onCreate", "6");
		ArrayList<PInfo> res = new ArrayList<PInfo>();
		List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);

		for (int i = 0; i < packs.size(); i++) {
			PackageInfo p = packs.get(i);
			if ((!getSysPackages) && (p.versionName == null)) {
				Log.w("onCreate", "7");
				continue;
			}
			PInfo newInfo = new PInfo();
			newInfo.appname = p.applicationInfo.loadLabel(getPackageManager())
					.toString();
			newInfo.pname = p.packageName;
			newInfo.versionName = p.versionName;
			newInfo.versionCode = p.versionCode;
			res.add(newInfo);
		}
		return res;
	}

}
