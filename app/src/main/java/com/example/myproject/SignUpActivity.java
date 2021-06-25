package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextFname, editTextLname, editTextPhoneNumber, editTextAddress,
            editTextEmail, editPassword, editRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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

        editTextFname = findViewById(R.id.editTextFname);
        editTextLname = findViewById(R.id.editTextLname);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextEmail = findViewById(R.id.editTextEmail);
        editPassword = findViewById(R.id.editPassword);
        editRePassword = findViewById(R.id.editRePassword);

        signUp();
        logIn();
    }

    public void signUp(){
        CustomerDatabaseHelper db = new CustomerDatabaseHelper(this);
        Button signupButton = findViewById(R.id.signupButton);
        signupButton.setOnClickListener(v -> {
            String fName, lName, phoneNumber, address, email, password, rePassword;
            fName = editTextFname.getText().toString();
            lName = editTextLname.getText().toString();
            phoneNumber = editTextPhoneNumber.getText().toString();
            address = editTextAddress.getText().toString();
            email = editTextEmail.getText().toString();
            password = editPassword.getText().toString();
            rePassword = editRePassword.getText().toString();

            CustomerData customerData = new CustomerData(-1, email,
                    phoneNumber, address, fName, lName, password, rePassword);

            if (fName.equals("") || lName.equals("") || phoneNumber.equals("") ||
                    address.equals("") || email.equals("") || password.equals("") ||
                    rePassword.equals("")){
                Toast.makeText(this, "Please Complete all Fields.", Toast.LENGTH_SHORT).show();
            } else {
                if (password.equals(rePassword)){
                    Boolean checkEmail = db.checkEmail(email);
                    if (!checkEmail){
                        boolean insert = db.save(customerData);
                        if (insert){
                            Toast.makeText(this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(this, "Email already exist", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(this, "Password Do Not Match.", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }

    public void logIn(){
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
            startActivity(intent);
        });
    }



}