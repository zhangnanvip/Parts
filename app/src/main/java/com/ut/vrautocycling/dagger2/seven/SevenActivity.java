package com.ut.vrautocycling.dagger2.seven;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ut.vrautocycling.dagger2.R;
import com.ut.vrautocycling.dagger2.seven.dragbubbleview.DragBubbleView;

/**
 * 自定义小型组件测试
 *
 * @author zhangnan
 */
public class SevenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven);
        DragBubbleView dbvNum = findViewById(R.id.dbv_num);
        dbvNum.setOnBubbleStateListener(new DragBubbleView.OnBubbleStateListener() {
            @Override
            public void onDrag() {
                Log.e("---> ", "拖拽气泡");
            }

            @Override
            public void onMove() {
                Log.e("---> ", "移动气泡");
            }

            @Override
            public void onRestore() {
                Log.e("---> ", "气泡恢复原来位置");
            }

            @Override
            public void onDismiss() {
                Log.e("---> ", "气泡消失");
            }
        });
    }
}
