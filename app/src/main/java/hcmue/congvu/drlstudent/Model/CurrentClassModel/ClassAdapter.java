package hcmue.congvu.drlstudent.Model.CurrentClassModel;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 11/09/2018.
 */
public class ClassAdapter extends ArrayAdapter<ClassItem> {

    public ClassAdapter(Context context, ArrayList<ClassItem> schoolList){
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
                    R.layout.current_class_row, parent, false
            );
        }

        TextView textViewFlag = convertView.findViewById(R.id.tvCurrentClass);
        ImageView imageViewFlag = convertView.findViewById(R.id.imgViewClass);
        ClassItem currentClassItem = getItem(position);

        if(currentClassItem != null) {
            textViewFlag.setText(currentClassItem.getmClassName());
            //imageViewFlag.setImageDrawable(R.drawable.currentClassItem.getmClassImg());
        }

        return convertView;
    }
}
