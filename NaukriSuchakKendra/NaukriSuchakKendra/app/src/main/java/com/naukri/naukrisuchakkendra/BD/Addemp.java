package com.naukri.naukrisuchakkendra.BD;

import android.app.Dialog;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.agrawalsuneet.dotsloader.loaders.LinearDotsLoader;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.naukri.naukrisuchakkendra.R;
import com.naukri.naukrisuchakkendra.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.naukri.naukrisuchakkendra.BD.MainActivity.basebd;

public class Addemp extends AppCompatActivity {


    public static final String Url_form = basebd+"Addemp.php";
    ImageView ci,cei,pi,ei,ai;
    EditText cnmaet,cenamet,phonet,emailt,adrt;
    Button submit;
    String cnmaes,cenames,phones,emails,adrs,ids;
    LinearLayout l1,l2,l3,l4,l5;
    RelativeLayout r,noconnection;
    private ProgressBar progress_bar;
    private LinearLayout lyt_no_connection;
    //DotLoader dotLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemp);
        Toolbar toolbar=findViewById(R.id.toolbarA);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("Employer Registration");
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
        }
        User user = PrefManager.getInstance(this).getUser1();
        ids=user.getId();
        l1=findViewById(R.id.lcn);
        l2=findViewById(R.id.lce);
        l3=findViewById(R.id.lp);
        l4=findViewById(R.id.le);
        l5=findViewById(R.id.lj);


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
       // dotLoader =  findViewById(R.id.dotloader);
        r.setVisibility(View.INVISIBLE);
        //dotLoader.setVisibility(View.INVISIBLE);
        ci=findViewById(R.id.icn);
        cei=findViewById(R.id.ice);
        pi=findViewById(R.id.ip);
        ei=findViewById(R.id.ie);
        ai=findViewById(R.id.ij);

        cnmaet=findViewById(R.id.cnmaea);
        cenamet=findViewById(R.id.enmaea);
        phonet=findViewById(R.id.phonea);
        emailt=findViewById(R.id.emaila);
        adrt=findViewById(R.id.adrA);



        submit=findViewById(R.id.submita);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check())

                { form();}
            }
        });

        noconnection=findViewById(R.id.no_connection);
        if(!isInternetConnection())
            noconnection.setVisibility(View.VISIBLE);
else
        noconnection.setVisibility(View.INVISIBLE);

        progress_bar = findViewById(R.id.progress_bar);
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

    private boolean check()
    {
        String MobilePattern = "[0-9]{10}";

        if(cnmaet.getText().toString().matches("")) {
            cnmaet.setError("Please Enter Company Name");
            l1.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_pressed_state));
            ci.setColorFilter(getResources().getColor(android.R.color.holo_red_dark));
            //            cnmaet.requestFocus();
            return false;
        }
        else
        {
            l1.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_suceess));
            ci.setColorFilter(getResources().getColor(R.color.gray));
        }

        if(!cenamet.getText().toString().contains(" "))
        {
            cenamet.setError("Please Enter full name");
            l2.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_pressed_state));
            cei.setColorFilter(getResources().getColor(android.R.color.holo_red_dark));

            //cenamet.requestFocus();
            return false;

        }
        else
        {
            l2.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_suceess));
            cei.setColorFilter(getResources().getColor(R.color.gray));
        }
        if(cenamet.getText().toString().trim().length()==0)
        {
            cenamet.setError("Please Enter full name");
            l2.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_pressed_state));
            cei.setColorFilter(getResources().getColor(android.R.color.holo_red_dark));

            //cenamet.requestFocus();
            return false;
        }
        else
        {
            l2.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_suceess));
            cei.setColorFilter(getResources().getColor(R.color.gray));
        }

        if(!phonet.getText().toString().matches(MobilePattern)) {

            phonet.setError( "Please enter valid 10 digit phone number");
            l3.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_pressed_state));
            pi.setColorFilter(getResources().getColor(android.R.color.holo_red_dark));

            //            phonet.requestFocus();
            return false;
        }
        else
        {
            l3.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_suceess));
            pi.setColorFilter(getResources().getColor(R.color.gray));
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailt.getText().toString()).matches())
        {
            emailt.setError("Please Enter correct Email");
            l4.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_pressed_state));
            ei.setColorFilter(getResources().getColor(android.R.color.holo_red_dark));

            //            emailt.requestFocus();
            return false;
        }
        else
        {
            l4.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_suceess));
            ei.setColorFilter(getResources().getColor(R.color.gray));
        }
        if(adrt.getText().toString().matches("")) {

            adrt.setError( "Please Enter Address");
//            adrt.requestFocus();
            l5.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_pressed_state));
            ai.setColorFilter(getResources().getColor(android.R.color.holo_red_dark));

            return false;
        }
        else
        {
            l5.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_suceess));
            ai.setColorFilter(getResources().getColor(R.color.gray));
        }

        return true;
    }


    public  void form()
    {
        //dotLoader.setVisibility(View.VISIBLE);
        r.setVisibility(View.VISIBLE);
            cnmaes=cnmaet.getText().toString().trim();
        cenames=cenamet.getText().toString().trim();
        phones=phonet.getText().toString().trim();
        emails=emailt.getText().toString().trim();
        adrs=adrt.getText().toString().trim();

       // showSimpleProgressDialog(this, "Loading...","Register....",false);


        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url_form,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("responce").equals("success")) {

                                r.setVisibility(View.GONE);
                                //dotLoader.setVisibility(View.GONE);
                                showCustomDialog();
//                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();



                                //Intent i = new Intent(Newadmission.this, Dashboardad.class);
                                //startActivity(i);


                            }else
                            if(obj.optString("responce").equals("error")){

                                r.setVisibility(View.GONE);
                                //dotLoader.setVisibility(View.GONE);


                                String s= obj.getString("data").trim();
                                showCustomDialog1(s);

                               // Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();

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
                r.setVisibility(View.GONE);
                //dotLoader.setVisibility(View.GONE);


                if(!isInternetConnection())
                    noconnection.setVisibility(View.VISIBLE);
                else
                    Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
                // Toast.makeText(getApplicationContext(),"Failure (" + error.networkResponse.statusCode + ")",Toast.LENGTH_LONG).show();

            }

        }){
            protected Map<String,String> getParams()throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("id",ids);
                params.put("cmpanyname",cnmaes);
                params.put("ename",cenames);
                params.put("contact",phones);
                params.put("email",emails);
                params.put("address",adrs);

                return params;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);





    }





    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dilog_success);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView textView=dialog.findViewById(R.id.content);

        textView.setText(cenames);


        dialog.findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), ((AppCompatButton) v).getText().toString() + " Clicked", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                cnmaet.setText("");
                cenamet.setText("");
                phonet.setText("");
                emailt.setText("");
                adrt.setText("");
                l1.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_default_state));
                ci.setColorFilter(getResources().getColor(R.color.gray));
                l2.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_default_state));
                cei.setColorFilter(getResources().getColor(R.color.gray));
                l3.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_default_state));
                pi.setColorFilter(getResources().getColor(R.color.gray));
                l4.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_default_state));
                ei.setColorFilter(getResources().getColor(R.color.gray));
                l5.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_default_state));
                ai.setColorFilter(getResources().getColor(R.color.gray));


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
        TextView textView=dialog.findViewById(R.id.content1);
        textView.setText(s);

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
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
