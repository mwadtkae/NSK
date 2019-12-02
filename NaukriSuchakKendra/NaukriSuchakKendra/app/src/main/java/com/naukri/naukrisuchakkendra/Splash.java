package com.naukri.naukrisuchakkendra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


import com.naukri.naukrisuchakkendra.BD.MainActivity;
import com.naukri.naukrisuchakkendra.BD.PrefManager;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //ImageView im=findViewById(R.id.im);
       // Animation animation= AnimationUtils.loadAnimation(Splash.this,R.anim.myani);
        //im.startAnimation(animation);

        int SPLASH_DISPLAY_LENGTH = 2000;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {


                if (!PrefManager.getInstance(getApplicationContext()).isLoggedIn1()) {

                    Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                    // MDToast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
                }else
                   {
                    Intent intent=new Intent(getApplicationContext(), Mainboard.class);
                    startActivity(intent);
                }
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
