package com.evision.android;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;



public class PersonalInformationGPSLocation extends BaseActivity implements LocationListener {

	String AppendString=" ";
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer keyPressTimeout = null;
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
	String delStr = "";
	public String reStr="",PersonalInformationName="",PersonalInformationContact="",PersonalInformationAddress="";

	private boolean scrollDown = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	private boolean scrollUp = false;
	boolean isNetworkEnabled = false;
	boolean canGetLocation = false;
	Location location = null; // location
	double latitude; // latitude
	double longitude; // longitude
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	boolean isGPSEnabled = false;
	boolean isGFKeyPressed = false;
	boolean b1 = false, b2 = false, b3 = false;
	protected LocationManager locationManager;
	Intent launchApp = new Intent();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);
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



		PersonalInformationName = this.getIntent().getStringExtra("PersonalInformationName");
		PersonalInformationContact = this.getIntent().getStringExtra("PersonalInformationContact");
		PersonalInformationAddress = this.getIntent().getStringExtra("PersonalInformationAddress");

		///////////////
		try {
			locationManager = (LocationManager) 
					getSystemService(LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				//this.canGetLocation = true;
				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network Enabled");
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							latitude = location.getLatitude();
							Log.i("jin","jin latitude22"+latitude);
							longitude = location.getLongitude();
						}
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String address = getAddress(latitude,longitude);

		if(address.length()==0)	{
			speak("your g p s location could not be fetched. you can enter manually");
			speak("select f g to continue");
		} else	{
			reStr = address;
			speak("your g p s location is " + address);
			speak("select f g to continue");
		}

		//////////////

		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true || isGFKeyPressed == true
						|| scrollUp == true || scrollDown == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
					isGFKeyPressed = false;
					lookuptable();
				} else {
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					if(reStr==null) { 
						reStr="";
						reStr=reStr+str;
						ResetBrailleKeys();
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
					stop();
					speak("h");
					b3 = setBoolean();
					keyPressTimeout.cancel();
					isHgKeypress = true;
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
					keyPressTimeout.cancel();
					stop();
					b2 = setBoolean();
					isGFKeyPressed = true;
					speak("g");
					numGButton.setBackgroundResource(R.drawable.btn_green);
				}  else if (ACTION_UP == event.getAction()) {
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					if (isFgKeypress == true) {
						stop();
						if((reStr == "") || (reStr == null) || (reStr == " ")) {
							reStr = "";
							speak("please enter the name ");
						} else {
							speak(" f g pressed");
							launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
							launchApp.putExtra("PersonalInformationName", PersonalInformationName);
							launchApp.putExtra("PersonalInformationContact", PersonalInformationContact);
							launchApp.putExtra("PersonalInformationAddress", PersonalInformationAddress);
							launchApp.putExtra("PersonalInformationGPSLocation", reStr);
							launchApp.setClassName("com.evision.android", "com.evision.android.PersonalInformationBloodGroup"); 
							startActivity(launchApp);
							finish();
						}
					}				
					keyPressTimeout.start();
				}
				else if (isHgKeypress == true) {
					Intent launchApp = new Intent();
					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					launchApp.setClassName("com.evision.android", "com.evision.android.StandbyMainMenu"); 
					startActivity(launchApp);
					finish();
					isHgKeypress = false;
				}

				return true;
			}});


		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					b1 = setBoolean();
					speak("f");
					isFgKeypress = true;
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(isGFKeyPressed == true) {
						isGFKeyPressed = false;
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
					ttsInvalidChoice();
					numCButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numCButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		numAButton.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					numAButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					ttsInvalidChoice();
					numAButton.setBackgroundResource(R.drawable.btn_normal);
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
							if (lastChar.equalsIgnoreCase(" ")) 
								delStr = "deleting space";
							else
								delStr = "deleting  " + lastChar;
							reStr=reStr.substring(0,reStr.length()-1);
							speak(delStr);
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
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE1Button.setOnTouchListener( new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {

				if ( ACTION_DOWN == event.getAction()) {
					numE1Button.setBackgroundResource(R.drawable.btn_green);
					if(modChange){
						BrailleCommonUtil.NumericLookUpTable(mTts);
					}
					else{
						BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					}
					if(reStr!=null){

						reStr.toString().concat(AppendString);
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
				}
				else if (ACTION_UP == event.getAction()) {
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


	public String convertTwoDigit(String convert)
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append(convert);
		if(buffer.length()<2)
		{
			convert=buffer.toString();
			buffer.delete(0,buffer.length());
			buffer.append(0);
			buffer.append(convert);
			convert=buffer.toString();
			buffer.delete(0,buffer.length());
		}
		return convert;
	}



	@Override
	public void onInit(int status) {
		// speak("enter your g p s location and select F g to continue"	,TextToSpeech.QUEUE_ADD, null);
		// speak("press d to delete a character entry", TextToSpeech.QUEUE_ADD, null);
		try {
			locationManager = (LocationManager) 
					getSystemService(LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				//this.canGetLocation = true;
				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network Enabled");
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}

		} catch (Exception e) {
		}

		String Address=getAddress(latitude,longitude);

		if(Address.length()==0)
		{
			speak("your g p s location could not be fetched. you can enter manually");
			speak("select f g to continue");

		}
		else
		{
			speak("your g p s location is "+Address);
			speak("select f g to continue");
		}
		super.onInit(status);
	}
	private String getAddress(double latitude, double longitude) {
		StringBuilder result = new StringBuilder();
		try {
			Geocoder geocoder = new Geocoder(this, Locale.getDefault());
			List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
			if (addresses.size() > 0) {
				Address address = addresses.get(0);
				result.append(address.getSubLocality()).append("\n");
				result.append(address.getLocality()).append("\n");
				result.append(address.getCountryName());
			}
		} catch (IOException e) {
			Log.e("tag", e.getMessage());
		}

		return result.toString();
	}
	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}


}
