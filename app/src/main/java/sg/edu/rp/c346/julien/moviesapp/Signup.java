package sg.edu.rp.c346.julien.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Signup extends AppCompatActivity {
    TextInputEditText tietFullname, tietUsername, tietPassword, tietEmail;
    Button btnSignup;
    TextView tvLogin;
    ProgressBar pgb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        tietFullname = findViewById(R.id.fullname);
        tietPassword = findViewById(R.id.password);
        tietUsername = findViewById(R.id.username);
        tietEmail = findViewById(R.id.email);
        btnSignup = findViewById(R.id.buttonSignUp);
        tvLogin = findViewById(R.id.loginText);
        pgb = findViewById(R.id.progress);


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname, username, password, email;
                fullname = tietFullname.getText().toString();
                username = tietUsername.getText().toString();
                password = tietPassword.getText().toString();
                email = tietEmail.getText().toString();

                if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")){
                    pgb.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            PutData putData = new PutData("http://localhost/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    pgb.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Successfully signed up!")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}