package com.jhdev.hr1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProfileEmployeeEdit extends ActionBarActivity
    implements DatePickerDialog.OnDateSetListener{

    Button buttonSave, buttonCancel;
    private EditText editTextName, editTextDOB, editTextResume;
    private String name, resume;
    private ParseUser currentUser;
    private ProfileEmployee profileEmployee;
    private Date DOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile_edit);

        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        currentUser = ParseUser.getCurrentUser();
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextResume = (EditText) findViewById(R.id.editTextResume);
        editTextDOB = (EditText) findViewById(R.id.editTextDate);

        //set content from Profile. in future get bundle or load local, save on load time.
        getProfile();

        editTextDOB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(ProfileEmployeeEdit.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editTextDOB.setText("new" + year + monthOfYear + dayOfMonth);
//                                DOB = new Date(year, monthOfYear, dayOfMonth);
                                Calendar calendar = new GregorianCalendar();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                DOB = new Date(calendar.getTimeInMillis());
                            }
                        }
                        //todo set saved date please
                        ,2014, 12, 25);
                dialog.show();
            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
//                Intent intent = new Intent(getBaseContext(), ProfileEmployeeEdit.class);
//                startActivity(intent);

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                name = editTextName.getText().toString();
                resume = editTextResume.getText().toString();


                Log.d("Profile Edit", "onsave" + name + resume);
                saveProfile();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int mth, int day) {
        editTextDOB.setText(year + mth + day);
        Log.d("Date", "set to:" + year + mth + day);
    }

    public void getProfile() {
        ParseQuery<ProfileEmployee> query = ParseQuery.getQuery("ProfileEmployee");
        query.whereEqualTo("user", currentUser);
        query.getFirstInBackground(new GetCallback<ProfileEmployee>() {
            @Override
            public void done(ProfileEmployee object, ParseException e) {
                if (e == null) {
                    profileEmployee = object;
                    Log.d("Profile get", "found" + profileEmployee.getResume());
                    editTextResume.setText(profileEmployee.getResume());
                    editTextName.setText(profileEmployee.getFullName());
                } else {
                    Log.e("Profile get", e.toString());
                }
            }
        });
    }

    public void saveProfile() {

        profileEmployee.setResume(resume);
        profileEmployee.setFullName(name);
        profileEmployee.setBirthday(DOB);
        //todo save the date
        profileEmployee.saveEventually();

        finish();
    }



}
