package com.parts.zn.seven.dragrecyclerview;

import android.content.ClipData;
import android.content.ClipDescription;
import android.support.v7.widget.RecyclerView;

/**
 * @author zhangnan
 * @date 2018/1/10
 */

public class RecyclerViewItemDragListener extends DefaultRecyclerViewItemClickListener {

    private final String LABEL = "LABEL";

    public RecyclerViewItemDragListener(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public void onItemLongClick(RecyclerView.ViewHolder vh) {
        super.onItemLongClick(vh);
        ClipData.Item dataItem = new ClipData.Item(vh.getAdapterPosition() + "");
        ClipData dragData = new ClipData(LABEL, new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, dataItem);
        ItemDragHelper.getInstance().startDrag(vh.itemView, dragData);
    }
}
