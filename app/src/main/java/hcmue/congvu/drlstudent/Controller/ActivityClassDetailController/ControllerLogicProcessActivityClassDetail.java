package hcmue.congvu.drlstudent.Controller.ActivityClassDetailController;

import android.content.Context;
import android.util.Log;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

import hcmue.congvu.drlstudent.AppConstants.AppUrl;
import hcmue.congvu.drlstudent.View.ActivityClassDetailView.ViewProcessActivityClassDetail;

/**
 * Created by CongVu on 09/10/2018.
 */
public class ControllerLogicProcessActivityClassDetail extends AppUrl implements ControllerImpProcessActivityClassDetail {
    Context context;
    ViewProcessActivityClassDetail viewProcessActivityClassDetail;

    public ControllerLogicProcessActivityClassDetail(Context context, ViewProcessActivityClassDetail viewProcessActivityClassDetail) {
        this.context = context;
        this.viewProcessActivityClassDetail = viewProcessActivityClassDetail;
    }

    @Override
    public void getDataListClassDetail(int idClass, int idClassDetail) {

    }

    @Override
    public void getActivityGroupList() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_GET_ACTIVITY_GROUP_LIST, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        viewProcessActivityClassDetail.setActivityGroupList(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Lỗi Mạng!", Toast.LENGTH_LONG).show();
                        Log.e("err",error.toString());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void getActivityLevelList() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_GET_ACTIVITY_LEVEL_LIST, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        viewProcessActivityClassDetail.setActivityLevelList(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Lỗi Mạng!", Toast.LENGTH_LONG).show();
                        Log.e("err",error.toString());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void createAcitvityClass(final int idUser, final int idClass, final int idClassDetail, final int idActivityGroup, final int idActivityLevel, final String dateTimeStart, final String dateTimeEnd, final String content, final String scores) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CREATE_ACTIVITY_CLASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("success")){
                            viewProcessActivityClassDetail.resultCreateActivityClass(true);
                        }
                        else {
                            viewProcessActivityClassDetail.resultCreateActivityClass(false);
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
                params.put("idUser",String.valueOf(idUser));
                params.put("idClass",String.valueOf(idClass));
                params.put("idClassDetail",String.valueOf(idClassDetail));
                params.put("idActivityGroup",String.valueOf(idActivityGroup));
                params.put("idActivityLevel",String.valueOf(idActivityLevel));
                params.put("dateTimeStart",dateTimeStart);
                params.put("dateTimeEnd",dateTimeEnd);
                params.put("content",content);
                params.put("scores",scores);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void getActivityClass(final int idUser, final int idClass, final int idClassDetail) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_ACTIVITY_CLASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            viewProcessActivityClassDetail.setActivityClass(jsonArray);
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
                params.put("idUser",String.valueOf(idUser));
                params.put("idClass",String.valueOf(idClass));
                params.put("idClassDetail",String.valueOf(idClassDetail));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
