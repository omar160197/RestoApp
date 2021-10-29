package com.example.android.our_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

public void breakfast(View view){
    Intent intent=new Intent(this,breakfast.class);
    startActivity(intent);

}
    public void desserts(View view){
        Intent intent1=new Intent(this,dessert.class);
        startActivity(intent1);

    }

    public void drink(View view){
        Intent intent2=new Intent(this,drinks.class);
        startActivity(intent2);

    }

    public void maindish(View view){
        Intent intent3=new Intent(this,maindish.class);
        startActivity(intent3);

    }
    public void sidedish(View view){
        Intent intent4=new Intent(this,sidedish.class);
        startActivity(intent4);

    }


}


