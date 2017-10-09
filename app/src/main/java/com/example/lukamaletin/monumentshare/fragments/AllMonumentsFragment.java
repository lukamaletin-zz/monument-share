package com.example.lukamaletin.monumentshare.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.example.lukamaletin.monumentshare.R;
import com.example.lukamaletin.monumentshare.adapters.MonumentsAdapter;
import com.example.lukamaletin.monumentshare.database.dao.wrappers.MonumentDAOWrapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_items)
public class AllMonumentsFragment extends Fragment {

    @Bean
    MonumentsAdapter monumentsAdapter;

    @Bean
    MonumentDAOWrapper monumentDAOWrapper;

    @ViewById
    ListView listView;

    @ViewById
    SwipeRefreshLayout swipeRefresh;

    @AfterViews
    @UiThread(delay = 100)
    void setAdapter() {
        listView.setAdapter(monumentsAdapter);
        monumentsAdapter.setMonuments(monumentDAOWrapper.getAllMonuments());

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                monumentsAdapter.setMonuments(monumentDAOWrapper.getAllMonuments());
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (monumentsAdapter != null) {
            monumentsAdapter.setMonuments(monumentDAOWrapper.getAllMonuments());
        }
    }
}
