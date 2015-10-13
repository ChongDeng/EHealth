package com.example.emergency;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import android.widget.ArrayAdapter;
import android.widget.ListView;


public class RecentActivity extends Activity {
	
	private static final int COMPLETED = 0; 
	URL url;
	InputStream in; 
	private static final String TAG = "PAUL";
	private static final String TAG2 = "DC";
	
	private ListView listView;
	ArrayAdapter<Quake> aa;
	ArrayList<Quake> earthquakes = new ArrayList<Quake>();
	
	private EditText SearchText;
	private Button SearchButton;
	
	List<Map<String, Object>> list;
	SimpleAdapter adapter;
	boolean network_active; 
	private Handler handler = new Handler() {  
		       @Override  
		        public void handleMessage(Message msg) {  
		            if (msg.what == COMPLETED) {  
		            	if(network_active)
		            	   Refresh_View_DB( );
		            	else
		            	   Refresh_By_LocalDB();
		            }  
		        }  
		    };  

		    protected void Refresh_View_DB( ) {
            //aa.notifyDataSetChanged();
		    	
		    	list = new ArrayList<Map<String, Object>>();
           	    adapter = new SimpleAdapter(this,getData(),R.layout.row, 
                        new String[]{"name","img"}, 
                        new int[]{R.id.title,R.id.img}); 
        		 listView.setAdapter(adapter); 
        		 
        		ContentValues values = new ContentValues();
        		DBHelper helper = new DBHelper(getApplicationContext());
        		helper.del();
         		for(Quake obj: earthquakes)
      	        {
      	    	    String name = obj.getName();
      	    	    values.put("name", name);  
      	    	    
      	    	    String addr = obj.getAddress();
      	    	    values.put("address", addr);
      	    	    
      	    	    String tel = obj.getTel();
    	    	    values.put("telephone", tel);
    	    	    
    	    	    String description = obj.getDescription();
    	    	    values.put("description", description);
      	    	    
  				    helper.insert(values);
      	        }
		    	
		    }
   
		    
		    
		    private void Refresh_By_LocalDB()
			{	
		    	
		    	list = new ArrayList<Map<String, Object>>();
	    		final DBHelper helper = new DBHelper(this);	   			
	   			Cursor c = helper.query();
	   			if(c.getCount() > 0)
	   			{
	   				while(c.moveToNext())
	   				{  
	   						Map<String, Object> map = new HashMap<String, Object>(); 
	   				        map.put("name", c.getString(1));		     
	   				        map.put("img", R.drawable.sos2);	
	   				        map.put("address", c.getString(2));
	   				        map.put("telephone", c.getString(3));
	   				        map.put("description", c.getString(4));
	   				        list.add(map);							
	   			    }					
	   			}	   	   
		   	    adapter = new SimpleAdapter(this,getData(),R.layout.row, 
		                new String[]{"name","img"}, 
		                new int[]{R.id.title,R.id.img}); 
				 listView.setAdapter(adapter);
				  
			/*	
				 network_active = false;
		   	  //读取本地数据库 
		   	  final int time = Toast.LENGTH_LONG;
					Toast t1 = Toast.makeText(getApplicationContext(), "no network", time);
					t1.show(); 
				
			}			
			*/
			}
		    
		    private void ReadLocalDB()
			{	
		    	list = new ArrayList<Map<String, Object>>();
	    		final DBHelper helper = new DBHelper(this);	   			
	   			Cursor c = helper.query();
	   			if(c.getCount() > 0)
	   			{
	   				while(c.moveToNext())
	   				{  
	   						Map<String, Object> map = new HashMap<String, Object>(); 
	   				        map.put("name", c.getString(1));		     
	   				        map.put("img", R.drawable.sos2);	
	   				        map.put("address", c.getString(1));
	   				        list.add(map);							
	   			    }					
	   			}	   	   
			}
		    
