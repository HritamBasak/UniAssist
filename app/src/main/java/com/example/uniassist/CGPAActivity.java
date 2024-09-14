package com.example.uniassist;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uniassist.databinding.ActivityCgpaactivityBinding;

public class CGPAActivity extends AppCompatActivity {
    ActivityCgpaactivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCgpaactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String credit1 = binding.credit1.getText().toString();
                String grade1 = binding.grade1.getText().toString();
                String credit2 = binding.credit2.getText().toString();
                String grade2 = binding.grade2.getText().toString();
                String credit3 = binding.credit3.getText().toString();
                String grade3 = binding.grade3.getText().toString();
                String credit4 = binding.credit4.getText().toString();
                String grade4 = binding.grade4.getText().toString();
                String credit5 = binding.credit5.getText().toString();
                String grade5 = binding.grade5.getText().toString();
                String credit6 = binding.credit6.getText().toString();
                String grade6 = binding.grade6.getText().toString();
                String credit7 = binding.credit7.getText().toString();
                String grade7 = binding.grade7.getText().toString();
                String credit8 = binding.credit8.getText().toString();
                String grade8 = binding.grade8.getText().toString();
                String credit9 = binding.credit9.getText().toString();
                String grade9 = binding.grade9.getText().toString();

                // ... get other credit and grade values ...

                // Parsing the credit from string to integer
                int c1 = credit1.equals("") ? 0 : Integer.parseInt(credit1);
                int c2 = credit2.equals("") ? 0 : Integer.parseInt(credit2);
                int c3 = credit3.equals("") ? 0 : Integer.parseInt(credit3);
                int c4 = credit4.equals("") ? 0 : Integer.parseInt(credit4);
                int c5 = credit5.equals("") ? 0 : Integer.parseInt(credit5);
                int c6 = credit6.equals("") ? 0 : Integer.parseInt(credit6);
                int c7 = credit7.equals("") ? 0 : Integer.parseInt(credit7);
                int c8 = credit8.equals("") ? 0 : Integer.parseInt(credit8);
                int c9 = credit9.equals("") ? 0 : Integer.parseInt(credit9);
                // ... parse other credit values ...

                int g1 = getGradeValue(grade1);
                int g2 = getGradeValue(grade2);
                int g3 = getGradeValue(grade3);
                int g4 = getGradeValue(grade4);
                int g5 = getGradeValue(grade5);
                int g6 = getGradeValue(grade6);
                int g7 = getGradeValue(grade7);
                int g8 = getGradeValue(grade8);
                int g9 = getGradeValue(grade9);

                // ... get other grade values using getGradeValue() ...

                double cgpa1 = g1 * c1;
                double cgpa2 = g2 * c2;
                double cgpa3 = g3 * c3;
                double cgpa4 = g4 * c4;
                double cgpa5 = g5 * c5;
                double cgpa6 = g6 * c6;
                double cgpa7 = g7 * c7;
                double cgpa8 = g8 * c8;
                double cgpa9 = g9 * c9;
                // ... calculate other cgpa values ...

                double res = cgpa1 + cgpa2 + cgpa3 + cgpa4 + cgpa5 + cgpa6 + cgpa7 + cgpa8 + cgpa9;
                int totalCredits = c1 + c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9;
                double finalRes = 0;

                if (totalCredits > 0) {
                    finalRes = res / totalCredits;
                    String str=String.valueOf(finalRes);
                    binding.cgpashow.setText("Your CGPA is: "+String.format("%.2f", finalRes));

                    // Set remarks based on finalRes
                    if (finalRes >= 8)
                        binding.remarksshow.setText("Congratulations! Keep It Up");
                    else if (finalRes >= 5)
                        binding.remarksshow.setText("Focus On Improving Your Performance");
                    else
                        binding.remarksshow.setText("You Can Do Better Next Time");}
                else {
                    binding.cgpashow.setText("Please enter credits");
                }
            }
        });

// Helper function to get grade value
    }
    private int getGradeValue(String grade) {
        switch (grade) {
            case "S":
                return 10;
            case "s":
                return 10;
            case "A":
                return 9;
            case "a":
                return 9;
            case "B":
                return 8;
            case "b":
                return 8;
            case "C":
                return 7;
            case "c":
                return 7;
            case "D":
                return 6;
            case "d":
                return 6;
            case "E":
                return 5;
            case "e":
                return 5;
            default:
                return 0;
        }
    }
}