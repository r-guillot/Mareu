package com.guillot.mareu.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.guillot.mareu.injection.DI;
import com.guillot.mareu.service.MeetingApiService;
import com.guillot.mareu.R;
import com.guillot.mareu.databinding.ActivityAddBinding;
import com.guillot.mareu.fragments.DatePickerFragment;
import com.guillot.mareu.fragments.TimePickerFragment;
import com.guillot.mareu.model.Meeting;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;


public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {
    private ActivityAddBinding binding;
    public String textSpinnerMeeting;
    private MeetingApiService mApiService;
    private String emailResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mApiService = DI.getMeetingApiService();

        viewBinding();
        dateClickListener();
        hourClickListener();
        setSpinner();
        createChip();
        validation();

    }

    public void viewBinding() {
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    //open a dialogFragment with datePicker
    public void dateClickListener() {
        binding.buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    //set date to text view
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());

        binding.textviewDate.setText(currentDateString);
    }

    //open a dialogFragment with timePicker
    public void hourClickListener() {
        binding.buttonHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                Log.e("logee","timepicker"+timePicker);
            }
        });
    }

    //set time to text view
    @SuppressLint("StringFormatMatches")
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        binding.textviewHour.setText(getString(R.string.h, hourOfDay, minute));
        Log.e("logee", "heure"+hourOfDay );
        Log.e("logee","minute"+minute);
    }

    //set spinner with meetings rooms
    public void setSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.meeting_rooms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerMeeting.setAdapter(adapter);
        binding.spinnerMeeting.setOnItemSelectedListener(this);
    }

    //get meeting room from spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        textSpinnerMeeting = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //create chip for email address
    public void createChip() {
        binding.emailEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String email = textView.getText().toString().trim();
                    if (!email.isEmpty()) {
                        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            addChipToGroup(email, binding.chipGroup);
                            binding.emailEditText.setText("");
                        } else if (email.contains(" ")) {
                            binding.emailEditText.setError(getString(R.string.error_space));
                        }
                        else {
                            binding.emailEditText.setError(getString(R.string.error_format));
                        }
                    } else {
                        binding.emailEditText.setError(getString(R.string.error_empty));
                    }
                return true;
                }
                return false;
            }
        });
    }

    //add chip to chipGroup
    private void addChipToGroup(String text, final ChipGroup chipGroup) {
        final Chip chip = new Chip(this);
        chip.setChipDrawable(ChipDrawable.createFromResource(this,R.xml.chip));
        chip.setText(text);
        chipGroup.addView(chip);
        chip.isCloseIconVisible();
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chipGroup.removeView(chip);
            }
        });
    }

    //convert all chips in a string and get it
    public void getEmailFromChipGroup() {
        ChipGroup chipGroup = binding.chipGroup;

        for (int i = 0; i < chipGroup.getChildCount(); i++) {

            Chip chip = (Chip) chipGroup.getChildAt(i);
            emailResult = emailResult.trim()+" "+chip.getText().toString().trim();
        }
    }

    //create new meeting
    public void createMeeting() throws ParseException {
        Meeting meeting = new Meeting(
                binding.textviewDate.getText().toString(),
                binding.textviewHour.getText().toString(),
                textSpinnerMeeting,
                binding.textInputLayoutTopic.getEditText().getText().toString().trim(),
                emailResult
        );
        mApiService.createMeeting(meeting);
    }

    //check if all field are complete
    public boolean validateDate() {
        if (binding.textviewDate.getText().toString().isEmpty()) {
            binding.textviewDate.setError(getString(R.string.error_date));
            return false;
        } else {
            binding.textviewDate.setError(null);
            return true;
        }
    }

    public boolean validateTime() {
        if (binding.textviewHour.getText().toString().isEmpty()) {
            binding.textviewHour.setError(getString(R.string.error_hour));
            return false;
        } else {
            binding.textviewHour.setError(null);
            return true;
        }
    }

    public boolean validateTopic() {
        if (binding.textInputLayoutTopic.getEditText().toString().isEmpty()) {
            binding.textInputLayoutTopic.setError(getString(R.string.error_topic));
            return false;
        } else {
            binding.textInputLayoutTopic.setError(null);
            return true;
        }
    }

    public boolean validateEmail() {
        getEmailFromChipGroup();
        if (emailResult.isEmpty()) {
            binding.textInputLayoutEmail.setError(getString(R.string.error_participants));
            return false;
        } else {
            binding.textInputLayoutEmail.setError(null);
            return true;
        }
    }

    //if every fields are complete create new meeting and finish the activity if not error appears on screen
    public void validation() {
            binding.buttonValidation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!validateDate() | !validateTime() | !validateTopic() | !validateEmail()) {
                      return;
                    }
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


