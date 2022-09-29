package com.example.tabview2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DynamicTabActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_tab);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.view_pager);

        // Setup tab layout
        tabLayout.setupWithViewPager(viewPager);

        // Prepare view pager
        prepareViewPager(viewPager);
    }
    private void prepareViewPager(ViewPager viewPager) {
        // Initialize main adapter
        MainAdapter adapter=new MainAdapter(getSupportFragmentManager());

        // Initialize main fragment
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();

        Bundle bundle=new Bundle();
        bundle.putString("title","One");
        fragmentOne.setArguments(bundle);
        adapter.addFragment(fragmentOne,"One");

        bundle=new Bundle();
        bundle.putString("title","Two");
        fragmentTwo.setArguments(bundle);
        adapter.addFragment(fragmentTwo,"Two");

        bundle=new Bundle();
        bundle.putString("title","Three");
        fragmentThree.setArguments(bundle);
        adapter.addFragment(fragmentThree,"Three");

        // set adapter
        viewPager.setAdapter(adapter);
    }
    private class MainAdapter extends FragmentPagerAdapter {
        // Initialize arrayList
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> fragment_title =new ArrayList<>();

        // Create constructor
        public void addFragment(Fragment fragment,String s)
        {
            // Add fragment
            fragments.add(fragment);
            // Add title
            fragment_title.add(s);
        }

        public MainAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // return fragment position
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            // Return fragment array list size
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            // Initialize spannable string
            SpannableString spannableString=new SpannableString("   "+ fragment_title.get(position));

            // return spannable string
            return spannableString;
        }
    }
}