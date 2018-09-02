package hcmue.congvu.drlstudent.View.UserInfoView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 01/09/2018.
 */
public class ResetPassDialog extends AppCompatDialogFragment {
    private EditText edtPassCurrent, edtPassNew, edtRePassNew;
    private ResetPassDialogListener resetPassDialogListener;
    private int idUser;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_reset_pass, null);

        builder.setView(view)
                .setTitle("Reset Mật Khẩu")
                .setNegativeButton("hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        edtPassCurrent = view.findViewById(R.id.edt_current_pass);
        edtPassNew = view.findViewById(R.id.edt_new_password);
        edtRePassNew = view.findViewById(R.id.edt_new_repassword);
        return builder.create();
    }

    public interface ResetPassDialogListener{

    }
}
