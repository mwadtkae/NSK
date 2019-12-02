package com.naukri.naukrisuchakkendra.BD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.naukri.naukrisuchakkendra.DataModel;
import com.naukri.naukrisuchakkendra.R;
import com.naukri.naukrisuchakkendra.User;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


   PieChart pieChartt;
    ArrayList<PieEntry> yvalues;
    RelativeLayout r1,r2,r3,r4,noconnection;
    Intent intent;
    TextView name,in,idhere;
    String ids;
    public static final String basemain="https://naukrisuchak.com/app/api/";
    public static final String basebd=basemain+"bd/";
    public static final String Url_form = basebd+"bddash.php";
    private ProgressBar progress_bar;
    private LinearLayout lyt_no_connection;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {
            finish();
            PrefManager.getInstance(getApplicationContext()).logout1();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("Naukri Suchak Kendra");
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
        }
        setSupportActionBar(toolbar);


        User user = PrefManager.getInstance(this).getUser1();
        ids=user.getId();
        name=findViewById(R.id.name);
        in=findViewById(R.id.namee);
        idhere=findViewById(R.id.id);
        name.setText(user.getUsername());
        idhere.setText("NSK_ID :- "+user.getId());





        r1=findViewById(R.id.jp);
        r2=findViewById(R.id.jv);
        r3=findViewById(R.id.ea);
        r4=findViewById(R.id.ev);

      //  rcheck=findViewById(R.id.rc);

//rcheck.setVisibility(View.GONE);
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               intent =new Intent(getApplicationContext(),Addjob.class);
               startActivity(intent);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getApplicationContext(),viewjob.class);
                startActivity(intent);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getApplicationContext(),Addemp.class);
                startActivity(intent);
            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getApplicationContext(),viewemp.class);
                startActivity(intent);
            }
        });


        pieChartt=findViewById(R.id.piechart);
        pieChartt.setUsePercentValues(true);
        pieChartt.getDescription().setEnabled(false);
        // pieChartt.setDescription(" ");
        pieChartt.setExtraOffsets(5,10,5,5);

        // pieChartt.setDragDecelerationFrictionCoef(0.15f);
        pieChartt.setDragDecelerationFrictionCoef(0.99f);

        pieChartt.setDrawHoleEnabled(true);
        //pieChartt.setDrawHoleEnabled(false);
        pieChartt.setHoleColor(Color.WHITE);
        pieChartt.setHoleRadius(40f);
        pieChartt.setTransparentCircleRadius(50f);





        // pieChartt.animateXY(4000, 3000);




                    emp();


        noconnection=findViewById(R.id.no_connection);
        noconnection.setVisibility(View.INVISIBLE);

        progress_bar =  findViewById(R.id.progress_bar);
        lyt_no_connection = findViewById(R.id.lyt_no_connection);

        progress_bar.setVisibility(View.GONE);
        lyt_no_connection.setVisibility(View.VISIBLE);

        lyt_no_connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                progress_bar.setVisibility(View.VISIBLE);
                lyt_no_connection.setVisibility(View.GONE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        startActivity(getIntent());
                    }
                }, 1000);
            }
        });
    }
    public  boolean isInternetConnection()
    {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }


    private void emp()
    {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url_form,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                         yvalues=new ArrayList<>();

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {
                                JSONArray dataArray = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();

                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setName(dataobj.getString("jd"));
                                    playerModel.setCrid(dataobj.getString("eid"));
                                    yvalues.add(new PieEntry(Float.parseFloat(dataobj.getString("jd")),dataobj.getString("jd")));
                                    yvalues.add(new PieEntry(Float.parseFloat(dataobj.getString("eid")),dataobj.getString("eid")));
                                }

                                if (yvalues.size() > 0) {
                                    setupListview();
                                }

                            }


                            if(obj.optString("responce").equals("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();


                            }

                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs

//                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

                        if(!isInternetConnection())
                            noconnection.setVisibility(View.VISIBLE);
                        else
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
                       // Toast.makeText(getApplicationContext(),"Failure (" + error.networkResponse.statusCode + ")", Toast.LENGTH_SHORT).show();

                    }
                }){
            protected Map<String,String> getParams()throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("user_id",ids);
                return params;

            }
        };



        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }



    private void setupListview()
    {

        final int[] MY_COLORS = { Color.rgb(230,0,126), Color.rgb(45,46,131),
                Color.rgb(102,36,131)};
        final String[] titleList={ "JOB POST","EMPLOYER REGISTER"};

        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        List<LegendEntry> entries = new ArrayList<>();
        for(int c: MY_COLORS) colors.add(c);
        pieChartt.animateY(1000, Easing.EaseInOutCubic);
        Collections.addAll(title, titleList);


        for (int i = 0; i < titleList.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors.get(i);
            entry.label = title.get(i);
            entries.add(entry);
        }


        PieDataSet dataSet=new PieDataSet(yvalues,"");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(20f);
        dataSet.setColors(colors);



        // dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data=new PieData((dataSet));
        data.setValueTextSize(0);
        data.setValueTextColor(Color.YELLOW);

        Legend l = pieChartt.getLegend();
        l.setEnabled(true);
        l.setTextSize(14f);
        l.setCustom(entries);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setTextColor(Color.WHITE);
        l.setEnabled(true);
       // pieChartt.getLegend().setEnabled(true);
        pieChartt.setData(data);
        pieChartt.invalidate();
        pieChartt.setCenterText("DASHBOARD");


    }

  /*  @Override
    public void onBackPressed() {


        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Naukri Suchak Kendra")
                .setMessage("Are you sure you want to Exit App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }*/
}
