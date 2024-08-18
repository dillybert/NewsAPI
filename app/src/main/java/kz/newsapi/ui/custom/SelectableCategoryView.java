package kz.newsapi.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;

import androidx.annotation.Nullable;

public class SelectableCategoryView extends androidx.appcompat.widget.AppCompatTextView implements Checkable {
    public SelectableCategoryView(Context context) {
        super(context);
    }

    public SelectableCategoryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectableCategoryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean b) {

    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }
}
