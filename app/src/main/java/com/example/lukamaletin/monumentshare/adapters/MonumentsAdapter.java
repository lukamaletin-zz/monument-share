package com.example.lukamaletin.monumentshare.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.lukamaletin.monumentshare.models.Monument;
import com.example.lukamaletin.monumentshare.views.MonumentItemView;
import com.example.lukamaletin.monumentshare.views.MonumentItemView_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class MonumentsAdapter extends BaseAdapter {

    @RootContext
    Context context;
    private List<Monument> monuments;

    @AfterInject
    void afterInject() {
        monuments = new ArrayList<>();
    }

    public List<Monument> getMonuments() {
        return monuments;
    }

    public void setMonuments(List<Monument> monuments) {
        this.monuments = monuments;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return monuments.size();
    }

    @Override
    public Object getItem(int i) {
        return monuments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MonumentItemView monumentItemView;
        if (view == null) {
            monumentItemView = MonumentItemView_.build(context);
        } else {
            monumentItemView = (MonumentItemView) view;
        }

        monumentItemView.bind((Monument) getItem(i));
        return monumentItemView;
    }
}
