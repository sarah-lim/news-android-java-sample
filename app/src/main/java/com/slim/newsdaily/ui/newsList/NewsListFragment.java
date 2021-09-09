package com.slim.newsdaily.ui.newsList;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.slim.newsdaily.databinding.FragmentNewsListBinding;
import com.slim.newsdaily.network.models.Articles;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
public class NewsListFragment extends Fragment implements INewsListView,
        NewsAdapter.OnBottomReachedListener, NewsAdapter.OnItemSelectedListener {

    private FragmentNewsListBinding binding;

    private INewsListPresenter presenter;

    private NewsAdapter adapter;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentNewsListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initLayout();
    }

    private void initLayout() {
        adapter = new NewsAdapter(this, this);
        binding.fragmentNewsListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.fragmentNewsListRv.setAdapter(adapter);

        binding.fragmentNewsListSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        presenter = new NewsListPresenter(this);
        presenter.attach();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.refresh();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showList(List<Articles> articles) {
        adapter.setArticles(articles);
    }

    @Override
    public void addList(List<Articles> articles) {
        adapter.addArticles(articles);
    }

    @Override
    public void showEmptyView(boolean show) {
        if(show) {
            binding.fragmentNewsListEmpty.setVisibility(View.VISIBLE);
        } else {
            binding.fragmentNewsListEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(boolean show) {
        binding.fragmentNewsListSwiperefresh.setRefreshing(show);
    }

    @Override
    public void onBottomReached() {
        presenter.loadNextPage();
    }

    @Override
    public void onItemSelected(Articles articles) {
        NavHostFragment.findNavController(NewsListFragment.this).navigate(NewsListFragmentDirections.actionNewsFragmentToDetailsFragment(articles));
    }
}