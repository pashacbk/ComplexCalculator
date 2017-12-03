package com.interntask.calculator;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by manga on 03.12.2017.
 */

public class RegisterActivity extends AppCompatActivity {
    public static final String EXTRA_USERNAME = "Username";
    String mUsername = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText mUsernameText = (EditText) findViewById(R.id.username_text);

        Button registerButton = (Button) findViewById(R.id.register_btt);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUsernameText.getText().toString().trim().length() != 0) {
                    mUsername = mUsernameText.getText().toString();

                    Intent intent = new Intent(RegisterActivity.this, CalculatorActivity.class);
                    intent.putExtra(EXTRA_USERNAME, mUsername);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this,
                            R.string.usernameMsg,
                            Toast.LENGTH_SHORT).show();
                }
            }
        } );
    }


}
