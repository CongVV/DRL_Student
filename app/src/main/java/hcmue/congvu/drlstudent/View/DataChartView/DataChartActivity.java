package hcmue.congvu.drlstudent.View.DataChartView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.json.JSONArray;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 26/11/2018.
 */
public class DataChartActivity extends AppCompatActivity implements ViewProcessDataChart{
    BarChart chart;
    int userId, scores1, scores2, scores3, scores4, scores5, scores6, scores7, scores8;
    String avatar, term1, term2, term3, term4, term5, term6, term7, term8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_chart);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null){
            userId = bundle.getInt("userId");
            avatar = bundle.getString("avatar");
            term1 = bundle.getString("term1");
            term2 = bundle.getString("term2");
            term3 = bundle.getString("term3");
            term4 = bundle.getString("term4");
            term5 = bundle.getString("term5");
            term6 = bundle.getString("term6");
            term7 = bundle.getString("term7");
            term8 = bundle.getString("term8");
            scores1 = bundle.getInt("scores1");
            scores2 = bundle.getInt("scores2");
            scores3 = bundle.getInt("scores3");
            scores4 = bundle.getInt("scores4");
            scores5 = bundle.getInt("scores5");
            scores6 = bundle.getInt("scores6");
            scores7 = bundle.getInt("scores7");
            scores8 = bundle.getInt("scores8");
        }
        else{
            Toast.makeText(this, "Lỗi mạng! Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        }

        chart = (BarChart) findViewById(R.id.chart1);

        chart.setDrawBarShadow(false);
        //chart.setDrawValueAboveBar(false);

        chart.getDescription().setEnabled(false);
        //chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        //chart.setPinchZoom(false);

        chart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, scores1));
        barEntries.add(new BarEntry(2, scores2));
        barEntries.add(new BarEntry(3, scores3));
        barEntries.add(new BarEntry(4, scores4));
        barEntries.add(new BarEntry(5, scores5));
        barEntries.add(new BarEntry(6, scores6));
        barEntries.add(new BarEntry(7, scores7));
        barEntries.add(new BarEntry(8, scores8));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Điểm Rèn Luyện");

        BarData data = new BarData(barDataSet);
        chart.setData(data);

        String[] term = new String[]{term1, term2, term3, term4, term5, term6, term7, term8};

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(term));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        chart.setDragEnabled(true);
        chart.setVisibleXRangeMaximum(3);

        float barSpace = 0.1f;
        float groupSpace = 0.5f;

        data.setBarWidth(0.15f);
        chart.getXAxis().setAxisMinimum(0);
        //chart.groupBars(0, groupSpace, barSpace);
        chart.invalidate();
    }

    @Override
    public void setListViewClassCurrent(JSONArray jsonArray) {

    }
}
