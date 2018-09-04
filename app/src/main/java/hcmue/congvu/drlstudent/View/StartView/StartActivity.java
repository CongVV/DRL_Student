package hcmue.congvu.drlstudent.View.StartView;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import hcmue.congvu.drlstudent.R;
import hcmue.congvu.drlstudent.View.LogInView.LogInActivity;

/**
 * Created by CongVu on 21/08/2018.
 */

public class StartActivity extends AppCompatActivity {
    TextView tvTool, tvBook, tvCorrect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        tvTool = (TextView) findViewById(R.id.tvTool);
        tvBook = (TextView) findViewById(R.id.tvBook);
        tvCorrect = (TextView) findViewById(R.id.tvCorrect);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/brushsbi.ttf");

        tvTool.setTypeface(typeface);
        tvBook.setTypeface(typeface);
        tvCorrect.setTypeface(typeface);

        intView();
    }

    private void intView(){
        AnimationDrawable drawable = (AnimationDrawable) ((ImageView) findViewById(R.id.impProcess)).getDrawable();
        drawable.start();
        handler.sendEmptyMessageDelayed(0,3000);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(StartActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
