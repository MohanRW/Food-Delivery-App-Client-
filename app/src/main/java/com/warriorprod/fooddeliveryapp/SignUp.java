package com.warriorprod.fooddeliveryapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.warriorprod.fooddeliveryapp.Model.Common;
import com.warriorprod.fooddeliveryapp.Model.User;

public class SignUp extends AppCompatActivity {
    Button btnSignUp;
    EditText addPhone,addPwd,addName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        addPhone = (EditText)findViewById(R.id.addPhone);
        addName = (EditText)findViewById(R.id.addName);
        addPwd = (EditText)findViewById(R.id.addPassword);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference t_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Common.isConnectedToInternet(getBaseContext())) {
                    final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                    mDialog.setMessage("Waiting....");
                    mDialog.show();

                    t_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.child(addPhone.getText().toString()).exists()) {
                                Toast.makeText(SignUp.this, "Account already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                mDialog.dismiss();
                                User user = new User(addName.getText().toString(), addPwd.getText().toString());
                                t_user.child(addPhone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                finish();

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else
                {
                    Toast.makeText(SignUp.this,"Please check your internet connection !!",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


    }
}
