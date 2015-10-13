package com.example.emergency;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class MainActivity extends TabActivity {

	public static String myInfo;
	private TabHost tabHost;
	private RadioGroup radioGroup;
	private static final String RECENT = "会话";
	private static final String BUDDY = "好友";
	private static final String GROUP = "群组";
	private static final String TRENDS = "动态";
	private static final String MORE= "更多";
	public Intent recentIntent;
	public Intent buddyIntent;
	public Intent groupIntent;
	public Intent trendsIntent;
	public Intent moreIntent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabHost = this.getTabHost();
		setupIntent();
		radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case R.id.tab_recent:
					tabHost.setCurrentTabByTag(RECENT);
					break;
				case R.id.tab_buddy:
					tabHost.setCurrentTabByTag(BUDDY);
					break;
				case R.id.tab_group:
					tabHost.setCurrentTabByTag(GROUP);
					break;
				case R.id.tab_trends:
					tabHost.setCurrentTabByTag(TRENDS);
					break;
					/*
				case R.id.tab_more:
					tabHost.setCurrentTabByTag(MORE);
					break;*/
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void setupIntent() {
		
		//recentIntent= new Intent(this, uselessActivity.class);
		recentIntent= new Intent(this, RecentActivity.class);
		//recentIntent= new Intent(this, HospitalActivity.class);
		//buddyIntent= new Intent(this, BuddyActivity.class);
		buddyIntent= new Intent(this, WiFiServiceDiscoveryActivity.class);		
		groupIntent= new Intent(this, GroupAcitivity.class);
		trendsIntent= new Intent(this, TrendsAcitivy.class);
		moreIntent= new Intent(this, MoreActivity.class);
		
		TabSpec tabSpec1 = tabHost.newTabSpec(RECENT).setIndicator(RECENT)
				.setContent(recentIntent);
		tabHost.addTab(tabSpec1);
		TabSpec tabSpec2 = tabHost.newTabSpec(BUDDY).setIndicator(BUDDY)
				.setContent(buddyIntent);
		tabHost.addTab(tabSpec2);
		TabSpec tabSpec3 = tabHost.newTabSpec(GROUP).setIndicator(GROUP)
				.setContent(groupIntent);
		tabHost.addTab(tabSpec3);
		TabSpec tabSpec4 = tabHost.newTabSpec(TRENDS).setIndicator(TRENDS)
				.setContent(trendsIntent);
		tabHost.addTab(tabSpec4);
		TabSpec tabSpec5 = tabHost.newTabSpec(MORE).setIndicator(MORE)
				.setContent(moreIntent);
		tabHost.addTab(tabSpec5);
	}
	
}
