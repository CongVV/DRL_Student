package hcmue.congvu.drlstudent.View.DataChartView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Controller.DataChartController.ControllerLogicProcessDataChart;
import hcmue.congvu.drlstudent.Model.CurrentClassModel.ClassAdapter;
import hcmue.congvu.drlstudent.Model.CurrentClassModel.ClassItem;
import hcmue.congvu.drlstudent.Model.DataChartModel.DataChartClassAdapter;
import hcmue.congvu.drlstudent.Model.DataChartModel.DataChartClassItem;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 26/11/2018.
 */
public class DataChartListActivity extends AppCompatActivity implements ViewProcessDataChart{
    BarChart chart;
    private ListView listViewClass;
    private TextView tvClassList;
    private ArrayList<DataChartClassItem> arrayDataClass;
    private ControllerLogicProcessDataChart controllerLogicProcessDataChart = new ControllerLogicProcessDataChart(this, this);
    private int userId;
    String avatar;
    DataChartClassAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_chart_list);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null){
            userId = bundle.getInt("userId");
            avatar = bundle.getString("avatar");
        }
        else{
            Toast.makeText(this, "Lỗi mạng! Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        }

        tvClassList = (TextView) findViewById(R.id.tvEmpty);
        listViewClass = (ListView) findViewById(R.id.listViewClassCurrent);
        controllerLogicProcessDataChart.getDataChartList(userId);

        listViewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentDataChart = new Intent(DataChartListActivity.this, DataChartActivity.class);
                intentDataChart.putExtra("userId", userId);
                intentDataChart.putExtra("avatar", avatar);
                intentDataChart.putExtra("idClass", arrayDataClass.get(position).getIdClass());
                intentDataChart.putExtra("className", arrayDataClass.get(position).getClassName());
                intentDataChart.putExtra("term1", arrayDataClass.get(position).getClassTerm()[0]);
                intentDataChart.putExtra("term2", arrayDataClass.get(position).getClassTerm()[1]);
                intentDataChart.putExtra("term3", arrayDataClass.get(position).getClassTerm()[2]);
                intentDataChart.putExtra("term4", arrayDataClass.get(position).getClassTerm()[3]);
                intentDataChart.putExtra("term5", arrayDataClass.get(position).getClassTerm()[4]);
                intentDataChart.putExtra("term6", arrayDataClass.get(position).getClassTerm()[5]);
                intentDataChart.putExtra("term7", arrayDataClass.get(position).getClassTerm()[6]);
                intentDataChart.putExtra("term8", arrayDataClass.get(position).getClassTerm()[7]);
                intentDataChart.putExtra("scores1", arrayDataClass.get(position).getScoresClassTerm()[0]);
                intentDataChart.putExtra("scores2", arrayDataClass.get(position).getScoresClassTerm()[1]);
                intentDataChart.putExtra("scores3", arrayDataClass.get(position).getScoresClassTerm()[2]);
                intentDataChart.putExtra("scores4", arrayDataClass.get(position).getScoresClassTerm()[3]);
                intentDataChart.putExtra("scores5", arrayDataClass.get(position).getScoresClassTerm()[4]);
                intentDataChart.putExtra("scores6", arrayDataClass.get(position).getScoresClassTerm()[5]);
                intentDataChart.putExtra("scores7", arrayDataClass.get(position).getScoresClassTerm()[6]);
                intentDataChart.putExtra("scores8", arrayDataClass.get(position).getScoresClassTerm()[7]);
                startActivity(intentDataChart);
            }
        });
    }

    @Override
    public void setListViewClassCurrent(JSONArray jsonArray) {
        if(jsonArray.length()==0){
            tvClassList.setVisibility(View.VISIBLE);
            listViewClass.setVisibility(View.GONE);
        }
        else{
            tvClassList.setVisibility(View.GONE);
            listViewClass.setVisibility(View.VISIBLE);
            arrayDataClass = new ArrayList<>();

            for (int i=0; i<jsonArray.length(); i++){
                try{
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    DataChartClassItem item= new DataChartClassItem();
                    item.setIdClass(jsonObject.getInt("idClass"));
                    item.setClassName(jsonObject.getString("className"));
                    String[] strClassTerm = {jsonObject.getString("term1"),
                            jsonObject.getString("term2"),
                            jsonObject.getString("term3"),
                            jsonObject.getString("term4"),
                            jsonObject.getString("term5"),
                            jsonObject.getString("term6"),
                            jsonObject.getString("term7"),
                            jsonObject.getString("term8")};
                    item.setClassTerm(strClassTerm);
                    int[] intScores = {jsonObject.getInt("scores1"),
                            jsonObject.getInt("scores2"),
                            jsonObject.getInt("scores3"),
                            jsonObject.getInt("scores4"),
                            jsonObject.getInt("scores5"),
                            jsonObject.getInt("scores6"),
                            jsonObject.getInt("scores7"),
                            jsonObject.getInt("scores8")};
                    item.setScoresClassTerm(intScores);


                    arrayDataClass.add(item);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
            adapter = new DataChartClassAdapter(
                    DataChartListActivity.this,
                    R.layout.current_class_data_row,
                    arrayDataClass
            );

            listViewClass.setAdapter(adapter);
        }
    }
}
