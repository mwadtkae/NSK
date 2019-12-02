package com.naukri.naukrisuchakkendra.Candidate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.naukri.naukrisuchakkendra.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Detailsjob extends AppCompatActivity {

    TextView titlev,jobdesv,jobtimev,jobintv,skillv,minv,maxv,distrv,talukav,expv;
    String title,jobdes,jobtime,jobint,skill,min,max,distr,taluka,exp,jid,empid,applystring,cid;
    Button apply;
    private String sendurl = "http://naukrisuchak.com/app/api/candidate/applyjob.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsjob);
        Toolbar toolbar=findViewById(R.id.toolbarA);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("Naukri Suchak Kendra");
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
        }

        Bundle bundle=getIntent().getExtras();

        title=bundle.getString("title");
        jobdes=bundle.getString("jobdes");
        jobtime=bundle.getString("jobtime");
        jobint=bundle.getString("jobint");
        skill=bundle.getString("skill");
        min=bundle.getString("min");
        max=bundle.getString("max");
        distr=bundle.getString("distr");
        taluka=bundle.getString("taluka");
        exp=bundle.getString("exp");

        jid=bundle.getString("jid");
        empid=bundle.getString("emid");
        applystring=bundle.getString("apply");

        titlev=findViewById(R.id.dj);
        jobdesv=findViewById(R.id.dd);
        jobtimev=findViewById(R.id.dtime);
       jobintv=findViewById(R.id.dint);
        skillv=findViewById(R.id.ds);
        minv=findViewById(R.id.dmin);
        maxv=findViewById(R.id.dmax);
        distrv=findViewById(R.id.ddi);
        talukav=findViewById(R.id.dt);
        expv=findViewById(R.id.dexp);
        apply=findViewById(R.id.app);


        if(applystring.equals("0"))
        {
            apply.setText("APPLY");
            apply.setBackgroundColor(getResources().getColor(R.color.green));
            apply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    apply(jid,empid,cid);
                }
            });
            }
        else {
            apply.setText("APPLIED");
            apply.setBackgroundColor(getResources().getColor(R.color.red));

        }



        titlev.setText(title);
        jobdesv.setText(jobdes);
        jobtimev.setText(jobtime);
        jobintv.setText(jobint);
        skillv.setText(skill.replace(","," , ")+" .");
        minv.setText(min);
        maxv.setText(max);
        distrv.setText(distr);
        talukav.setText(taluka);
        expv.setText(exp);




    }


    private void apply(final String ji, final String emid, final String cid) {



        StringRequest stringRequest=new StringRequest(Request.Method.POST, sendurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {

                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();
                                // TextView txtView = ((Activity)context).findViewById(R.id.app);
                                // txtView.setText("APPLIED");

                            }else
                            if(obj.optString("responce").equals("error")){



                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();


                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                },new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {


                Toast.makeText(getApplicationContext(),"Failure (" + error + ")",Toast.LENGTH_LONG).show();

            }

        }){
            protected Map<String,String> getParams()throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("jid",ji);
                params.put("cid","104");
                params.put("emid",emid);
                return params;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
