package hcmue.congvu.drlstudent.View.ActivityClassDetailView;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityManagementAdapter;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityManagementItem;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 11/10/2018.
 */
public class FragmentClassDetailManagement extends Fragment {
    ListView lvAcitityRequest;
    TextView tvTotalActivityRequest;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_management, container, false);
        lvAcitityRequest = view.findViewById(R.id.lvManagementActivity);
        tvTotalActivityRequest = view.findViewById(R.id.tvTotalActivityRequest);
        return view;
    }

    public void setDataActivityManagement(ArrayList<ActivityManagementItem> arrayList){
        ActivityManagementAdapter adapter = new ActivityManagementAdapter(getActivity(), arrayList);
        lvAcitityRequest.setAdapter(adapter);
    }
}
