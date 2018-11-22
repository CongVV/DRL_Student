package hcmue.congvu.drlstudent.Model.StudentModel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityClassItem;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 02/10/2018.
 */
public class StudentClassAdapter extends ArrayAdapter<StudentClassItem> {
    public int idUser;
    public ConfirmDeleteClassItemListener confirm;

    public StudentClassAdapter(@NonNull Context context, @NonNull ArrayList<StudentClassItem> arrayList) {
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
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.class_list_management_row, parent, false
            );
        }

        TextView tvUsername         = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvFullname         = (TextView) convertView.findViewById(R.id.tvFullname);
        TextView tvDeleteStudent    = (TextView) convertView.findViewById(R.id.tvDeleteStudent);
        String userName = "Username: " + getItem(position).getUserNameStudent();
        String fullName = "Họ tên: " + getItem(position).getFullNameStudent();
        tvUsername.setText(userName);
        tvFullname.setText(fullName);
        if(idUser == getItem(position).getIdUser()){
            tvDeleteStudent.setVisibility(View.GONE);
        }

        tvDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn có muốn xóa???");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirm.deleteStudentClassItem(position, getItem(position).getIdUser(), getItem(position).getIdClass());
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();;
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return convertView;
    }

    public interface ConfirmDeleteClassItemListener{
        void deleteStudentClassItem(int index, int idUser, int idClass);
    }
}