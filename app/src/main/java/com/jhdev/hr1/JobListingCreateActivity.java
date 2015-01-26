package com.jhdev.hr1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;

public class JobListingCreateActivity extends ActionBarActivity {

    Button buttonSave, buttonCancel;
    EditText editTextTitle, editTextDescription, editTextLocation, editTextSalary, editTextDate;
    private ParseUser currentUser;
    JobListing jobListing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listing_create);

        currentUser = ParseUser.getCurrentUser();
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextSalary = (EditText) findViewById(R.id.editTextSalary);
        editTextDate = (EditText) findViewById(R.id.editTextDate);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveItem();
            }
        });


    }

    public void saveItem() {

        //todo check for blanks.

        jobListing = new JobListing();
        jobListing.setTitle(editTextTitle.getText().toString());
        jobListing.setDescription(editTextDescription.getText().toString());
        jobListing.setLister(currentUser);
        jobListing.setStatus("active");
        jobListing.saveEventually();

        finish();
    }


}
