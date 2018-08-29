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

import hcmue.congvu.drlstudent.View.SignUpView.ViewProcessSignUp;

/**
 * Created by CongVu on 28/08/2018.
 */
public class ControllerLogicProcessSignUp implements ControllerImpProcessSignUp {
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
        //JsonArrayClass jsonSchoolList = new JsonArrayClass();
        //ModelSchoolTable modelSchoolTable = new ModelSchoolTable();
        //modelSchoolTable.setContext(context);
        //modelSchoolTable.getData(jsonSchoolList);
//        Toast.makeText(context, modelSchoolTable.getListSchool().toString(), Toast.LENGTH_SHORT).show();
        /*for(int i=0; i<modelSchoolTable.getListSchool().length(); i++){
            try{
                JSONObject jsonObject = modelSchoolTable.getListSchool().getJSONObject(i);
                //.put(jsonObject);
                Toast.makeText(context, jsonObject.getString("id")+jsonObject.getString("name"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        */
        //modelSchoolTable.tmp();
        //viewProcessSignUp.setSpinnerSchool(jsonSchoolList.getMJsonArray());

        /*for(int i=0; i<modelSchoolTable.getListSchool().length(); i++){
            JSONObject jsonObject = modelSchoolTable.getListSchool().getJSONObject(i);
            Toast.makeText(context, jsonObject.getString("name"), Toast.LENGTH_SHORT).show();
        }*/
        //modelSchoolTable.getListSchool();

        String url = "http://192.168.1.88//drlstudent//getdataschool.php";
        //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /*for(int i=0; i<response.length(); i++){
                            try{
                                JSONObject jsonObject = response.getJSONObject(i);
                                listSchool.put(jsonObject);
                                //Toast.makeText(context, jsonObject.getString("id")+jsonObject.getString("name"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                        }*/
                        viewProcessSignUp.setSpinnerSchool(response);



                        /*for(int i=0; i<listSchool.length(); i++){
                            try{
                                JSONObject jsonObject = listSchool.getJSONObject(i);
                                //listSchool.put(jsonObject);
                                Toast.makeText(context, jsonObject.getString("id")+jsonObject.getString("name"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                        }*/
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
