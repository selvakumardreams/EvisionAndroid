package com.evision.android.education;

import java.io.IOException;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.evision.android.BaseActivity;
import com.evision.android.R;
import com.evision.android.util.BrailleCommonUtil;
import com.evision.android.util.PackageUtil;
import com.evision.android.util.ResourceUtil;
import android.media.MediaPlayer;

public class MediaFilePlayer extends BaseActivity {

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
	boolean b1 = false, b2 = false, b3 = false;
	boolean isHgKeypress=false;
	CountDownTimer keyPressTimeout = null;
	CountDownTimer startUpTimer = null;
	boolean isGFKeypress = false;
	/*	TextView txtDisplay = null;
	Button resetButton = null;
	 */
	String className = null;
	public static final int ACTION_DOWN = 0;
	public static final int ACTION_UP = 1;
	public static int counter = 0;
	CountDownTimer timer = null;// startUpTimer = null;
	// Media Player related variables
	Intent launchApp = new Intent();
	MediaPlayer mp = null;
	int pos = 0;
	String absoluteFilePath = null;
	int totalduration = 0;

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



		// get the absolute file path
		Intent intent = getIntent();
		absoluteFilePath = intent.getStringExtra(BrailleCommonUtil.APP_NAME_INTENT);
		className = this.getIntent().getStringExtra("className");
		startUpTimer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				ttsForMediaPlayerMenu();
			}
		}.start();
		keyPressTimeout = new CountDownTimer(1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				if (isGFKeypress == true || isHgKeypress == true) {
					isGFKeypress = false;
					isHgKeypress = false;
					lookuptable();
				}
			}
		};


		timer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFinish() {
				ttsForMediaPlayerMenu();
			}
		}.start();

		num0Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					//	ttsInvalidChoice();
					num0Button.setBackgroundResource(R.drawable.btn_green);
					// speak(speechOut);
				} else if (ACTION_UP == event.getAction()) {
					ttsForMediaPlayerMenu();
					num0Button.setBackgroundResource(R.drawable.btn_normal);
				}

				return true;
			}
		});

		num1Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("1");
					num1Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {

					// Start Playing
					mpStart();
					num1Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num2Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// timer.start();

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("2");
					num2Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					// stop Playing
					mpstop();
					num2Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num3Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				// timer.start();
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("3");
					num3Button.setBackgroundResource(R.drawable.btn_green);

				} else if (ACTION_UP == event.getAction()) {
					// Pause audio
					mpPause();
					num3Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num4Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// timer.start();
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("4");
					num4Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num4Button.setBackgroundResource(R.drawable.btn_normal);
					// Jump forward
					mpForward();
				}
				return true;
			}
		});

		num5Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("5");
					num5Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num5Button.setBackgroundResource(R.drawable.btn_normal);
					// Jump back word
					mpBackward();
				}
				return true;
			}
		});

		num6Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				// timer.start();

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("6");
					num6Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.not_assign));
					num6Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num7Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("7");
					num7Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.not_assign));
					num7Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num8Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("8");
					num8Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.not_assign));
					num8Button.setBackgroundResource(R.drawable.btn_normal);
				}
				return true;
			}
		});

		num9Button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("9");
					num9Button.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					num9Button.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.not_assign));
				}
				return true;
			}
		});




		numHButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (ACTION_DOWN == event.getAction()) {
					stop();
					speak("H");
					b3 = setBoolean();
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
					stop();
					b2 = setBoolean();
					speak("G");
					keyPressTimeout.cancel();
					isGFKeypress = true;
					numGButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numGButton.setBackgroundResource(R.drawable.btn_normal);
					if (isHgKeypress == true) {
						mpstop();
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						// launchApp.putExtra("TDLalarmhrs",reStr);
						if (className.equals("VoiceRecordMainMenu")) {
							launchApp.setClassName(PackageUtil.PACKAGE,PackageUtil.VOICERECORDERFILELIST);
							launchApp.putExtra(BrailleCommonUtil.APP_NAME_INTENT, BrailleCommonUtil.APP_NAME_VR);
						}
						startActivity(launchApp);
						finish();
					}
					keyPressTimeout.start();

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
					if (isGFKeypress == true) {
						launchApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						launchApp.setClassName(PackageUtil.PACKAGE,PackageUtil.STANDBYMAINMENU); 
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
					timer.cancel();
					stop();
					speak("Star");
					numStarButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numStarButton.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.not_assign));
				}
				return true;
			}
		});

		numHashButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (ACTION_DOWN == event.getAction()) {
					timer.cancel();
					stop();
					speak("Hash");
					numHashButton.setBackgroundResource(R.drawable.btn_green);
				} else if (ACTION_UP == event.getAction()) {
					numHashButton.setBackgroundResource(R.drawable.btn_normal);
					speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.not_assign));
				}
				return true;
			}
		});
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

	public void ttsForMediaPlayerMenu() {

		stop();
		timer.cancel();
		speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.mediaPlay_welcome));
		speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.please));
		speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.mediaPlay_one));
		speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.mediaPlay_two));
		speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.mediaPlay_three));
		speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.mediaPlay_four));
		speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.mediaPlay_five));
		speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.mediaPlay_six));
		speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.repeat_0));
		speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.previous_hg));
		// timer.start();
	}

	// MediaPlayer API's for Start ,Pause stop forward & backward
	public void mpStart() {

		if (mp != null) {
			mp.seekTo(pos);
			mp.start();
			pos = 0;
		} else {
			mp = new MediaPlayer();

			try {
				mp.setDataSource(absoluteFilePath);
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

		if (mp != null) {
			mp.stop();
			mp.release();
			mp = null;
			pos = 0;
		} else {
			speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.mediaPlay_stop));
		}

	}

	public void mpPause() {

		if (mp != null) {
			pos = mp.getCurrentPosition();
			mp.pause();
		} else {
			speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.mediaPlay_pause));
		}

	}

	public void mpForward() {
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
			speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.mediaPlay_forward));
		}
	}

	public void mpBackward() {
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
			speak(ResourceUtil.getStringFromResource(MediaFilePlayer.this,R.string.mediaPlay_backward));
		}
	}
}

