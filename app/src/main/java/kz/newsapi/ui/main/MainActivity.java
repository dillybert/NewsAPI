package kz.newsapi.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import kz.newsapi.databinding.ActivityMainBinding;
import kz.newsapi.ui.BaseActivity;
import kz.newsapi.ui.adapters.TopHeadlinesAdapter;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private static final String LOG_TAG = MainActivity.class.getName();

    private TopHeadlinesAdapter mTopHeadlinesAdapter;
    private ShimmerFrameLayout mTopHeadlinesShimmerLayout;

    @Override
    protected ActivityMainBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    protected MainViewModel createViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d", Locale.ENGLISH);
        String date = localDate.format(formatter);
        binding.date.setText(date);

        mTopHeadlinesShimmerLayout = binding.topNewsHorizontalListShimmer;

        mTopHeadlinesAdapter = new TopHeadlinesAdapter();

        RecyclerView topHeadlinesRecyclerView = binding.topNewsHorizontalList;

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(topHeadlinesRecyclerView);

        topHeadlinesRecyclerView.setAdapter(mTopHeadlinesAdapter);
        topHeadlinesRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        viewModel.getTopHeadlines().observe(this, resource -> {
            switch (resource.status) {
                case LOADING:
                    mTopHeadlinesShimmerLayout.startShimmer();
                    break;
                case ERROR:
                    Log.e(LOG_TAG, resource.message);
                    break;
                case SUCCESS:
                    mTopHeadlinesShimmerLayout.stopShimmer();
                    mTopHeadlinesShimmerLayout.setVisibility(View.GONE);
                    mTopHeadlinesAdapter.setArticleList(resource.data.getArticles());
                    break;
            }
        });

        viewModel.fetchTopHeadlines();
    }
}
