package com.example.lukamaletin.monumentshare.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.example.lukamaletin.monumentshare.R;
import com.example.lukamaletin.monumentshare.adapters.TypesAdapter;
import com.example.lukamaletin.monumentshare.database.dao.wrappers.TypeDAOWrapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_items)
public class TypesFragment extends Fragment {

    @Bean
    TypesAdapter typesAdapter;

    @Bean
    TypeDAOWrapper typeDAOWrapper;

    @ViewById
    ListView listView;

    @ViewById
    SwipeRefreshLayout swipeRefresh;

    @AfterViews
    @UiThread(delay = 100)
    void setAdapter() {
        listView.setAdapter(typesAdapter);
        typesAdapter.setTypes(typeDAOWrapper.getTypes());

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                typesAdapter.setTypes(typeDAOWrapper.getTypes());
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (typesAdapter != null) {
            typesAdapter.setTypes(typeDAOWrapper.getTypes());
        }
    }
}
