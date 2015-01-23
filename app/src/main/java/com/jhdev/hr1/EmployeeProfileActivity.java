package com.jhdev.hr1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;


public class EmployeeProfileActivity extends ActionBarActivity {

    ParseUser currentUser;
    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        //todo Check if User is logged in. If not logged in, redirect to login/signup page.

        //todo Check if User has a profile. If do not, prompt to fill up details?

        currentUser = ParseUser.getCurrentUser();
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
