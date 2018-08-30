package hcmue.congvu.drlstudent.Controller.SignUpController;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import hcmue.congvu.drlstudent.AppConstants.AppUrl;
import hcmue.congvu.drlstudent.View.SignUpView.ViewProcessSignUp;

/**
 * Created by CongVu on 28/08/2018.
 */
public class ControllerLogicProcessSignUp extends AppUrl implements ControllerImpProcessSignUp {
    ViewProcessSignUp viewProcessSignUp;
    Context context;


    public ControllerLogicProcessSignUp(ViewProcessSignUp viewProcessSignUp, Context context) {
        this.viewProcessSignUp = viewProcessSignUp;
        this.context = context;
    }

    @Override
    public boolean validateUser() {

        return false;
    }

    public void getSchoolList(){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_GET_SCHOOL_LIST, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("response", response.toString());
                        viewProcessSignUp.setSpinnerSchool(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.i("err",error.toString());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
