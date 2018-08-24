package hcmue.congvu.drlstudent.View.LogInView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.SignUpView.SignUpActivity;
import hcmue.congvu.drlstudent.View.StartView.StartActivity;

/**
 * Created by CongVu on 22/08/2018.
 */
public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnLogIn, btnSignUp, btnForgotPass;
    EditText edtUsername, edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogIn = (Button) findViewById(R.id.btn_login);
        btnSignUp= (Button) findViewById(R.id.btn_signup);
        btnForgotPass = (Button) findViewById(R.id.btn_forgot_pass);
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);

        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                break;
            case R.id.btn_signup:
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_forgot_pass:
                break;
        }
    }
}
