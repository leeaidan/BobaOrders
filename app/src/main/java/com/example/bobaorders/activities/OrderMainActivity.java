package com.example.bobaorders.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.bobaorders.Drink;
import com.example.bobaorders.adapters.OrderRecyclerAdapter;
import com.example.bobaorders.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Map;


public class OrderMainActivity extends AppCompatActivity {
    private RecyclerView menuList;
    private Button submit;
    private LinearLayoutManager llm;
    private TextView owo;
    OrderRecyclerAdapter adapter;
    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Make Your Orders");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        menuList = findViewById(R.id.Menu_List);
        submit = findViewById(R.id.buttonSubmit);
        owo = findViewById(R.id.editText);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("drinkList").push();
                Map<String, Object> map = new HashMap<>();
                map.put("name", owo.getText().toString());
                map.put("price", "$3.75");

                databaseReference.setValue(map);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        llm = new LinearLayoutManager(this);
        menuList.setHasFixedSize(true);
        menuList.setLayoutManager(llm);
        fetchData();


    }

    private void fetchData() {

        query = FirebaseDatabase.getInstance().getReference().child("drinkList").limitToLast(50);
        FirebaseRecyclerOptions<Drink> options = new FirebaseRecyclerOptions.Builder<Drink>().setQuery(query, new SnapshotParser<Drink>() {
            @NonNull
            @Override
            public Drink parseSnapshot(@NonNull DataSnapshot snapshot) {
                return new Drink(snapshot.child("name").getValue().toString(), snapshot.child("price").getValue().toString());
            }
        })
                .build();
        adapter = new OrderRecyclerAdapter(options);
        menuList.setAdapter(adapter);

        adapter.setOnItemClickListener(new OrderRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataSnapshot snapshot, int position) {
                Drink drink = snapshot.getValue(Drink.class);
                Log.i("on click", "At position " + position + " is " + drink.getName());
                Toast.makeText(OrderMainActivity.this, "At position " + position + " is " + drink.getName() + " which costs " + drink.getPrice(), Toast.LENGTH_SHORT).show();

                Intent goToSubmission = new Intent(getApplicationContext(), SubmitOrder.class);
                goToSubmission.putExtra("com.example.activities.DRINK_NAME", drink.getName());
                goToSubmission.putExtra("com.example.activities.DRINK_PRICE", drink.getPrice());

                startActivity(goToSubmission);
            }
        });

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
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}

