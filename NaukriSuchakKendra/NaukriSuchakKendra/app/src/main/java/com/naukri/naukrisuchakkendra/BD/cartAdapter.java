package com.naukri.naukrisuchakkendra.BD;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.RequiresApi;

import com.naukri.naukrisuchakkendra.DataModel;
import com.naukri.naukrisuchakkendra.R;

import java.util.ArrayList;

public class cartAdapter extends BaseAdapter {

    private Context context;
        private ArrayList<DataModel> dataModelArrayList;
        cartAdapter(Context context, ArrayList<DataModel> dataModelArrayList) {


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

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.viewjob, null, true);

                holder.tven =  convertView.findViewById(R.id.en);
                holder.tvj =  convertView.findViewById(R.id.jn);
                holder.tvmin = convertView.findViewById(R.id.minn);
                holder.tvmax = convertView.findViewById(R.id.maxn);
                holder.tvop = convertView.findViewById(R.id.on);
                holder.tvs = convertView.findViewById(R.id.sn);
                holder.tvd = convertView.findViewById(R.id.dn);
                holder.tvt = convertView.findViewById(R.id.tn);
                holder.tvq = convertView.findViewById(R.id.qn);
                holder.tvex = convertView.findViewById(R.id.exn);
                holder.tvdate =convertView.findViewById(R.id.dan);
                convertView.setTag(holder);
            }else {
                // the getTag returns the viewHolder object set as a tag to the view
                holder = (ViewHolder)convertView.getTag();
            }
            holder.tven.setText(dataModelArrayList.get(position).getName());
            holder.tvj.setText(dataModelArrayList.get(position).getJob());
            holder.tvmin.setText(dataModelArrayList.get(position).getPrice());
            holder.tvmax.setText(dataModelArrayList.get(position).getWeight());
            holder.tvop.setText(dataModelArrayList.get(position).getCatid());
            holder.tvs.setText(dataModelArrayList.get(position).getMessage());
            holder.tvd.setText(dataModelArrayList.get(position).getImgURL());
            holder.tvt.setText(dataModelArrayList.get(position).getQuantity());
            holder.tvq.setText(dataModelArrayList.get(position).getProdid());
            holder.tvex.setText(dataModelArrayList.get(position).getCrid());
            holder.tvdate.setText(dataModelArrayList.get(position).getExpe());
            return convertView;
        }


        private class ViewHolder {

            TextView tven, tvj,tvmin,tvmax,tvop,tvs,tvd,tvt,tvq,tvex,tvdate;
            }

    }

