package hcmue.congvu.drlstudent.Controller.CreateClassController;

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
import hcmue.congvu.drlstudent.View.CreateClassView.ViewProcessCreateClass;

/**
 * Created by CongVu on 11/09/2018.
 */
public class ControllerLogicProcessCreateClass extends AppUrl implements ControllerImpProcessCreateClass {
    Context context;
    ViewProcessCreateClass viewProcessCreateClass;

    public ControllerLogicProcessCreateClass(Context context, ViewProcessCreateClass viewProcessCreateClass) {
        this.context = context;
        this.viewProcessCreateClass = viewProcessCreateClass;
    }

    @Override
    public void createClass(final String className, final int idUser, final int idSchool) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CREATE_CLASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        Log.i("loile", response);
                        if(response.equals("sucess")){
                            viewProcessCreateClass.createClassSucess();
                        }
                        else {
                            viewProcessCreateClass.createClassFail();
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
                params.put("className",className);
                params.put("idSchool",String.valueOf(idSchool));
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
                        viewProcessCreateClass.setSpinnerSchool(response);
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
}
