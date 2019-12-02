package com.naukri.naukrisuchakkendra.BD;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.naukri.naukrisuchakkendra.DataModel;
import com.naukri.naukrisuchakkendra.R;

import java.util.ArrayList;

public class ViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DataModel> dataModelArrayList;

    ViewAdapter(Context context, ArrayList<DataModel> dataModelArrayList) {


        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
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
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.viewemp, null, true);

            holder.tvd=convertView.findViewById(R.id.eidv);
            holder.tven =  convertView.findViewById(R.id.cev);
            holder.tvj =  convertView.findViewById(R.id.eev);
            holder.tvmin =  convertView.findViewById(R.id.pev);
            holder.tvmax = convertView.findViewById(R.id.emev);
            holder.tvop = convertView.findViewById(R.id.tev);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tvd.setText(" ( "+dataModelArrayList.get(position).getName()+" )");
        holder.tven.setText(dataModelArrayList.get(position).getCrid());
        holder.tvj.setText(dataModelArrayList.get(position).getPrice());
        holder.tvmin.setText(dataModelArrayList.get(position).getWeight());
        holder.tvop.setText(dataModelArrayList.get(position).getMessage());
        holder.tvmax.setText(dataModelArrayList.get(position).getCatid());
        return convertView;
    }


    private class ViewHolder {

        TextView tven, tvj,tvmin,tvmax,tvop,tvd;
    }

}
