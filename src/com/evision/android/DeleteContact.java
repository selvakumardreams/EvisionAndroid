package com.evision.android;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

/**
 * Phone Search Contact
 * @author selvakumar
 *
 */
public class DeleteContact extends BaseActivity  {


	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	// public final String MEDIA_PATH = new String("/mnt/sdcard/ebook");
	public List<String> contactNameFullList;
	public List<String> contactNumberFullList;
	public static List<String> contactNameSubList;
	public static List<String> contactNumberSubList;
	public static List<String> contactId = new ArrayList<String>();

	String phonenameNum="";
	int fileIndex = 0;
	Intent launchApp = new Intent();

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

	CountDownTimer doubleClickTimer = null;
	// private boolean doubleClick = false;
	private boolean scrollDown = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeyPressed = false;
	boolean isConform = false; 
	boolean isACkeypress = false;
	boolean b1 = false, b2 = false, b3 = false;
	private boolean scrollUp = false;

	String phone;
	String name;
	String number;
	Uri parsedPhoneNumber;
	String str = "";
	String id;
	public List<String> contactNames = new ArrayList<String>();	
	public List<String> contactsNumber = new ArrayList<String>();	
	public static List<String> getFilterFiles = new ArrayList<String>();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braille_screen);

		final PopulateContacts getContact = new PopulateContacts();
		getContact.execute();

		// Get List of files based on extn local copy is maintained here
		contactNameFullList = contactNames;
		contactNumberFullList = contactsNumber;
		// Initially Full is same as Sublist
		contactNameSubList = contactNameFullList;
		contactNumberSubList = contactNumberFullList;

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
				if (isFgKeypress == true || isHgKeypress == true || isACkeypress == true || 
						isGFKeyPressed == true || scrollUp == true || scrollDown == true) {
					isFgKeypress = false;
					isHgKeypress = false;
					scrollUp = false;
					scrollDown = false;
					isACkeypress = false;
					isGFKeyPressed = false;
					lookuptable();
				} else {
					//					String str = BrailleCommonUtil.AlpabeticalLookUpTable(mTts);
					//					ResetBrailleKeys();
					//					if (str != "") {
					//						contactNameSubList = BrailleCommonUtil.getFilterFilesByNameString(contactNames, str);
					//						contactNumberSubList = BrailleCommonUtil.getFilterFilesByNumberString(contactsNumber, str);
					//					}
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
					stop();
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
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					keyPressTimeout.start();
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
					isGFKeyPressed = true;
					keyPressTimeout.cancel();
					b2 = setBoolean();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if ((isFgKeypress == true)) {
						if (FilesExist() == true) {
							stop();
							if(isConform == true) {
								isConform = false;
								deleteContact(contactId.get(fileIndex));
							} else {
								speak(contactNameSubList.get(fileIndex) + "is Select for Delete. press F G to Conform");
								isConform = true;
							}

						} 
					}
					if (isHgKeypress == true) {

						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.CREATEPHONEBOOK); 
						startActivity(launchApp);
						finish();
						isHgKeypress = false;
					}
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					keyPressTimeout.start();
					return true;
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
					b1= setBoolean();
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak("F");
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

		numAButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("A");
					numAButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if (scrollUp == true) {
						if (FilesExist() == true) {
							if (fileIndex > 0)
								fileIndex--;
							else
								fileIndex = contactNameSubList.size() - 1;
							//Log.i(TAG, "EBook Up FileIndex= " + fileIndex);
							stop();
							speak(contactNameSubList.get(fileIndex));
						} else {
							stop();
							speak(ResourceUtil.getStringFromResource(DeleteContact.this,R.string.fmfriend_nocant));
						}
					}
					isACkeypress = true;
					keyPressTimeout.start();
					numAButton.setBackgroundResource(R.drawable.btn_normal);

				}
				return true;
			}
		});

		numCButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("C");
					numCButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					if  (scrollDown == true) {

						if (FilesExist() == true) {

							if ((contactNameSubList.size() - 1) > fileIndex)
								fileIndex++;
							else
								fileIndex = 0;
							stop();
							speak(contactNameSubList.get(fileIndex));
						} else {
							stop();
							speak(ResourceUtil.getStringFromResource(DeleteContact.this,R.string.fmfriend_nocant));
						}
					}
					isACkeypress = true;
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
					contactNameFullList.clear();
					contactNumberSubList.clear();
					contactsNumber.clear();
					contactNames.clear();
					contactNumberFullList.clear();
					PopulateContacts pop = new PopulateContacts();
					pop.execute();
					// Get List of files based on extn local copy is maintained here
					contactNameFullList = contactNames;
					contactNumberFullList = contactsNumber;
					// Initially Full is same as Sublist
					contactNameSubList = contactNameFullList;
					contactNumberSubList = contactNumberFullList;

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
					isConform = false;
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
		super.onStop();
	}

	@Override
	public void onResume() {
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
			stop();
			speak(ResourceUtil.getStringFromResource(DeleteContact.this,R.string.fmfriend_nocant));
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


	public void ResetBrailleKeys() {
		BrailleCommonUtil.ResetBrailleKeyflag();
	}

	public void deleteContact(String deleteId) {
		ContentResolver cr = getContentResolver();
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
				null, ContactsContract.Contacts._ID + "=" + deleteId, null, null);
		while (cur.moveToNext()) {
			try {
				String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
				Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
				cr.delete(uri, ContactsContract.Contacts._ID + "=" + id, null);
			}
			catch(Exception e)
			{
				System.out.println(e.getStackTrace());
			}
		}
		speak("Contact  Deleted");
		contactNameSubList.remove(fileIndex);
		contactId.remove(fileIndex);
		isConform = false;
	}

	public void ttsInvalidChoice() {
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}

	public void ttsForEbookListMenu() {
		stop();
		speak(ResourceUtil.getStringFromResource(DeleteContact.this,R.string.DeleteContact_welcome));
		speak(ResourceUtil.getStringFromResource(DeleteContact.this,R.string.scroll_up));
		speak(ResourceUtil.getStringFromResource(DeleteContact.this,R.string.scroll_down));
		speak(ResourceUtil.getStringFromResource(DeleteContact.this,R.string.DeleteContact_select));
		speak(ResourceUtil.getStringFromResource(DeleteContact.this,R.string.previous_hg));
	}

	private class PopulateContacts extends AsyncTask<Void, String, String> {  	
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub

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
								phone = pCur.getString(pCur  
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));  

								//  Contacts.add(name+" "+phone); //contacts with name and number
								// only name of the contact
								contactId.add(id);
								contactNames.add(name);
								contactsNumber.add(name + phone);
							} 
							if (pCur != null)
								pCur.close();
						}  
					} 
					if (cur != null)
						cur.close();
				} 
			} catch (Exception e) {
				Log.i("DeleteContact", "Error");
			}

			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// execution of result of Long time consuming operation

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			// Things to be done before execution of long running operation. For
			// example showing ProgessDialog
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
		 */

		protected void onProgressUpdate(String... text) {

			// Things to be done while execution of long running operation is in
			// progress. For example updating ProgessDialog

		}
	}

}
