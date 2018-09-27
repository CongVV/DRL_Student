package hcmue.congvu.drlstudent.View.CreateClassView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Controller.CreateClassController.ControllerLogicProcessCreateClass;
import hcmue.congvu.drlstudent.Controller.CreateClassController.ControllerLogicProcessCreateClassSecond;
import hcmue.congvu.drlstudent.Model.StudentModel.StudentAdapter;
import hcmue.congvu.drlstudent.Model.StudentModel.StudentItem;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.HomeView.HomeActivity;

/**
 * Created by CongVu on 07/09/2018.
 */
public class CreateClassSecondActivity extends AppCompatActivity implements ViewProcessCreateClassSecond, View.OnClickListener {
    int userId;
    int idSchool;
    String className;
    String avatar;
    TextView tvClassName;
    EditText edtUsernameStudent;
    LinearLayout linearAddStudent;
    Button btnAddStudent;
    Spinner spinnerTypeStudent;
    ListView listViewStudentClass;
    ArrayList<StudentItem> arrayStudentClass;
    StudentAdapter studentAdapter;
    BottomNavigationView bottomNavigationView;
    ProgressBar progressBarCreateClass;
    ControllerLogicProcessCreateClassSecond controllerLogicProcessCreateClassSecond = new ControllerLogicProcessCreateClassSecond(this, this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class_second);

        Bundle bundle = this.getIntent().getExtras();
        userId = bundle.getInt("userId");
        avatar = bundle.getString("avatar");
        idSchool = bundle.getInt("idSchool");
        className = bundle.getString("className");

        edtUsernameStudent = (EditText) findViewById(R.id.edtUsernameStudent);
        tvClassName = (TextView) findViewById(R.id.tvClassName);
        linearAddStudent = (LinearLayout) findViewById(R.id.linear1);
        btnAddStudent = (Button) findViewById(R.id.btn_Add_Student);
        spinnerTypeStudent = (Spinner) findViewById(R.id.spinnerStyleStudent);
        listViewStudentClass = (ListView) findViewById(R.id.listStudentOfClass);
        progressBarCreateClass = (ProgressBar) findViewById(R.id.progressBarCreateClass);

        arrayStudentClass = new ArrayList<StudentItem>();
        //arrayStudentClass.add(new StudentItem("congvu",1,1));
        //arrayStudentClass.add(new StudentItem("chotot",1,1));
        studentAdapter = new StudentAdapter(this,
                                            R.layout.add_student_row,
                                            arrayStudentClass);
        listViewStudentClass.setAdapter(studentAdapter);
        tvClassName.setText(className);

        btnAddStudent.setOnClickListener(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.item_nav_back:
                            finish();
                            break;
                        case R.id.item_nav_finish:
                            progressBarCreateClass.setVisibility(View.VISIBLE);
                            controllerLogicProcessCreateClassSecond.saveCreateClass(arrayStudentClass, className, userId, idSchool);
                            break;
                    }
                    return true;
                }
            };

    public void DeleteStudent(int position){
        arrayStudentClass.remove(position);
        studentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*Intent intentHome = new Intent(CreateClassSecondActivity.this, HomeActivity.class);
        intentHome.putExtra("userId", userId);
        intentHome.putExtra("avatar", avatar);
        startActivity(intentHome);*/
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Add_Student:
                controllerLogicProcessCreateClassSecond.checkUsername(edtUsernameStudent.getText().toString());
                break;
        }
    }

    @Override
    public void createClassSucess() {
        progressBarCreateClass.setVisibility(View.GONE);
        Toast.makeText(this, "Tạo lớp thành công!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CreateClassSecondActivity.this, HomeActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("avatar", avatar);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void createClassFail() {
        progressBarCreateClass.setVisibility(View.GONE);
        Toast.makeText(this, "Lỗi Mạng! Vui lòng kiểm tra!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkUsernameAddClass(int idUser) {
        if(idUser == -10){
            edtUsernameStudent.setError("Username không tồn tại!");
        }
        else{
            if(idUser == userId){
                edtUsernameStudent.setError("Bạn không thể thêm chính mình!");
            }
            else {
                boolean check = true;
                for (StudentItem item : arrayStudentClass) {
                    if (item.getIdUser() == idUser) {
                        check = false;
                    }
                }
                if (check == false) {
                    edtUsernameStudent.setError("Username này đã được thêm!");
                } else {
                    final String type = spinnerTypeStudent.getSelectedItem().toString();
                    final int res;
                    if (type.equals("Sinh viên")) {
                        res = 1;
                    } else {
                        res = 2;
                    }
                    arrayStudentClass.add(new StudentItem(edtUsernameStudent.getText().toString(), idUser, res));
                    //arrayStudentClass.add(studentItem);
                    studentAdapter.notifyDataSetChanged();
                }
            }
        }
        edtUsernameStudent.setText("");
    }
}
