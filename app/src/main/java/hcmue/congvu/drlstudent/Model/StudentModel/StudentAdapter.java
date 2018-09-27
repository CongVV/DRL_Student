package hcmue.congvu.drlstudent.Model.StudentModel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.CreateClassView.CreateClassSecondActivity;

/**
 * Created by CongVu on 25/09/2018.
 */
public class StudentAdapter extends BaseAdapter {

    CreateClassSecondActivity context;
    int layout;
    List<StudentItem> studentItemList;

    public StudentAdapter(CreateClassSecondActivity context, int layout, List<StudentItem> studentItemList) {
        this.context = context;
        this.layout = layout;
        this.studentItemList = studentItemList;
    }

    @Override
    public int getCount() {
        return studentItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView tvUsernameStudent, tvStyleStudent;
        Button btnDeleteStudent;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.tvUsernameStudent = (TextView) convertView.findViewById(R.id.tvUsernameStudent);
            holder.tvStyleStudent = (TextView) convertView.findViewById(R.id.tvStyleStudent);
            holder.btnDeleteStudent = (Button) convertView.findViewById(R.id.btnDeleteStudent);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        final StudentItem studentItem = studentItemList.get(position);
        holder.tvUsernameStudent.setText(studentItem.getUsername());
        if(studentItem.getTypeStudent()==1){
            holder.tvStyleStudent.setText("SV");
        }
        else{
            holder.tvStyleStudent.setText("CB");
        }

        holder.btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete(studentItem.getUsername(), position);
            }
        });

        return convertView;
    }

    private void confirmDelete(String username, final int position){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);
        dialogDelete.setMessage("Bạn có muốn xóa sinh viên: "+username+" khỏi danh sách?");
        dialogDelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.DeleteStudent(position);
            }
        });

        dialogDelete.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogDelete.show();
    }
}
