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

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 10/11/2018.
 */
public class ActivityManagementAdapter extends ArrayAdapter<ActivityManagementItem> {
    public ActivityManagementAdapter(@NonNull Context context, @NonNull ArrayList<ActivityManagementItem> managementItemArrayList){
        super(context, 0, managementItemArrayList);
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

    private View initView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.management_activity_row, parent, false);
        }
        TextView tvFullname = convertView.findViewById(R.id.tvFullname);
        TextView tvActivity = convertView.findViewById(R.id.tvActivityname);

        ActivityManagementItem item = getItem(position);
        if(item != null){
            String s = item.getmFullname()
                    + System.getProperty ("line.separator")
                    + item.getmUsername();
            tvFullname.setText(s);
            String content="";
            if(item.getmActivityname().length() < 20){
                content = item.getmActivityname() + System.getProperty("line.separator");
            }
            else{
                content = item.getmActivityname();
            }
            tvActivity.setText(content);
        }
        return convertView;
    }
}
