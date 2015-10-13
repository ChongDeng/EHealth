package com.example.emergency;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class HospitalQueryResultActivity extends Activity {
	private static final String TAG = "SHIT";
	List<Map<String, Object>> list;
	SimpleAdapter adapter; 
	ListView listView;
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_queryresult);	 
	
		
		listView = (ListView)findViewById(R.id.listView);
		list = new ArrayList<Map<String, Object>>();
   	    adapter = new SimpleAdapter(this,getData(),R.layout.row, 
                new String[]{"name","img"}, 
                new int[]{R.id.title,R.id.img}); 
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListenerImpl());
		/*	 
		 
		
		
		final int time = Toast.LENGTH_LONG;
		Toast t1 = Toast.makeText(getApplicationContext(), name, time);
		t1.show();
		
		*/
		
		
		
		
		    
	}
	
	 private List<Map<String, Object>> getData() 
	 { 		
		   Intent intent = this.getIntent();		
			Bundle b = intent.getBundleExtra("data");
			
			String name = b.getString("name");	
			
			final DBHelper helpter = new DBHelper(this);
			//Cursor c = helpter.query(result_columns, where);
			Cursor c = helpter.query();
			if(c.getCount() > 0)
			{
				while(c.moveToNext()) 
				{   Log.d(TAG, "AA");
	              //  c.move(i);
					String Name_Col = c.getString(1);
					if(Name_Col.indexOf(name) >= 0)
					{
						Map<String, Object> map = new HashMap<String, Object>(); 
				        map.put("name", Name_Col);		     
				        map.put("img", R.drawable.sos2);
				        
				        String Address_Col = c.getString(2);
				        String Tel_Col = c.getString(3);
				        String Description_Col = c.getString(4);
				        
				        map.put("address", Address_Col);
				        map.put("telephone", Tel_Col);
		   		           map.put("description", Description_Col);
				        list.add(map);							
					}					
				}
			}
			
		
			return list;         
	 } 
	 
	 private class OnItemClickListenerImpl implements OnItemClickListener {


			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) { 				
				
				Map<String, Object> obj = (Map<String, Object>) HospitalQueryResultActivity.this.adapter.getItem(position);
				String name = obj.get("name").toString();
				//String addess = "San Jose";
				String address = obj.get("address").toString();
				String link = "http://www.sutterhealth.org/";
				/*
				String description = "2013 Report to Our Communities In 2013, our Sutter Health family of " +
						"employees, physicians and volunteers continued to " +
						"demonstrate our passion for delivering health care personalized to patients' needs";

				String tel = "4087506139";
				*/
				
				String description = obj.get("description").toString();
				String tel = obj.get("telephone").toString();
				Bundle b = new Bundle();
				b.putString("name", name);
				b.putString("address", address);
				b.putString("link", link);
				b.putString("description", description);
				b.putString("tel", tel);
				
				Intent intent = new Intent(HospitalQueryResultActivity.this,HospitalItemActivity.class);			
				intent.putExtra("data", b);  //b «Bundle¿‡			
				startActivity(intent);

		
		       }
		}
}
