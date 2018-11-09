package hcmue.congvu.drlstudent.Model.CurrentClassModel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 11/09/2018.
 */
public class ClassAdapter extends BaseAdapter {
    public Context context;
    public int layout;
    public List<ClassItem> classItemList;
    public ConfirmDeleteClassListener confirm;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        ImageView imgClass              = (ImageView) convertView.findViewById(R.id.imgViewClass);
        TextView tvClassName            = (TextView) convertView.findViewById(R.id.tvClassName);
        TextView tvClassNumberStudent   = (TextView) convertView.findViewById(R.id.tvClassNumberStudent);
        TextView tvDeleteClass          = (TextView) convertView.findViewById(R.id.tvDeleteClass);

        imgClass.setImageResource(R.drawable.book);
        tvClassName.setText(classItemList.get(position).getmClassName());
        tvClassNumberStudent.setText(String.valueOf(classItemList.get(position).getmNumberStudent()) + " Sinh viên");
        tvDeleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(classItemList.get(position).ismIsAdmin()){
                    //Toast.makeText(context, "XÓA XÓA XÓA!", Toast.LENGTH_LONG).show();
                    /*ConfirmDeleteClass confirm = new ConfirmDeleteClass();
                    confirm.idClass     = classItemList.get(position).getmIdClass();
                    confirm.className   = classItemList.get(position).getmClassName();
                    confirm.show();*/

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Bạn có muốn xóa???");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(context, "Xóa nè!!!", Toast.LENGTH_SHORT).show();
                            confirm.deleteClass(classItemList.get(position).getmIdClass());
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

    /*public static class ConfirmDeleteClass extends AppCompatDialogFragment{
        public TextView tvDeleteClass;
        public confirmDeleteClassListener confirmDeleteClassListener;
        public int idClass;
        public String className;
        public ConfirmDeleteClass(){

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_delete, null);
            builder.setView(view)
                    .setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            confirmDeleteClassListener.deleteClass(idClass);
                        }
                    });
            tvDeleteClass = view.findViewById(R.id.tvDelete);
            tvDeleteClass.setText("Bạn có muốn xóa???");
            return builder.create();
        }

        public void show(){

        }
    }*/


    public interface ConfirmDeleteClassListener{
        void deleteClass(int idClass);
    }

}
