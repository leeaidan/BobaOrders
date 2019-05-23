package com.example.bobaorders.activities;

import android.os.Bundle;

import com.example.bobaorders.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SubmitOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView displayItem;
    TextView drinkPrice;
    String getdrinkItem;
    String getDrinkPrice;
    String finalIce;
    String finalSweetness;
    Spinner sweetnessSpinner;
    Spinner iceSpinner;
    TextView nameField;

    Button submit;

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
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("orders").push();
                Map<String, Object> map = new HashMap<>();
                map.put("submittername", nameField.toString());
                map.put("drink", getdrinkItem);
                map.put("price", getDrinkPrice);
                map.put("ice", finalIce);
                map.put("sweetness", finalSweetness);

                //map.put("toppings", TOPPINGARRAY);

                //All I did was add the rest of the fields, you need to complete it cuz i dunno how to get the data from this code
                //Note that the toppings are an array, check Drink class for more info on how it works
                databaseReference.setValue(map);
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
        nameField = findViewById(R.id.nameField);


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


        //TODO: This is your job Aidan xDDDDDD
        //So I actually have no idea how Buttons work so you're gonna need to check my work on this, I just copy pasted from OrderMainActivity
    //    submit = findViewById(R.id.buttonSubmit);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getId() == R.id.iceSelector) {
            finalIce = parent.getItemAtPosition(position).toString();
        } else if(parent.getId() == R.id.sweetnessSelector){
            finalSweetness = parent.getItemAtPosition(position).toString();
        }

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
