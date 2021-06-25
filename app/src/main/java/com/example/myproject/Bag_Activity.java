package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//coded by: Bell John Demetria
public class Bag_Activity extends AppCompatActivity {
    private Item items;
    private double selectedPrice;
    private String selectedName;


    RecyclerView recyclerView;
    ArrayList<Item> itemList;
    ArrayList<Double> priceList;


    ItemDataSource itemDataSource = new ItemDataSource(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_final);



        try {
            itemDataSource.open();
            itemList = itemDataSource.getItems();
            priceList = itemDataSource.getPrices();
            itemDataSource.close();
            recyclerView = findViewById(R.id.bagRvView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            BagActivityAdapter vaccineAdapter = new BagActivityAdapter(itemList, this);
            recyclerView.setAdapter(vaccineAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error retrieving items", Toast.LENGTH_LONG).show();
        }






        TextView subView = findViewById(R.id.bagSubView);
        TextView taxView = findViewById(R.id.bagTaxView);
        TextView totalView = findViewById(R.id.bagTotalView);
        TextView itemCountView = findViewById(R.id.bagItemCount);

        double subNum = 0;
        for(int i= 0 ; i < priceList.size(); i++){
            subNum += priceList.get(i);

        }
        double tax = subNum*0.025;
        double total = subNum + tax;



        String taxString = String.format("%.2f", tax);
        String totalString = String.format("%.2f", total);
        String subString = String.format("%.2f", subNum);




        subView.setText("$"+subString);
        taxView.setText("$"+taxString);
        totalView.setText("$"+totalString);
        itemCountView.setText(itemList.size()+" items");








    initAboutButton();
    initBagButton();
    initHomeButton();
    initMenuButton();
    checkout();



    }



    private void initHomeButton(){

        View icon = findViewById(R.id.homeIcon);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(Bag_Activity.this, MainActivity.class);

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

                Intent intent = new Intent(Bag_Activity.this, MenuActivity.class);

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

                Intent intent = new Intent(Bag_Activity.this, AboutActivity.class);
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

                Intent intent = new Intent(Bag_Activity.this, Bag_Activity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }









    public void refresh()
    {


        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);

    }
    private void checkout() {
        Button checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(Bag_Activity.this, CheckoutActivity.class);
            startActivity(intent);
        });
    }




















}