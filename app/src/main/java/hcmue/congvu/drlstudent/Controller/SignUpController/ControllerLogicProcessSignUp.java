package hcmue.congvu.drlstudent.Controller.SignUpController;

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

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import hcmue.congvu.drlstudent.AppConstants.AppUrl;
import hcmue.congvu.drlstudent.Model.UserModel.UserInfo;
import hcmue.congvu.drlstudent.Model.UserModel.UserItem;
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
    public void validateUser(final String username) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CHECK_USERNAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("success")){
                            try {
                                viewProcessSignUp.resultCheckUsername(true);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            try {
                                viewProcessSignUp.resultCheckUsername(false);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("err",error.toString());
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
    public void getSchoolList(){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_GET_SCHOOL_LIST, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        viewProcessSignUp.setSpinnerSchool(response);
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

    @Override
    public void signUpUser(final UserItem userItem, final UserInfo userInfo) {
        //Toast.makeText(context, userInfo.getAvatar(), Toast.LENGTH_SHORT).show();
        Log.i("base64", userInfo.getAvatar());
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST_SIGN_UP_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                        Log.i("us", response);
                        if(response.equals("fail")){
                            viewProcessSignUp.signUpFail();
                        }
                        else {
                            viewProcessSignUp.signUpSuccessful();
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
                params.put("username",userItem.getUsername());
                params.put("password",userItem.getPassword());
                params.put("idSchool",String.valueOf(userInfo.getIdSchool()));
                params.put("fullName",userInfo.getFullName());
                params.put("birthday",userInfo.getBirthday());
                params.put("email",userInfo.getEmail());
                params.put("address",userInfo.getAddress());
                params.put("gender",String.valueOf(userInfo.getGender()));
                params.put("avatar",userInfo.getAvatar());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
