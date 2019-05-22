package com.example.bobaorders.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bobaorders.R;


public class MainActivity extends AppCompatActivity {
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        tb.setNavigationIcon(R.drawable.ic_launcher_foreground);
        getSupportActionBar().setTitle("Boba;Orders");


        img = (ImageView) findViewById(R.id.bobalogo);
//        getScaleLogo(img);

        Button btnOrder = (Button) findViewById(R.id.btnOrder);
        Button btnRetrieve = (Button) findViewById(R.id.btnRetrieve);

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(goToLoginActivity);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToOrdering = new Intent(getApplicationContext(), OrderMainActivity.class);
                startActivity(goToOrdering);
            }
        });


    }

    private void getScaleLogo(ImageView img){

        int logo = R.drawable.bobalogo;
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options= new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), logo, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if(imgWidth > screenWidth){
            int ratio = Math.round((float) imgWidth / (float) screenWidth);
            options.inSampleSize = ratio;
        }

        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), logo, options);
        img.setImageBitmap(scaledImg);



    }
}
