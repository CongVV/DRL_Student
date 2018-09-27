package hcmue.congvu.drlstudent.View.LogInView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import hcmue.congvu.drlstudent.Controller.LogInController.ControllerLogicProcessLogIn;
import hcmue.congvu.drlstudent.Model.UserModel.UserItem;
import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.ForgotPassView.ForgotPassActivity;
import hcmue.congvu.drlstudent.View.HomeView.HomeActivity;
import hcmue.congvu.drlstudent.View.SignUpView.SignUpActivity;

/**
 * Created by CongVu on 22/08/2018.
 */
public class LogInActivity extends AppCompatActivity implements ViewProcessLogIn,View.OnClickListener{
    private Button btnLogIn, btnSignUp, btnForgotPass;
    private EditText edtUsername, edtPassword;
    private String username, password;
    private ControllerLogicProcessLogIn controllerLogicProcessLogIn = new ControllerLogicProcessLogIn(this,this);
    private ProgressBar progressBarLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogIn            = (Button) findViewById(R.id.btn_login);
        btnSignUp           = (Button) findViewById(R.id.btn_signup);
        btnForgotPass       = (Button) findViewById(R.id.btn_forgot_pass);
        edtUsername         = (EditText) findViewById(R.id.edt_username);
        edtPassword         = (EditText) findViewById(R.id.edt_password);
        progressBarLogIn    = (ProgressBar) findViewById(R.id.progressBarLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);
        btnForgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if(validateForm()) {
                    username = edtUsername.getText().toString();
                    password = edtPassword.getText().toString();
                    progressBarLogIn.setVisibility(View.VISIBLE);
                    controllerLogicProcessLogIn.checkLogIn(username, password);
                }
                break;
            case R.id.btn_signup:
                Intent intentSignUp = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intentSignUp);
                break;
            case R.id.btn_forgot_pass:
                Intent intentForgot = new Intent(LogInActivity.this, ForgotPassActivity.class);
                startActivity(intentForgot);
                break;
        }
    }

    @Override
    public void logInSuccessfull(int userId, String avatar) {
        progressBarLogIn.setVisibility(View.GONE);
        Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("avatar", avatar);
        startActivity(intent);
        finish();
    }

    @Override
    public void logInFail() {
        progressBarLogIn.setVisibility(View.GONE);
        Toast.makeText(this, "Tên Đăng Nhập hoặc Mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logInError() {
        progressBarLogIn.setVisibility(View.GONE);
        Toast.makeText(this, "Lỗi Mạng!!!", Toast.LENGTH_SHORT).show();
    }

    public boolean validateForm() {
        boolean valid = true;
        if(TextUtils.isEmpty(edtUsername.getText().toString())){
            edtUsername.setError("Tên Đăng Nhập không được trống!");
            valid = false;
        } else {
            edtUsername.setError(null);
        }

        if(TextUtils.isEmpty(edtPassword.getText().toString())){
            edtPassword.setError("Mật khẩu không được trống!");
            valid = false;
        } else {
            edtPassword.setError(null);
        }

        return valid;
    }
}
