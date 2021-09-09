package com.slim.newsdaily.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.slim.newsdaily.databinding.FragmentDetailsBinding;
import com.slim.newsdaily.network.models.Articles;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        Articles article = DetailsFragmentArgs.fromBundle(getArguments()).getArticle();
        binding.setArticle(article);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}