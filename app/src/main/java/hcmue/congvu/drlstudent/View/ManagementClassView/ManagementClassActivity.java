package hcmue.congvu.drlstudent.View.ManagementClassView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Controller.ManagementClassController.ControllerLogicProcessManagementClass;
import hcmue.congvu.drlstudent.Model.StudentModel.StudentClassItem;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 18/11/2018.
 */
public class ManagementClassActivity extends AppCompatActivity implements ViewProcessManagementClass {
    int idUser, idClass, numberStudent;
    BottomNavigationView bottomNavigationView;
    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;
    public FragmentManagementClassList fragmentManagementClassList;
    public FragmentManagementTypeStudent fragmentManagementTypeStudent;
    public ControllerLogicProcessManagementClass controllerLogicProcessManagementClass = new ControllerLogicProcessManagementClass(this, this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_class);

        Bundle bundle = this.getIntent().getExtras();

        if(bundle!=null){
            idUser = bundle.getInt("idUser");
            idClass = bundle.getInt("idClass");
            numberStudent = bundle.getInt("numberStudent");
        }
        else{
            Toast.makeText(this, "Lỗi Mạng!", Toast.LENGTH_LONG).show();
        }

        bottomNavigationView = findViewById(R.id.navi_activity_management_class);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        fragmentManager = getFragmentManager();
        fragmentManagementClassList = new FragmentManagementClassList();
        fragmentManagementTypeStudent = new FragmentManagementTypeStudent();
        openFragmentManagementClassList();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.item_management_class_list:
                    openFragmentManagementClassList();
                    break;
                case R.id.item_type_student:
                    openFragmentManagementTypeStudent();
                    break;
            }
            return true;
        }
    };

    public void openFragmentManagementClassList(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragmentManagementClassList, "managementClassList");
        Bundle bundle = new Bundle();
        bundle.putInt("idClass", idClass);
        bundle.putInt("idUser", idUser);
        bundle.putInt("numberStudent", numberStudent);
        fragmentManagementClassList.setArguments(bundle);
        fragmentTransaction.commit();
        controllerLogicProcessManagementClass.getClassList(idClass);


    }

    public void openFragmentManagementTypeStudent(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragmentManagementTypeStudent, "managementTypeStudent");
        Bundle bundle = new Bundle();
        bundle.putInt("idClass", idClass);
        bundle.putInt("idUser", idUser);
        fragmentManagementTypeStudent.setArguments(bundle);
        fragmentTransaction.commit();
        controllerLogicProcessManagementClass.getClassListTypeStudent(idClass);
    }

    @Override
    public void setStudentClassList(JSONArray jsonArray) {
        ArrayList<StudentClassItem> arrStudentClass= new ArrayList<>();
        FragmentManagementClassList fragment = (FragmentManagementClassList) getFragmentManager().findFragmentByTag("managementClassList");
        for(int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                StudentClassItem item = new StudentClassItem();
                item.setIdClass(idClass);
                item.setIdUser(jsonObject.getInt("idUser"));
                item.setUserNameStudent(jsonObject.getString("username"));
                item.setFullNameStudent(jsonObject.getString("fullname"));
                item.setTypeStudentClass(jsonObject.getInt("typeStudent"));
                arrStudentClass.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        fragment.setDataManagementClassList(arrStudentClass);
    }

    @Override
    public void setClassListTypeStudent(JSONArray jsonArray) {
        ArrayList<StudentClassItem> arrStudentClass= new ArrayList<>();
        FragmentManagementTypeStudent fragment = (FragmentManagementTypeStudent) getFragmentManager().findFragmentByTag("managementTypeStudent");
        for(int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                StudentClassItem item = new StudentClassItem();
                item.setIdClass(idClass);
                item.setIdUser(jsonObject.getInt("idUser"));
                item.setUserNameStudent(jsonObject.getString("username"));
                item.setFullNameStudent(jsonObject.getString("fullname"));
                item.setTypeStudentClass(jsonObject.getInt("typeStudent"));
                arrStudentClass.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        fragment.setDataManagementTypeStudent(arrStudentClass);
    }

    @Override
    public void resultDeleteStudentClass(boolean result) {
        if(result){
            Toast.makeText(this, "Xóa Sinh Viên Thành Công!", Toast.LENGTH_SHORT).show();
            numberStudent--;
            FragmentManagementClassList fragment = (FragmentManagementClassList) getFragmentManager().findFragmentByTag("managementClassList");
            fragment.setDataManagementClassList(fragment.arrayClassList);

        }
        else{
            Toast.makeText(this, "Xóa Sinh Viên Thất Bại!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void resultTypeStudentClass(boolean result) {
        if(result){
            Toast.makeText(this, "Cập Nhật Thành Công!", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Cập Nhật Thất Bại!", Toast.LENGTH_SHORT).show();
        }
    }

    public void applyDeleteStudentClassItem(int index, int idUser, int idClass){
        FragmentManagementClassList fragment = (FragmentManagementClassList) getFragmentManager().findFragmentByTag("managementClassList");
        fragment.arrayClassList.remove(index);
        controllerLogicProcessManagementClass.deleteStudentClass(idClass, idUser, numberStudent);
    }

    public void applyUpdateTypeStudentClass(int idClass, ArrayList<StudentClassItem> arrayList){
        controllerLogicProcessManagementClass.updateTypeStudentClass(idClass, arrayList);
    }
}
