package hcmue.congvu.drlstudent.View.CreateClassView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.HomeView.HomeActivity;

/**
 * Created by CongVu on 31/08/2018.
 */
public class CreateClassActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtClassName;
    Button btnNext, btnBack;
    int userId;
    String avatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        Bundle bundle = this.getIntent().getExtras();
        userId = bundle.getInt("userId");
        avatar = bundle.getString("avatar");

        btnNext         = (Button) findViewById(R.id.btn_next);
        btnBack         = (Button) findViewById(R.id.btn_back);
        edtClassName    = (EditText) findViewById(R.id.edt_create_class);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                Intent intent = new Intent(CreateClassActivity.this, HomeActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("avatar", avatar);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_next:
                Intent intentNext = new Intent(CreateClassActivity.this, HomeActivity.class);
                intentNext.putExtra("userId", userId);
                intentNext.putExtra("avatar", avatar);
                intentNext.putExtra("className", edtClassName.getText().toString());
                startActivity(intentNext);
                break;
        }
    }
}
