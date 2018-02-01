package com.evision.android;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.*;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class EmergencyDialing extends BaseActivity  {


	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	// public final String MEDIA_PATH = new String("/mnt/sdcard/ebook");
	public List<String> contactNameFullList;
	public List<String> contactNumberFullList;
	public static List<String> contactNameSubList;
	public static List<String> contactNumberSubList;

	SharedPreferences getPref;
	SharedPreferences.Editor editor;
	public static final String PREFS_NAME ="EvisionAssignGroup";
	String assignValue = "";
	String phoneName = "";
	String phoneNumber = "";
	String emergency = "emergency";
	String speed = "speed";
	int fileIndex = 0;
	int	emergencyCheck,speedCheck;
	final Intent launchApp = new Intent();

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

	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;

	private String TAG = "EBookList";
	CountDownTimer doubleClickTimer = null;
	// private boolean doubleClick = false;
	private boolean scrollDown = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isFGFGKeypress = false;
	boolean isGFKeyPressed = false;
	boolean isACKeypress = false;
	private boolean scrollUp = false;
	boolean b1 = false, b2 = false, b3 = false;
	String phone;
	String name;
	String number;
	//	String selectedfile;
	String id;
	String num="";
	public List<String> contactsName = new ArrayList<String>();	
	public List<String> contactsNumber = new ArrayList<String>();	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);

		PopulateContacts getContact = new PopulateContacts();
		getContact.execute();
		// *Initially Set the Emergency , Speed Dial Array Value *
		getPref = getSharedPreferences(PREFS_NAME,0);
		editor = getPref.edit();
		emergencyCheck = getPref.getInt("emergency_value", 0);
		speedCheck = getPref.getInt("speed_value", 0);

		if(emergencyCheck == 0) {
			editor.putInt("emergency_value", 1);
			editor.commit();
			emergencyCheck = getPref.getInt("emergency_value", 0);
		} 
		if(speedCheck == 0) {
			editor.putInt("speed_value", 1);
			editor.commit();
			speedCheck = getPref.getInt("speed_value", 0);
		} 
		// Get List of files based on extn local copy is maintained here
		contactNameFullList = contactsName;
		contactNumberFullList = contactsNumber;
		// Initially Full is same as Sublist
		contactNameSubList = contactNameFullList;
		contactNumberSubList = contactNumberFullList;

		/**
		 * 
		 * To get Emergency or Speed Vallue
		 * 
		 */

		assignValue = this.getIntent().getStringExtra("assignValue");
		Log.i(TAG, "Get value = " + assignValue);

		//		Intent intent = getIntent();
		//		msgtwo = intent.getStringExtra("msgbodymaintwo");
		//		Log.i("manju","msgbodyjin=" +msgtwo);	

		// Initialize Lookup table variable
		ResetBrailleKeys();

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
			}

			@Override
			public void onFinish() {
				ttsForEbookListMenu();
			}
		}.start();

		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (isFgKeypress == true || isHgKeypress == true || isACKeypress == true || isACKeypress == true 
						|| isGFKeyPressed == true || scrollUp == true || scrollDown == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
					isACKeypress = false;
					isGFKeyPressed = false;
					lookuptable();
				} else {
					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					ResetBrailleKeys();
					if (str != null) {
						contactNameSubList = BrailleCommonUtil.getFilterFilesByNameString(contactsName, str);
						contactNumberSubList = BrailleCommonUtil.getFilterFilesByNumberString(contactsNumber, str);
						if (FilesExist() == true)
							speak(contactNameSubList.get(0));
					}

				}
			}
		};

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					keyPressTimeout.cancel();
					stop();
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
					keyPressTimeout.cancel();
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					stop();
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
					stop();
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
					stop();
					BrailleCommonUtil.SetBrailleKeyflag(6);
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		// final Intent IntFilenameSend = new Intent(this, AppClassName);
		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					keyPressTimeout.cancel();
					isHgKeypress = true;
					isGFKeyPressed = false;
					b3 = setBoolean();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("H");
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
					stop();
					speak("G");
					keyPressTimeout.cancel();
					isGFKeyPressed = true;
					b2 = setBoolean();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// Select file to launch
					if(isFgKeypress == true) {
						if ((FilesExist() == true)) {

							if((assignValue!=null) && (assignValue.equals(emergency))) {
								if ((emergencyCheck > 0) && (emergencyCheck < 5)) {
									phoneName = contactNameSubList.get(fileIndex);
									phoneNumber=contactNumberSubList.get(fileIndex);
									editor.putString("emergencyName_" + emergencyCheck, phoneName);
									editor.putString("emergencyNumber_" +emergencyCheck, phoneNumber);
									editor.putInt("emergency_value", emergencyCheck + 1 );
									editor.commit();
									speak(phoneName + " as assigned for emergency message");
									isFgKeypress = false;
									waitForSpeechFinished();
									launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
									launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.ACTIVEMAINMENU); 
									startActivity(launchApp);
									finish();
								} else if (isFGFGKeypress == true) {
									isFGFGKeypress = false;
									phoneName = contactNameSubList.get(fileIndex);
									phoneNumber=contactNumberSubList.get(fileIndex);
									launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
									launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.EMERGENCYREPLACE);
									launchApp.putExtra("phoneNumber", phoneNumber);
									launchApp.putExtra("phoneName", phoneName);
									startActivity(launchApp);
									finish();

								} else {
									speak(" Only 4 number can be assign Emergency Contact.");
									speak("If want Replaced the Emergency Contact Name Press FG To Continue");
								}

							} else {

								if ((speedCheck > 0) && (speedCheck < 10)) {
									phoneName = contactNameSubList.get(fileIndex);
									phoneNumber=contactNumberSubList.get(fileIndex);
									editor.putString("speedName_" + speedCheck, phoneName);
									editor.putString("speedNumber_" +speedCheck, phoneNumber);
									editor.putInt("speed_value", speedCheck + 1 );
									editor.commit();
									speak("speed dialling number ,"+ speedCheck +" assigned  to  "+ phoneName);
									waitForSpeechFinished();
									isFgKeypress = false;
									launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
									launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.ACTIVEMAINMENU); 
									startActivity(launchApp);
									finish();
								} else if (isFGFGKeypress == true) {
									isFGFGKeypress = false;
									phoneName = contactNameSubList.get(fileIndex);
									phoneNumber=contactNumberSubList.get(fileIndex);
									launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
									launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.SPEEDDIALLINGREPLACE);
									launchApp.putExtra("phoneNumber", phoneNumber);
									launchApp.putExtra("phoneName", phoneName);
									startActivity(launchApp);
									finish();
								} else {
									speak(" Only 9 number can be assign Emergency Contact.");
									speak("If want Replaced the Speed Dialing  Contact Nmae Press FG To Continue");
								}
							}
						}

					}

					if (isHgKeypress == true) {

						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.ASSIGNGROUP); 
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

		// final Intent intActiveMenu = new Intent(this,
		// com.datapadsystem.ActiveMainMenu.class);
		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {

					stop();
					keyPressTimeout.cancel();
					isFgKeypress = true;
					b1 = setBoolean();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("F");
					if(isGFKeyPressed == true) {
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

		numAButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("A");
					keyPressTimeout.cancel();
					isACKeypress = true;
					numAButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (scrollUp == true) {
						if (FilesExist() == true) {
							scrollUp = false;
							if (fileIndex > 0)
								fileIndex--;
							else
								fileIndex = contactNameSubList.size() - 1;
							//Log.i(TAG, "EBook Up FileIndex= " + fileIndex);
							stop();
							speak(contactNameSubList.get(fileIndex));
						}
					} 
					numAButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();

				}
				return true;
			}
		});

		numCButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("C");
					keyPressTimeout.cancel();
					numCButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if(scrollDown == true) {
						if (FilesExist() == true) {
							if ((contactNameSubList.size() - 1) > fileIndex)
								fileIndex++;
							else
								fileIndex = 0;
							Log.i(TAG, "EBook Down FileIndex= " + fileIndex);
							stop();
							speak(contactNameSubList.get(fileIndex));
						} 
					}
					isACKeypress = true;
					keyPressTimeout.start();
					numCButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numDButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					numDButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("D");
					contactNameSubList.clear();
					Log.i("Contcat Name list : ", "Clear");
					PopulateContacts pop = new PopulateContacts();
					pop.execute();

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
					scrollUp = true;
					scrollDown = true;
					keyPressTimeout.cancel();
					numBButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
					numBButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					numE1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("e 1 ");

					// ttsForEbookListMenu();
					/*String str = BrailleCommonUtil.AlpabeticalLookUpTable(tts);
					ResetBrailleKeys();
					if (str != null) {
						ContactNameSubList = getFilterFilesBystring(ContactNameFullList, str);
					}*/

					numE1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numE2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					numE2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("e 2 ");

					// ttsForEbookListMenu();
					/*String str = BrailleCommonUtil.AlpabeticalLookUpTable(tts);
					ResetBrailleKeys();
					if (str != null) {
						ContactNameSubList = getFilterFilesBystring(ContactNameFullList, str);
					}*/
					numE2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

	}


	@Override
	public void onPause() {
		Log.i(TAG, "Active Mode onPause");

		if (timer != null)
			timer.cancel();
		/*
		 * if (startUpTimer!=null) startUpTimer.cancel();
		 */if (doubleClickTimer != null)
			 doubleClickTimer.cancel();

		 if (mTts != null)
			 stop();
		 super.onPause();
	}

	@Override
	public void onStop() {
		Log.i(TAG, "Active Mode OnStop");
		super.onStop();
	}

	@Override
	public void onResume() {
		Log.i(TAG, "Active Mode onResume");
		//		PopulateContacts getContact = new PopulateContacts();
		//		getContact.execute();
		if (timer != null)
			timer.start();
		/*
		 * if (startUpTimer!=null) startUpTimer.start();
		 */if (doubleClickTimer != null)
			 doubleClickTimer.start();
		 super.onResume();
	}

	public boolean FilesExist() {
		boolean flag = true;
		Log.i("TAG", "No contacts present :" + contactNameSubList.size());
		if (contactNameSubList.size() == 0) {
			speak(ResourceUtil.getStringFromResource(EmergencyDialing.this,R.string.fmfriend_nocant));
			flag = false;
		}
		return flag;
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

	public void ResetBrailleKeys() 
	{
		BrailleCommonUtil.ResetBrailleKeyflag();
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}

	public void ttsForEbookListMenu() {
		stop();
		speak("Welcome to " + assignValue +"Dialing setup");
		speak(ResourceUtil.getStringFromResource(EmergencyDialing.this,R.string.phConatct_charater));
		speak(ResourceUtil.getStringFromResource(EmergencyDialing.this,R.string.scroll_up));
		speak(ResourceUtil.getStringFromResource(EmergencyDialing.this,R.string.scroll_down));
		speak(ResourceUtil.getStringFromResource(EmergencyDialing.this,R.string.Emergency_FG));
	}


	public static final List<String> getFilterFilesByNumberstring(
			List<String> fileNameList, String str) {

		//ContactNumberSubList.clear();
		int size = fileNameList.size();

		String tempStr = null;
		char tmpStr = 0;

		if (str.length() != 0) {
			tmpStr = str.charAt(0);	
		}

		for (int i = 0; size > i; i++) {
			tempStr = fileNameList.get(i).toLowerCase();
			char chr = tempStr.charAt(0);
			// Compare the first letter of filename and make separate sublist
			if (chr == tmpStr) {
				contactNumberSubList.add(tempStr);
			}
		}
		return contactNumberSubList;
	}


	private class PopulateContacts extends AsyncTask<Void, String, String>
	{  	
		@Override
		protected String doInBackground(Void... params) {

			try {
				String[] projection = new String[] {
						ContactsContract.Contacts.DISPLAY_NAME,
						ContactsContract.Contacts.HAS_PHONE_NUMBER,
						ContactsContract.Contacts._ID
				};

				ContentResolver cr = getContentResolver();  
				Cursor cur = managedQuery(ContactsContract.Contacts.CONTENT_URI, projection, ContactsContract.Contacts.HAS_PHONE_NUMBER+"=?",
						new String[]{"1"}, ContactsContract.Contacts.DISPLAY_NAME);

				if (cur.getCount() > 0) {  

					while (cur.moveToNext()) {  

						// ID AND NAME FROM CONTACTS CONTRACTS  
						id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));  

						// GET PHONE NUMBERS WITH QUERY STRING  
						if (Integer.parseInt(cur .getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {  

							Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID  
									+ " = ?", new String[] { id }, null);  

							// WHILE WE HAVE CURSOR GET THE PHONE NUMERS  
							while ((pCur.moveToNext()) && (pCur.getCount() > 0)) {  

								name = cur .getString(cur .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));     


								// Do something with phones  
								//             int phoneColumn = pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
								//            number =pCur.getString(phoneColumn);

								phone = pCur.getString(pCur  
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));  

								//  Contacts.add(name+" "+phone); //contacts with name and number
								// only name of the contact
								contactsName.add(name);
								contactsNumber.add(name + phone);
							} 
							if (pCur !=null) {
								pCur.close(); 
							}
						}  
					}
					if (cur !=null) {
						cur.close();
					}
				}
			} catch (Exception e) {
				Log.e("PhoneSearchContact", "Failed to get Contact");
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// Nothing to do
		}

		@Override
		protected void onPreExecute() {
			// Nothing to do
		}
		protected void onProgressUpdate(String... text) {
			// Nothing to do

		}
	}

}
