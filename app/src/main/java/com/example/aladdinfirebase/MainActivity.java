package com.example.aladdinfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView tv1,tv2,tv11,tv22,tv111,tv222;

    DatabaseReference databaseReference;

    String wish1,wish2,wish3;
    int wishnum1,wishnum2,wishnum3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv11=findViewById(R.id.tv11);
        tv22=findViewById(R.id.tv22);
        tv111=findViewById(R.id.tv111);
        tv222=findViewById(R.id.tv222);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("wishes"); //this is a reference


        databaseReference.addValueEventListener(new ValueEventListener() { // this is called everytime the
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //here we have the value(dataSnapshot) of the reference

                int i=0;
                for(DataSnapshot childDataSnapshot : dataSnapshot.getChildren()){ //iterating through the children of the dataSnapshot
                    // with each child being named childDataSnapshot for every iteration
                    if(i==0) {
                        wish1 = childDataSnapshot.child("wishName").getValue(String.class);
                        // childDataSnapshot.child is done as we have reached 0: now its child is wishlist who has the value.
                        wishnum1= childDataSnapshot.child("wishNum").getValue(Integer.class);
                        // .getValue() is for it to in converted/understood as of a particular data type.
                        tv1.setText(wish1);
                        tv2.setText(wishnum1+"");
                    }
                    else if(i==1){
                        wish2 = childDataSnapshot.child("wishName").getValue(String.class);
                        wishnum2= childDataSnapshot.child("wishNum").getValue(Integer.class);
                        tv11.setText(wish2);
                        tv22.setText(wishnum2+"");
                    }
                    else if(i==2){
                        wish3 = childDataSnapshot.child("wishName").getValue(String.class);
                        wishnum3= childDataSnapshot.child("wishNum").getValue(Integer.class);
                        tv111.setText(wish3);
                        tv222.setText(wishnum3+"");
                    }
                    i++;
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
