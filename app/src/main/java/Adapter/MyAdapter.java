package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniassist.CGPAActivity;
import com.example.uniassist.GoogleMapsActivity;
import com.example.uniassist.MainActivity;
import com.example.uniassist.PreviousYearsPapers;
import com.example.uniassist.R;
import com.example.uniassist.TodoListActivity;
import com.example.uniassist.TravelHelperActivity;
import com.example.uniassist.VITApUpdatesActivity;

import java.util.ArrayList;

import Model.MyModel;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{

    @NonNull
    ArrayList<MyModel> list;
    Context context;
    public MyAdapter(ArrayList<MyModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_home_page,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        MyModel model=list.get(position);
        holder.imageView.setImageResource(model.getImage());
        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDescription());

        switch (position)
        {
            case 0:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, CGPAActivity.class);
                        context.startActivity(intent);
                    }
                });
                holder.desc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, CGPAActivity.class);
                        context.startActivity(intent);
                    }
                });
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, CGPAActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 1:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, VITApUpdatesActivity.class);
                        context.startActivity(intent);
                    }
                });
                holder.desc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, VITApUpdatesActivity.class);
                        context.startActivity(intent);
                    }
                });
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, VITApUpdatesActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 2:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, GoogleMapsActivity.class);
                        context.startActivity(intent);
                    }
                });
                holder.desc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, GoogleMapsActivity.class);
                        context.startActivity(intent);
                    }
                });
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, GoogleMapsActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 3:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, TravelHelperActivity.class);
                        context.startActivity(intent);
                    }
                });
                holder.desc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, TravelHelperActivity.class);
                        context.startActivity(intent);
                    }
                });
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, TravelHelperActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 4:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, TodoListActivity.class);
                        context.startActivity(intent);
                    }
                });
                holder.desc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, TodoListActivity.class);
                        context.startActivity(intent);
                    }
                });
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, TodoListActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 5:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, PreviousYearsPapers.class);
                        context.startActivity(intent);
                    }
                });
                holder.desc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, PreviousYearsPapers.class);
                        context.startActivity(intent);
                    }
                });
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, PreviousYearsPapers.class);
                        context.startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView title;
        TextView desc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView2);
            title=itemView.findViewById(R.id.title);
            desc=itemView.findViewById(R.id.desciption);
        }
    }
}