package com.quickly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quickly.R;

import java.util.List;

public class GridViewApapter extends BaseAdapter {


    private Context mContext;

    private List<Integer> mData;

    private LayoutInflater mInflate;


    public GridViewApapter(Context context,List<Integer> data){
        mContext = context;
        mData = data;
        mInflate = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflate = mInflate.inflate(R.layout.home_category_item, null);
        ImageView imageView = inflate.findViewById(R.id.category_img);
        imageView.setImageResource(mData.get(position));
        TextView tv = inflate.findViewById(R.id.category_title);
        tv.setText("菜单"+position);
        return inflate;
    }

}