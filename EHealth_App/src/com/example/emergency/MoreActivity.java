package com.example.emergency;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MoreActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_more);
	}
}
