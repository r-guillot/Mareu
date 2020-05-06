package com.guillot.mareu.controler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guillot.mareu.R;

import com.guillot.mareu.databinding.MeetingItemBinding;
import com.guillot.mareu.event.DeleteEvent;
import com.guillot.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;


import java.util.List;
import java.util.Random;


public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.MyViewHolder>{

    private List<Meeting> mMeetings;
    private Random mRandom = new Random(System.currentTimeMillis());
    private Context context;

    public MyMeetingRecyclerViewAdapter(List<Meeting> meetings, Context context) {
        mMeetings = meetings;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        MeetingItemBinding binding;

        public MyViewHolder(@NonNull MeetingItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new MyViewHolder(MeetingItemBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Meeting currentMeeting = mMeetings.get(position);

        final String meetingInfo = currentMeeting.getPlace() + " - " + (currentMeeting.getHour()) + " - " + currentMeeting.getTopic();
        holder.binding.textMeetingInfo.setText(meetingInfo);
        holder.binding.textMeetingParticipant.setText((currentMeeting.getParticipant()).trim().replace( " ", ", "));
        holder.binding.imageMeeting.setColorFilter(generateRandomColor());

        holder.binding.trashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(currentMeeting);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    //generate random color for the meetings circle image
    private int generateRandomColor() {
        // This is the base color which will be mixed with the generated one
        final int baseColor = Color.WHITE;

        final int baseRed = Color.red(baseColor);
        final int baseGreen = Color.green(baseColor);
        final int baseBlue = Color.blue(baseColor);

        final int red = (baseRed + mRandom.nextInt(256)) / 2;
        final int green = (baseGreen + mRandom.nextInt(256)) / 2;
        final int blue = (baseBlue + mRandom.nextInt(256)) / 2;

        return Color.rgb(red, green, blue);
    }

    //Alert dialog for the delete feature
    public void deleteDialog(final Meeting currentMeeting) {
        AlertDialog alertDialog = new AlertDialog.Builder(this.context).create();
        alertDialog.setTitle(R.string.alert);
        alertDialog.setMessage("Êtes-vous sûr de vouloir supprimer cette réunion ?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EventBus.getDefault().post(new DeleteEvent(currentMeeting));
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Non",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}