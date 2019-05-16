package com.example.finaldailyplanner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddGoal extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd;
    private EditText editText2;
    private Spinner spinnerg;
    private EditText editText3;

    EditText date;
    DatePickerDialog datePickerDialog;
    Date dateObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        // initiate the date picker and a button
        date = findViewById(R.id.date);
        spinnerg = findViewById(R.id.spinnerg);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddGoal.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        btnAdd = findViewById(R.id.btnAdd);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String spinnervalue = spinnerg.getSelectedItem().toString();
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                date = findViewById(R.id.date);
                final Date dateObject;
                String tempDate = "";

                try{
                    String dob_var=(date.getText().toString());
                    dateObject = formatter.parse(dob_var);
                    tempDate = new SimpleDateFormat("dd/MM/yyyy").format(dateObject);

                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                final String stringDate = tempDate;
                String name = editText2.getText().toString();
                String description = editText3.getText().toString();
                if (editText2.length() != 0 && spinnervalue.length() != 0 && stringDate.length() != 0 && description.length() != 0) {
                    boolean insertData = mDatabaseHelper.addData(name, spinnervalue, stringDate, description);

                    if (insertData) {
                        toastMessage("Saved!");
                        startActivity(new Intent(AddGoal.this, LongTermGoals.class));
                    } else {
                        toastMessage("Something went wrong");
                    }
                    editText2.setText("");
                    editText3.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }

        });
    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_screen_orientation);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_add_goal);
        }
    }


}
