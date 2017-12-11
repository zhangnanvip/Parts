package com.ut.vrautocycling.dagger2.seven;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ut.vrautocycling.dagger2.R;

/**
 * 自定义小型组件测试
 *
 * @author zhangnan
 */
public class SevenActivity extends AppCompatActivity {

    private ViewGroup mVGRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven);
        mVGRoot = findViewById(R.id.layout_root);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(32, 32);
        params.setMargins(100, 0, 0, 0);
        params.gravity = Gravity.CENTER;

        CircleTextView circleTextView = new CircleTextView(this);
        circleTextView.setLayoutParams(params);
        circleTextView.setBGColor(R.color.colorAccent);
        circleTextView.setText("1");
        circleTextView.setTextColor(R.color.colorPrimaryDark);
        circleTextView.setTextSize(10);
        circleTextView.setBorderColor(R.color.black);
        mVGRoot.addView(circleTextView);
        params.gravity = Gravity.CENTER;

        CircleTextView circleTextView2 = new CircleTextView(this);
        circleTextView2.setLayoutParams(params);
        circleTextView2.setBGColor(R.color.colorPrimaryDark);
        circleTextView2.setText("2");
        circleTextView2.setTextColor(R.color.colorAccent);
        circleTextView2.setTextSize(10);
        circleTextView2.setBorderColor(R.color.black);
        mVGRoot.addView(circleTextView2);
    }
}
