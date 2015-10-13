package com.example.emergency;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HospitalItemActivity extends Activity {
	TextView NameView;
	TextView AddessView;
	TextView LinkView;
	TextView DescriptView;
	TextView TelView;
	String tel;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_hospitalitem);
		
		NameView = (TextView)findViewById(R.id.textView_name);
		AddessView = (TextView)findViewById(R.id.textView_address); 
		LinkView = (TextView)findViewById(R.id.textView_link); 
		DescriptView = (TextView)findViewById(R.id.textView_description); 
		TelView = (TextView)findViewById(R.id.textView_tel); 
		
        Intent intent = this.getIntent();	 	
		Bundle b = intent.getBundleExtra("data");
		
		//String name = b.getString("name"); 
		//NameView.setText("Hospital: " + name);
		
		String addess = b.getString("address");
		AddessView.setText("Address: " + addess);
		
		String website = b.getString("link");
		LinkView.setText(website); 
		
		String description = b.getString("description");
		DescriptView.setText(description);
		
		tel = b.getString("tel");
		TelView.setText("Tel: " + tel); 
		
		
		
		 TelView.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// Switching to Register screen
					/*
					final int time = Toast.LENGTH_LONG;
					Toast t1 = Toast.makeText(getApplicationContext(), "Hello", time);
					t1.show();
					*/
					Intent intent = new Intent();
					Uri uri ;
					String data;

					data = "tel:" + tel;
					uri = Uri.parse(data);
					intent.setAction(Intent.ACTION_DIAL);
					intent.setData(uri);
					startActivity(intent);

				}
			});
		
	}

}
