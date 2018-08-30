package hcmue.congvu.drlstudent.Model.ModelSchoolTable;

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



/**
 * Created by CongVu on 28/08/2018.
 */
public class ModelSchoolTable {
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public ModelSchoolTable() {
        this.context = null;
    }

    public ModelSchoolTable(Context context) {
        this.context = context;
    }

    public void getData(){
        String url = "http://192.168.1.18//drlstudent/getdataschool.php";
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
                        //jsonSchoolList.setMJsonArray(response);



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

        /*for(int i=0; i<listSchool.length(); i++){
            try{
                JSONObject jsonObject = listSchool.getJSONObject(i);
                Toast.makeText(context, jsonObject.getString("name"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e){
                e.printStackTrace();
            }
        }*/
    }

}
