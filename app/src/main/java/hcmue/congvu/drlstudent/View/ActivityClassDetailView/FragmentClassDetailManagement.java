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
public class FragmentClassDetailManagement extends Fragment implements ActivityManagementAdapter.ConfirmActivityManagement{
    ListView lvAcitityRequest;
    TextView tvTotalActivityRequest;
    ArrayList<ActivityManagementItem> arrayRequest;
    int totalRequest = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_management, container, false);
        lvAcitityRequest = view.findViewById(R.id.lvManagementActivity);
        tvTotalActivityRequest = view.findViewById(R.id.tvTotalActivityRequest);
        return view;
    }

    public void setDataActivityManagement(ArrayList<ActivityManagementItem> arrayList){
        arrayRequest = arrayList;
        ActivityManagementAdapter adapter = new ActivityManagementAdapter(getActivity(), arrayList);
        adapter.confirm = this;
        lvAcitityRequest.setAdapter(adapter);
    }

    @Override
    public void acceptActivity(int index, int idUser, int idActivity) {
        ActivityClassDetailActivity act = (ActivityClassDetailActivity) getActivity();
        act.applyAcceptActivity(index, idUser, idActivity);
    }

    @Override
    public void cancelActivity(int index, int idUser, int idActivity) {
        ActivityClassDetailActivity act = (ActivityClassDetailActivity) getActivity();
        act.applyCancelActivity(index, idUser, idActivity);
    }
}
