package com.example.finaldailyplanner;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ListView lv;
    private ArrayList<Goal> modelArrayList;
    private CustomAdapter customAdapter;
    private  String[] descriptionlist = new String[]{"Lion", "Tiger", "Leopard", "Cat", "Cheetah", "Bear",
            "Mouse", "Dog", "Squirrel", "Snake", "Lizard", "Bird", "Rabbit", "Pig", "Cow", "Sheep"};
    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle nToggle;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);

        modelArrayList = getGoal(false);
        customAdapter = new CustomAdapter(this,modelArrayList);
        lv.setAdapter(customAdapter);

        nDrawerLayout = findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this, nDrawerLayout, R.string.open, R.string.closed);

        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddTask.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(nToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_account){
            Intent intent = new Intent(this, LongTermGoals.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private ArrayList<Goal> getGoal(boolean isSelect){
        ArrayList<Goal> list = new ArrayList<>();
        for(int i = 0; i < 16; i++){

            Goal model = new Goal();
            model.setSelected(isSelect);
            model.setDescription(descriptionlist[i]);
            list.add(model);
        }
        return list;
    }
}
