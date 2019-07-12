package com.example.finaltestingapp;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class MainActivity extends AppCompatActivity {

    private static final Random RANDOM = new Random();

    BlankFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.random:
                final int page = RANDOM.nextInt(mAdapter.getCount());
                Toast.makeText(this, "Changing to page " + page, Toast.LENGTH_SHORT);
                mPager.setCurrentItem(page);
                return true;

            case R.id.add_page:
                if (mAdapter.getCount() < 10) {
                    mAdapter.setCount(mAdapter.getCount() + 1);
                    mIndicator.notifyDataSetChanged();
                }
                return true;

            case R.id.remove_page:
                if (mAdapter.getCount() > 1) {
                    mAdapter.setCount(mAdapter.getCount() - 1);
                    mIndicator.notifyDataSetChanged();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new BlankFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        CirclePageIndicator smb = findViewById(R.id.indicator);
        //Here we can change params of our circles
        smb.userSettingsForCircles(100, 100);

        //Here we can change colors of our circles if you know int equals to colors
        int fillColor = ContextCompat.getColor(this, R.color.colorPrimary);
        int pageColor = ContextCompat.getColor(this, R.color.colorAccent);
        smb.setFillColor(fillColor);
        smb.setPageColor(pageColor);

        mIndicator = smb;
        mIndicator.setViewPager(mPager);
    }
}
