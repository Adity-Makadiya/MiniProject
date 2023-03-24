package com.example.MiniProject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyRecyclerView2 extends AppCompatActivity {
    EditText cname, about, update_cname, update_about;
    Button add, btn_update, btn_cancel;
    RecyclerView recyclerView;
    Adapter adapter;

    List<CountryData> list = new ArrayList<>();

    AlertDialog.Builder builder;

    AlertDialog dialog;

    String u_cname, u_about;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_2);

        cname = (EditText) findViewById(R.id.Cname);
        about = (EditText) findViewById(R.id.about);

        add = (Button) findViewById(R.id.btn_add);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(list);
        recyclerView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                u_cname = cname.getText().toString();
                u_about = about.getText().toString();

                CountryData countryData = new CountryData();

                countryData.setCname(u_cname);
                countryData.setAbout(u_about);

                list.add(countryData);
                adapter.notifyDataSetChanged();
                Toast.makeText(MyRecyclerView2.this, "User Add Success...", Toast.LENGTH_SHORT).show();

                cname.setText("");
                about.setText("");

            }
        });
        adapter.setOnItemClickListener(new ItemClickListener() {

            @Override
            public void OnItemClick(int position, CountryData countryData) {

                builder = new AlertDialog.Builder(MyRecyclerView2.this);
                builder.setTitle("Update country Info");
                builder.setCancelable(false);
                View view = LayoutInflater.from(MyRecyclerView2.this).inflate(R.layout.dialog_update, null, false);
                InitUpdateDialog(position, view);
                builder.setView(view);
                dialog = builder.create();
                dialog.show();
            }
        });


    }

    private void InitUpdateDialog(final int position, View view) {

        update_cname = view.findViewById(R.id.update_cname);
        update_about = view.findViewById(R.id.update_about);
        btn_update = view.findViewById(R.id.btn_update_Country);
        btn_cancel = view.findViewById(R.id.btn_update_cancel);

        update_cname.setText(u_cname);
        update_about.setText(u_about);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                u_cname = update_cname.getText().toString();
                u_about = update_about.getText().toString();

                CountryData countryData = new CountryData();

                countryData.setCname(u_cname);
                countryData.setAbout(u_about);

                adapter.UpdateData(position, countryData);
                Toast.makeText(MyRecyclerView2.this, "Country Updated..", Toast.LENGTH_SHORT).show();

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

        public void Reback  (View view) {
            Intent intent = new Intent(MyRecyclerView2.this, HomeActivity.class);
            startActivity(intent);
        }
}
