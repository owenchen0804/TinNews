package com.laioffer.tinnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view); //R is a resource and has a unique id
        //  先根据id找到bottomNavigationView 也就是最下面三个按钮
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);  // 同时还需要找到nav_host_fragment (找fragment需要这么做)
        navController = navHostFragment.getNavController(); // navController一定要从navHostFragment里面拿
        //  因为知道这些页面的互相跳转等关系info的就只有NavHostFragment
        NavigationUI.setupWithNavController(navView, navController);    //  实现了点击选择bottom的某个navView，然后跳转的功能
        NavigationUI.setupActionBarWithNavController(this, navController);
        //  这一行实现了header上面可以跟着页面切换改变显示，比如home, search, save等


        // fetch data from back ending using Retrofit
        NewsApi api = RetrofitClient.newInstance().create(NewsApi.class);
        api.getTopHeadlines("US").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("getTopHeadlines", response.body().toString());
                }
                else {
                    Log.d("getTopHeadlines", response.toString());
                }
            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d("getTopHeadlines", t.toString());

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }   // 作用是action bar上面的back后退的功能可以实现always回到Home view

        //    binding = ActivityMainBinding.inflate(getLayoutInflater());
        //    setContentView(binding.getRoot());

        //    TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        //    EditText nameEditText = findViewById(R.id.emailEditText);
        //    EditText emailEditText = findViewById(R.id.nameEditText);
        //    Button submitButton = findViewById(R.id.submitButton);



}