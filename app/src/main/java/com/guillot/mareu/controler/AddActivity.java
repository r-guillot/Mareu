package com.guillot.mareu.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.guillot.mareu.Meeting_List;
import com.guillot.mareu.R;
import com.guillot.mareu.databinding.ActivityAddBinding;
import com.guillot.mareu.fragments.DatePickerFragment;
import com.guillot.mareu.fragments.TimePickerFragment;
import com.guillot.mareu.model.Meeting;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;


public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        viewBinding();
        dateClickListener();
        hourClickListener();
        validation();
    }

    public void viewBinding() {
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void dateClickListener() {
        binding.buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        binding.textviewDate.setText(currentDateString);
    }

    public void hourClickListener() {
        binding.buttonHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        binding.textviewHour.setText(hourOfDay + "H"+ minute);
    }


    public void createMeeting() {
        Meeting meeting = new Meeting(
            binding.textviewDate.getText().toString(),
            Double.parseDouble(binding.textviewHour.getText().toString()),
            binding.textInputLayoutPlace.getEditText().getText().toString(),
            binding.textInputLayoutTopic.getEditText().getText().toString(),
            binding.textInputLayoutEmail.getEditText().getText().toString()
        );
        Meeting_List.mMeetingList.add(meeting);
        finish();
    }

    public void validation () {
        binding.buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMeeting();
            }
        });
    }
}
