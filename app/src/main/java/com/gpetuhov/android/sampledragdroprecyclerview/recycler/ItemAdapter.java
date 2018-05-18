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

  private static final int TYPE_ITEM = 0;

  private List<ItemModel> itemList;
  private OnItemClickListener mItemClickListener;
  private final LayoutInflater mInflater;

  public ItemAdapter(Context context, List<ItemModel> list) {
    this.itemList = list;
    this.mInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    if (viewType == TYPE_ITEM) {
      //inflate your layout and pass it to view holder
      View v = mInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
      return new VHItem(v );
    }

    throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
  }

  @Override
  public int getItemViewType(int position) {
    return TYPE_ITEM;
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
    if (viewHolder instanceof VHItem) {
      ((VHItem) viewHolder).text.setText(itemList.get(i).getText());
    }
  }

  @Override
  public int getItemCount() {
    return itemList.size();
  }

  public ItemModel getItem(int position) {
    return itemList.get(position);
  }

  public interface OnItemClickListener {
    void onItemClick(View view, int position);
  }

  public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
    this.mItemClickListener = mItemClickListener;
  }

  @Override
  public void onItemDismiss(int position) {
    itemList.remove(position);
    notifyItemRemoved(position);
  }

  @Override
  public boolean onItemMove(int fromPosition, int toPosition) {
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
    return true;
  }

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
      if (mItemClickListener != null) {
        mItemClickListener.onItemClick(v, getPosition());
      }
    }

    @Override
    public boolean onLongClick(View v) {
      setGrayBackground();
      return false;
    }

    private void setGrayBackground() {
      itemView.setBackgroundColor(Color.LTGRAY);
    }

    public void resetBackground() {
      itemView.setBackgroundColor(0);
    }
  }
}