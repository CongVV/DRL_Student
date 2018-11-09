package hcmue.congvu.drlstudent.View.ActivityClassDetailView;

import android.accessibilityservice.GestureDescription;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Controller.ActivityClassDetailController.ControllerLogicProcessActivityClassDetail;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityClassItem;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityGroupAdapter;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityGroupItem;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityLevelAdapter;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityLevelItem;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityStudentInfoItem;
import hcmue.congvu.drlstudent.Model.CurrentClassModel.ClassAdapter;
import hcmue.congvu.drlstudent.Model.CurrentClassModel.ClassItem;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.CurrentClassView.CurrentClassActivity;

/**
 * Created by CongVu on 06/10/2018.
 */
public class ActivityClassDetailActivity extends AppCompatActivity implements ViewProcessActivityClassDetail {
    BottomNavigationView bottomNavigationView;
    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;
    public FragmentClassDetailActivity fragmentClassDetailActivity;
    public FragmentClassDetailList fragmentClassDetailList;
    public FragmentClassDetailCreateActivity fragmentClassDetailCreateActivity;
    public FragmentClassDetailManagement fragmentClassDetailManagement;
    public FragmentClassDetailStudentActivityInfo fragmentClassDetailStudentActivityInfo;
    int userId, idClass, idClassDetail, typeStudent;
    public int totalScores=0;
    boolean isAdmin;
    String avatar;
    private int idActivityGroup, idActivityLevel;
    public ControllerLogicProcessActivityClassDetail controllerLogicProcessActivityClassDetail = new ControllerLogicProcessActivityClassDetail(this, this);
    public ArrayList<ActivityGroupItem> mActivityGroupList = new ArrayList<>();
    public ArrayList<ActivityLevelItem> mActivityLevelList = new ArrayList<>();

    //JSONArray jActivityGroupList = new JSONArray();
    //JSONArray jActivityLevelList = new JSONArray();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_class);

        Bundle bundle = this.getIntent().getExtras();

        if(bundle!=null){
            userId = bundle.getInt("userId");
            avatar = bundle.getString("avatar");
            idClass = bundle.getInt("idClass");
            idClassDetail = bundle.getInt("idClassDetail");
            isAdmin = bundle.getBoolean("isAdmin");
            typeStudent = bundle.getInt("typeStudent");
        }
        else{
            Toast.makeText(this, "Lỗi Mạng!", Toast.LENGTH_LONG).show();
        }

