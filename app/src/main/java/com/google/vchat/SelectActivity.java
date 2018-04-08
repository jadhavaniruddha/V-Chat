package com.google.vchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
//import com.pshkrh.pkchat.R;


public class SelectActivity extends AppCompatActivity {

    private static final String TAG = "SelectActivity";

    public static final String ANONYMOUS = "Anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN = 1;
    private static final int RC_PHOTO_PICKER =  2;

    private String mUsername;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private MessageAdapter mMessageAdapter;
    private ListView mMessageListView;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotosStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.google.vchat.R.layout.activity_select);
        mUsername = getIntent().getStringExtra("Username");

        Button chatBtn = (Button) findViewById(com.google.vchat.R.id.button2);
        final EditText edit = (EditText)findViewById(com.google.vchat.R.id.editText);



        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txt = edit.getText().toString();
                SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("key",txt);
                edit.apply();

                if(txt.matches("")){
                    Toast.makeText(SelectActivity.this, "Please enter a Group Code", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                    intent.putExtra("groupCode",txt);
                    intent.putExtra("Username", mUsername);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(com.google.vchat.R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case com.google.vchat.R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                Intent intent = new Intent(SelectActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}