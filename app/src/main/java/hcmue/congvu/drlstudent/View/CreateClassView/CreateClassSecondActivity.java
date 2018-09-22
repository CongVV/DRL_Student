package hcmue.congvu.drlstudent.View.CreateClassView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.HomeView.HomeActivity;

/**
 * Created by CongVu on 07/09/2018.
 */
public class CreateClassSecondActivity extends AppCompatActivity {
    int userId;
    String avatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class_second);

        Bundle bundle = this.getIntent().getExtras();
        userId = bundle.getInt("userId");
        avatar = bundle.getString("avatar");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_nav_back:
                Intent intent = new Intent(CreateClassSecondActivity.this, HomeActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("avatar", avatar);
                startActivity(intent);
                finish();
                break;
            case R.id.item_nav_next:
                Intent intentNext = new Intent(CreateClassSecondActivity.this, HomeActivity.class);
                intentNext.putExtra("userId", userId);
                intentNext.putExtra("avatar", avatar);
                startActivity(intentNext);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentHome = new Intent(CreateClassSecondActivity.this, HomeActivity.class);
        intentHome.putExtra("userId", userId);
        intentHome.putExtra("avatar", avatar);
        startActivity(intentHome);
        finish();
    }
}
