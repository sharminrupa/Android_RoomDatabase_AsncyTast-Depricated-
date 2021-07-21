package com.example.androidroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.androidroom.Room.Student;

public class UpdateActivity extends AppCompatActivity {

    EditText updateId, updateName, updateContactNo;
    Button update;
    RadioButton updateRbtn_male, updateRbtn_female;

    int srollno;
    String st_name, st_contact, sgender = "";

    String srollno_update, st_name_to_update="", st_contact_to_update="", sgender_to_update="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

//        ========================find view by id start ==========================
        updateId = findViewById(R.id.updateId);
        updateName = findViewById(R.id.updateName);
        updateContactNo = findViewById(R.id.updateContactNo);

        updateRbtn_male = findViewById(R.id.updateRbtn_male);
        updateRbtn_female = findViewById(R.id.updateBtn_female);
        update = findViewById(R.id.update);

//        ========================find view by id end ==========================

//        ================Get value form customAdapter start ===============
            Bundle data = getIntent().getExtras();

            if (data != null){

                srollno = data.getInt("rollno");
                st_name = data.getString("student_name");
                st_contact = data.getString("contacatno");
                sgender = data.getString("gender");

            }
//        ================Get value form customAdapter start ===============

//        =================================== Set value ui start=================================//

        updateId.setText(srollno+"");
        updateName.setText(st_name+"");
        updateContactNo.setText(st_contact+"");


        if (sgender.trim().toLowerCase().equalsIgnoreCase("male")){
            updateRbtn_male.setChecked(true);
        }else if (sgender.trim().toLowerCase().equalsIgnoreCase("female")){
            updateRbtn_female.setChecked(true);
        }
//        =================================== Set value ui end=================================


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateName.getText().toString().isEmpty() ||
                        updateContactNo.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Fill details", Toast.LENGTH_SHORT).show();
                }else {
                    srollno_update = updateId.getText().toString().trim();
                    st_name_to_update = updateName.getText().toString().trim();
                    st_contact_to_update = updateContactNo.getText().toString().trim();


                    if (updateRbtn_male.isChecked()){
                        sgender_to_update="Male";
                    }else if (updateRbtn_female.isChecked()){
                        sgender_to_update="Female";
                    }

                    StudentRepository studentRepository = new StudentRepository(getApplicationContext());
                    Student student = new Student(Integer.parseInt(srollno_update), st_name_to_update, st_contact_to_update, sgender_to_update);
                    studentRepository.updateTask(student);

                    Toast.makeText(getApplicationContext(), "Values Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
}