package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {

    private CustomerData customerData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        initHomeButton();
        initMenuButton();
        initBagButton();
        initAboutButton();
        placeOrder();
    }
    private void  placeOrder(){

        Button button = findViewById(R.id.orderButton);
        button.setOnClickListener(v -> {

            EditText checkoutFname = findViewById(R.id.checkoutFname);
            EditText checkoutLname = findViewById(R.id.checkoutLname);
            EditText checkoutEmail = findViewById(R.id.checkoutEmail);
            EditText checkoutPhone = findViewById(R.id.checkoutPhone);
            EditText checkoutStreet = findViewById(R.id.checkoutStreet);
            EditText checkoutCity = findViewById(R.id.checkoutCity);
            EditText checkoutState = findViewById(R.id.checkoutState);
            EditText checkoutZip = findViewById(R.id.checkoutZip);

            String firstName = checkoutFname.getText().toString();
            String lastName = checkoutLname.getText().toString();
            String email = checkoutEmail.getText().toString();
            String phoneNumber = checkoutPhone.getText().toString();
            String street = checkoutStreet.getText().toString();
            String city = checkoutCity.getText().toString();
            String state = checkoutState.getText().toString();
            String zip = checkoutZip.getText().toString();
            String address = String.format("%s %s %s %s", street, city, state, zip);

            if (firstName.equals("") || lastName.equals("") || email.equals("") || phoneNumber.equals("")){
                Toast.makeText(this, "Please fill up the information above.", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    customerData = new CustomerData(-1, email,
                            phoneNumber, address, firstName, lastName);
                } catch (Exception e){
                    Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
                }
                ItemDataSource db = new ItemDataSource(this);

                boolean insert = db.insertCustomer(customerData);
                if (insert){
                    Intent intent = new Intent(CheckoutActivity.this, OrderActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Something went wrong with your order, please try again.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void initHomeButton(){

        View icon = findViewById(R.id.homeIcon);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }

    private void initMenuButton(){

        View icon = findViewById(R.id.menuIcon);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(CheckoutActivity.this, MenuActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);


            }

        });

    }

    private void initAboutButton(){

        View icon = findViewById(R.id.aboutIcon);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(CheckoutActivity.this, AboutActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }
    private void initBagButton(){

        View icon = findViewById(R.id.bagIcon);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(CheckoutActivity.this, Bag_Activity.class);

                startActivity(intent);

            }

        });
    }
}