package hcmue.congvu.drlstudent.View.UserInfoView;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import hcmue.congvu.drlstudent.Controller.SignUpController.ControllerLogicProcessSignUp;
import hcmue.congvu.drlstudent.Controller.UserInfoController.ControllerLogicProcessUserInfo;
import hcmue.congvu.drlstudent.Model.SchoolModel.SchoolAdapter;
import hcmue.congvu.drlstudent.Model.SchoolModel.SchoolItem;
import hcmue.congvu.drlstudent.Model.UserModel.UserInfo;
import hcmue.congvu.drlstudent.Model.UserModel.UserItem;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.HomeView.HomeActivity;

/**
 * Created by CongVu on 30/08/2018.
 */
public class UserInfoActivity extends AppCompatActivity implements ViewProcessUserInfo, View.OnClickListener, ResetPassDialog.ResetPassDialogListener {
    private int idUser;
    private ArrayList<SchoolItem> mSchoolList;
    private SchoolAdapter mSchoolAdapter;
    private Button btn_update_pass, btn_birthday, btn_update, btn_avatar, btn_home_activity;
    private EditText edt_fullname, edt_email, edt_address, edt_username, edt_password, edt_repassword;
    private ImageView imgView_avatar;
    private Spinner spinner_school;
    private RadioGroup radio_gender;
    private RadioButton radioButton;
    private int idSchool;
    private String birthdayUser;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private UserInfo userInfo;
    private UserItem userItem;
    private Dialog dialog;
    private ProgressBar progressBarUpdateUserInfo;
    private String avatar = "";
    public  String newPass;
    private Bitmap bitmap;
    private String imageData;
    private ControllerLogicProcessUserInfo controllerLogicProcessUserInfo = new ControllerLogicProcessUserInfo(this, this);
    final int CODE_GALLERY_REQUEST = 999;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Bundle bundle = this.getIntent().getExtras();
        idUser = bundle.getInt("userId");
        avatar = bundle.getString("avatar");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        btn_update_pass     = (Button)      findViewById(R.id.btn_update_pass);
        btn_update          = (Button)      findViewById(R.id.btn_update);
        btn_home_activity   = (Button)      findViewById(R.id.btn_home_activity);
        btn_birthday        = (Button)      findViewById(R.id.btn_birthday);
        btn_avatar          = (Button)      findViewById(R.id.btn_avatar);
        radio_gender        = (RadioGroup)  findViewById(R.id.radio_gender);
        edt_fullname        = (EditText)    findViewById(R.id.edt_fullname);
        edt_email           = (EditText)    findViewById(R.id.edt_email);
        edt_address         = (EditText)    findViewById(R.id.edt_address);
        edt_username        = (EditText)    findViewById(R.id.edt_username);
        edt_password        = (EditText)    findViewById(R.id.edt_password);
        edt_repassword      = (EditText)    findViewById(R.id.edt_repassword);
        imgView_avatar      = (ImageView) findViewById(R.id.imgView_avatar);
        progressBarUpdateUserInfo = (ProgressBar) findViewById(R.id.progressBarUpdateUserInfo);

        btn_update.setOnClickListener(this);
        btn_update_pass.setOnClickListener(this);
        btn_home_activity.setOnClickListener(this);
        btn_birthday.setOnClickListener(this);
        btn_avatar.setOnClickListener(this);

