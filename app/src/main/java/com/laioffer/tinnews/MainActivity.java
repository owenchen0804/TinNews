package com.laioffer.tinnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laioffer.tinnews.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view); //R is a resource and has a unique id
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

        //    binding = ActivityMainBinding.inflate(getLayoutInflater());
        //    setContentView(binding.getRoot());

        //    TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        //    EditText nameEditText = findViewById(R.id.emailEditText);
        //    EditText emailEditText = findViewById(R.id.nameEditText);
        //    Button submitButton = findViewById(R.id.submitButton);



}