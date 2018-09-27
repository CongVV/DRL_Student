package hcmue.congvu.drlstudent.Controller.CreateClassController;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hcmue.congvu.drlstudent.AppConstants.AppUrl;
import hcmue.congvu.drlstudent.Model.StudentModel.StudentItem;
import hcmue.congvu.drlstudent.View.CreateClassView.ViewProcessCreateClassSecond;


/**
 * Created by CongVu on 25/09/2018.
 */
public class ControllerLogicProcessCreateClassSecond extends AppUrl implements ControllerImpProcessCreateClassSecond {

    Context context;
    ViewProcessCreateClassSecond viewProcessCreateClassSecond;

    public ControllerLogicProcessCreateClassSecond(Context context, ViewProcessCreateClassSecond viewProcessCreateClassSecond) {
        this.context = context;
        this.viewProcessCreateClassSecond = viewProcessCreateClassSecond;
    }

    @Override
    public void checkUsername(final String username) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CHECK_USERNAME_ADD_CLASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.equals("fail")){
                            int idUser = Integer.parseInt(response);
                            viewProcessCreateClassSecond.checkUsernameAddClass(idUser);
                        }
                        else{
                            viewProcessCreateClassSecond.checkUsernameAddClass(-10);
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
                params.put("username",username);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void saveCreateClass(final ArrayList<StudentItem> studentItemArrayList, final String nameClass, final int idUser, final int idSchool) {
        Gson gson = new GsonBuilder().create();
        final JsonArray jsonArrayClassList = gson.toJsonTree(studentItemArrayList).getAsJsonArray();
        //Log.i("json", jsonArrayClassList.toString());
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_CLASS_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.i("save", response);
                        if(!response.equals("fail")){
                            viewProcessCreateClassSecond.createClassSucess();
                        }
                        else{
                            viewProcessCreateClassSecond.createClassFail();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errVolley",error.toString());
                        viewProcessCreateClassSecond.createClassFail();
                        //Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("className", nameClass);
                params.put("idUser", String.valueOf(idUser));
                params.put("idSchool", String.valueOf(idSchool));
                params.put("jsonClassList", jsonArrayClassList.toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
