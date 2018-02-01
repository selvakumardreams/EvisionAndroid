
package com.evision.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;

public class BugReportActivity extends Activity {
	static final String STACKTRACE = "evision.stacktrace";

	private String getVersionName() {
		try {
			return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (Exception e) {
			return "";
		}
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bug_report_view);
		final String stackTrace = getIntent().getStringExtra(STACKTRACE);
		final TextView reportTextView = (TextView)findViewById(R.id.report_text);
		reportTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
		reportTextView.setClickable(false);
		reportTextView.setLongClickable(false);

		final String versionName = getVersionName();
		reportTextView.append("Evision " + versionName + " has been crached, sorry. You can help to fix this bug by sending the report below to bereader developers. The report will be sent by e-mail. Thank you in advance!\n\n");
		reportTextView.append(stackTrace);

		findViewById(R.id.send_report).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View view) {
						Intent sendIntent = new Intent(Intent.ACTION_SEND);
						sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "mydreams.info@gmail.com" });
						sendIntent.putExtra(Intent.EXTRA_TEXT, stackTrace);
						sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Evision " + versionName + " exception report");
						sendIntent.setType("message/rfc822");
						startActivity(sendIntent);
						finish();
					}
				});

		findViewById(R.id.cancel_report).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View view) {
						finish();
					}
				}
				);
	}
}
