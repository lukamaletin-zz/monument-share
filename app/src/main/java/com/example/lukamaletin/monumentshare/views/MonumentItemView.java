package com.example.lukamaletin.monumentshare.views;

import android.content.Context;
import android.net.Uri;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.lukamaletin.monumentshare.R;
import com.example.lukamaletin.monumentshare.models.Monument;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_item_monument)
public class MonumentItemView extends FrameLayout {

    @ViewById
    TextView username;

    @ViewById
    TextView title;

    @ViewById
    TextView type;

    @ViewById
    TextView description;

    @ViewById
    SimpleDraweeView image;

    private Monument monument;

    public MonumentItemView(Context context) {
        super(context);
    }

    public void bind(Monument monument) {
        this.monument = monument;

        username.setText(monument.getUser().getUsername());
        title.setText(monument.getTitle());
        type.setText(getContext().getString(R.string.upload_message, monument.getType().getTypeName()));
        description.setText(monument.getDescription());
        image.setImageURI(new Uri.Builder().scheme(UriUtil.LOCAL_FILE_SCHEME).path(monument.getImageUri()).build());
    }
}
