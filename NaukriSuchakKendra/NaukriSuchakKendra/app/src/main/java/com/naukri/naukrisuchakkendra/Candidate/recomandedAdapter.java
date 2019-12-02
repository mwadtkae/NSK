package com.naukri.naukrisuchakkendra.Candidate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.naukri.naukrisuchakkendra.DataModel;
import com.naukri.naukrisuchakkendra.R;
import java.util.ArrayList;


public class recomandedAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DataModel> dataModelArrayList;
    recomandedAdapter(Context context, ArrayList<DataModel> dataModelArrayList) {


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
            convertView = inflater.inflate(R.layout.candidatejob, null, true);

            holder.tvd=convertView.findViewById(R.id.en);
            holder.tven =  convertView.findViewById(R.id.jn);
            holder.tvmin =  convertView.findViewById(R.id.minn);
            holder.tvmax = convertView.findViewById(R.id.maxn);
            holder.tvop =  convertView.findViewById(R.id.tn);
            holder.tvt =  convertView.findViewById(R.id.dn);
            holder.tvq =  convertView.findViewById(R.id.dan);
            holder.tvj =  convertView.findViewById(R.id.sk);
            holder.tvst =  convertView.findViewById(R.id.stamp);
            holder.cardView =  convertView.findViewById(R.id.card);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tvd.setText(dataModelArrayList.get(position).getJob());
        holder.tven.setText(dataModelArrayList.get(position).getExpe());
        holder.tvmin.setText(dataModelArrayList.get(position).getQuantity());
        holder.tvmax.setText(dataModelArrayList.get(position).getWeight());
        holder.tvop.setText(dataModelArrayList.get(position).getTaluka());
        holder.tvt.setText(dataModelArrayList.get(position).getName());
        holder.tvq.setText(dataModelArrayList.get(position).getDate());
        String sk=dataModelArrayList.get(position).getPrice().replaceAll(","," , ");
        holder.tvj.setText(sk);

        if(dataModelArrayList.get(position).getProdid().equals("0"))
            holder.tvst.setVisibility(View.INVISIBLE);
        else {
            holder.tvst.setVisibility(View.VISIBLE);

        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,Detailsjob.class);
                intent.putExtra("title",dataModelArrayList.get(position).getJob());
                intent.putExtra("jobdes",dataModelArrayList.get(position).getDesc());
                intent.putExtra("jobtime",dataModelArrayList.get(position).getMessage());
                intent.putExtra("jobint",dataModelArrayList.get(position).getImgURL());
                intent.putExtra("skill",dataModelArrayList.get(position).getPrice());
                intent.putExtra("min",dataModelArrayList.get(position).getQuantity());
                intent.putExtra("max",dataModelArrayList.get(position).getWeight());
                intent.putExtra("distr",dataModelArrayList.get(position).getName());
                intent.putExtra("taluka",dataModelArrayList.get(position).getTaluka());
                intent.putExtra("exp",dataModelArrayList.get(position).getExpe());
                intent.putExtra("jid",dataModelArrayList.get(position).getId());
                intent.putExtra("emid",dataModelArrayList.get(position).getCrid());
                intent.putExtra("apply",dataModelArrayList.get(position).getProdid());

                context.startActivity(intent);
            }
        });

        return convertView;
    }


    private class ViewHolder {

        TextView tven, tvj,tvmin,tvmax,tvop,tvd,tvt,tvq,tvst;
        CardView cardView;
    }

}
