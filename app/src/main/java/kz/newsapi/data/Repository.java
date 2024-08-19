package kz.newsapi.data;

import kz.newsapi.data.model.ArticleListModel;
import retrofit2.Call;

public class Repository {
    private final ApiService mApiService;

    public Repository() {
        mApiService = ApiService.create();
    }

    public Call<ArticleListModel> getTopHeadlines(String country) {
        return mApiService.getTopHeadlines(country);
    }

    public Call<ArticleListModel> getNewsByCategory(String category, String country) {
        return mApiService.getTopHeadlinesByCategory(category, country);
    }

    public Call<ArticleListModel> getArticlesByQuery(String query) {
        return mApiService.getArticlesByQuery(query);
    }
}
