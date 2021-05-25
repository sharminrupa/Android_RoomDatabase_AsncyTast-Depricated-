package com.example.androidroom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidroom.Room.Student;

import java.util.ArrayList;
import java.util.Random;

public class CustomAdater extends RecyclerView.Adapter<CustomAdater.MyViewHolder>{

    private Context context;
    private ArrayList<Student> dataSet;

    public CustomAdater(Context context , ArrayList<Student> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_student, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder , int position) {

        TextView tv_reline = holder.tv_reline;
        TextView tv_student_name = holder.tv_student_name;
        TextView tv_contact_no = holder.tv_contact_no;
        ImageView img_gender = holder.img_gender;
        ImageView img_call = holder.img_call;
        Button btn_title = holder.btn_title;

        tv_reline.setText(dataSet.get(position).rollno+"");
        tv_student_name.setText(dataSet.get(position).student_name+"");
        tv_contact_no.setText(dataSet.get(position).contacatno+"");

        if (dataSet.get(position).gender.equalsIgnoreCase("Male")){
            img_gender.setImageResource(R.drawable.male);
        }else if (dataSet.get(position).gender.equalsIgnoreCase("Female")){
            img_gender.setImageResource(R.drawable.female);
        }

        //======== logic to set title start============

        btn_title.setText(dataSet.get(position).student_name.toUpperCase().charAt(0)+"");

        //======== logic to set title end============

        // ================ Random color start =================

        Random random = new Random();

        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        btn_title.setBackgroundColor(Color.rgb(red, green, blue));

        if (dataSet.get(position).contacatno.length()>0){
            img_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + dataSet.get(position).contacatno));
                    context.startActivity(intent);
                }
            });

        }else{
            img_call.setVisibility(View.GONE);
        }


        // ================ Random color end =================


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_reline;
        TextView tv_student_name;
        TextView tv_contact_no;
        ImageView img_gender;
        ImageView img_call;
        Button btn_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_reline = itemView.findViewById(R.id.tv_reline);
            tv_student_name = itemView.findViewById(R.id.tv_student_name);
            tv_contact_no = itemView.findViewById(R.id.tv_contact_no);
            img_gender = itemView.findViewById(R.id.img_gender);
            img_call = itemView.findViewById(R.id.img_call);
            btn_title = itemView.findViewById(R.id.btn_title);

        }
    }

}
