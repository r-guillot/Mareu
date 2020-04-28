package com.guillot.mareu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.guillot.mareu.R;
import com.guillot.mareu.databinding.FragmentChipBinding;
import com.guillot.mareu.databinding.FragmentRecyclerviewMeetingBinding;

public class ChipFragment extends Fragment {
    FragmentChipBinding binding;

    public static ChipFragment newInstance () {
        ChipFragment fragment = new ChipFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChipBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Context context = view.getContext();

        binding.etValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String txtVal = v.getText().toString();
                    if(!txtVal.isEmpty()) {
                        addChipToGroup(txtVal, binding.chipGroup2);
                        binding.etValue.setText("");
                    }
                    return true;
                }
                return false;
            }
        });
        return view;
    }


    private void addChipToGroup(String txt, final ChipGroup chipGroup) {
         Chip chip = new Chip(getContext());
         chip.setText(txt);
         chip.isCloseIconVisible();
         chip.setChipIconResource(R.color.red);

        chipGroup.addView(chip);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chipGroup.removeView(v);
            }
        });
        printChipsValue(chipGroup);
    }

    private void printChipsValue (ChipGroup chipGroup) {
        for (int i = 0; i<chipGroup.getChildCount(); i++) {
            ChipGroup chipObj = (ChipGroup) chipGroup.getChildAt(i);
        }
    }


}
