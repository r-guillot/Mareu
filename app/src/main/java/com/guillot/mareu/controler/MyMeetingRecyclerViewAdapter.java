package com.guillot.mareu.controler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guillot.mareu.R;

import com.guillot.mareu.databinding.MeetingItemBinding;
import com.guillot.mareu.fragments.MeetingFragment;
import com.guillot.mareu.model.Meeting;


import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.MyViewHolder>{

    private List<Meeting> mMeetings;
    private Random mRandom = new Random(System.currentTimeMillis());
    private  Context context;

    public MyMeetingRecyclerViewAdapter(List<Meeting> meetings, Context context) {
        mMeetings = meetings;
        this.context = context;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewInfo;
        public TextView mTextViewParticipant;
        public ImageView mImageView;
        public ImageButton mTrashButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewInfo = itemView.findViewById(R.id.text_meeting_info);
            mTextViewParticipant = itemView.findViewById(R.id.text_meeting_participant);
            mImageView = itemView.findViewById(R.id.image_meeting);
            mTrashButton = itemView.findViewById(R.id.trash_button);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_item, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Meeting currentMeeting = mMeetings.get(position);

        final String meetingInfo = currentMeeting.getPlace() + " - " + (new DecimalFormat("##0.00").format(currentMeeting.getHour()).replace(',', 'H').replace('.', 'H')) + " - " + currentMeeting.getTopic();
        holder.mTextViewInfo.setText(meetingInfo);
        holder.mTextViewParticipant.setText((currentMeeting.getParticipant()).replace( " ", ", "));
        holder.mImageView.setColorFilter(generateRandomColor());

        holder.mTrashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
//        mMeetings == null ? 0 :
    }

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

    public void deleteDialog(final int position) {
        AlertDialog alertDialog = new AlertDialog.Builder(this.context).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Êtes-vous sûr de vouloir supprimer cette réunion ?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItem(position);
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

    public void deleteItem(int position) {
        mMeetings.remove(position);
        notifyItemRemoved(position);
    }
}