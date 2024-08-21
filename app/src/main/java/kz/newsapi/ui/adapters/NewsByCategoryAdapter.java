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
import kz.newsapi.data.model.ArticleModel;

public class NewsByCategoryAdapter extends RecyclerView.Adapter<NewsByCategoryAdapter.ViewHolder> {
    private List<ArticleModel> mArticleList;

    @NonNull
    @Override
    public NewsByCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_by_category_news_grid_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsByCategoryAdapter.ViewHolder holder, int position) {
        holder.bind(mArticleList.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticleList != null ? mArticleList.size() : 0;
    }

    public void setArticleList(@NonNull List<ArticleModel> articleModels) {
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

            mArticle = itemView.findViewById(R.id.byCategoryNewsArticle);
            mArticleImage = itemView.findViewById(R.id.byCategoryNewsImage);
            mArticleAuthor = itemView.findViewById(R.id.byCategoryNewsAuthorShimmer);
            mArticleTitle = itemView.findViewById(R.id.byCategoryNewsTitleShimmer);
        }

        public void bind(@NonNull ArticleModel articleModel) {
            mArticleAuthor.setText(articleModel.getAuthor());
            mArticleTitle.setText(articleModel.getTitle());
            Glide.with(itemView).load(articleModel.getUrlToImage()).into(mArticleImage);

            mArticle.setOnClickListener(view -> {
                Intent articleIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articleModel.getUrl()));
                itemView.getContext().startActivity(articleIntent);
            });
        }
    }
}
