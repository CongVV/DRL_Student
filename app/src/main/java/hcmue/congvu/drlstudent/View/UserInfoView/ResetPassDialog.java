package hcmue.congvu.drlstudent.View.UserInfoView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hcmue.congvu.drlstudent.R;

/**
 * Created by CongVu on 01/09/2018.
 */

public class ResetPassDialog extends AppCompatDialogFragment{
    private EditText edtPassCurrent, edtPassNew, edtRePassNew;
    private ResetPassDialogListener resetPassDialogListener;

    public ResetPassDialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_reset_pass, null);

        builder.setView(view)
                .setTitle("Reset Mật Khẩu")
                .setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*Toast.makeText(context, edtPassCurrent.getText().toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "123 nè", Toast.LENGTH_SHORT).show();*/
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String currentPassword, newPassword, reNewPassword;
                        currentPassword = edtPassCurrent.getText().toString();
                        newPassword = edtPassNew.getText().toString();
                        reNewPassword = edtRePassNew.getText().toString();
                        resetPassDialogListener.applyResetPass(currentPassword, newPassword, reNewPassword);
                    }
                });
        edtPassCurrent = view.findViewById(R.id.edt_current_pass);
        edtPassNew = view.findViewById(R.id.edt_new_password);
        edtRePassNew = view.findViewById(R.id.edt_new_repassword);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            resetPassDialogListener = (ResetPassDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
            "Phải thực thi....");
        }
    }


    public interface ResetPassDialogListener{
        void applyResetPass(String currentPassword, String newPassword, String reNewPassword);
    }
}
