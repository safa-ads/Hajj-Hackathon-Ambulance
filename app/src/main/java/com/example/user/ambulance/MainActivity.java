package com.example.user.ambulance;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    /**
     * Notification params
     */
    private NotificationCompat.Builder builder;
    private NotificationManager NotificationManager;
    private RemoteViews remoteViews;
    private int notification_id;
    private Context notificationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationContext = this;
        NotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        //remoteViews = new RemoteViews(getPackageName(),R.layout.custom_notification);

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //sendNotification("Emergency case", "حاله إستغاثه جديده");
                Toast.makeText(MainActivity.this, "حاله إستغاثه جديده", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
    private void sendNotification(String title, String content) {



    }


}
