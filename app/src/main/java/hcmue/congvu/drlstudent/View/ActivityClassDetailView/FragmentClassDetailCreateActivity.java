package hcmue.congvu.drlstudent.View.ActivityClassDetailView;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityGroupAdapter;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityGroupItem;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityLevelAdapter;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityLevelItem;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.SignUpView.SignUpActivity;

/**
 * Created by CongVu on 09/10/2018.
 */
public class FragmentClassDetailCreateActivity extends Fragment {
    //ArrayList<ActivityGroupItem> mActivityGroupList = new ArrayList<>();
    //ArrayList<ActivityLevelItem> mActivityLevelList = new ArrayList<>();
    ActivityGroupAdapter activityGroupAdapter;
    ActivityLevelAdapter activityLevelAdapter;
    Spinner spinnerActivityGroup;
    Spinner spinnerActivityLevel;
    DatePickerDialog.OnDateSetListener datePickerDialogStart, datePickerDialogEnd;
    TimePickerDialog.OnTimeSetListener timePickerDialogStart, timePickerDialogEnd;
    Button btnDatePickerStart, btnDatePickerEnd, btnTimePickerStart, btnTimePickerEnd, btnCreateActivity;
    String dStart, dEnd, tStart, tEnd;
    int idActivityGroup, idActivityLevel, idUser, idClass, idClassDetail;
    EditText edtContent, edtScores;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle != null){
            idUser = bundle.getInt("idUser");
            idClass = bundle.getInt("idClass");
            idClassDetail = bundle.getInt("idClassDetail");
        }
        View view = inflater.inflate(R.layout.fragment_create_activity, container, false);
        spinnerActivityGroup = view.findViewById(R.id.spinner_activity_group);
        spinnerActivityLevel = view.findViewById(R.id.spinner_activity_level);
        btnDatePickerStart = view.findViewById(R.id.datepicker_start);
        btnDatePickerEnd = view.findViewById(R.id.datepicker_end);
        btnTimePickerStart = view.findViewById(R.id.timepicker_start);
        btnTimePickerEnd = view.findViewById(R.id.timepicker_end);
        edtContent = view.findViewById(R.id.edt_activity_content);
        edtScores = view.findViewById(R.id.edt_scores);
        btnCreateActivity = view.findViewById(R.id.btn_create_activity);

        datePickerDialogStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                String dateStart = dayOfMonth + "/" + month + "/" + year;
                dStart = year + "-" + month + "-" + dayOfMonth;
                btnDatePickerStart.setText(dateStart);
            }
        };

        datePickerDialogEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                String dateEnd = dayOfMonth + "/" + month + "/" + year;
                dEnd = year + "-" + month + "-" + dayOfMonth;
                btnDatePickerEnd.setText(dateEnd);
            }
        };

        timePickerDialogStart = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String timeStart = hourOfDay + ":" + minute;
                tStart = timeStart;
                btnTimePickerStart.setText(timeStart);
            }
        };

        timePickerDialogEnd = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String timeEnd = hourOfDay + ":" + minute;
                tEnd = timeEnd;
                btnTimePickerEnd.setText(timeEnd);
            }
        };

        btnDatePickerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        datePickerDialogStart,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        btnDatePickerEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        datePickerDialogEnd,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        btnTimePickerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(getActivity(),
                        timePickerDialogStart, hour, minute, true);


                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });

        btnTimePickerEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(getActivity(),
                        timePickerDialogEnd, hour, minute, true);


                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        btnCreateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityClassDetailActivity act = (ActivityClassDetailActivity) getActivity();
                act.createActivityClass(idUser, idClass, idClassDetail, idActivityGroup, idActivityLevel,
                                                                dStart+" "+tStart, dEnd+" "+tEnd,
                                                                edtContent.getText().toString(),
                                                                edtScores.getText().toString());
            }
        });

        /*Bundle bundle = getArguments();
        if(bundle != null){
            Log.i("listAc", bundle.getString("listActivityGroup"));
            //Toast.makeText(context, bundle.getString("listActivityGroup"), Toast.LENGTH_SHORT).show();
            try {
                jActivityGroupList = new JSONArray(bundle.getString("listActivityGroup"));
                jActivityLevelList = new JSONArray(bundle.getString("listActivityLevel"));
                for(int i=0; i<jActivityGroupList.length(); i++){
                    try {
                        JSONObject jsonObject = jActivityGroupList.getJSONObject(i);
                        ActivityGroupItem activityGroupItem = new ActivityGroupItem();
                        activityGroupItem.setmId(jsonObject.getInt("id"));
                        activityGroupItem.setmName(jsonObject.getString("name"));
                        mActivityGroupList.add(activityGroupItem);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                for(int i=0; i<jActivityLevelList.length(); i++){
                    try {
                        JSONObject jsonObject = jActivityLevelList.getJSONObject(i);
                        ActivityLevelItem activityLevelItem = new ActivityLevelItem();
                        activityLevelItem.setmId(jsonObject.getInt("id"));
                        activityLevelItem.setmName(jsonObject.getString("name"));
                        mActivityLevelList.add(activityLevelItem);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                activityGroupAdapter = new ActivityGroupAdapter(context, mActivityGroupList);
                spinnerActivityGroup.setAdapter(activityGroupAdapter);
                activityLevelAdapter = new ActivityLevelAdapter(context, mActivityLevelList);
                spinnerActivityLevel.setAdapter(activityLevelAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/
        return view;
    }

    public void setGroupList(final ArrayList<ActivityGroupItem> mActivityGroupList){
        activityGroupAdapter = new ActivityGroupAdapter(getActivity(), mActivityGroupList);
        spinnerActivityGroup.setAdapter(activityGroupAdapter);
        spinnerActivityGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ActivityGroupItem item = (ActivityGroupItem) mActivityGroupList.get(position);
                idActivityGroup = item.getmId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setLevelList(final ArrayList<ActivityLevelItem> mActivityLevelList){
        activityLevelAdapter = new ActivityLevelAdapter(getActivity(), mActivityLevelList);
        spinnerActivityLevel.setAdapter(activityLevelAdapter);
        spinnerActivityLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ActivityLevelItem item = (ActivityLevelItem) mActivityLevelList.get(position);
                idActivityLevel = item.getmId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
