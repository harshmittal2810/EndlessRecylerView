package com.web2droid.endlessscrollingrecylerview;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.web2droid.endlessrecylerview.EndlessRecyclerOnScrollListener;
import com.web2droid.endlessscrollingrecylerview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mStringList;
    private int mLoadedItems = 0;
    private DataAdapter mDataAdapter;
    private ActivityMainBinding mActivityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Initializing Layout Manager For RecylerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mActivityMainBinding.recyclerView.setLayoutManager(linearLayoutManager);
        mActivityMainBinding.recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));

        mStringList = new ArrayList<>();
        mDataAdapter = new DataAdapter(mStringList, new OnRecylerViewItemClickListner() {
            @Override
            public void OnItemClick(String name) {
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
        mActivityMainBinding.recyclerView.setAdapter(mDataAdapter);

        addDataToList();

        mActivityMainBinding.recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                addDataToList();
            }
        });

    }

    private void addDataToList() {
        mActivityMainBinding.itemProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 30; i++) {
                    mStringList.add("Item : " + mLoadedItems);
                    mLoadedItems++;
                }
                mDataAdapter.notifyDataSetChanged();
                mActivityMainBinding.itemProgressBar.setVisibility(View.GONE);
            }
        }, 1000);

    }
}
