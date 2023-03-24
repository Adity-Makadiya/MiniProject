package com.example.MiniProject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.MiniProject.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dbHelper = new DBHelper(LoginActivity.this);

        binding.loginLoginButton.setOnClickListener(view1 -> loginUser());
    }

    private void loginUser() {
        String userName, password;
        userName = binding.loginUserNameEditText.getText().toString();
        password = binding.loginPasswordEditText.getText().toString();

        if (userName.isEmpty() || userName.length()<7)
        {
            binding.loginUserNameEditText.setError("Your username is not valid");
        }
        else if (password.isEmpty() || password.length()<7)
        {
            binding.loginPasswordEditText.setError("Password must be 7 character");
        } else {
            boolean flag = dbHelper.getRecord(userName, password);
            if (flag) {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Record not found..", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void signupback(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
