package com.example.lukamaletin.monumentshare.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.lukamaletin.monumentshare.models.Type;
import com.example.lukamaletin.monumentshare.views.TypeItemView;
import com.example.lukamaletin.monumentshare.views.TypeItemView_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class TypesAdapter extends BaseAdapter {

    @RootContext
    Context context;
    private List<Type> types;

    @AfterInject
    void afterInject() {
        types = new ArrayList<>();
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> monumentTypes) {
        this.types = monumentTypes;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return types.size();
    }

    @Override
    public Object getItem(int i) {
        return types.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TypeItemView typeItemView;
        if (view == null) {
            typeItemView = TypeItemView_.build(context);
        } else {
            typeItemView = (TypeItemView) view;
        }

        typeItemView.bind((Type) getItem(i), i);
        return typeItemView;
    }
}
