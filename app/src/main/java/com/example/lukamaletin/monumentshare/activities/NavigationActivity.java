package com.example.lukamaletin.monumentshare.activities;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lukamaletin.monumentshare.R;
import com.example.lukamaletin.monumentshare.adapters.TabAdapter;
import com.example.lukamaletin.monumentshare.database.dao.wrappers.TypeDAOWrapper;
import com.example.lukamaletin.monumentshare.models.Type;
import com.example.lukamaletin.monumentshare.utils.FileUtils;
import com.example.lukamaletin.monumentshare.utils.Preferences_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.File;
import java.io.IOException;

@OptionsMenu(R.menu.menu_options)
@EActivity(R.layout.activity_navigation)
public class NavigationActivity extends AppCompatActivity {

    private static final String TAG = NavigationActivity.class.getSimpleName();
    private static final String PHOTO_PATH = "PHOTO_PATH";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int[] fabIcons = {R.drawable.ic_photo_camera_white_24dp,
            R.drawable.ic_photo_camera_white_24dp, R.drawable.ic_add_white_24dp};

    @Bean
    TypeDAOWrapper typeDAOWrapper;

    @Pref
    Preferences_ preferences;

    @ViewById
    TabLayout tabLayout;

    @ViewById
    ViewPager viewPager;

    @ViewById
    FloatingActionButton fab;

    @Bean
    FileUtils fileUtils;

    private int currentTabPosition = 0;
    private boolean backPressed = false;
    private String currentPhotoPath;

    @AfterViews
    void setupTabs() {
        viewPager.setAdapter(new TabAdapter(this));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int newTabPosition = tab.getPosition();
                // Icon changes when switching to and from last tab
                if (newTabPosition == 2 || (currentTabPosition == 2 && newTabPosition != 2)) {
                    animateFab(newTabPosition);
                }
                currentTabPosition = newTabPosition;
                viewPager.setCurrentItem(newTabPosition);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Click
    void fab() {
        if (currentTabPosition == 0 || currentTabPosition == 1) {
            if (typeDAOWrapper.getTypes().size() == 0) {
                Toast.makeText(this, R.string.type_required, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
                final File imageFile;
                try {
                    imageFile = fileUtils.createImageFile();
                    currentPhotoPath = imageFile.getAbsolutePath();
                    Log.d(TAG, currentPhotoPath);

                    takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                    startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE);
                } catch (IOException e) {
                    Log.d(TAG, e.getMessage(), e);
                }
            }
        } else {
            addNewType();
        }
    }

    @OnActivityResult(REQUEST_IMAGE_CAPTURE)
    void onResult(int resultCode) {
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, currentPhotoPath, Toast.LENGTH_SHORT).show();
            Log.d(TAG, currentPhotoPath);

            NewMonumentActivity_.intent(this).photoPath(currentPhotoPath).start();
        } else {
            File file = new File(currentPhotoPath);
            boolean deleted = file.delete();
        }
    }

    private void addNewType() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.alertDialogStyle);
        builder.setTitle(getString(R.string.new_type));

        final EditText typeEdit = new EditText(this);
        typeEdit.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        builder.setView(typeEdit);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = typeEdit.getText().toString();
                if (!typeDAOWrapper.nameAvailable(name)) {
                    makeToast();
                } else {
                    Type newType = new Type(name);
                    typeDAOWrapper.addType(newType);
                }
            }
        });
        builder.setNegativeButton("Cancel", null);

        builder.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PHOTO_PATH, currentPhotoPath);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currentPhotoPath = savedInstanceState.getString(PHOTO_PATH);
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressed) {
            moveTaskToBack(true);
            super.onBackPressed();
        } else {
            backPressed = true;
            Toast.makeText(this, R.string.confirm_exit, Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressed = false;
                }
            }, 2000);
        }
    }

    @OptionsItem(R.id.itemLogout)
    void itemLogout() {
        preferences.id().remove();
        LoginActivity_.intent(this).start();
        finish();
    }

    private void animateFab(final int position) {
        fab.clearAnimation();

        // Scale down animation:
        ScaleAnimation shrink = new ScaleAnimation(1f, 0.2f, 1f, 0.2f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(150);
        shrink.setInterpolator(new DecelerateInterpolator());
        shrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationEnd(Animation animation) {
                fab.setImageDrawable(getResources().getDrawable(fabIcons[position], null));
                // Scale up animation:
                ScaleAnimation expand = new ScaleAnimation(0.2f, 1f, 0.2f, 1f, Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                expand.setDuration(100);
                expand.setInterpolator(new AccelerateInterpolator());
                fab.startAnimation(expand);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        fab.startAnimation(shrink);
    }

    private void makeToast() {
        Toast.makeText(this, R.string.type_name_unavailable, Toast.LENGTH_SHORT).show();
    }
}
