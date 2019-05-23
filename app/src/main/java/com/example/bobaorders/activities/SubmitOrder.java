package com.example.bobaorders.activities;

import android.content.Intent;
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

public class SubmitOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView displayItem, drinkPrice, nameField;
    String getdrinkItem, getDrinkPrice, finalIce, finalSweetness, finalToppings;
    Spinner sweetnessSpinner, iceSpinner;
    CheckBox checkTapiocaP, checkAgarP, checkCoconutJ, checkRainbowJ, checkCoffeeJ, checkGrassJ, checkPudding, checkRedBean, checkTopCream;


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
                map.put("submittername", nameField.getText().toString());
                map.put("name", getdrinkItem);
                map.put("price", getDrinkPrice);
                map.put("ice", finalIce);
                map.put("sweetness", finalSweetness);
                map.put("toppings", finalToppings);

                //All I did was add the rest of the fields, you need to complete it cuz i dunno how to get the data from this code
                //Note that the toppings are an array, check Drink class for more info on how it works
                databaseReference.setValue(map);
                Snackbar.make(view, "Order Submitted", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Toast.makeText(SubmitOrder.this, "Thank you for ordering " + nameField.getText().toString() + "! You order for " + getdrinkItem + " has been placed", Toast.LENGTH_LONG).show();
                Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backToMain);
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        displayItem = findViewById(R.id.drinkName);
        drinkPrice = findViewById(R.id.price);
        sweetnessSpinner = findViewById(R.id.sweetnessSelector);
        iceSpinner = findViewById(R.id.iceSelector);
        nameField = findViewById(R.id.nameField);
        checkTapiocaP = findViewById(R.id.checkTapiocaP);
        checkAgarP = findViewById(R.id.checkAgarP);
        checkCoconutJ = findViewById(R.id.checkCoconutJ);
        checkRainbowJ = findViewById(R.id.checkRainbowJ);
        checkCoffeeJ = findViewById(R.id.checkCoffeeJ);
        checkGrassJ = findViewById(R.id.checkGrassJ);
        checkPudding = findViewById(R.id.checkPudding);
        checkRedBean = findViewById(R.id.checkRedBean);
        checkTopCream = findViewById(R.id.checkTopCream);


        finalToppings = "";



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


    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()){
            case R.id.checkTapiocaP:
                if(checked){
                    finalToppings += "Tapioca, ";
                }
                break;
            case R.id.checkAgarP:
                if(checked){
                    finalToppings += "Agar, ";
                }
                break;
            case R.id.checkCoconutJ:
                if(checked){
                    finalToppings += "Coconut Jelly, ";
                }
                break;
            case R.id.checkRainbowJ:
                if(checked){
                    finalToppings += "Rainbow Jelly, ";
                }
                break;
            case R.id.checkCoffeeJ:
                if(checked){
                    finalToppings += "Coffee Jelly, ";
                }
                break;
            case R.id.checkGrassJ:
                if(checked){
                    finalToppings += "Grass Jelly, ";
                }
                break;
            case R.id.checkPudding:
                if(checked){
                    finalToppings += "Pudding, ";
                }
                break;
            case R.id.checkRedBean:
                if(checked){
                    finalToppings += "Red Bean, ";
                }
                break;
            case R.id.checkTopCream:
                if(checked){
                    finalToppings += "Top Cream, ";
                }
                break;
        }




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
