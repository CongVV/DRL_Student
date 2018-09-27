package hcmue.congvu.drlstudent.Model.CurrentClassModel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 11/09/2018.
 */
public class ClassAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<ClassItem> classItemList;

    public ClassAdapter(Context context, int layout, List<ClassItem> classItemList){
        this.context = context;
        this.layout = layout;
        this.classItemList = classItemList;
    }

    @Override
    public int getCount() {
        return classItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        ImageView imgClass              = (ImageView) convertView.findViewById(R.id.imgViewClass);
        TextView tvClassName            = (TextView) convertView.findViewById(R.id.tvClassName);
        TextView tvClassNumberStudent   = (TextView) convertView.findViewById(R.id.tvClassNumberStudent);

        imgClass.setImageResource(R.drawable.book);
        tvClassName.setText(classItemList.get(position).getmClassName());
        tvClassNumberStudent.setText(String.valueOf(classItemList.get(position).getmNumberStudent()) + " Sinh viên");
        return convertView;
    }
}
