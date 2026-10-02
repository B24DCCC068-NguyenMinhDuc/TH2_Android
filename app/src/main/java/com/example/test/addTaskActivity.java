package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addTaskActivity extends AppCompatActivity {
    EditText edtTaskName, edtDeadline, edtNote;
    Button btnSaveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_activity);

        edtTaskName = findViewById(R.id.edtTaskName);
        edtDeadline = findViewById(R.id.edtDeadline);
        edtNote = findViewById(R.id.edtNote);
        btnSaveTask = findViewById(R.id.btnSaveTask);

        btnSaveTask.setOnClickListener(v -> {
            String taskName = edtTaskName.getText().toString().trim();
            if (taskName.isEmpty()) {
                Toast.makeText(addTaskActivity.this, "Vui lòng nhập tên công việc", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra("taskName", taskName);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
