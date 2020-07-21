package istec.java.listSqlite.ComeApp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import istec.java.listSqlite.BaseClass;
import istec.java.listSqlite.R;

public class RegisterActivity extends BaseClass {


    private Button goToHomeRegister;
    private EditText emailRegister;
    private EditText passwordRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        goToHomeRegister = findViewById(R.id.button_register);
        emailRegister = findViewById(R.id.editTextEmailAddress_register);
        passwordRegister = findViewById(R.id.editTextPassword_register);


        goToHomeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailRegister.getText().toString();
                String password = passwordRegister.getText().toString();
                registerUser(email, password);


            }
        });
    }

    private void registerUser(String email, String password) {
        SharedPreferences sharedPrefs = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString("email", email);
        editor.putString("password", password);

        editor.apply();

        goToFinish(RegisterActivity.this, MainActivity.class);
    }
}