package hcmue.congvu.drlstudent.Model.YearModel;

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
import hcmue.congvu.drlstudent.View.CurrentClassDetailView.CreateClassTermDialog;

/**
 * Created by CongVu on 03/10/2018.
 */
public class YearAdapter extends ArrayAdapter<YearItem>{

    public YearAdapter(Context context, ArrayList<YearItem> yearItemArrayList){
        super(context, 0, (List) yearItemArrayList);
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
        YearItem currentYearItem = getItem(position);

        if(currentYearItem != null) {
            textViewFlag.setText(currentYearItem.getmYearStart() + " - " + currentYearItem.getmYearEnd());
        }

        return convertView;
    }

}
