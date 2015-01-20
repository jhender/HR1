package com.jhdev.hr1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/*
 * This loads the list of jobs for the prospective employee
 */
public class EmployeeMainActivity extends ActionBarActivity {

    ParseUser currentUser;
    ListView lv;
    ParseQueryAdapter<JobListing> mainAdapter;
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

                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_SHORT)
                        .show();

            }
        });


//        loadFromParse();
    }



//    public void loadFromParse() {
////        ParseQuery
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.employer_menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
