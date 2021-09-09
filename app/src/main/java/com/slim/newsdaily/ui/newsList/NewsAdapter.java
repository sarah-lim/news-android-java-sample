package com.slim.newsdaily.ui.newsList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.slim.newsdaily.databinding.ViewHolderHeadlinesBinding;
import com.slim.newsdaily.network.models.Articles;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Articles> articlesList;

    private OnBottomReachedListener bottomReachedListener;

    private OnItemSelectedListener itemSelectedListener;

    public NewsAdapter(OnBottomReachedListener bottomReachedListener,
                       OnItemSelectedListener itemSelectedListener) {
        this.bottomReachedListener = bottomReachedListener;
        this.itemSelectedListener = itemSelectedListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderHeadlinesBinding binding = ViewHolderHeadlinesBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NewsAdapter.ViewHolder holder, int position) {
        Articles articles = articlesList.get(position);
        if(position == getItemCount() -1) {
            if (bottomReachedListener != null) {
                bottomReachedListener.onBottomReached();
            }
        }
        holder.bind(articles, itemSelectedListener);
    }

    @Override
    public int getItemCount() {
        if(articlesList == null) {
            return 0;
        }

        return articlesList.size();

    }

    public void setArticles(List<Articles> articles) {
        this.articlesList = articles;
        this.notifyDataSetChanged();
    }

    public void addArticles(List<Articles> articles) {
        this.articlesList.addAll(articles);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewHolderHeadlinesBinding binding;

        public ViewHolder(ViewHolderHeadlinesBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(Articles articles, OnItemSelectedListener itemSelectedListener) {
            binding.setArticle(articles);

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemSelectedListener != null) {
                        itemSelectedListener.onItemSelected(articles);
                    }
                }
            });
            binding.executePendingBindings();
        }
    }

    public interface OnBottomReachedListener {
        void onBottomReached();
    }

    public interface OnItemSelectedListener {
        void onItemSelected(Articles articles);
    }
}
