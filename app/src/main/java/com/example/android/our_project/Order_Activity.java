package com.example.android.our_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Order_Activity extends AppCompatActivity {

    TextView food , small_pr , large_pr ;
    ImageView food_image ;
    TextView small_count , large_count ;
    TextView bill_txt ;
    int s_c , l_c ;
    int s_price , l_price , bill ;
    int small_bill = 0 ,large_bill = 0;
    DataBaseClass db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_);

        Intent intent = getIntent();
        String food_name = intent.getStringExtra("Food");
        String medium_price = intent.getStringExtra("Medium");
        String large_price = intent.getStringExtra("Large");
        int img_id = intent.getIntExtra("Img_id",0);

        food = findViewById(R.id.food_name);
        small_pr = findViewById(R.id.small_price);
        large_pr = findViewById(R.id.large_price);
        food_image = findViewById(R.id.food_image);

        food.setText(food_name);
        small_pr.setText(medium_price);
        large_pr.setText(large_price);
        food_image.setImageResource(img_id);

        small_count = findViewById(R.id.small_count);
        s_c = Integer.valueOf(small_count.getText().toString());

        large_count = findViewById(R.id.large_count);
        l_c = Integer.valueOf(large_count.getText().toString());

        s_price = Integer.valueOf(medium_price.substring(0 , medium_price.length()-3 ));
        l_price = Integer.valueOf(large_price.substring(0 , large_price.length()-3 ));

        bill_txt = findViewById(R.id.bill);
        bill = Integer.valueOf(bill_txt.getText().toString());

        db = new DataBaseClass(this);
    }

    public void clicks (View view ){

        switch (view.getId()){
            case R.id.min_small:
                if (s_c == 0 )
                    return ;
                else {
                    s_c--;
                    small_count.setText(String.valueOf(s_c));
                    bill -= s_price;
                    small_bill -= s_price ;
                    bill_txt.setText(String.valueOf(bill));
                    if ( s_c == 0 ) findViewById(R.id.min_small).setVisibility(View.GONE);
                }
                break;
            case R.id.pls_small:
                findViewById(R.id.min_small).setVisibility(View.VISIBLE);
                s_c++;
                small_count.setText(String.valueOf(s_c));
                bill+= s_price;
                small_bill += s_price;
                bill_txt.setText(String.valueOf(bill));
                break;
            case R.id.min_large:
                if (l_c == 0 )
                    return ;
                else {
                    l_c--;
                    large_count.setText(String.valueOf(l_c));
                    bill-= l_price;
                    large_bill -= l_price;
                    bill_txt.setText(String.valueOf(bill));
                    if ( l_c == 0 ) findViewById(R.id.min_large).setVisibility(View.GONE);
                }
                break;
            case R.id.pls_large:
                findViewById(R.id.min_large).setVisibility(View.VISIBLE);
                l_c++;
                large_count.setText(String.valueOf(l_c));
                bill+= l_price;
                large_bill += l_price;
                bill_txt.setText(String.valueOf(bill));
                break;

        }
    }

    public void doneBtn(View view){
        String food_name = food.getText().toString();
        int small_order_count = Integer.valueOf(small_count.getText().toString());
        int small_order_price = small_bill;

        int large_order_count = Integer.valueOf(large_count.getText().toString());
        int large_order_price = large_bill;

        int total_bill = bill ;


        db.deleteData(food_name);
        boolean bol =db.insertDate(food_name,small_order_count,large_order_count,total_bill);

        if (bol)
        {
            Toast.makeText(this, "Added", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();


        this.onBackPressed();
    }
}
