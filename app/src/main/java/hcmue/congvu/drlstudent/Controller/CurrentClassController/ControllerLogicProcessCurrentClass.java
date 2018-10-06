package hcmue.congvu.drlstudent.Controller.CurrentClassController;

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
import hcmue.congvu.drlstudent.View.CurrentClassView.ViewProcessCurrentClass;

/**
 * Created by CongVu on 11/09/2018.
 */
public class ControllerLogicProcessCurrentClass extends AppUrl implements ControllerImpProcessCurrentClass {
    Context context;
    ViewProcessCurrentClass viewProcessCurrentClass;

    public ControllerLogicProcessCurrentClass(Context context, ViewProcessCurrentClass viewProcessCurrentClass) {
        this.context = context;
        this.viewProcessCurrentClass = viewProcessCurrentClass;
    }

    @Override
    public void getCurrentClassList(final int idUser) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_CURRENT_CLASS ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("curentClass", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            viewProcessCurrentClass.setListViewClass(jsonArray);
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
