package cn.matrixsci.doublerecording.app.base;

import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.common.MFGT;
import cn.matrixsci.doublerecording.utils.ExitAppUtils;
import cn.matrixsci.doublerecording.common.StatusBarCompat;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import static butterknife.ButterKnife.bind;

/**
 * BaseActivity
 */
public abstract class BaseActivity extends SwipeBackActivity {
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
        ExitAppUtils.getInstance().addActivity(this);
        bind(this);
        findView();
        initView();
        initData();
        setListener();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MFGT.finish(this);
    }

    protected abstract void findView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListener();

}
