package hcmue.congvu.drlstudent.Model.ActivityModel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 11/10/2018.
 */
public class ActivityClassAdapter extends ArrayAdapter<ActivityClassItem> {
    public ActivityClassAdapter(@NonNull Context context, @NonNull ArrayList<ActivityClassItem> activityClassItemArrayList) {
        super(context, 0, activityClassItemArrayList);
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
                    R.layout.class_activity_row, parent, false
            );
        }

        TextView tvContent = convertView.findViewById(R.id.tv_content);
        TextView tvDateTime = convertView.findViewById(R.id.tv_date_time);
        Button btnConfirm = convertView.findViewById(R.id.btn_confirm);
        ActivityClassItem activityClassItem = getItem(position);

        if(activityClassItem != null) {
            tvContent.setText(activityClassItem.getmContent());
            tvDateTime.setText(activityClassItem.getmDateTimeStart() + "-->>" + activityClassItem.getmDateTimeEnd());
        }

        return convertView;
    }
}
