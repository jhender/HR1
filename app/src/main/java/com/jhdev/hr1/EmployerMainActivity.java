package com.jhdev.hr1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class EmployerMainActivity extends ActionBarActivity {

    ParseUser currentUser;
    ListView lv;
    ParseQueryAdapter<JobListing> parseQueryAdapter;

    private ParseQueryAdapter<JobListing> popularListAdapter;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_activity_main);

        currentUser = ParseUser.getCurrentUser();

        // Set up the Parse query to use in the adapter
        ParseQueryAdapter.QueryFactory<JobListing> factory = new ParseQueryAdapter.QueryFactory<JobListing>() {
            public ParseQuery<JobListing> create() {
                ParseQuery<JobListing> query = JobListing.getQuery();
//                query.orderByAscending("title");
//                query.whereEqualTo("isDraft", false);
                query.fromLocalDatastore();
                return query;
            }
        };

//        parseQueryAdapter = new ParseQueryAdapter<JobListing>(getBaseContext(), factory);
        popularListAdapter = new popularListAdapter(this, factory);

        lv = (ListView) findViewById(R.id.listView);
//        lv.setAdapter(parseQueryAdapter);
        lv.setAdapter(popularListAdapter);

        loadFromParse();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                JobListing jobListing = (JobListing) popularListAdapter.getItem(position);

                ParseAnalytics.trackEventInBackground("Open JobListItem");
                jobListing.increment("open");
                jobListing.saveEventually();

                Intent intent = new Intent(view.getContext(), JobListItem.class);
                intent.putExtra("selectedId", jobListing.getObjectId());
                startActivity(intent);

            }
        });

    }


    private class popularListAdapter extends ParseQueryAdapter<JobListing> {

        public popularListAdapter(Context context, QueryFactory<JobListing> queryFactory) {
            super(context, queryFactory);
        }

        @Override
        public View getItemView(JobListing tinyMap, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view == null) {
                inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.job_list_item, parent, false);
                holder = new ViewHolder();
                holder.title = (TextView) view
                        .findViewById(R.id.firstLine);
                holder.description = (TextView) view.findViewById(R.id.secondLine);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            TextView tinymapTitle = holder.title;
            tinymapTitle.setText(tinyMap.getTitle());
            TextView description = holder.description;
            description.setText(tinyMap.getDescription());
            if (tinyMap.isDraft()) {
                tinymapTitle.setTypeface(null, Typeface.ITALIC);
            } else {
                tinymapTitle.setTypeface(null, Typeface.NORMAL);
            }
            return view;
        }
    }

    private static class ViewHolder {
        TextView title;
        TextView description;
    }

    private void loadFromParse() {
        ParseQuery<JobListing> query = JobListing.getQuery();
//        query.whereEqualTo("isDraft", false);
        query.findInBackground(new FindCallback<JobListing>() {
            public void done(List<JobListing> jobListings, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground(jobListings,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
//                                            if (!isFinishing()) {
                                        popularListAdapter.loadObjects();
//                                            Log.i("popularListActivity", "after loadobjects" + popularListAdapter);
//                                            }
                                    } else {
                                        Log.e("popularListActivity", "Error pinning hashmaps: " + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("popularListActivity",
                            "loadFromParse: Error finding pinned hashmaps: "
                                    + e.getMessage());
                }
            }
        });
    }

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
