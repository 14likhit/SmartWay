package com.example.smartway.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartway.R;
import com.example.smartway.utils.Utils;

/**
 * Parent Activity for all the Activities.
 */

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    public void setupToolbar(@Nullable String title, boolean homeButtonEnable) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }

        if (!homeButtonEnable) {
            int inset = (int) Utils.dpToPx(this, 16);
            toolbar.setContentInsetsAbsolute(inset, inset);
        }

        TextView title_text_View = findViewById(R.id.toolbar_title_text_view);
        title_text_View.setText(title);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(homeButtonEnable);
            actionBar.setDisplayShowHomeEnabled(homeButtonEnable);

        }
    }

    protected BaseFragment getCurrentFragment() {
        return (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.container);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getCurrentFragment() == null || !getCurrentFragment().onBackPressed()) {
            super.onBackPressed();
        }
    }

    // Fragment Related
    protected void replaceFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, tag);
        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(int messageResId) {
        Toast.makeText(this, getString(messageResId), Toast.LENGTH_SHORT).show();
    }

}
