package hcmue.congvu.drlstudent.View.CurrentClassDetailView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hcmue.congvu.drlstudent.Controller.CurrentClassDetailController.ControllerLogicProcessCurrentClassDetail;
import hcmue.congvu.drlstudent.Model.CurrentClassDetailModel.ClassDetailAdapter;
import hcmue.congvu.drlstudent.Model.CurrentClassDetailModel.ClassDetailItem;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.ActivityClassDetailView.ActivityClassDetailActivity;
import hcmue.congvu.drlstudent.View.ManagementClassView.ManagementClassActivity;

/**
 * Created by CongVu on 24/09/2018.
 */
public class CurrentClassDetailActivity extends AppCompatActivity implements ViewCurrentClassDetail, CreateClassTermDialog.CreateClassTermDialogListener, ClassDetailAdapter.ConfirmDeleteClassDetailListener, AddClassMember.AddClassMemberListener {
    ListView listViewClassDetail;
    TextView tvClassDetailEmpty;
    ArrayList<ClassDetailItem> arrayClassDetail;
    int userId;
    int idClass;
    int typeStudent, typeStudentAddNewMember, numberStudent;
    boolean isAdmin;
    android.support.design.widget.BottomNavigationView bottomNavigationItemView;
    LinearLayout linearListTerm;
    String avatar;
    ControllerLogicProcessCurrentClassDetail controllerLogicProcessCurrentClassDetail = new ControllerLogicProcessCurrentClassDetail(this, this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_class_detail);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null){
            userId = bundle.getInt("userId");
            avatar = bundle.getString("avatar");
            idClass = bundle.getInt("idClass");
            numberStudent = bundle.getInt("numberStudent");
            isAdmin = bundle.getBoolean("isAdmin");
            typeStudent = bundle.getInt("typeUserClass");
        }
        else {
            Toast.makeText(this, "Lỗi Mạng!", Toast.LENGTH_LONG).show();
        }


        tvClassDetailEmpty = (TextView) findViewById(R.id.tvClassDetailEmpty);
        listViewClassDetail = (ListView) findViewById(R.id.listViewClassCurrentDetail);
        bottomNavigationItemView = (android.support.design.widget.BottomNavigationView) findViewById(R.id.navi_current_class_detail);
        linearListTerm = (LinearLayout) findViewById(R.id.linearListTerm);

        if(typeStudent == 2){
            bottomNavigationItemView.setVisibility(View.VISIBLE);
        }
        else{
            bottomNavigationItemView.setVisibility(View.GONE);
            ViewGroup.MarginLayoutParams margin =(ViewGroup.MarginLayoutParams) listViewClassDetail.getLayoutParams();
            margin.setMargins(0, 0, 0, 0);
            listViewClassDetail.requestLayout();
        }
        bottomNavigationItemView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        controllerLogicProcessCurrentClassDetail.getCurrentClassDetailList(userId, idClass);

        listViewClassDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CurrentClassDetailActivity.this, ActivityClassDetailActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("avatar", avatar);
                intent.putExtra("idClass", idClass);
                intent.putExtra("idClassDetail", arrayClassDetail.get(position).getmIdClassDetail());
                intent.putExtra("isAdmin", isAdmin);
                intent.putExtra("typeStudent", typeStudent);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setListViewClassDetail(JSONArray jsonArray) {
        if(jsonArray.length() == 0){
            tvClassDetailEmpty.setVisibility(View.VISIBLE);
            listViewClassDetail.setVisibility(View.GONE);
        }
        else{
            tvClassDetailEmpty.setVisibility(View.GONE);
            listViewClassDetail.setVisibility(View.VISIBLE);
            arrayClassDetail = new ArrayList<ClassDetailItem>();
            for (int i=0; i<jsonArray.length(); i++){
                try{
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ClassDetailItem classDetailItem = new ClassDetailItem();
                    classDetailItem.setmIdClassDetail(jsonObject.getInt("idClass"));
                    classDetailItem.setmTerm(jsonObject.getInt("term"));
                    classDetailItem.setmYearStart(jsonObject.getInt("yearStart"));
                    classDetailItem.setmYearEnd(jsonObject.getInt("yearEnd"));
                    classDetailItem.setMtypeStudent(typeStudent);
                    classDetailItem.setmIsAdmin(isAdmin);
                    arrayClassDetail.add(classDetailItem);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
            ClassDetailAdapter adapbter = new ClassDetailAdapter(
                    CurrentClassDetailActivity.this,
                    R.layout.current_class_detail_row,
                    arrayClassDetail
            );
            adapbter.confirm = this;

            listViewClassDetail.setAdapter(adapbter);
        }
    }

    @Override
    public void resutlCreateClassDetail(int value) {
        if(value == 1){
            Toast.makeText(this, "Tạo Học Kỳ Mới Thành Công!", Toast.LENGTH_LONG).show();
            controllerLogicProcessCurrentClassDetail.getCurrentClassDetailList(userId, idClass);
        }
        else if(value == 2){
            Toast.makeText(this, "Lỗi! Bạn Tạo Hơn 8 Học Kỳ!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Lỗi Mạng! Tạo Học Kỳ Mới Thất Bại!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void resultDeleteCurrentClassDetail(boolean result) {
        if(result){
            Toast.makeText(this, "Xóa Học Kỳ thành công!", Toast.LENGTH_SHORT).show();
            controllerLogicProcessCurrentClassDetail.getCurrentClassDetailList(userId, idClass);
        }
        else{
            Toast.makeText(this, "Xóa Học Kỳ thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void resultCheckUsername(int result) {
        if(result==-1){
            Toast.makeText(this, "Username này đã có trong lớp!", Toast.LENGTH_SHORT).show();
        }
        else if(result==-2){
            Toast.makeText(this, "Username không tồn tại!", Toast.LENGTH_SHORT).show();
        }
        else{
            //Toast.makeText(this, ""+idClass + "-" + result + "-" + typeStudentAddNewMember, Toast.LENGTH_SHORT).show();
            controllerLogicProcessCurrentClassDetail.addNewClassMember(idClass, userId, result, typeStudentAddNewMember);
        }
    }

    @Override
    public void resultAddNewMember(boolean result) {
        if(result){
            Toast.makeText(this, "Thêm thành viên thành công!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Lỗi! Thêm thành viên thất bại!", Toast.LENGTH_SHORT).show();
        }

    }

    android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.item_nav_add:
                    openDialogAddTerm();
                    break;
                case R.id.item_nav_management_class:
                    Intent intent = new Intent(CurrentClassDetailActivity.this, ManagementClassActivity.class);
                    intent.putExtra("idUser", userId);
                    intent.putExtra("idClass", idClass);
                    intent.putExtra("numberStudent", numberStudent);
                    startActivity(intent);
                    break;
                case R.id.item_nav_add_member:
                    openDialogAddMember();
                    break;
            }
            return true;
        }
    };

    public void openDialogAddTerm(){
        CreateClassTermDialog createClassTermDialog = new CreateClassTermDialog();
        createClassTermDialog.context = CurrentClassDetailActivity.this;
        createClassTermDialog.show(getSupportFragmentManager(), "Create Term Class");
    }

    public void openDialogAddMember(){
        AddClassMember addClassMember = new AddClassMember();
        addClassMember.context = CurrentClassDetailActivity.this;
        addClassMember.show(getSupportFragmentManager(), "Add Class Member");
    }

    @Override
    public void applyCreateClassTerm(int yearStart, int yearEnd, int yearTerm) {
        controllerLogicProcessCurrentClassDetail.createCurrentClassDetail(userId, idClass, yearStart, yearEnd, yearTerm);
    }

    @Override
    public void deleteClassDetail(int idClassDetail) {
        controllerLogicProcessCurrentClassDetail.deleteCurrentClassDetail(idClassDetail);
    }

    @Override
    public void applyAddClassMember(String username, int typeStudent) {
        typeStudentAddNewMember = typeStudent;
        controllerLogicProcessCurrentClassDetail.checkUsername(idClass, username);
    }
}
