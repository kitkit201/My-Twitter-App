package com.codepath.apps.mytwitterapp.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import com.codepath.apps.mytwitterapp.MyTwitterApp;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;

public class HomeTimelineFragment extends TweetsListFragments {
	@Override
	public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			MyTwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() { 
				@Override
				public void onSuccess(JSONArray jsonTweets){
					getAdapter().addAll(Tweet.fromJson(jsonTweets));
			     }
			});
		
	}
}
