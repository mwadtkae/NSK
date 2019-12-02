package com.naukri.naukrisuchakkendra.Candidate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.naukri.naukrisuchakkendra.DataModel;
import com.naukri.naukrisuchakkendra.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class profilecd extends AppCompatActivity {

    ArrayList<DataModel> dataModelArrayList;
    GridView list;
    TextView states,district,edu,phoneN;

    String request_view = "http://naukrisuchak.com/app/api/candidate/jobroleview.php";
    String url = "http://naukrisuchak.com/app/global/";
    String infourl="http://naukrisuchak.com/app/api/candidate/profileview.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilecd);


        list=findViewById(R.id.gridview);
        states=findViewById(R.id.ps);
        district=findViewById(R.id.pd);
        edu=findViewById(R.id.pq);
        phoneN=findViewById(R.id.pp);

        emp();
        info();
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
                                    playerModel.setName(dataobj.getString("name"));
                                    playerModel.setImgURL(url+dataobj.getString("path"));

                                    dataModelArrayList.add(playerModel);

                                }

                                if (dataModelArrayList.size() > 0) {
                                    setupListview();
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

    private void setupListview()
    {

        ListAdapter listAdapter = new roleAdapter(this, dataModelArrayList);
        list.setAdapter(listAdapter);
    }

    private void info() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, infourl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);

                            if (obj.optString("responce").equals("success")) {

                                JSONArray dataArray = obj.getJSONArray("data");

                                String phone, qualification, skill, state, dirst;
                                for (int i = 0; i < dataArray.length(); i++) {

                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    phone = dataobj.getString("phone");
                                    phoneN.setText(phone);
                                    qualification = dataobj.getString("qualification");
                                   edu.setText(qualification);
                                    skill = dataobj.getString("skill");
                                    state = dataobj.getString("state");
                                    states.setText(state);
                                    dirst = dataobj.getString("distr");
                                    district.setText(dirst);
                                    chipView(skill);

                                }


                            }


                            if (obj.optString("responce").equals("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();
                                // dotLoader.setVisibility(View.GONE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", "104");
                return params;

            }
        };


        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }




    public  void chipView(String skill)
            {
                ChipGroup chipGroup = findViewById(R.id.relativeLayout);
                chipGroup.setSingleSelection(false);
                chipGroup.setChipSpacingVertical(10);
                String[] sk=skill.split(",");
                for(String gender : sk){
                    Chip chip = new Chip(this);
                    chip.setText(gender);
                    chipGroup.addView(chip);
                }
            }
}
