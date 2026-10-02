package com.example.test;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class todoActivity extends AppCompatActivity {
    Button btnAdd;
    LinearLayout layoutTodoList;
    ActivityResultLauncher<Intent> addTaskLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_activity);

        btnAdd = findViewById(R.id.btnAdd);
        layoutTodoList = findViewById(R.id.layoutTodoList);

        // Cài đặt hiệu ứng gạch ngang và mờ cho các CheckBox có sẵn
        CheckBox chkTodo1 = findViewById(R.id.chkTodo1);
        CheckBox chkTodo2 = findViewById(R.id.chkTodo2);
        CheckBox chkTodo3 = findViewById(R.id.chkTodo3);

        if (chkTodo1 != null) setupTaskCheckBox(chkTodo1);
        if (chkTodo2 != null) setupTaskCheckBox(chkTodo2);
        if (chkTodo3 != null) setupTaskCheckBox(chkTodo3);

        addTaskLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String taskName = result.getData().getStringExtra("taskName");
                        if (taskName != null && !taskName.trim().isEmpty()) {
                            addTodoCheckBox(taskName);
                        }
                    }
                }
        );

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(todoActivity.this, addTaskActivity.class);
            addTaskLauncher.launch(intent);
        });
    }

    private void addTodoCheckBox(String taskName) {
        CheckBox checkBox = new CheckBox(this);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        checkBox.setText(taskName);
        setupTaskCheckBox(checkBox);
        layoutTodoList.addView(checkBox);
    }

    private void setupTaskCheckBox(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                checkBox.setAlpha(0.5f);
            } else {
                checkBox.setPaintFlags(checkBox.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                checkBox.setAlpha(1.0f);
            }
        });
    }
}
