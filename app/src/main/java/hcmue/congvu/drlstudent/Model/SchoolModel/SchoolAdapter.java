package hcmue.congvu.drlstudent.Model.SchoolModel;

import android.content.Context;
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

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 25/08/2018.
 */
public class SchoolAdapter extends ArrayAdapter<SchoolItem> {
    public SchoolAdapter(Context context, ArrayList<SchoolItem> schoolList){
        super(context, 0, (List) schoolList);
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

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.school_spinner_row, parent, false
            );
        }

        TextView textViewFlag = convertView.findViewById(R.id.tv_spinner_school);
        SchoolItem currentSchoolItem = getItem(position);

        if(currentSchoolItem != null) {
            textViewFlag.setText(currentSchoolItem.getmSchoolName());
        }

        return convertView;
    }
}
