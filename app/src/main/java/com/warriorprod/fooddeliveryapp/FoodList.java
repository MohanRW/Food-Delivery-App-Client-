package com.warriorprod.fooddeliveryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;
import com.warriorprod.fooddeliveryapp.Interface.ItemCLickListener;
import com.warriorprod.fooddeliveryapp.Model.Common;
import com.warriorprod.fooddeliveryapp.Model.Food;
import com.warriorprod.fooddeliveryapp.ViewHolder.FoodViewHolder;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter1;

    String categoryId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food");

        recyclerView=(RecyclerView)findViewById(R.id.recycle_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent
        if(getIntent()!=null){
            categoryId= getIntent().getStringExtra("CategoryId");
                    }

        if (!categoryId.isEmpty()&&categoryId!=null)
        {
            if (Common.isConnectedToInternet(this))
                loadFoodList(categoryId);
            else
            {
                Toast.makeText(FoodList.this,"Please check your connection !!",Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }


    private void loadFoodList(String categoryId) {
        Query query = FirebaseDatabase.getInstance().getReference("Food").orderByChild("menuID").equalTo(categoryId);


       FirebaseRecyclerOptions<Food> options= new FirebaseRecyclerOptions.Builder<Food>().setQuery(query,Food.class).build();
        adapter1 = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull Food model) {
                holder.food_name.setText(model.getName());
                Picasso.get().load(model.getImage())
                        .into(holder.food_image);
                final Food local = model;
                holder.setItemCLickListener(new ItemCLickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent foodDetail = new Intent (FoodList.this,FoodDetail.class);
                        foodDetail.putExtra("FoodId",adapter1.getRef(position).getKey());
                        startActivity(foodDetail);
                    }
                });

            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup p, int v) {
                View view = LayoutInflater.from(p.getContext())
                        .inflate(R.layout.food_item,p,false);
                return new FoodViewHolder(view);
            }
        };
        Log.d("TAG",""+adapter1.getItemCount());
        recyclerView.setAdapter(adapter1);


    }
    @Override
    public void onStart() {
        super.onStart();
        adapter1.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter1.stopListening();
    }

}
