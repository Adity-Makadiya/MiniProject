package com.example.MiniProject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

// initiate view's
        ImageButton simpleImageButtonCountrylist = (ImageButton)findViewById(R.id.imageButton);
        ImageButton simpleImageButtonYourList = (ImageButton)findViewById(R.id.imageButton2);

// perform click event on button's
        simpleImageButtonCountrylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"CountryList",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeActivity.this, MyRecyclerView.class);
                startActivity(intent);
            }
        });
        simpleImageButtonYourList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Your List", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, MyRecyclerView2.class);
                startActivity(intent);
            }
        });
//        simpleImageButtonYourList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"YourList",Toast.LENGTH_LONG).show();
//                Intent intent1 = new Intent(HomeActivity.this, MyRecyclerView2.class);
//                startActivity(intent1);
//            }
//        });
    }
    public void backhome(View view){
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
