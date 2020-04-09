package com.guillot.mareu.controler;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guillot.mareu.R;

import com.guillot.mareu.model.Meeting;


import java.text.DecimalFormat;
import java.util.List;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.MyViewHolder> {

    private final List<Meeting> mMeetings;

    public MyMeetingRecyclerViewAdapter(List<Meeting> meetings) {
        mMeetings = meetings;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewInfo;
        public TextView mTextViewParticipant;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewInfo = itemView.findViewById(R.id.text_meeting_info);
            mTextViewParticipant = itemView.findViewById(R.id.text_meeting_participant);

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meeting currentMeeting = mMeetings.get(position);

       String meetingInfo = (new DecimalFormat("##0.00").format(currentMeeting.getHour()).replace(',','H'))+ " - "+ currentMeeting.getPlace()+ " - "+ currentMeeting.getTopic();
       holder.mTextViewInfo.setText(meetingInfo);
       holder.mTextViewParticipant.setText(currentMeeting.getParticipant());
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

}
