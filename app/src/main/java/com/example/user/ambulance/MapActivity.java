package com.example.user.ambulance;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MapActivity extends AppCompatActivity {
    private Object[] pickUpCoordinates = {21.6168511, 39.15646259999994};
    private Object[] dropOffCoordinates = {21.629936733505197, 39.154574324853456};
    private String url;
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Set the on click method of the Book button
        goButton = (Button) findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Disable the button during the request
                goButton.setEnabled(false);
                Log.e("Button","Button disabled");
                //TOD0: open next activity
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        // Get dimensions of screen in dp
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int dpHeight = (int) ((displayMetrics.heightPixels) / displayMetrics.density);
        int dpWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);

        // Create url to get the required map
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("maps.googleapis.com")
                .appendPath("maps")
                .appendPath("api")
                .appendPath("staticmap")
                .appendQueryParameter("size",dpWidth+"x"+dpHeight)
                .appendQueryParameter("maptype","roadmap")
                .appendQueryParameter("scale","2");

        url = builder.build().toString();
        url += "&markers=color:red%7C" + coordinatesToString(pickUpCoordinates);
        url += "&markers=color:green%7C" + coordinatesToString(dropOffCoordinates);

        // Load pic of the map into the imageView
        Picasso.with(MapActivity.this)
                .load(url)
                .into(imageView);
    }
    public static String coordinatesToString(Object[] arr)
    {
        return arr[0] + "," + arr[1];
    }
}
