package hcmue.congvu.drlstudent.Model.ActivityModel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmue.congvu.drlstudent.Model.SchoolModel.SchoolItem;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 09/10/2018.
 */
public class ActivityGroupAdapter extends ArrayAdapter<ActivityGroupItem> {
    public ActivityGroupAdapter(@NonNull Context context, @NonNull ArrayList<ActivityGroupItem> activityGroupItemArrayList) {
        super(context, 0, activityGroupItemArrayList);
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
        ActivityGroupItem activityGroupItem = getItem(position);

        if(activityGroupItem != null) {
            textViewFlag.setText(activityGroupItem.getmName());
        }

        return convertView;
    }
}
