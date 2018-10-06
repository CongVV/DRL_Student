package hcmue.congvu.drlstudent.View.CurrentClassDetailView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
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

/**
 * Created by CongVu on 24/09/2018.
 */
public class CurrentClassDetailActivity extends AppCompatActivity implements ViewCurrentClassDetail, CreateClassTermDialog.CreateClassTermDialogListener {
    ListView listViewClassDetail;
    ArrayList<ClassDetailItem> arrayClassDetail;
    int userId;
    int idClass;
    int typeStudent;
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
        userId = bundle.getInt("userId");
        avatar = bundle.getString("avatar");
        idClass = bundle.getInt("idClass");
        isAdmin = bundle.getBoolean("isAdmin");
        typeStudent = bundle.getInt("typeUserClass");

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


    }

    @Override
    public void setListViewClassDetail(JSONArray jsonArray) {
        arrayClassDetail = new ArrayList<ClassDetailItem>();
        for (int i=0; i<jsonArray.length(); i++){
            try{
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ClassDetailItem classDetailItem = new ClassDetailItem();
                classDetailItem.setmIdClassDetail(jsonObject.getInt("idClass"));
                classDetailItem.setmTerm(jsonObject.getInt("term"));
                classDetailItem.setmYearStart(jsonObject.getInt("yearStart"));
                classDetailItem.setmYearEnd(jsonObject.getInt("yearEnd"));
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

        listViewClassDetail.setAdapter(adapbter);
    }

    @Override
    public void resutlCreateClassDetail(int value) {
        if(value == 1){
            Toast.makeText(this, "Tạo Học Kỳ Mới Thành Công!", Toast.LENGTH_LONG).show();
            controllerLogicProcessCurrentClassDetail.getCurrentClassDetailList(userId, idClass);
        }
        else {
            Toast.makeText(this, "Lỗi Mạng! Tạo Học Kỳ Mới Thất Bại!", Toast.LENGTH_LONG).show();
        }
    }

    android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Toast.makeText(CurrentClassDetailActivity.this, "Vào đây rồi nè!", Toast.LENGTH_SHORT).show();
            switch (item.getItemId()){
                case R.id.item_nav_add:
                    openDialog();
                    break;
                case R.id.item_nav_delete:
                    break;
            }
            return true;
        }
    };

    public void openDialog(){
        CreateClassTermDialog createClassTermDialog = new CreateClassTermDialog();
        createClassTermDialog.context = CurrentClassDetailActivity.this;
        createClassTermDialog.show(getSupportFragmentManager(), "Create Term Class");
    }

    @Override
    public void applyCreateClassTerm(int yearStart, int yearEnd, int yearTerm) {
        controllerLogicProcessCurrentClassDetail.createCurrentClassDetail(userId, idClass, yearStart, yearEnd, yearTerm);
    }
}
