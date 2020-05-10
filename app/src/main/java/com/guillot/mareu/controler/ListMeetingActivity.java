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
import com.guillot.mareu.databinding.ActivityListMeetingBinding;
import com.guillot.mareu.event.FilterDateEvent;
import com.guillot.mareu.event.FilterPlaceEvent;
import com.guillot.mareu.fragments.DatePickerFragment;
import com.guillot.mareu.fragments.MeetingFragment;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.util.Calendar;

public class ListMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ActivityListMeetingBinding binding;
    public String filterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        viewBinding();
        setListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        openFragment();
    }

    public void viewBinding() {
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    /**
     * Create the menu to filter
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filtering_menu, menu);
        return true;
    }

    /**
     * set menu option with filter
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_date:
                Toast.makeText(this, getString(R.string.date_filter), Toast.LENGTH_SHORT).show();
                dateClickListener();
                return true;

            case R.id.reu_a:
            case R.id.reu_b:
            case R.id.reu_c:
            case R.id.reu_d:
            case R.id.reu_e:
            case R.id.reu_f:
            case R.id.reu_g:
            case R.id.reu_h:
            case R.id.reu_i:
            case R.id.reu_j:
                applySelectedFilter(item, filterText);
                return true;

            case R.id.filter_reset:
                Toast.makeText(this, getString(R.string.filter_reset), Toast.LENGTH_SHORT).show();
                filterText = item.getTitle().toString();
                EventBus.getDefault().post(new FilterPlaceEvent(filterText));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void applySelectedFilter(MenuItem item, String filterText) {
        filterText = item.getTitle().toString();
        Toast.makeText(this, getString(R.string.place_filter, filterText), Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new FilterPlaceEvent(filterText));
    }

    /**
     * date picker for date Filter
     */
    public void dateClickListener() {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    /**
     * Event bus to pass date
     *
     * @param view
     * @param year
     * @param month
     * @param dayOfMonth
     */
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


    /**
     * add the fragment with recyclerview into the activity
     */
    public void openFragment() {
        MeetingFragment fragment = MeetingFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, fragment).commit();
    }

    /**
     * Set listener for the add floating action button
     */
    public void setListeners() {
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });
    }

    public void openAddActivity() {
        Intent addIntent = new Intent(this, AddMeetingActivity.class);
        startActivity(addIntent);
    }

}

