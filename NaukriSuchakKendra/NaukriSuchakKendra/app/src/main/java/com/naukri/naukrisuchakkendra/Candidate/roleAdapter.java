package com.naukri.naukrisuchakkendra.Candidate;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.florent37.viewtooltip.ViewTooltip;
import com.naukri.naukrisuchakkendra.DataModel;
import com.naukri.naukrisuchakkendra.R;
import java.util.ArrayList;


public class roleAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DataModel> dataModelArrayList;


    roleAdapter(Context context, ArrayList<DataModel> dataModelArrayList) {


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

            holder.linearLayout=convertView.findViewById(R.id.card);
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



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewTooltip
                        .on(holder.linearLayout)
                        // .customView(customView)
                        .position(ViewTooltip.Position.BOTTOM)
                        .arrowSourceMargin(0)
                        .arrowTargetMargin(0)
                        .text(dataModelArrayList.get(position).getName())
                        .clickToHide(true)
                        .autoHide(true, 1000)
                        .color(createPaint())
                        .animation(new ViewTooltip.FadeTooltipAnimation(500))
                        .onDisplay(new ViewTooltip.ListenerDisplay() {
                            @Override
                            public void onDisplay(View view) {
                                Log.d("ViewTooltip", "onDisplay");
                            }
                        })
                        .onHide(new ViewTooltip.ListenerHide() {
                            @Override
                            public void onHide(View view) {
                                Log.d("ViewTooltip", "onHide");
                            }
                        })
                        .show();


            }
        });
        return convertView;
    }


    private class ViewHolder {

        TextView tven;
        ImageView tvd;
        LinearLayout linearLayout;
    }
    private Paint createPaint() {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(new LinearGradient(0, 0, 150, 0,Color.parseColor("#25018E") ,Color.parseColor("#C32DEE"), Shader.TileMode.CLAMP));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }
}
