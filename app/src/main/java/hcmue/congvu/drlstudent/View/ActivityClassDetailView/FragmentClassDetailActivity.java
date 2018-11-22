package hcmue.congvu.drlstudent.View.ActivityClassDetailView;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityClassAdapter;
import hcmue.congvu.drlstudent.Model.ActivityModel.ActivityClassItem;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 09/10/2018.
 */
public class FragmentClassDetailActivity extends Fragment implements ActivityClassAdapter.ConfirmActivityListener{
    ListView lvActivityClass;
    TextView tvEmpty;
    int idUser, idClass, idClassDetail;
    ArrayList<ActivityClassItem> arrayActivityClass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle != null){
            idUser = bundle.getInt("idUser");
            idClass = bundle.getInt("idClass");
            idClassDetail = bundle.getInt("idClassDetail");
        }
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        lvActivityClass = view.findViewById(R.id.listViewActivityClass);
        tvEmpty = view.findViewById(R.id.tvEmpty);
        //ActivityClassDetailActivity act = (ActivityClassDetailActivity) getActivity();
        //act.controllerLogicProcessActivityClassDetail.getActivityClass(idUser,idClass,idClassDetail);
        lvActivityClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }

    public void setDataActivityClass(ArrayList<ActivityClassItem> arrActivityClass){
        arrayActivityClass = arrActivityClass;
        ActivityClassAdapter adapter = new ActivityClassAdapter(getActivity(), arrActivityClass);
        adapter.idUser = idUser;
        adapter.idClass = idClass;
        adapter.idClassDetail = idClassDetail;
        adapter.confirm = this;
        lvActivityClass.setAdapter(adapter);
    }

    @Override
    public void checkActivity(int idUser, int idClass, int idClassDetail, int idActivity) {
        //Toast.makeText(getActivity(), "Confirm nè!", Toast.LENGTH_SHORT).show();
        ActivityClassDetailActivity act = (ActivityClassDetailActivity) getActivity();
        act.applyConfirmAcceptActivity(idUser, idClass, idClassDetail, idActivity);
    }

    @Override
    public void editActivity(int idUser, int idClass, int idClassDetail, int idActivity, int idGroup, int idLevel, String content, String timeStart, String timeEnd, int scores) {
        //Toast.makeText(getActivity(), "Edit Activity nha!", Toast.LENGTH_SHORT).show();
        ActivityClassDetailActivity act = (ActivityClassDetailActivity) getActivity();
        act.applyUpdateClasActivity(idUser, idClass, idClassDetail, idActivity, idGroup, idLevel, content, timeStart, timeEnd, scores);
    }

    @Override
    public void deleteActivity(int index, int idUser, int idClass, int idClassDetail, int idActivity) {
        ActivityClassDetailActivity act = (ActivityClassDetailActivity) getActivity();
        act.applyDeleteActivity(index, idUser, idClass, idClassDetail, idActivity);
    }
}
