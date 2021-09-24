package com.sib.healthcare.Adapter.Consultancy;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sib.healthcare.Activities.Consultancy.DoctorProfileActivity;
import com.sib.healthcare.DataModels.UserDataModel;
import com.sib.healthcare.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class TopDrListAdapter extends RecyclerView.Adapter {
    private List<UserDataModel> listData;

    public TopDrListAdapter(List<UserDataModel> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.top_dr_list_item, parent, false)) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.doctorNameId);
        TextView textView1= holder.itemView.findViewById(R.id.doctorDesId);
        CircleImageView imageView= holder.itemView.findViewById(R.id.topDrProfilePic);
        textView.setText(listData.get(position).getName());
        textView1.setText(listData.get(position).getDegrees());
        StorageReference storageReference=FirebaseStorage.getInstance().getReference(listData.get(position).getImage());
        //Glide.with(holder.itemView.getContext()).load(storageReference).into(imageView);
        storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(holder.itemView.getContext()).load(uri).into(imageView);
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), DoctorProfileActivity.class).putExtra("userDataModel",listData.get(holder.getAdapterPosition())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
