package com.example.bobaorders;


import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MenuHolder extends RecyclerView.ViewHolder {
    public LinearLayout root;
    public TextView mTitle;
    public TextView mPrice;



    public MenuHolder(View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        mTitle = itemView.findViewById(R.id.item_title);
        mPrice = itemView.findViewById(R.id.item_price);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, getAdapterPosition());
                Log.d(TAG, "onItemClick: ");



            }
        });
    }
    private OnItemClickListener listener;

    public void setTxtTitle(String str) {
        mTitle.setText(str);

    }

    public void setTxtPrice(String str) {
        mPrice.setText(str);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}

