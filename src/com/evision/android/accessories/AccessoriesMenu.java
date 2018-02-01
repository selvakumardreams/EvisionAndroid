package com.evision.android.accessories;


import java.util.ArrayList;
import java.util.List;

import com.evision.android.BaseActivity;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class AccessoriesMenu extends BaseActivity{

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

	CountDownTimer keyPressTimeout = null;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null;
	boolean SettingsAppPresent;
	boolean HomeAutomationAppPresent;
	boolean isHgKeypress = false;
	boolean isGFKeypress = false;
	boolean b1 = false, b2 = false, b3 = false;
	private String TAG = "AccessoriesMenu";
	Intent launchApp = new Intent();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standbymode_screen);

		//	SettingsAppPresent = isAppInstalled("Evision_Settings");

		//	HomeAutomationAppPresent = isAppInstalled("EvisionHomeAutomation");

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
				if (isGFKeypress == true || isHgKeypress == true) {
					isGFKeypress= false;
					isHgKeypress = false;
					lookuptable();
				}

			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("0");
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsMenu();
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

				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.setClassName(PackageUtil.PACKAGE , PackageUtil.CALCULATORSCREEN);
					startActivity(launchApp);
					finish();
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
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					//speak("thermometer under development", );
					launchApp.setClassName(PackageUtil.PACKAGE , PackageUtil.CALANDERDATE);
					startActivity(launchApp);
					finish();
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

				} else if (ACTION_UP == event.getAction()) {
					stop();
					//	speak("measuring tape under development", );
					//					IntLaunch.setClassName("com.datapadsystem","com.datapadsystem.MeasurementMenu");
					//					startActivity(IntLaunch);
					//finish();
					//speak("photometer under development", );
					speak(ResourceUtil.getStringFromResource(AccessoriesMenu.this,R.string.not_assign));
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("4");
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(AccessoriesMenu.this,R.string.not_assign));
					//					if (HomeAutomationAppPresent)
					//					{
					//						Intent goTofbInfo = new Intent();
					//						goTofbInfo.setClassName("com.evision.homeAutomation", "com.evision.homeAutomation.BluetoothChat");
					//						startActivity(goTofbInfo);
					//					} else {
					//						speak(getStringFromResource(R.string.acce_homeAutomation));
					//					}
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("5");
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(AccessoriesMenu.this,R.string.not_assign));
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("6");
					//ttsInvalidChoice();
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(AccessoriesMenu.this,R.string.not_assign));
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("7");
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(AccessoriesMenu.this,R.string.not_assign));
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					stop();
					speak("8");
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_normal);
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.TUTORMENU);
					startActivity(launchApp);
					finish();

				}
				return true;
			}
		});

		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("9");
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					//					if (SettingsAppPresent) {
					//						Intent goTofbInfo = new Intent();
					//						goTofbInfo.setClassName("com.evision.settings", "com.evision.settings.StartActivity");
					//						startActivity(goTofbInfo);
					//					} else {
					//						speak(getStringFromResource(R.string.acce_settings));
					//					}
					//speak("set up under development", );
					launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.MANUALMENU);
					startActivity(launchApp);
					finish();

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
					keyPressTimeout.cancel();
					isHgKeypress=true;
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
					b2 = setBoolean();
					stop();
					speak("G");
					isGFKeypress = true;
					keyPressTimeout.cancel();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) 
				{
					if (isHgKeypress == true) {
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE , PackageUtil.ACTIVEMAINMENU); 
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

		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("F");
					b1 = setBoolean();
					keyPressTimeout.cancel();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numFButton.setBackgroundResource(R.drawable.btn_normal);
					if (isGFKeypress = true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.STANDBYMAINMENU);
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();

				}
				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("Star");
					ttsInvalidChoice();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					//ttsMenu();
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("Hash");
					ttsInvalidChoice();
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					//ttsMenu();
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
	}

	public boolean isAppInstalled(String appName) {
		boolean check = false;
		ArrayList<PInfo> apps = getInstalledApps(false);
		final int max = apps.size();
		for (int i = 0; i < max; i++) {
			if (apps.get(i).appname.toString().equals(appName.toString())) {
				check = true;
				break;
			}
		}
		return check;
	}

	class PInfo {
		private String appname = "";
		private String pname = "";
		private String versionName = "";
		private int versionCode = 0;
	}

	private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
		ArrayList<PInfo> res = new ArrayList<PInfo>();
		List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);

		for (int i = 0; i < packs.size(); i++) {
			PackageInfo p = packs.get(i);
			if ((!getSysPackages) && (p.versionName == null)) {
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



	public void onInit(int status) {

	}

	@Override
	public void onPause() {

		if (timer != null)
			timer.cancel();
		if (mTts != null)
			stop();
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onResume() {
		if (timer != null)
			timer.start();

		super.onResume();
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
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


	public void ttsMenu() {
		stop();
		timer.cancel();
		speak(getStringFromResource(R.string.acce_welcome));
		speak(getStringFromResource(R.string.acce_select));
		speak(getStringFromResource(R.string.acce_1));
		speak(getStringFromResource(R.string.acce_2));
		//		speak(getStringFromResource(R.string.acce_3));
		//		speak(getStringFromResource(R.string.acce_4));
		//speak("5 e compass ", );

		/*speak("2 for thermometer", );
		speak("3 for photo meter", );
		speak("4 for measuring tape ", );
		speak("5 e compass ", );
		speak("6 for home automation", );*/
		speak(getStringFromResource(R.string.acce_8));
		speak(getStringFromResource(R.string.acce_9));
		speak(getStringFromResource(R.string.repeat_0));
		speak(getStringFromResource(R.string.previous_hg));
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}

	/**
	 * convert the resource int to string
	 * @param resourceId
	 * @return
	 */
	private String getStringFromResource(int resourceId)
	{
		return getResources().getString(resourceId);		
	}
}
