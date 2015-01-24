package com.jhdev.hr1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ProfileEmployeeEdit extends ActionBarActivity {

    Button buttonSave, buttonCancel;
    private EditText editTextName, editTextDOB, editTextResume;
    private String name, DOB, resume;
    private ProfileEmployee profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile_edit);

        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        //check logged in ?
        //todo set Content from User's profile


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
//                Intent intent = new Intent(getBaseContext(), ProfileEmployeeEdit.class);
//                startActivity(intent);

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                editTextName = (EditText) findViewById(R.id.editTextName);
                editTextResume = (EditText) findViewById(R.id.editTextResume);
                name = editTextName.getText().toString();
                resume = editTextResume.getText().toString();

                Log.d("Profile Edit", "onsave" + name + resume);
                saveProfile();
            }
        });
    }

    //save the edits into the user's profile
    public void saveProfile() {



        finish();
    }

}
