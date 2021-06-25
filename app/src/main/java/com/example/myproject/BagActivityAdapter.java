package com.example.myproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class BagActivityAdapter extends RecyclerView.Adapter {



    ArrayList<Double> priceList;
    private ArrayList<Item> itemData;
    private View.OnClickListener mOnItemClickListener;
    /*Two variables added to make to hold the delete status of the adapter and the activity(context)
     * that the adapter is operating in*/
    private boolean isDeleting;
    private Context parentContext;
    private BagPopUp activity;







    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewItem;
        public TextView textViewPrice;
        public Button deleteButton;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewItem = itemView.findViewById(R.id.textItemName);
            textViewPrice = itemView.findViewById(R.id.textItemPrice);
            deleteButton = itemView.findViewById(R.id.itemRemButton);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

        }


        public TextView getTextViewVaccine() {
            return textViewItem;
        }
        public TextView getTextViewVaccineCode() {
            return textViewPrice;
        }
        public Button getDeleteButton() {
            return deleteButton;
        }

    }







    public BagActivityAdapter(ArrayList<Item> arrayList, Context context) {
        itemData = arrayList;
        parentContext = context;
    }

    public void setmOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.getTextViewVaccine().setText(itemData.get(position).getItemName());
        itemViewHolder.getTextViewVaccineCode().setText(Double.toString(itemData.get(position).getItemPrice()));



        itemViewHolder.getDeleteButton().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deleteItem(position);
                ((Bag_Activity)parentContext).refresh();

            }
        });



    }



    public void deleteItem(int position) {
        Item item = itemData.get(position);
        ItemDataSource itemDataSource = new ItemDataSource(parentContext);
        try {
            itemDataSource.open();
            boolean didDelete = itemDataSource.deleteItem(item.getItemId());
            itemDataSource.close();
            if(didDelete) {
                itemData.remove(position);
                notifyDataSetChanged();
            }
            else {
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }

        }
        catch (Exception e) {
            Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
        }
    }


    public void methodStartActivity(Context context) {

    }






    @Override
    public int getItemCount () {
        return itemData.size();
    }

}
