package com.example.uniassist.ui.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.uniassist.databinding.FragmentGalleryBinding;

import org.json.JSONObject;

public class WeatherFragment extends Fragment {

    private FragmentGalleryBinding binding;
//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;
//    boolean nightMode;
    String url="https://api.openweathermap.org/data/2.5/weather?q=bangalore&appid=0d9a972d0ef6d982113a5c13f2ba45d1";
    String api="e023e368b08962bdfbf8dabfacd46efa";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        sharedPreferences=getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
//        nightMode=sharedPreferences.getBoolean("nightMode",false);
//        if(nightMode){
//            binding.aSwitch.setChecked(true);
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
//        binding.aSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (nightMode){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    editor = sharedPreferences.edit();
//                    editor.putBoolean("nightMode", false);
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    editor = sharedPreferences.edit();
//                    editor.putBoolean("nightMode", true);
//                }
//                nightMode = !nightMode; // Toggle the value of nightMode
//                editor.apply();
//            }
//        });
        binding.press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                getData();
            }
        });

        LocalDateTime current = null;
        String formatted=null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             current = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, EEEE, HH:mm:ss"); // Format pattern
            formatted = current.format(formatter);
        }
        binding.dateshow.setText(formatted);



        return root;
    }
    public void getData() {
        String api = "0d9a972d0ef6d982113a5c13f2ba45d1";
        String city = binding.editcityname.getText().toString();
        if(city.isEmpty())
        {
            Toast.makeText(getActivity(),"Please Enter City Name",Toast.LENGTH_SHORT).show();
        }
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + api;RequestQueue queue = Volley.newRequestQueue(requireActivity().getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("main");
                    JSONObject jsonObject=response.getJSONObject("wind");
                    org.json.JSONArray weatherArray = response.getJSONArray("weather");

                    JSONObject object1= weatherArray.getJSONObject(0); // Get first object in array

                    String desc = object1.getString("description");
                    binding.descshow.setText(desc);
                    String main = object1.getString("main");
                    binding.mainshow.setText(main);
                    String temp = object.getString("temp");
                    String pressure = object.getString("pressure");
                    String humidity = object.getString("humidity");
                    binding.presshow.setText(pressure);
                    binding.humidshow.setText(humidity);
                    String wind=jsonObject.getString("speed");
                    binding.windshow.setText(wind);


                    double far = Double.parseDouble(temp);
                    far = far - 273.15;
                    temp = String.format("%.2f",far);
                    binding.tempshow.setText(temp); // Corrected spelling
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });
        queue.add(request);
    }
}