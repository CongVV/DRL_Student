package hcmue.congvu.drlstudent.View.ForgotPassView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.LogInView.LogInActivity;

/**
 * Created by CongVu on 29/08/2018.
 */
public class ForgotPassActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edtResetPass;
    Button btnReset, btnSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtResetPass    = (EditText) findViewById(R.id.edt_resetpass);
        btnReset        = (Button) findViewById(R.id.btn_reset);
        btnSignIn       = (Button) findViewById(R.id.btn_login);

        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reset:

                break;
            case R.id.btn_login:
                Intent intent = new Intent(ForgotPassActivity.this, LogInActivity.class);
                startActivity(intent);
                break;
        }
    }
}
