package hcmue.congvu.drlstudent.View.HomeView;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import hcmue.congvu.drlstudent.Controller.HomeController.ControllerLogicProcessHome;
import hcmue.congvu.drlstudent.Model.UserModel.UserInfo;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.CreateClassView.CreateClassActivity;
import hcmue.congvu.drlstudent.View.CurrentClassView.CurrentClassActivity;
import hcmue.congvu.drlstudent.View.LogInView.LogInActivity;
import hcmue.congvu.drlstudent.View.UserInfoView.UserInfoActivity;

/**
 * Created by CongVu on 30/08/2018.
 */
public class HomeActivity extends AppCompatActivity implements ViewProcessHome, View.OnClickListener{
    private int userId;
    private UserInfo userInfoHome = new UserInfo();
    public String avatar="";
    private Button btnCreateClass, btnCurrentClass;
    private ControllerLogicProcessHome controllerLogicProcessHome = new ControllerLogicProcessHome(this, this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle = this.getIntent().getExtras();
        userId = bundle.getInt("userId");
        avatar = bundle.getString("avatar");

        btnCreateClass = (Button) findViewById(R.id.btn_create_class);
        btnCurrentClass = (Button) findViewById(R.id.btn_current_class);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/windsorb.ttf");
        btnCreateClass.setTypeface(typeface);
        btnCurrentClass.setTypeface(typeface);
        btnCreateClass.setOnClickListener(this);
        btnCurrentClass.setOnClickListener(this);


        //controllerLogicProcessHome.loadDataHome(userId);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.item_user);

        if(avatar.equals("male")){
            menuItem.setIcon(R.drawable.male_avatar);
        } else if(avatar.equals("female")){
            menuItem.setIcon(R.drawable.female_avatar);
        } else{
            Glide.with(this).load(avatar).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    menuItem.setIcon(resource);
                }
            });
        }

        //menuItem.setIcon(R.drawable.female_avatar);
        return super.onCreateOptionsMenu(menu);
    }

    /*@Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        final MenuItem menuItem = menu.findItem(R.id.item_user);
        Glide.with(this).load(userInfoHome.getAvatar()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                menuItem.setIcon(resource);
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_user:
                Intent intentUserInfo = new Intent(HomeActivity.this, UserInfoActivity.class);
                intentUserInfo.putExtra("userId", userId);
                startActivity(intentUserInfo);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create_class:
                Intent intentCreateClass = new Intent(HomeActivity.this, CreateClassActivity.class);
                intentCreateClass.putExtra("userId", userId);
                intentCreateClass.putExtra("avatar", avatar);
                startActivity(intentCreateClass);
                break;
            case R.id.btn_current_class:
                startActivity(new Intent(HomeActivity.this, CurrentClassActivity.class));
                break;
        }
    }

    @Override
    public void getDataUser(final UserInfo userInfo) {
        //userInfoHome.setUserInfo(userInfo);
        userInfoHome.setIdUser(userInfo.getIdUser());
        userInfoHome.setIdSchool(userInfo.getIdSchool());
        userInfoHome.setFullName(userInfo.getFullName());
        userInfoHome.setBirthday(userInfo.getBirthday());
        userInfoHome.setEmail(userInfo.getEmail());
        userInfoHome.setAddress(userInfo.getAddress());
        userInfoHome.setAvatar(userInfo.getAvatar());
        avatar = userInfoHome.getAvatar().toString();
    }
}
