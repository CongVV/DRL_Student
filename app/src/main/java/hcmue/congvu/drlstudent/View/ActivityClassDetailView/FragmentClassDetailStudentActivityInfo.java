package hcmue.congvu.drlstudent.View.ActivityClassDetailView;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityStudentInfoAdapter;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityStudentInfoItem;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 11/10/2018.
 */
public class FragmentClassDetailStudentActivityInfo extends Fragment {
    ListView lvActivityStudentInfo;
    TextView tvTotalScores;
    public int totalScores=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_activity_info, container, false);
        lvActivityStudentInfo = view.findViewById(R.id.lvStudentActivityInfo);
        tvTotalScores = view.findViewById(R.id.tvTotalScores);

        //tvTotalScores.setText("123 Điểm");
        //tvTotalScores.setText(String.valueOf(totalScores) + " Điểm");
        return view;
    }

    public void setDataActivityStudentInfo(ArrayList<ActivityStudentInfoItem> arrActivityStudentInfo){
        ActivityStudentInfoAdapter adapter = new ActivityStudentInfoAdapter(getActivity(), arrActivityStudentInfo);
        lvActivityStudentInfo.setAdapter(adapter);
    }
}
