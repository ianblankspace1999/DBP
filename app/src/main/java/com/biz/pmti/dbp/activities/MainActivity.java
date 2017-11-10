package com.biz.pmti.dbp.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.biz.pmti.dbp.R;
import com.biz.pmti.dbp.adapter.ClientViewPagerAdapter;
import com.biz.pmti.dbp.fragments.BaseFragment;
import com.biz.pmti.dbp.fragments.FragmentTransactionFive;
import com.biz.pmti.dbp.fragments.FragmentTransactionFour;
import com.biz.pmti.dbp.fragments.FragmentTransactionOne;
import com.biz.pmti.dbp.fragments.FragmentTransactionSix;
import com.biz.pmti.dbp.fragments.FragmentTransactionThree;
import com.biz.pmti.dbp.fragments.FragmentTransactionTwo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.customview.CustomViewPager;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.img_home)
    ImageView mImgHome;


    @BindView(R.id.title_munc)
    TextView mTitleMunc;

    @BindView(R.id.tvPageLabel)
    TextView mTvPageLabel;

    @BindView(R.id.vpRegistration)
    CustomViewPager mVpRegistration;

    @BindView(R.id.btnCancel)
    Button mBtnCancel;

    @BindView(R.id.btnSignUp)
    Button mBtnSignUp;

    @BindView(R.id.nav_view)
    NavigationView mNavView;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.vpStripOne)
    LinearLayout mVpStripOne;
    @BindView(R.id.vpStripTwo)
    LinearLayout mVpStripTwo;
    @BindView(R.id.vpStripThree)
    LinearLayout mVpStripThree;
    @BindView(R.id.vpStripFour)
    LinearLayout mVpStripFour;
    @BindView(R.id.vpStripFive)
    LinearLayout mVpStripFive;


    private ClientViewPagerAdapter mClientViewPagerAdapter;

    private Toolbar mToolBar;

    private int mPagePosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        ButterKnife.bind(this);

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
        }
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_drawer);

        initialize();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

//            case R.id.menuItemDownloadConference:
//                // download conference here
//                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initialize() {

        mClientViewPagerAdapter = new ClientViewPagerAdapter(getSupportFragmentManager());
        mClientViewPagerAdapter.addFragment(FragmentTransactionOne.newInstance());
        mClientViewPagerAdapter.addFragment(FragmentTransactionTwo.newInstance());
        mClientViewPagerAdapter.addFragment(FragmentTransactionThree.newInstance());
        mClientViewPagerAdapter.addFragment(FragmentTransactionFour.newInstance());
        mClientViewPagerAdapter.addFragment(FragmentTransactionFive.newInstance());
        mClientViewPagerAdapter.addFragment(FragmentTransactionSix.newInstance());

        mVpRegistration.setAdapter(mClientViewPagerAdapter);

        mVpRegistration.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPagePosition = position;
            }

            @Override
            public void onPageSelected(int position) {

                setStips(position);

                switch (position) {
                    case 0:
                        mBtnCancel.setAlpha(.5f);
                        mBtnCancel.setClickable(false);
                        mBtnSignUp.setText("NEXT");
                        mTvPageLabel.setText("TIN Verification");
                        break;
                    case 1:
                        mBtnCancel.setAlpha(1f);
                        mBtnCancel.setClickable(true);
                        mBtnSignUp.setText("NEXT");
                        mTvPageLabel.setText("Tax Payment");
                        break;
                    case 2:
                        mBtnCancel.setClickable(true);
                        mBtnSignUp.setText("NEXT");
                        mTvPageLabel.setText("Payment Details");
                        break;
                    case 3:
                        mBtnCancel.setClickable(true);
                        mBtnSignUp.setText("NEXT");
                        mTvPageLabel.setText("Payment Options");
                        break;
                    case 4:
                        mBtnCancel.setClickable(true);
                        mBtnSignUp.setText("NEXT");
                        mTvPageLabel.setText("Remarks");
                        break;
                    case 5 :
                        mBtnCancel.setClickable(true);
                        mBtnSignUp.setText("CONFIRM");
                        mTvPageLabel.setText("Transaction Summary");
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
    }

    private void setStips(int position) {

        switch (position) {

            case 0:
                mVpStripOne.setBackgroundColor(getResources().getColor(R.color.cl_green_selected));
                mVpStripTwo.setBackgroundColor(getResources().getColor(R.color.cl_gray_disable));
                mVpStripThree.setBackgroundColor(getResources().getColor(R.color.cl_gray_disable));
                mVpStripFour.setBackgroundColor(getResources().getColor(R.color.cl_gray_disable));
                mVpStripFive.setBackgroundColor(getResources().getColor(R.color.cl_gray_disable));

                break;
            case 1:
                mVpStripOne.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripTwo.setBackgroundColor(getResources().getColor(R.color.cl_green_selected));
                mVpStripThree.setBackgroundColor(getResources().getColor(R.color.cl_gray_disable));
                mVpStripFour.setBackgroundColor(getResources().getColor(R.color.cl_gray_disable));
                mVpStripFive.setBackgroundColor(getResources().getColor(R.color.cl_gray_disable));
                break;
            case 2:
                mVpStripOne.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripTwo.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripThree.setBackgroundColor(getResources().getColor(R.color.cl_green_selected));
                mVpStripFour.setBackgroundColor(getResources().getColor(R.color.cl_gray_disable));
                mVpStripFive.setBackgroundColor(getResources().getColor(R.color.cl_gray_disable));
                break;
            case 3:
                mVpStripOne.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripTwo.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripThree.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripFour.setBackgroundColor(getResources().getColor(R.color.cl_green_selected));
                mVpStripFive.setBackgroundColor(getResources().getColor(R.color.cl_gray_disable));
                break;
            case 4:
                mVpStripOne.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripTwo.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripThree.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripFour.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripFive.setBackgroundColor(getResources().getColor(R.color.cl_green_selected));
                break;
            case 5:
                mVpStripOne.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripTwo.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripThree.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripFour.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                mVpStripFive.setBackgroundColor(getResources().getColor(R.color.cl_blue_selected));
                break;

        }

    }


    @OnClick({R.id.btnCancel, R.id.btnSignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCancel:
                boolean pageChecker = mVpRegistration.getCurrentItem() == 0;
                if (!pageChecker) {
                    mVpRegistration.setCurrentItem(mVpRegistration.getCurrentItem() - 1);
                    return;
                }

                break;
            case R.id.btnSignUp:
                BaseFragment baseFragment = (BaseFragment) mClientViewPagerAdapter.getItem(mPagePosition);

                if (baseFragment.isValid()) {
                    mVpRegistration.setCurrentItem(mPagePosition + 1);
                }

//                mVpRegistration.setCurrentItem(mPagePosition + 1);


                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }



}
