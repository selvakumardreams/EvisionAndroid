package com.evision.android.voicerecord;

import java.io.File;
import java.io.IOException;

import com.evision.android.BaseActivity;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.ResourceUtil;


import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

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
	private MediaPlayer mp = null;
	private String TAG = "VoiceRecorder";
	int pos = 0;
	int totalduration = 0;
	private File outfile = null;

	private int MY_DATA_CHECK_CODE = 0;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	public static int counter = 0;
	CountDownTimer timer = null;
	CountDownTimer keyPressTimeout = null;
	// private boolean recordPlayMode = true;
	// private boolean voiceOutMode = true;
	public static final String INITIAL_STATE = "InitialState";
	public static final String RECORD_STARTED = "RecordingStarted";
	public static final String RECORD_STOPPED = "RecordingStopped";
	public static final String PLAY_STARTED = "PlayStarted";
	public static String vrApplicationState = "InitialState";

	boolean isRecordedFilePresent = false;
	boolean isFgKeypress = false;
	boolean isHgKeypress = false;

	File storageDir;

	public static void setVrApplicationState(String vrApplicationState) {
		VoiceRecorder.vrApplicationState = vrApplicationState;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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

		storageDir = new File(Environment.getExternalStorageDirectory(),
				BrailleCommonUtil.APP_NAME_VR);
		storageDir.mkdir();

		timer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				counter = 0;
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
				if ((isHgKeypress == true) || (isFgKeypress == true)) {
					isHgKeypress = false;
					isFgKeypress = false;
				}
			}
		};

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					num0Button.setBackgroundResource(R.drawable.btn_green);
					ttsInvalidChoice();
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
					if (isSpeaking()) 
					{
						stop();
					}
					if (isRecordedFilePresent == false) {

						stop();
						speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceDiary_record));

						startRecord();
					} else {
						mpStart();
					}
				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num2Button.setBackgroundResource(R.drawable.btn_normal);
					if (isRecordedFilePresent == false)
					{
						if(mTts!=null)
							stop();
						stopRecord();
						speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_stop));
						isRecordedFilePresent = true;
						//	ttsVoiceRecordMenu();
					} else {
						mpPause();
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
					num3Button.setBackgroundResource(R.drawable.btn_normal);
					if (isRecordedFilePresent == true) {
						mpstop();
					} else {
						ttsInvalidChoice();
						ttsVoiceRecordMenu();
					}
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// ttsVoiceRecordMenu();
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					if (isRecordedFilePresent == true) {
						mpForward();
					} else {
						ttsInvalidChoice();
						ttsVoiceRecordMenu();
					}
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// ttsVoiceRecordMenu();
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					if (isRecordedFilePresent == true) {
						mpBackward();
					} else {
						ttsInvalidChoice();
						ttsVoiceRecordMenu();
					}
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					ttsInvalidChoice();
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsVoiceRecordMenu();
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					ttsInvalidChoice();
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsVoiceRecordMenu();
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
		final Intent intActive = new Intent(this,
				com.evision.android.ActiveMainMenu.class);
		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					waitForShortSpeechFinished();
					startActivity(intActive);
					finish();
					num8Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsVoiceRecordMenu();
					num9Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numFButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					numFButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					isFgKeypress = true;
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
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					if (isFgKeypress == true) {
						// FG for Save the file
						keyPressTimeout.cancel();
						speak("recording " + outfile.getName() + " Saved");
						isRecordedFilePresent = false;
					} else if (isHgKeypress == true) {
						// HG for discard the file
						keyPressTimeout.cancel();
						speak("recorded " + outfile.getName()
								+ " discarding");
						outfile.delete();
						isRecordedFilePresent = false;
						isHgKeypress = false;

					}
					if (timer != null)
						timer.start();
				}
				else if (isHgKeypress == true) {
					stopRecord();
					Intent launchApp = new Intent();
					launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);


					// launchApp.putExtra("TDLalarmhrs",reStr);
					launchApp.setClassName("com.evision.android.voicerecord", "com.evision.android.voicerecord.VoiceRecordMenu");
					waitForShortSpeechFinished();
					startActivity(launchApp);
					finish();
					isHgKeypress = false;
				}
				return true;
			}
		});

		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					// ttsInvalidChoice();
					numHButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// ttsVoiceRecordMenu();
					isHgKeypress = true;
					keyPressTimeout.start();
					numHButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numStarButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					ttsInvalidChoice();
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsVoiceRecordMenu();
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					ttsInvalidChoice();
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					ttsVoiceRecordMenu();
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});
	}


	private void startRecord() {
		// Start recording for first click , If user pressed again
		// then no need to start recording once again
		if (recorder == null) {

			try {

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

	// MediaPlayer API's for Start ,Pause stop forward & backward
	public void mpStart() {
		Log.i(TAG, "mpStart");

		if (mp != null) {
			mp.seekTo(pos);
			mp.start();
			pos = 0;
		} else {
			mp = new MediaPlayer();

			try {
				mp.setDataSource(outfile.getAbsolutePath());
				mp.prepare();
				mp.start();
				pos = 0;
				totalduration = mp.getDuration();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void mpstop() {
		Log.i(TAG, "mpstop");

		if (mp != null) {
			mp.stop();
			mp.release();
			mp = null;
			pos = 0;
		} else {
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.mediaPlay_stop));
		}

	}

	public void mpPause() {
		Log.i(TAG, "mpPause");

		if (mp != null) {
			pos = mp.getCurrentPosition();
			mp.pause();
		} else {
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.mediaPlay_pause));
		}

	}

	public void mpForward() {
		Log.i(TAG, "mpForward");
		if (mp != null) {
			pos = mp.getCurrentPosition();
			// Jumping by 2 sec;
			pos += 2000;
			if (totalduration >= pos) {
				mp.seekTo(pos);
			} else {
				mp.seekTo(totalduration);
			}
			mp.start();
		} else {
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.mediaPlay_forward));
		}
	}

	public void mpBackward() {
		Log.i(TAG, "mpBackward");
		if (mp != null) {
			pos = mp.getCurrentPosition();
			// Jumping by 2 sec;
			pos -= 2000;
			if (pos >= 0) {
				mp.seekTo(pos);
			} else {
				mp.seekTo(0);
			}
			mp.start();
		} else {
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.mediaPlay_backward));
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (timer != null)
			timer.cancel();
		if (mTts != null)
			stop();
		if (vrApplicationState.equals(RECORD_STOPPED)
				|| vrApplicationState.equals(PLAY_STARTED)) {
			setVrApplicationState(INITIAL_STATE);
			Log.d("ONPause:", "On Pause called finish() method.");
			finish();
		}
	}

	@Override
	public void onStop() {
		Log.i(APP_TAG, "EBookRead OnStop");
		super.onStop();
		Log.d("ONSTOP:", "On Stop called finish() method.");
		finish();
	}

	@Override
	public void onResume() {
		Log.i(APP_TAG, "EBookRead onResume");

		if (timer != null)
			timer.start();
		super.onResume();
	}

	public void ttsInvalidChoice() 
	{
		BrailleCommonUtil.ttsInvalidChoice(mTts);
	}

	public void ttsVoiceRecordMenu() {

		// stop();
		speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.please));

		if (isRecordedFilePresent == false) {
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_1));
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_2));
		} else {
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_one));
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_two));
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_three));
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_four));
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_five));
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_fg));
			speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_fg));
		}
		speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.voiceRecord_eight));
		speak(ResourceUtil.getStringFromResource(VoiceRecorder.this,R.string.repeat_nine));

		/*
		 * speak("Please select", TextToSpeech.QUEUE_ADD, null); if
		 * (vrApplicationState.equals(INITIAL_STATE)) {
		 * speak("one for start recording", TextToSpeech.QUEUE_ADD, null);
		 * speak("two for stop recording", TextToSpeech.QUEUE_ADD, null); }
		 * else if (vrApplicationState.equals(RECORD_STARTED)) { stopRecord();
		 * outfile.delete(); setVrApplicationState(INITIAL_STATE);
		 * speak("one for start recording", TextToSpeech.QUEUE_ADD, null);
		 * speak("two for stop recording", TextToSpeech.QUEUE_ADD, null); }
		 * else if (vrApplicationState.equals(RECORD_STOPPED)) {
		 * speak("one for play recorded audio", TextToSpeech.QUEUE_ADD,
		 * null); speak("two for save recorded audio",
		 * TextToSpeech.QUEUE_ADD, null);
		 * speak("three for discard recorded audio", TextToSpeech.QUEUE_ADD,
		 * null); } else if (vrApplicationState.equals(PLAY_STARTED)) {
		 * stopPlay(); setVrApplicationState(RECORD_STOPPED);
		 * speak("one for play recorded audio", TextToSpeech.QUEUE_ADD,
		 * null); speak("two for save recorded audio",
		 * TextToSpeech.QUEUE_ADD, null);
		 * speak("three for  discard recorded audio",
		 * TextToSpeech.QUEUE_ADD, null); }
		 */
	}
}
