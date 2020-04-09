package com.guillot.mareu.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.guillot.mareu.R;
import com.guillot.mareu.databinding.ActivityAddBinding;
import com.guillot.mareu.databinding.ActivityMainBinding;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        viewBinding();
    }

    public void viewBinding() {
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
