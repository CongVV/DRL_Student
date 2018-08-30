package hcmue.congvu.drlstudent.AppManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by CongVu on 30/08/2018.
 */
public class AppSession {
    private SharedPreferences sharedPreferences;

    public AppSession(Context context){
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUsername(String username){
        sharedPreferences.edit().putString("username",username).commit();
    }

    public String getUsername(){
        String username = sharedPreferences.getString("username","");
        return username;
    }

    public void destroyUsername(String username){
        sharedPreferences.edit().putString("username","").commit();
    }
}
