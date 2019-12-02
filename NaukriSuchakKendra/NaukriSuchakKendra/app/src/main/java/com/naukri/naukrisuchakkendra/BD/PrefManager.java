package com.naukri.naukrisuchakkendra.BD;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.naukri.naukrisuchakkendra.Mainboard;
import com.naukri.naukrisuchakkendra.User;

public class PrefManager {


    //the constants
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref1";
    private static final String KEY_USERNAME = "keyusername1";
    private static final String KEY_USERID = "keyuserid1";
    private static final String KEY_USEREMAIL = "keyuseremail1";
    private static final String KEY_USERMOBILE = "keyuserno1";
    private static final String MY_PREFERENCES = "my_preferences1";
    private static PrefManager mInstance1;
    private static Context mCtx1;

    private PrefManager(Context context) {
        mCtx1 = context;
    }

    public static synchronized PrefManager getInstance(Context context) {
        if (mInstance1 == null) {
            mInstance1 = new PrefManager(context);
        }
        return mInstance1;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin1(User user) {
        SharedPreferences sharedPreferences = mCtx1.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERID, user.getId());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn1() {

        SharedPreferences sharedPreferences = mCtx1.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser1() {
        SharedPreferences sharedPreferences = mCtx1.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_USERID, null),
                sharedPreferences.getString(KEY_USERNAME, null)
        );
    }


    //this method will logout the user
    public void logout1() {
        SharedPreferences sharedPreferences = mCtx1.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();


        // After logout redirect user to Loing Activity
        Intent i = new Intent(mCtx1, Mainboard.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        mCtx1.startActivity(i);
    }


    public static boolean isFirst(Context context){
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final boolean first = reader.getBoolean("is_first", true);
        if(first){
            final SharedPreferences.Editor editor = reader.edit();
            editor.putBoolean("is_first", false);
            editor.apply();
        }
        return first;
    }
}
