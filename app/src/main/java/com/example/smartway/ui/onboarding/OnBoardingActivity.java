package com.example.smartway.ui.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.smartway.R;
import com.example.smartway.base.BaseActivity;
import com.example.smartway.utils.ActivityLauncher;
import com.google.android.material.tabs.TabLayout;

public class OnBoardingActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private Button btnSkip;
    private Button btnNext;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
//        setupToolbar(getString(R.string.app_name), false);

        WalkthroughPagerAdapter walkthroughPagerAdapter = new WalkthroughPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.vpPager);
        viewPager.setAdapter(walkthroughPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        TabLayout tabs = findViewById(R.id.tabLayout);
        tabs.setupWithViewPager(viewPager, true);

        btnSkip = findViewById(R.id.btnSkip);
        btnNext = findViewById(R.id.btnNext);

        onClicks();
    }

    @Override
    protected void onDestroy() {
        viewPager.removeOnPageChangeListener(this);
        super.onDestroy();
    }

    private void onClicks() {
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //noinspection ConstantConditions
                if (getItem() < viewPager.getAdapter().getCount() - 1) {
                    viewPager.setCurrentItem(getItem() + 1, true);
                } else {
                    proceed();
                }
            }
        });
    }

    private int getItem() {
        return viewPager.getCurrentItem();
    }

    private void proceed() {
        ActivityLauncher.launchLoginActivity(this);
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 2) {
            btnNext.setText(R.string.done);
            btnSkip.setVisibility(View.GONE);
        } else {
            btnNext.setText(R.string.next);
            btnSkip.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static class WalkthroughFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "SECTION_NUMBER";

        int images[] = new int[]{R.drawable.banner1,
                R.drawable.banner2, R.drawable.banner3};

        public WalkthroughFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static WalkthroughFragment newInstance(int sectionNumber) {
            WalkthroughFragment fragment = new WalkthroughFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_walkthrough, container, false);
            ImageView ivImage = rootView.findViewById(R.id.ivBoardingImage);
            ivImage.setImageResource(images[getArguments().getInt(ARG_SECTION_NUMBER)]);
            return rootView;
        }
    }

    public class WalkthroughPagerAdapter extends FragmentStatePagerAdapter {

        WalkthroughPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a WalkthroughFragment (defined as a static inner class below).
            return WalkthroughFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

}
