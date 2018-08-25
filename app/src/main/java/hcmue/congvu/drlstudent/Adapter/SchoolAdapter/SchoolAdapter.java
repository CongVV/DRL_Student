package hcmue.congvu.drlstudent.Adapter.SchoolAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;

/**
 * Created by CongVu on 25/08/2018.
 */
public class SchoolAdapter extends ArrayAdapter {
    public SchoolAdapter(Context context, ArrayAdapter<SchoolItem> schoolList){
        super(context, 0, (List) schoolList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}
