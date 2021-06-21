package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
//coded by:Bell John Demetria
public class MenuActivity extends AppCompatActivity {
    View[] contentview = new View[10];
    String[] itemName = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initHomeButton();
        initMenuButton();
        initAboutButton();
        initBagButton();

        LinearLayout contents = findViewById(R.id.contents);
        LayoutInflater inflater = LayoutInflater.from(this);



        for (int i = 0;  i < 10; i++){
            int num = i+1;
            String uri = "@drawable/item"+Integer.toString(num);
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            contentview[i] = inflater.inflate(R.layout.menu_image, contents, false);
            ImageView imageView = contentview[i].findViewById(R.id.picture);
            Drawable res = getResources().getDrawable(imageResource);
            imageView.setImageDrawable(res);
            contents.addView(contentview[i]);

        }
        for (int i = 0;  i < 10; i++){
            View icon = contentview[i];

            icon.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    Intent intent = new Intent(MenuActivity.this, BagPopUp.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);

                }

            });

        }


    }


    private void initHomeButton(){

        View icon = findViewById(R.id.homeIcon);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, MainActivity.class);

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

                Intent intent = new Intent(MenuActivity.this, MenuActivity.class);

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

                Intent intent = new Intent(MenuActivity.this, AboutActivity.class);

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

                Intent intent = new Intent(MenuActivity.this, BagActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }
}