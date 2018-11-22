package hcmue.congvu.drlstudent.Model.ActivityModel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Model.CurrentClassDetailModel.ClassDetailAdapter;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 11/10/2018.
 */
public class ActivityClassAdapter extends ArrayAdapter<ActivityClassItem> {
    public  int idUser, idClass, idClassDetail;
    public ConfirmActivityListener confirm;

    public ActivityClassAdapter(@NonNull Context context, @NonNull ArrayList<ActivityClassItem> activityClassItemArrayList) {
        super(context, 0, activityClassItemArrayList);
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

    private View initView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.class_activity_row, parent, false
            );
        }

        TextView tvContent = convertView.findViewById(R.id.tv_content);
        TextView tvDateTime = convertView.findViewById(R.id.tv_date_time);
        TextView tvEdit = convertView.findViewById(R.id.tv_edit);
        TextView tvCheck = convertView.findViewById(R.id.tv_confirm);
        TextView tvDelete = convertView.findViewById(R.id.tv_del);
        LinearLayout linearTask = convertView.findViewById(R.id.linearTask);
        //TextView tvConfirm = convertView.findViewById(R.id.tv_confirm);


        final ActivityClassItem activityClassItem = getItem(position);

        if(activityClassItem != null) {
            tvCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Bạn đã hoàn thành hoạt động này???");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(context, "Xóa nè!!!", Toast.LENGTH_SHORT).show();
                            confirm.checkActivity(idUser, idClass, idClassDetail, activityClassItem.getmId());
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
            });

            tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Bạn có muốn chỉnh sửa hoạt động này???");


                    builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(context, "Xóa nè!!!", Toast.LENGTH_SHORT).show();
                            confirm.editActivity(idUser, idClass, idClassDetail, activityClassItem.getmId(),
                                                activityClassItem.getmIdGroup(), activityClassItem.getmIdLevel(),
                                                activityClassItem.getmContent(), activityClassItem.getmDateTimeStart(),
                                                activityClassItem.getmDateTimeEnd(), activityClassItem.mScores);
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
            });

            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Bạn có muốn xóa hoạt động này???");

                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(context, "Xóa nè!!!", Toast.LENGTH_SHORT).show();
                            confirm.deleteActivity(position, idUser, idClass, idClassDetail, activityClassItem.getmId());
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
            });
            if(activityClassItem.getmTypeUser() == 2){
                linearTask.setVisibility(convertView.VISIBLE);
            }
            tvContent.setText(activityClassItem.getmContent());
            tvDateTime.setText("> Start: " + activityClassItem.getmDateTimeStart() + "\n" +"> End:  " + activityClassItem.getmDateTimeEnd());
        }

        return convertView;
    }

    public interface ConfirmActivityListener{
        void checkActivity(int idUser, int idClass, int idClassDetail, int idActivity);
        void editActivity(int idUser, int idClass, int idClassDetail, int idActivity, int idGroup, int idLevel, String content, String timeStart, String timeEnd, int scores);
        void deleteActivity(int index, int idUser, int idClass, int idClassDetail, int idActivity);
    }
}
