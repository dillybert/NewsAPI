package kz.newsapi.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Checkable;

import androidx.annotation.Nullable;

import kz.newsapi.R;

public class SelectableView extends androidx.appcompat.widget.AppCompatTextView implements Checkable {
    private boolean mChecked;
    private Drawable mBackground;

    public SelectableView(Context context) {
        super(context);
        init(null);
    }

    public SelectableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SelectableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.SelectableView);
            mChecked = a.getBoolean(R.styleable.SelectableView_checked, false);
            mBackground = a.getDrawable(R.styleable.SelectableView_android_background);
            a.recycle();
        }

        if (getId() == SelectableView.NO_ID)
            setId(generateViewId());

        if (mBackground != null)
            setBackground(mBackground);

        setClickable(true);
        setFocusable(true);
    }

    @Override
    public void setChecked(boolean b) {
        mChecked = b;
        refreshDrawableState();
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] drawableStates = super.onCreateDrawableState(extraSpace + 1);

        if (isChecked())
            mergeDrawableStates(drawableStates, new int[]{android.R.attr.state_checked});

        return drawableStates;
    }
}
