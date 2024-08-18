package kz.newsapi.data;

import retrofit2.Call;

public class Repository {
    private final ApiService mApiService;

    public Repository() {
        mApiService = ApiService.create();
    }

    public Call<ArticleListModel> getTopHeadlines(String country) {
        return mApiService.getTopHeadlines(country);
    }

    public Call<ArticleListModel> getTopHeadlinesByCategory(String category) {
        return mApiService.getTopHeadlinesByCategory(category);
    }

    public Call<ArticleListModel> getArticlesByQuery(String query) {
        return mApiService.getArticlesByQuery(query);
    }
}
