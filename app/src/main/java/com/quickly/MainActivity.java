package com.quickly;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.quickly.activity.base.BaseActivity;
import com.quickly.fragment.CircleFragment;
import com.quickly.fragment.HomeFragment;
import com.quickly.fragment.RefreshFragment;
import com.quickly.fragment.VideoFragment;

public class MainActivity extends BaseActivity  implements OnBottomNavigationItemClickListener{

    //底部菜单
    private BottomNavigationView mBottomNavigationView;

    // fragment
    private Fragment mHomeFragment;

    private Fragment mCircleFragment;

    private Fragment mVideoFragment;

    private Fragment mRefreshFragment;

    private FragmentManager mFragmentManager;







    @Override
    public int getContentViewResId() {
        return R.layout.activity_main_layout;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        mBottomNavigationView = findViewById(R.id.bottomNavigation);

        mHomeFragment = HomeFragment.newInstance();

        BottomNavigationItem navHome = new BottomNavigationItem("首页", ContextCompat.getColor(this, R.color.colorAccent), R.drawable.menu_home);
        BottomNavigationItem navCircle = new BottomNavigationItem("圈子", ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.menu_circle);
        BottomNavigationItem navRefresh2 = new BottomNavigationItem("刷新", ContextCompat.getColor(this, R.color.colorPrimary), R.mipmap.home_banner_01);
        BottomNavigationItem navVideo = new BottomNavigationItem("视频", ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.menu_circle);
        BottomNavigationItem navRefresh = new BottomNavigationItem("刷新", ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.menu_circle);
        mBottomNavigationView.addTab(navHome);
        mBottomNavigationView.addTab(navCircle);
        mBottomNavigationView.addTab(navVideo);
        mBottomNavigationView.addTab(navRefresh);
        mBottomNavigationView.addTab(navRefresh2);

        mBottomNavigationView.disableViewPagerSlide();

        mBottomNavigationView.setOnBottomNavigationItemClickListener(this);


        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        // 添加初始化页面
        fragmentTransaction.replace(R.id.home_content,mHomeFragment);
        fragmentTransaction.commit();






    }

    @Override
    public void onNavigationItemClick(int index) {


        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        switch (index){
            case 0:
                // 隐藏其它的fragment
                hideFragment(mCircleFragment,fragmentTransaction);
                hideFragment(mVideoFragment,fragmentTransaction);
                hideFragment(mRefreshFragment,fragmentTransaction);

                if(mHomeFragment == null) {
                    mHomeFragment =HomeFragment.newInstance();
                    fragmentTransaction.add(R.id.home_content,mHomeFragment);
                }else {
                    fragmentTransaction.show(mHomeFragment);
                }

                break;
            case 1:
                // 隐藏其它的fragment
                hideFragment(mHomeFragment,fragmentTransaction);
                hideFragment(mVideoFragment,fragmentTransaction);
                hideFragment(mRefreshFragment,fragmentTransaction);

                if(mCircleFragment == null) {
                    mCircleFragment = CircleFragment.newInstance();
                    fragmentTransaction.add(R.id.home_content,mCircleFragment);
                }else {
                    fragmentTransaction.show(mCircleFragment);
                }

                break;
            case 2:

                // 隐藏其它的fragment
                hideFragment(mHomeFragment,fragmentTransaction);
                hideFragment(mCircleFragment,fragmentTransaction);
                hideFragment(mRefreshFragment,fragmentTransaction);

                if(mVideoFragment == null) {
                    mVideoFragment = new VideoFragment();
                    fragmentTransaction.add(R.id.home_content,mVideoFragment);
                }else {
                    fragmentTransaction.show(mVideoFragment);
                }

                break;
            case 3:
                // 隐藏其它的fragment
                hideFragment(mHomeFragment,fragmentTransaction);
                hideFragment(mCircleFragment,fragmentTransaction);
                hideFragment(mVideoFragment,fragmentTransaction);

                if(mRefreshFragment == null) {
                    mRefreshFragment = new RefreshFragment();
                    fragmentTransaction.add(R.id.home_content,mRefreshFragment);
                }else {
                    fragmentTransaction.show(mRefreshFragment);
                }
                break;
        }

        fragmentTransaction.commit();

    }



    /**
     * 隐藏具体的Fragment
     *
     * @param fragment
     * @param fragmentTransaction
     */
    private void hideFragment(Fragment fragment, FragmentTransaction fragmentTransaction) {
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }










}
