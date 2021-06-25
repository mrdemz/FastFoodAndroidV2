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
public class BagActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_bag);



        try {
            itemDataSource.open();
            itemList = itemDataSource.getItems();
            priceList = itemDataSource.getPrices();
            itemDataSource.close();
            recyclerView = findViewById(R.id.rvItemList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            ItemAdapter vaccineAdapter = new ItemAdapter(itemList, this);
            recyclerView.setAdapter(vaccineAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error retrieving items", Toast.LENGTH_LONG).show();
        }


        Bundle bundle = getIntent().getExtras();
        int index = bundle.getInt("index");
        String nameArr[] = getResources().getStringArray(R.array.name_array);
        String priceArr[] = getResources().getStringArray(R.array.price_array);


        TextView confirm = findViewById(R.id.addConfirmView);
        TextView subView = findViewById(R.id.subView);
        TextView taxView = findViewById(R.id.taxView);
        TextView totalView = findViewById(R.id.totalView);
        TextView itemCountView = findViewById(R.id.itemCountView);

        double subNum = 0;
        for(int i= 0 ; i < priceList.size(); i++){
            subNum += priceList.get(i);

        }
        double tax = subNum*0.025;
        double total = subNum + tax;



        String taxString = String.format("%.2f", tax);
        String totalString = String.format("%.2f", total);
        String subString = String.format("%.2f", subNum);



        confirm.setText("Would you like to add "+nameArr[index]+" to your bag?");
        subView.setText("$"+subString);
        taxView.setText("$"+taxString);
        totalView.setText("$"+totalString);
        itemCountView.setText(itemList.size()+" items");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.7));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y=-20;

        getWindow().setAttributes(params);
        selectedPrice = Double.parseDouble(priceArr[index]);
        selectedName = nameArr[index];
        checkout();
        initAddBag();










    }










    private void checkout() {
        Button checkoutButton = findViewById(R.id.popUpBagButton);
        checkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(BagActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });
    }


    public void refresh()
    {


        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);

    }














    private void initAddBag(){
        // declare the widget to use the listener
        Button saveButton = findViewById(R.id.popUpAddButton);
        // set a listener
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean wasSuccessful; //variable captures the return values of VaccineDataSource methods
                // and determines the operations that should be performed based on success or failure of the methods
                ItemDataSource dataSource = new ItemDataSource(BagActivity.this); //declare datasourse object
                items = new Item();
                items.setItemName(selectedName);
                items.setItemPrice(selectedPrice);




                try {

                    dataSource.open(); //open the database


                    wasSuccessful = dataSource.insertItem(items);
                    if (wasSuccessful) {
                        int newId = dataSource.getLastItemID();
                        items.setItemId(newId);
                        refresh();
                    }


                } catch (Exception e) {
                    wasSuccessful = false;
                }

                dataSource.close();

            }

        });
    }



    private void initHomeButton(){

        View icon = findViewById(R.id.homeIcon);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(BagActivity.this, MainActivity.class);

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

                Intent intent = new Intent(BagActivity.this, MenuActivity.class);

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

                Intent intent = new Intent(BagActivity.this, AboutActivity.class);

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

                Intent intent = new Intent(BagActivity.this, BagActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }

}