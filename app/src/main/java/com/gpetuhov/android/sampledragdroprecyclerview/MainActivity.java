package com.gpetuhov.android.sampledragdroprecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.gpetuhov.android.sampledragdroprecyclerview.recycler.EditItemTouchHelperCallback;
import com.gpetuhov.android.sampledragdroprecyclerview.recycler.ItemAdapter;
import com.gpetuhov.android.sampledragdroprecyclerview.recycler.ItemModel;
import com.gpetuhov.android.sampledragdroprecyclerview.recycler.interfaces.OnStartDragListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnStartDragListener {

  private RecyclerView mRecyclerView;
  private ItemTouchHelper mItemTouchHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mRecyclerView = findViewById(R.id.recycler_view);

    mRecyclerView.setHasFixedSize(true);
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);

    List<ItemModel> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(new ItemModel("Item " + i));
    }

    ItemAdapter mAdapter = new ItemAdapter(this, list, this);
    ItemTouchHelper.Callback callback = new EditItemTouchHelperCallback(mAdapter);
    mItemTouchHelper = new ItemTouchHelper(callback);
    mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    mRecyclerView.setAdapter(mAdapter);
  }

  @Override
  public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    mItemTouchHelper.startDrag(viewHolder);
  }
}
