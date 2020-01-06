package com.biswash.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.biswash.taskmanager.api.TaskAPI;
import com.biswash.taskmanager.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddtaskActivity extends AppCompatActivity {
    Button btnaddNotes;
    EditText etNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);


        btnaddNotes = findViewById(R.id.btnaddNote);
        etNote = findViewById(R.id.etNote);

        btnaddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotes();
            }
        });
    }

    private void addNotes() {
        //Task notes = new Task(etNote.getText().toString());

        TaskAPI noteAPI = URL.getInstance().create(TaskAPI.class);

        Call<Void> voidCall = noteAPI.addTask(URL.token,etNote.getText().toString());

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddtaskActivity.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddtaskActivity.this, "Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddtaskActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
