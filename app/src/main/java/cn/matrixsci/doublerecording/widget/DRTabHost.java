package cn.matrixsci.doublerecording.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class DRTabHost extends LinearLayout implements OnClickListener {
    public DRTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view instanceof DRTabButton || view instanceof DRTabTextView) {
                view.setOnClickListener(this);
            }
        }
    }

    public void setChecked(int position) {
        setChecked(position, false);
    }

    private void setChecked(int position, boolean byUser) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view instanceof DRTabButton || view instanceof DRTabTextView) {
                view.setSelected(position == i);
                if (position == i && mListener != null) {
                    mListener.onCheckedChange(position, byUser);
                }
            }
        }
    }

    /**
     * 设置是否有更新
     *
     * @param position
     * @param hasNew
     */
    public void setHasNew(int position, boolean hasNew) {
        DRTabButton button = (DRTabButton) getChildAt(position);
        if (button != null) {
            button.setHasNew(hasNew);
        }
    }

    /**
     * 设置未读数
     *
     * @param position
     * @param count
     */
    public void setUnreadCount(int position, int count) {
        DRTabButton button = (DRTabButton) getChildAt(position);
        if (button != null) {
            button.setUnreadCount(count);
        }
    }

    @Override
    public void onClick(View v) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (v == view) {
                setChecked(i, true);
                break;
            }
        }
    }

    private OnCheckedChangeListener mListener;

    public void setOnCheckedChangeListener(OnCheckedChangeListener mListener) {
        this.mListener = mListener;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChange(int checkedPosition, boolean byUser);
    }
}
