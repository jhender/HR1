package com.jhdev.hr1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ProfileEmployeeEdit extends ActionBarActivity {

    Button buttonSave;
    Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile_edit);

        buttonCancel = (Button) findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
//                Intent intent = new Intent(getBaseContext(), ProfileEmployeeEdit.class);
//                startActivity(intent);

            }
        });
    }


}
