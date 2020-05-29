package cn.matrixsci.doublerecording.app.module.main;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import cn.matrixsci.doublerecording.R;
import cn.matrixsci.doublerecording.app.base.BaseActivity;
import cn.matrixsci.doublerecording.common.EventTag;
import cn.matrixsci.doublerecording.common.MFGT;
import cn.matrixsci.doublerecording.event.EventListener;
import cn.matrixsci.doublerecording.utils.CommonUtils;
import cn.matrixsci.doublerecording.utils.ExitAppUtils;
import cn.matrixsci.doublerecording.widget.DRTabHost;

import static butterknife.ButterKnife.bind;

public class MainActivity extends BaseActivity implements DRTabHost.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    @Bind(R.id.txt_title)
    TextView txt_title;
    @Bind(R.id.tab_host)
    DRTabHost mHost;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private MainTabAdapter adapter;
    private int keyBackClickCount = 0;
    private int PageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind(this);
        initMainTab();
    }


    @Override
    public void onBackPressed() {
        switch (keyBackClickCount++) {
            case 0:
                CommonUtils.showLongToast(getString(R.string.key_back_msg));
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        keyBackClickCount = 0;
                    }
                }, 3000);
                break;
            case 1:
//                MFGT.finishFormBottom(this);
                ExitAppUtils.getInstance().exit();
                break;
        }
    }

    private void initMainTab() {
        mHost.setChecked(0);
        adapter = new MainTabAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        adapter.clear();
        viewpager.setOffscreenPageLimit(4);
        adapter.addFragment(new HomeFragment(), getString(R.string.home_fragment));
        adapter.addFragment(new TodoPolicyFragment(), getString(R.string.todo_policy_fragment));
        adapter.addFragment(new MyPolicyFragment(), getString(R.string.my_policy_fragment));
        adapter.addFragment(new ProfileFragment(), getString(R.string.profile_fragment));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void findView() {
        getSwipeBackLayout().setEnableGesture(false);
    }

    @Override
    protected void initView() {
        mHost.setOnCheckedChangeListener(this);
        mHost.setChecked(0);
        viewpager.setOnPageChangeListener(this);
        //可控制tabHost上的红点
        //mHost.setHasNew(2, true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
//        EventManager.ins().registListener(EventTag.ACCOUNT_RELOGOUT, listener);
//        EventManager.ins().registListener(EventTag.CHAT_NOTIFY_MSG, listener);
    }

    @Override
    public void onCheckedChange(int checkedPosition, boolean byUser) {
        setTabMsg(checkedPosition);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mHost.setChecked(position);
        setTabMsg(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setTabMsg(int checkedPosition) {
        PageIndex = checkedPosition;
        viewpager.setCurrentItem(checkedPosition);
        switch (checkedPosition) {
            case 0:
                txt_title.setText(getString(R.string.home_fragment));
                break;
            case 1:
                txt_title.setText(getString(R.string.todo_policy_fragment));
                break;
            case 2:
                txt_title.setText(getString(R.string.my_policy_fragment));
                break;
            case 3:
                txt_title.setText(getString(R.string.profile_fragment));
                break;
        }
    }


    EventListener listener = new EventListener() {
        @Override
        public void handleMessage(int what, int arg1, int arg2, Object dataobj) {
            switch (what) {
                case EventTag.ACCOUNT_RELOGOUT:
                    CommonUtils.showLongToast((String) dataobj);
                    MFGT.gotoGuide(MainActivity.this);
                    break;
            }
        }
    };

    /**
     * 获取未读申请与通知消息
     *
     * @return
     */
//    public int getUnreadAddressCountTotal() {
//        int unreadAddressCountTotal = 0;
//        InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(this);
//        unreadAddressCountTotal = inviteMessgeDao.getUnreadMessagesCount();
//        return unreadAddressCountTotal;
//    }

    /**
     * 获取未读消息数
     *
     * @return
     */
//    public int getUnreadMsgCountTotal() {
//        int unreadMsgCountTotal = 0;
//        int chatroomUnreadMsgCount = 0;
//        unreadMsgCountTotal = EMClient.getInstance().chatManager().getUnreadMsgsCount();
//        for (EMConversation conversation : EMClient.getInstance().chatManager().getAllConversations().values()) {
//            if (conversation.getType() == EMConversation.EMConversationType.ChatRoom)
//                chatroomUnreadMsgCount = chatroomUnreadMsgCount + conversation.getUnreadMsgCount();
//        }
//        return unreadMsgCountTotal - chatroomUnreadMsgCount;
//    }
}
