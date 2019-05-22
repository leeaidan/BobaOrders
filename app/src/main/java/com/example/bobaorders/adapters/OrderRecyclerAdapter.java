package com.example.bobaorders.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bobaorders.Drink;
import com.example.bobaorders.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;

public class OrderRecyclerAdapter extends FirebaseRecyclerAdapter<Drink, OrderRecyclerAdapter.MenuHolder> {
    private OnItemClickListener listener;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderRecyclerAdapter(@NonNull FirebaseRecyclerOptions<Drink> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MenuHolder menuHolder, int i, @NonNull Drink drink) {
        menuHolder.setTxtTitle(drink.getName());
        menuHolder.setTxtPrice(drink.getPrice());
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_menu, parent, false);
        return new OrderRecyclerAdapter.MenuHolder(view);
    }

    public class MenuHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView mTitle;
        public TextView mPrice;

        public MenuHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            mTitle = itemView.findViewById(R.id.item_title);
            mPrice = itemView.findViewById(R.id.item_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }

        public void setTxtPrice(String str) {
            mPrice.setText(str);
        }

        public void setTxtTitle(String str) {
            mTitle.setText(str);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DataSnapshot snapshot, int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
