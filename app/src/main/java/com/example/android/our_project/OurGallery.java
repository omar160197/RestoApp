package com.example.android.our_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class OurGallery extends AppCompatActivity {

    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_gallery);

        gridView = (GridView)findViewById(R.id.grid);
        GalleryAdapter adapter = new GalleryAdapter(this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OurGallery.this,FullPhoto.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });
    }
}
