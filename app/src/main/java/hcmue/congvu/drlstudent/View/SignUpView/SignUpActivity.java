package hcmue.congvu.drlstudent.View.SignUpView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hcmue.congvu.drlstudent.Model.SchoolModel.SchoolAdapter;
import hcmue.congvu.drlstudent.Model.SchoolModel.SchoolItem;
import hcmue.congvu.drlstudent.Controller.SignUpController.ControllerLogicProcessSignUp;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.LogInView.LogInActivity;

/**
 * Created by CongVu on 24/08/2018.
 */
public class SignUpActivity extends AppCompatActivity implements ViewProcessSignUp,View.OnClickListener{

    private ArrayList<SchoolItem> mSchoolList;
    private SchoolAdapter mSchoolAdapter;
    private Button btn_login, btn_birthday, btn_signup;
    private EditText edt_fullname, edt_email, edt_phone, edt_username, edt_password, edt_repassword;
    private Spinner spinner_school;
    private RadioGroup radio_gender;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ControllerLogicProcessSignUp controllerLogicProcessSignUp = new ControllerLogicProcessSignUp(this,this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_birthday = (Button) findViewById(R.id.btn_birthday);
        radio_gender = (RadioGroup) findViewById(R.id.radio_gender);
        edt_fullname = (EditText) findViewById(R.id.edt_fullname);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_repassword = (EditText) findViewById(R.id.edt_repassword);

        btn_login.setOnClickListener(this);
        btn_birthday.setOnClickListener(this);
        btn_signup.setOnClickListener(this);

        spinner_school = (Spinner) findViewById(R.id.spinner_school);




        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(SignUpActivity.this, btn_birthday.getText().toString(), Toast.LENGTH_SHORT).show();
                month += 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                btn_birthday.setText(date);
            }
        };

        controllerLogicProcessSignUp.getSchoolList();
    }

    private void initList(){
        mSchoolList = new ArrayList<>();
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
        mSchoolList.add(new SchoolItem("123",1));
        mSchoolList.add(new SchoolItem("abc",2));
        mSchoolList.add(new SchoolItem("xyz",3));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                //Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                //startActivity(intent);
                //finish();
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.btn_signup:
                //validateForm();
                //Toast.makeText(SignUpActivity.this, "here here!!!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_birthday:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SignUpActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
        }
    }

    @Override
    public void signUpSuccessfull() {

    }

    @Override
    public void signUpFail() {

    }

    @Override
    public boolean validateForm() {
        boolean valid = true;
        if(TextUtils.isEmpty(edt_fullname.getText().toString())){
            edt_fullname.setError("Họ và tên không được trống!");
            valid = false;
        } else {
            edt_fullname.setError(null);
        }

        if(!isEmailValidate(edt_email.getText().toString())){
            edt_email.setError("Nhập đúng định dạng Email!");
            valid = false;
        }
        else {
            edt_email.setError(null);
        }

        if(TextUtils.isEmpty(edt_phone.getText().toString())){
            edt_phone.setError("Họ và tên không được trống!");
            valid = false;
        } else {
            edt_phone.setError(null);
        }

        if(TextUtils.isEmpty(edt_username.getText().toString())){
            edt_username.setError("Tên đăng nhập không được trống!");
            valid = false;
        } else {
            edt_username.setError(null);
        }

        if(TextUtils.isEmpty(edt_password.getText().toString())){
            edt_password.setError("Mật khẩu không được trống!");
            valid = false;
        } else {
            if (!edt_password.getText().toString().equals(edt_repassword.getText().toString())) {
                edt_password.setError("Mật khẩu phải trùng nhau!");
                edt_repassword.setError("Mật khẩu phải trùng nhau!");
                valid = false;
            } else {
                edt_password.setError(null);
                edt_repassword.setError(null);
            }
        }

        return valid;
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
                Toast.makeText(SignUpActivity.this, jsonObject.getString("name"), Toast.LENGTH_SHORT).show();
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
                //SchoolItem clickedItem = (SchoolItem) parent.getItemAtPosition(position);
                //String clickedSchoolName = clickedItem.getmSchoolName();
                //Toast.makeText(SignUpActivity.this, clickedSchoolName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean isEmailValidate(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
