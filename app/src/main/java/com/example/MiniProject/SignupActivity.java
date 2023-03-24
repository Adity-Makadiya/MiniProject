package com.example.MiniProject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MiniProject.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dbHelper = new DBHelper(SignupActivity.this);

        binding.signupSignupButton.setOnClickListener(view1 -> signupUser());
    }

    private void signupUser() {
        String email, userName, password;
        email = binding.signupEmailEditText.getText().toString();
        userName = binding.signupUserNameEditText.getText().toString();
        password = binding.signupPasswordEditText.getText().toString();

        if (!email.contains("@gmail.com"))
        {
                binding.signupEmailEditText.setError("Email is not valid");
        }
        else if (userName.isEmpty() || userName.length()<7)
        {
                binding.signupUserNameEditText.setError("Your username is not valid");
        }
        else if (password.isEmpty() || password.length()<7)
        {
                binding.signupPasswordEditText.setError("Password must be 7 character");
        }
        else
        {
            boolean flag = dbHelper.insertRecord(email, userName, password);

            if (flag)
            {
                Toast.makeText(this, "Signup Successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            } else
            {
                Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void signupback(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}