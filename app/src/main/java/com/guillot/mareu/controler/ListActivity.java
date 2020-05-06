package com.guillot.mareu.controler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.guillot.mareu.R;
import com.guillot.mareu.databinding.ActivityMainBinding;
import com.guillot.mareu.event.FilterDateEvent;
import com.guillot.mareu.event.FilterPlaceEvent;
import com.guillot.mareu.fragments.DatePickerFragment;
import com.guillot.mareu.fragments.MeetingFragment;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.util.Calendar;

public class ListActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

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

    public void viewBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    //Create the menu to filter
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filtering_menu, menu);
        return true;
    }

    //set menu option with filter
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_date:
                Toast.makeText(this, getString(R.string.date_filter), Toast.LENGTH_SHORT).show();
                dateClickListener();
                return true;

            case R.id.reu_a:
                Toast.makeText(this, getString(R.string.place_filter, getString(R.string.reu_a)), Toast.LENGTH_SHORT).show();
                filterText = item.getTitle().toString();
                EventBus.getDefault().post(new FilterPlaceEvent(filterText));
                return true;
            case R.id.reu_b:
                Toast.makeText(this, getString(R.string.place_filter, getString(R.string.reu_b)), Toast.LENGTH_SHORT).show();
                filterText = item.getTitle().toString();
                EventBus.getDefault().post(new FilterPlaceEvent(filterText));
                return true;
            case R.id.reu_c:
                Toast.makeText(this, getString(R.string.place_filter, getString(R.string.reu_c)), Toast.LENGTH_SHORT).show();
                filterText = item.getTitle().toString();
                EventBus.getDefault().post(new FilterPlaceEvent(filterText));
                return true;
            case R.id.reu_d:
                Toast.makeText(this, getString(R.string.place_filter, getString(R.string.reu_d)), Toast.LENGTH_SHORT).show();
                filterText = item.getTitle().toString();
                EventBus.getDefault().post(new FilterPlaceEvent(filterText));
                return true;
            case R.id.reu_e:
                Toast.makeText(this, getString(R.string.place_filter, getString(R.string.reu_e)), Toast.LENGTH_SHORT).show();
                filterText = item.getTitle().toString();
                EventBus.getDefault().post(new FilterPlaceEvent(filterText));
                return true;
            case R.id.reu_f:
                Toast.makeText(this, getString(R.string.place_filter, getString(R.string.reu_f)), Toast.LENGTH_SHORT).show();
                filterText = item.getTitle().toString();
                EventBus.getDefault().post(new FilterPlaceEvent(filterText));
                return true;
            case R.id.reu_g:
                Toast.makeText(this, getString(R.string.place_filter, getString(R.string.reu_g)), Toast.LENGTH_SHORT).show();
                filterText = item.getTitle().toString();
                EventBus.getDefault().post(new FilterPlaceEvent(filterText));
                return true;
            case R.id.reu_h:
                Toast.makeText(this, getString(R.string.place_filter, getString(R.string.reu_h)), Toast.LENGTH_SHORT).show();
                filterText = item.getTitle().toString();
                EventBus.getDefault().post(new FilterPlaceEvent(filterText));
                return true;
            case R.id.reu_i:
                Toast.makeText(this, getString(R.string.place_filter, getString(R.string.reu_i)), Toast.LENGTH_SHORT).show();
                filterText = item.getTitle().toString();
                EventBus.getDefault().post(new FilterPlaceEvent(filterText));
                return true;
            case R.id.reu_j:
                Toast.makeText(this, getString(R.string.place_filter, getString(R.string.reu_j)), Toast.LENGTH_SHORT).show();
                filterText = item.getTitle().toString();
                EventBus.getDefault().post(new FilterPlaceEvent(filterText));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //date picker for date Filter
    public void dateClickListener() {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
    }

    //Event bus to pass date
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String filterDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());

        Toast.makeText(this, filterDate, Toast.LENGTH_SHORT).show();

        EventBus.getDefault().post(new FilterDateEvent(filterDate));
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
        Intent addIntent = new Intent(this, AddActivity.class);
        startActivity(addIntent);
    }

}

