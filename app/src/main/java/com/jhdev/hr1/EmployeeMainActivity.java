package com.jhdev.hr1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;

/*
 * This loads the list of jobs for the prospective employee
 */
public class EmployeeMainActivity extends ActionBarActivity {

    ParseUser currentUser;
    ListView lv;
    JobListingCustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_activity_main);

        currentUser = ParseUser.getCurrentUser();

//        // Set up the Parse query to use in the adapter
//        ParseQueryAdapter.QueryFactory<JobListing> factory = new ParseQueryAdapter.QueryFactory<JobListing>() {
//            public ParseQuery<JobListing> create() {
//                ParseQuery<JobListing> query = JobListing.getQuery();
//                query.orderByAscending("title");
//                query.whereEqualTo("isDraft", false);
//                query.fromLocalDatastore();
//                return query;
//            }
//        };

//        mainAdapter = new ParseQueryAdapter<JobListing>(getBaseContext(), factory);
//        mainAdapter = new ParseQueryAdapter<>(this, JobListing.class);
//        mainAdapter.setTextKey("title");

        customAdapter = new JobListingCustomAdapter(this);

        lv = (ListView) findViewById(R.id.listView);
//        lv.setAdapter(mainAdapter);
//        mainAdapter.loadObjects();

        lv.setAdapter(customAdapter);
        customAdapter.loadObjects();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                JobListing jobListing = (JobListing) customAdapter.getItem(position);

//                Toast.makeText(getApplicationContext(),
//                        "Click ListItem Number " + jobListing.getTitle(), Toast.LENGTH_SHORT)
//                        .show();

                ParseAnalytics.trackEventInBackground("Open JobListItem");
                jobListing.increment("open");
                jobListing.saveEventually();

                Intent intent = new Intent(view.getContext(), JobListItem.class);
                intent.putExtra("selectedId", jobListing.getObjectId());
                startActivity(intent);

            }
        });


//        loadFromParse();
    }



//    public void loadFromParse() {
////        ParseQuery
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            Intent intent = new Intent(this, EmployeeProfileActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_applications) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
