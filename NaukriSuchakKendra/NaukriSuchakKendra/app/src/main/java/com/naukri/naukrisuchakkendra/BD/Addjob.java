package com.naukri.naukrisuchakkendra.BD;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abdeveloper.library.MultiSelectDialog;
import com.abdeveloper.library.MultiSelectModel;
import com.agrawalsuneet.dotsloader.loaders.LinearDotsLoader;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import worker8.com.github.radiogroupplus.RadioGroupPlus;

import static com.naukri.naukrisuchakkendra.BD.MainActivity.basebd;

public class Addjob extends AppCompatActivity {


    public static final String emp_urln = basebd+"showemp.php";
    public static final String job_t = basebd+"Jobtitle.php?user_id=0";
    public static final String st_url = basebd+"state.php?user_id=0";
    public static final String dt = basebd+"district.php?st_id=";
    public static final String tr_u = basebd+"taluka.php?d_id=";
    public static final String jt_u = basebd+"jobdesc.php?job_id=";
    public static final String sk_u = basebd+"skill.php?job_id=";
    public static final String job_submit = basebd+"Jobpost.php";

    LinearLayout info1,info2,info3;
    ArrayList<DataModel> dataModelArrayList;
    ArrayList<DataModel> dataModelArrayList1;

    Button submit;
    TextView tv;
    Button in1,ip2,in2,ip3;
    EditText enamet,jobt,statet,disrctt,talukat,titlet,skillt,mint,maxt,opt,jobtt,inrt;
    RadioGroupPlus redu,rex,rg,re;
    private static ProgressDialog mProgressDialog;
    String eid,jid,mins,maxs,nos,sts,ds,ts,eds="",exps="",gs="",es="",jds,sks,jts,its,jmainid,ids;
    String[] ear,eidar,jr,jidar,jtid,sr,sidar,dr,didar,tr,tidar,skr;
ArrayList<String> items,items1,items2,items3,items4;

    RelativeLayout r,noconnection;
    private ProgressBar progress_bar;
    private LinearLayout lyt_no_connection;
    private String TAG = "Cancel";

    ArrayList<MultiSelectModel> listOfCountries= new ArrayList<>();

