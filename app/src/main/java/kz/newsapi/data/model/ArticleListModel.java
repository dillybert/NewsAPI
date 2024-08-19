package kz.newsapi.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleListModel {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("totalResults")
    private String mTotalResult;

    @SerializedName("articles")
    private List<ArticleModel> mArticles;

    @SerializedName("code")
    private String mCode;

    @SerializedName("message")
    private String mMessage;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getTotalResult() {
        return mTotalResult;
    }

    public void setTotalResult(String mTotalResult) {
        this.mTotalResult = mTotalResult;
    }

    public List<ArticleModel> getArticles() {
        return mArticles;
    }

    public void setArticles(List<ArticleModel> mArticles) {
        this.mArticles = mArticles;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String mCode) {
        this.mCode = mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
