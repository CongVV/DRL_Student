package hcmue.congvu.drlstudent.View.ManagementClassView;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Model.StudentModel.StudentClassItem;
import hcmue.congvu.drlstudent.Model.StudentModel.StudentClassTypeAdapter;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 19/11/2018.
 */
public class FragmentManagementTypeStudent extends Fragment {
    ListView lvTypeStudent;
    Button btnUpdateTypeStudent;
    int idUser, idClass;
    ArrayList<StudentClassItem> arrayListTypeStudent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_type_student_management, container, false);
        Bundle bundle = getArguments();
        if(bundle!=null){
            idUser = bundle.getInt("idUser");
            idClass = bundle.getInt("idClass");
        }
        lvTypeStudent = view.findViewById(R.id.lvTypeStudentManagement);
        btnUpdateTypeStudent = view.findViewById(R.id.btnUpdateTypeStudent);
        btnUpdateTypeStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Gson gson = new GsonBuilder().create();
                final JsonArray jsonArrayTypeStudentClass = gson.toJsonTree(arrayListTypeStudent).getAsJsonArray();
                Log.i("arrRes", jsonArrayTypeStudentClass.toString());*/

                ManagementClassActivity act = (ManagementClassActivity) getActivity();
                act.applyUpdateTypeStudentClass(idClass, arrayListTypeStudent);
            }
        });
        return view;
    }

    public void setDataManagementTypeStudent(ArrayList<StudentClassItem> arrayList){
        arrayListTypeStudent = arrayList;
        StudentClassTypeAdapter adapter = new StudentClassTypeAdapter(getActivity(), arrayList);
        adapter.idUser = idUser;
        lvTypeStudent.setAdapter(adapter);
    }
}
