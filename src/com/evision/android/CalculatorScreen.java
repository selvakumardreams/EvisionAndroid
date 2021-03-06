package com.evision.android;

import com.evision.android.BaseActivity;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;


public class CalculatorScreen extends BaseActivity {

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
	static String ADD = "addition";
	static String SUB = "Subtract";
	static String MULT = "Multiply";
	static String DIV = "Divide";
	static String EQU = "equal to";
	static String dot = ".";
	String delStr="";

	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;

	private String TAG = "Calculator";


	String op1 ="", op2 = "";
	double numb1;
	double numb2;
	double result = 0;
	String operator = null;
	boolean isOperatorPress = false;
	boolean b1 = false, b2 = false, b3 = false;
	private boolean isHashKeyPressed = false, isStarKeyPressed = false,
			fgKey = false , hg=false;
	boolean isGFKeyPressed = false;
	boolean isDecimal = false;
	boolean isDecimalResult = false;
	Intent launchApp = new Intent();

	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;

	// CountDownTimer doubleClickTimer = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standbymode_screen);

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

		timer = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				ttsForCalculatorMenu();
			}
		}.start();

		keyPressTimeout = new CountDownTimer(1000, 2000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				isHashKeyPressed = false;
				isGFKeyPressed = false;
				isStarKeyPressed = false;
				fgKey = false;
				isOperatorPress = false;
				lookuptable();
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					num0Button.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					stop();
					speak(ResourceUtil.getStringFromResource(CalculatorScreen.this,R.string.calc_zero));
					// operand1.
				} else if (ACTION_UP == event.getAction()) {
					appendKeyPressValue("0");
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					stop();
					speak(getStringFromResource(R.string.calc_one));					
					if (isHashKeyPressed == true) {
						isStarKeyPressed = false;
						keyPressTimeout.cancel();
						speak(getStringFromResource(R.string.calc_add));
						isHashKeyPressed = false;
						operator = ADD;
						isDecimal = false;
						isOperatorPress = true;
					} else {
						appendKeyPressValue("1");
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
					timer.cancel();
					stop();
					speak(getStringFromResource(R.string.calc_two));
					if (isHashKeyPressed == true) {
						keyPressTimeout.cancel();
						isStarKeyPressed = false;
						speak(getStringFromResource(R.string.calc_sub));
						isHashKeyPressed = false;
						operator = SUB;
						isDecimal = false;
						isOperatorPress = true;
					} else {
						appendKeyPressValue("2");
					}
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		// Please create new activity for Editing files
		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					stop();
					speak(getStringFromResource(R.string.calc_three));
					if (isHashKeyPressed == true) {
						isStarKeyPressed = false;
						keyPressTimeout.cancel();
						speak(getStringFromResource(R.string.calc_mul));
						isHashKeyPressed = false;
						operator = MULT;
						isDecimal = false;
						isOperatorPress = true;
					} else {
						appendKeyPressValue("3");
					}
				} else if (ACTION_UP == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					stop();
					speak(getStringFromResource(R.string.calc_four));
					if (isHashKeyPressed == true) {
						isStarKeyPressed = false;
						keyPressTimeout.cancel();
						speak(getStringFromResource(R.string.calc_div));
						isHashKeyPressed = false;
						operator = DIV;
						isDecimal = false;
						isOperatorPress = true;
					} else {
						appendKeyPressValue("4");
					}
				} else if (ACTION_UP == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					stop();
					speak(getStringFromResource(R.string.calc_five));

				} else if (ACTION_UP == event.getAction()) {
					if (isHashKeyPressed == true) {
						isHashKeyPressed = false;
						isDecimalResult = true;
						keyPressTimeout.cancel();
						if (isDecimal == false) {
							appendKeyPressValue(".");
							isDecimal = true;
							speak("point");
						} else {
							speak("decimal key all ready presed");
						}
					} else
						appendKeyPressValue("5");
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					stop();
					speak(getStringFromResource(R.string.calc_six));
					appendKeyPressValue("6");
				} else if (ACTION_UP == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num7Button.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					stop();
					appendKeyPressValue("7");
					speak(getStringFromResource(R.string.calc_seven));
				} else if (ACTION_UP == event.getAction()) {
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		// final Intent intActive = new Intent(this, com.pharynx.Pharynx.class);
		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					stop();
					appendKeyPressValue("8");
					speak(getStringFromResource(R.string.calc_eight));
				} else if (ACTION_UP == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					stop();
					if (isHashKeyPressed == true) {
						speak(getStringFromResource(R.string.calc_welcome));
						speak(getStringFromResource(R.string.calc_new));
						speak(getStringFromResource(R.string.calc_operand));
						speak(getStringFromResource(R.string.calc_select));
						speak(getStringFromResource(R.string.calc_hash1));
						speak(getStringFromResource(R.string.calc_hash2));
						speak(getStringFromResource(R.string.calc_hash3));
						speak(getStringFromResource(R.string.calc_hash4));
						speak(getStringFromResource(R.string.calc_hash5));
						speak(getStringFromResource(R.string.calc_fg));
						speak(getStringFromResource(R.string.calc_starh));
						speak(getStringFromResource(R.string.calc_hg));
						isHashKeyPressed = false;
					} else 
						appendKeyPressValue("9");
					speak(getStringFromResource(R.string.calc_nine));
				} else if (ACTION_UP == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);

				}
				return true;
			}
		});

		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					numFButton.setBackgroundResource(R.drawable.btn_green);
					stop();
					b1 = setBoolean();
					keyPressTimeout.cancel();
					speak(getStringFromResource(R.string.F));
				} else if (ACTION_UP == event.getAction()) {
					fgKey = true;
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
		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					numGButton.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					stop();
					isGFKeyPressed = true;
					b2 =setBoolean();
					speak(getStringFromResource(R.string.G));
					keyPressTimeout.cancel();
					//hg press to go one step back
					if(hg==true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.ACCESSORIESMENU);
						startActivity(launchApp);
						finish();
					}
					// #G is equal
					if (isHashKeyPressed == true) {
						speak(getStringFromResource(R.string.calc_cancel));
						cancelKeyPressValue();
						isHashKeyPressed = false;
					} // FG is Equal;
					else if (fgKey == true) {
						isGFKeyPressed = false;
						if ((!op1.equalsIgnoreCase("")) && (!op2.equalsIgnoreCase(""))) {
							equalKeyPressResult();
						} else {
							speak("Plese Enter the Values");
							cancelKeyPressValue();
						}

						fgKey = false;
					}
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					numHButton.setBackgroundResource(R.drawable.btn_green);
					timer.cancel();
					stop();
					keyPressTimeout.cancel();
					isGFKeyPressed = false;
					hg=true;
					b3 = setBoolean();
					speak(getStringFromResource(R.string.H));

					if (isStarKeyPressed == true) {
						deleteKeyPressValue();
						isStarKeyPressed = false;
						hg=false;
						// After Handling flag, reset the value
					}
				} else if (ACTION_UP == event.getAction()) {
					numHButton.setBackgroundResource(R.drawable.btn_normal);
					lookuptable();
					keyPressTimeout.start();
				}
				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {

					timer.cancel();
					stop();
					speak(getStringFromResource(R.string.calc_star));
					keyPressTimeout.cancel();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					isStarKeyPressed = true;
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					if (timer != null)
						timer.cancel();
					stop();
					speak(getStringFromResource(R.string.calc_hash));
					if (keyPressTimeout != null)
						keyPressTimeout.cancel();
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					isHashKeyPressed = true;
					keyPressTimeout.start();
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
	}


	public void appendKeyPressValue(String number) {
		if (isOperatorPress == false) {
			op1 = op1 + number;
			Log.i("op1", " value");
		} else {
			Log.i("op2", " value");
			op2 = op2 + number;
		}
	}

	public void deleteKeyPressValue() {
		Log.i(TAG, "Before deleteKeyPressValue Op1 =" + op1 + "Op2 = " + op2);
		int delValue = 0;
		if (isOperatorPress == false) {
			if (op1.length()!=0) {
				String lastChar = op1.substring(op1.length()-1, op1.length());
				delStr = "deleting  " + lastChar;
				op1=op1.substring(0,op1.length()-1);
			}
			else
				delValue = 11; // readout isse fixed temp

		} else {
			if (op2.length()!=0) {
				String lastChar = op2.substring(op2.length()-1, op2.length());
				delStr = "deleting  " + lastChar;
				op2=op2.substring(0,op2.length()-1);
			}
			else
				delValue = 11;
		}

		if (delValue != 11)
			speak(delStr);
		else
			speak(ResourceUtil.getStringFromResource(CalculatorScreen.this,R.string.nonum_delete));

		Log.i(TAG, "After deleteKeyPressValue Op1 =" + op1 + "Op2 = " + op2);
	}

	public void cancelKeyPressValue() {
		op1 = op2 = "";
		result = 0;
		operator = null;
		isOperatorPress = false;
		isStarKeyPressed = false;
		isHashKeyPressed = false;
		fgKey = false;
		isDecimal = false;
	}

	public void equalKeyPressResult() {

		try {
			numb1 = Double.parseDouble(op1);
			numb2 = Double.parseDouble(op2);
			String symbol = "";
			// boolean error = false;
			if (operator == ADD) {
				result = numb1 + numb2;
				symbol = " plus ";
			} else if (operator == SUB) {
				result = numb1 - numb2;
				symbol = " minus ";
			} else if (operator == MULT) {
				result = numb1 * numb2;
				symbol = " multiplied by ";
			} else if (operator == DIV) {
				if (numb2 == 0) {
					speak(getStringFromResource(R.string.calc_error));
				} else {
					result = numb1 / numb2;
					symbol = " divided by ";
				}
			}  else if (operator == null) {
				result = numb1;
			}
			/*
			 * else { speak("Error"); error =
			 * true; }
			 */// if (error == false) {
			/*
			 * speak(Integer.toString(op1));
			 * speak(operator);
			 * speak(Integer.toString(op2));
			 * Double.toString(result)
			 */
			double newResult = Math.round(result*100.0)/100.0;
			String str = Double.toString(newResult);

			Log.i(TAG, " Double String :" + str);

			String[] doubleFloat = str.split("\\.");
			Log.i(TAG, " doubleFloat[0] :" + doubleFloat[0]);
			Log.i(TAG, " doubleFloat[1] :" + doubleFloat[1]);
			String beforeDot = doubleFloat[0];
			String afterDot = doubleFloat[1];
			if (afterDot.compareTo("0") == 0) {
				afterDot = "";
			} else {
				afterDot = "." + afterDot;
			}
			if (isDecimalResult == true) {
				speak(Double.toString(numb1) + symbol + Double.toString(numb2));
				speak(EQU);
				speak(beforeDot + afterDot);
				cancelKeyPressValue();
			} else {
				speak(Integer.valueOf(String.valueOf(Math.round(numb1))) + symbol + Integer.valueOf(String.valueOf(Math.round(numb2))));
				speak(EQU);
				speak(beforeDot + afterDot);
				cancelKeyPressValue();
			}
		} catch (Exception e) {
			Log.i("", e.getMessage());
			speak("please Following right formate");
			cancelKeyPressValue();
		}
		// }
	}

	@Override
	public void onStop() {
		Log.i(TAG, "EBook OnStop");
		super.onStop();
	}

	@Override
	public void onResume() {
		Log.i(TAG, "EBook onResume");
		if (timer != null)
			timer.start();
		super.onResume();
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

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(this.mTts);
	}

	public void ttsForCalculatorMenu() {

		timer.cancel();
		speak(getStringFromResource(R.string.calc_welcome));
		speak(getStringFromResource(R.string.calc_format));
		speak(getStringFromResource(R.string.calc_newformate));


	}

	/**
	 * Convert the resource int to string
	 * @param resourceId
	 * @return
	 */
	private String getStringFromResource(int resourceId) {
		return getResources().getString(resourceId);

	}

}
