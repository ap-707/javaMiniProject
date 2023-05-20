package com.dataflair.musicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dataflair.musicplayer.MainActivity;
import com.dataflair.musicplayer.R;

public class LoginActivity extends AppCompatActivity {

    EditText email, password ;
    Button login, sign_up ;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email= findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        sign_up = findViewById(R.id.sign_up);

        preferences = getSharedPreferences("userdata",0);

        login.setOnClickListener(view -> {

            String loginemailok = email.getText().toString();
            String loginpasswordok = password.getText().toString();

            String sing_upEmail = preferences.getString("email","");
            String sing_upPassword = preferences.getString("password","");

            if(error()) {
                if (loginemailok.equals(sing_upEmail) && loginpasswordok.equals(sing_upPassword)) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "invalidate email id and password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sign_up.setOnClickListener(view -> {
            Intent i2 = new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(i2);
        });

    }
    private boolean error() {

        if (email.getText().toString().length() == 0) {
            Toast.makeText(LoginActivity.this, "please enter email id", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().length() == 0) {
            Toast.makeText(LoginActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().length() != 8) {
            Toast.makeText(LoginActivity.this, "please enter 8 digit password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}