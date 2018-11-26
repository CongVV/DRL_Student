package hcmue.congvu.drlstudent.Controller.ManagementClassController;

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
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hcmue.congvu.drlstudent.AppConstants.AppUrl;
import hcmue.congvu.drlstudent.Model.StudentModel.StudentClassItem;
import hcmue.congvu.drlstudent.View.ManagementClassView.ViewProcessManagementClass;

/**
 * Created by CongVu on 18/11/2018.
 */
public class ControllerLogicProcessManagementClass extends AppUrl implements ControllerImpProcessManagementClass{
    Context context;
    ViewProcessManagementClass viewProcessManagementClass;

    public ControllerLogicProcessManagementClass(Context context, ViewProcessManagementClass viewProcessManagementClass) {
        this.context = context;
        this.viewProcessManagementClass = viewProcessManagementClass;
    }

    @Override
    public void getClassList(final int idClass) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_MANAGEMENT_CLASS_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.i("respun", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            viewProcessManagementClass.setStudentClassList(jsonArray);
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
                        //Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("err",error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("idClass",String.valueOf(idClass));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void getClassListTypeStudent(final int idClass) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_MANAGEMENT_CLASS_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.i("respun", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            viewProcessManagementClass.setClassListTypeStudent(jsonArray);
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
                        //Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("err",error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("idClass",String.valueOf(idClass));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void deleteStudentClass(final int idClass, final int idUser, final int numberStudent) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DELETE_STUDENT_CLASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.i("respun", response);
                        if(response.equals("success")){
                            viewProcessManagementClass.resultDeleteStudentClass(true);
                        }
                        else {
                            viewProcessManagementClass.resultDeleteStudentClass(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("err",error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("idClass",String.valueOf(idClass));
                params.put("idUser",String.valueOf(idUser));
                params.put("numberStudent",String.valueOf(numberStudent));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void updateTypeStudentClass(final int idClass, final ArrayList<StudentClassItem> arrayList) {
        Gson gson = new GsonBuilder().create();
        final JsonArray jsonArrayTypeStudentClass = gson.toJsonTree(arrayList).getAsJsonArray();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_TYPE_STUDENT_CLASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.i("respun", response);
                        //Log.i("json", jsonArrayTypeStudentClass.toString());
                        if(response.equals("success")){
                            viewProcessManagementClass.resultTypeStudentClass(true);
                        }
                        else {
                            viewProcessManagementClass.resultTypeStudentClass(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("err",error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("idClass",String.valueOf(idClass));
                params.put("arrayTypeStudent",jsonArrayTypeStudentClass.toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
