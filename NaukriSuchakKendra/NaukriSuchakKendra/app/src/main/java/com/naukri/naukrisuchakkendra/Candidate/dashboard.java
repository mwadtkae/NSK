package com.naukri.naukrisuchakkendra.Candidate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.naukri.naukrisuchakkendra.DataModel;
import com.naukri.naukrisuchakkendra.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class dashboard extends AppCompatActivity {
    ArrayList<DataModel> dataModelArrayList;
    TextView er,ea,es;
    String request_view = "http://naukrisuchak.com/app/api/candidate/dashboard.php";

    ListView list;
    String request_view1 = "http://naukrisuchak.com/app/api/candidate/profilejob.php";
    TextView tv;
    String ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        info();
        er=findViewById(R.id.dr);
        ea=findViewById(R.id.da);
        es=findViewById(R.id.ds);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        list=findViewById(R.id.list);
        tv=findViewById(R.id.tvev);
        tv.setVisibility(View.INVISIBLE);
        emp();


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
              //      toolbar.setTitle("Shop");
                    return true;
                case R.id.navigation_gifts:
                    intent=new Intent(dashboard.this,Recomandjobs.class);
                    startActivity(intent);
                //    toolbar.setTitle("My Gifts");
                    return true;
                case R.id.navigation_cart:
                    intent=new Intent(dashboard.this,profilecd.class);
                    startActivity(intent);
                  //  toolbar.setTitle("Cart");
                    return true;
                case R.id.navigation_profile:

                    intent=new Intent(dashboard.this,Profilecn.class);
                    startActivity(intent);
                    //toolbar.setTitle("Profile");
                    return true;
            }
            return false;
        }
    };

    private void info()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, request_view,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {
                                dataModelArrayList = new ArrayList<>();

                                JSONArray dataArray = obj.getJSONArray("data");
                                for (int i = 0; i < dataArray.length(); i++) {

                                    JSONObject dataobj = dataArray.getJSONObject(i);



                                    String shortlist, apply, status, recome;
                                    shortlist = dataobj.getString("shortlist");
                                    es.setText(shortlist);

                                    apply = dataobj.getString("apply");
                                    ea.setText(apply);
                                    status = dataobj.getString("status");
                                    recome = dataobj.getString("recome");
                                    er.setText(recome);

                                }
                            }


                            if(obj.optString("responce").equals("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();
                                // dotLoader.setVisibility(View.GONE);
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
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            protected Map<String,String> getParams()throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("user_id","104");
                return params;

            }
        };



        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void emp()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, request_view1,
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
                                    playerModel.setId(dataobj.getString("title"));
                                    playerModel.setCrid(dataobj.getString("exp"));
                                    playerModel.setJob(dataobj.getString("status"));
                                    dataModelArrayList.add(playerModel);
                                }

                                if (dataModelArrayList.size() > 0) {
                                    setupListView();
                                }
                                else
                                {
                                    tv.setVisibility(View.VISIBLE);
                                    list.setVisibility(View.INVISIBLE);
                                }
                            }


                            if(obj.optString("responce").equals("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();
                                // dotLoader.setVisibility(View.GONE);
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
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            protected Map<String,String> getParams()throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("user_id","104");
                return params;

            }
        };



        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupListView()
    {
        tv.setVisibility(View.INVISIBLE);
        list.setVisibility(View.VISIBLE);
        ListAdapter listAdapter = new ProfielAdapter(this, dataModelArrayList);
        list.setAdapter(listAdapter);
    }


}
