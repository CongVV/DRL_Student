package hcmue.congvu.drlstudent.View.HomeView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.LogInView.LogInActivity;
import hcmue.congvu.drlstudent.View.UserInfoView.UserInfoActivity;

/**
 * Created by CongVu on 30/08/2018.
 */
public class HomeActivity extends AppCompatActivity {
    private int userId;
    private Button btnCreateClass, btnCurrentClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle = this.getIntent().getExtras();
        userId = bundle.getInt("userId");

        btnCreateClass = (Button) findViewById(R.id.btn_create_class);
        btnCurrentClass = (Button) findViewById(R.id.btn_current_class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_user:
                Intent intentUserInfo = new Intent(HomeActivity.this, UserInfoActivity.class);
                intentUserInfo.putExtra("userId", userId);
                startActivity(intentUserInfo);
                finish();
                break;
            case R.id.item_logout:
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
