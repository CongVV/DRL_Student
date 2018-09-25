package hcmue.congvu.drlstudent.Controller.CurrentClassDetailController;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import hcmue.congvu.drlstudent.AppConstants.AppUrl;
import hcmue.congvu.drlstudent.View.CurrentClassDetailView.ViewCurrentClassDetail;


/**
 * Created by CongVu on 24/09/2018.
 */
public class ControllerLogicProcessCurrentClassDetail extends AppUrl implements ControllerImpProcessCurrentClassDetail {
    Context context;
    ViewCurrentClassDetail viewCurrentClassDetail;

    public ControllerLogicProcessCurrentClassDetail(Context context, ViewCurrentClassDetail viewCurrentClassDetail) {
        this.context = context;
        this.viewCurrentClassDetail = viewCurrentClassDetail;
    }

    @Override
    public void getCurrentClassDetailList(final int idUser, final int idCurrenClassDetail) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_CURRENT_CLASS_DETAIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            viewCurrentClassDetail.setListViewClassDetail(jsonArray);

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
                params.put("idClass",String.valueOf(idCurrenClassDetail));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
