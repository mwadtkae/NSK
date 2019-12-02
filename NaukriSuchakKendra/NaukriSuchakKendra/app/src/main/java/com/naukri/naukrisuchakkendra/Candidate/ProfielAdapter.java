package com.naukri.naukrisuchakkendra.Candidate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.naukri.naukrisuchakkendra.DataModel;
import com.naukri.naukrisuchakkendra.R;

import java.util.ArrayList;

public class ProfielAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<DataModel> dataModelArrayList;
    ProfielAdapter(Context context, ArrayList<DataModel> dataModelArrayList) {


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


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.profilelayout, null, true);

            holder.tvd=convertView.findViewById(R.id.pt);
            holder.tven =  convertView.findViewById(R.id.pe);
            holder.tvmin =  convertView.findViewById(R.id.ps);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tvd.setText(dataModelArrayList.get(position).getId());
        holder.tven.setText(dataModelArrayList.get(position).getCrid());
        if (dataModelArrayList.get(position).getJob().equals("null"))
        {
            holder.tvmin.setText("Applied");
        }
        else
           if(dataModelArrayList.get(position).getJob().equals("1"))
            {
                holder.tvmin.setText("HIRE");
            }
           else
           if(dataModelArrayList.get(position).getJob().equals("2"))
           {
               holder.tvmin.setText("SHORT LIST");
           }
           else
           if(dataModelArrayList.get(position).getJob().equals("0"))
           {
               holder.tvmin.setText("REJECT");
           }




        return convertView;
    }


    private class ViewHolder {

        TextView tven,tvmin,tvd;
    }

}
