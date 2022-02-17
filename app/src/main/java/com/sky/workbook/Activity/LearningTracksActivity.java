package com.sky.workbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.sky.workbook.Adapter.LearningAdapter;
import com.sky.workbook.Adapter.LearningSearchAdapter;
import com.sky.workbook.R;

import java.util.ArrayList;
import java.util.List;

public class LearningTracksActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbar;
    private RecyclerView learning_track_recycler;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_tracks);

        spinner = (Spinner)findViewById(R.id.spinner);
        toolbar=(Toolbar)findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.drawer_learning));

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Spinner click listener
        spinner.setOnItemSelectedListener(LearningTracksActivity.this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Software Developer");
        categories.add("Java Developer");
        categories.add("Data Scientist");
        categories.add("Web Developer");
        categories.add("Python Web Developer");
        categories.add("App Developer");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        // LEARNING TRACK RECYCLER
        learning_track_recycler = findViewById(R.id.learning_track_recycler);
        learning_track_recycler.setHasFixedSize(true);

        learning_track_recycler.setLayoutManager(new LinearLayoutManager(LearningTracksActivity.this, LinearLayoutManager.VERTICAL, false));
        learning_track_recycler.setNestedScrollingEnabled(false);

        ArrayList<String> itemsData1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            itemsData1.add( "Course " + i);
        }

        LearningSearchAdapter learningSearchAdapter = new LearningSearchAdapter(itemsData1);
        learning_track_recycler.setAdapter(learningSearchAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}