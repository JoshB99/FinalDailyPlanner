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
import java.util.ArrayList;
import java.util.List;

public class AddTask<imageButton> extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd;
    private EditText editText;
    Spinner dropdownmenu;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        dropdownmenu = (Spinner) findViewById(R.id.spinner);
        btnAdd = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        mDatabaseHelper = new DatabaseHelper(this);

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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                String spinnerValue = dropdownmenu.getSelectedItem().toString();
                if (editText.length() != 0 && spinnerValue.length() != 0) {
                    boolean insertData = mDatabaseHelper.addData(newEntry, spinnerValue);

                    if (insertData) {
                        toastMessage("Saved!");
                        startActivity(new Intent(AddTask.this, MainActivity.class));
                    } else {
                        toastMessage("Something went wrong");
                    }
                    editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
