package com.example.uniassist;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.uniassist.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFragment extends Fragment {
    LinearLayout angry,sad,neutral,happy,excited;
    ImageView shown;
    TextInputLayout textInputLayout;
    Button feedback;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackFragment newInstance(String param1, String param2) {
        FeedbackFragment fragment = new FeedbackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed_back, container, false);
    }
    @SuppressLint("WrongViewCast")
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        angry=view.findViewById(R.id.angry);
        sad=view.findViewById(R.id.sad);
        neutral=view.findViewById(R.id.neutral);
        happy=view.findViewById(R.id.happy);
        excited=view.findViewById(R.id.excited);
        shown=view.findViewById(R.id.shown_feedback);

        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shown.setImageDrawable(getResources().getDrawable(R.drawable.angry));
                shown.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(),"Angry!",Toast.LENGTH_SHORT).show();
            }
        });
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shown.setImageDrawable(getResources().getDrawable(R.drawable.sad));
                shown.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(),"Sad!",Toast.LENGTH_SHORT).show();
            }
        });
        neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shown.setImageDrawable(getResources().getDrawable(R.drawable.neutral));
                shown.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(),"Neutral!",Toast.LENGTH_SHORT).show();
            }
        });
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shown.setImageDrawable(getResources().getDrawable(R.drawable.happy));
                shown.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(),"Happy!",Toast.LENGTH_SHORT).show();
            }
        });
        excited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shown.setImageDrawable(getResources().getDrawable(R.drawable.star));
                shown.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(),"Excited!",Toast.LENGTH_SHORT).show();
            }
        });
        textInputLayout=view.findViewById(R.id.textInputLayout);
        feedback=view.findViewById(R.id.buttonfeedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fb=textInputLayout.getEditText().getText().toString();
                if(fb.isEmpty())
                {
                    Toast.makeText(getContext(), "Please Give a FeedBack!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "FeedBack Sent!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}