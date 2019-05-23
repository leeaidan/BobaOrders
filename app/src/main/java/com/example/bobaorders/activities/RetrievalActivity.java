package com.example.bobaorders.activities;

import android.os.Bundle;

import com.example.bobaorders.Drink;
import com.example.bobaorders.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RetrievalActivity extends AppCompatActivity {
    private Query query;
    private RecyclerView order_list;
    private FirebaseRecyclerAdapter adapter;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retrieval);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() !=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        Snackbar loginSuccess = Snackbar.make(findViewById(R.id.loginSuccess), "Login Successful", Snackbar.LENGTH_LONG);
        loginSuccess.show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // Recycler View Code
        order_list = findViewById(R.id.Order_List);
        query = FirebaseDatabase.getInstance().getReference().child("orders").limitToLast(50);
        FirebaseRecyclerOptions<Drink> options = new FirebaseRecyclerOptions.Builder<Drink>().setQuery(query, new SnapshotParser<Drink>() {
            @NonNull
            @Override
            public Drink parseSnapshot(@NonNull DataSnapshot snapshot) {
                return snapshot.getValue(Drink.class);
            }
        }).build();

         adapter = new FirebaseRecyclerAdapter<Drink, OrderHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull OrderHolder orderHolder, int i, @NonNull Drink drink) {
                orderHolder.setDrinkName(drink.getName());
                Log.i("price", drink.getPrice());
                orderHolder.setPrice(drink.getPrice());
                orderHolder.setSweetness(drink.getSweetness());
                orderHolder.setIce(drink.getIce());
                orderHolder.setToppings(drink.getToppings());
                orderHolder.setDrinkName(drink.getName());
                orderHolder.setSubmitterName(drink.getSubmittername());

                //Toast.makeText(RetrievalActivity.this, "At position " + i + " is " + drink.getName() + " which costs " + drink.getPrice(), Toast.LENGTH_SHORT).show();

                //TODO: Bind the rest of the values (ice, sweetness, toppings) to the layout
            }

            @NonNull
            @Override
            public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_menu, parent, false);
                return new OrderHolder(view);
            }
        };

        order_list.setAdapter(adapter);

        llm = new LinearLayoutManager(this);
        order_list.setHasFixedSize(true);
        order_list.setLayoutManager(llm);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public class OrderHolder extends RecyclerView.ViewHolder {
        private LinearLayout leftCol;
        private TextView mDrinkName;
        private TextView mSweetness;
        private TextView mIce;
        private TextView mToppings;

        private LinearLayout rightCol;
        private TextView mPrice;
        private TextView mSubmitterName;

        public OrderHolder(@NonNull View itemView) {
            super(itemView);
            leftCol = itemView.findViewById(R.id.leftCol);
            mDrinkName = itemView.findViewById(R.id.drinkname);
            mSweetness = itemView.findViewById(R.id.sweetness);
            mIce = itemView.findViewById(R.id.ice);
            mToppings = itemView.findViewById(R.id.toppings);

            rightCol = itemView.findViewById(R.id.rightCol);
            mPrice = itemView.findViewById(R.id.item_price);
            mSubmitterName = itemView.findViewById(R.id.submitter_name);

        }

        public void setDrinkName(String s) {
            mDrinkName.setText(s);
        }
        public void setSweetness(String s) {
            mSweetness.setText(s);
        }
        public void setIce(String s) {
            mIce.setText(s);
        }
        public void setToppings(String s) {
            mToppings.setText(s);
        }
        public void setPrice(String s) {
            mPrice.setText(s);
        }
        public void setSubmitterName(String s) {
            mSubmitterName.setText(s);
        }

    }

    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
