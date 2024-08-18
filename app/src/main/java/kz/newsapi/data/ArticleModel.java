package kz.newsapi.data;

import com.google.gson.annotations.SerializedName;

public class ArticleModel {
    @SerializedName("author")
    private String mAuthor;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("urlToImage")
    private String mUrlToImage;

    @SerializedName("publishedAt")
    private String mPublishedAt;

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getUrlToImage() {
        return mUrlToImage != null ? mUrlToImage : "https://placehold.co/600x600/DABFFF/FFFFFF.jpg?text=Image Not Included";
    }

    public void setUrlToImage(String mUrlToImage) {
        this.mUrlToImage = mUrlToImage;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public void setPublishedAt(String mPublishedAt) {
        this.mPublishedAt = mPublishedAt;
    }
}
