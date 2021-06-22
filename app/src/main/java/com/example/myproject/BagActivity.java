package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//coded by: Bell John Demetria
public class BagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);
        initHomeButton();
        initMenuButton();
        initAboutButton();
        initBagButton();

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