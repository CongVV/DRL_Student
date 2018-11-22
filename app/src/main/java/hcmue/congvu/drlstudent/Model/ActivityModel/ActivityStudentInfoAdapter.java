package hcmue.congvu.drlstudent.Model.ActivityModel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 08/11/2018.
 */
public class ActivityStudentInfoAdapter extends ArrayAdapter<ActivityStudentInfoItem> {

    public ActivityStudentInfoAdapter(@NonNull Context context, @NonNull ArrayList<ActivityStudentInfoItem> studentInfoItemArrayList){
        super(context, 0, studentInfoItemArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_activity_info_row, parent, false);
        }
        TextView tvContentActivity = convertView.findViewById(R.id.tvContentActivity);
        ImageView imgStatusActivity = convertView.findViewById(R.id.imgStatusActivity);
        TextView tvScoresActivity = convertView.findViewById(R.id.tvScoresActivity);

        ActivityStudentInfoItem activityStudentInfoItem = getItem(position);
        if(activityStudentInfoItem!=null){
            tvContentActivity.setText(activityStudentInfoItem.getmContentActivity());
            if(activityStudentInfoItem.getmStatus()==1){
                imgStatusActivity.setImageResource(R.drawable.correct);
            }
            else if(activityStudentInfoItem.getmStatus()==0){
                imgStatusActivity.setImageResource(R.drawable.icon_delete);
            }
            else if(activityStudentInfoItem.getmStatus()==2){
                imgStatusActivity.setImageResource(R.drawable.icon_pending);
            }
            else{
                imgStatusActivity.setImageResource(R.drawable.icon_refuse);
            }
            tvScoresActivity.setText(String.valueOf(activityStudentInfoItem.getmScores()));
        }
        return convertView;
    }
}
