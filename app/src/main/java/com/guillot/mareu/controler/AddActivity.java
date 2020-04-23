package com.guillot.mareu.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.guillot.mareu.service.MeetingApiService;
import com.guillot.mareu.service.Meeting_List;
import com.guillot.mareu.R;
import com.guillot.mareu.databinding.ActivityAddBinding;
import com.guillot.mareu.fragments.DatePickerFragment;
import com.guillot.mareu.fragments.TimePickerFragment;
import com.guillot.mareu.model.Meeting;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {
    private ActivityAddBinding binding;
    public String textSpinnerMeeting;
    private MeetingApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        viewBinding();
        dateClickListener();
        hourClickListener();
        setSpinner();
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

        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());

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

    public void setSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.meeting_rooms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerMeeting.setAdapter(adapter);
        binding.spinnerMeeting.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        textSpinnerMeeting = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    public void createMeeting() throws ParseException {
        String date = binding.textviewDate.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.FRANCE);

        Meeting meeting = new Meeting(
                sdf.parse(date),
            Double.parseDouble(binding.textviewHour.getText().toString()),
            textSpinnerMeeting,
            binding.textInputLayoutTopic.getEditText().getText().toString(),
            binding.textInputLayoutEmail.getEditText().getText().toString()
        );
        mApiService.createMeeting(meeting);
    }

    public void validation () {
        binding.buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createMeeting();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                finish();

            }
        });
    }

}
