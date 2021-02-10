package sg.edu.rp.c346.julien.moviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    TextView tvView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnv = findViewById(R.id.bottom_navigation);
        bnv.setSelectedItemId(R.id.home);
        tvView = findViewById(R.id.textView);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://movie-database-imdb-alternative.p.rapidapi.com/?s=Avengers%20Endgame&page=1&r=json")
                .get()
                .addHeader("x-rapidapi-key", "7ec8518910mshb512279cf235fbap15baf5jsnff51b0a59182")
                .addHeader("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
                .build();

        try {
            Response response = client.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.home:
                        return true;

                    case R.id.booking:
                        startActivity(new Intent(getApplicationContext(),Booking.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(),Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }



}