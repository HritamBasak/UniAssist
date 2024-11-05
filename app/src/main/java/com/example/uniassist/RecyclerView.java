package com.example.uniassist;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;import com.example.uniassist.databinding.ActivityRecyclerViewBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterTodoList;
import Adapter.AdapterTodoList;
import Model.ModelTodoList;

public class RecyclerView extends AppCompatActivity {
    ActivityRecyclerViewBinding binding;
    AdapterTodoList adapter;
    List<ModelTodoList> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;});

        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        todoList = new ArrayList<>();
        adapter = new AdapterTodoList(todoList, this);
        binding.rv.setAdapter(adapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelper(adapter));
        touchHelper.attachToRecyclerView(binding.rv);

        FirebaseDatabase.getInstance().getReference().child("TodoList")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        todoList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            ModelTodoList model = dataSnapshot.getValue(ModelTodoList.class);
                            if (model != null) {
                                model.setKey(dataSnapshot.getKey()); // Set the key here
                                todoList.add(model);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {// Handle errors
                    }
                });
    }
}