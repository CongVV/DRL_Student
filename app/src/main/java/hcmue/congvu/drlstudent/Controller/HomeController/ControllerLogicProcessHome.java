package hcmue.congvu.drlstudent.Controller.HomeController;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import hcmue.congvu.drlstudent.AppConstants.AppUrl;
import hcmue.congvu.drlstudent.Model.UserModel.UserInfo;
import hcmue.congvu.drlstudent.View.HomeView.ViewProcessHome;

/**
 * Created by CongVu on 03/09/2018.
 */
public class ControllerLogicProcessHome extends AppUrl implements ControllerImpProcessHome {
    Context context;
    ViewProcessHome viewProcessHome;

    public ControllerLogicProcessHome(Context context, ViewProcessHome viewProcessHome) {
        this.context = context;
        this.viewProcessHome = viewProcessHome;
    }

    @Override
    public void loadDataHome(final int idUser) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_USER_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String avatar;
                            if(!jsonObject.getString("avatar").equals("male") && !jsonObject.getString("avatar").equals("female")){
                                avatar = URL_APP_BASE + jsonObject.getString("avatar");
                            }else {
                                avatar = jsonObject.getString("avatar");
                            }

                            UserInfo userInfo = new UserInfo(
                                    idUser,
                                    Integer.valueOf(jsonObject.getString("idSchool")),
                                    jsonObject.getString("fullName"),
                                    jsonObject.getString("birthday"),
                                    jsonObject.getString("email"),
                                    jsonObject.getString("address"),
                                    Integer.valueOf(jsonObject.getString("gender")),
                                    avatar
                            );
                            Log.i("json", response.toString());
                            viewProcessHome.getDataUser(userInfo);

                        } catch (JSONException e) {
                            Log.e("errVolley",e.toString());
                            //Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errVolley",error.toString());
                        //Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("idUser",String.valueOf(idUser));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
