package com.jhdev.hr1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class JobListItem extends ActionBarActivity {

    JobListing jobListing;

    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8;

    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list_item);

        textView1 = (TextView) findViewById(R.id.textViewFullName);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textViewNationality);
        textView8 = (TextView) findViewById(R.id.textViewCategory);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        String incomingId = getIntent().getStringExtra("selectedId");

        getItem(incomingId);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "button press", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "button press", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //retrieve item from local datastore
    private void getItem(String id) {

        ParseQuery<JobListing> query = JobListing.getQuery();
        query.fromLocalDatastore();
        query.getInBackground(id, new GetCallback<JobListing>() {
            @Override

            public void done(JobListing object, ParseException e) {
                if (!isFinishing()) {
                    jobListing = object;

                    if (object.getCategory() != null) {
                        textView8.setText(object.getCategory());
                    }

                    //show title
                    textView1.setText(object.getTitle());

                    //show main description
                    if (object.getDescription() != null) {
                        textView2.setText(object.getDescription());
                    }

                    //show availability
                    if (object.getStatus().equals("active")) {
                        textView4.setText("Still Available");
                    }

                    textView5.setText("Location: " + object.getLocation());

                    //show Time since
                    Long sec = object.getUpdatedAt().getTime();
                    CharSequence time = DateUtils.getRelativeTimeSpanString(sec);
                    textView6.setText("Listed By X " + time);

                    //show open number of times
                    textView7.setText("Viewed " + object.getOpen() + " times.");


                }
            }
        });
    }
}

