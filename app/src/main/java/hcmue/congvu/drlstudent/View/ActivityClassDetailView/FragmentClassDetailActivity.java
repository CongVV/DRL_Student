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
public class FragmentClassDetailActivity extends Fragment {
    ListView lvActivityClass;
    TextView tvEmpty;
    int idUser, idClass, idClassDetail;

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
        ActivityClassAdapter adapter = new ActivityClassAdapter(getActivity(), arrActivityClass);
        lvActivityClass.setAdapter(adapter);
    }
}
