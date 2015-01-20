package com.jhdev.hr1;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;

public class MainApplication extends Application {

    public static final String GROUP_NAME_LISTINGS = "ALL_LISTINGS";

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(JobListing.class);

        // enable the Local Datastore
        Parse.enableLocalDatastore(getApplicationContext());

        // Required - Initialize the Parse SDK
        Parse.initialize(this, getString(R.string.parse_app_id),
                getString(R.string.parse_client_key));

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

//        ParseACL defaultACL = new ParseACL();
//        ParseACL.setDefaultACL(defaultACL, true);

        // Optional - If you don't want to allow Twitter login, you can
        // remove this line (and other related ParseTwitterUtils calls)
//        ParseTwitterUtils.initialize(getString(R.string.twitter_consumer_key),
//                getString(R.string.twitter_consumer_secret));
    }
}
