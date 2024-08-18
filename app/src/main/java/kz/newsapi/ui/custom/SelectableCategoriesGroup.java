package kz.newsapi.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.core.view.ScrollingView;

public class SelectableCategoriesGroup extends ViewGroup implements ScrollingView {
    public SelectableCategoriesGroup(Context context) {
        super(context);
    }

    public SelectableCategoriesGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectableCategoriesGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SelectableCategoriesGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    public int computeHorizontalScrollRange() {
        return 0;
    }

    @Override
    public int computeHorizontalScrollOffset() {
        return 0;
    }

    @Override
    public int computeHorizontalScrollExtent() {
        return 0;
    }

    @Override
    public int computeVerticalScrollRange() {
        return 0;
    }

    @Override
    public int computeVerticalScrollOffset() {
        return 0;
    }

    @Override
    public int computeVerticalScrollExtent() {
        return 0;
    }
}
