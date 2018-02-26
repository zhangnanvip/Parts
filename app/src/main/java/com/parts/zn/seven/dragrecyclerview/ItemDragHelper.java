package com.parts.zn.seven.dragrecyclerview;

import android.content.ClipData;
import android.os.Build;
import android.view.View;

/**
 * View 拖动帮助类
 *
 * @author zhangnan
 * @date 2018/1/10
 */

public class ItemDragHelper {

    private static ItemDragHelper singleton = null;

    private ItemDragHelper() {
    }

    public static ItemDragHelper getInstance() {
        if (singleton == null) {
            synchronized (ItemDragHelper.class) {
                if (singleton == null) {
                    singleton = new ItemDragHelper();
                }
            }
        }
        return singleton;
    }

    public void startDrag(View view) {
        startDrag(view, new View.DragShadowBuilder(view));
    }

    public void startDrag(View view, ClipData data) {
        startDrag(view, data, new View.DragShadowBuilder(view));
    }

    public void startDrag(View view, View.DragShadowBuilder dragShadowBuilder) {
        startDrag(view, null, dragShadowBuilder);
    }

    public void startDrag(View view, ClipData data, View.DragShadowBuilder dragShadowBuilder) {
        startDrag(view, data, dragShadowBuilder, null, 0);
    }

    public void startDrag(View view, ClipData data, View.DragShadowBuilder shadowBuilder, Object myLocalState, int flags) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.startDragAndDrop(data, shadowBuilder, myLocalState, flags);
        } else {
            view.startDrag(data, shadowBuilder, myLocalState, flags);
        }
    }
}
