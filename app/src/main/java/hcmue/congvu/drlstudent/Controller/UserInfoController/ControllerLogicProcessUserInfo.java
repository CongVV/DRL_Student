package hcmue.congvu.drlstudent.Controller.UserInfoController;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import hcmue.congvu.drlstudent.AppConstants.AppUrl;
import hcmue.congvu.drlstudent.Model.UserModel.UserInfo;
import hcmue.congvu.drlstudent.View.UserInfoView.ViewProcessUserInfo;

/**
 * Created by CongVu on 31/08/2018.
 */
public class ControllerLogicProcessUserInfo extends AppUrl implements ControllerImpProcessUserInfo {
    ViewProcessUserInfo viewProcessUserInfo;
    Context context;

    public ControllerLogicProcessUserInfo(ViewProcessUserInfo viewProcessUserInfo, Context context) {
        this.viewProcessUserInfo = viewProcessUserInfo;
        this.context = context;
    }

    @Override
    public void getDataUserInfo(final int idUser) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_USER_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            UserInfo userInfo = new UserInfo(
                                    idUser,
                                    Integer.valueOf(jsonObject.getString("idSchool")),
                                    jsonObject.getString("fullName"),
                                    jsonObject.getString("birthday"),
                                    jsonObject.getString("email"),
                                    jsonObject.getString("address"),
                                    Integer.valueOf(jsonObject.getString("gender")),
                                    jsonObject.getString("avatar")
                            );
                            viewProcessUserInfo.setDataUserInfo(userInfo);

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

    @Override
    public void getSchoolList() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_GET_SCHOOL_LIST, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("response", response.toString());
                        viewProcessUserInfo.setSpinnerSchool(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("err",error.toString());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void updateUserInfo(final UserInfo userInfo) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST_USER_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("fail")){
                            viewProcessUserInfo.updateFail();
                        }
                        else {
                            viewProcessUserInfo.updateSuccessful();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errVolley", error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("idUser", String.valueOf(userInfo.getIdUser()));
                params.put("idSchool", String.valueOf(userInfo.getIdSchool()));
                params.put("fullName", userInfo.getFullName());
                params.put("birthday", userInfo.getBirthday());
                params.put("email", userInfo.getEmail());
                params.put("address", userInfo.getAddress());
                params.put("gender", String.valueOf(userInfo.getGender()));
                params.put("avatar", userInfo.getAvatar());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
