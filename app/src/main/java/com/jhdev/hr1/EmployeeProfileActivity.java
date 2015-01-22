package com.jhdev.hr1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;


public class EmployeeProfileActivity extends ActionBarActivity {

    ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        //todo Check if User is logged in. If not logged in, redirect to login/signup page.

        //todo Check if User has a profile. If do not, prompt to fill up details?

        currentUser = ParseUser.getCurrentUser();

    }





}
