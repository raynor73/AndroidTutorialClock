package org.ilapin.androidtutorialclock;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
	private static final int TICK_DELAY_MILLIS = 250;

	private final Handler mHandler = new Handler(Looper.getMainLooper());
	private final SimpleDateFormat mDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
	private final Runnable mTickListener = new Runnable() {
		@Override
		public void run() {
			mTimeTextView.setText(mDateFormat.format(Calendar.getInstance().getTime()));
			mHandler.postDelayed(mTickListener, TICK_DELAY_MILLIS);
		}
	};

	private TextView mTimeTextView;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTimeTextView = (TextView) findViewById(R.id.textView);
		mTickListener.run();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		mHandler.removeCallbacks(mTickListener);
		mTimeTextView = null;
	}
}
