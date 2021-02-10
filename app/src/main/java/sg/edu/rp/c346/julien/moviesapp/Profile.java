package sg.edu.rp.c346.julien.moviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    Button btnFav, btnFriends;
    TextView tvProfileName, tvMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        btnFriends = findViewById(R.id.Friends);
        btnFav = findViewById(R.id.favouriteMovieid);
        tvProfileName = findViewById(R.id.ProfileName);
        tvProfileName.setText(Name.name);

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favouriteIntent = new Intent(Profile.this, Favourite.class);
                startActivity(favouriteIntent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                startActivity(new Intent(getApplicationContext(),Settings.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}