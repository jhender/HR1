package com.jhdev.hr1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.List;


public class EmployerMainActivity extends ActionBarActivity {

    ParseUser currentUser;
    ListView lv;
    ParseQueryAdapter<JobListing> parseQueryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_activity_main);

        currentUser = ParseUser.getCurrentUser();

        // Set up the Parse query to use in the adapter
        ParseQueryAdapter.QueryFactory<JobListing> factory = new ParseQueryAdapter.QueryFactory<JobListing>() {
            public ParseQuery<JobListing> create() {
                ParseQuery<JobListing> query = JobListing.getQuery();
                query.orderByAscending("title");
                query.whereEqualTo("isDraft", false);
                query.fromLocalDatastore();
                return query;
            }
        };

        parseQueryAdapter = new ParseQueryAdapter<JobListing>(getBaseContext(), factory);

        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(parseQueryAdapter);

        loadFromParse();
    }

    public void loadFromParse() {
//        ParseQuery
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.employer_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
