package com.example.bobaorders.activities;

import android.os.Bundle;

import com.example.bobaorders.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SubmitOrder extends AppCompatActivity {
    TextView displayItem;
    TextView drinkPrice;
    String getdrinkItem;
    String getDrinkPrice;
    Spinner sweetnessSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        displayItem = findViewById(R.id.drinkName);
        drinkPrice = findViewById(R.id.totalPrice);
        sweetnessSpinner = findViewById(R.id.sweetnessSelector);

        getdrinkItem = getIntent().getExtras().getString("com.example.activities.DRINK_NAME");
        getDrinkPrice = getIntent().getExtras().getString("com.example.activities.DRINK_PRICE");

        displayItem.setText("Selected: " + getdrinkItem);
        drinkPrice.setText("Subtotal: " + getDrinkPrice);

        ArrayAdapter<CharSequence> sweetnessAdapter = ArrayAdapter.createFromResource(this, R.array.sweetness_array, R.layout.content_submit_order);
        sweetnessAdapter.setDropDownViewResource(R.layout.content_submit_order);
        sweetnessSpinner.setAdapter(sweetnessAdapter);





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
