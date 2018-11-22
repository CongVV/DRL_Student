package hcmue.congvu.drlstudent.Model.StudentModel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 20/11/2018.
 */
public class StudentClassTypeAdapter extends ArrayAdapter<StudentClassItem> {
    public int idUser;

    public StudentClassTypeAdapter(@NonNull Context context, @NonNull ArrayList<StudentClassItem> arrayList){
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    public View initView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.type_student_management_row, parent, false);
        }

        TextView tvUsername         = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvFullname         = (TextView) convertView.findViewById(R.id.tvFullname);
        final Spinner spinnerTypeStudent  = (Spinner) convertView.findViewById(R.id.spinnerTypeStudent);
        String userName = "Username: " + getItem(position).getUserNameStudent();
        String fullName = "Họ tên: " + getItem(position).getFullNameStudent();
        tvUsername.setText(userName);
        tvFullname.setText(fullName);
        if(idUser == getItem(position).getIdUser()){
            spinnerTypeStudent.setVisibility(View.GONE);
        }

        if(getItem(position).getTypeStudentClass()==1){
            spinnerTypeStudent.setSelection(0);
        }
        else{
            spinnerTypeStudent.setSelection(1);
        }

        spinnerTypeStudent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posi, long id) {
                //StudentClassItem item = (StudentClassItem) parent.getItemAtPosition(position);
                if(spinnerTypeStudent.getItemAtPosition(posi).toString().equals("Sinh viên")){
                    getItem(position).setTypeStudentClass(1);
                }
                else{
                    getItem(position).setTypeStudentClass(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return convertView;
    }

    public interface ConfirmChangeTypeStudentClass{
        void applyChangeTypeStudentClass(ArrayList<StudentClassItem> arrayList);
    }
}
