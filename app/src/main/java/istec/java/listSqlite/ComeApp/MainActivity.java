package istec.java.listSqlite.ComeApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import istec.java.listSqlite.BaseClass;
import istec.java.listSqlite.R;
import istec.java.listSqlite.home.HomeActivity;

public class MainActivity extends BaseClass {

    private TextView goToRegister;
    private Button goToHome;
    private EditText emailLogin;
    private android.widget.EditText passwordlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToRegister = findViewById(R.id.textView_register);
        goToHome = findViewById(R.id.button_login);
        emailLogin = findViewById(R.id.editTextTextEmailAddress);
        passwordlogin = findViewById(R.id.editTextTextPassword);

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFinish(MainActivity.this, RegisterActivity.class);
            }
        });

        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailLogin.getText().toString();
                String password = passwordlogin.getText().toString();
                loginUser(email, password);

            }
        });
    }

    private void loginUser(String email, String password) {

        SharedPreferences sharePrefs = getSharedPreferences("userData", MODE_PRIVATE);
        String savedEmail = sharePrefs.getString("email", "");
        String savedPassword = sharePrefs.getString("password", "");

        if (email.equals(savedEmail) && password.equals(savedPassword)) {
            goToFinish(MainActivity.this, HomeActivity.class);

        } else {
            toast(MainActivity.this, "Algo de errado aconteceu!");
        }
    }
}