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


public class drinks extends AppCompatActivity {
    ListView listView3;

    String[]food={"Mango","Fresh Orange","Strawberry","Pepsi Can","Oreo","Cantaloupe"};
    String[]medum={"M","M","M","M","M","M"};
    String[]large={"L","L","L","L","L","L"};
    String[]medumprice={"26 LE","26 LE","26 LE","8 LE","22 LE","20 LE"};
    String[]largeprice={"30 LE","30 LE","30 LE","14 LE","26 LE","30 LE"};
    int[]photto={
            R.drawable.mango,
            R.drawable.orange,
            R.drawable.strawberry2,
            R.drawable.pepsi,
            R.drawable.oreo,
            R.drawable.cantalop,


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        listView3=(ListView)findViewById(R.id.list2);

        MyAdapter myAdapter=new MyAdapter(drinks.this,food,large,largeprice,medum,medumprice,photto);
        listView3.setAdapter(myAdapter);

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String food_name = ( (TextView) view.findViewById(R.id.food ) ).getText().toString();
                String medium_price = ( (TextView) view.findViewById(R.id.price1 ) ).getText().toString();
                String large_price = ( (TextView) view.findViewById(R.id.price2 ) ).getText().toString();
                int image_id = ((ImageView)view.findViewById(R.id.meal)).getId();

                Intent intent = new Intent(drinks.this , Order_Activity.class);
                intent.putExtra("Food" , food_name );
                intent.putExtra("Medium" , medium_price);
                intent.putExtra("Large" , large_price);
                intent.putExtra("Img_id", photto[position]);

                //startActivity(intent);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        drinks.this ,
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