    private List<Map<String, Object>> getData() 
    { 
		        
    	       for(Quake obj: earthquakes)
    	       {
    	    	   Map<String, Object> map = new HashMap<String, Object>(); 
   		           map.put("name", obj.getName());		     
   		           map.put("img", R.drawable.sos2); 
   		           map.put("address", obj.getAddress());
   		           map.put("telephone", obj.getTel());
   		           map.put("description", obj.getDescription());
   		           list.add(map); 
    	       }    	       
    	       return list;    
    } 	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recent);		
		
		network_active = false;
		earthquakes.clear();		 
		 
		listView = (ListView)findViewById(R.id.listView);	
	    listView.setOnItemClickListener(new OnItemClickListenerImpl()); 
		
	    SearchText = (EditText)findViewById(R.id.SearchInput);
	    SearchButton = (Button)findViewById(R.id.SearchButton);	    
	    SearchButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {		
				
				if(!SearchText.getText().toString().trim().equals(""))
				{
					Bundle b = new Bundle();
					b.putString("name", SearchText.getText().toString());
					
					Intent intent = new Intent(RecentActivity.this,
							HospitalQueryResultActivity.class);
					intent.putExtra("data", b); 
					startActivity(intent);
				}
			}
		});
	       
	   
		Thread t = new Thread(new Runnable(){
	    public void run()
	    {
	    	   boolean networkState = NetworkDetector.detect(RecentActivity.this);  	    	   
	    	   if (networkState)
	    	   {
	    		   network_active = true;	
	    		   getRemoteHospitalInfo();
	    	   }      	    	  	   

	    	   Message msg = new Message();  
 	  	       msg.what = COMPLETED;  
 		       handler.sendMessage(msg);
	    	}
	    });
	    t.start();		   
	}
	
	public void getRemoteHospitalInfo() {
		
		// 命名空间
		String nameSpace = "http://104.209.40.90/ehealth/";
		// 调用的方法名称
		String methodName = "getHospitalInfos";
		// EndPoint
		String endPoint = "http://104.209.40.90/ehealth/EHealthWebservice.asmx";
		// SOAP Action
		String soapAction = "http://104.209.40.90/ehealth/getHospitalInfos";
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的两个参数mobileCode、userId
		//rpc.addProperty("mobileCode", phoneSec);
		//rpc.addProperty("userId", "");
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		envelope.setOutputSoapObject(rpc);
		Log.d(TAG, "5");
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		Log.d(TAG, "6"); 
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 获取返回的数据
		SoapObject object = (SoapObject) envelope.bodyIn;
		
		// 获取返回的结果
		String result = object.getProperty(0).toString();	
		
				
		InputStream  in  =   new   ByteArrayInputStream(result.getBytes());
		
		processStream2(in);
	}
	
	private void processStream2(InputStream inputStream) {
		   // Create a new XML Pull Parser.
		    XmlPullParserFactory factory;
		    try {
		      factory = XmlPullParserFactory.newInstance();
		      factory.setNamespaceAware(true);
		      XmlPullParser xpp = factory.newPullParser();
		      // Assign a new input stream.
		      xpp.setInput(inputStream, null);
		      int eventType = xpp.getEventType();
		      
		      earthquakes.clear();
		      
		      // Continue until the end of the document is reached.
		      while (eventType != XmlPullParser.END_DOCUMENT) {
		    	  //Log.d(TAG, "2.2");
		        // Check for a start tag of the results tag.
		        if (eventType == XmlPullParser.START_TAG &&
		          xpp.getName().equals("Hospital")) {
		          eventType = xpp.next();
		          String name = "";		        
	        	  String addr = "";
	        	  String tel = "";
	        	  String description = "";
		          Log.d(TAG, "2.a");
		          // Process each result within the result tag.
		          while (!(eventType == XmlPullParser.END_TAG &&
		            xpp.getName().equals("Hospital"))) {
		        	   
			            // Check for the name tag within the results tag.
			            if (eventType == XmlPullParser.START_TAG &&
			              xpp.getName().equals("f_name"))
			            {
			              // Extract the POI name.
			              name = xpp.nextText();			            
			            }
			            
			            if (eventType == XmlPullParser.START_TAG &&
					         xpp.getName().equals("f_add_street_1"))
					    {
			            	addr = xpp.nextText();
					    }
			            
			            if (eventType == XmlPullParser.START_TAG &&
						         xpp.getName().equals("f_add_city"))
						    {
				            	addr = addr + ", " + xpp.nextText();
						    }
			            
			            if (eventType == XmlPullParser.START_TAG &&
						         xpp.getName().equals("f_phoneNumber"))
						    {
			            	tel =  xpp.nextText();
						    }
			            
			            if (eventType == XmlPullParser.START_TAG &&
						         xpp.getName().equals("f_intro"))
						    {
			            	description =  xpp.nextText();
						    }
			            
			            
			            
		                // Move on to the next tag.
		                eventType = xpp.next();
		            }
		          
		          
		          Quake quake = new Quake(name, addr, tel, description);			              
	              addNewQuake(quake);
	              
	              
		          // Do something with each POI name.
		          }
		        // Move on to the next result tag.
		        eventType = xpp.next();
		      }	      
		      
		    } catch (XmlPullParserException e) {
		      Log.d("PULLPARSER", "XML Pull Parser Exception", e);
		    } catch (IOException e) {
		      Log.d("PULLPARSER", "IO Exception", e);
		    }
		    Log.d(TAG, "2.z");
		  }
	
	
	private class OnItemClickListenerImpl implements OnItemClickListener {


		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
		long id) { 
					
			Map<String, Object> obj = (Map<String, Object>) RecentActivity.this.adapter.getItem(position);
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
			
			Intent intent = new Intent(RecentActivity.this,HospitalItemActivity.class);			
			intent.putExtra("data", b);  //b是Bundle类			
			startActivity(intent);

			/*
			final int time = Toast.LENGTH_LONG;
			Toast t1 = Toast.makeText(getApplicationContext(), str, time);
			t1.show();
			*/
 
		         }
	}
		




	
	private void processStream(InputStream inputStream) {
		   // Create a new XML Pull Parser.
		    XmlPullParserFactory factory;
		    try {
		      factory = XmlPullParserFactory.newInstance();
		      factory.setNamespaceAware(true);
		      XmlPullParser xpp = factory.newPullParser();
		      // Assign a new input stream.
		      xpp.setInput(inputStream, null);
		      int eventType = xpp.getEventType();
		      Log.d(TAG, "2.1");
		      
		      earthquakes.clear();
		      
		      // Continue until the end of the document is reached.
		      while (eventType != XmlPullParser.END_DOCUMENT) {
		    	  //Log.d(TAG, "2.2");
		        // Check for a start tag of the results tag.
		        if (eventType == XmlPullParser.START_TAG &&
		          xpp.getName().equals("entry")) {
		          eventType = xpp.next();
		          String name = "";
		          Log.d(TAG, "2.a");
		          // Process each result within the result tag.
		          while (!(eventType == XmlPullParser.END_TAG &&
		            xpp.getName().equals("entry"))) {
			            // Check for the name tag within the results tag.
			            if (eventType == XmlPullParser.START_TAG &&
			              xpp.getName().equals("title"))
			            {
			              // Extract the POI name.
			              name = xpp.nextText();
			              Quake quake = new Quake(name);			              
			              addNewQuake(quake);
			              Log.d(TAG, name);
			            }
		                // Move on to the next tag.
		                eventType = xpp.next();
		            }
		          // Do something with each POI name.
		          }
		        // Move on to the next result tag.
		        eventType = xpp.next();
		      }
		      
		      Message msg = new Message();  
		      msg.what = COMPLETED;  
		      handler.sendMessage(msg);  

		      
		    } catch (XmlPullParserException e) {
		      Log.d("PULLPARSER", "XML Pull Parser Exception", e);
		    } catch (IOException e) {
		      Log.d("PULLPARSER", "IO Exception", e);
		    }
		    Log.d(TAG, "2.z");
		  }
	
	  private void addNewQuake(Quake _quake) {
		    // Add the new quake to our list of earthquakes.
		    earthquakes.add(_quake);

		    // Notify the array adapter of a change.
		    //aa.notifyDataSetChanged();
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
	
	private boolean HasExistedInDatabase(List<Map<String, Object>> list, String SearchName)
	{
		for(Map<String, Object> map : list)
		{
		   Log.d(TAG2, map.get("name").toString());
		   if(map.get("name").toString().equals(SearchName))
			   return true;
		}
		return false;
	}
}
