package hcmue.congvu.drlstudent.View.ManagementClassView;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Model.StudentModel.StudentClassAdapter;
import hcmue.congvu.drlstudent.Model.StudentModel.StudentClassItem;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 19/11/2018.
 */
public class FragmentManagementClassList extends Fragment implements StudentClassAdapter.ConfirmDeleteClassItemListener{
    ListView lvManagementClassList;
    int idUser, idClass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_list_management, container, false);
        Bundle bundle = getArguments();
        if(bundle != null){
            idUser = bundle.getInt("idUser");
            idClass = bundle.getInt("idClass");
        }
        lvManagementClassList = view.findViewById(R.id.lvClassListManagement);

        return view;
    }

    public void setDataManagementClassList(ArrayList<StudentClassItem> arrayList){
        StudentClassAdapter adapter = new StudentClassAdapter(getActivity(), arrayList);
        adapter.idUser = idUser;
        adapter.confirm=this;
        lvManagementClassList.setAdapter(adapter);
    }

    @Override
    public void deleteStudentClassItem(int idUser, int idClass) {
        //Toast.makeText(getActivity(), "Xóa nha!!! haha", Toast.LENGTH_SHORT).show();
        ManagementClassActivity act = new ManagementClassActivity();
        act.applyDeleteStudentClassItem(idUser, idClass);
    }
}
