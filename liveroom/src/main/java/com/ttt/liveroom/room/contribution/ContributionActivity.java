package com.ttt.liveroom.room.contribution;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.ImageButton;

import com.ttt.liveroom.R;
import com.ttt.liveroom.base.BaseActivity;

import java.util.LinkedList;
import java.util.List;

import rx.functions.Action1;

public class ContributionActivity extends BaseActivity {

    ImageButton mBack;
    TabLayout mTabLayout;
    ViewPager mViewPager;

    private List<String> pageTitles = new LinkedList<>();
    private List<Fragment> mFragmentList = new LinkedList<>();
    private ContributionFragment mFragment;
    private String userId;

    @Override
    protected void parseIntentData(Intent intent, boolean isFrom) {
        super.parseIntentData(intent, isFrom);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contribution;
    }

    @Override
    protected void findViews() {
        pageTitles.add(getString(R.string.day_contribution));
        pageTitles.add(getString(R.string.week_contribution));
        pageTitles.add(getString(R.string.total_contribution));

        mBack = (ImageButton) findViewById(R.id.ib_back);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        String id = getIntent().getBundleExtra("user").getString("id");
        if (!TextUtils.isEmpty(id)) {
            userId = id;
        }

        mTabLayout.setupWithViewPager(mViewPager);
        initFragment();
        mViewPager.removeAllViews();

        mViewPager.setAdapter(new ContributionAdapter(getSupportFragmentManager(), mFragmentList, pageTitles));
        mViewPager.setOffscreenPageLimit(pageTitles.size());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    @Override
    protected void init() {
        subscribeClick(mBack, new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                finish();
            }
        });
    }


    private void initFragment() {
        for (int i = 0; i < pageTitles.size(); i++) {
            mFragment = ContributionFragment.newInstance(i, userId);
            mFragmentList.add(mFragment);
        }

    }

    private class ContributionAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        private List<String> titleList;

        public ContributionAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
            super(fm);
            this.fragmentList = fragmentList;
            this.titleList = titleList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }

}
