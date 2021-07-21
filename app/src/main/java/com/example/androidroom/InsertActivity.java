package com.example.androidroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.androidroom.Room.Student;

public class InsertActivity extends AppCompatActivity {

    EditText insertId, nameText, contactNo;
    Button submit;
    RadioButton rbtn_male, rbtn_female;

    String st_id, st_name, st_contact, sgender = "Male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        insertId = findViewById(R.id.insertId);
        nameText = findViewById(R.id.nameText);
        contactNo = findViewById(R.id.contactNo);

        rbtn_male = findViewById(R.id.rbtn_male);
        rbtn_female = findViewById(R.id.rbtn_female);
        submit = findViewById(R.id.insert);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insertId.getText().toString().trim().isEmpty() || nameText.getText().toString().trim().isEmpty()
                    || contactNo.getText().toString().trim().isEmpty())
                {

                    Toast.makeText(getApplicationContext(), " Fill Details", Toast.LENGTH_SHORT).show();

                }
                else {

                    st_id = insertId.getText().toString().trim();
                    st_name = nameText.getText().toString().trim();
                    st_contact = contactNo.getText().toString().trim();

                    if (rbtn_male.isChecked()){
                        sgender = "Male";
                    }else if (rbtn_female.isChecked()){
                        sgender = "Female";
                    }


                    StudentRepository studentRepository = new StudentRepository(getApplicationContext());
                    Student student = new Student(Integer.parseInt(st_id), st_name, st_contact, sgender);
                    studentRepository.insertTast(student);

                }
            }
        });

    }
}