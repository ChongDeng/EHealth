package com.example.emergency;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.ChannelListener;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


@SuppressLint("NewApi")
public class BuddyActivity extends Activity {
  
  private static final String TAG = "WiFiDirectActivity";
  private List<WifiP2pDevice> deviceList = new ArrayList<WifiP2pDevice>();
  private ListView listView;
  private ListView chat;
  private ArrayAdapter aa;
  private TextView tv;
  private Button buttonDiscover;
  private Button buttonSend;
  private EditText inputEdit;
  private ArrayList<String> ChatContentList;
  private ArrayAdapter<String> a2;
  
  IntentFilter peerfilter;
  IntentFilter connectionfilter;
  IntentFilter p2pEnabled;
  int device_index;


  private static final int COMPLETED = 0; 
  //private Handler handler = new Handler();
  
	private Handler handler = new Handler() {  
		       @Override  
		        public void handleMessage(Message msg) {  
		            if (msg.what == COMPLETED) {  
		            	
		            }  
		        }  
		    };  

  /**
   * step1.1:  Initializing Wi-Fi Direct
   */
  private WifiP2pManager wifiP2pManager;
  private Channel wifiDirectChannel;

  private void initializeWiFiDirect() {
    wifiP2pManager = 
      (WifiP2pManager)getSystemService(Context.WIFI_P2P_SERVICE);

    wifiDirectChannel = wifiP2pManager.initialize(this, getMainLooper(), 
      new ChannelListener() {
        public void onChannelDisconnected() {
         initializeWiFiDirect();
        }
      }
    );
  }
  
  /**
   * step1.2: Creating a WiFi P2P Manager Action Listener
   */
  private ActionListener actionListener = new ActionListener() {
    public void onFailure(int reason) {
      String errorMessage = "WiFi Direct Failed: ";
      switch (reason) {
        case WifiP2pManager.BUSY : 
          errorMessage += "Framework busy."; break;
        case WifiP2pManager.ERROR : 
          errorMessage += "Internal error."; break;
        case WifiP2pManager.P2P_UNSUPPORTED : 
          errorMessage += "Unsupported."; break;
        default: 
          errorMessage += "Unknown error."; break;
      }
      Log.d(TAG, errorMessage);
    }
    
    public void onSuccess() {
      // Success! 
      // Return values will be returned using a Broadcast Intent
    	Log.d(TAG, "#########  Init Success");
    }
  };
  
  /**
   * step 2.1: Receiving a Wi-Fi Direct status change
   */
  BroadcastReceiver p2pStatusReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      int state = intent.getIntExtra(
        WifiP2pManager.EXTRA_WIFI_STATE,
        WifiP2pManager.WIFI_P2P_STATE_DISABLED);
     
      switch (state) {
        case (WifiP2pManager.WIFI_P2P_STATE_ENABLED): 
          buttonDiscover.setEnabled(true);
          buttonSend.setEnabled(true);
          break;
        default: 
          buttonDiscover.setEnabled(false);
          buttonSend.setEnabled(false);
      } 
    }
  };
  
  /**
   * step 3.1: Discovering Wi-Fi Direct peers
   */
  private void discoverPeers() {
	  wifiP2pManager.discoverPeers(wifiDirectChannel, actionListener);	    
  }

  //step 3.2
  BroadcastReceiver peerDiscoveryReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      wifiP2pManager.requestPeers(wifiDirectChannel, 
        new PeerListListener() {
          public void onPeersAvailable(WifiP2pDeviceList peers) {
            deviceList.clear();
            deviceList.addAll(peers.getDeviceList());
            aa.notifyDataSetChanged();
          }
        });
    }
  };
  
  /**
   * step 4.1: Requesting a connection to a Wi-Fi Direct peer
   */
  private void connectTo(WifiP2pDevice device) {
    WifiP2pConfig config = new WifiP2pConfig();
    config.deviceAddress = device.deviceAddress;
    Log.d(TAG, "#########  B");
    wifiP2pManager.connect(wifiDirectChannel, config, actionListener);
    Log.d(TAG, "#########  C");
  }
  
  /**
   * step 4.2: Connecting to a Wi-Fi Direct peer
   */
  BroadcastReceiver connectionChangedReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      
      // Extract the NetworkInfo
      String extraKey = WifiP2pManager.EXTRA_NETWORK_INFO;
      NetworkInfo networkInfo = 
        (NetworkInfo)intent.getParcelableExtra(extraKey);
      Log.d(TAG, "#########  D");
      // Check if we're connected
      if (networkInfo.isConnected()) {
        wifiP2pManager.requestConnectionInfo(wifiDirectChannel, 
          new ConnectionInfoListener() {
            public void onConnectionInfoAvailable(WifiP2pInfo info) { 
              // If the connection is established
              if (info.groupFormed) { 
                // If we're the server
                if (info.isGroupOwner) {
                   // TODO Initiate server socket.
                	Log.d(TAG, "#########  E 1");
                  //initiateServerSocket();
                  Log.d(TAG, "#########  E 2");
                }
                // If we're the client
                else if (info.groupFormed) {
                  // TODO Initiate client socket.
                	Log.d(TAG, "#########  F 1");
                  //initiateClientSocket(info.groupOwnerAddress.toString());
                  Log.d(TAG, "#########  F 2");
                }
              }
            }
          });
      } else {
        Log.d(TAG, "Wi-Fi Direct Disconnected");
      }
    }
  };
  
