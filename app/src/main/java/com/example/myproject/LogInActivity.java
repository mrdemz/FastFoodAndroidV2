package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    private EditText emailLogin, passwordLogin;
    Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.8));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y=-20;

        getWindow().setAttributes(params);

        logIn = findViewById(R.id.buttonForLogin);
        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);

        login();
    }

    public void login(){
        CustomerDatabaseHelper db = new CustomerDatabaseHelper(this);
        logIn.setOnClickListener(v -> {
            String email = emailLogin.getText().toString();
            String password = passwordLogin.getText().toString();

            if (emailLogin.equals("") || passwordLogin.equals("")){
                Toast.makeText(this, "Complete all fields", Toast.LENGTH_SHORT).show();
            }else{
                Boolean checkEmailPassword = db.checkEmailPassword(email, password);
                if (checkEmailPassword){
                    Toast.makeText(this, "Log in Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}