package com.naukri.naukrisuchakkendra.Candidate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
import com.naukri.naukrisuchakkendra.DataModel;
import com.naukri.naukrisuchakkendra.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Recomandjobs extends AppCompatActivity {

    ArrayList<DataModel> dataModelArrayList;
    ListView list;
    String request_view = "http://naukrisuchak.com/app/api/candidate/recommended_jobs.php";
    TextView tv;
    String ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomandjobs);
        Toolbar toolbar=findViewById(R.id.toolbarA);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("Naukri Suchak Kendra");
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
        }

        list=findViewById(R.id.viewe);
        tv=findViewById(R.id.tvev);
        tv.setVisibility(View.INVISIBLE);
        emp();



    }
    private void emp()
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

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setId(dataobj.getString("jobid"));
                                    playerModel.setCrid(dataobj.getString("empid"));
                                    playerModel.setJob(dataobj.getString("jobtitle"));
                                    playerModel.setDesc(dataobj.getString("jobdes"));
                                    playerModel.setMessage(dataobj.getString("jobtime"));
                                    playerModel.setImgURL(dataobj.getString("jobint"));
                                    playerModel.setPrice(dataobj.getString("skill"));
                                    playerModel.setProdid(dataobj.getString("apply"));
                                    playerModel.setQuantity(dataobj.getString("min"));
                                    playerModel.setWeight(dataobj.getString("max"));
                                    playerModel.setName(dataobj.getString("distr"));
                                    playerModel.setTaluka(dataobj.getString("taluka"));
                                    playerModel.setExpe(dataobj.getString("exp"));
                                    playerModel.setDate(dataobj.getString("time_date"));
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
        ListAdapter listAdapter = new recomandedAdapter(this, dataModelArrayList);
        list.setAdapter(listAdapter);
    }
}
