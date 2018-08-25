package hcmue.congvu.drlstudent.View.SignUpView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import hcmue.congvu.drlstudent.Adapter.SchoolAdapter.SchoolAdapter;
import hcmue.congvu.drlstudent.Adapter.SchoolAdapter.SchoolItem;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.LogInView.LogInActivity;

/**
 * Created by CongVu on 24/08/2018.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private String array_spinner[];
    private ArrayList<SchoolItem> mSchoolList;
    private SchoolAdapter mSchoolAdapter;
    private Button btn_login, btn_birthday;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_birthday = (Button) findViewById(R.id.btn_birthday);
        btn_login.setOnClickListener(this);
        btn_birthday.setOnClickListener(this);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(SignUpActivity.this, btn_birthday.getText().toString(), Toast.LENGTH_SHORT).show();
                month += 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                btn_birthday.setText(date);
            }
        };

        array_spinner = new String[5];
        array_spinner[0]="option 1";
        array_spinner[1]="option 2";
        array_spinner[2]="option 3";
        array_spinner[3]="option 4";
        array_spinner[4]="option 5";

        initList();

        Spinner spinnerSchool = (Spinner) findViewById(R.id.spin_school);
        /*ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);*/

        mSchoolAdapter = new SchoolAdapter(this, mSchoolList);
        spinnerSchool.setAdapter(mSchoolAdapter);
        spinnerSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SchoolItem clickedItem = (SchoolItem) parent.getItemAtPosition(position);
                String clickedSchoolName = clickedItem.getmSchoolName();
                Toast.makeText(SignUpActivity.this, clickedSchoolName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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

    public void checkButton(){

    }
}
