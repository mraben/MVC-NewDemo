package com.example.mvc.ui.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.mvc.R;
import com.example.mvc.base.BaseActivity;
import com.example.mvc.bean.TabEntity;
import com.example.mvc.ui.fragment.HomeFragment;
import com.example.mvc.ui.fragment.LikeFragment;
import com.example.mvc.ui.fragment.SearchFragment;
import com.example.mvc.utils.ConstantTool;
import com.example.mvc.utils.ToastUtil;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

/**
 * Description:
 */

public class MainActivity extends BaseActivity {
    private TextView mpText;
    private CommonTabLayout ctlMain;
    private FrameLayout fmMain;
    private DrawerLayout dlMain;
    private LinearLayout dLLeft;

    private String[] mTitles = new String[ConstantTool.THR];
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int[] img_u = {R.drawable.home, R.drawable.like, R.drawable.search_tab};
    private int[] img_p = {R.drawable.home_p, R.drawable.like_p, R.drawable.search_tab_p};

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDate() {
        super.initDate();
        findView();
        initTab();
        initDL();
    }

    private void findView() {
        mTitles[ConstantTool.ZERO] = getString(R.string.home);
        mTitles[ConstantTool.ONE] = getString(R.string.like);
        mTitles[ConstantTool.TWO] = getString(R.string.search);
        mpText = findViewById(R.id.mp_text);
        ctlMain = findViewById(R.id.ctl_main);
        fmMain = findViewById(R.id.fm_main);
        dlMain = findViewById(R.id.dl_main);
        dLLeft = findViewById(R.id.DL_left);

        setClick(findViewById(R.id.mp_menu), findViewById(R.id.mp_search),
                findViewById(R.id.left_home), findViewById(R.id.left_like),
                findViewById(R.id.left_search), findViewById(R.id.left_suggest),
                findViewById(R.id.left_setting), findViewById(R.id.left_shape));
    }

    private void setClick(View... view) {
        for (View v : view) {
            v.setOnClickListener(this::onCostomClick);
        }
    }

    private void initDL() {
        dlMain.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        });
    }

    private void initTab() {
        mFragments.add(new HomeFragment());
        mFragments.add(new LikeFragment());
        mFragments.add(new SearchFragment());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], img_p[i], img_u[i]));
        }
        ctlMain.setTabData(mTabEntities, this, R.id.fm_main, mFragments);
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    public void onCostomClick(View v) {
        int id = v.getId();
        if (id == R.id.mp_menu) {
            dlMain.openDrawer(dLLeft);
        } else if (id == R.id.mp_search) {
            ToastUtil.showShort(this, getString(R.string.searchMenu));
        } else if (id == R.id.left_home) {
            closeDrawerAndSetTab(ConstantTool.ZERO);
        } else if (id == R.id.left_like) {
            closeDrawerAndSetTab(ConstantTool.ONE);
        } else if (id == R.id.left_search) {
            closeDrawerAndSetTab(ConstantTool.TWO);
        } else if (id == R.id.left_suggest) {
            ToastUtil.showShort(this, getString(R.string.ProblemsAndProposals));
        } else if (id == R.id.left_setting) {
            ToastUtil.showShort(this, getString(R.string.setting));
        } else if (id == R.id.left_shape) {
            ToastUtil.showShort(this, getString(R.string.ShapeFriend));
        }
    }

    private void closeDrawerAndSetTab(int tabIndex) {
        ctlMain.setCurrentTab(tabIndex);
        dlMain.closeDrawer(dLLeft);
    }
}
