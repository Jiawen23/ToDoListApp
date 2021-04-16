package com.example.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.model.ToDoModel;

import java.util.Date;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<ToDoModel>  todoList;
    private MainActivity activity;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(MainActivity activity) {
        this.activity = activity;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklayout, parent, false);

        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.date.setText(item.getDate());
        holder.prio2.setText(item.getPrio());
        holder.task.setChecked(toBoolean(item.getStatus()));
    }
    public int getItemCount(){
        return todoList.size();
    }

    private boolean toBoolean(int n){
        return n!=0;
    }
    public void setTasks(List<ToDoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }
    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder  {
        CheckBox task;
        TextView date;
        TextView date1;
        TextView prio1;
        TextView prio2;
        ViewHolder(View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.checkBox);
            date1 = itemView.findViewById(R.id.textView9);
            date = itemView.findViewById(R.id.textView8);
            prio1 = itemView.findViewById(R.id.textView6);
            prio2=itemView.findViewById(R.id.textView7);
        }

    }


}
