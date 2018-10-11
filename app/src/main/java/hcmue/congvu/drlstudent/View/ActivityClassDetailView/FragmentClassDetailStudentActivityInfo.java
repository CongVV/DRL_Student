package hcmue.congvu.drlstudent.View.ActivityClassDetailView;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 11/10/2018.
 */
public class FragmentClassDetailStudentActivityInfo extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_activity_info, container, false);

        return view;
    }
}
