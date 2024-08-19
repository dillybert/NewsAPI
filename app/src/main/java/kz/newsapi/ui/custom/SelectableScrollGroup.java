package kz.newsapi.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class SelectableScrollGroup extends LinearLayout {
    private int mCheckedSelectableCategoryViewId = -1;
    private SelectableScrollGroupListener mListener;

    public SelectableScrollGroup(Context context) {
        super(context);
    }

    public SelectableScrollGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectableScrollGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            if (child instanceof SelectableView) {
                SelectableView selectableCategoryView = (SelectableView) child;

                if (selectableCategoryView.isChecked())
                    mCheckedSelectableCategoryViewId = selectableCategoryView.getId();

                selectableCategoryView.setOnClickListener(clickedSelectableCategoryView -> {
                    onSelectableCategoryViewClicked((SelectableView) clickedSelectableCategoryView);
                    mListener.onItemSelected((SelectableView) clickedSelectableCategoryView);
                });
            }
        }
    }

    public void onItemSelected(SelectableScrollGroupListener listener) {
        mListener = listener;
    }

    private void onSelectableCategoryViewClicked(SelectableView clickedSelectableCategoryView) {
        if (mCheckedSelectableCategoryViewId != -1) {
            SelectableView checkedSelectableCategoryView = findViewById(mCheckedSelectableCategoryViewId);
            checkedSelectableCategoryView.setChecked(false);
        }

        mCheckedSelectableCategoryViewId = clickedSelectableCategoryView.getId();
        clickedSelectableCategoryView.setChecked(true);
    }
}
