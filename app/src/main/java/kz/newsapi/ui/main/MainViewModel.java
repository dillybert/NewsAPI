package kz.newsapi.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import kz.newsapi.data.model.ArticleListModel;
import kz.newsapi.data.Repository;
import kz.newsapi.ui.BaseViewModel;
import kz.newsapi.utils.Resource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends BaseViewModel {
    private final Repository mRepository;
    private final MutableLiveData<String> mCountryCodeLiveData = new MutableLiveData<>("ru");
    private final MutableLiveData<String> mCategoryLiveData = new MutableLiveData<>("business");
    private final MutableLiveData<Resource<ArticleListModel>> mTopHeadlinesLiveData = new MutableLiveData<>();
    private final MutableLiveData<Resource<ArticleListModel>> mArticleByQueryLiveData = new MutableLiveData<>();
    private final MutableLiveData<Resource<ArticleListModel>> mByCategoryLiveData = new MutableLiveData<>();

    public MainViewModel() {
        this.mRepository = new Repository();
    }

    public LiveData<String> getCountryCode() {
        return mCountryCodeLiveData;
    }

    public LiveData<String> getCategory() {
        return mCategoryLiveData;
    }

    public LiveData<Resource<ArticleListModel>> getTopHeadlines() {
        return mTopHeadlinesLiveData;
    }

    public LiveData<Resource<ArticleListModel>> getNewsByCategory() {
        return mByCategoryLiveData;
    }

    public LiveData<Resource<ArticleListModel>> getArticleByQueryLiveData() {
        return mArticleByQueryLiveData;
    }

    public void setCountryCode(@NonNull String countryCode) {
        mCountryCodeLiveData.setValue(countryCode.toLowerCase());
    }

    public void setCategory(@NonNull String category) {
        mCategoryLiveData.setValue(category.toLowerCase());
    }

    public void fetchTopHeadlines() {
        mTopHeadlinesLiveData.setValue(Resource.loading(null));
        mRepository.getTopHeadlines(getCountryCode().getValue()).enqueue(new Callback<ArticleListModel>() {
            @Override
            public void onResponse(@NonNull Call<ArticleListModel> call, @NonNull Response<ArticleListModel> response) {
                if (response.isSuccessful()) {
                    mTopHeadlinesLiveData.setValue(Resource.success(response.body()));
                } else {
                    mTopHeadlinesLiveData.setValue(Resource.error(null, "Unknown Error Caught: " + response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticleListModel> call, @NonNull Throwable throwable) {
                mTopHeadlinesLiveData.setValue(Resource.error(null, "Unknown Error Caught: " + throwable.getMessage()));
            }
        });
    }

    public void fetchNewsByCategory() {
        mByCategoryLiveData.setValue(Resource.loading(null));
        mRepository.getNewsByCategory(getCategory().getValue(), getCountryCode().getValue()).enqueue(new Callback<ArticleListModel>() {
            @Override
            public void onResponse(@NonNull Call<ArticleListModel> call, @NonNull Response<ArticleListModel> response) {
                if (response.isSuccessful()) {
                    mByCategoryLiveData.setValue(Resource.success(response.body()));
                } else {
                    mByCategoryLiveData.setValue(Resource.error(null, "Unknown Error Caught: " + response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticleListModel> call, @NonNull Throwable throwable) {
                mByCategoryLiveData.setValue(Resource.error(null, "Unknown Error Caught: " + throwable.getMessage()));
            }
        });
    }

    public void fetchArticleByQuery(String query) {
        mArticleByQueryLiveData.setValue(Resource.loading(null));
        mRepository.getArticlesByQuery(query).enqueue(new Callback<ArticleListModel>() {
            @Override
            public void onResponse(@NonNull Call<ArticleListModel> call, @NonNull Response<ArticleListModel> response) {
                if (response.isSuccessful()) {
                    mArticleByQueryLiveData.setValue(Resource.success(response.body()));
                } else {
                    mArticleByQueryLiveData.setValue(Resource.error(null, "Unknown Error Caught: " + response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticleListModel> call, @NonNull Throwable throwable) {
                mArticleByQueryLiveData.setValue(Resource.error(null, "Unknown Error Caught: " + throwable.getMessage()));
            }
        });
    }
}
