package hcmue.congvu.drlstudent.View.ActivityClassDetailView;

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

import hcmue.congvu.drlstudent.Controller.ActivityClassDetailController.ControllerLogicProcessActivityClassDetail;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 06/10/2018.
 */
public class ActivityClassDetailActivity extends AppCompatActivity implements ViewProcessActivityClassDetail {
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FragmentClassDetailActivity fragmentClassDetailActivity;
    FragmentClassDetailList fragmentClassDetailList;
    FragmentClassDetailCreateActivity fragmentClassDetailCreateActivity;
    int userId, idClass, idClassDetail, typeStudent;
    boolean isAdmin;
    String avatar;
    ControllerLogicProcessActivityClassDetail controllerLogicProcessActivityClassDetail = new ControllerLogicProcessActivityClassDetail(this, this);

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

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentClassDetailActivity = new FragmentClassDetailActivity();
        fragmentClassDetailList = new FragmentClassDetailList();
        fragmentClassDetailCreateActivity = new FragmentClassDetailCreateActivity();
        fragmentTransaction.add(R.id.fragment_content, fragmentClassDetailActivity);
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.item_activity_current:
                    fragmentTransaction.remove(fragmentClassDetailList);
                    fragmentTransaction.remove(fragmentClassDetailCreateActivity);
                    fragmentTransaction.add(R.id.fragment_content, fragmentClassDetailActivity);
                    fragmentTransaction.commit();
                    break;
                case R.id.item_status_class_list:
                    fragmentTransaction.remove(fragmentClassDetailActivity);
                    fragmentTransaction.remove(fragmentClassDetailCreateActivity);
                    fragmentTransaction.add(R.id.fragment_content, fragmentClassDetailList);
                    fragmentTransaction.commit();
                    break;
                case R.id.item_activity_create:
                    fragmentTransaction.remove(fragmentClassDetailList);
                    fragmentTransaction.remove(fragmentClassDetailActivity);
                    fragmentTransaction.add(R.id.fragment_content, fragmentClassDetailCreateActivity);
                    fragmentTransaction.commit();
                    break;
            }
            return true;
        }
    };
}
