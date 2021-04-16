package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.todolist.adapter.MyRecyclerViewAdapter;
import com.example.todolist.model.Prio;
import com.example.todolist.model.ToDoModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected RecyclerView taskRecyclerView;
    protected MyRecyclerViewAdapter adapter;
    private List<ToDoModel> taskList;
    public final static int IDENIFICATION_ADD =0;
    public ToDoModel taskNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        taskList = new ArrayList<>();
        //Intent intent = getIntent();
        //String message = intent.getStringExtra(EnterTaskActivity.EXTRA_MESSAGE);
        taskRecyclerView = findViewById(R.id.list);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this);
        taskRecyclerView.setAdapter(adapter);
        ToDoModel task = new ToDoModel();
        task.setTask("Réviser NF28");
        task.setStatus(0);
        task.setId(1);
        task.setPrio(Prio.HIGH);
        task.setDate(new Date());
        taskList.add(task);
        adapter.setTasks(taskList);
    }

    public void addTask(View view) {
        Intent intent = new Intent(MainActivity.this, EnterTaskActivity.class);
        Log.d("ss", "addTask: ");
        startActivityForResult(intent,IDENIFICATION_ADD);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IDENIFICATION_ADD) {

            if (resultCode == RESULT_OK) {
                taskNew = new ToDoModel();

                String d = data.getStringExtra(EnterTaskActivity.EXTRA_MESSAGE);
                Log.d("d1",d);
                taskNew.setTask(d.split(",")[0]);
                taskNew.setStatus(0);
                taskNew.setId(2);
                String ddOld = d.split(",")[1];
                Log.d("d1",ddOld);
                Log.d("d1",ddOld.split("/")[2]);
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, Integer.parseInt(ddOld.split("/")[2]));
                cal.set(Calendar.MONTH, Integer.parseInt(ddOld.split("/")[1]));
                cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ddOld.split("/")[0]));
                Date dd= cal.getTime();
                //Date dd = new Date(Integer.parseInt(ddOld.split("/")[2].substring(0,3)), Integer.parseInt(ddOld.split("/")[1]), Integer.parseInt(ddOld.split("/")[0]));
                //pb with Date constructor, use Calendar instead!!!
                taskNew.setDate(dd);
                Log.d("d1",dd.toString());
                Log.d("d1",d.split(",")[2]);
                switch (d.split(",")[2]) {
                    case "Low":
                        taskNew.setPrio(Prio.LOW);
                        break;
                    case "Medium":
                        taskNew.setPrio(Prio.MEDIUM);
                        break;
                    case "High":
                        taskNew.setPrio(Prio.HIGH);
                        break;
                }

                taskList.add(taskNew);
                for(ToDoModel t :taskList){
                    Log.d("1",t.getTask());
                }
                setContentView(R.layout.activity_main);
                taskRecyclerView = findViewById(R.id.list);
                taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new MyRecyclerViewAdapter(this);
                taskRecyclerView.setAdapter(adapter);
                adapter.setTasks(taskList);

            }
        }
    }


}