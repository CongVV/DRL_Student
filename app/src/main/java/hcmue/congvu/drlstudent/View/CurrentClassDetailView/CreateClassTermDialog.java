package hcmue.congvu.drlstudent.View.CurrentClassDetailView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import hcmue.congvu.drlstudent.Model.YearModel.YearAdapter;
import hcmue.congvu.drlstudent.Model.YearModel.YearItem;
import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 02/10/2018.
 */
public class CreateClassTermDialog extends AppCompatDialogFragment {
    private Spinner spinnerYear, spinnerTerm;
    private ArrayList<YearItem> mYearList = new ArrayList<>();
    private YearAdapter mYearAdapter;
    public int yearCurrent, yearStart, yearEnd, yearTerm;
    private CreateClassTermDialogListener createClassTermDialogListener;
    public Context context;

    public CreateClassTermDialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create_class_term, null);

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        yearCurrent = calendar.get(Calendar.YEAR);
        yearStart = yearCurrent;
        yearEnd = yearCurrent+1;
        yearCurrent -=5;
        for(int i=0; i<10; i++){
            YearItem yearItem = new YearItem(yearCurrent, yearCurrent+1);
            mYearList.add(yearItem);
            yearCurrent++;
        }

        builder.setView(view)
                .setNegativeButton("HỦY", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String val = spinnerTerm.getSelectedItem().toString();
                        if(val.equals("I")){
                            yearTerm = 1;
                        }
                        else if(val.equals("II")){
                            yearTerm = 2;
                        }
                        else{
                            yearTerm = 3;
                        }
                        createClassTermDialogListener.applyCreateClassTerm(yearStart, yearEnd, yearTerm);
                    }
                });
        spinnerYear = view.findViewById(R.id.spinner_year);
        spinnerTerm = view.findViewById(R.id.spinner_term);
        mYearAdapter = new YearAdapter(context, mYearList);
        spinnerYear.setAdapter(mYearAdapter);
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                YearItem yearItem = (YearItem) parent.getItemAtPosition(position);
                //yearStart = mYearList.get(position).getmYearStart();
                yearStart = yearItem.getmYearStart();
                yearEnd = yearItem.getmYearEnd();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            createClassTermDialogListener = (CreateClassTermDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "Phải thực thi....");
        }
    }

    public interface CreateClassTermDialogListener{
        void applyCreateClassTerm(int yearStart, int yearEnd, int yearTerm);
    }
}
