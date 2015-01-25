package com.jhdev.hr1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class EmployeeProfileActivity extends ActionBarActivity {

    ParseUser currentUser;
    Button editButton;
    private ProfileEmployee profileEmployee;
    TextView tv1,tv2,tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        tv1 = (TextView) findViewById(R.id.textViewResume);
        tv2 = (TextView) findViewById(R.id.textViewFullName);
        tv3 = (TextView) findViewById(R.id.textViewDOB);
        tv4 = (TextView) findViewById(R.id.textViewNationality);


        currentUser = ParseUser.getCurrentUser();
        //todo Check if User is logged in. If not logged in, redirect to login/signup page.

        //todo need to debug this bit here. doesn't seem to be working
        Log.d("hr1", "hasProfile" + currentUser);
        Log.d("hr1", "hasProfile" + !currentUser.getBoolean("hasProfile"));
        if (!currentUser.getBoolean("hasProfile")) {
            ProfileEmployee profile = new ProfileEmployee();
            profile.setUser(currentUser);
            profile.saveEventually();
            profileEmployee = profile;

            currentUser.put("hasProfile", true);
            currentUser.saveEventually();
        }
        //todo Check if User has a profile. If do not, prompt to fill up details?


        editButton = (Button) findViewById(R.id.button1);

        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), ProfileEmployeeEdit.class);
                //todo bundle

                startActivity(intent);

            }
        });

        if (currentUser != null) {
            getProfile();
        } else {
            getProfile();
            Toast.makeText(this, "not logged in", Toast.LENGTH_LONG).show();
        }

    }

    public void getProfile() {
        //todo save locally too. pinInBackground

        ParseQuery<ProfileEmployee> query = ParseQuery.getQuery("ProfileEmployee");
        query.whereEqualTo("user", currentUser);
        query.getFirstInBackground(new GetCallback<ProfileEmployee>() {
            @Override
            public void done(ProfileEmployee object, ParseException e) {
                if (e == null) {
                    profileEmployee = object;
                    Log.d("Profile get", "found" + profileEmployee.getResume());
                    tv1.setText(profileEmployee.getResume());
                    tv2.setText(profileEmployee.getFullName());
                    tv3.setText(profileEmployee.getBirthday().toString());
                    tv4.setText(profileEmployee.getNationality());
                } else {
                    Log.e("Profile get", e.toString());
                }
            }
        });



    }


}
