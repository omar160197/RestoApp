package com.example.android.our_project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class sidedish extends AppCompatActivity {
    ListView listView4;

    String[]food={"Soup","Frensh Frise","Spaghetti","Veg Salad","Vegetables Horiz"};
    String[]medum={"M","M","M","M","M","M"};
    String[]large={"L","L","L","L","L","L"};
    String[]medumprice={"22 LE","15 LE","40 LE","50 LE","26 LE","30 LE"};
    String[]largeprice={"35 LE","20 LE","70 LE","65 LE","150 LE","75 LE"};
    int[]photto={
            R.drawable.soup,
            R.drawable.frenshfries,
            R.drawable.spaghetti,
            R.drawable.sallad,
            R.drawable.vegetableshoriz,


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidedish);
        listView4=(ListView)findViewById(R.id.list4);

        MyAdapter myAdapter=new MyAdapter(sidedish.this,food,large,largeprice,medum,medumprice,photto);
        listView4.setAdapter(myAdapter);

        listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String food_name = ( (TextView) view.findViewById(R.id.food ) ).getText().toString();
                String medium_price = ( (TextView) view.findViewById(R.id.price1 ) ).getText().toString();
                String large_price = ( (TextView) view.findViewById(R.id.price2 ) ).getText().toString();
                int image_id = ((ImageView)view.findViewById(R.id.meal)).getId();

                Intent intent = new Intent(sidedish.this , Order_Activity.class);
                intent.putExtra("Food" , food_name );
                intent.putExtra("Medium" , medium_price);
                intent.putExtra("Large" , large_price);
                intent.putExtra("Img_id", photto[position]);

               // startActivity(intent);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        sidedish.this ,
                        new Pair<View , String >( view.findViewById(R.id.meal ) , ViewCompat.getTransitionName(view.findViewById(R.id.meal ))),
                        new Pair<View , String >( view.findViewById(R.id.food) , ViewCompat.getTransitionName(view.findViewById(R.id.food ))) ,
                        new Pair<View , String >( view.findViewById(R.id.price1) , ViewCompat.getTransitionName(view.findViewById(R.id.price1 ))),
                        new Pair<View , String >( view.findViewById(R.id.price2) , ViewCompat.getTransitionName(view.findViewById(R.id.price2 )))
                );

                startActivity(intent , options.toBundle());

            }
        });
    }



}
