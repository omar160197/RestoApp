package com.example.android.our_project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by etc on 7/21/2018.
 */

public class GalleryAdapter extends BaseAdapter {

    private Context context;

    static int[] im={

            R.drawable.c,R.drawable.k,R.drawable.l,
            R.drawable.d,R.drawable.e,R.drawable.f,
            R.drawable.n,R.drawable.o,R.drawable.r,
            R.drawable.p,R.drawable.q,R.drawable.t,
            R.drawable.s,R.drawable.v,R.drawable.x,
            R.drawable.y,R.drawable.z
    };

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return im.length;
    }

    @Override
    public Object getItem(int position) {
        return im[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(ImageLesser.decodeSampledBitmapFromResource(context.getResources(),im[position],100,100));
        imageView.setScaleType( ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(240 ,240));

        return imageView;
    }
}