    MultiSelectDialog multiSelectDialog;
SpinnerDialog spinnerDialog,spinnerDialog1,spinnerDialog2,spinnerDialog3,spinnerDialog4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjob);
        Toolbar toolbar=findViewById(R.id.toolbarA);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("Naukri Suchak Kendra");
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
        }

        LinearLayout containerLL = findViewById(R.id.container);

        LinearDotsLoader loader = new LinearDotsLoader(this);
        loader.setDefaultColor(ContextCompat.getColor(this, R.color.blue_delfault));
        loader.setSelectedColor(ContextCompat.getColor(this, R.color.blue_selected));
        loader.setSingleDir(true);
        loader.setNoOfDots(5);
        loader.setSelRadius(30);
        loader.setExpandOnSelect(true);
        loader.setRadius(20);
        loader.setDotsDistance(20);
        loader.setAnimDur(200);
        loader.setShowRunningShadow(true);
        loader.setFirstShadowColor(ContextCompat.getColor(this, R.color.blue_delfault));
        loader.setSecondShadowColor(ContextCompat.getColor(this, R.color.blue_delfault));
        containerLL.addView(loader);
        r=findViewById(R.id.rd);
        tv=findViewById(R.id.tvev);
        tv.setVisibility(View.INVISIBLE);
        r.setVisibility(View.INVISIBLE);
        User user = PrefManager.getInstance(this).getUser1();
        ids=user.getId();
        enamet=findViewById(R.id.empname);
        jobt=findViewById(R.id.jobtitle);
        statet=findViewById(R.id.statei);
        disrctt=findViewById(R.id.districti);
        talukat=findViewById(R.id.talukai);
        titlet=findViewById(R.id.jobdi);
        skillt=findViewById(R.id.skilli);


        mint=findViewById(R.id.mins);
        maxt=findViewById(R.id.maxs);
        opt=findViewById(R.id.open);

        jobtt=findViewById(R.id.jobti);
        inrt=findViewById(R.id.inrti);



        redu=findViewById(R.id.edr);
        rex=findViewById(R.id.expi);
        rg=findViewById(R.id.gei);
        re=findViewById(R.id.ei);


        info1=findViewById(R.id.l1);
        info2=findViewById(R.id.l2);
        info3=findViewById(R.id.l3);

        info2.setVisibility(View.INVISIBLE);
        info3.setVisibility(View.INVISIBLE);

        ip2=findViewById(R.id.b2);
        ip3=findViewById(R.id.b3);
        in1=findViewById(R.id.bn1);
        in2=findViewById(R.id.bn2);

        ip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info3.setVisibility(View.INVISIBLE);
                info2.setVisibility(View.INVISIBLE);
                info1.setVisibility(View.VISIBLE);

            }
        });
        ip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info3.setVisibility(View.INVISIBLE);
                info2.setVisibility(View.VISIBLE);
                info1.setVisibility(View.INVISIBLE);

            }
        });

        in1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check()) {
                    info3.setVisibility(View.INVISIBLE);
                    info2.setVisibility(View.VISIBLE);
                    info1.setVisibility(View.INVISIBLE);
                }
            }
        });
        in2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check2()) {
                    info3.setVisibility(View.VISIBLE);
                    info2.setVisibility(View.INVISIBLE);
                    info1.setVisibility(View.INVISIBLE);
                }
            }
        });

        submit=findViewById(R.id.submitj);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check3())
                {
                   end();
                }
            }
        });

        redu.setOnCheckedChangeListener(new RadioGroupPlus.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroupPlus group,@IdRes int checkedId) {
                eds = ((RadioButton)findViewById(checkedId)).getText().toString();
            }
        });
        rex.setOnCheckedChangeListener(new RadioGroupPlus.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroupPlus group, @IdRes int checkedId) {
                exps = ((RadioButton)findViewById(checkedId)).getText().toString();
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroupPlus.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroupPlus group, @IdRes int checkedId) {

                gs = ((RadioButton)findViewById(checkedId)).getText().toString();
            }
        });


        re.setOnCheckedChangeListener(new RadioGroupPlus.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroupPlus group, @IdRes int checkedId) {

                es = ((RadioButton)findViewById(checkedId)).getText().toString();
            }
        });


        enamet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });
        enamet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    spinnerDialog.showSpinerDialog();
                }
            }
        });

        jobt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog1.showSpinerDialog();
            }
        });

        jobt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    spinnerDialog1.showSpinerDialog();
                }

            }
        });


        statet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog2.showSpinerDialog();
            }
        });
        statet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    spinnerDialog2.showSpinerDialog();
                }
            }
        });

            disrctt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(statet.getText().toString().length()!=0) {

                    spinnerDialog3.showSpinerDialog();
                    }

                }
            });

            disrctt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(statet.getText().toString().length()!=0) {

                        if (hasFocus) {

                            spinnerDialog3.showSpinerDialog();
                        }
                    }

                }
            });
        talukat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(disrctt.getText().toString().length()!=0) {
                    spinnerDialog4.showSpinerDialog();
                }
                }
        });

        talukat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (disrctt.getText().toString().length() != 0) {
                    if (hasFocus) {

                        spinnerDialog4.showSpinerDialog();
                    }

                }
            }
        });

        skillt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (jobt.getText().toString().length() != 0) {
                    multiSelectDialog.show(getSupportFragmentManager(), "multiSelectDialog");
                }
            }
        });

        skillt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (jobt.getText().toString().length() != 0) {
                    if (hasFocus) {

                        multiSelectDialog.show(getSupportFragmentManager(), "multiSelectDialog");
                    }

                }
            }
        });




        jobt();

         emp();




        noconnection=findViewById(R.id.no_connection);
        if(!isInternetConnection())
            noconnection.setVisibility(View.VISIBLE);
        else
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

        r.setVisibility(View.VISIBLE);
        //showSimpleProgressDialog(this, "Loading...","EMPLOYER...",false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, emp_urln,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {
                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setId(dataobj.getString("id"));
                                    playerModel.setName(dataobj.getString("ename"));

                                    dataModelArrayList.add(playerModel);


                                }

                                if (dataModelArrayList.size() > 0) {
                                    r.setVisibility(View.INVISIBLE);
                                    setupListview();
                                }  else
                                {
                                    info1.setVisibility(View.INVISIBLE);
                                    r.setVisibility(View.INVISIBLE);
                                    tv.setVisibility(View.VISIBLE);
                                }

                            }


                                if(obj.optString("responce").equals("error")) {
                                    Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();

                                    r.setVisibility(View.INVISIBLE);
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

                        r.setVisibility(View.INVISIBLE);
//                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                        if(!isInternetConnection())
                            noconnection.setVisibility(View.VISIBLE);
                        else
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();

                        //Toast.makeText(getApplicationContext(),"Failure (" + error.networkResponse.statusCode + ")",Toast.LENGTH_SHORT).show();

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
        r.setVisibility(View.INVISIBLE);
        ear = new String[dataModelArrayList.size()];
        eidar = new String[dataModelArrayList.size()];
        items=new ArrayList<>();
        for(int i=0;i<dataModelArrayList.size();i++) {
            ear[i] = dataModelArrayList.get(i).getName();
            eidar[i] = dataModelArrayList.get(i).getId();
            items.add(dataModelArrayList.get(i).getName());
        }


spinnerDialog=new SpinnerDialog(this,items,"Select EMPLOYER");
        spinnerDialog.setTitleColor(getResources().getColor(R.color.spinertext));
         spinnerDialog.setSearchTextColor(getResources().getColor(R.color.spinertext));
        spinnerDialog.setItemColor(getResources().getColor(R.color.spinertext));
        spinnerDialog.setCloseColor(getResources().getColor(R.color.spinertext));

        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {

                eid=eidar[i];
                enamet.setText(ear[i]);

            }
        });


    }





    private void jobt()
    {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, job_t,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {
                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setCrid(dataobj.getString("id"));
                                    playerModel.setWeight(dataobj.getString("jobtitle"));
                                    playerModel.setProdid(dataobj.getString("jobid"));
                                    dataModelArrayList.add(playerModel);


                                }

                                if (dataModelArrayList.size() > 0) {
                                    setupListview1();
                                }
                            }

                            if(obj.optString("responce").equals("error"))
                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();



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
//                        Toast.makeText(getApplicationContext(),"Failure (" + error.networkResponse.statusCode + ")",Toast.LENGTH_SHORT).show();
                        if(!isInternetConnection())
                            noconnection.setVisibility(View.VISIBLE);
                        else
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();

                      //  Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupListview1()
    {
        items1=new ArrayList<>();
        jr = new String[dataModelArrayList.size()];
        jidar = new String[dataModelArrayList.size()];
        jtid=new String[dataModelArrayList.size()];
        for(int i=0;i<dataModelArrayList.size();i++) {
            jr[i] = dataModelArrayList.get(i).getWeight();
            jidar[i] = dataModelArrayList.get(i).getCrid();

            jtid[i] = dataModelArrayList.get(i).getProdid();
            items1.add(dataModelArrayList.get(i).getWeight());
        }

        st();
        spinnerDialog1=new SpinnerDialog(Addjob.this,items1,"Select Job Title");
        spinnerDialog1.setTitleColor(getResources().getColor(R.color.spinertext));
        spinnerDialog1.setSearchTextColor(getResources().getColor(R.color.spinertext));
        spinnerDialog1.setItemColor(getResources().getColor(R.color.spinertext));
        spinnerDialog1.setCloseColor(getResources().getColor(R.color.spinertext));

        spinnerDialog1.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {


                jobt.setText(jr[i]);

                jid=jidar[i];
                jmainid=jtid[i];
                jt(jt_u+jidar[i]);
                sk(sk_u+jtid[i]);
            }
        });


    }


    private void jt(String jt_u)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, jt_u,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {
                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setImgURL(dataobj.getString("desc"));

                                    dataModelArrayList.add(playerModel);


                                }

                                if (dataModelArrayList.size() > 0) {

                                    setupListview5();
                                }

                            }
                            else if(obj.optString("responce").equals("success1"))
                            {
                                titlet.setText("");
                            }

                            if(obj.optString("responce").equals("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();

                                titlet.setText("");
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

                      //  Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(),"Failure (" + error.networkResponse.statusCode + ")",Toast.LENGTH_SHORT).show();
                        if(!isInternetConnection())
                            noconnection.setVisibility(View.VISIBLE);
                        else
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();

                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupListview5() {

      String[]  sr = new String[dataModelArrayList.size()];
        for(int i=0;i<dataModelArrayList.size();i++) {
            sr[i] = dataModelArrayList.get(i).getImgURL();
              }

        titlet.setText(sr[0]);

        }



        private void st()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, st_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {
                                dataModelArrayList1 = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setCatid(dataobj.getString("id"));
                                    playerModel.setMessage(dataobj.getString("state"));

                                    dataModelArrayList1.add(playerModel);


                                }

                                if (dataModelArrayList1.size() > 0) {
                                    setupListview2();
                                }

                            }

                            if(obj.optString("responce").equals("error"))
                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();



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
//                        Toast.makeText(getApplicationContext(),"Failure (" + error.networkResponse.statusCode + ")",Toast.LENGTH_SHORT).show();
                        if(!isInternetConnection())
                            noconnection.setVisibility(View.VISIBLE);
                        else
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();

                       // Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupListview2()
    {
        items2=new ArrayList<>();
        sr = new String[dataModelArrayList1.size()];
        sidar = new String[dataModelArrayList1.size()];
        for(int i=0;i<dataModelArrayList1.size();i++) {
            sr[i] = dataModelArrayList1.get(i).getMessage();
            sidar[i] = dataModelArrayList1.get(i).getCatid();
            items2.add(dataModelArrayList1.get(i).getMessage());
        }


        spinnerDialog2=new SpinnerDialog(Addjob.this,items2,"Select States");
        spinnerDialog2.setTitleColor(getResources().getColor(R.color.spinertext));
        spinnerDialog2.setSearchTextColor(getResources().getColor(R.color.spinertext));
        spinnerDialog2.setItemColor(getResources().getColor(R.color.spinertext));
        spinnerDialog2.setCloseColor(getResources().getColor(R.color.spinertext));

        spinnerDialog2.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {

                statet.setText(sr[i]);
                sts=sidar[i];
                dt(dt+sidar[i]);

            }
        });


    }
    private void dt(String dt_url)
    {
        r.setVisibility(View.VISIBLE);
        //showSimpleProgressDialog(this, "Loading...","DISTRICT...",false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, dt_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {
                                dataModelArrayList1 = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setPrice(dataobj.getString("id"));
                                    playerModel.setDesc(dataobj.getString("district"));

                                    dataModelArrayList1.add(playerModel);


                                }

                                if (dataModelArrayList1.size() > 0) {
                                    r.setVisibility(View.INVISIBLE);
                                    setupListview3();
                                }
                            }

                            if(obj.optString("responce").equals("error")) {
                                r.setVisibility(View.INVISIBLE);
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
                        r.setVisibility(View.INVISIBLE);
//                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                        if(!isInternetConnection())
                            noconnection.setVisibility(View.VISIBLE);
                        else
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();

                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupListview3()
    {
        r.setVisibility(View.INVISIBLE);
        items3=new ArrayList<>();
        dr = new String[dataModelArrayList1.size()];
        didar = new String[dataModelArrayList1.size()];
        for(int i=0;i<dataModelArrayList1.size();i++) {
            dr[i] = dataModelArrayList1.get(i).getDesc();
            didar[i] = dataModelArrayList1.get(i).getPrice();
            items3.add(dataModelArrayList1.get(i).getDesc());
        }

            spinnerDialog3 = new SpinnerDialog(Addjob.this, items3, "Select District");
        spinnerDialog3.setTitleColor(getResources().getColor(R.color.spinertext));
        spinnerDialog3.setSearchTextColor(getResources().getColor(R.color.spinertext));
        spinnerDialog3.setItemColor(getResources().getColor(R.color.spinertext));
        spinnerDialog3.setCloseColor(getResources().getColor(R.color.spinertext));
        spinnerDialog3.bindOnSpinerListener(new OnSpinerItemClick() {
                @Override
                public void onClick(String s, int i) {


                    disrctt.setText(dr[i]);
                    ds=didar[i];
                    tr(tr_u+didar[i]);
                }
            });


    }


    private void tr(String tr_url)
    {
        r.setVisibility(View.VISIBLE);
        //showSimpleProgressDialog(this, "Loading...","Taluka...",false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tr_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {
                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setPrice(dataobj.getString("id"));
                                    playerModel.setDesc(dataobj.getString("taluka"));

                                    dataModelArrayList.add(playerModel);


                                }

                                if (dataModelArrayList.size() > 0) {
                                    r.setVisibility(View.INVISIBLE);
                                    setupListview4();
                                }
                            }

                            if(obj.optString("responce").equals("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();
                                r.setVisibility(View.INVISIBLE);
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
                        r.setVisibility(View.INVISIBLE);
//                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                        if(!isInternetConnection())
                            noconnection.setVisibility(View.VISIBLE);
                        else
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();

                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupListview4()
    {
        r.setVisibility(View.INVISIBLE);
        items4=new ArrayList<>();
        tr = new String[dataModelArrayList.size()];
        tidar = new String[dataModelArrayList.size()];
        for(int i=0;i<dataModelArrayList.size();i++) {
            tr[i] = dataModelArrayList.get(i).getDesc();
            tidar[i] = dataModelArrayList.get(i).getPrice();
            items4.add(dataModelArrayList.get(i).getDesc());
        }

        spinnerDialog4 = new SpinnerDialog(Addjob.this, items4, "Select Taluka");
        spinnerDialog4.setTitleColor(getResources().getColor(R.color.spinertext));
        spinnerDialog4.setSearchTextColor(getResources().getColor(R.color.spinertext));
        spinnerDialog4.setItemColor(getResources().getColor(R.color.spinertext));
        spinnerDialog4.setCloseColor(getResources().getColor(R.color.spinertext));

        spinnerDialog4.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {

                ts=tidar[i];
                talukat.setText(tr[i]);
            }
        });


    }



    private void sk(String sk_url)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, sk_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {
                                dataModelArrayList1 = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setProdid(dataobj.getString("id"));
                                    playerModel.setName(dataobj.getString("skill"));
                                    dataModelArrayList1.add(playerModel);


                                }

                                if (dataModelArrayList1.size() > 0) {
                                    setupListview6();
                                }

                            }

                            if(obj.optString("responce").equals("error"))
                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();



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
                        if(!isInternetConnection())
                            noconnection.setVisibility(View.VISIBLE);
                        else
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();

                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupListview6()
    {


        skr = new String[dataModelArrayList1.size()];

        listOfCountries.clear();
        for(int i=0,j=1;i<dataModelArrayList1.size();i++,j++) {
            skr[i] = dataModelArrayList1.get(i).getName();

       listOfCountries.add(new MultiSelectModel(j,dataModelArrayList1.get(i).getName()));

        }


        multiSelectDialog = new MultiSelectDialog()
                .title("Show Multi Select Dialog") //setting title for dialog
                .titleSize(25)
                .positiveText("Done")
                .negativeText("Cancel")
                .setMinSelectionLimit(0)
                .setMaxSelectionLimit(listOfCountries.size())
                .multiSelectList(listOfCountries) // the multi select model list with ids and name
                .onSubmit(new MultiSelectDialog.SubmitCallbackListener() {
                    @Override
                    public void onSelected(ArrayList<Integer> selectedIds, ArrayList<String> selectedNames, String dataString) {
                        //will return list of selected IDS
                        for (int i = 0; i < selectedIds.size(); i++) {


                            dataString = dataString.replaceAll(", ", ",");
                            skillt.setText(dataString);
                        }


                        Toast.makeText(getApplicationContext(),dataString,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG,"Dialog cancelled");

                    }
                });

    }












    private void end()
    {

        mins=mint.getText().toString().trim();
        maxs=maxt.getText().toString().trim();
        nos=opt.getText().toString().trim();
        jds=titlet.getText().toString().trim();
        sks=skillt.getText().toString().trim();
        jts=jobtt.getText().toString().trim();
        its=inrt.getText().toString().trim();



        r.setVisibility(View.VISIBLE);
        //showSimpleProgressDialog(this, "Loading...","Posting...",false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, job_submit,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {

                                r.setVisibility(View.INVISIBLE);
                                String s= obj.getString("data").trim();
                                showCustomDialog(s);

                            }



                            if(obj.optString("responce").equals("error")) {
                                r.setVisibility(View.INVISIBLE);
                               // Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();
                                String s= obj.getString("data").trim();
                                showCustomDialog1(s);
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
                        r.setVisibility(View.INVISIBLE);
//                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                        if(!isInternetConnection())
                            noconnection.setVisibility(View.VISIBLE);
                        else
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();

                    }
                }){
            protected Map<String,String> getParams()throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("eid",eid);
                params.put("bdid",ids);
                params.put("jobid",jid);
                params.put("mins",mins);
                params.put("maxs",maxs);
                params.put("opening",nos);
                params.put("state",sts);
                params.put("district",ds);
                params.put("taluka",ts);
                params.put("jobd",jds);
                params.put("jobt",jts);
                params.put("intd",its);
                params.put("qua",eds);
                params.put("exp",exps);
                params.put("gen",gs);
                params.put("eng",es);
                params.put("jid",jmainid);
                params.put("skill",sks);
                return params;
            }
        };

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);

    }




    private boolean check()
    {
        if(enamet.getText().toString().length()==0) {
            enamet.setError("Please Enter Employer Name");
            enamet.requestFocus();
            return false;
        }
        if(jobt.getText().toString().length()==0)
        {
            jobt.setError("Please Enter Job Title");

            jobt.requestFocus();
            return false;

        }
        if(mint.getText().toString().trim().length()==0 || maxt.getText().toString().trim().length()==0 )
        {
            maxt.setError("Please Enter Min ANd Max Salary");

            maxt.requestFocus();
            return false;
        }
        if(opt.getText().toString().length()==0) {

            opt.setError( "Please enter No of Opening");

            opt.requestFocus();
            return false;
        }
        if(statet.getText().toString().length()==0) {

            statet.setError( "Please Enter State");
            statet.requestFocus();

            return false;
        }
        if(disrctt.getText().toString().matches("")) {

            disrctt.setError( "Please Enter District");
            disrctt.requestFocus();

            return false;
        }
        if(talukat.getText().toString().matches("")) {

            talukat.setError( "Please Enter Taluka");
            talukat.requestFocus();

            return false;
        }



       return  true;
    }

    private boolean check2()
    {

        if(eds.length()==0) {

            Toast.makeText(getApplicationContext(),"Please Select Qualification",Toast.LENGTH_SHORT).show();

            return false;
        }
        if(exps.length()==0) {


            Toast.makeText(getApplicationContext(),"Please Select Experience",Toast.LENGTH_SHORT).show();

            return false;
        }
        if(gs.length()==0) {

            Toast.makeText(getApplicationContext(),"Please Select Gender",Toast.LENGTH_SHORT).show();

            return false;
        }

        if(es.length()==0) {

            Toast.makeText(getApplicationContext(),"Please Select English",Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }

    private boolean check3()
    {
        if(titlet.getText().toString().matches("")) {

            titlet.setError( "Please Enter Job Description");
            titlet.requestFocus();

            return false;
        }

        if(skillt.getText().toString().matches("")) {

            skillt.setError( "Please Enter Skills");
            skillt.requestFocus();

            return false;
        }
        if(jobtt.getText().toString().matches("")) {

            jobtt.setError( "Please Enter Job Timing");
            jobtt.requestFocus();

            return false;
        }
        if(inrt.getText().toString().matches("")) {

            inrt.setError( "Please Enter Job Interview Details");
            inrt.requestFocus();

            return false;
        }

        return true;
    }
    private void showCustomDialog(String s) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dilog_success);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView textView=dialog.findViewById(R.id.title);
        textView.setText(s);

        TextView textView1=dialog.findViewById(R.id.content);
        textView1.setText(enamet.getText().toString().trim());
        dialog.findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), ((AppCompatButton) v).getText().toString() + " Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Addjob.this, MainActivity.class);
                startActivity(i);

                dialog.dismiss();

            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    private void showCustomDialog1(final String s) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dilog_failed);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        TextView textView=dialog.findViewById(R.id.title);
        textView.setText(s);
        TextView textView1=dialog.findViewById(R.id.content);
        textView1.setText(enamet.getText().toString().trim());
        dialog.findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(), ((AppCompatButton) v).getText().toString() + " Clicked", Toast.LENGTH_SHORT).show();



                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


    @Override
    public void onBackPressed()
    {
        Intent  intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
