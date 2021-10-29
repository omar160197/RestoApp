package com.example.android.our_project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Order extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    DataBaseClass db;
    String message;
    int totalprice = 0;
    EditText t1;
    EditText t2;
    EditText t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        db = new DataBaseClass(this);
        message = new String();
        t1 = findViewById(R.id.nametxt);
        t2 = findViewById(R.id.phonetxt);
        t3 = findViewById(R.id.adresstxt);
    }

    public void makeMessage() {
        message += "Name : " + t1.getText().toString() + "\n";
        message += "Phone : " + t2.getText().toString() + "\n";
        message += "Adress : " + t3.getText().toString() + "\n\n";

        Cursor res = db.getAllData();

        if (res.getCount() > 0) {
            while (res.moveToNext()) {

                message += res.getString(0) + "\n";
                message += "M  " + String.valueOf(res.getInt(1)) + "\n";
                message += "L  " + String.valueOf(res.getInt(2)) + "\n";
                message += "price " + String.valueOf(res.getInt(3)) + "\n\n";
                totalprice += res.getInt(3);

            }
        }

        message += "total price  " + String.valueOf(totalprice) + "\n";
    }

    public void sendOrder(View view) {
        if (t1.getText().toString().equals("") || t2.getText().toString().equals("") || t3.getText().toString().equals("")) {
            Toast.makeText(this, "Enter All Data", Toast.LENGTH_LONG).show();
        } else {
            makeMessage();
            Intent intent = new Intent(Intent.ACTION_SEND);
            String[] to = {"aliaaatef440@gmail.com"};
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Order");
            intent.putExtra(Intent.EXTRA_TEXT, message);
            intent.setType("message/rfc822");
            startActivity(intent);
            message = "";
            totalprice = 0;
        }
    }

    public void callNow(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:01092862806"));
        if(intent.resolveActivity(getPackageManager()) !=null)
            startActivity(intent);
    }
}


