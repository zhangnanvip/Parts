package com.ut.vrautocycling.dagger2.seven;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ut.vrautocycling.dagger2.R;
import com.ut.vrautocycling.dagger2.seven.dragbubbleview.DragBubbleView;
import com.ut.vrautocycling.dagger2.seven.garbagecanview.FillingFragment;

/**
 * 自定义小型组件测试
 *
 * @author zhangnan
 */
public class SevenActivity extends AppCompatActivity {

    private DragBubbleView dbvNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven);
        dbvNum = findViewById(R.id.dbv_num);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_one:
                dbvNum.setVisibility(View.GONE);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_fragment, FillingFragment.newInstance(null));
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
