package com.example.uniassist.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.uniassist.R;
import com.example.uniassist.databinding.FragmentHomeBinding;

import java.util.ArrayList;

import Adapter.MyAdapter;
import Model.MyModel;

public class HomeFragment extends Fragment {
    SharedPreferences sharedPreferences;
    private FragmentHomeBinding binding;
    SharedPreferences.Editor editor;
    boolean nightMode;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences=getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        nightMode=sharedPreferences.getBoolean("nightMode",false);
        if(nightMode){
            binding.aSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        binding.aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", true);
                }
                nightMode = !nightMode; // Toggle the value of nightMode
                editor.apply();
            }
        });


        ArrayList<MyModel> list=new ArrayList<>();
        list.add(new MyModel(R.drawable.calcu,"CGPA CALCULATOR","Simplify your academic performance tracking with our intuitive CGPA Calculator. Designed to make your life easier, this tool allows you to calculate your Cumulative Grade Point Average (CGPA) effortlessly based on your course credits and grades"));
        list.add(new MyModel(R.drawable.newss,"VIT-AP NEWS","Stay informed and up-to-date with the latest news and announcements from VIT-AP University. The section brings you real-time information on everything happening at the university, ensuring you never miss important events or changes."));
        list.add(new MyModel(R.drawable.plane,"MAP TO TRAVEL ANYWHERE","Navigate anywhere with ease using our feature. Whether you’re exploring a new city, planning a road trip, or simply finding your way around campus, this powerful map tool has you covered."));
        list.add(new MyModel(R.drawable.info,"TRAVEL HELPER","Seamlessly plan your journey to VIT-AP University with our feature. Designed to make your arrival at VIT hassle-free, this tool provides all the information you need for a smooth travel experience."));
        list.add(new MyModel(R.drawable.task,"LIST YOUR WORKS","Stay organized and on top of your academic and personal tasks with our “To-Do List for Students” feature. Designed specifically for student life, this tool helps you manage your assignments, deadlines, and daily responsibilities efficiently."));
        list.add(new MyModel(R.drawable.team,"PLACEMENT UPDATES","Stay updated with the latest placement opportunities and career developments. Our placement updates provide real-time information on upcoming job fairs, internship openings, employer visits, and recruitment drives, ensuring you never miss out on key opportunities to advance your career"));
        MyAdapter adapter=new MyAdapter(list,getContext());
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(linearLayoutManager);


    return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}