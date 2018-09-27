package hcmue.congvu.drlstudent.View.CreateClassView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Controller.CreateClassController.ControllerLogicProcessCreateClass;
import hcmue.congvu.drlstudent.Model.SchoolModel.SchoolAdapter;
import hcmue.congvu.drlstudent.Model.SchoolModel.SchoolItem;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.HomeView.HomeActivity;

/**
 * Created by CongVu on 31/08/2018.
 */
public class CreateClassActivity extends AppCompatActivity implements ViewProcessCreateClass, View.OnClickListener {
    EditText edtClassName;
    //Button btnNext, btnBack;
    ArrayList<SchoolItem> mSchoolList;
    SchoolAdapter mSchoolAdapter;
    int userId, idSchool;
    String avatar;
    BottomNavigationView bottomNavigationView;
    private Spinner spinner_school;
    ControllerLogicProcessCreateClass controllerLogicProcessCreateClass = new ControllerLogicProcessCreateClass(this, this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        Bundle bundle = this.getIntent().getExtras();
        userId = bundle.getInt("userId");
        avatar = bundle.getString("avatar");

        //btnNext         = (Button) findViewById(R.id.btn_next);
        //btnBack         = (Button) findViewById(R.id.btn_back);
        edtClassName    = (EditText) findViewById(R.id.edt_create_class);
        spinner_school = (Spinner) findViewById(R.id.spinner_school);

        //btnNext.setOnClickListener(this);
        //btnBack.setOnClickListener(this);
        controllerLogicProcessCreateClass.getSchoolList();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.item_nav_back:
                            Intent intent = new Intent(CreateClassActivity.this, HomeActivity.class);
                            intent.putExtra("userId", userId);
                            intent.putExtra("avatar", avatar);
                            startActivity(intent);
                            finish();
                            break;
                        case R.id.item_nav_next:
                            /*Intent intentNext = new Intent(CreateClassActivity.this, HomeActivity.class);
                            intentNext.putExtra("userId", userId);
                            intentNext.putExtra("avatar", avatar);
                            intentNext.putExtra("className", edtClassName.getText().toString());
                            startActivity(intentNext);*/
                            if(TextUtils.isEmpty(edtClassName.getText().toString())){
                                edtClassName.setError("Tên lớp không được trống!");
                            }
                            else{
                                //controllerLogicProcessCreateClass.createClass(edtClassName.getText().toString(), userId, idSchool);
                                Intent intentCreateClassNext = new Intent(CreateClassActivity.this, CreateClassSecondActivity.class);
                                intentCreateClassNext.putExtra("userId", userId);
                                intentCreateClassNext.putExtra("avatar", avatar);
                                intentCreateClassNext.putExtra("idSchool", idSchool);
                                intentCreateClassNext.putExtra("className", edtClassName.getText().toString());
                                startActivity(intentCreateClassNext);
                            }
                            break;
                    }
                    return true;
                }
            };

    @Override
    public void onClick(View v) {
        /*switch (v.getId()){
            case R.id.btn_back:
                Intent intent = new Intent(CreateClassActivity.this, HomeActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("avatar", avatar);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_next:
                Intent intentNext = new Intent(CreateClassActivity.this, HomeActivity.class);
                intentNext.putExtra("userId", userId);
                intentNext.putExtra("avatar", avatar);
                intentNext.putExtra("className", edtClassName.getText().toString());
                startActivity(intentNext);
                break;
        }*/
    }

    @Override
    public void createClassSucess() {
        Intent intentNext = new Intent(CreateClassActivity.this, HomeActivity.class);
        intentNext.putExtra("userId", userId);
        intentNext.putExtra("avatar", avatar);
        intentNext.putExtra("className", edtClassName.getText().toString());
        startActivity(intentNext);
    }

    @Override
    public void createClassFail() {
        Toast.makeText(this, "Lỗi Mạng! Không thể tạo lớp mới!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSpinnerSchool(JSONArray listSchool) {
        mSchoolList = new ArrayList<>();
        for (int i=0; i<listSchool.length(); i++){
            try{

                JSONObject jsonObject = listSchool.getJSONObject(i);
                SchoolItem schoolItem = new SchoolItem();
                if(i==0){
                    idSchool = jsonObject.getInt("id");
                }
                schoolItem.setmIdSchool(jsonObject.getInt("id"));
                schoolItem.setmSchoolName(jsonObject.getString("name"));
                mSchoolList.add(schoolItem);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        mSchoolAdapter = new SchoolAdapter(this, mSchoolList);
        spinner_school.setAdapter(mSchoolAdapter);
        spinner_school.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SchoolItem clickedItem = (SchoolItem) parent.getItemAtPosition(position);
                idSchool = clickedItem.getmIdSchool();
                //String clickedSchoolName = clickedItem.getmSchoolName();
                //Toast.makeText(SignUpActivity.this, clickedSchoolName + " selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentHome = new Intent(CreateClassActivity.this, HomeActivity.class);
        intentHome.putExtra("userId", userId);
        intentHome.putExtra("avatar", avatar);
        startActivity(intentHome);
        finish();
    }
}
