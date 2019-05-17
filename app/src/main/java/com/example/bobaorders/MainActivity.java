package com.example.bobaorders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ImageView img;

    public static DatabaseReference getmDatabase() {
        return mDatabase;
    }

    private static DatabaseReference mDatabase;
    private static ChildEventListener drinkListener;
    private static ValueEventListener drinkSnapshot;
    private static final ArrayList<Drink> drinks = new ArrayList<Drink>();
    private static int drinkCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //FIREBASE STUFF
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query sortedKeys = mDatabase.child("drinkInventory").orderByKey();

        ValueEventListener drinkListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<DataSnapshot> data = (ArrayList<DataSnapshot>) makeCollection(dataSnapshot.getChildren());
                drinks.add(data.get(data.size()-1).getValue(Drink.class));
                drinkCounter++;

                Log.i("dataChangeTest", drinks.get(0).toString() + "DRINK LENGTH:" + drinks.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        sortedKeys.addValueEventListener(drinkListener);

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
                Intent goToOrdering = new Intent(getApplicationContext(),OrderMainActivity.class);
                startActivity(goToOrdering);
            }
        });

    }

    public static ArrayList<Drink> getCompiledDrinks(){
        return drinks;
    }

    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

    public static ArrayList<Drink> getDrinks(){
        return drinks;
    }

    public static void addDrinkToDatabase(String drinkID, Drink d){
        mDatabase.child("drinkInventory").child(drinkID).setValue(d);
    }

    public static String appendDrinkToDatabase(Drink d){
        mDatabase.child("drinkInventory").push().setValue(d);
        return mDatabase.child("drinkInventory").push().getKey();
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
