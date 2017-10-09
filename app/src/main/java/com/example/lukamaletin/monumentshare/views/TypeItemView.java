package com.example.lukamaletin.monumentshare.views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lukamaletin.monumentshare.R;
import com.example.lukamaletin.monumentshare.models.Type;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_item_type)
public class TypeItemView extends LinearLayout {

    @ViewById
    TextView typeNumber;

    @ViewById
    TextView typeName;

    Type type;

    public TypeItemView(Context context) {
        super(context);
    }

    public void bind(Type type, int i) {
        this.type = type;

        typeNumber.setText(Integer.toString(i + 1) + ". ");
        typeName.setText(type.getTypeName());
    }
}
