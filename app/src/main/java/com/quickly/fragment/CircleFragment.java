package com.quickly.fragment;
import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.quickly.DownloadActivity;
import com.quickly.activity.MyCaptureActivity;
import com.quickly.R;
import com.quickly.adapter.GridViewApapter;
import com.quickly.fragment.base.BaseFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleFragment extends BaseFragment{

    //https://github.com/yipianfengye/android-zxingLibrary/blob/master/app/src/main/java/com/uuch/android_zxinglibrary/utils/CheckPermissionUtils.java
    // 权限



    private int SCAN_CODE = 1;

    private int REQUEST_IMAGE = 2;


    private ViewPager circleBanner;

    private CircleIndicator indicator;

    private List<Integer>  list = new ArrayList<>();


    private List<View>  mViews = new ArrayList<>();

    private Button  scanBtn;

    private Button  scanBtn2;

    private Button  scanBtn3;


    public static CircleFragment newInstance(){
        return new CircleFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_circle_layout;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        circleBanner = getmRootView().findViewById(R.id.circle_pager);

        indicator = getmRootView().findViewById(R.id.indicator);

        scanBtn = getmRootView().findViewById(R.id.scan_01);

        scanBtn2 = getmRootView().findViewById(R.id.scan_02);


        scanBtn3 = getmRootView().findViewById(R.id.scan_03);

        getmRootView().findViewById(R.id.scan_04).setOnClickListener(v->{

            Intent intent = new Intent();
            intent.setClass(getActivity(), DownloadActivity.class);
            startActivity(intent);


        });

        getmRootView().findViewById(R.id.scan_05).setOnClickListener(v->{


            Platform.ShareParams params = new Platform.ShareParams();

            Platform platform = ShareSDK.getPlatform(QQ.NAME);

            platform.share(params);

            OnekeyShare oks  = new OnekeyShare();
            oks.disableSSOWhenAuthorize();;
            oks.setTitle("此你妹法拉");
            oks.setTitleUrl("http://xyfoaofsdajof.com");
            oks.setText("我是分享的内容主题加快立法了艰苦奋斗i  ");
            oks.setImageUrl("http://image.luosen365.com/home_banner_02.jpg");
            oks.show(getActivity());


        });

        getmRootView().findViewById(R.id.scan_06).setOnClickListener(v->{

            Intent intent = new Intent();
            intent.setClass(getActivity(), DownloadActivity.class);
            startActivity(intent);


        });


        getmRootView().findViewById(R.id.scan_07).setOnClickListener(v->{




        });










        scanBtn.setOnClickListener(v->{

            if(hasPermission(Manifest.permission.CAMERA)){
                Intent intent = new Intent(getActivity(), MyCaptureActivity.class);
                startActivityForResult(intent,SCAN_CODE);
            }else {
                requestPermission(CAMERA_PERMISSION,new String[]{
                        Manifest.permission.CAMERA
                });
            }

        });


        scanBtn2.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE);
        });
        // notification 不显示问题


        //TODO  服务检查有  当打开app时 自动检测
        //TODO   用户手动触发检测 询问下载
        // 下载要吗跳转web页面下载  要吗在通知栏上显示下载

        // 用户登陆管理   登陆成功后可以localbrocast广播 需要更新ui的模块做更新
        scanBtn3.setOnClickListener(v->{

            // 向服务器发送请求  判断是否有最新版本

            //有则弹框询问用户是否要更新

            //无则提示已是最新版本



        });


        for (int i = 0; i < 8; i++) {
            list.add(R.mipmap.home_banner_01);
        }


        View   categoryPageOne = LayoutInflater.from(getActivity()).inflate(R.layout.circle_grid_view,null);

        GridView one = categoryPageOne.findViewById(R.id.circle_category_panel);



        View   categoryPageOTwo = LayoutInflater.from(getActivity()).inflate(R.layout.circle_grid_view,null);

        GridView two = categoryPageOTwo.findViewById(R.id.circle_category_panel);


        //  https://github.com/dpc761218914/CNiaoTuanGou/tree/master/viewpager%E5%95%86%E5%93%81%E5%88%86%E7%B1%BB%E7%9A%84%E5%AE%9E%E7%8E%B0/src

        // circleBanner.removeAllViews();




        MyAdapter  myAdapter = new MyAdapter();
        two.setAdapter(new GridViewApapter(getActivity(),list));
        one.setAdapter(new GridViewApapter(getActivity(),list));

        mViews.add(one);
        mViews.add(two);


        circleBanner.setAdapter(myAdapter);
        myAdapter.registerDataSetObserver(indicator.getDataSetObserver());
        indicator.setViewPager(circleBanner);



        JZVideoPlayerStandard jzVideoPlayerStandard =getmRootView().findViewById(R.id.videoplayer);
        jzVideoPlayerStandard.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");

        Uri uri = Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
        jzVideoPlayerStandard.thumbImageView.setImageURI(uri);


        //TODO  https://blog.csdn.net/u014537423/article/details/52556081   recyclerview 实习底部 加载更多    下拉方案待解决原理

    }



    //TODO 少一个回退 处理  待参考demo

    // 切换fragment 需要暂停视频

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * 处理二维码扫描结果
         */
        if (requestCode == SCAN_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(getActivity(), uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }



    }

    @Override
    public void doCamera() {
        super.doCamera();
        Toast.makeText(getActivity(), "正在写读sd卡", Toast.LENGTH_SHORT).show();
    }

    class MyAdapter extends PagerAdapter {


            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                View view = mViews.get(position);
                container.addView(view);


                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

                container.removeView((View) object);
            }


        }
}