//step 4.3 
  private void initiateServerSocket() {
    ServerSocket serverSocket;
    try {
      /**
       * Listing 16-25: Creating a Server Socket
       */
      serverSocket = new ServerSocket(8666);
      Socket serverClient = serverSocket.accept();
      
      // TODO Start Sending Messages
    } catch (IOException e) {
      Log.e(TAG, "I/O Exception", e);
    }
  }
  
  //step 4.4
  private void initiateClientSocket(String hostAddress) {
    /**
     * Listing 16-26: Creating a client Socket
     */
    int timeout = 10000;
    int port = 8666;

    InetSocketAddress socketAddress 
      = new InetSocketAddress(hostAddress, port);

    try {
      Socket socket = new Socket();
      socket.bind(null);
      socket.connect(socketAddress, timeout);
    } catch (IOException e) {
      Log.e(TAG, "IO Exception.", e);
    }
    
    // TODO Start Receiving Messages
  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_buddy);
    
    tv = (TextView)findViewById(R.id.textView);   
   
    ///Used to display devices
    listView = (ListView)findViewById(R.id.listView);
    aa = new ArrayAdapter<WifiP2pDevice>(this, android.R.layout.simple_list_item_1, deviceList);
    listView.setAdapter(aa);
    
    //step1: initialize
    initializeWiFiDirect();
    
       
    peerfilter = new IntentFilter(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
    connectionfilter = new IntentFilter(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
    p2pEnabled = new IntentFilter(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
    
   
    //step 2.2
    registerReceiver(p2pStatusReceiver, p2pEnabled);
    //step 3.3
    registerReceiver(peerDiscoveryReceiver, peerfilter);
    //step 4.5
    registerReceiver(connectionChangedReceiver, connectionfilter);
    
    //step2 enable wifi direct
    Button buttonEnable = (Button)findViewById(R.id.buttonEnable);
    buttonEnable.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        /**
         * Listing 16-20: Enabling Wi-Fi Direct on a device
         */
    	  /*
        Intent intent = new Intent(
          android.provider.Settings.ACTION_WIRELESS_SETTINGS);

        startActivity(intent);
        */    	 
    	  Thread t = new Thread(new Runnable(){
  	    	public void run()
  	    	{  	    		
  	    		discoverPeers();
  	    
  	    		  Message msg = new Message();  
  			      msg.what = COMPLETED;  
  			      handler.sendMessage(msg); 

  	    	}
  	    });
  	     t.start();
    	  
         
      }
    });
    
    //step3 discover peers
    buttonDiscover = (Button)findViewById(R.id.buttonDiscover);
    buttonDiscover.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
       //discoverPeers();
      }
    });

   
    //step4 click device list to  connect
    listView.setOnItemClickListener(new OnItemClickListener() {
      public void onItemClick(AdapterView<?> arg0, View arg1, int index,
          long arg3) {
    	  Log.d(TAG, "#########  A");
    	  device_index = index;
    	  Thread t = new Thread(new Runnable(){
    	    	public void run()
    	    	{
    	    		Log.d(TAG, "1111111111111111");
    	    		connectTo(deviceList.get(device_index));
    	    		 /*
    	    		  Message msg = new Message();  
    			      msg.what = COMPLETED;  
    			      handler.sendMessage(msg); 
*/
    	    	}
    	    });
    	   t.start();

    	
        
        Log.d(TAG, "#########  Z");
      }
    });
    
    //step5 send
    chat = (ListView)findViewById(R.id.contentChatView);
    ChatContentList = new ArrayList<String>();
    a2 = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            ChatContentList);  
    chat.setAdapter(a2);
    
    inputEdit = (EditText)findViewById(R.id.InputText);    
    buttonSend = (Button)findViewById(R.id.SendButton);
    buttonSend.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
            if(inputEdit.getText().toString().length() > 0)
            {
            	ChatContentList.add(inputEdit.getText().toString());
            	inputEdit.setText("");
            	a2.notifyDataSetChanged();
            }
        	
        }
      });    
  }
  
   
  private void sendMessage(Socket socket, String message) {
      OutputStream outStream;
      try {
        outStream = socket.getOutputStream();

        // Add a stop character.
        byte[] byteArray = (message + " ").getBytes();
        byteArray[byteArray.length - 1] = 0;

        outStream.write(byteArray);
      } catch (IOException e) { 
        Log.e(TAG, "Message send failed.", e);
      }
    }

    private boolean listening = false;
     
    private void listenForMessages(Socket socket, 
                                   StringBuilder incoming) {
      listening = true;


      int bufferSize = 1024;
      byte[] buffer = new byte[bufferSize];

      try {
        InputStream instream = socket.getInputStream();
        int bytesRead = -1;

        while (listening) {
          bytesRead = instream.read(buffer);
          if (bytesRead != -1) {
            String result = "He: ";
            while ((bytesRead == bufferSize) &&
                   (buffer[bufferSize-1] != 0)){
              result = result + new String(buffer, 0, bytesRead - 1);
              bytesRead = instream.read(buffer);
            }
            result = result + new String(buffer, 0, bytesRead - 1);
            incoming.append(result);
          }
         // socket.close();
        }
      } catch (IOException e) {
        Log.e(TAG, "Message received failed.", e);
      }
      finally {
      }
    }
  
  @Override
  protected void onPause() {
    unregisterReceiver(peerDiscoveryReceiver);
    unregisterReceiver(connectionChangedReceiver);
    unregisterReceiver(p2pStatusReceiver);
    super.onPause();
  }

  @Override
  protected void onResume() {
    super.onResume();
    registerReceiver(peerDiscoveryReceiver, peerfilter);
    registerReceiver(connectionChangedReceiver, connectionfilter);
    registerReceiver(p2pStatusReceiver, p2pEnabled);
  }  
 
}