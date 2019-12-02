package com.naukri.naukrisuchakkendra.BD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
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
import static com.naukri.naukrisuchakkendra.BD.MainActivity.basebd;

public class viewemp extends AppCompatActivity {

    ArrayList<DataModel> dataModelArrayList;
    ListView list;
    String request_view = basebd+"Viewemp.php";
    TextView tv;
    String ids;

    RelativeLayout r,noconnection;
    private ProgressBar progress_bar;
    private LinearLayout lyt_no_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewemp);
        Toolbar toolbar=findViewById(R.id.toolbarA);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("Naukri Suchak Kendra");
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
        }


        User user = PrefManager.getInstance(this).getUser1();
        ids=user.getId();
        list=findViewById(R.id.viewe);
        tv=findViewById(R.id.tvev);
        tv.setVisibility(View.INVISIBLE);

        LinearLayout containerLL =findViewById(R.id.container);

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
        // dotLoader =  findViewById(R.id.dotloader);
        r.setVisibility(View.INVISIBLE);
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

        r.setVisibility(View.VISIBLE);
       // showSimpleProgressDialog(this, "Loading...","Emp...",false);
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
                                    playerModel.setName(dataobj.getString("id"));
                                    playerModel.setCrid(dataobj.getString("cname"));
                                    playerModel.setPrice(dataobj.getString("ename"));
                                    playerModel.setWeight(dataobj.getString("contact"));
                                    playerModel.setCatid(dataobj.getString("email"));
                                    playerModel.setMessage(dataobj.getString("postjob"));
                                     dataModelArrayList.add(playerModel);

                                }

                                if (dataModelArrayList.size() > 0) {
                                    r.setVisibility(View.INVISIBLE);
                                    setupListview();
                                }
                                else
                                {
                                    r.setVisibility(View.INVISIBLE);
                                    tv.setVisibility(View.VISIBLE);
                                    list.setVisibility(View.INVISIBLE);
                                }
                            }


                            if(obj.optString("responce").equals("error")) {
                                r.setVisibility(View.INVISIBLE);
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
//                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                        r.setVisibility(View.INVISIBLE);
                //        Toast.makeText(getApplicationContext(),"Failure (" + error.networkResponse.statusCode + ")",Toast.LENGTH_SHORT).show();
                        if(!isInternetConnection())
                            noconnection.setVisibility(View.VISIBLE);
                        else
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
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
        tv.setVisibility(View.INVISIBLE);
        list.setVisibility(View.VISIBLE);
        ListAdapter listAdapter = new ViewAdapter(this, dataModelArrayList);
        list.setAdapter(listAdapter);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
