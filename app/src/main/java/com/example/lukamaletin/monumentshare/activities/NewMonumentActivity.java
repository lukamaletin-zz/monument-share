package com.example.lukamaletin.monumentshare.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lukamaletin.monumentshare.R;
import com.example.lukamaletin.monumentshare.database.dao.wrappers.MonumentDAOWrapper;
import com.example.lukamaletin.monumentshare.database.dao.wrappers.TypeDAOWrapper;
import com.example.lukamaletin.monumentshare.models.Monument;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_new_monument)
public class NewMonumentActivity extends AppCompatActivity {

    @Extra
    String photoPath;

    @Bean
    MonumentDAOWrapper monumentDAOWrapper;

    @Bean
    TypeDAOWrapper typeDAOWrapper;

    @ViewById
    SimpleDraweeView background;

    @ViewById
    EditText title;

    @ViewById
    EditText description;

    @ViewById
    Spinner types;

    @AfterViews
    void setPhoto() {
        background.setImageURI(new Uri.Builder().scheme(UriUtil.LOCAL_FILE_SCHEME).path(photoPath).build());
    }

    @AfterViews
    void setSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, typeDAOWrapper.getTypeNames());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        types.setAdapter(adapter);
    }

    @Click
    @EditorAction(R.id.description)
    void addNewMonument() {
        Monument monument = new Monument();
        if (title.getText().toString().equals("")) {
            Toast.makeText(this, R.string.title_required, Toast.LENGTH_SHORT).show();
            return;
        } else {
            monument.setTitle(title.getText().toString());
        }
        monument.setImageUri(photoPath);
        monument.setDescription(description.getText().toString());
        monument.setType(typeDAOWrapper.getTypeByName(types.getSelectedItem().toString()));

        monumentDAOWrapper.addMonument(monument);
        finish();
    }

    @Click
    void cancelMonument() {
        finish();
    }
}
