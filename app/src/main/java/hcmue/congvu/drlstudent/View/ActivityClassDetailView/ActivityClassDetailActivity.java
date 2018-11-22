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
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityClassAdapter;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityClassItem;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityGroupAdapter;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityGroupItem;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityLevelAdapter;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityLevelItem;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityManagementItem;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityStudentInfoItem;
import hcmue.congvu.drlstudent.Model.CurrentClassDetailModel.ClassDetailAdapter;
import hcmue.congvu.drlstudent.Model.CurrentClassModel.ClassAdapter;
import hcmue.congvu.drlstudent.Model.CurrentClassModel.ClassItem;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.CurrentClassView.CurrentClassActivity;

/**
 * Created by CongVu on 06/10/2018.
 */
public class ActivityClassDetailActivity extends AppCompatActivity implements ViewProcessActivityClassDetail{
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
                    openFragmentCreateActivity(-1);
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

    public void openFragmentCreateActivity(int idActivity){

        fragmentManager     = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragmentClassDetailCreateActivity, "createActivity");

        Bundle bundle = new Bundle();
        bundle.putInt("idUser", userId);
        bundle.putInt("idClass", idClass);
        bundle.putInt("idClassDetail", idClassDetail);
        bundle.putInt("idActivity", idActivity);
        fragmentClassDetailCreateActivity.setArguments(bundle);

        fragmentTransaction.commit();

        controllerLogicProcessActivityClassDetail.getActivityGroupList();
        controllerLogicProcessActivityClassDetail.getActivityLevelList();

    }

    public void openFragmentManagementActiviyClass(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_content, fragmentClassDetailManagement, "activityManagement");
        fragmentTransaction.commit();
        controllerLogicProcessActivityClassDetail.getActivityManagement(userId, idClass, idClassDetail);
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

    public void openFragmentCreateActivity(int idUser, int idClass, int idClassDetail, int idActivity, int idGroup, int idLevel, String content, String timeStart, String timeEnd, int scores){
        //openFragmentCreateActivity(idActivity);
        bottomNavigationView.setSelectedItemId(R.id.item_activity_create);
        fragmentManager     = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragmentClassDetailCreateActivity, "createActivity");

        Bundle bundle = new Bundle();
        bundle.putInt("idUser", userId);
        bundle.putInt("idClass", idClass);
        bundle.putInt("idClassDetail", idClassDetail);
        bundle.putInt("idActivity", idActivity);
        bundle.putInt("idGroup", idGroup);
        bundle.putInt("idLevel", idLevel);
        bundle.putString("content", content);
        bundle.putString("timeStart", timeStart);
        bundle.putString("timeEnd", timeEnd);
        bundle.putInt("scores", scores);
        fragmentClassDetailCreateActivity.setArguments(bundle);

        fragmentTransaction.commit();

        //controllerLogicProcessActivityClassDetail.getActivityGroupList();
        //controllerLogicProcessActivityClassDetail.getActivityLevelList();

        Log.i("resAciti", ""+idUser+"-"+idClass+"-"+idClassDetail+"-"+idActivity+"-"+idGroup+"-"+idLevel+"-"+content+"-"+timeStart+"-"+timeEnd+"-"+scores);
        //Toast.makeText(this, ""+idUser+"-"+idClass+"-"+idClassDetail+"-"+idActivity+"-"+idGroup+"-"+idLevel+"-"+content+"-"+timeStart+"-"+timeEnd+"-"+scores, Toast.LENGTH_SHORT).show();
        //FragmentClassDetailCreateActivity fragment = (FragmentClassDetailCreateActivity) getFragmentManager().findFragmentByTag("createActivity");
        //fragment.setDataUpdateActivityClass(idUser, idClass, idClassDetail, idGroup, idLevel, content, timeStart, timeEnd, scores);
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
                    activityClassItem.setmTypeUser(jsonObject.getInt("typeUser"));
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
                    //Log.i("sco", item.getmScores()+"");
                }
                arrActivitySudentInfo.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        fragment.tvTotalScores.setText(String.valueOf(scores) + " Điểm");
        //Log.i("scoFinal", scores+"");
        fragment.setDataActivityStudentInfo(arrActivitySudentInfo);
    }

    @Override
    public void setActivityManagement(JSONArray jsonArray) {
        FragmentClassDetailManagement fragment = (FragmentClassDetailManagement) getFragmentManager().findFragmentByTag("activityManagement");
        int totalRequest = 0;
        ArrayList<ActivityManagementItem> arrActivityManagement = new ArrayList<>();
        for(int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ActivityManagementItem item = new ActivityManagementItem();
                item.setmFullname(jsonObject.getString("fullname"));
                item.setmUsername(jsonObject.getString("username"));
                item.setmActivityname(jsonObject.getString("content"));
                totalRequest++;
                arrActivityManagement.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        fragment.tvTotalActivityRequest.setText(String.valueOf(totalRequest) + " Yêu Cầu");
        //Log.i("requestFinal", totalRequest+"");
        fragment.setDataActivityManagement(arrActivityManagement);
    }

    @Override
    public void resultConfirmActivity(boolean result) {
        if(result == true){
            Toast.makeText(this, "Đã Gửi Yêu Cầu Xác Nhận!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Lỗi Mạng! Vui Lòng Thử Lại!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void resultDeleteActivityClass(boolean result) {
        if(result == true){
            Toast.makeText(this, "Hoạt Động Đã Được Xóa!", Toast.LENGTH_LONG).show();
            FragmentClassDetailActivity fragment = (FragmentClassDetailActivity) getFragmentManager().findFragmentByTag("activityClass");
            fragment.setDataActivityClass(fragment.arrayActivityClass);
        }
        else {
            Toast.makeText(this, "Lỗi Mạng! Vui Lòng Thử Lại!", Toast.LENGTH_LONG).show();
        }
    }

    public void createActivityClass(int idUser, int idClass, int idClassDetail, int idActivity, int idActivityGroup, int idActivityLevel, String s, String s1, String s2, String s3) {
        controllerLogicProcessActivityClassDetail.createAcitvityClass(idUser, idClass, idClassDetail, idActivity, idActivityGroup, idActivityLevel, s, s1, s2, s3);
    }

    public void applyConfirmAcceptActivity(int idUser, int idClass, int idClassDetail, int idActivity){
        controllerLogicProcessActivityClassDetail.confirmAcceptActivity(idUser, idClass, idClassDetail, idActivity);
    }

    public void applyUpdateClasActivity(int idUser, int idClass, int idClassDetail, int idActivity, int idGroup, int idLevel, String content, String timeStart, String timeEnd, int scores){
        openFragmentCreateActivity(idUser, idClass, idClassDetail, idActivity, idGroup, idLevel, content, timeStart, timeEnd, scores);
    }

    public void applyDeleteActivity(int index, int idUser, int idClass, int idClassDetail, int idActivity){
        FragmentClassDetailActivity fragment = (FragmentClassDetailActivity) getFragmentManager().findFragmentByTag("activityClass");
        fragment.arrayActivityClass.remove(index);
        controllerLogicProcessActivityClassDetail.deleteActivityClass(idUser, idClass, idClassDetail, idActivity);
    }

}
