package com.example.uniassist;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uniassist.databinding.ActivityVitapUpdatesBinding;

public class VITApUpdatesActivity extends AppCompatActivity {
    ActivityVitapUpdatesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityVitapUpdatesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.hide();

        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl("https://www.vitap.ac.in/news");
        binding.webView.setWebViewClient(new WebViewController());
    }
}