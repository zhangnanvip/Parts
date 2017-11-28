package com.ut.vrautocycling.dagger2.five;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.ut.vrautocycling.dagger2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FiveActivity extends AppCompatActivity {

    private AutoCompleteTextView mTvContent;

    /**
     * 自定义适配器
     */
    private DataAdapter mDataAdapter;
    /**
     * 显示数据列表
     */
    private List<String> mShowText = new ArrayList<>();
    /**
     * 缓存数据列表
     */
    private List<String> mCacheText = new ArrayList<>();
    /**
     * 服务器数据
     */
    private List<String> mServiceText = new ArrayList<>();
    /**
     * 当输入框清空后重新查询
     */
    private boolean mReSearch = true;
    private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        mTvContent = findViewById(R.id.tv_content);

        mTvContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString().trim();
                if (TextUtils.isEmpty(str)) {
                    mReSearch = true;
                    return;
                }
                if (!mReSearch) {
                    mShowText.clear();
                    mShowText.addAll(Matcher.match(str, mCacheText));
                    mTvContent.showDropDown();
                    return;
                }
                if (str.length() > 0) {
                    mReSearch = false;
                }
                // 模拟从服务器模糊查询
                obtainDataFromService(str, new CallBack() {
                    @Override
                    public void onCallBack(String matchStr, List<String> data) {
                        mCacheText.clear();
                        mCacheText.addAll(data);
                        mShowText.clear();
                        mShowText.addAll(data);
                        if (mDataAdapter == null) {
                            mDataAdapter = new DataAdapter(mShowText);
                            mTvContent.setAdapter(mDataAdapter);
                        }
                        mTvContent.showDropDown();
                    }
                });
            }
        });
        mTvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FiveActivity.this, mShowText.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 模拟从服务器获取数据
     *
     * @param text     匹配字符串
     * @param callBack 回调函数
     */
    private void obtainDataFromService(final String text, final CallBack callBack) {
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                // 子线程模拟网络延迟
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 模仿模糊查询
                String[] texts = getResources().getStringArray(R.array.five_text);
                mServiceText.clear();
                for (String s : texts) {
                    if (s.contains(text)) {
                        mServiceText.add(s);
                    }
                }
                // 主线程返回数据更新 UI
                FiveActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onCallBack(text, mServiceText);
                    }
                });
            }
        });
    }

    interface CallBack {

        /**
         * 服务器回调数据
         *
         * @param matchStr 进行匹配字符串
         * @param data     服务器返回数据
         */
        void onCallBack(String matchStr, List<String> data);

    }
}
