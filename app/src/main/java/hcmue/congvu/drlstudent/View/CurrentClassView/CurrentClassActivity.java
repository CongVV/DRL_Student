package hcmue.congvu.drlstudent.View.CurrentClassView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Controller.CurrentClassController.ControllerLogicProcessCurrentClass;
import hcmue.congvu.drlstudent.Model.CurrentClassModel.ClassAdapter;
import hcmue.congvu.drlstudent.Model.CurrentClassModel.ClassItem;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.CurrentClassDetailView.CurrentClassDetailActivity;
import hcmue.congvu.drlstudent.View.HomeView.HomeActivity;

/**
 * Created by CongVu on 31/08/2018.
 */
public class CurrentClassActivity extends AppCompatActivity implements ViewProcessCurrentClass, ClassAdapter.ConfirmDeleteClassListener{
    private ListView listViewClass;
    private TextView tvClassList;
    private ArrayList<ClassItem> arrayClass;
    private ControllerLogicProcessCurrentClass controllerLogicProcessCurrentClass = new ControllerLogicProcessCurrentClass(this, this);
    private int userId;
    String avatar;
    ClassAdapter adapbter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_class);
        Bundle bundle = this.getIntent().getExtras();
        userId = bundle.getInt("userId");
        avatar = bundle.getString("avatar");

        tvClassList = (TextView) findViewById(R.id.tvEmpty);
        listViewClass = (ListView) findViewById(R.id.listViewClassCurrent);
        controllerLogicProcessCurrentClass.getCurrentClassList(userId);

        listViewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ClassItem clickedItem = (ClassItem) parent.getItemAtPosition(position);
                Intent intentClassDetail = new Intent(CurrentClassActivity.this, CurrentClassDetailActivity.class);
                intentClassDetail.putExtra("userId", userId);
                intentClassDetail.putExtra("avatar", avatar);
                intentClassDetail.putExtra("idClass", arrayClass.get(position).getmIdClass());
                intentClassDetail.putExtra("numberStudent", arrayClass.get(position).getmNumberStudent());
                intentClassDetail.putExtra("isAdmin", arrayClass.get(position).ismIsAdmin());
                intentClassDetail.putExtra("typeUserClass", arrayClass.get(position).getmTypeUserClass());

                //intentClassDetail.putExtra("idCurrentClassDetail", arrayClass.get(position).getmIdClass());
                //intentClassDetail.putExtra("idCurrentClassDetail", arrayClass.get(position).getmIdClass());
                startActivity(intentClassDetail);
                //Toast.makeText(CurrentClassActivity.this, arrayClass.get(position).getmClassName(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void setListViewClass(JSONArray jsonArrayClass) {
        if(jsonArrayClass.length()==0){
            tvClassList.setVisibility(View.VISIBLE);
            listViewClass.setVisibility(View.GONE);
        }
        else{
            tvClassList.setVisibility(View.GONE);
            listViewClass.setVisibility(View.VISIBLE);
            arrayClass = new ArrayList<ClassItem>();
            for (int i=0; i<jsonArrayClass.length(); i++){
                try{
                    JSONObject jsonObject = jsonArrayClass.getJSONObject(i);
                    ClassItem classItem = new ClassItem();
                    classItem.setmIdClass(jsonObject.getInt("idClass"));
                    classItem.setmClassName(jsonObject.getString("className"));
                    classItem.setmNumberStudent(jsonObject.getInt("numberStudent"));
                    classItem.setmIsAdmin(jsonObject.getBoolean("isAdmin"));
                    classItem.setmTypeUserClass(jsonObject.getInt("idclassUserType"));
                    arrayClass.add(classItem);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
            adapbter = new ClassAdapter(
                    CurrentClassActivity.this,
                    R.layout.current_class_row,
                    arrayClass
            );
            adapbter.confirm = this;
            listViewClass.setAdapter(adapbter);
        }
    }

    @Override
    public void resultDeleteCurrentClass(boolean result) {
        if(result){
            Toast.makeText(this, "Xóa lớp thành công!", Toast.LENGTH_SHORT).show();
            controllerLogicProcessCurrentClass.getCurrentClassList(userId);
        }
        else{
            Toast.makeText(this, "Xóa lớp thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentHome = new Intent(CurrentClassActivity.this, HomeActivity.class);
        intentHome.putExtra("userId", userId);
        intentHome.putExtra("avatar", avatar);
        startActivity(intentHome);
        finish();
    }

    @Override
    public void deleteClass(int idClass) {
        controllerLogicProcessCurrentClass.deleteCurrentClass(idClass);
    }
}
