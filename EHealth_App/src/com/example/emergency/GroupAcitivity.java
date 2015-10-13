package com.example.emergency;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GroupAcitivity extends Activity {
	
	
	WebView webview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group); 
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        //webview.loadUrl("http://www.sutterhealth.org/quality/innovation/");
        webview.loadUrl("http://104.209.40.90/ehealth/firstaid.aspx");
        
        webview.setWebViewClient(new HelloWebViewClient ());
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	Log.i("test", keyCode+"-------");
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            Log.i("test", keyCode+"+++++++++++++++++");
            return true;
        }
        return super.onKeyDown(keyCode, event); 
    }
    
    
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    
}
