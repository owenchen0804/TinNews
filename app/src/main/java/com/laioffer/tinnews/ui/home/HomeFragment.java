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
import com.laioffer.tinnews.databinding.FragmentHomeBinding;
import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.repository.NewsRepository;
import com.laioffer.tinnews.repository.NewsViewModelFactory;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.List;


public class HomeFragment extends Fragment implements CardStackListener {

    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;
    private CardStackLayoutManager layoutManager;
    private List<Article> articles;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

        //  Call the static inflate() method included in the generated binding class.
        //  This creates an instance of the binding class for the activity to use.
        //  Get a reference to the root view by either calling the getRoot() method or using Kotlin property syntax.
        //  Pass the root view to setContentView() to make it the active view on the screen.
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup CardStackView
        CardSwipeAdapter swipeAdapter = new CardSwipeAdapter();
        layoutManager = new CardStackLayoutManager(requireContext());
        layoutManager.setStackFrom(StackFrom.Top);
        binding.homeCardStackView.setLayoutManager(layoutManager);
        binding.homeCardStackView.setAdapter(swipeAdapter);

        // Handle like unlike button clicks
        // TODO


        NewsRepository repository = new NewsRepository();
        //  viewModel = new HomeViewModel(repository);  // HomeViewModel???depends on repository???
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository))
                .get(HomeViewModel.class);  // ?????????new HomeViewModel(repo)???????????????Home???????????????????????????
        //  ???????????????????????? Factory?????????????????????call???????????????HomeView?????????????????????map?????????????????????????????????????????????
        viewModel.setCountryInput("us");    // Input
        viewModel
                .getTopHeadlines()  //  Output
                .observe(
                        getViewLifecycleOwner(),    //  Stream???????????????????????????????????????????????????????????????LifecycleOwner?????????????????????Observe
                        //  ?????????fragment???????????????????????????GC
                        newsResponse -> {
                            if (newsResponse != null) {
                                //  Log.d("HomeFragment", newsResponse.toString());
                                articles = newsResponse.articles;
                                swipeAdapter.setArticles(articles);
                            }
                        });
    }

}