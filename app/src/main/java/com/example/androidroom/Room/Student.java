package com.example.androidroom.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {

    @PrimaryKey (autoGenerate = true)
    public int rollno;

    @ColumnInfo(name = "student_name")
    public String student_name;

    @ColumnInfo(name = "contacatno")
    public String contacatno;

    @ColumnInfo(name = "gender")
    public String gender;


    public Student(String student_name , String contacatno , String gender) {
        this.student_name = student_name;
        this.contacatno = contacatno;
        this.gender = gender;
    }
}
