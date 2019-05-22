package com.example.bobaorders.activities;

import android.os.Bundle;

import com.example.bobaorders.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SubmitOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView displayItem;
    TextView drinkPrice;
    String getdrinkItem;
    String getDrinkPrice;
    Spinner sweetnessSpinner;
    Spinner iceSpinner;
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
        iceSpinner = findViewById(R.id.iceSelector);


        getdrinkItem = getIntent().getExtras().getString("com.example.activities.DRINK_NAME");
        getDrinkPrice = getIntent().getExtras().getString("com.example.activities.DRINK_PRICE");

        displayItem.setText("Selected: " + getdrinkItem);
        drinkPrice.setText("Subtotal: " + getDrinkPrice);

        ArrayAdapter<CharSequence> sweetnessAdapter = ArrayAdapter.createFromResource(this, R.array.sweetness_array, R.layout.support_simple_spinner_dropdown_item);
        sweetnessAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sweetnessSpinner.setAdapter(sweetnessAdapter);
        sweetnessSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> iceAdapter = ArrayAdapter.createFromResource(this, R.array.iceValues, R.layout.support_simple_spinner_dropdown_item);
        iceAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        iceSpinner.setAdapter(iceAdapter);
        iceSpinner.setOnItemSelectedListener(this);








    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sugarLevel = parent.getItemAtPosition(position).toString();
        String iceLevel = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(),sugarLevel, Toast.LENGTH_LONG ).show();
        Toast.makeText(parent.getContext(), iceLevel, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
