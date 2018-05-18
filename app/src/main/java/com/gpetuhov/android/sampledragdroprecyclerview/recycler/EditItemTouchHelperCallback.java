package com.gpetuhov.android.sampledragdroprecyclerview.recycler;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class EditItemTouchHelperCallback extends ItemTouchHelper.Callback {

  private final ItemAdapter itemAdapter;

  public EditItemTouchHelperCallback(ItemAdapter adapter) {
    itemAdapter = adapter;
  }

  @Override
  public boolean isLongPressDragEnabled() {
    return true;
  }

  @Override
  public boolean isItemViewSwipeEnabled() {
    return true;
  }

  @Override
  public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
    return makeMovementFlags(dragFlags, swipeFlags);
  }

  @Override
  public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                        RecyclerView.ViewHolder target) {
    itemAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    return true;
  }

  @Override
  public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    itemAdapter.onItemDismiss(viewHolder.getAdapterPosition());
  }

  @Override
  public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    super.clearView(recyclerView, viewHolder);

    if (viewHolder instanceof ItemAdapter.VHItem) {
      ((ItemAdapter.VHItem) viewHolder).onStopDrag();
    }
  }
}