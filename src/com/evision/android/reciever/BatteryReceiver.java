package com.evision.android.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class BatteryReceiver extends BroadcastReceiver {

	AssetFileDescriptor afd;
	private MediaPlayer mMediaPlayer;
	private Context mContext;
	private int batteryLevel;
	private int batteryHealth;
	
	public BatteryReceiver(Context context) {
		this.mContext = context;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BATTERY_CHANGED)) {  
			int level = intent.getIntExtra("level", -1);
			int scale = intent.getIntExtra("scale", -1);
			int health = intent.getIntExtra("health", 0);
			
			if (level >= 0 && scale > 0) {
				setBatteryLevel((level * 100) / scale);
			}
			setBatteryHealth(health);
		}  else if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BATTERY_LOW)  
				|| intent.getAction().equalsIgnoreCase(  
						Intent.ACTION_BATTERY_OKAY)) {
			try
			{
				afd = mContext.getAssets().openFd("beep.wav");
				mMediaPlayer = new MediaPlayer();
				mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd
						.getStartOffset(), afd.getLength());
				afd.close();
				mMediaPlayer.prepare();
				mMediaPlayer.start();
				mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					public void onCompletion(MediaPlayer mMediaPlayer) {
						mMediaPlayer.stop();
						mMediaPlayer.release();
					}
				});

			} catch (Exception e) {
			}
		}

	}
	
	public int getBatteryLevel() {
		return batteryLevel;
	}


	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}


	public int getBatteryHealth() {
		return batteryHealth;
	}


	public void setBatteryHealth(int batteryHealth) {
		this.batteryHealth = batteryHealth;
	}

}
