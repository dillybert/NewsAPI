package kz.newsapi.data;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/v2/top-headlines")
    Call<ArticleListModel> getTopHeadlines(@Query("country") String country);

    @GET("/v2/top-headlines")
    Call<ArticleListModel> getTopHeadlinesByCategory(@Query("category") String category);

    @GET("/v2/everything")
    Call<ArticleListModel> getArticlesByQuery(@Query("query") String query);


    static ApiService create() {
        Interceptor interceptor = new Interceptor() {
            @NonNull
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request newRequest = original.newBuilder()
                        .header("X-Api-Key", "YOUR-API-KEY")
                        .build();

                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}
