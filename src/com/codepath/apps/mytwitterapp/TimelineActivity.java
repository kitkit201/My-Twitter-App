package com.codepath.apps.mytwitterapp;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.mytwitterapp.fragments.HomeTimelineFragment;
import com.codepath.apps.mytwitterapp.fragments.MentionsFragment;
import com.codepath.apps.mytwitterapp.fragments.TweetsListFragments;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends FragmentActivity implements TabListener{
	TweetsListFragments fragmentTweets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupNavigationTabs();
	}

	private void setupNavigationTabs(){
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		Tab tabHome = actionBar.newTab().setText("Home")
				.setTag("HomeTimelineFragment")
				.setIcon(R.drawable.ic_home)
				.setTabListener(this);
		
		Tab tabMentions = actionBar.newTab().setText("Mentions")
				.setTag("MentionsTimelineFragment")
				.setIcon(R.drawable.ic_mentions)
				.setTabListener(this);;
		
		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);
		
		
		
	}
/*	public void refreshTimeline(){
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
	*/
	
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
/*		case R.id.action_refresh:
				Toast.makeText(this, "Refreshing Timeline", Toast.LENGTH_SHORT).show();
				refreshTimeline();
				return true;*/
		default:
			return super.onOptionsItemSelected(item);

		}
	}

	public void onProfileView(MenuItem mi) {
		Intent i = new Intent(this, ProfileActivity.class);
		startActivity(i);
	}
	
	public void launchCompose(){
			Intent i = new Intent(getBaseContext(), ComposeActivity.class);
			startActivity(i);
			   
		}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		
		if (tab.getTag() == "HomeTimelineFragment") {
			// set framgement in framelayout to home timeline
			fts.replace(R.id.frame_container, new HomeTimelineFragment());
		} else { 
			fts.replace(R.id.frame_container, new MentionsFragment());
			// fragement layout to mention timeline.
		}
		fts.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
