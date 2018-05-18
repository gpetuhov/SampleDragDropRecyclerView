package com.gpetuhov.android.sampledragdroprecyclerview.recycler;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.gpetuhov.android.sampledragdroprecyclerview.recycler.interfaces.ItemTouchHelperAdapter;

// This class controls drag-and-drop and swipe-to-dismiss behaviour
public class EditItemTouchHelperCallback extends ItemTouchHelper.Callback {

  private final ItemTouchHelperAdapter itemAdapter;

  public EditItemTouchHelperCallback(ItemAdapter adapter) {
    itemAdapter = adapter;
  }

  @Override
  public boolean isLongPressDragEnabled() {
    // Return true to enable drag on long click
    return true;
  }

  @Override
  public boolean isItemViewSwipeEnabled() {
    // Return true to enable swipe
    return true;
  }

  @Override
  public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    // Set flags for drag and swipe
    int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
    return makeMovementFlags(dragFlags, swipeFlags);
  }

  // This is called, when item is being moved
  @Override
  public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                        RecyclerView.ViewHolder target) {
    itemAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    return true;
  }

  // This is called, when item is being swiped
  @Override
  public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    itemAdapter.onItemDismiss(viewHolder.getAdapterPosition());
  }

  // And this is called when drag is finished (on item drop)
  @Override
  public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    super.clearView(recyclerView, viewHolder);
    itemAdapter.onItemDrop(viewHolder);
  }
}