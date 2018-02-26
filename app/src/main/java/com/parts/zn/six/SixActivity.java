package com.parts.zn.six;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.parts.zn.R;


/**
 * 使用 ViewStub 优化布局好处：
 * 1.ViewStub 调用时才会去生成其 layout 属性中的布局
 * 2.ViewStub 在生成指定布局后会将自己 remove 掉
 */
public class SixActivity extends AppCompatActivity {

    private TextView mTvShowMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);
        mTvShowMore = findViewById(R.id.tv_show_more);
    }

    public void showMore(View view) {
        ViewStub viewStub = findViewById(R.id.vs_more);
        viewStub.inflate();
        ((ViewGroup) mTvShowMore.getParent()).removeView(mTvShowMore);
    }

}
