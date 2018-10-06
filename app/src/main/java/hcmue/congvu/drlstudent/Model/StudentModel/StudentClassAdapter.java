package hcmue.congvu.drlstudent.Model.StudentModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by CongVu on 02/10/2018.
 */
public class StudentClassAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<StudentClassItem> studentClassItemList;

    public StudentClassAdapter(Context context, int layout, List<StudentClassItem> studentClassItemList) {
        this.context = context;
        this.layout = layout;
        this.studentClassItemList = studentClassItemList;
    }

    @Override
    public int getCount() {
        return studentClassItemList.size();
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

        return convertView;
    }
}
