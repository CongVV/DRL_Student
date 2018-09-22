package hcmue.congvu.drlstudent.View.CurrentClassView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import org.json.JSONArray;

import hcmue.congvu.drlstudent.Controller.CurrentClassController.ControllerLogicProcessCurrentClass;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.HomeView.HomeActivity;

/**
 * Created by CongVu on 31/08/2018.
 */
public class CurrentClassActivity extends AppCompatActivity implements ViewProcessCurrentClass{
    private GridView gridViewClass;
    private ControllerLogicProcessCurrentClass controllerLogicProcessCurrentClass = new ControllerLogicProcessCurrentClass(this, this);
    private int userId;
    String avatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_class);
        Bundle bundle = this.getIntent().getExtras();
        userId = bundle.getInt("userId");
        avatar = bundle.getString("avatar");

        gridViewClass = (GridView) findViewById(R.id.gridViewClassCurrent);
    }

    @Override
    public void setGridViewClass(JSONArray jsonArrayClass) {

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
}
