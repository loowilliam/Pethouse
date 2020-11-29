package com.example.project.pethouse.activity;

import androidx.annotation.NonNull;

import com.example.project.pethouse.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.pethouse.fragment.AccountFragment;
import com.example.project.pethouse.fragment.HomeFragment;
import com.example.project.pethouse.fragment.OrderFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView BottomNav =findViewById(R.id.bottom_navigation);

        BottomNav.setOnNavigationItemSelectedListener(navListener);
        //this will set Home Fragment as default fragment to be displayed
        if(savedInstanceState == null){
            BottomNav.setSelectedItemId(R.id.nav_home);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = new HomeFragment();

            switch(item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_orders:
                    selectedFragment = new OrderFragment();
                    break;
                case R.id.nav_account:
                    selectedFragment = new AccountFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}
