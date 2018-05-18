package com.gpetuhov.android.sampledragdroprecyclerview.recycler;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gpetuhov.android.sampledragdroprecyclerview.recycler.interfaces.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {

  // In this simple example, the adapter contains just list of Strings
  private List<String> itemList;
  private OnItemClickListener itemClickListener;
  private final LayoutInflater layoutInflater;

  public interface OnItemClickListener {
    void onItemClick(View view, int position);
  }

  public ItemAdapter(Context context, List<String> list) {
    this.itemList = list;
    this.layoutInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    View v = layoutInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
    return new VHItem(v);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
    if (viewHolder instanceof VHItem) {
      ((VHItem) viewHolder).text.setText(itemList.get(i));
    }
  }

  @Override
  public int getItemCount() {
    return itemList.size();
  }

  public String getItem(int position) {
    return itemList.get(position);
  }

  public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
    this.itemClickListener = mItemClickListener;
  }

  // This is called, when item is removed
  @Override
  public void onItemDismiss(int position) {
    itemList.remove(position);
    notifyItemRemoved(position);
  }

  // This is called, when item is moved
  @Override
  public void onItemMove(int fromPosition, int toPosition) {
    if (fromPosition < itemList.size() && toPosition < itemList.size()) {
      if (fromPosition < toPosition) {
        for (int i = fromPosition; i < toPosition; i++) {
          Collections.swap(itemList, i, i + 1);
        }
      } else {
        for (int i = fromPosition; i > toPosition; i--) {
          Collections.swap(itemList, i, i - 1);
        }
      }
      notifyItemMoved(fromPosition, toPosition);
    }
  }

  // This is called, when item is dropped (item movement has stopped)
  @Override
  public void onItemDrop(RecyclerView.ViewHolder viewHolder) {
    if (viewHolder instanceof VHItem) {
      ((VHItem) viewHolder).onStopDrag();
    }
  }

  // === ViewHolder ===
  // Implements click listener to show toast on item click
  // and long click listener to change item background color on start drag.
  public class VHItem extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public TextView text;

    VHItem(View itemView) {
      super(itemView);
      text = (TextView) itemView;
      itemView.setOnClickListener(this);
      itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
      if (itemClickListener != null) {
        itemClickListener.onItemClick(v, getLayoutPosition());
      }
    }

    @Override
    public boolean onLongClick(View v) {
      // On item long click set item's background to gray
      onStartDrag();
      return false;
    }

    private void onStartDrag() {
      itemView.setBackgroundColor(Color.LTGRAY);
    }

    // This is called, when drag is finished. Remove gray background
    void onStopDrag() {
      itemView.setBackgroundColor(0);
    }
  }
}