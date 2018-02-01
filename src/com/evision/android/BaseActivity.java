package com.evision.android;

import java.util.Locale;

import com.evision.android.util.PackageUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;

/**
 * 
 * @author selvakumar
 *
 */
public class BaseActivity extends Activity implements OnInitListener, OnUtteranceCompletedListener {

	protected TextToSpeech mTts;
	Intent launchApp = new Intent();
	private static final int REQ_TTS_STATUS_CHECK = 0;
	private static final int TTS_SPEECH_MAX_WAIT_TIME = 5 * 1000;
	private static final int MEDIUM_THREADWAIT = 500;
	private static final int SHORT_THREADWAIT = 100;


	public BaseActivity() {
		super();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent checkIntent = 	new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	protected void speak(String text) {
		if (mTts != null) {
			mTts.speak(text, TextToSpeech.QUEUE_ADD, null); }
	}

	public boolean isSpeaking(){
		if (mTts != null) {
			return mTts.isSpeaking();
		} else {
			return false;
		}

	}

	public boolean waitForComplete() throws InterruptedException {
		if(isSpeaking()) {
			wait(TTS_SPEECH_MAX_WAIT_TIME);
			return false;
		} else {
			return true;
		}
	}

	public void stop() {
		if (mTts != null) {
			mTts.stop();
		}
	}

	@Override
	public void onInit(int status) {
		// Now that the TTS engine is ready, we enable the button
		if (status == TextToSpeech.SUCCESS) {

			Locale lang = getCurrentLocale();
			int result = mTts.setLanguage(lang);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				// Lanuage data is missing or the language is not supported.
			} 
		}
	}

	/**
	 * @return
	 */
	private static Locale getCurrentLocale() {
		Locale lang = Locale.ENGLISH;
		return lang;
	}

	@Override
	public void onPause() {
		// TODO: save any changes made to data (the app may be killed)
		super.onPause();
		// if we're losing focus, stop talking
		if (mTts != null)
			mTts.stop();
	}

	@Override
	public void onDestroy() {
		if (mTts != null)
			mTts.shutdown();
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQ_TTS_STATUS_CHECK) {
			switch (resultCode) {
			case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS:
				// TTS is up and running
				mTts = new TextToSpeech(this, this);
				break;
			case TextToSpeech.Engine.CHECK_VOICE_DATA_BAD_DATA:
			case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:
			case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_VOLUME:
				// missing data, install it
				// TODO: handle multilanguage
				Intent installIntent = new Intent();
				installIntent
				.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
				break;
			case TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:
			}
		}
	}

	@Override
	public void onUtteranceCompleted(String uttId) {
		Log.v("AndroidTextToSpeechActivity", uttId);
		if (uttId.equalsIgnoreCase("done")) {
			Log.v("BaseActivity", "Done talking, end of message");
		} 
	}

	@Override
	public void onBackPressed() {
	}

	public void goActiveMode() {
		launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.ACTIVEMAINMENU);
		startActivity(launchApp);
		finish();
	}

	public  void waitForSpeechFinished() {
		try {
			Thread.sleep(MEDIUM_THREADWAIT);
		} catch (InterruptedException e) { }
		while (isSpeaking()) {
			try {
				Thread.sleep(MEDIUM_THREADWAIT);
			} catch (InterruptedException e) { }
		}
		try {
			Thread.sleep(MEDIUM_THREADWAIT);
		} catch (InterruptedException e) { }
	}

	public void waitForShortSpeechFinished() {
		try {
			Thread.sleep(SHORT_THREADWAIT);
		} catch (InterruptedException e) { }
		while (isSpeaking()) {
			try {
				Thread.sleep(SHORT_THREADWAIT);
			} catch (InterruptedException e) { }
		}
		try {
			Thread.sleep(SHORT_THREADWAIT);
		} catch (InterruptedException e) { }
	}
}
