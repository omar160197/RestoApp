package com.example.android.our_project;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    DataBaseClass db;
    TextView textView;
    ArrayList<CartItem> itemss;
    ListView listView;
    int sum = 0;
    TextView textView2;
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        db = new DataBaseClass(this);
        textView2 = findViewById(R.id.t2);
        textView = findViewById(R.id.txtempty);
        listView = findViewById(R.id.list_cart);
        itemss = new ArrayList<CartItem>();
        Cursor res = db.getAllData();

        if (res.getCount() > 0) {
            textView.setVisibility(TextView.INVISIBLE);
            while (res.moveToNext()) {
                itemss.add(new CartItem(res.getString(0), res.getInt(1), res.getInt(2), res.getInt(3)));
                sum += res.getInt(3);
            }
        }

        adapter = new CartAdapter(itemss);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        textView2.setText("Total Price " + String.valueOf(sum) + " LE");


    }

    class CartAdapter extends BaseAdapter {
        ArrayList<CartItem> item;

        public CartAdapter(ArrayList<CartItem> item) {
            this.item = item;
        }

        @Override
        public int getCount() {
            return item.size();
        }

        @Override
        public Object getItem(int i) {
            return item.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            View view3 = inflater.inflate(R.layout.cartitem, null);

            TextView textView1 = view3.findViewById(R.id.txt1);
            TextView textView2 = view3.findViewById(R.id.txt2);
            TextView textView3 = view3.findViewById(R.id.txt3);
            TextView textView4 = view3.findViewById(R.id.txt4);

            textView1.setText(item.get(i).getMeal());
            textView2.setText(String.valueOf(item.get(i).getMed_amout()));
            textView3.setText(String.valueOf(item.get(i).getLarge_amout()));
            textView4.setText(String.valueOf(item.get(i).getTotal_price()));

            return view3;
        }
    }

    public void clear(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Cart.this);
        builder.setMessage(R.string.msg)
                .setIcon(R.drawable.alert_icon)
                .setTitle(R.string.title)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        itemss.clear();
                        db.deleteAll();
                        adapter.notifyDataSetChanged();
                        textView.setVisibility(TextView.VISIBLE);
                        textView2.setText("Total Price 0 LE");
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Cart.this , "OK" , Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    public void order_now(View view) {

        String[] op = {"EMAIL","CALL" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setItems(op, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i)
                {
                    case 0 :
                        Intent intent = new Intent(Cart.this , Order.class);
                        startActivity(intent);
                        break;

                    case 1 :
                        Intent intent1 = new Intent(Intent.ACTION_DIAL);
                        intent1.setData(Uri.parse("tel:01092862806"));
                        startActivity(intent1);
                        break ;
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.cartmenu,menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.delete:
                itemss.remove(info.position);
                adapter.notifyDataSetChanged();
                View v = info.targetView;
                TextView t2 = v.findViewById(R.id.txt4);
                TextView t = v.findViewById(R.id.txt1);
                db.deleteData(t.getText().toString());
                sum-=Integer.parseInt(t2.getText().toString());
                textView2.setText("Total Price " + String.valueOf(sum) + " LE");
                if(itemss.isEmpty())
                    textView.setVisibility(TextView.VISIBLE);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
