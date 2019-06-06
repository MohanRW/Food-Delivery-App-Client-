package com.warriorprod.fooddeliveryapp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.warriorprod.fooddeliveryapp.Interface.ItemCLickListener;
import com.warriorprod.fooddeliveryapp.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemCLickListener itemCLickListener;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMenuName = (TextView)itemView.findViewById(R.id.menu_item);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);
    }
    public void setItemCLickListener(ItemCLickListener itemCLickListener){
        this.itemCLickListener=itemCLickListener;
    }

    @Override
    public void onClick(View v) {
        itemCLickListener.onClick(v,getAdapterPosition(),false);

    }
}
