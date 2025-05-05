package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAssignmentActivity extends AppCompatActivity {
    private EditText titleField, descriptionField, dueDateField;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_assignment);

        titleField = findViewById(R.id.assignmentTitle);
        descriptionField = findViewById(R.id.assignmentDescription);
        dueDateField = findViewById(R.id.assignmentDueDate);
        submitBtn = findViewById(R.id.submitAssignmentBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleField.getText().toString().trim();
                String description = descriptionField.getText().toString().trim();
                String dueDate = dueDateField.getText().toString().trim();

                if (title.isEmpty() || description.isEmpty() || dueDate.isEmpty()) {
                    Toast.makeText(CreateAssignmentActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Assignment assignment = new Assignment(0, title, description, dueDate);

                AppDatabase db = AppDatabase.getInstance(getApplicationContext());

                long insertedId = db.assignmentDao().insert(assignment);

                Toast.makeText(CreateAssignmentActivity.this,
                        "Assignment created with ID: " + insertedId,
                        Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, CreateAssignmentActivity.class);
    }
}
