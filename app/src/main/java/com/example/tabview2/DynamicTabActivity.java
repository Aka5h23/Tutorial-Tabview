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

import com.example.tabview2.FragmentOne;
import com.example.tabview2.FragmentThree;
import com.example.tabview2.FragmentTwo;
import com.google.android.material.badge.BadgeDrawable;
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

        BadgeDrawable badgeDrawable = tabLayout.getTabAt(0).getOrCreateBadge();
        badgeDrawable.setVisible(true);

        BadgeDrawable badgeDrawable2 = tabLayout.getTabAt(2).getOrCreateBadge();
        badgeDrawable2.setNumber(678);
        badgeDrawable.setVisible(true);
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

        int[] imageList={
                R.drawable.ic_baseline_favorite_24,
                R.drawable.ic_baseline_add_24,
                R.drawable.ic_baseline_color_lens_24
        };

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

            // Initialize drawable
            Drawable icon= ContextCompat.getDrawable(getApplicationContext()
                    ,imageList[position]);

            // set bound
            icon.setBounds(0,0,icon.getIntrinsicWidth(),
                    icon.getIntrinsicHeight());

            // Initialize spannable string
            SpannableString spannableString=new SpannableString("   "+ fragment_title.get(position));

            // Initialize image span
            ImageSpan imageSpan=new ImageSpan(icon,ImageSpan.ALIGN_CENTER);

            // Set span
            spannableString.setSpan(imageSpan,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // return spannable string
            return spannableString;
        }
    }
}