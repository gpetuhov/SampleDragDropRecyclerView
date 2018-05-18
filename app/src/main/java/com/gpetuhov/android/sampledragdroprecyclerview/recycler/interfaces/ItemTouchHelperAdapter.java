package com.gpetuhov.android.sampledragdroprecyclerview.recycler.interfaces;

import android.support.v7.widget.RecyclerView;

// Adapter, used in our RecyclerView must implement this interface
public interface ItemTouchHelperAdapter {
  void onItemMove(int fromPosition, int toPosition);
  void onItemDismiss(int position);
  void onItemDrop(RecyclerView.ViewHolder viewHolder);
}
