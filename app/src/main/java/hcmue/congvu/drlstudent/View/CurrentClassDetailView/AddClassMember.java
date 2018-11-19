package hcmue.congvu.drlstudent.View.CurrentClassDetailView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 17/11/2018.
 */
public class AddClassMember extends AppCompatDialogFragment {
    public Context context;
    private EditText edtUsername;
    private Spinner spinnerStyleStudent;
    private AddClassMemberListener listener;

    public AddClassMember(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_class_member, null);

        edtUsername = view.findViewById(R.id.edtUsernameStudent);
        spinnerStyleStudent = view.findViewById(R.id.spinnerStyleStudent);

        builder.setTitle("Thêm thành viên mới");
        builder.setView(view)
                .setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String val = spinnerStyleStudent.getSelectedItem().toString();
                        String username = edtUsername.getText().toString();
                        int typeStudent=1;
                        if(val.equals("Cán bộ")){
                            typeStudent = 2;
                        }
                        listener.applyAddClassMember(username, typeStudent);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (AddClassMemberListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "Phải thực thi....");
        }
    }

    public interface AddClassMemberListener{
        void applyAddClassMember(String username, int typeStudent);
    }
}
