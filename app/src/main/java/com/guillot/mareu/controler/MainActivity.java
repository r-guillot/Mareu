package com.guillot.mareu.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.guillot.mareu.R;
import com.guillot.mareu.databinding.ActivityMainBinding;
import com.guillot.mareu.fragments.MeetingFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewBinding();
        openFragment();
        setListeners();
    }

    //Create the menu for sorting
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sorting_menu, menu);
        return true;
    }

    public void viewBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    //add the fragment with recyclerview into the activity
    public void openFragment() {
        MeetingFragment fragment = MeetingFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, fragment).commit();
    }

    //Set listener for the add floating action button
    public void setListeners() {
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });
    }

    public void openAddActivity() {
        Intent addIntent = new Intent (this, AddActivity.class);
        startActivity(addIntent);
    }




}

