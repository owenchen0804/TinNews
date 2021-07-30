package com.laioffer.tinnews.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.repository.NewsRepository;
import com.laioffer.tinnews.repository.NewsViewModelFactory;


public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NewsRepository repository = new NewsRepository();
        //  viewModel = new HomeViewModel(repository);  // HomeViewModel是depends on repository的
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository))
                .get(HomeViewModel.class);
        viewModel.setCountryInput("us");    // Input
        viewModel
                .getTopHeadlines()  //  Output
                .observe(
                        getViewLifecycleOwner(),    //  Stream是动态接收数据，随时可以塞入数据，如果没有LifecycleOwner的话会导致一直Observe
                        //  这样当fragment被关掉的时候还无法GC
                        newsResponse -> {
                            if (newsResponse != null) {
                                Log.d("HomeFragment", newsResponse.toString());
                            }
                        });
    }

}