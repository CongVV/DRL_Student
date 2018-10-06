package hcmue.congvu.drlstudent.View.UserInfoView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    private ControllerLogicProcessUserInfo controllerLogicProcessUserInfo = new ControllerLogicProcessUserInfo(this, this);

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
        progressBarUpdateUserInfo = (ProgressBar) findViewById(R.id.progressBarUpdateUserInfo);

        btn_update.setOnClickListener(this);
        btn_update_pass.setOnClickListener(this);
        btn_home_activity.setOnClickListener(this);

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
                btn_birthday.setText(birthdayUser);
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

                userInfo.setAvatar("img");

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
        }
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
        btn_avatar.setText(userInfo.getAvatar());

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
        Intent intentHome = new Intent(UserInfoActivity.this, HomeActivity.class);
        intentHome.putExtra("userId", idUser);
        intentHome.putExtra("avatar", avatar);
        intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentHome);
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
}
