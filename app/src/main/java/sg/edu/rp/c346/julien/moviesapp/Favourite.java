package sg.edu.rp.c346.julien.moviesapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Favourite extends AppCompatActivity {
        ListView lvMovies;
        ArrayList<String> favouriteMov;
        Button addMovie;

    @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.favouritemovies);
            lvMovies = findViewById(R.id.movielistview);
            addMovie = findViewById(R.id.btnAdd);
            Intent receive = getIntent();
            String addedMovie = receive.getStringExtra("MovieName");
            favouriteMov.add(addedMovie);
            addMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent addmovie = new Intent(getApplicationContext(), AddMovie.class);
                    startActivity(addmovie);
                }
            });
        }
}
