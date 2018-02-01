package com.evision.android.util;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

public class GetContactDetailsTask extends AsyncTask<String, Void, ArrayList<ContactDetails>>{

	private Context mContext;
	private ContactDetails contact;
	private ArrayList<ContactDetails> contactDetails = new ArrayList<ContactDetails>();

	public GetContactDetailsTask(Context context) {
		mContext = context;
		contact = new ContactDetails();
	}
	@Override
	protected ArrayList<ContactDetails> doInBackground(String... params) {
		
		contactDetails.clear();
		String id, name, phone;
		String[] projection = new String[] {
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.HAS_PHONE_NUMBER,
				ContactsContract.Contacts._ID
		};
		ContentResolver cr = mContext.getContentResolver();  

		Cursor cur = mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projection, ContactsContract.Contacts.HAS_PHONE_NUMBER+"=?",
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
					while (pCur.moveToNext() && pCur.getCount() > 0) {  

						name = cur .getString(cur .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));     

						phone = pCur.getString(pCur  
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));

						contact.setContactID(id);
						contact.setContactName(name);
						contact.setContactNumber(phone);
						contactDetails.add(contact);
					} 
					pCur.close();  
				}  
			}  
			cur.close();
		}

		return contactDetails;
	}
}
