package com.evision.android;

import java.util.Calendar;
import java.util.GregorianCalendar;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;


public class CalanderYear extends BaseActivity {
	/** Called when the activity is first created. */


	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;

	CountDownTimer keyPressTimeout = null;
	Intent launchApp = new Intent();
	private boolean starPressed = false;
	boolean b1 = false, b2 = false, b3 = false;

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
	public String reStr="";

	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeyPressed = false;
	boolean isContinue = false;
	private String date,month,year;
	int months,myDay,reminderDay,desDay,destinationDay,day,myYear,lessDay;
	private Calendar myCalendar,givenCalendar,calendar;
	int desWeek;
	String Caldate;
	String Calmonth;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standbymode_screen);

		Intent intent = getIntent();
		Caldate = intent.getStringExtra("Caldate");
		Log.i("Caldate","Caldate=" +Caldate);		
		Calmonth = intent.getStringExtra("Calmonth");
		Log.i("Calmonth","Calmonth=" +Calmonth);		

		myCalendar = Calendar.getInstance();
		givenCalendar = Calendar.getInstance();
		calendar = Calendar.getInstance();
		myDay = myCalendar.get(Calendar.DAY_OF_YEAR);
		myYear = myCalendar.get(Calendar.YEAR);


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


		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true || isGFKeyPressed == true
						|| starPressed == true) {
					lookuptable();
					isFgKeypress = false;
					isHgKeypress = false;
					isGFKeyPressed = false;
					starPressed = false;
				} else {
					String str = BrailleCommonUtil.NumericLookUpTable(mTts);
					reStr=reStr+str;
					ResetBrailleKeys();
				}
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num0Button.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					speak("0");
					reStr =reStr+"0";
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
				} else if (ACTION_UP == event.getAction()) {
					speak("1");
					reStr =reStr+"1";
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
				} else if (ACTION_UP == event.getAction()) {
					speak("2");
					reStr =reStr+"2";
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});


		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					speak("3");
					reStr =reStr+"3";
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					speak("4");
					reStr =reStr+"4";
					num4Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					speak("5");
					reStr =reStr+"5";
					num5Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num6Button.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					speak("6");
					reStr =reStr+"6";
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num7Button.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					speak("7");
					reStr =reStr+"7";
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					speak("8");
					reStr =reStr+"8";
					num8Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_green);
					stop();
				} else if (ACTION_UP == event.getAction()) {
					speak("9");
					reStr =reStr+"9";
					num9Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("h");
					keyPressTimeout.cancel();
					isHgKeypress=true;
					b3 = setBoolean();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(starPressed == true){
						if(reStr!=null) {
							if(reStr.length()!=0){
								String lastChar = reStr.substring(reStr.length()-1, reStr.length());
								String delStr = "deleting  " + lastChar;
								reStr=reStr.substring(0,reStr.length()-1);
								speak(delStr);
							} else
								speak(ResourceUtil.getStringFromResource(CalanderYear.this,R.string.nonum_delete));
						}
					}
					lookuptable();
					keyPressTimeout.start();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}

				return true;
			}
		});

		numGButton.setOnTouchListener(new OnTouchListener()  {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
					isGFKeyPressed = true;
					speak("g");
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (isFgKeypress == true) {
						stop();
						if (isContinue == true) {
							launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.CALANDERDATE);
							startActivity(launchApp);
							finish();

						} else {
							if (reStr.length() == 4 && reStr != null) {
								if(reStr=="") {
									speak("please enter the year "+reStr);
								} else {
									speak("year entered is "+reStr);
									waitForSpeechFinished();
									date = Caldate;
									month = Calmonth;      		
									months = Integer.parseInt(month)-1;
									year = reStr;
									if(months == 0 || months == 2 || months == 4 || months == 6 || months == 7 || months == 9 || months == 11) {
										if(Integer.parseInt(date) <= 31) {
											perform();
										}
										else 
											speak("");

									}
									else if(months == 3 || months == 5 || months == 8 || months == 10) {
										if(Integer.parseInt(date) <= 30) {
											perform();
										}
										else
											speak("Entered month has only 30 days");
									}
									else if(months == 1) {
										GregorianCalendar cal = new GregorianCalendar();
										if(cal.isLeapYear(Integer.parseInt(year))) {
											if(Integer.parseInt(date) <= 29) {
												perform();
											}
											else {
												speak("Entered month has only 29 days");
											}
										}
										else {
											if(Integer.parseInt(date) <= 28) {
												perform();
											}
											else {
												speak("Entered month has only 28 days");
											}
										}
									}

								}
							} else {
								reStr="";
								speak("please enter the valid year ");
							}
						}
					} 
					if (isHgKeypress == true) {
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.CALANDERMONTH); 
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					stop();
					speak("star");
					if (keyPressTimeout != null)
						keyPressTimeout.cancel();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					starPressed =true;
					/*launchApp.setClassName(PackageUtil.PACKAGE, "com.evision.android.SpeedDialing"); 
					 startActivity(launchApp); finish();*/
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				} 
				return true;
			}
		});



		/*final Intent intActiveMenu = new Intent(this,
				com.datapadsystem.enotepad.class);	*/
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
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

	}


	public String convertTwoDigit(String convert)
	{
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


	public String calculateDay(int day) {
		String whichDay;
		if(day == 1) {
			whichDay = "Sunday";
		} else if(day == 2) {
			whichDay = "Monday";
		} else if(day == 3) {
			whichDay = "Tuesday";	
		} else if(day == 4) {
			whichDay = "Wednesday";
		} else if(day == 5) {
			whichDay = "Thursday";	
		} else if(day == 6) {
			whichDay = "Friday";
		} else  {
			whichDay = "Saturday";	
		}
		return whichDay;
	}

	@Override
	public void onInit(int status) {
		speak(ResourceUtil.getStringFromResource(CalanderYear.this, R.string.CalanderYear_welcome));
		super.onInit(status);
	}

	public void perform() {

		date = convertTwoDigit(date);
		month = convertTwoDigit(month);
		desDay = 0;

		if(Integer.parseInt(year) >= myYear) {        		
			for(int i=myCalendar.get(Calendar.YEAR);i<=Integer.parseInt(year);i++) {
				if(i == Integer.parseInt(year)) {        	
					givenCalendar.set(Calendar.DATE,Integer.parseInt(date));
					givenCalendar.set(Calendar.MONTH,months);
					givenCalendar.set(Calendar.YEAR,Integer.parseInt(year));
					desDay = desDay + givenCalendar.get(givenCalendar.DAY_OF_YEAR);
					desWeek = givenCalendar.get(givenCalendar.WEEK_OF_YEAR);
				}
				else  {
					calendar.set(Calendar.DATE,31);
					calendar.set(Calendar.MONTH,11);        	
					calendar.set(Calendar.YEAR,i);
					desDay = desDay+calendar.get(calendar.DAY_OF_YEAR);                		
				}
			}


			if(desDay > myDay) {
				destinationDay = desDay-myDay;        		

				day = givenCalendar.get(givenCalendar.DAY_OF_WEEK);
				String whichDay = calculateDay(day);
				String details = year+"-"+convertTwoDigit(month)+"-"+convertTwoDigit(date);


				speak(destinationDay+" days from now and "+desWeek+" week");
				speak("the day is "+whichDay);
				speak("press FG for Calculate the date");
				
				isContinue = true;

				//					Intent startIntent = new Intent("com.mobiwhiz.CALENDAR_SPEAK");
				//					startIntent.putExtra("CALENDAR",destinationDay+" days from now and "+desWeek+" week");
				//					v.getContext().startService(startIntent);

				//				Intent startIntent1 = new Intent("com.mobiwhiz.CALENDAR_SPEAK");
				//				startIntent1.putExtra("CALENDAR","the day is "+whichDay);
				//				v.getContext().startService(startIntent1);

				/*if(anniversaryDetails.size() != 0)
		{
			for(int i=0;i<anniversaryDetails.size();i++)
			{
				speak("Anniversary on "+anniversaryDetails.get(i),TextToSpeech.QUEUE_ADD,null);

//				Intent startIntent11 = new Intent("com.mobiwhiz.CALENDAR_SPEAK");
//				startIntent11.putExtra("CALENDAR","Anniversary on "+anniversaryDetails.get(i));
//				v.getContext().startService(startIntent11);
				//Toast.makeText(this,"Anniversary on "+anniversaryDetails.get(i),Toast.LENGTH_LONG).show();
			}
		}
		CheckAppointmentDetails appdetails = new CheckAppointmentDetails();
		appointmentDetails = new ArrayList<String>();
		appointmentDetails = appdetails.checkDetails(date,month,year,CalanderYear.this);
		CheckToDoListDetails chDetails = new CheckToDoListDetails();
		toDoListDetails = new ArrayList<String>();
		toDoListDetails = chDetails.checkDetails(details,CalanderYear.this);
		//Toast.makeText(v.getContext(),""+toDoListDetails.size(),Toast.LENGTH_LONG).show();
		if(appointmentDetails.size() != 0)
		{
			for(int i=0;i<appointmentDetails.size();i++)
			{
				speak("Appointment on "+appointmentDetails.get(i),TextToSpeech.QUEUE_ADD,null);

//				Intent startIntent11 = new Intent("com.mobiwhiz.CALENDAR_SPEAK");
//				startIntent11.putExtra("CALENDAR","Appointment on "+appointmentDetails.get(i));
//				v.getContext().startService(startIntent11);
			//	Toast.makeText(this,"Appointment on "+appointmentDetails.get(i),Toast.LENGTH_LONG).show();
			}
		}
		if(toDoListDetails.size() != 0)
		{
			for(int i=0;i<toDoListDetails.size();i++)
			{
				speak("To Do List on "+toDoListDetails.get(i), TextToSpeech.QUEUE_ADD, null);
//				Intent startIntent11 = new Intent("com.mobiwhiz.CALENDAR_SPEAK");
//				startIntent11.putExtra("CALENDAR","To Do List on "+toDoListDetails.get(i));
//				v.getContext().startService(startIntent11);
			//	Toast.makeText(this,"To Do List on "+toDoListDetails.get(i),Toast.LENGTH_LONG).show();
			}
		}*/
			}


			//			if(desDay < myDay) {
			//				destinationDay = myDay-desDay;        		
			//
			//				day = givenCalendar.get(givenCalendar.DAY_OF_WEEK);
			//				String whichDay = calculateDay(day);
			//				//	Toast.makeText(this, ""+desWeek, Toast.LENGTH_LONG).show();
			//
			//				speak(destinationDay+" days before from now and"+desWeek+" week");
			//				speak("the day is "+whichDay);
			//
			//				//		    			Intent startIntent = new Intent("com.mobiwhiz.CALENDAR_SPEAK");
			//				//						startIntent.putExtra("CALENDAR",destinationDay+" days before from now and"+desWeek+" week");
			//				//						v.getContext().startService(startIntent);
			//
			//				//		    		Intent startIntent1 = new Intent("com.mobiwhiz.CALENDAR_SPEAK");
			//				//					startIntent1.putExtra("CALENDAR","the day is "+whichDay);
			//				//					v.getContext().startService(startIntent1);
			//			}
			//			else if( desDay == myDay) {
			//				day = givenCalendar.get(givenCalendar.DAY_OF_WEEK);
			//				String whichDay = calculateDay(day);
			//
			//				speak("today  date is " +Caldate+ " " +Calmonth+ " "+ year);
			//				speak("the day is "+whichDay);
			//			}
			//		} else {
			//			for(int i=myCalendar.get(Calendar.YEAR)-1;i >= Integer.parseInt(year);i--) {
			//				if(i == Integer.parseInt(year)) {
			//					givenCalendar.set(Calendar.DATE,Integer.parseInt(date));
			//					givenCalendar.set(Calendar.MONTH,months);
			//					givenCalendar.set(Calendar.YEAR,Integer.parseInt(year));
			//					desDay = givenCalendar.get(givenCalendar.DAY_OF_YEAR);
			//					desWeek = givenCalendar.get(givenCalendar.WEEK_OF_YEAR);
			//					day = givenCalendar.get(givenCalendar.DAY_OF_WEEK);
			//
			//					calendar.set(Calendar.DATE,31);
			//					calendar.set(Calendar.MONTH,11);        	
			//					calendar.set(Calendar.YEAR,i);
			//					lessDay = calendar.get(calendar.DAY_OF_YEAR);
			//					desDay = lessDay-desDay;
			//					destinationDay = desDay+myDay+destinationDay;
			//				} else {
			//					calendar.set(Calendar.DATE,31);
			//					calendar.set(Calendar.MONTH,11);        	
			//					calendar.set(Calendar.YEAR,i);
			//					destinationDay = calendar.get(calendar.DAY_OF_YEAR);
			//				}
			//			}
			//			String whichDay = calculateDay(day);
			//			//	Toast.makeText(this, ""+desWeek, Toast.LENGTH_LONG).show();
			//
			//			speak(destinationDay+" days before from now and "+desWeek+" week");
			//			speak("the day is "+whichDay);
			//			

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

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}
	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}
}
