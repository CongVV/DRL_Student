package hcmue.congvu.drlstudent.View.ActivityClassDetailView;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnDpWidthModel;
import de.codecrafters.tableview.model.TableColumnPxWidthModel;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 09/10/2018.
 */
public class FragmentClassDetailList extends Fragment {
    TableView<String[]> tableView;
    static final String[][] DATA_SHOW = {
            {"cc", "Cong Vu", "8"},
            {"cvv", "CongVV", "9"}
    };
    public Context context;

    static final String[] HEADER_DATA = {"Username", "Họ và Tên", "Điểm"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        tableView = (TableView<String[]>) view.findViewById(R.id.tb_activity_class_list);
        tableView.setColumnCount(3);
        /*TableColumnWeightModel columnWeight = new TableColumnWeightModel(4);
        columnWeight.setColumnWeight(1,1);
        columnWeight.setColumnWeight(2,3);
        columnWeight.setColumnWeight(3,1);
        tableView.setColumnModel(columnWeight);*/
        /*TableColumnPxWidthModel columnModel = new TableColumnPxWidthModel(3, 350);
        columnModel.setColumnWidth(1, 100);
        columnModel.setColumnWidth(2, 300);
        columnModel.setColumnWidth(3, 100);
        tableView.setColumnModel(columnModel);
        */
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(context, HEADER_DATA));
        //tableView.setDataAdapter(new SimpleTableDataAdapter(getActivity(), DATA_SHOW));
        return view;
    }

    public void setDataClassList(String [][] dataClassListArray){
        tableView.setDataAdapter(new SimpleTableDataAdapter(getActivity(), dataClassListArray));
    }
}
