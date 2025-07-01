package com.example.uniassist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class CGPAActivity extends AppCompatActivity {

    LinearLayout inputContainer;
    MaterialButton addRowButton, calculateButton;
    TextView cgpaResult, remarksResult;

    final String[] grades = {"S", "A", "B", "C", "D", "E", "F"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpaactivity);

        inputContainer = findViewById(R.id.inputContainer);
        addRowButton = findViewById(R.id.addRowButton);
        calculateButton = findViewById(R.id.calculateButton);
        cgpaResult = findViewById(R.id.cgpaResult);
        remarksResult = findViewById(R.id.remarksResult);

        addRowButton.setOnClickListener(v -> addInputRow());

        calculateButton.setOnClickListener(v -> calculateCGPA());

        // Initial row
        addInputRow();
    }

    private void addInputRow() {
        View row = LayoutInflater.from(this).inflate(R.layout.row_credit_grade, inputContainer, false);
        AutoCompleteTextView gradeInput = row.findViewById(R.id.gradeInput);
        ImageButton removeButton = row.findViewById(R.id.removeRowButton);

        // Setup grade dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, grades);
        gradeInput.setAdapter(adapter);

        // Remove functionality
        removeButton.setOnClickListener(v -> inputContainer.removeView(row));

        inputContainer.addView(row);
    }

    private void calculateCGPA() {
        double totalGradePoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < inputContainer.getChildCount(); i++) {
            View row = inputContainer.getChildAt(i);
            EditText creditInput = row.findViewById(R.id.creditInput);
            AutoCompleteTextView gradeInput = row.findViewById(R.id.gradeInput);

            String creditStr = creditInput.getText().toString().trim();
            String gradeStr = gradeInput.getText().toString().trim().toUpperCase();

            if (creditStr.isEmpty() || gradeStr.isEmpty()) continue;

            int credit = Integer.parseInt(creditStr);
            int gradeValue = getGradeValue(gradeStr);

            if (gradeValue == -1) {
                Toast.makeText(this, "Invalid grade: " + gradeStr, Toast.LENGTH_SHORT).show();
                return;
            }

            totalCredits += credit;
            totalGradePoints += credit * gradeValue;
        }

        if (totalCredits == 0) {
            cgpaResult.setText("Please enter valid inputs.");
            return;
        }

        double cgpa = totalGradePoints / totalCredits;
        cgpaResult.setText("Your CGPA is: " + String.format("%.2f", cgpa));

        if (cgpa >= 8) {
            remarksResult.setText("🎉 Congratulations! Keep It Up");
        } else if (cgpa >= 5) {
            remarksResult.setText("⚠️ Focus On Improving Your Performance");
        } else {
            remarksResult.setText("📌 You Can Do Better Next Time");
        }
    }

    private int getGradeValue(String grade) {
        switch (grade) {
            case "S": return 10;
            case "A": return 9;
            case "B": return 8;
            case "C": return 7;
            case "D": return 6;
            case "E": return 5;
            case "F": return 0;
            default: return -1;
        }
    }
}
