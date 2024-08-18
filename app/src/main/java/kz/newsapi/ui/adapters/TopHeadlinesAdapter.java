package kz.newsapi.ui.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import kz.newsapi.R;
import kz.newsapi.data.ArticleModel;

public class TopHeadlinesAdapter extends RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder> {
    private List<ArticleModel> mArticleList;

    @NonNull
    @Override
    public TopHeadlinesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_news_horizontal_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopHeadlinesAdapter.ViewHolder holder, int position) {
        holder.bind(mArticleList.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticleList != null ? mArticleList.size() : 0;
    }

    public void setArticleList(List<ArticleModel> articleModels) {
        mArticleList = articleModels;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewGroup mArticle;
        private final ImageView mArticleImage;
        private final TextView mArticleAuthor;
        private final TextView mArticleTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mArticle = itemView.findViewById(R.id.article);
            mArticleImage = itemView.findViewById(R.id.topNewsImage);
            mArticleAuthor = itemView.findViewById(R.id.topNewsAuthor);
            mArticleTitle = itemView.findViewById(R.id.topNewsTitle);
        }

        public void bind(@NonNull ArticleModel articleModel) {
            mArticleTitle.setText(articleModel.getTitle());
            mArticleAuthor.setText(articleModel.getAuthor());
            Glide.with(itemView).load(articleModel.getUrlToImage()).into(mArticleImage);

            mArticle.setOnClickListener(view -> {
                Intent articleIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articleModel.getUrl()));
                itemView.getContext().startActivity(articleIntent);
            });
        }
    }
}
