package com.jhdev.hr1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseQuery;
import com.parse.ParseUser;


public class EmployeeProfileActivity extends ActionBarActivity {

    ParseUser currentUser;
    Button editButton;
    private ProfileEmployee profileEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);


        currentUser = ParseUser.getCurrentUser();
        //todo Check if User is logged in. If not logged in, redirect to login/signup page.

        //todo need to debug this bit here. doesn't seem to be working
        Log.d("hr1", "hasProfile" + currentUser);
        Log.d("hr1", "hasProfile" + !currentUser.getBoolean("hasProfile"));
        if (!currentUser.getBoolean("hasProfile")) {
            ProfileEmployee profile = new ProfileEmployee();
            profile.setUser(currentUser);
            profile.saveEventually();

            currentUser.put("hasProfile", true);
            currentUser.saveEventually();
        }
        //todo Check if User has a profile. If do not, prompt to fill up details?


        editButton = (Button) findViewById(R.id.button1);

        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), ProfileEmployeeEdit.class);
                startActivity(intent);

            }
        });

        if (currentUser != null) {
            getProfile();
        } else {
            Toast.makeText(this, "not logged in", Toast.LENGTH_LONG).show();
        }

    }

    public void getProfile() {
        //todo get Profile. save locally too.



    }


}
