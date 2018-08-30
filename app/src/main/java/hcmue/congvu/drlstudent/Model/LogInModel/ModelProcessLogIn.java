package hcmue.congvu.drlstudent.Model.LogInModel;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import hcmue.congvu.drlstudent.View.LogInView.LogInActivity;

/**
 * Created by CongVu on 22/08/2018.
 */
public class ModelProcessLogIn{
    String username, password;
    Context context;


    public ModelProcessLogIn(Context context, String username, String password) {
        this.username   = username;
        this.password   = password;
        this.context    = context;
    }

    public String CheckLogInDB(){

        String url = "http://192.168.1.18/DRLStudent/checklogin.php";
        //String url = "http://google.com";
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        //String result="";

        //requestQueue.add(jsonArrayRequest);
        //JsonArrayRequest jsonArrayRequest;
        final String[] result = {""};
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("checkDBVolley",response);
                        result[0] = response.toString();
                        Toast.makeText(context, "Response is: " + response.substring(0,10), Toast.LENGTH_SHORT).show();
                        //ResultCheckLogIn(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("err","loi-loi");
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };

        /*JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Toast.makeText(ModelProcessLogIn.this, response.toString(), Toast.LENGTH_SHORT).show();
                        //String result="";
                        //result = response.toString();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(ModelProcessLogIn.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });*/
        requestQueue.add(stringRequest);

        return result[0];
    }

    public String ResultCheckLogIn(String response){
        if(response.equals("")){
            Toast.makeText(context, "Error Login", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
        }
        return response;
        /*if(check.equals("")){
            return false;
        }
        return  true;*/
    }
}
