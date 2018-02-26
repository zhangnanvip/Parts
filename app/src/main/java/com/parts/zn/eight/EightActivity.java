package com.parts.zn.eight;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.jaredrummler.android.widget.AnimatedSvgView;
import com.parts.zn.R;

/**
 * SVG 矢量图动画实现方式
 *
 * @author zhangnan
 * @date 2018/1/2
 */
public class EightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);
        /*
         * 方案一:
         * 在 https://shapeshifter.design/ 制作矢量图动画
         * 然后导出为 Animated Vector Drawable 的 xml 矢量图文件
         * 直接放入 drawable 文件夹使用。
         * 注意: (设置图片时使用 src ,不要使用 background)
         */
        ImageView ivSvg = findViewById(R.id.iv_animate);
        Drawable drawable = ivSvg.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        /*
         * 方案二:
         * 在模块 gradle 文件添加 AnimatedSvgView 依赖
         * 在 http://www.iconfont.cn/collections 阿里巴巴素材库选择需要的矢量图以 SVG 格式下载
         * Copy 出下载文件中的 pathData 、 fillColor 、viewportWidth 、 viewportHeight
         * 为 AnimatedSvgView 设置属性。
         */
        final AnimatedSvgView svgView = findViewById(R.id.asv_animate);
        svgView.postDelayed(new Runnable() {

            @Override
            public void run() {
                svgView.start();
            }
        }, 500);
    }

}
