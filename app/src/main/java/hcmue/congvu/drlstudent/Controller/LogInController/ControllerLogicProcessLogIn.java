package hcmue.congvu.drlstudent.Controller.LogInController;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import hcmue.congvu.drlstudent.AppConstants.AppUrl;
import hcmue.congvu.drlstudent.Model.UserModel.UserItem;
import hcmue.congvu.drlstudent.View.LogInView.ViewProcessLogIn;

/**
 * Created by CongVu on 22/08/2018.
 */
public class ControllerLogicProcessLogIn extends AppUrl implements ControllerImpProcessLogIn {
    Context context;
    ViewProcessLogIn viewProcessLogIn;

    public ControllerLogicProcessLogIn(Context context, ViewProcessLogIn viewProcessLogIn){
        this.context = context;
        this.viewProcessLogIn = viewProcessLogIn;
    }

    @Override
    public void checkLogIn(final String username, final String password) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CHECK_LOG_IN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response.toString());
                            if(!jsonObject.getString("id").equals("fail")){
                            viewProcessLogIn.logInSuccessfull(jsonObject.getInt("id"));
                            }
                            else {
                                viewProcessLogIn.logInFail();
                            }

                        } catch (JSONException e) {
                            Log.e("checkDBVolley",e.toString());
                            e.printStackTrace();
                        }
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
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }


}
