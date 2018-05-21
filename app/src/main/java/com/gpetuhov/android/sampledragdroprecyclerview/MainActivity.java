package com.gpetuhov.android.sampledragdroprecyclerview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.gpetuhov.android.sampledragdroprecyclerview.recycler.EditItemTouchHelperCallback;
import com.gpetuhov.android.sampledragdroprecyclerview.recycler.ItemAdapter;
import com.gpetuhov.android.sampledragdroprecyclerview.recycler.SimpleItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recycler_view);

    recyclerView.setHasFixedSize(true);
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(mLayoutManager);

    // Fill list with dummy data
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      list.add("Item " + i);
    }

    final ItemAdapter itemAdapter = new ItemAdapter(this, list);
    itemAdapter.setOnItemClickListener((view, position) ->
      Toast.makeText(MainActivity.this, "Click on: " + itemAdapter.getItem(position), Toast.LENGTH_SHORT).show());

    // These 3 lines enable item drag and swipe
    ItemTouchHelper.Callback callback = new EditItemTouchHelperCallback(itemAdapter);
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
    itemTouchHelper.attachToRecyclerView(recyclerView);

    recyclerView.setAdapter(itemAdapter);

    // Add dividers between items
    SimpleItemDecoration dividerItemDecoration =
      new SimpleItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider));
    recyclerView.addItemDecoration(dividerItemDecoration);
  }
}
