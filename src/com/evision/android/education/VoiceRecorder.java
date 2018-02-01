package com.evision.android.education;

import java.io.File;
import java.io.IOException;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.BaseActivity;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;

public class VoiceRecorder extends BaseActivity {

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
	Button resetButton = null;

	private static final String APP_TAG = "com.datapadsystem";

	private MediaRecorder recorder = null;
	private File outfile = null;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;
	// private boolean recordPlayMode = true;
	// private boolean voiceOutMode = true;
	//	public static final String INITIAL_STATE = "InitialState";
	//	public static final String RECORD_STARTED = "RecordingStarted";
	//	public static final String RECORD_STOPPED = "RecordingStopped";
	//	public static final String PLAY_STARTED = "PlayStarted";
	//	public static String vrApplicationState = "InitialState";

	boolean isFgKeypress = false;
	boolean isHgKeypress = false;
	boolean isGFKeypress = false;
	boolean b1 = false, b2 = false, b3 = false;
	Intent launchApp = new Intent();
	File storageDir;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standbymode_screen);
		// intent used of TTS

		ttsVoiceRecordMenu();

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

		storageDir = new File(Environment.getExternalStorageDirectory(),
				BrailleCommonUtil.APP_NAME_VR);
		storageDir.mkdir();

		timer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				ttsVoiceRecordMenu();
			}
		};
		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if ((isHgKeypress == true) || (isFgKeypress == true) || (isGFKeypress == true)) {
					isHgKeypress = false;
					isFgKeypress = false;
					isGFKeypress = false;
					lookuptable();
				}
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					num0Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsVoiceRecordMenu();
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}

				return true;
			}
		});

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event)
			{
				if (ACTION_DOWN == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num1Button.setBackgroundResource(R.drawable.btn_normal);
					stop();
					speak("1");

					speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceDiary_record));
					waitForShortSpeechFinished();
					if(recorder == null) {
						startRecord();
					}


				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("2");
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					if(recorder != null) {
						stopRecord();
						speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_stop));
					} else {
						speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_start));
					}




				}
				return true;
			}
		});

		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num3Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					stop();
					speak("3");
					speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.not_assign));
					num3Button.setBackgroundResource(R.drawable.btn_normal);

				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					stop();
					speak("4");
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// ttsVoiceRecordMenu();
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.not_assign));
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					num5Button.setBackgroundResource(R.drawable.btn_green);
					stop();
					speak("5");
				} else if (ACTION_UP == event.getAction()) {
					// ttsVoiceRecordMenu();
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.not_assign));
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("6");
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.not_assign));
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
					speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.not_assign));
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
					speak("8");
				} else if (ACTION_UP == event.getAction()) {

					speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.not_assign));
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
					speak("9");
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.not_assign));
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
					speak("F");
					b1 = setBoolean();
					keyPressTimeout.cancel();
				} else if (ACTION_UP == event.getAction()) {
					isFgKeypress = true;
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

		numGButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					stop();
					speak("G");
					b2 = setBoolean();
					isGFKeypress = true;
					keyPressTimeout.cancel();
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					if (isFgKeypress == true) {
						// FG for Save the file
						isGFKeypress = false;
						speak("recording " + outfile.getName() + " Saved");
						waitForShortSpeechFinished();
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.VOICERECORDMAINMENU);
						startActivity(launchApp);
						finish();
					} 
					keyPressTimeout.start();
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					if (isHgKeypress == true) {
						stopRecord();
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE, PackageUtil.VOICERECORDMAINMENU);
						startActivity(launchApp);
						finish();
						isHgKeypress = false;
					}
				}

				return true;
			}
		});

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					stop();
					b3 = setBoolean();
					speak("H");
					isHgKeypress = true;
					keyPressTimeout.cancel();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// ttsVoiceRecordMenu();
					lookuptable();
					keyPressTimeout.start();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("Star");
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.not_assign));
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
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.not_assign));
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
	}


	public void onInit(int status) {

	}

	private void startRecord() {
		// Start recording for first click , If user pressed again
		// then no need to start recording once again
		if (recorder == null) {

			try {

				waitForShortSpeechFinished();
				outfile = File.createTempFile("voice_record", ".3gp",
						storageDir);

				recorder = new MediaRecorder();

				recorder.reset();
				recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				recorder.setOutputFile(outfile.getAbsolutePath());
				// mp.setDataSource(outfile.getAbsolutePath());
				recorder.prepare();
				recorder.start();

			} catch (IOException e) {
				Log.w(APP_TAG, "File not accessible ", e);
			} catch (IllegalArgumentException e) {
				Log.w(APP_TAG, "Illegal argument ", e);
			} catch (IllegalStateException e) {
				Log.w(APP_TAG, "Illegal state, call reset/restore", e);
			}
		} else {
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceDiary_record));
		}
	}

	private void stopRecord() {
		if (recorder != null) {
			recorder.stop();
			recorder.reset();
			recorder.release();
			recorder = null;
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (timer != null)
			timer.cancel();
		if (mTts != null)
			mTts.stop();

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

	@Override
	public void onStop() {
		super.onStop();
		finish();
	}

	@Override
	public void onResume() {

		if (timer != null)
			timer.start();
		super.onResume();
	}
	public void ttsInvalidChoice() {
		// timer.cancel();
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}

	public void ttsVoiceRecordMenu() {
		speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_welcome));
		speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_1));
		speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_2));
		speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_fg));
		speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.previous_hg));

	}

}
