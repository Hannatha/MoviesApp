package sg.edu.rp.c346.julien.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddMovie extends AppCompatActivity {
    Button btnSave;
    EditText etMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmovie);
        btnSave = findViewById(R.id.btnAdd);
        etMovie = findViewById(R.id.etAddMovie);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favmov = new Intent(AddMovie.this, Favourite.class);
                favmov.putExtra("MovieName", etMovie.getText().toString());
                startActivity(favmov);
            }
        });
    }
}
