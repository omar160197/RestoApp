package com.example.android.our_project;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity{

    DataBaseClass db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseClass(this);
    }
    public void call(View view){

        Intent intent=new Intent(this,calls.class);

        startActivity(intent);
    }

    public void about(View view){
        Intent intent=new Intent(this,about.class);
        startActivity(intent);

    }

    public void menu (View view){

        Intent intent=new Intent(this,menu.class);
        startActivity(intent);

    }

    public void button3_google_Map(View view){
        Intent google_Map = new Intent(Intent.ACTION_VIEW);
        google_Map.setData(Uri.parse("geo:30.4685303,31.1862421?z = 18.14"));
        if(google_Map.resolveActivity(getPackageManager()) != null){
            startActivity(google_Map);
        }

    }

    public void gallery(View view)
    {
            Intent intent =new Intent(this , OurGallery.class);
            startActivity(intent);
    }

    public void cart(View view)
    {
        Intent intent =new Intent(this , Cart.class);
        startActivity(intent);

    }
}
