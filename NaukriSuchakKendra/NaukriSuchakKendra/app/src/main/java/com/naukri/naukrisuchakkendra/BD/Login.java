package com.naukri.naukrisuchakkendra.BD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.naukri.naukrisuchakkendra.Mainboard;
import com.naukri.naukrisuchakkendra.R;
import com.naukri.naukrisuchakkendra.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.naukri.naukrisuchakkendra.BD.MainActivity.basebd;

public class Login extends AppCompatActivity {

    String URl_Login1 = basebd+"loginbd.php";
    EditText editTextPassword;
    EditText editTextUsername;
    Button login;
    private String password;
    private String username;
    ArrayList<User> usersArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUsername = findViewById(R.id.editUserad);
        editTextPassword = findViewById(R.id.editPasswordad);
        login = findViewById(R.id.btnLoginad);
        login.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                userLogin1();
            }
        });

    }

    private void userLogin1() {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URl_Login1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONObject obj = new JSONObject(response);
                            usersArrayList = new ArrayList<>();
                            if(obj.optString("responce").equals("success")) {




                                JSONObject jsonObject1 = new JSONObject(response);


                                String username,userid;
                                userid=jsonObject1.getString("b_id");
                                username=jsonObject1.getString("b_name");
                                //creating a new user object
                                User user = new User(
                                        userid,username
                                );

                                Toast.makeText(getApplicationContext(), jsonObject1.getString("data").trim(), Toast.LENGTH_LONG).show();

                                PrefManager.getInstance(getApplicationContext()).userLogin1(user);

                                Intent i = new Intent(Login.this, MainActivity.class);
                                startActivity(i);

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


                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

            }

        }){
            protected Map<String,String> getParams()throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
