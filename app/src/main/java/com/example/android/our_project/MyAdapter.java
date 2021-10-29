package com.example.android.our_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

/**
 * Created by Omar Sameh on 7/24/2018.
 */
public class MyAdapter extends ArrayAdapter<String> {
    String[]foodname={""};
    String[]medumc={""};
    String[]largec={""};
    String[]medump={""};
    String[]largep={""};
    int[]phottosource={};
Context mcontext;
    public MyAdapter(Context context,String[]food,String[]large,String[]medumprice,String[]largeprice,String[]medum ,int[]photto ) {

        super(context, R.layout.listviewitem);
        this.foodname=food;
        this.medump=medumprice;
        this.largep=largeprice;
        this.medumc=medum;
        this.largec=large;
        this.phottosource=photto;
        this.mcontext=context;

    }

    @Override
    public int getCount() {
        return foodname.length;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder=new ViewHolder();
        if (convertView==null) {
            LayoutInflater mInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listviewitem, parent, false);
            mViewHolder.mFlag = (ImageView) convertView.findViewById(R.id.meal);
            mViewHolder.textView1 = (TextView) convertView.findViewById(R.id.food);
            mViewHolder.textView2 = (TextView) convertView.findViewById(R.id.medum);
            mViewHolder.textView3 = (TextView) convertView.findViewById(R.id.large);
            mViewHolder.textView4 = (TextView) convertView.findViewById(R.id.price1);
            mViewHolder.textView5 = (TextView) convertView.findViewById(R.id.price2);
       convertView.setTag(mViewHolder);
        }else {
             mViewHolder=(ViewHolder)convertView.getTag();
        }
            mViewHolder. mFlag.setImageResource(phottosource[position]);
            mViewHolder.textView1.setText(foodname[position]);

        mViewHolder.textView2.setText(largep[position]);

            mViewHolder.textView3.setText(largec[position]);

            mViewHolder.textView4.setText(medumc[position]);

            mViewHolder.textView5.setText(medump[position]);

        return convertView;
    }
    static class ViewHolder{
        ImageView mFlag;
        TextView textView1;
        TextView textView2;
        TextView textView3 ;
        TextView textView4 ;
        TextView textView5;

    }
    }




