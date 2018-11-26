package hcmue.congvu.drlstudent.Model.DataChartModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 26/11/2018.
 */
public class DataChartClassAdapter extends BaseAdapter {
    public Context context;
    public int layout;
    public List<DataChartClassItem> dataChartClassItemList;

    public DataChartClassAdapter(Context context, int layout, List<DataChartClassItem> dataChartClassItemList){
        this.context = context;
        this.layout = layout;
        this.dataChartClassItemList = dataChartClassItemList;
    }

    @Override
    public int getCount() {
        return dataChartClassItemList.size();
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
    public View getView(final int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        ImageView imgClass              = (ImageView) convertView.findViewById(R.id.imgViewClass);
        TextView tvClassName            = (TextView) convertView.findViewById(R.id.tvClassName);

        imgClass.setImageResource(R.drawable.book);
        tvClassName.setText(dataChartClassItemList.get(position).getClassName());

        return convertView;
    }
}
