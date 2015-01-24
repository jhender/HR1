package com.jhdev.hr1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileEmployeeEdit extends ActionBarActivity {

    Button buttonSave, buttonCancel;
    private EditText editText1, editText2;

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

                saveProfile();
            }
        });
    }

    //save the edits into the user's profile
    public void saveProfile() {
        finish();
    }

}
