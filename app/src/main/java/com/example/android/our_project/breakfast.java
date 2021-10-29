package com.example.android.our_project;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class breakfast extends AppCompatActivity {
ListView listView;

    String[]food={"Creamy Herp","Egg Sandwich 1","Egg Salad","Egg Sandwich","French Fries","Sweet Buns"};
    String[]medum={"M","M","M","M","M","M"};
    String[]large={"L","L","L","L","L","L"};
    String[]medumprice={"26 LE","30 LE","40 LE","50 LE","26 LE","30 LE"};
    String[]largeprice={"120 LE","150 LE","145 LE","175 LE","150 LE","145 LE"};
    int[]photto={
            R.drawable.creamyherb,
            R.drawable.eggsandwich1,
            R.drawable.eggsalad,
            R.drawable.eggsandwich,
            R.drawable.frenshfries,
            R.drawable.sweetbuns,


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        listView=(ListView)findViewById(R.id.list);

MyAdapter myAdapter=new MyAdapter(breakfast.this,food,large,largeprice,medum,medumprice,photto);
   listView.setAdapter(myAdapter); /////////////// medumprice ////////////  largeprice

   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
       @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String food_name = ( (TextView) view.findViewById(R.id.food ) ).getText().toString();
            String medium_price = ( (TextView) view.findViewById(R.id.price1 ) ).getText().toString();
            String large_price = ( (TextView) view.findViewById(R.id.price2 ) ).getText().toString();
            int image_id = ((ImageView)view.findViewById(R.id.meal)).getId();

            Intent intent = new Intent(breakfast.this , Order_Activity.class);
            intent.putExtra("Food" , food_name );
            intent.putExtra("Medium" , medium_price);
            intent.putExtra("Large" , large_price);
            intent.putExtra("Img_id", photto[position]);

          //  startActivity(intent);

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    breakfast.this ,
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
