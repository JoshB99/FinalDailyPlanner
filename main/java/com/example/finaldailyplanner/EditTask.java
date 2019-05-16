package com.example.finaldailyplanner;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditTask<imageButton> extends AppCompatActivity {

    Spinner dropdownmenu;
    private Button btnSave,btnDelete;
    private EditText edit_task;
    private Configuration newConfig;

    DatabaseHelper mDatabaseHelper;

    private String selectedTask;
    private String selectedGoal;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        dropdownmenu = (Spinner) findViewById(R.id.spinner);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        edit_task = findViewById(R.id.editText);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedTask = receivedIntent.getStringExtra("task");

        selectedGoal = receivedIntent.getStringExtra("linkedGoal");

        //set the text to show the current selected name
        edit_task.setText(selectedTask);

        List<String> list = new ArrayList<>();
        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        list.add("Item4");
        list.add("Item5");
        list.add("Item6");
        list.add("Item7");
        list.add("Item8");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dropdownmenu.setAdapter(adapter);
        int i = list.indexOf(selectedGoal);
        dropdownmenu.setSelection(i);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = edit_task.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateTask(item,selectedID,selectedTask, dropdownmenu.getSelectedItem().toString());
                    startActivity(new Intent(EditTask.this, MainActivity.class));
                }else{
                    toastMessage("You must enter a name");
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteTask(selectedID,selectedTask);
                edit_task.setText("");
                toastMessage("removed from database");
                startActivity(new Intent(EditTask.this, MainActivity.class));
            }
        });

    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}