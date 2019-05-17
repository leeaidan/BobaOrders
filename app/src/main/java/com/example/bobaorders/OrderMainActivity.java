package com.example.bobaorders;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
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

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class OrderMainActivity extends AppCompatActivity {
    private RecyclerView menuList;
    private Button submit;
    private LinearLayoutManager llm;
    private TextView owo;
    FirebaseRecyclerAdapter adapter;
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


        menuList =  findViewById(R.id.Menu_List);
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
        FirebaseRecyclerOptions<MenuDisplayBind> options = new FirebaseRecyclerOptions.Builder<MenuDisplayBind>().setQuery(query, new SnapshotParser<MenuDisplayBind>() {
                    @NonNull
                    @Override
                    public MenuDisplayBind parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new MenuDisplayBind(snapshot.child("name").getValue().toString(), snapshot.child("price").getValue().toString());
                    }
                })
                .build();
                adapter = new FirebaseRecyclerAdapter<MenuDisplayBind, MenuHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MenuHolder menuHolder, final int i, @NonNull MenuDisplayBind menuDisplayBind) {
                        menuHolder.setTxtTitle(menuDisplayBind.getName());
                        menuHolder.setTxtPrice(menuDisplayBind.getPrice());

                        menuHolder.root.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(OrderMainActivity.this, String.valueOf(i), Toast.LENGTH_LONG);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_menu, parent, false);
                        return new MenuHolder(view);
                    }
                };
        menuList.setAdapter(adapter);

    }


    public class MenuHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView mTitle;
        public TextView mPrice;


        public MenuHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            mTitle = itemView.findViewById(R.id.item_title);
            mPrice = itemView.findViewById(R.id.item_price);
        }


        public void setTxtTitle(String str) {
            mTitle.setText(str);

        }

        public void setTxtPrice(String str) {
            mPrice.setText(str);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}

