package com.dataflair.musicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dataflair.musicplayer.R;

public class SignUpActivity extends AppCompatActivity {

    EditText name, email, password;
    Button back, submit;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);


        preferences = getSharedPreferences("userdata", 0);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameok = name.getText().toString();
                String emailok = email.getText().toString();
                String passwordok = password.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("email", emailok);
                editor.putString("password", passwordok);
                editor.commit();

                if (error()) {
                    Intent i1 = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(i1);
                    finish();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean error() {
        if (name.getText().toString().length() == 0) {
            Toast.makeText(SignUpActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.getText().toString().length() == 0) {
            Toast.makeText(SignUpActivity.this, "please enter email id", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().length() == 0) {
            Toast.makeText(SignUpActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().length() != 8) {
            Toast.makeText(SignUpActivity.this, "please enter 8 digit password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}