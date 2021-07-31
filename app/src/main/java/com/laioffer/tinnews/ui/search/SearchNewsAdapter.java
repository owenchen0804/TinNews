package com.laioffer.tinnews.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;

import java.util.ArrayList;
import java.util.List;

import com.laioffer.tinnews.R;
import com.squareup.picasso.Picasso;


public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder>{
    // 1. Supporting data:
    // TODO
    private List<Article> articles = new ArrayList<>(); //  dataset就是List of articles

    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }


    // 2. Adapter overrides: 这里需要实现：由于extend的这个adapter base class自带的abstract class
    // TODO
    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {  // 解决了如何recycle views
        Article article = articles.get(position);   //  position指的是要显示的cell在总的dataset的相对位置
        holder.itemTitleTextView.setText(article.title);    // Reuse the article然后加上title
        Picasso.get().load(article.urlToImage).resize(200, 200).into(holder.itemImageView);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }   //  这里需要知道articles.size()总的数据量是多少


    // 3. SearchNewsViewHolder:
    // TODO
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }
}

