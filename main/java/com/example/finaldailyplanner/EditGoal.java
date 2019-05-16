package com.example.finaldailyplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditGoal<imageButton> extends AppCompatActivity {

    Spinner dropdownmenu;
    private Button btnSave,btnDelete;
    private EditText edit_name;
    private EditText edit_date;
    private EditText edit_description;
    private EditText edit_type;

    DatabaseHelper mDatabaseHelper;

    private String selectedTask;
    private String selectedGoal;
    private int selectedID;
    private String selectedName;
    private String selectedType;
    private String selectedDate;
    private String selectedDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_goal);

        dropdownmenu = (Spinner) findViewById(R.id.spinner);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        edit_name = findViewById(R.id.editText);
        edit_date = findViewById(R.id.date);
        edit_description = findViewById(R.id.editText3);
        edit_type = findViewById(R.id.editText2);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");
        selectedType = receivedIntent.getStringExtra("type");
        selectedDate = receivedIntent.getStringExtra("date");
        selectedDescription = receivedIntent.getStringExtra("description");

        //set the text to show the current selected name
        edit_name.setText(selectedTask);
        edit_date.setText(selectedDate);
        edit_description.setText(selectedDescription);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = edit_name.getText().toString();
                final String spinnervalue = dropdownmenu.getSelectedItem().toString();
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                final Date dateObject;
                String tempDate = "";

                try{
                    String dob_var=(edit_date.getText().toString());
                    dateObject = formatter.parse(dob_var);
                    tempDate = new SimpleDateFormat("dd/MM/yyyy").format(dateObject);

                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                final String stringDate = tempDate;
                if(!item.equals("")){
                    mDatabaseHelper.updateGoal(selectedID, selectedTask, item, dropdownmenu.getSelectedItem().toString(), stringDate, edit_description.getText().toString());
                    startActivity(new Intent(EditGoal.this, MainActivity.class));
                }else{
                    toastMessage("You must enter a name");
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteGoal(selectedID,selectedName);
                edit_name.setText("");
                toastMessage("removed from database");
                startActivity(new Intent(EditGoal.this, LongTermGoals.class));
            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}