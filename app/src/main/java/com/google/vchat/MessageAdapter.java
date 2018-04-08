package com.google.vchat;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {
    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects) {
        super(context, resource, objects);
    }

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private DatabaseReference mDatabase;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(com.google.vchat.R.layout.item_message, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(com.google.vchat.R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(com.google.vchat.R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(com.google.vchat.R.id.nameTextView);

        FriendlyMessage message = getItem(position);
        mFirebaseAuth = FirebaseAuth.getInstance();
        boolean isPhoto = message.getPhotoUrl() != null;

       // GroupIdentifier groupIdentifier = new GroupIdentifier();

        //value = groupIdentifier.group();

        //FirebaseUser user = mFirebaseAuth.getCurrentUser();
        //String uname = user.getDisplayName();

        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();


        MainActivity mainActivity = new MainActivity();


        /*mDatabase = FirebaseDatabase.getInstance().getReference().child("VD");
        mDatabase.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });*/

        if (mainActivity.value.equals("faculty")) {
        //if (firebaseUser.getDisplayName().equals("VD")) {
            if (message.getName().equals(firebaseUser.getDisplayName())) {
                if (isPhoto) {
                    messageTextView.setVisibility(View.GONE);
                    photoImageView.setVisibility(View.VISIBLE);
                    Glide.with(photoImageView.getContext())
                            .load(message.getPhotoUrl())
                            .into(photoImageView);
                } else {
                    messageTextView.setVisibility(View.VISIBLE);
                    photoImageView.setVisibility(View.GONE);
                    messageTextView.setText(message.getText());
                }
                authorTextView.setText(message.getName());
            }
        } else {
            if (isPhoto) {
                messageTextView.setVisibility(View.GONE);
                photoImageView.setVisibility(View.VISIBLE);
                Glide.with(photoImageView.getContext())
                        .load(message.getPhotoUrl())
                        .into(photoImageView);
            } else {
                messageTextView.setVisibility(View.VISIBLE);
                photoImageView.setVisibility(View.GONE);
                messageTextView.setText(message.getText());
            }
            authorTextView.setText(message.getName());
        }
        return convertView;
    }


}

