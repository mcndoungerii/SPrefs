package com.ndunga.sprefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.ndunga.sprefs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_ID = "message_prefs";
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);



        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = binding.editText.getText().toString().trim();

                if(!message.isEmpty()) {
                    saveData(message);
                    Snackbar.make(v,"Data Saved!",Snackbar.LENGTH_SHORT).show();
                }
                else {
                    Snackbar.make(v,"Enter a message",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        retrieveData();


    }

    private void saveData(String message) {

        SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);

        //Access the editor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //put a string in an editor
        editor.putString("message",message);

        //save data to disk
        editor.apply();

        System.out.println("Data Saved::::");





    }

    private void retrieveData() {

        SharedPreferences getSharedData = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);

        //access data
        String value = getSharedData.getString("message","Nothig added!!");

        binding.showText.setText(value);
        System.out.println("Data Retrieved::::");
    }
}