package com.example.hitorigoto.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.hitorigoto.Fragments.AboutFragment;
import com.example.hitorigoto.Fragments.CourseFragment;
import com.example.hitorigoto.Fragments.HomeFragment;
import com.example.hitorigoto.Fragments.ProfileFragment;
import com.example.hitorigoto.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load HomeFragment by default
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();

        // Setup bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                loadFragment(new HomeFragment());
                return true;
            } else if (itemId == R.id.course) {
                loadFragment(new CourseFragment());
                return true;
            } else if (itemId == R.id.about) {
                loadFragment(new AboutFragment());
                return true;
            } else if (itemId == R.id.profile) {
                loadFragment(new ProfileFragment());
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment instanceof HomeFragment) {
            finish(); // Exit the app when on HomeFragment
        } else {
            super.onBackPressed(); // Allow normal back press behavior for other fragments
        }
    }

}


