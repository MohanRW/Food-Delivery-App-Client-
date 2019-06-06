package com.warriorprod.fooddeliveryapp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.warriorprod.fooddeliveryapp.Interface.ItemCLickListener;
import com.warriorprod.fooddeliveryapp.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView food_name;
    public ImageView food_image;

    private ItemCLickListener itemCLickListener;

    public void setItemCLickListener(ItemCLickListener itemCLickListener) {
        this.itemCLickListener = itemCLickListener;
    }


    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        food_name = (TextView)itemView.findViewById(R.id.food_item);
        food_image = (ImageView)itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemCLickListener.onClick(v,getAdapterPosition(),false);

    }


}
