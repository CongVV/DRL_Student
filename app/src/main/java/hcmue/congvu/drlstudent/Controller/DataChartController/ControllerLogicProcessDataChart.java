package hcmue.congvu.drlstudent.Controller.DataChartController;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
import hcmue.congvu.drlstudent.View.DataChartView.ViewProcessDataChart;

/**
 * Created by CongVu on 26/11/2018.
 */
public class ControllerLogicProcessDataChart extends AppUrl implements ControllerImpProcessDataChart {
    Context context;
    ViewProcessDataChart viewProcessDataChart;

    public ControllerLogicProcessDataChart(Context context, ViewProcessDataChart viewProcessDataChart) {
        this.context = context;
        this.viewProcessDataChart = viewProcessDataChart;
    }

    @Override
    public void getDataChartList(final int idUser) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_DATA_CHART_CLASS ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            viewProcessDataChart.setListViewClassCurrent(jsonArray);
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
