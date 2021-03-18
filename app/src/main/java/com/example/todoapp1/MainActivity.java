package com.example.todoapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] todos;
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView todoTextView;
    private Button nextButton;
    private Button previousButton;
    private int todoIndex;
    public final String TODO_INDEX = "com.example.todoapp.todo_index";
    public final static String TODO_EXTRA = "com.example.todoapp.extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            todoIndex = savedInstanceState.getInt(TODO_INDEX);
        }

        Log.d(TAG, "onCreate()");

        Resources res = getResources();
        todos = res.getStringArray(R.array.todos);
        Log.d(TAG, "" + todos.length);

        todoTextView = findViewById(R.id.todo_tv);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previous_button);

        todoTextView.setText(todos[todoIndex]);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoIndex = ++todoIndex % todos.length;
                todoTextView.setText(todos[todoIndex]);
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(todoIndex == 0){
                    todoIndex = todos.length - 1;
                    todoTextView.setText(todos[todoIndex]);
                }
                else{
                    todoIndex = (todoIndex - 1) % todos.length;
                    todoTextView.setText(todos[todoIndex]);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TODO_INDEX,todoIndex);
    }
}