        spinner_school = (Spinner) findViewById(R.id.spinner_school);
        userInfo = new UserInfo();
        userItem = new UserItem();

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Toast.makeText(SignUpActivity.this, btn_birthday.getText().toString(), Toast.LENGTH_SHORT).show();
                month += 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                birthdayUser = year + "-" + month + "-" + dayOfMonth;
                btn_birthday.setText(date);
            }
        };
        controllerLogicProcessUserInfo.getSchoolList();
        controllerLogicProcessUserInfo.getDataUserInfo(idUser);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update:
                progressBarUpdateUserInfo.setVisibility(View.VISIBLE);
                userInfo.setIdUser(idUser);
                userInfo.setIdSchool(idSchool);
                userInfo.setFullName(edt_fullname.getText().toString());
                userInfo.setBirthday(birthdayUser);
                userInfo.setEmail(edt_email.getText().toString());
                userInfo.setAddress(edt_address.getText().toString());

                radioButton = (RadioButton) findViewById(radio_gender.getCheckedRadioButtonId());
                if(radioButton.getText().toString().equals("Nam")){
                    userInfo.setGender(1);
                }
                else {
                    userInfo.setGender(0);
                }
                //Log.i("avatarUp", imageData);
                userInfo.setAvatar(imageData);

                controllerLogicProcessUserInfo.updateUserInfo(userInfo);
                break;
            case R.id.btn_update_pass:
                openDialog();
                break;
            case R.id.btn_home_activity:
                Intent intentHome = new Intent(UserInfoActivity.this, HomeActivity.class);
                intentHome.putExtra("userId", idUser);
                intentHome.putExtra("avatar", avatar);
                intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentHome);
                break;
            case R.id.btn_birthday:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(UserInfoActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            case R.id.btn_avatar:
                ActivityCompat.requestPermissions(
                        UserInfoActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST
                );
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CODE_GALLERY_REQUEST){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                //Toast.makeText(this, String.valueOf(requestCode), Toast.LENGTH_SHORT).show();
                startActivityForResult(Intent.createChooser(intent, "Select Image"), CODE_GALLERY_REQUEST);
            }
            else {
                Toast.makeText(this, "Bạn không được quyền truy cập Bộ Sưu Tập!!!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data != null){
            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                //Toast.makeText(this, "in here", Toast.LENGTH_SHORT).show();
                imgView_avatar.setImageBitmap(bitmap);
                imageData = imageToString(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                //Log.e("errImg", e.toString());
            }
        }
        //Toast.makeText(this, String.valueOf(resultCode)+"-"+String.valueOf(RESULT_OK), Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setDataUserInfo(UserInfo userInfo) {
        edt_fullname.setText(userInfo.getFullName());
        btn_birthday.setText(userInfo.getBirthday());

        birthdayUser = userInfo.getBirthday();

        if(userInfo.getGender() == 1){
            radio_gender.check(R.id.raio_male);
        }
        else{
            radio_gender.check(R.id.raio_female);
        }

        try {
            int i=0;
            for (SchoolItem item:mSchoolList) {
                if(item.getmIdSchool() == userInfo.getIdSchool()){
                    idSchool = item.getmIdSchool();
                    spinner_school.setSelection(i);
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        edt_email.setText(userInfo.getEmail());
        edt_address.setText(userInfo.getAddress());
        //Toast.makeText(this, userInfo.getAvatar(), Toast.LENGTH_SHORT).show();
        Glide.with(this).load(avatar).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                imgView_avatar.setImageDrawable(resource);
                imageData = "noChange";
            }
        });
       // btn_avatar.setText(userInfo.getAvatar());

        //Toast.makeText(this, userInfo.getFullName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setSpinnerSchool(JSONArray listSchool) {
        mSchoolList = new ArrayList<>();
        for (int i=0; i<listSchool.length(); i++){
            try{
                JSONObject jsonObject = listSchool.getJSONObject(i);
                SchoolItem schoolItem = new SchoolItem();
                schoolItem.setmIdSchool(jsonObject.getInt("id"));
                schoolItem.setmSchoolName(jsonObject.getString("name"));
                mSchoolList.add(schoolItem);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        mSchoolAdapter = new SchoolAdapter(this, mSchoolList);
        spinner_school.setAdapter(mSchoolAdapter);
        spinner_school.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SchoolItem clickedItem = (SchoolItem) parent.getItemAtPosition(position);
                idSchool = clickedItem.getmIdSchool();
                //String clickedSchoolName = clickedItem.getmSchoolName();
                //Toast.makeText(SignUpActivity.this, clickedSchoolName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void updateFail() {
        progressBarUpdateUserInfo.setVisibility(View.GONE);
        Toast.makeText(this, "Lỗi mạng! Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateSuccessful() {
        progressBarUpdateUserInfo.setVisibility(View.GONE);
        Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
        controllerLogicProcessUserInfo.getAvatar(idUser);

    }

    @Override
    public void resultCheckPassword(String result) {
        if(result.equals("true")){
            controllerLogicProcessUserInfo.updatePassword(idUser, newPass);
        }
        else{
            Toast.makeText(this, "Bạn nhập mật khẩu hiện tại không đúng!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updatePassword(String result) {
        if(result.equals("true")){
            Toast.makeText(this, "Cập nhật mật khẩu thành công!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Lỗi mạng! Cập nhật mật khẩu thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void resultGetAvatar(String response) {
        if(!response.equals("fail")){
            avatar = response;
        }
        //Log.i("imgAbc", avatar);
        Intent intentHome = new Intent(UserInfoActivity.this, HomeActivity.class);
        intentHome.putExtra("userId", idUser);
        intentHome.putExtra("avatar", avatar);
        intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentHome);
    }

    public void openDialog(){
        ResetPassDialog resetPassDialog = new ResetPassDialog();
        resetPassDialog.show(getSupportFragmentManager(), "Reset Password");
        /*dialog = new Dialog(UserInfoActivity.this);
        dialog.setTitle("Reset Mật Khẩu");
        dialog.setContentView(R.layout.dialog_reset_pass);
        dialog.show();*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentHome = new Intent(UserInfoActivity.this, HomeActivity.class);
        intentHome.putExtra("userId", idUser);
        intentHome.putExtra("avatar", avatar);
        intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentHome);
        finish();
    }

    @Override
    public void applyResetPass(String currentPassword, String newPassword, String reNewPassword) {
        if(newPassword.equals(reNewPassword)){
            controllerLogicProcessUserInfo.checkCurrentPassword(idUser, currentPassword);
            newPass = newPassword;
        }
        else {
            Toast.makeText(this, "Mật khẩu mới không khớp!", Toast.LENGTH_SHORT).show();
        }
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
