package com.gpetuhov.android.sampledragdroprecyclerview.recycler.interfaces;

public interface ItemTouchHelperAdapter {
  boolean onItemMove(int fromPosition, int toPosition);
  void onItemDismiss(int position);
}