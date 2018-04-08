package com.google.vchat;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by ABC on 3/18/2018.
 */

public class GroupIdentifier {

    private Firebase mRef;

    public String value;

    private FirebaseAuth mFirebaseAuth;

    String group (){

        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

        mRef = new Firebase("https://v-chat-d9f68.firebaseio.com/AJ");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                value = dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        return value;
    }
}