        bottomNavigationView = findViewById(R.id.navi_activity_class_detail);
        if(typeStudent!=2){
            bottomNavigationView.getMenu().removeItem(R.id.item_activity_create);
            bottomNavigationView.getMenu().removeItem(R.id.item_management);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        fragmentManager = getFragmentManager();
        //fragmentTransaction = fragmentManager.beginTransaction();
        fragmentClassDetailActivity             = new FragmentClassDetailActivity();
        fragmentClassDetailList                 = new FragmentClassDetailList();
        fragmentClassDetailManagement           = new FragmentClassDetailManagement();
        fragmentClassDetailStudentActivityInfo  = new FragmentClassDetailStudentActivityInfo();
        fragmentClassDetailCreateActivity       = new FragmentClassDetailCreateActivity();
        fragmentClassDetailList.context = this;
        //fragmentTransaction.add(R.id.fragment_content, fragmentClassDetailActivity);
        //fragmentTransaction.commit();
        openFragmentActivity();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.item_activity_current:
                    openFragmentActivity();
                    break;
                case R.id.item_status_class_list:
                    openFragmentClassList();
                    break;
                case R.id.item_activity_create:
                    openFragmentCreateActivity();
                    break;
                case R.id.item_student_info:
                    openFragmentStudentActivityInfo();
                    break;
                case R.id.item_management:
                    openFragmentManagementActiviyClass();
                    break;
            }
            return true;
        }
    };

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Build.VERSION.SDK_INT > 11 && typeStudent==1){
            invalidateOptionsMenu();
            menu.findItem(R.id.item_activity_create).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void openFragmentCreateActivity(){

        fragmentManager     = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragmentClassDetailCreateActivity, "createActivity");

        Bundle bundle = new Bundle();
        bundle.putInt("idUser", userId);
        bundle.putInt("idClass", idClass);
        bundle.putInt("idClassDetail", idClassDetail);
        fragmentClassDetailCreateActivity.setArguments(bundle);

        fragmentTransaction.commit();

        controllerLogicProcessActivityClassDetail.getActivityGroupList();
        controllerLogicProcessActivityClassDetail.getActivityLevelList();
        /*final FragmentClassDetailCreateActivity fCreateActivity = (FragmentClassDetailCreateActivity) getFragmentManager().findFragmentByTag("createActivity");
        fCreateActivity.btnCreateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateTimeStart = fCreateActivity.dStart + " " + fCreateActivity.tStart,
                        dateTimeEnd = fCreateActivity.dEnd + " " + fCreateActivity.tEnd,
                        scores = fCreateActivity.edtScores.getText().toString(),
                        content = fCreateActivity.edtContent.getText().toString();
                Toast.makeText(ActivityClassDetailActivity.this, dateTimeStart, Toast.LENGTH_SHORT).show();
                Toast.makeText(ActivityClassDetailActivity.this, dateTimeEnd, Toast.LENGTH_SHORT).show();
                Toast.makeText(ActivityClassDetailActivity.this, content, Toast.LENGTH_SHORT).show();
                Toast.makeText(ActivityClassDetailActivity.this, scores, Toast.LENGTH_SHORT).show();
                Toast.makeText(ActivityClassDetailActivity.this, ""+idAcGroup, Toast.LENGTH_SHORT).show();
                Toast.makeText(ActivityClassDetailActivity.this, ""+idAcLevel, Toast.LENGTH_SHORT).show();
                //controllerLogicProcessActivityClassDetail.createAcitvityClass(userId, idClassDetail, idAcGroup, idAcLevel, dateTimeStart, dateTimeEnd, content, scores);
            }
        });*/
        /*fragmentTransaction.remove(fragmentClassDetailList);
        fragmentTransaction.remove(fragmentClassDetailActivity);*/
        /*Bundle bundle = new Bundle();
        bundle.putString("listActivityGroup", jActivityGroupList.toString());
        bundle.putString("listActivityLevel", jActivityLevelList.toString());
        fragmentClassDetailCreateActivity.setArguments(bundle);
        */
        //fragmentTransaction.add(R.id.fragment_content, fragmentClassDetailCreateActivity);

        //Toast.makeText(this, mActivityLevelList.get(2).getmName(), Toast.LENGTH_SHORT).show();

        //FragmentClassDetailCreateActivity fragment = (FragmentClassDetailCreateActivity) getFragmentManager().findFragmentByTag("createActivity");
        //fragmentClassDetailCreateActivity.setContent(mActivityGroupList, mActivityLevelList);


        //fragment.setContent(mActivityGroupList, mActivityLevelList);123

        //activityGroupAdapter = new ActivityGroupAdapter(this, mActivityGroupList);
        //activityLevelAdapter = new ActivityLevelAdapter(this, mActivityLevelList);

        //fragment.spinnerActivityGroup.setAdapter(activityGroupAdapter);
        //fragment.spinnerActivityLevel.setAdapter(activityLevelAdapter);

    }

    public void openFragmentManagementActiviyClass(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
                    /*fragmentTransaction.remove(fragmentClassDetailActivity);
                    fragmentTransaction.remove(fragmentClassDetailCreateActivity);
                    fragmentTransaction.add(R.id.fragment_content, fragmentClassDetailList);*/
        fragmentTransaction.replace(R.id.fragment_content, fragmentClassDetailManagement, "activityManagement");
        fragmentTransaction.commit();
    }

    public void openFragmentStudentActivityInfo(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragmentClassDetailStudentActivityInfo, "activityStudentInfo");

        Bundle bundle = new Bundle();
        bundle.putInt("idUser", userId);
        bundle.putInt("idClass", idClass);
        bundle.putInt("idClassDetail", idClassDetail);
        fragmentClassDetailStudentActivityInfo.setArguments(bundle);
        fragmentTransaction.commit();
        controllerLogicProcessActivityClassDetail.getActivityStudentInfo(userId, idClass, idClassDetail);
    }

    public void openFragmentActivity(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragmentClassDetailActivity, "activityClass");
        Bundle bundle = new Bundle();
        bundle.putInt("idUser", userId);
        bundle.putInt("idClass", idClass);
        bundle.putInt("idClassDetail", idClassDetail);
        fragmentClassDetailActivity.setArguments(bundle);
        fragmentTransaction.commit();
        controllerLogicProcessActivityClassDetail.getActivityClass(userId, idClass, idClassDetail);
    }

    public void openFragmentClassList(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragmentClassDetailList, "classList");
        fragmentTransaction.commit();

        controllerLogicProcessActivityClassDetail.getActivityClassList(idClass, idClassDetail);
    }

    @Override
    public void setActivityGroupList(JSONArray jsonArray) {
        mActivityGroupList = new ArrayList<>();
        FragmentClassDetailCreateActivity fragment = (FragmentClassDetailCreateActivity) getFragmentManager().findFragmentByTag("createActivity");
        for(int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ActivityGroupItem activityGroupItem = new ActivityGroupItem();
                activityGroupItem.setmId(jsonObject.getInt("id"));
                activityGroupItem.setmName(jsonObject.getString("name"));
                mActivityGroupList.add(activityGroupItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        fragment.setGroupList(mActivityGroupList);
        /*idAcGroup = mActivityGroupList.get(0).getmId();
        activityGroupAdapter = new ActivityGroupAdapter(this, mActivityGroupList);
        fragment.spinnerActivityGroup.setAdapter(activityGroupAdapter);
        fragment.spinnerActivityGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ActivityGroupItem item = (ActivityGroupItem) mActivityGroupList.get(position);
                idAcGroup = item.getmId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }

    @Override
    public void setActivityLevelList(JSONArray jsonArray) {
        mActivityLevelList = new ArrayList<>();
        FragmentClassDetailCreateActivity fragment = (FragmentClassDetailCreateActivity) getFragmentManager().findFragmentByTag("createActivity");
        for(int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ActivityLevelItem activityLevelItem = new ActivityLevelItem();
                activityLevelItem.setmId(jsonObject.getInt("id"));
                activityLevelItem.setmName(jsonObject.getString("name"));
                mActivityLevelList.add(activityLevelItem);
                //Toast.makeText(this, mActivityLevelList.get(i).getmName(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        fragment.setLevelList(mActivityLevelList);
    }

    @Override
    public void resultCreateActivityClass(Boolean result) {
        if(result == true){
            Toast.makeText(this, "Tạo Hoạt Động Thành Công!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Lỗi Mạng! Vui Lòng Thử Lại!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setActivityClass(JSONArray jsonArray) {
        FragmentClassDetailActivity fragment = (FragmentClassDetailActivity) getFragmentManager().findFragmentByTag("activityClass");
        if(jsonArray.length()==0){
            fragment.tvEmpty.setVisibility(View.VISIBLE);
            fragment.lvActivityClass.setVisibility(View.GONE);
        }
        else {
            fragment.tvEmpty.setVisibility(View.GONE);
            fragment.lvActivityClass.setVisibility(View.VISIBLE);
            ArrayList<ActivityClassItem> arrActivityClass = new ArrayList<>();
            for (int i=0; i<jsonArray.length(); i++){
                try{
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ActivityClassItem activityClassItem = new ActivityClassItem();
                    activityClassItem.setmId(jsonObject.getInt("idActivityClass"));
                    activityClassItem.setmIdGroup(jsonObject.getInt("idGroup"));
                    activityClassItem.setmIdLevel(jsonObject.getInt("idLevel"));
                    activityClassItem.setmScores(jsonObject.getInt("scores"));
                    activityClassItem.setmContent(jsonObject.getString("content"));
                    activityClassItem.setmDateTimeStart(jsonObject.getString("dateTimeStart"));
                    activityClassItem.setmDateTimeEnd(jsonObject.getString("dateTimeEnd"));
                    arrActivityClass.add(activityClassItem);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
            fragment.setDataActivityClass(arrActivityClass);
        }
    }

    @Override
    public void setActivityClassList(JSONArray jsonArray) {
        FragmentClassDetailList fragment = (FragmentClassDetailList) getFragmentManager().findFragmentByTag("classList");
        String [][] dataClassListArray = new String[jsonArray.length()][3];
        for(int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                dataClassListArray[i][0] = jsonObject.getString("username");
                dataClassListArray[i][1] = jsonObject.getString("fullname");
                dataClassListArray[i][2] = jsonObject.getString("scores");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        fragment.setDataClassList(dataClassListArray);
    }

    @Override
    public void setActivityStudentInfo(JSONArray jsonArray) {
        FragmentClassDetailStudentActivityInfo fragment = (FragmentClassDetailStudentActivityInfo) getFragmentManager().findFragmentByTag("activityStudentInfo");
        int scores = 0;
        ArrayList<ActivityStudentInfoItem> arrActivitySudentInfo = new ArrayList<>();
        for(int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ActivityStudentInfoItem item = new ActivityStudentInfoItem();
                item.setmContentActivity(jsonObject.getString("content"));
                item.setmScores(jsonObject.getInt("scores"));
                item.setmStatus(jsonObject.getInt("status"));
                if(item.getmStatus() == 1){
                    totalScores = totalScores + item.getmScores();
                    scores+=item.getmScores();
                    Log.i("sco", item.getmScores()+"");
                }
                arrActivitySudentInfo.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        fragment.totalScores = scores;
        fragment.tvTotalScores.setText(String.valueOf(scores) + " Điểm");
        Log.i("scoFinal", scores+"");
        fragment.setDataActivityStudentInfo(arrActivitySudentInfo);
    }

    public void createActivityClass(int idUser, int idClass, int idClassDetail, int idActivityGroup, int idActivityLevel, String s, String s1, String s2, String s3) {
        controllerLogicProcessActivityClassDetail.createAcitvityClass(idUser, idClass, idClassDetail, idActivityGroup, idActivityLevel, s, s1, s2, s3);
    }
}
