package com.naukri.naukrisuchakkendra.Candidate;

import android.app.Activity;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.naukri.naukrisuchakkendra.DataModel;
import com.naukri.naukrisuchakkendra.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DataModel> dataModelArrayList;
    private SparseBooleanArray mSelectedItemsIds;


    GridAdapter(Context context, ArrayList<DataModel> dataModelArrayList) {


        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
        mSelectedItemsIds=new SparseBooleanArray();
    }




    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gridview, null, true);

            holder.tvd=convertView.findViewById(R.id.img);
            holder.tven =  convertView.findViewById(R.id.tvtext);
            holder.cardView=convertView.findViewById(R.id.card);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tven.setText(dataModelArrayList.get(position).getName());

        Glide
                .with(context)
                .load(dataModelArrayList.get(position).getImgURL())
                .into(holder.tvd);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mSelectedItemsIds.size()<3||mSelectedItemsIds.get(position)) {
                    checkCheckBox(position, !mSelectedItemsIds.get(position));
                    holder.tvd.clearColorFilter();
                }
            }
        });
        return convertView;
    }


    private class ViewHolder {

        TextView tven;
        ImageView tvd;
        LinearLayout cardView;
    }
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }
    private void checkCheckBox(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, true);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();

    }


    SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }



}
