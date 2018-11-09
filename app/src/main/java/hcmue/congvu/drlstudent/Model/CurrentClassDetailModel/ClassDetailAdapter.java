package hcmue.congvu.drlstudent.Model.CurrentClassDetailModel;

import android.animation.LayoutTransition;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    public ConfirmDeleteClassDetailListener confirm;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        ImageView imgClassDetail = (ImageView) convertView.findViewById(R.id.imgViewClass);
        TextView tvClassDetailName = (TextView) convertView.findViewById(R.id.tvClassDetailName);
        TextView tvDeleteClassDetail = (TextView) convertView.findViewById(R.id.tvDeleteClassDetail);

        imgClassDetail.setImageResource(R.drawable.classdetail);
        tvClassDetailName.setText(String.valueOf(classDetailItemList.get(position).getmYearStart()) + " - " +
                String.valueOf(classDetailItemList.get(position).getmYearEnd()) + " (Học kỳ: " +
                String.valueOf(classDetailItemList.get(position).getmTerm()) + ")");
        tvDeleteClassDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(classDetailItemList.get(position).ismIsAdmin()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Bạn có muốn xóa???");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(context, "Xóa nè!!!", Toast.LENGTH_SHORT).show();
                            confirm.deleteClassDetail(classDetailItemList.get(position).getmIdClassDetail());
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Bạn không có quyền xóa!");
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
        return convertView;
    }

    public interface ConfirmDeleteClassDetailListener{
        void deleteClassDetail(int idClassDetail);
    }
}
