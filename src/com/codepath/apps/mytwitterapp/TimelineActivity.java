package com.codepath.apps.mytwitterapp;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		
		MyTwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() { 
			@Override
			public void onSuccess(JSONArray jsonTweets){
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				
				ListView lvTweets = (ListView) findViewById(R.id.lvTweets);
				TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
				lvTweets.setAdapter(adapter);
				Log.d("DEBUG", jsonTweets.toString());
		     }
		});
		
		
	}

	public void refreshTimeline(){
		MyTwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() { 
			@Override
			public void onSuccess(JSONArray jsonTweets){
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				
				ListView lvTweets = (ListView) findViewById(R.id.lvTweets);
				TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
				lvTweets.setAdapter(adapter);
				Log.d("DEBUG", jsonTweets.toString());
		     }
		});
		
	} 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.action_compose:
				Toast.makeText(this, "Launching Compose", Toast.LENGTH_SHORT).show();
				launchCompose();
				return true;
		case R.id.action_refresh:
				Toast.makeText(this, "Refreshing Timeline", Toast.LENGTH_SHORT).show();
				refreshTimeline();
				return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}

	
	
	public void launchCompose(){
			Intent i = new Intent(getBaseContext(), ComposeActivity.class);
			startActivity(i);
			   
		}
}
