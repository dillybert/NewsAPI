package kz.newsapi.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import kz.newsapi.databinding.ActivityMainBinding;
import kz.newsapi.ui.BaseActivity;
import kz.newsapi.ui.adapters.NewsByCategoryAdapter;
import kz.newsapi.ui.adapters.TopHeadlinesAdapter;
import kz.newsapi.ui.custom.SelectableScrollGroup;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private static final String LOG_TAG = MainActivity.class.getName();

    private TopHeadlinesAdapter mTopHeadlinesAdapter;
    private NewsByCategoryAdapter mNewsByCategoryAdapter;

    private ShimmerFrameLayout mTopHeadlinesShimmerLayout;
    private ShimmerFrameLayout mNewsByCategoryShimmerLayout;

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
        mNewsByCategoryShimmerLayout = binding.byCategoryNewsListShimmer;

        SelectableScrollGroup selectableCategories = binding.selectableCategories;

        mTopHeadlinesAdapter = new TopHeadlinesAdapter();
        mNewsByCategoryAdapter = new NewsByCategoryAdapter();

        RecyclerView topHeadlinesRecyclerView = binding.topNewsHorizontalList;

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(topHeadlinesRecyclerView);

        topHeadlinesRecyclerView.setAdapter(mTopHeadlinesAdapter);
        topHeadlinesRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        RecyclerView newsByCategoryRecyclerView = binding.byCategoryNewsList;
        newsByCategoryRecyclerView.setNestedScrollingEnabled(false);
        newsByCategoryRecyclerView.setAdapter(mNewsByCategoryAdapter);
        newsByCategoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

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

        viewModel.getNewsByCategory().observe(this, resource -> {
            switch (resource.status) {
                case LOADING:
                    newsByCategoryRecyclerView.setVisibility(View.INVISIBLE);
                    mNewsByCategoryShimmerLayout.setVisibility(View.VISIBLE);
                    mNewsByCategoryShimmerLayout.startShimmer();
                    break;
                case ERROR:
                    Log.e(LOG_TAG, resource.message);
                    break;
                case SUCCESS:
                    newsByCategoryRecyclerView.setVisibility(View.VISIBLE);
                    mNewsByCategoryShimmerLayout.stopShimmer();
                    mNewsByCategoryShimmerLayout.setVisibility(View.INVISIBLE);
                    mNewsByCategoryAdapter.setArticleList(resource.data.getArticles());
            }
        });

        viewModel.getCategory().observe(this, category -> {
            viewModel.fetchNewsByCategory();
        });

        selectableCategories.onItemSelected(selectableView -> {
            String category = selectableView.getText().toString().toLowerCase();
            viewModel.setCategory(category);
        });

        viewModel.fetchNewsByCategory();
        viewModel.fetchTopHeadlines();
    }
}
