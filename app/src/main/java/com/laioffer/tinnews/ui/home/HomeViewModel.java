package com.laioffer.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

public class HomeViewModel extends ViewModel {  // viewModel有很多built-in methods
    private final NewsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();   //  又定义了一个管道

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }

    // 下面2个methods是把HomeViewModel的作用分开了，一个是输入，一个是输出。

    public void setCountryInput(String country) {
        countryInput.setValue(country); //  管道输入了一个String: country 这个method只管管道输入
    }

    public LiveData<NewsResponse> getTopHeadlines() {
        return Transformations.switchMap(countryInput, country -> repository.getTopHeadlines(country));
        //  管道的另一头是这样一个输出，作用是如果countryInput有输出的话，那么把这个输出提供给后面这个
        //  repo.getTopHeadlines，这里是管道只管输出
    }

}
