package com.example.uniassist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uniassist.databinding.ActivityTodoListBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import Model.ModelTodoList;

public class TodoListActivity extends AppCompatActivity {
    ActivityTodoListBinding binding;
    FirebaseDatabase database;
    private String uTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityTodoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        database = FirebaseDatabase.getInstance();
        binding.btnLetsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=binding.editTextText.getText().toString();
                if(text.isEmpty())
                {
                    Toast.makeText(TodoListActivity.this, "Please Enter Your Task", Toast.LENGTH_SHORT).show();
                }
                else {
                    insertData();
                }
            }
        });
        Intent intent = getIntent();
        if(intent != null) {
            String text = intent.getStringExtra("text");
            String key = intent.getStringExtra("key");
            int position = intent.getIntExtra("position", -1);
            if(text != null && position != -1 && key != null)
            {
                binding.editTextText.setText(text);
                binding.btnLetsGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateData(key, position);
                    }
                });
            }
        }
    }
    public void updateData(String key, int position) {
        String newText=binding.editTextText.getText().toString();
        ModelTodoList updateModel=new ModelTodoList(newText);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database.getReference().child("TodoList").child(userId).child(key)
                .setValue(updateModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent = new Intent(TodoListActivity.this, RecyclerView.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    public void insertData() {
        String text = binding.editTextText.getText().toString();
//        String id = database.getReference().push().getKey();
        ModelTodoList model = new ModelTodoList(text);
        //Changes
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        model.setKey(id);
        database.getReference().child("TodoList").child(userId).push()
                .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                    }
                });
        Intent intent = new Intent(TodoListActivity.this, RecyclerView.class);
        startActivity(intent);
//        finish();
    }
}