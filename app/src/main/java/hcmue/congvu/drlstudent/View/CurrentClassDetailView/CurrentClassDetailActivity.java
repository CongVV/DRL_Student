package hcmue.congvu.drlstudent.View.CurrentClassDetailView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hcmue.congvu.drlstudent.Controller.CurrentClassDetailController.ControllerLogicProcessCurrentClassDetail;
import hcmue.congvu.drlstudent.Model.CurrentClassDetailModel.ClassDetailAdapter;
import hcmue.congvu.drlstudent.Model.CurrentClassDetailModel.ClassDetailItem;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 24/09/2018.
 */
public class CurrentClassDetailActivity extends AppCompatActivity implements ViewCurrentClassDetail {
    ListView listViewClassDetail;
    ArrayList<ClassDetailItem> arrayClassDetail;
    int userId;
    int idCurrentClassDetail;
    int numberStudent;
    String avatar;
    ControllerLogicProcessCurrentClassDetail controllerLogicProcessCurrentClassDetail = new ControllerLogicProcessCurrentClassDetail(this, this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_class_detail);

        Bundle bundle = this.getIntent().getExtras();
        userId = bundle.getInt("userId");
        avatar = bundle.getString("avatar");
        idCurrentClassDetail = bundle.getInt("idCurrentClassDetail");
        numberStudent = 10;

        listViewClassDetail = (ListView) findViewById(R.id.listViewClassCurrentDetail);
        controllerLogicProcessCurrentClassDetail.getCurrentClassDetailList(userId, idCurrentClassDetail);


    }

    @Override
    public void setListViewClassDetail(JSONArray jsonArray) {
        arrayClassDetail = new ArrayList<ClassDetailItem>();
        for (int i=0; i<jsonArray.length(); i++){
            try{
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ClassDetailItem classItem = new ClassDetailItem();
                classItem.setmIdClass(jsonObject.getInt("idClass"));
                classItem.setmTerm(jsonObject.getInt("term"));
                classItem.setmYearStart(jsonObject.getInt("yearStart"));
                classItem.setmYearEnd(jsonObject.getInt("yearEnd"));
                classItem.setmClassDetailImg(jsonObject.getString("image"));
                classItem.setmNumberStudent(numberStudent);
                arrayClassDetail.add(classItem);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        ClassDetailAdapter adapbter = new ClassDetailAdapter(
                CurrentClassDetailActivity.this,
                R.layout.current_class_row,
                arrayClassDetail
        );

        listViewClassDetail.setAdapter(adapbter);
    }
}
