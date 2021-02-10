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

public class Login extends AppCompatActivity {
    TextInputEditText tietUsername, tietPassword;
    Button btnLogin;
    TextView tvSignup;
    ProgressBar pgb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tietUsername = findViewById(R.id.usernameLogin);
        tietPassword = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignup = findViewById(R.id.signUpText);
        pgb = findViewById(R.id.progress);
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password;
                username = String.valueOf(tietUsername.getText());
                password = String.valueOf(tietPassword.getText());

                if(!username.equals("") && !password.equals("")){
                    pgb.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(() -> {
                        String[] field = new String[2];
                        field[0] = "username";
                        field[1] = "password";
                        String[] data = new String[2];
                        data[0] = username;
                        data[1] = password;
                        PutData putData = new PutData("http://192.168.10.148/LoginRegister/login.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                pgb.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if(result.equals("Login Success")){
                                    Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                                    Name.name = username;
                                    startActivity(intentMain);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
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