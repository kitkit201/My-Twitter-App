package com.codepath.apps.mytwitterapp;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);	
	}

	public void sendTweet(View v){
		// If user clicked on tweet, send via AsyncHttp and send back to timeline

		// Get Edit text and convert to string
		EditText tempstatusupdate = (EditText) findViewById(R.id.etTweet);
		String status = tempstatusupdate.getText().toString(); 
		
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();

        //only proceed if there is a body message. Otherwise, do nothing
    	if (status.length() != 0) {
    		MyTwitterApp.getRestClient().postNewTweet(status, new JsonHttpResponseHandler() { 
				@Override
				public void onSuccess(String j){
					Toast.makeText(getBaseContext(), j, Toast.LENGTH_SHORT).show();
			    	Intent i = new Intent(getBaseContext(), TimelineActivity.class);
			    	startActivity(i);	
			     }
				 public void onFailure(Throwable e, JSONArray error) {
					    // Handle the failure and alert the user to retry
					    Log.e("ERROR", e.toString());
				 }
	    	});
    		Intent i = new Intent(getBaseContext(), TimelineActivity.class);
    		startActivity(i);
    	
    	}

	    
	}
	
	public void backtoTimeline(View v){
		        Toast.makeText(this, "Going back to Timeline", Toast.LENGTH_SHORT).show();
		    	Intent i = new Intent(getBaseContext(), TimelineActivity.class);
		    	startActivity(i);	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}
	
	

}
