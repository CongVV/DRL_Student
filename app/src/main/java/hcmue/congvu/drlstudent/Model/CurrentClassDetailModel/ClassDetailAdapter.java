package hcmue.congvu.drlstudent.Model.CurrentClassDetailModel;

import android.animation.LayoutTransition;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 24/09/2018.
 */
public class ClassDetailAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ClassDetailItem> classDetailItemList;

    public ClassDetailAdapter(Context context, int layout, List<ClassDetailItem> classDetailItemslist){
        this.context = context;
        this.layout = layout;
        this.classDetailItemList = classDetailItemslist;
    }

    @Override
    public int getCount() {
        return classDetailItemList.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        ImageView imgClassDetail = (ImageView) convertView.findViewById(R.id.imgViewClass);
        TextView tvClassDetailName = (TextView) convertView.findViewById(R.id.tvClassDetailName);

        imgClassDetail.setImageResource(R.drawable.correct);
        tvClassDetailName.setText(String.valueOf(classDetailItemList.get(position).getmYearStart()) + " - " +
                String.valueOf(classDetailItemList.get(position).getmYearEnd()) + " (Học kỳ: " +
                String.valueOf(classDetailItemList.get(position).getmTerm()) + " )");
        return convertView;
    }
}
