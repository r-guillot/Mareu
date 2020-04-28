package com.guillot.mareu.controler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.guillot.mareu.R;
import com.guillot.mareu.databinding.ActivityMainBinding;
import com.guillot.mareu.fragments.MeetingFragment;
import com.guillot.mareu.model.Meeting;
import com.guillot.mareu.service.MeetingApiService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String filterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewBinding();
        setListeners();
    }


    @Override
    protected void onStart() {
        super.onStart();
        openFragment();
    }

    //Create the menu to filter
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sorting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_date:
                Toast.makeText(this, R.string.date_filter, Toast.LENGTH_SHORT).show();

                return true;
            case R.id.reu_a:
                Toast.makeText(this, R.string.place_filter+ R.string.reu_a, Toast.LENGTH_SHORT).show();
                filterText=item.getTitle().toString();
                return true;
            case R.id.reu_b:
                Toast.makeText(this, R.string.place_filter+ R.string.reu_b, Toast.LENGTH_SHORT).show();
                filterText=(String)item.getTitle().toString();
                return true;
            case R.id.reu_c:
                Toast.makeText(this, R.string.place_filter+ R.string.reu_c, Toast.LENGTH_SHORT).show();
                filterText=(String)item.getTitle().toString();
                return true;
            default:return super.onOptionsItemSelected(item);
        }

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

